package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskHandleBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfMonthChargeBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpArchiveConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskPeriodConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfMonthChargeConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskHandlePostBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.*;
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
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private HfArchiveBaseAdjustService hfArchiveBaseAdjustService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");

    @Override
    public List<HfEmpTaskHandleBo> getEmpTaskHandleData(HfEmpTaskHandlePostBo hfEmpTaskHandleDTO) {
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

        HfEmpTask inputHfEmpTask = new HfEmpTask();
        inputHfEmpTask.setEmpTaskId(empTaskId);
        inputHfEmpTask.setTaskCategory(params.getInteger("taskCategory"));

        // 任务单费用段是否存在判断
        JSONArray operatorListData = params.getJSONArray("operatorListData");
        if (operatorListData == null) {
            switch (inputHfEmpTask.getTaskCategory()) {
                case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
                case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
                case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
                case HfEmpTaskConstant.TASK_CATEGORY_IN_MULTI_TRANS_IN:
                case HfEmpTaskConstant.TASK_CATEGORY_ADJUST_OPEN:
                case HfEmpTaskConstant.TASK_CATEGORY_REPAIR:
                    return JsonResultKit.ofError("当前任务单费用段信息为空");
                default:
                    break;
            }
        }

        inputHfEmpTask.setHfEmpAccount(params.getString("hfEmpAccount"));
//        String startMonth = params.getString("startMonth");
//        if (StringUtils.isNotEmpty(startMonth)) {
//            inputHfEmpTask.setStartMonth(startMonth);
//        }
        inputHfEmpTask.setOperationRemind(params.getInteger("operationRemind"));
        String operationRemindDateStr = params.getString("operationRemindDate");
        if (StringUtils.isNotBlank(operationRemindDateStr)) {
            inputHfEmpTask.setOperationRemindDate(LocalDate.parse(operationRemindDateStr));
        }
        inputHfEmpTask.setHandleRemark(params.getString("handleRemark"));
        inputHfEmpTask.setRejectionRemark(params.getString("rejectionRemark"));
        inputHfEmpTask.setModifiedBy("test"); //TODO
        inputHfEmpTask.setModifiedTime(LocalDateTime.now());

        if (isHandle) {
            ComAccountParamExtBo comAccountParamExtBo = new ComAccountParamExtBo();
            comAccountParamExtBo.setCompanyId(hfEmpTask.getCompanyId());
            List<ComAccountExtBo> hfComAccountList = hfComAccountService.getHfComAccountList(comAccountParamExtBo);
            if (CollectionUtils.isNotEmpty(hfComAccountList)) {
                if (hfComAccountList.size() > 1) {
                    return JsonResultKit.ofError("当前雇员任务单所属的企业账户数据有误");
                }
            } else {
                return JsonResultKit.ofError("当前雇员任务单所属的企业账户不存在");
            }

            JsonResult rlt = setEmpTask(params, inputHfEmpTask);
            if (rlt.getCode() != 200) {
                return rlt;
            }

            // 雇员档案处理
            Long existEmpArchive = (Long) rlt.getData();
            Long newEmpArchive = handleEmpArchive(params, existEmpArchive, inputHfEmpTask);
            this.updateById(inputHfEmpTask);

            if (newEmpArchive != null) {
                inputHfEmpTask.setEmpArchiveId(newEmpArchive);
            } else {
                inputHfEmpTask.setEmpArchiveId(existEmpArchive);
            }

            inputHfEmpTask.setHfType(hfEmpTask.getHfType());
            inputHfEmpTask.setCompanyId(hfEmpTask.getCompanyId());
            inputHfEmpTask.setEmployeeId(hfEmpTask.getEmployeeId());
        } else {
            this.updateById(inputHfEmpTask);
        }

        // 任务单费用段操作处理
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
                        case HfEmpTaskConstant.TASK_CATEGORY_ADJUST_OPEN:
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
        } else {
            String endMonth = params.getString("endMonth");
            if (StringUtils.isNotEmpty(endMonth)) {
                inputHfEmpTask.setEndMonth(endMonth);
            }
            Map<String, Object> condition = new HashMap<>();
            condition.put("emp_task_id", empTaskId);
            condition.put("is_active", 1);

            HfEmpTaskPeriod hfEmpTaskPeriod = new HfEmpTaskPeriod();
            hfEmpTaskPeriod.setCreatedBy("test"); // TODO
            hfEmpTaskPeriod.setEndMonth(endMonth);

            List<HfEmpTaskPeriod> hfEmpTaskPeriodList = hfEmpTaskPeriodService.selectByMap(condition);
            if (CollectionUtils.isNotEmpty(hfEmpTaskPeriodList)) {
                if (hfEmpTaskPeriodList.size() > 1) {
                    return JsonResultKit.ofError("当前雇员任务单费用段数据不正确");
                }

                hfEmpTaskPeriod.setEmpTaskPeriodId(hfEmpTaskPeriodList.get(0).getEmpTaskPeriodId());
            } else {
                hfEmpTaskPeriod.setEmpTaskId(empTaskId);
                hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_NONE);
                hfEmpTaskPeriod.setModifiedTime(LocalDateTime.now());
                hfEmpTaskPeriod.setModifiedBy("test"); // TODO
            }
            hfEmpTaskPeriodService.insertOrUpdate(hfEmpTaskPeriod);

            if (isHandle) {
                // 转出或封存任务单时，没有任务单费用段信息
                switch (inputHfEmpTask.getTaskCategory()) {
                    case HfEmpTaskConstant.TASK_CATEGORY_ADJUST_CLOSE:
                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_MULTI_TRANS_OUT:
                        stopEmpBasePeriod(inputHfEmpTask, hfEmpTaskPeriod);
                        // TODO MonthRecharge数据如何处理，业务待明确
                        break;
                    default:
                        break;
                }
            }
        }
        return JsonResultKit.of(hfEmpTask.getTaskId());
    }

    /**
     * 根据任务单费用分段表数据实装雇员档案费用分段表数据
     *
     * @param hfArchiveBasePeriodList 雇员档案费用分段表数据列表
     * @param hfEmpTask 任务单表数据
     * @param hfEmpTaskPeriod 任务费用分段表数据
     * @param repairHfArchiveBasePeriod 差额补缴的雇员档案费用分段表数据
     */
    private void setHfArchiveBasePeriodList(List<HfArchiveBasePeriod> hfArchiveBasePeriodList, HfEmpTask hfEmpTask, HfEmpTaskPeriod hfEmpTaskPeriod, HfArchiveBasePeriod repairHfArchiveBasePeriod) {
        HfArchiveBasePeriod hfArchiveBasePeriod = new HfArchiveBasePeriod();
        System.out.println("e.getArchiveBasePeriodId() = " + hfEmpTaskPeriod.getArchiveBasePeriodId()); // TODO log
        hfArchiveBasePeriod.setArchiveBasePeriodId(hfEmpTaskPeriod.getArchiveBasePeriodId());
        hfArchiveBasePeriod.setEmpTaskId(hfEmpTaskPeriod.getEmpTaskId());
        hfArchiveBasePeriod.setEmployeeId(hfEmpTask.getEmployeeId());
        hfArchiveBasePeriod.setCompanyId(hfEmpTask.getCompanyId());
        hfArchiveBasePeriod.setBaseAmount(hfEmpTaskPeriod.getBaseAmount());
        hfArchiveBasePeriod.setRatioCom(hfEmpTaskPeriod.getRatioCom());
        hfArchiveBasePeriod.setRatioEmp(hfEmpTaskPeriod.getRatioEmp());
        BigDecimal ratioCom = hfEmpTaskPeriod.getRatioCom();
        BigDecimal ratioEmp = hfEmpTaskPeriod.getRatioEmp();
        BigDecimal amount = hfEmpTaskPeriod.getAmount();
//        if (hfEmpTaskPeriod.getRatioCom() != null) {
//            ratioCom = hfEmpTaskPeriod.getRatioCom();
//        }
//        if (hfEmpTaskPeriod.getRatioEmp() != null) {
//            ratioEmp = hfEmpTaskPeriod.getRatioEmp();
//        }
//        if (hfEmpTaskPeriod.getAmount() != null) {
//            amount = hfEmpTaskPeriod.getAmount();
//        }
        hfArchiveBasePeriod.setRatio(ratioCom.add(ratioEmp).setScale(2, BigDecimal.ROUND_HALF_UP));
//        if (hfArchiveBasePeriod.getRatio().compareTo(BigDecimal.ZERO) > 0) {
        if (repairHfArchiveBasePeriod == null) {
            hfArchiveBasePeriod.setAmount(hfEmpTaskPeriod.getAmount());
            hfArchiveBasePeriod.setAmountEmp(amount.multiply(ratioEmp.divide(hfArchiveBasePeriod.getRatio())).setScale(2, BigDecimal.ROUND_HALF_UP));
            hfArchiveBasePeriod.setComAmount(amount.multiply(ratioCom.divide(hfArchiveBasePeriod.getRatio())).setScale(2, BigDecimal.ROUND_HALF_UP));
        } else {
            // 代码特殊处理，仅为了差额补缴时，不重新计算单月差额
            hfArchiveBasePeriod.setAmountEmp(repairHfArchiveBasePeriod.getAmountEmp());
            hfArchiveBasePeriod.setComAmount(repairHfArchiveBasePeriod.getComAmount());
            hfArchiveBasePeriod.setAmount(repairHfArchiveBasePeriod.getAmountEmp()
                .add(repairHfArchiveBasePeriod.getComAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
            hfArchiveBasePeriod.setDiffRepair(true);
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
     * 新进或转入任务时雇员档案费用分段表数据处理
     *
     * @param hfEmpTask 任务单表数据
     * @param hfEmpTaskPeriodList 任务费用分段表数据列表
     * @return 雇员档案费用分段表数据
     */
    private List<HfArchiveBasePeriod> createEmpBasePeriod(HfEmpTask hfEmpTask, List<HfEmpTaskPeriod> hfEmpTaskPeriodList) {
        List<HfArchiveBasePeriod> hfArchiveBasePeriodList = new ArrayList<>();
        hfEmpTaskPeriodList.stream().forEach(e -> setHfArchiveBasePeriodList(hfArchiveBasePeriodList, hfEmpTask, e, null));
        hfArchiveBasePeriodService.insertOrUpdateBatch(hfArchiveBasePeriodList);
        return hfArchiveBasePeriodList;
    }

    /**
     * 补缴任务时雇员档案费用分段表数据处理
     * 分全额补缴及差额补缴
     *
     * @param hfEmpTask 任务单表数据
     * @param hfEmpTaskPeriodList 任务费用分段表数据列表
     * @return 雇员档案费用分段表数据
     */
    private List<HfArchiveBasePeriod> repairEmpBasePeriod(HfEmpTask hfEmpTask, List<HfEmpTaskPeriod> hfEmpTaskPeriodList) {
        List<HfArchiveBasePeriod> hfArchiveBasePeriodList;
        List<HfArchiveBasePeriod> diffHfArchiveBasePeriodList;
        EntityWrapper<HfArchiveBasePeriod> wrapper = new EntityWrapper<>();
        wrapper.where("employee_id={1} AND is_active = 1", hfEmpTask.getEmployeeId());
        wrapper.orderBy("start_month", true);
        List<HfArchiveBasePeriod> existHfArchiveBasePeriodList = hfArchiveBasePeriodService.selectList(wrapper);

        if (CollectionUtils.isNotEmpty(existHfArchiveBasePeriodList)) {
            hfArchiveBasePeriodList = new ArrayList<>();
            diffHfArchiveBasePeriodList = new ArrayList<>();
            List<HfArchiveBaseAdjust> hfArchiveBaseAdjustList = new ArrayList<>();

            hfEmpTaskPeriodList.stream().forEach(e -> {
                if (StringUtils.isEmpty(e.getStartMonth()) || StringUtils.isEmpty(e.getEndMonth())) {
                    throw new BusinessException("补缴任务费用分段中缴费起始月或缴费截止月为空");
                }
                final YearMonth currentMonth = YearMonth.now();
                YearMonth repairStartMonth = YearMonth.parse(e.getStartMonth(), formatter);
                YearMonth repairEndMonth = YearMonth.parse(e.getEndMonth(), formatter);
                if (repairEndMonth.equals(currentMonth) || repairEndMonth.isAfter(currentMonth)) {
                    throw new BusinessException("补缴任务费用分段中缴费截止月不能在当前年月以后");
                }

                YearMonth[] permitYearMonth = new YearMonth[1];
                boolean isMatch = false;

                for (HfArchiveBasePeriod hfArchiveBasePeriod : existHfArchiveBasePeriodList) {
                    YearMonth startMonth = YearMonth.parse(hfArchiveBasePeriod.getStartMonth(), formatter);
                    YearMonth endMonth = currentMonth;
                    if (StringUtils.isNotEmpty(hfArchiveBasePeriod.getEndMonth())) {
                        endMonth = YearMonth.parse(hfArchiveBasePeriod.getEndMonth(), formatter);
                    }
                    // 匹配费用段，如果任务单费用段包含在雇员档案费用段中，那么支持差额补缴；
                    // 差额补缴时，雇员档案费用段不变，增加雇员调整差异数据
                    if ((repairStartMonth.isAfter(startMonth) || repairStartMonth.equals(startMonth))
                        && (repairEndMonth.isBefore(endMonth) || repairEndMonth.equals(endMonth))) {
                        if (!hfEmpTask.getCompanyId().equals(hfArchiveBasePeriod.getCompanyId())) {
                            throw new BusinessException("补缴任务费用分段中缴费期间与所匹配的费用段不属于同一个客户");
                        }
                        setHfArchiveBaseAdjust(hfArchiveBaseAdjustList, hfEmpTask, e, hfArchiveBasePeriod);
                        setHfArchiveBasePeriodList(diffHfArchiveBasePeriodList, hfEmpTask, e, hfArchiveBasePeriod);
                        isMatch = true;
                        break;
                    } else {
                        // 匹配费用段空档区间，如果任务单费用段包含在雇员档案费用段空档区间中，那么支持全额补缴；
                        // 全额补缴时，更新雇员档案费用段，不需要增加雇员调整差异数据
                        if (permitYearMonth[0] == null) {
                            // 匹配最早的费用段，判断补缴段的截止年月是否早于雇员档案费用段的起始年月
                            if (repairEndMonth.isBefore(startMonth)) {
                                if (!hfEmpTask.getCompanyId().equals(hfArchiveBasePeriod.getCompanyId())) {
                                    throw new BusinessException("补缴任务费用分段中缴费期间与所匹配的费用段不属于同一个客户");
                                }
                                permitYearMonth[0] = endMonth;
                                setHfArchiveBasePeriodList(hfArchiveBasePeriodList, hfEmpTask, e, null);
                                isMatch = true;
                                break;
                            }
                        } else if (endMonth.isBefore(currentMonth)) {
                            // 如果雇员档案费用段的截止年月早于当前年月
                            // 那么判断补缴段是否属于两个雇员档案费用段之间的空档期间
                            if (repairStartMonth.isAfter(permitYearMonth[0]) && repairEndMonth.isBefore(startMonth)) {
                                if (!hfEmpTask.getCompanyId().equals(hfArchiveBasePeriod.getCompanyId())) {
                                    throw new BusinessException("补缴任务费用分段中缴费期间与所匹配的费用段不属于同一个客户");
                                }
                                permitYearMonth[0] = endMonth;
                                setHfArchiveBasePeriodList(hfArchiveBasePeriodList, hfEmpTask, e, null);
                                isMatch = true;
                                break;
                            }
//                        } else {
//                            //
//                            if (repairStartMonth.isAfter(startMonth) && repairEndMonth.isBefore(endMonth)) {
//                                if (!hfEmpTask.getCompanyId().equals(hfArchiveBasePeriod.getCompanyId())) {
//                                    throw new BusinessException("补缴任务费用分段中缴费期间与所匹配的费用段不属于同一个客户");
//                                }
//                                setHfArchiveBasePeriodList(hfArchiveBasePeriodList, hfEmpTask, e, null);
//                                isMatch = true;
//                                break;
//                            }
                        }
                    }
                }

                if (!isMatch) {
                    throw new BusinessException("补缴任务费用分段期间不正确");
                }
            });

            if (CollectionUtils.isNotEmpty(hfArchiveBaseAdjustList)) {
                hfArchiveBaseAdjustService.insertBatch(hfArchiveBaseAdjustList);
            }
        } else {
            // 雇员档案费用段不存在时，数据不正常，需先做新增再做补缴
            throw new BusinessException("当前雇员的雇员汇缴月份段数据不存在，不能补缴");
        }

        if (CollectionUtils.isNotEmpty(hfArchiveBasePeriodList)) {
            hfArchiveBasePeriodService.insertOrUpdateBatch(hfArchiveBasePeriodList);
        }

        if (CollectionUtils.isNotEmpty(diffHfArchiveBasePeriodList)) {
            hfArchiveBasePeriodList.addAll(diffHfArchiveBasePeriodList);
        }
        return hfArchiveBasePeriodList;
    }

    /**
     * 转出或封存任务时雇员档案费用分段表数据处理
     *
     * @param hfEmpTask
     * @param hfEmpTaskPeriod
     * @return
     */
    private HfArchiveBasePeriod stopEmpBasePeriod(HfEmpTask hfEmpTask, HfEmpTaskPeriod hfEmpTaskPeriod) {
        EntityWrapper<HfArchiveBasePeriod> wrapper = new EntityWrapper<>();
        wrapper.where("employee_id={1} AND company_id={2} AND is_active = 1", hfEmpTask.getEmployeeId(), hfEmpTask.getCompanyId());
        wrapper.orderBy("start_month", false);
        List<HfArchiveBasePeriod> existHfArchiveBasePeriodList = hfArchiveBasePeriodService.selectList(wrapper);

        if (CollectionUtils.isNotEmpty(existHfArchiveBasePeriodList)) {
            HfArchiveBasePeriod lastHfArchiveBasePeriod = existHfArchiveBasePeriodList.get(0);
            if (StringUtils.isEmpty(lastHfArchiveBasePeriod.getEndMonth())) {
                throw new BusinessException("当前雇员最后汇缴月份段的缴费截止月已经存在");
            }
            HfArchiveBasePeriod hfArchiveBasePeriod = new HfArchiveBasePeriod();
            hfArchiveBasePeriod.setArchiveBasePeriodId(lastHfArchiveBasePeriod.getArchiveBasePeriodId());
            hfArchiveBasePeriod.setEndMonth(hfEmpTaskPeriod.getEndMonth());
            hfArchiveBasePeriod.setModifiedBy("test"); //TODO
            hfArchiveBasePeriod.setModifiedTime(LocalDateTime.now());
            hfArchiveBasePeriodService.updateById(hfArchiveBasePeriod);

            return hfArchiveBasePeriod;
        } else {
            throw new BusinessException("当前雇员的汇缴月份段数据不存在");
        }
    }

    /**
     * 创建雇员月度汇缴明细库数据
     *
     * @param hfEmpTask 任务单表数据
     * @param hfArchiveBasePeriodList 雇员档案费用分段表数据列表
     */
    private void createHfMonthCharge(HfEmpTask hfEmpTask, List<HfArchiveBasePeriod> hfArchiveBasePeriodList) {
        int[] paymentType = new int[1];
        switch (hfEmpTask.getTaskCategory()) {
            case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
            case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
            case HfEmpTaskConstant.TASK_CATEGORY_IN_MULTI_TRANS_IN:
                paymentType[0] = HfMonthChargeConstant.PAYMENT_TYPE_NEW;
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
                paymentType[0] = HfMonthChargeConstant.PAYMENT_TYPE_OPEN;
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_REPAIR:
                paymentType[0] = HfMonthChargeConstant.PAYMENT_TYPE_REPAIR;
                // 如果是补缴任务单，雇员月度汇缴明细库数据可能会被覆盖
                HfMonthChargeBo hfMonthChargeBo = new HfMonthChargeBo();
                hfMonthChargeBo.setEmpArchiveId(hfEmpTask.getEmpArchiveId());
                hfMonthChargeBo.setHfType(hfEmpTask.getHfType());
                hfMonthChargeBo.setSsMonthBelongStart(hfEmpTask.getStartMonth());
                hfMonthChargeBo.setSsMonthBelongEnd(hfEmpTask.getEndMonth());
                hfMonthChargeBo.setSsMonthBelongEnd(StringUtils.join(new Integer[] {
                    HfMonthChargeConstant.PAYMENT_TYPE_REPAIR,
                    HfMonthChargeConstant.PAYMENT_TYPE_DIFF_REPAIR
                }, ','));
                hfMonthChargeService.inactiveHfMonthCharge(hfMonthChargeBo);
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_ADJUST_OPEN:
                paymentType[0] = HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_OPEN;
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_ADJUST_CLOSE:
                paymentType[0] = HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_CLOSE;
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_MULTI_TRANS_OUT:
                paymentType[0] = HfMonthChargeConstant.PAYMENT_TYPE_CLOSE;
                break;
            default:
                break;
        }
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
            BigDecimal comAmount = e.getComAmount();
            BigDecimal empAmount = e.getAmountEmp();
            BigDecimal amount = e.getAmount();

            if (e.getDiffRepair() != null && e.getDiffRepair()) {
                paymentType[0] = HfMonthChargeConstant.PAYMENT_TYPE_DIFF_REPAIR;
//            } else {
//                comAmount = e.getBaseAmount().multiply(e.getRatioCom()).setScale(2, BigDecimal.ROUND_HALF_UP);
//                empAmount = e.getBaseAmount().multiply(e.getRatioEmp()).setScale(2, BigDecimal.ROUND_HALF_UP);
//                amount = comAmount.add(empAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
            }

            // 根据雇员费用段期间，每月生成一条雇员月度汇缴明细记录
            for(long i = 0; i <= months; i++) {
                HfMonthCharge hfMonthCharge = new HfMonthCharge();
                hfMonthCharge.setEmpArchiveId(e.getEmpArchiveId());
                hfMonthCharge.setEmpTaskId(hfEmpTask.getEmpTaskId());
                hfMonthCharge.setHfMonth(e.getHfMonth());
                hfMonthCharge.setSsMonthBelong(startMonthDate.plusMonths(i).format(formatter));
                hfMonthCharge.setCompanyId(hfEmpTask.getCompanyId());
                hfMonthCharge.setEmployeeId(hfEmpTask.getEmployeeId());
                hfMonthCharge.setHfType(e.getHfType());
                hfMonthCharge.setAmount(amount);
                hfMonthCharge.setComAmount(comAmount);
                hfMonthCharge.setEmpAmount(empAmount);
                hfMonthCharge.setBase(e.getBaseAmount());
                hfMonthCharge.setRatio(e.getRatio());
                hfMonthCharge.setRatioCom(e.getRatioCom());
                hfMonthCharge.setRatioEmp(e.getRatioEmp());
                hfMonthCharge.setPaymentType(paymentType[0]);
                hfMonthCharge.setCreatedBy("test"); // TODO
                hfMonthCharge.setModifiedBy("test"); // TODO
                hfMonthChargeList.add(hfMonthCharge);
            }
            hfMonthChargeService.insertBatch(hfMonthChargeList);
        });
    }

    /**
     * 雇员任务表数据处理
     *
     * @param params 画面输入的参数
     * @param inputHfEmpTask 画面输入的雇员任务数据
     */
    private JsonResult setEmpTask(JSONObject params, HfEmpTask inputHfEmpTask) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("company_id", params.getString("companyId"));
        condition.put("employee_id", params.getString("employeeId"));
        condition.put("is_active", 1);
        boolean isNothing = false;
        Long empArchiveId = null;
        List<HfEmpArchive> hfEmpArchiveList = hfEmpArchiveService.selectByMap(condition);
        if (CollectionUtils.isNotEmpty(hfEmpArchiveList)) {
            hfEmpArchiveList = hfEmpArchiveList.stream()
                .filter(e -> e.getArchiveStatus() == null || e.getArchiveStatus() != HfEmpArchiveConstant.ARCHIVE_STATUS_CLOSED)
                .collect(Collectors.toList());

            if (hfEmpArchiveList.size() > 1) {
                return JsonResultKit.ofError("该雇员的雇员档案数据不正确");
            }

            empArchiveId = hfEmpArchiveList.get(0).getEmpArchiveId();
        } else {
            isNothing = true;
        }

        switch (params.getInteger("taskCategory")) {
            case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
            case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
            case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
            case HfEmpTaskConstant.TASK_CATEGORY_IN_MULTI_TRANS_IN:
                if (CollectionUtils.isNotEmpty(hfEmpArchiveList)) {
                    return JsonResultKit.ofError("雇员档案已存在，且非封存状态");
                }
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_REPAIR:
            case HfEmpTaskConstant.TASK_CATEGORY_ADJUST_OPEN:
                if (isNothing) {
                    return JsonResultKit.ofError("雇员档案不存在");
                }
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_ADJUST_CLOSE:
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_MULTI_TRANS_OUT:
                if (CollectionUtils.isEmpty(hfEmpArchiveList)) {
                    return JsonResultKit.ofError("非封存状态雇员档案不存在");
                }
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_TRANS_TASK:
            case HfEmpTaskConstant.TASK_CATEGORY_SPEC_TASK:
            case HfEmpTaskConstant.TASK_CATEGORY_TURN_OVER:
                // TODO 这3种？这里不处理？
                break;
            default:
                break;
        }

        inputHfEmpTask.setTaskStatus(HfEmpTaskConstant.TASK_STATUS_HANDLED);
        inputHfEmpTask.setHandleStatus(2); // TODO 1.收缴材料 skip？
        inputHfEmpTask.setHandleDate(YearMonth.now().format(formatter));
        inputHfEmpTask.setHandleUserId("test"); // TODO
        inputHfEmpTask.setHandleUserName("test"); // TODO

        return JsonResultKit.of(empArchiveId);
    }

    /**
     * 雇员档案表数据处理
     *
     * @param params 画面传入参数
     * @param inputHfEmpTask 任务单表数据
     */
    private Long handleEmpArchive(JSONObject params, Long empArchiveId, HfEmpTask inputHfEmpTask) {
        HfEmpArchive hfEmpArchive = new HfEmpArchive();
        System.out.println("inputHfEmpTask.getEmpArchiveId() = " + inputHfEmpTask.getEmpArchiveId()); // TODO Log
        hfEmpArchive.setStartMonth(inputHfEmpTask.getStartMonth());
//        hfEmpArchive.setEndMonth(inputHfEmpTask.getEndMonth());
        hfEmpArchive.setOperationRemind(inputHfEmpTask.getOperationRemind());
        hfEmpArchive.setOperationRemindDate(inputHfEmpTask.getOperationRemindDate());
        setEmpArchiveStatus(hfEmpArchive, inputHfEmpTask.getTaskCategory());

        hfEmpArchive.setModifiedBy("test"); // TODO
        boolean isNew = false;

        if (empArchiveId == null) {
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
            isNew = true;
        } else {
            hfEmpArchive.setEmpArchiveId(empArchiveId);
            hfEmpArchive.setModifiedTime(LocalDateTime.now());
        }
        hfEmpArchiveService.insertOrUpdate(hfEmpArchive);

        if (isNew) {
            return hfEmpArchive.getEmpArchiveId();
        }
        return null;
    }

    /**
     * 雇员公积金历史月份调整差异表数据处理
     *
     * @param hfArchiveBaseAdjustList 雇员公积金历史月份调整差异数据列表
     * @param hfEmpTask 任务单表数据
     * @param hfEmpTaskPeriod 雇员档案费用分段表数据
     */
    private void setHfArchiveBaseAdjust(List<HfArchiveBaseAdjust> hfArchiveBaseAdjustList,
                                        HfEmpTask hfEmpTask,
                                        HfEmpTaskPeriod hfEmpTaskPeriod,
                                        HfArchiveBasePeriod hfArchiveBasePeriod) {
        String startMonth = hfEmpTaskPeriod.getStartMonth();
        String endMonth = hfEmpTaskPeriod.getEndMonth();
        HfArchiveBaseAdjust hfArchiveBaseAdjust = new HfArchiveBaseAdjust();
        hfArchiveBaseAdjust.setEmpTaskId(hfEmpTask.getEmpTaskId());
        hfArchiveBaseAdjust.setHfMonth(hfEmpTaskPeriod.getHfMonth());
        hfArchiveBaseAdjust.setCompanyId(hfEmpTask.getCompanyId());
        hfArchiveBaseAdjust.setEmployeeId(hfEmpTask.getEmployeeId());
        hfArchiveBaseAdjust.setEmpArchiveId(hfEmpTask.getEmpArchiveId());
        hfArchiveBaseAdjust.setStartMonth(startMonth);
        hfArchiveBaseAdjust.setEndMonth(endMonth);
        hfArchiveBaseAdjust.setNewBaseAmount(hfEmpTaskPeriod.getBaseAmount());
        hfArchiveBaseAdjust.setRatio(hfEmpTaskPeriod.getRatio());
        hfArchiveBaseAdjust.setRatioCom(hfEmpTaskPeriod.getRatioCom());
        hfArchiveBaseAdjust.setRatioEmp(hfEmpTaskPeriod.getRatioEmp());

        BigDecimal months = BigDecimal.valueOf(YearMonth.parse(startMonth, formatter).until(YearMonth.parse(endMonth, formatter), ChronoUnit.MONTHS) + 1);
        BigDecimal singleComDiffAmount = hfEmpTaskPeriod.getBaseAmount().multiply(hfEmpTaskPeriod.getRatioCom())
            .subtract(hfArchiveBasePeriod.getBaseAmount().multiply(hfArchiveBasePeriod.getRatioCom()))
            .setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal singleEmpDiffAmount = hfEmpTaskPeriod.getBaseAmount().multiply(hfEmpTaskPeriod.getRatioEmp())
            .subtract(hfArchiveBasePeriod.getBaseAmount().multiply(hfArchiveBasePeriod.getRatioEmp()))
            .setScale(2, BigDecimal.ROUND_HALF_UP);

        BigDecimal comDiffAmount = singleComDiffAmount.multiply(months).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal empDiffAmount = singleEmpDiffAmount.multiply(months).setScale(2, BigDecimal.ROUND_HALF_UP);
        hfArchiveBaseAdjust.setComDiffSumAmount(comDiffAmount);
        hfArchiveBaseAdjust.setEmpDiffSumAmount(empDiffAmount);
        hfArchiveBaseAdjust.setComempSumDiffAmount(comDiffAmount.add(empDiffAmount).setScale(2, BigDecimal.ROUND_HALF_UP));
        hfArchiveBaseAdjust.setCreatedBy("test"); //TODO
        hfArchiveBaseAdjust.setModifiedBy("test"); //TODO
        hfArchiveBaseAdjustList.add(hfArchiveBaseAdjust);

        // 仅是为了提高执行效率，在后面生成雇员月度汇缴明细库时无需重新计算，借用该两个字段（差额补缴时不更新档案费用分段表）
        hfArchiveBasePeriod.setComAmount(singleComDiffAmount);
        hfArchiveBasePeriod.setAmountEmp(singleEmpDiffAmount);
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
