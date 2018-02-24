package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.HfComAccountDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.HfComAccountParamDto;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskHandleBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.*;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.constant.HfEmpArchiveConstant;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.constant.HfMonthChargeConstant;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto.HfEmpTaskHandleDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class HfEmpTaskHandleServiceImpl extends ServiceImpl<HfEmpTaskMapper, HfEmpTask> implements HfEmpTaskHandleService {
    @Autowired
    private HfEmpArchiveService hfEmpArchiveService;
    @Autowired
    private HfMonthChargeService hfMonthChargeService;
    @Autowired
    private HfArchiveBasePeriodService hfArchiveBasePeriodService;
    @Autowired
    private HfEmpTaskPeriodService hfEmpTaskPeriodService;
    @Autowired
    private HfComAccountService hfComAccountService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");

    @Override
    public List<HfEmpTaskHandleBo> getEmpTaskHandleData(HfEmpTaskHandleDTO hfEmpTaskHandleDTO) {
        return baseMapper.getEmpTaskHandleData(hfEmpTaskHandleDTO);
    }

    /**
     * 雇员任务单相关数据保存
     *
     * @param params JSON格式对象参数
     * @param isHandle 是否办理
     * @return JSON格式处理结果
     */
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public JsonResult inputDataSave(JSONObject params, boolean isHandle) {
        Long empTaskId = params.getLong("empTaskId");
        Integer taskStatus = params.getInteger("taskStatus");
        HfEmpTask hfEmpTask = this.selectById(empTaskId);
        if (hfEmpTask == null || !hfEmpTask.getActive()) {
            return JsonResultKit.ofError("当前任务单已不存在");
        }
        if (StringUtils.isEmpty(hfEmpTask.getTaskId())) {
            return JsonResultKit.ofError("当前任务单TaskId为空");
        }
        if ((hfEmpTask.getTaskStatus() != null && !hfEmpTask.getTaskStatus().equals(taskStatus))
            || (hfEmpTask.getTaskStatus() == null && taskStatus != HfEmpTaskConstant.TASK_STATUS_UNHANDLED)) {
            return JsonResultKit.ofError("当前雇员任务单状态已变更，处理失败");
        }
        if (isHandle) {
            HfComAccountParamDto hfComAccountParamDto = new HfComAccountParamDto();
            hfComAccountParamDto.setCompanyId(hfEmpTask.getCompanyId());
            List<HfComAccountDTO> hfComAccountList = hfComAccountService.getHfComAccountList(hfComAccountParamDto);
            if (CollectionUtils.isNotEmpty(hfComAccountList)) {
                if (hfComAccountList.size() > 1) {
                    return JsonResultKit.ofError("当前雇员任务单所属的企业账户数据有误");
                }
            } else {
                return JsonResultKit.ofError("当前雇员任务单所属的企业账户不存在");
            }
        }

        HfEmpTask inputHfEmpTask = new HfEmpTask();
        if (isHandle) {
            handleEmpTask(hfEmpTask, inputHfEmpTask);
        }
        inputHfEmpTask.setEmpTaskId(empTaskId);
        inputHfEmpTask.setTaskCategory(params.getInteger("taskCategory"));
        inputHfEmpTask.setHfEmpAccount(params.getString("hfEmpAccount"));
        inputHfEmpTask.setStartMonth(params.getString("startMonth"));
        inputHfEmpTask.setOperationRemind(params.getInteger("operationRemind"));
        String operationRemindDateStr = params.getString("operationRemindDate");
        if (StringUtils.isNotBlank(operationRemindDateStr)) {
            inputHfEmpTask.setOperationRemindDate(LocalDate.parse(operationRemindDateStr));
        }
        inputHfEmpTask.setHandleRemark(params.getString("handleRemark"));
        inputHfEmpTask.setRejectionRemark(params.getString("rejectionRemark"));
        inputHfEmpTask.setModifiedBy("test"); //TODO
        inputHfEmpTask.setModifiedTime(LocalDateTime.now());

        this.updateById(inputHfEmpTask);

        if (isHandle) {
            inputHfEmpTask.setHfType(hfEmpTask.getHfType());
            inputHfEmpTask.setCompanyId(hfEmpTask.getCompanyId());
            inputHfEmpTask.setEmployeeId(hfEmpTask.getEmployeeId());
            handleEmpArchive(params, inputHfEmpTask);
        }

        JSONArray operatorListData = params.getJSONArray("operatorListData");
        if (operatorListData != null) {
            List<HfEmpTaskPeriod> hfEmpTaskPeriodList = operatorListData.toJavaList(HfEmpTaskPeriod.class);
            if (hfEmpTaskPeriodList.size() > 0) {
                hfEmpTaskPeriodList.stream().forEach(e -> {
                    if (e.getEmpTaskPeriodId() != null) {
                        e.setModifiedBy("test"); // TODO
                        e.setModifiedTime(LocalDateTime.now());
                    } else {
                        e.setEmpTaskId(empTaskId);
                        e.setCreatedBy("test"); //TODO
                        e.setModifiedBy("test"); //TODO
                    }
                });
                hfEmpTaskPeriodService.insertOrUpdateBatch(hfEmpTaskPeriodList);

                if (isHandle) {
                    List<HfArchiveBasePeriod> hfArchiveBasePeriodList;
                    switch (inputHfEmpTask.getTaskCategory()) {
                        case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
                        case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
                        case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
                        case HfEmpTaskConstant.TASK_CATEGORY_IN_MULTI_TRANS_IN:
                            hfArchiveBasePeriodList = createEmpBasePeriod(inputHfEmpTask, hfEmpTaskPeriodList);
                            createHfMonthCharge(inputHfEmpTask, hfArchiveBasePeriodList);
                            break;
                        case HfEmpTaskConstant.TASK_CATEGORY_REPAIR:
                            hfArchiveBasePeriodList = repairEmpBasePeriod(inputHfEmpTask, hfEmpTaskPeriodList);
                            createHfMonthCharge(inputHfEmpTask, hfArchiveBasePeriodList);
                            break;

                        default:
                            break;
                    }
                }
            }
        }
        return JsonResultKit.of(hfEmpTask.getTaskId());
    }

    /**
     * 雇员任务表数据处理
     *
     * @param origEmpTask 既存的雇员任务数据
     * @param inputHfEmpTask 画面输入的雇员任务数据
     */
    private void handleEmpTask(HfEmpTask origEmpTask, HfEmpTask inputHfEmpTask) {
        System.out.println("origEmpTask.getEmpArchiveId() = " + origEmpTask.getEmpArchiveId()); // TODO log
        inputHfEmpTask.setEmpArchiveId(origEmpTask.getEmpArchiveId());
        inputHfEmpTask.setTaskStatus(HfEmpTaskConstant.TASK_STATUS_HANDLED);
        inputHfEmpTask.setHandleStatus(2); // TODO 1.收缴材料 skip？
        inputHfEmpTask.setHandleDate(YearMonth.now().format(formatter));
        inputHfEmpTask.setHandleUserId("test"); // TODO
        inputHfEmpTask.setHandleUserName("test"); // TODO
    }

    /**
     * 根据任务单费用分段表数据实装雇员档案费用分段表数据
     *
     * @param hfArchiveBasePeriodList 雇员档案费用分段表数据
     * @param hfEmpTask 任务单表数据
     * @param hfEmpTaskPeriod 任务费用分段表数据
     */
    private void setHfArchiveBasePeriodList(List<HfArchiveBasePeriod> hfArchiveBasePeriodList, HfEmpTask hfEmpTask, HfEmpTaskPeriod hfEmpTaskPeriod) {
        HfArchiveBasePeriod hfArchiveBasePeriod = new HfArchiveBasePeriod();
        System.out.println("e.getArchiveBasePeriodId() = " + hfEmpTaskPeriod.getArchiveBasePeriodId()); // TODO log
        hfArchiveBasePeriod.setArchiveBasePeriodId(hfEmpTaskPeriod.getArchiveBasePeriodId());
        hfArchiveBasePeriod.setEmpTaskId(hfEmpTaskPeriod.getEmpTaskId());
        hfArchiveBasePeriod.setEmployeeId(hfEmpTask.getEmployeeId());
        hfArchiveBasePeriod.setCompanyId(hfEmpTask.getCompanyId());
        hfArchiveBasePeriod.setAmount(hfEmpTaskPeriod.getAmount());
        hfArchiveBasePeriod.setBaseAmount(hfEmpTaskPeriod.getBaseAmount());
        hfArchiveBasePeriod.setRatioCom(hfEmpTaskPeriod.getRatioCom());
        hfArchiveBasePeriod.setRatioEmp(hfEmpTaskPeriod.getRatioEmp());
        BigDecimal ratioCom = BigDecimal.ZERO;
        BigDecimal ratioEmp = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        if (hfEmpTaskPeriod.getRatioCom() != null) {
            ratioCom = hfEmpTaskPeriod.getRatioCom();
        }
        if (hfEmpTaskPeriod.getRatioEmp() != null) {
            ratioEmp = hfEmpTaskPeriod.getRatioEmp();
        }
        if (hfEmpTaskPeriod.getAmount() != null) {
            amount = hfEmpTaskPeriod.getAmount();
        }
        hfArchiveBasePeriod.setRatio(ratioCom.add(ratioEmp).setScale(2, BigDecimal.ROUND_HALF_UP));
        if (hfArchiveBasePeriod.getRatio().compareTo(BigDecimal.ZERO) > 0) {
            hfArchiveBasePeriod.setAmountEmp(amount.multiply(ratioEmp.divide(hfArchiveBasePeriod.getRatio())).setScale(2, BigDecimal.ROUND_HALF_UP));
            hfArchiveBasePeriod.setComAmount(amount.multiply(ratioCom.divide(hfArchiveBasePeriod.getRatio())).setScale(2, BigDecimal.ROUND_HALF_UP));
        } else {
            hfArchiveBasePeriod.setAmountEmp(BigDecimal.ZERO);
            hfArchiveBasePeriod.setComAmount(BigDecimal.ZERO);
        }

        hfArchiveBasePeriod.setStartMonth(hfEmpTaskPeriod.getStartMonth());
        hfArchiveBasePeriod.setEndMonth(hfEmpTaskPeriod.getEndMonth());
        hfArchiveBasePeriod.setHfMonth(hfEmpTaskPeriod.getHfMonth());
        hfArchiveBasePeriod.setRepairReason(hfEmpTaskPeriod.getRepairReason());
        hfArchiveBasePeriod.setModifiedBy("test"); // TODO

        if (hfArchiveBasePeriod.getArchiveBasePeriodId() == null) {
            hfArchiveBasePeriod.setCreatedBy("test"); // TODO
            hfArchiveBasePeriod.setEmpArchiveId(hfEmpTask.getEmpArchiveId());
            hfArchiveBasePeriod.setRemitWay(hfEmpTaskPeriod.getRemitWay());
            hfArchiveBasePeriod.setHfType(hfEmpTask.getHfType());
        } else {
            hfArchiveBasePeriod.setModifiedTime(LocalDateTime.now());
        }
        hfArchiveBasePeriodList.add(hfArchiveBasePeriod);
    }

    /**
     * 新增任务时雇员档案费用分段表数据处理
     *
     * @param hfEmpTask 任务单表数据
     * @param hfEmpTaskPeriodList 任务费用分段表数据
     * @return 雇员档案费用分段表数据
     */
    private List<HfArchiveBasePeriod> createEmpBasePeriod(HfEmpTask hfEmpTask, List<HfEmpTaskPeriod> hfEmpTaskPeriodList) {
        List<HfArchiveBasePeriod> hfArchiveBasePeriodList = new ArrayList<>();
        hfEmpTaskPeriodList.stream().forEach(e -> setHfArchiveBasePeriodList(hfArchiveBasePeriodList, hfEmpTask, e));
        hfArchiveBasePeriodService.insertOrUpdateBatch(hfArchiveBasePeriodList);
        return hfArchiveBasePeriodList;
    }

    /**
     * 补缴任务时雇员档案费用分段表数据处理
     * 分全额补缴及差额补缴
     *
     * @param hfEmpTask 任务单表数据
     * @param hfEmpTaskPeriodList 任务费用分段表数据
     * @return 雇员档案费用分段表数据
     */
    private List<HfArchiveBasePeriod> repairEmpBasePeriod(HfEmpTask hfEmpTask, List<HfEmpTaskPeriod> hfEmpTaskPeriodList) {
        List<HfArchiveBasePeriod> hfArchiveBasePeriodList = new ArrayList<>();
        EntityWrapper<HfArchiveBasePeriod> wrapper = new EntityWrapper<>();
        wrapper.where("company_id={0} AND employee_id={1} AND is_active = 1", hfEmpTask.getCompanyId(), hfEmpTask.getEmployeeId());
        wrapper.orderBy("start_month", true);
        List<HfArchiveBasePeriod> existHfArchiveBasePeriodList = hfArchiveBasePeriodService.selectList(wrapper);

        if (CollectionUtils.isNotEmpty(existHfArchiveBasePeriodList)) {
            YearMonth permitStartMonth;
            YearMonth permitEndMonth;

            hfEmpTaskPeriodList.stream().forEach(e -> {
                if (StringUtils.isEmpty(e.getStartMonth()) || StringUtils.isEmpty(e.getEndMonth())) {
                    throw new BusinessException("补缴任务费用分段表中缴费起始月或缴费截止月为空");
                }
                YearMonth repairStartMonth = YearMonth.parse(e.getStartMonth(), formatter);
                YearMonth repairEndMonth = YearMonth.parse(e.getEndMonth(), formatter);

                existHfArchiveBasePeriodList.stream().forEach(o -> {
                    YearMonth startMonth = YearMonth.parse(o.getStartMonth(), formatter);
                    YearMonth endMonth = YearMonth.now();
                    if (StringUtils.isNotEmpty(o.getEndMonth())) {
                        endMonth = YearMonth.parse(o.getEndMonth(), formatter);
                    }
                    // 匹配费用段，如果任务单费用段包含在雇员档案费用段中，那么支持差额补缴；
                    // 差额补缴时，雇员档案费用段不变，增加雇员调整差异数据
                    if ((repairStartMonth.isAfter(startMonth) || repairStartMonth.equals(startMonth))
                        && (repairEndMonth.isBefore(endMonth) || repairEndMonth.equals(endMonth))) {
//                        throw new BusinessException("补缴任务费用分段表中缴费期间与雇员档案费用分段表中缴费期间交叉");

                    } else {
//                        permitEndMonth = startMonth;
                    }
                });

                setHfArchiveBasePeriodList(hfArchiveBasePeriodList, hfEmpTask, e);
            });
        } else {
            // 雇员档案费用段不存在时，数据不正常，需先做新增再做补缴
            throw new BusinessException("当前雇员的雇员汇缴月份段数据不存在，不能补缴");
        }
        hfArchiveBasePeriodService.insertOrUpdateBatch(hfArchiveBasePeriodList);
        return hfArchiveBasePeriodList;
    }

    /**
     * 创建雇员月度汇缴明细库数据
     *
     * @param hfEmpTask 任务单表数据
     * @param hfArchiveBasePeriodList 雇员档案费用分段表数据
     */
    private void createHfMonthCharge(HfEmpTask hfEmpTask, List<HfArchiveBasePeriod> hfArchiveBasePeriodList) {
        int temp = 0;
        switch (hfEmpTask.getTaskCategory()) {
            case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
            case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
            case HfEmpTaskConstant.TASK_CATEGORY_IN_MULTI_TRANS_IN:
                temp = HfMonthChargeConstant.PAYMENT_TYPE_NORMAL;
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
                temp = HfMonthChargeConstant.PAYMENT_TYPE_OPEN;
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_REPAIR:
                temp = HfMonthChargeConstant.PAYMENT_TYPE_REPAIR;
            default:
                break;
        }
        final int paymentType = temp;
        hfArchiveBasePeriodList.stream().forEach(e -> {
            String startMonth = e.getStartMonth();
            if (StringUtils.isEmpty(startMonth)) {
                throw new BusinessException("雇员档案费用分段表中缴费起始月为空");
            }
            YearMonth startMonthDate = YearMonth.parse(startMonth, formatter);
            String endMonth = e.getEndMonth();
            YearMonth endMonthDate = startMonthDate;
            if (StringUtils.isNotEmpty(endMonth)) {
                endMonthDate = YearMonth.parse(endMonth, formatter);
            }
            long months = startMonthDate.until(endMonthDate, ChronoUnit.MONTHS);
            List<HfMonthCharge> hfMonthChargeList = new ArrayList<>();
            for(long i = 0; i <= months; i++) {
                HfMonthCharge hfMonthCharge = new HfMonthCharge();
                hfMonthCharge.setEmpArchiveId(e.getEmpArchiveId());
                hfMonthCharge.setEmpTaskId(hfEmpTask.getEmpTaskId());
                hfMonthCharge.setComAmount(e.getComAmount());
                hfMonthCharge.setHfMonth(startMonthDate.plusMonths(i).format(formatter));
                hfMonthCharge.setCompanyId(hfEmpTask.getCompanyId());
                hfMonthCharge.setEmployeeId(hfEmpTask.getEmployeeId());
                hfMonthCharge.setHfType(e.getHfType());
                hfMonthCharge.setAmount(e.getAmount());
                hfMonthCharge.setComAmount(e.getComAmount());
                hfMonthCharge.setEmpAmount(e.getAmountEmp());
                hfMonthCharge.setBase(e.getBaseAmount());
                hfMonthCharge.setRatio(e.getRatio());
                hfMonthCharge.setRatioCom(e.getRatioCom());
                hfMonthCharge.setRatioEmp(e.getRatioEmp());
                hfMonthCharge.setPaymentType(paymentType);
                hfMonthCharge.setCreatedBy("test"); // TODO
                hfMonthCharge.setModifiedBy("test"); // TODO
                hfMonthChargeList.add(hfMonthCharge);
            }
            hfMonthChargeService.insertBatch(hfMonthChargeList);
        });
    }

    /**
     * 雇员档案表数据处理
     *
     * @param params 画面传入参数
     * @param inputHfEmpTask 任务单表数据
     */
    private void handleEmpArchive(JSONObject params, HfEmpTask inputHfEmpTask) {
        HfEmpArchive hfEmpArchive = new HfEmpArchive();
        System.out.println("inputHfEmpTask.getEmpArchiveId() = " + inputHfEmpTask.getEmpArchiveId()); // TODO Log
        hfEmpArchive.setStartMonth(inputHfEmpTask.getStartMonth());
//        hfEmpArchive.setEndMonth(inputHfEmpTask.getEndMonth());
        hfEmpArchive.setOperationRemind(inputHfEmpTask.getOperationRemind());
        hfEmpArchive.setOperationRemindDate(inputHfEmpTask.getOperationRemindDate());
        setEmpArchiveStatus(hfEmpArchive, inputHfEmpTask.getTaskCategory());

        hfEmpArchive.setModifiedBy("test"); // TODO
        if (hfEmpArchive.getEmpArchiveId() == null) {
            hfEmpArchive.setEmpArchiveId(inputHfEmpTask.getEmpArchiveId());
            hfEmpArchive.setCompanyId(params.getString("companyId"));
            hfEmpArchive.setEmployeeId(params.getString("employeeId"));
            hfEmpArchive.setComAccountId(params.getInteger("comAccountId"));
            hfEmpArchive.setHfType(inputHfEmpTask.getHfType());
            if (hfEmpArchive.getHfType() == 1) {
                hfEmpArchive.setComAccountClassId(params.getLong("basicComAccountClassId"));
            } else {
                hfEmpArchive.setComAccountClassId(params.getLong("addedComAccountClassId"));
                hfEmpArchive.setBelongEmpArchiveId(params.getLong("belongEmpArchiveId"));
            }

            hfEmpArchive.setHfEmpAccount(inputHfEmpTask.getHfEmpAccount());
            hfEmpArchive.setCreatedBy("test"); // TODO
        } else {
            hfEmpArchive.setModifiedTime(LocalDateTime.now());
        }
        hfEmpArchiveService.insertOrUpdate(hfEmpArchive);
        inputHfEmpTask.setEmpArchiveId(hfEmpArchive.getEmpArchiveId());
    }

    /**
     * 根据任务单类型及雇员档案当前原始状态来设置雇员档案中的任务单状态及原始状态
     *
     * @param hfEmpArchive 雇员档案当前原始状态
     * @param taskCategory 任务单类型
     */
    private void setEmpArchiveStatus(HfEmpArchive hfEmpArchive, Integer taskCategory) {
        Integer origStatus = hfEmpArchive.getArchiveStatus();

        switch (taskCategory) {
            case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
            case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
            case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
            case HfEmpTaskConstant.TASK_CATEGORY_IN_MULTI_TRANS_IN:
                hfEmpArchive.setArchiveTaskStatus(HfEmpArchiveConstant.ARCHIVE_TASK_STATUS_HANDLED);
                hfEmpArchive.setArchiveStatus(HfEmpArchiveConstant.ARCHIVE_STATUS_HANDLED);
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_ADJUST_CLOSE:
                hfEmpArchive.setArchiveTaskStatus(HfEmpArchiveConstant.ARCHIVE_TASK_STATUS_CLOSED);
                hfEmpArchive.setArchiveStatus(HfEmpArchiveConstant.ARCHIVE_STATUS_CLOSED);
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_ADJUST_OPEN:
                hfEmpArchive.setArchiveTaskStatus(HfEmpArchiveConstant.ARCHIVE_TASK_STATUS_HANDLED);
                hfEmpArchive.setArchiveStatus(HfEmpArchiveConstant.ARCHIVE_STATUS_HANDLED);
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_REPAIR:
                if (origStatus == null || origStatus == HfEmpArchiveConstant.ARCHIVE_STATUS_CLOSED) {
                    hfEmpArchive.setArchiveTaskStatus(HfEmpArchiveConstant.ARCHIVE_TASK_STATUS_CLOSED);
                    hfEmpArchive.setArchiveStatus(HfEmpArchiveConstant.ARCHIVE_STATUS_CLOSED);
                } else if (origStatus == HfEmpArchiveConstant.ARCHIVE_STATUS_HANDLED || origStatus == HfEmpArchiveConstant.ARCHIVE_STATUS_COMPLETED) {
                    hfEmpArchive.setArchiveTaskStatus(HfEmpArchiveConstant.ARCHIVE_TASK_STATUS_HANDLED);
                    hfEmpArchive.setArchiveStatus(HfEmpArchiveConstant.ARCHIVE_STATUS_HANDLED);
                }
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_MULTI_TRANS_OUT:
                hfEmpArchive.setArchiveTaskStatus(HfEmpArchiveConstant.ARCHIVE_TASK_STATUS_CLOSED);
                hfEmpArchive.setArchiveStatus(HfEmpArchiveConstant.ARCHIVE_STATUS_CLOSED);
                break;
            default:
                break;
        }
    }
}
