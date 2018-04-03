package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afcompanycenter.commandservice.api.dto.employee.AfEmpSocialUpdateDateDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.utils.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.utils.LogMessage;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpArchiveConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskPeriodConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfMonthChargeConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.TaskSheetRequestDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.CalculateSocialUtils;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.kit.DateKit;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.GetSSPItemsRequestDTO;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.GetSSPItemsResposeDTO;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.SSPItemDTO;
import com.ciicsh.gto.commonservice.util.dto.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
    @Autowired
    private CommonApiUtils commonApiUtils;
    @Autowired
    private LogApiUtil logApiUtil;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");
    private DateTimeFormatter yyyyMMddFormatter = DateTimeFormatter.ofPattern("uuuuMMdd");

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
        if (StringUtils.isEmpty(hfEmpTask.getBusinessInterfaceId())) {
            return JsonResultKit.ofError("当前任务单BusinessInterfaceId为空");
        }
        if ((hfEmpTask.getTaskStatus() != null && !hfEmpTask.getTaskStatus().equals(taskStatus))
            || (hfEmpTask.getTaskStatus() == null && taskStatus != HfEmpTaskConstant.TASK_STATUS_UNHANDLED)) {
            return JsonResultKit.ofError("当前雇员任务单状态已变更，处理失败");
        }

        if (isHandle && hfEmpTask.getIsChange() == HfEmpTaskConstant.IS_CHANGE_YES
            && (hfEmpTask.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_IN_ADD
                || hfEmpTask.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN
                || hfEmpTask.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN
                || hfEmpTask.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_FLOP_ADD
                || hfEmpTask.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_IN
                || hfEmpTask.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_FLOP_OPEN
                )
            ) {
            Map<String, Object> condition = new HashMap<>();
            condition.put("company_id", hfEmpTask.getCompanyId());
            condition.put("employee_id", hfEmpTask.getEmployeeId());
            condition.put("process_category", hfEmpTask.getProcessCategory());
            condition.put("task_category", hfEmpTask.getTaskCategory());
            condition.put("hf_type", hfEmpTask.getHfType());
            condition.put("is_change", HfEmpTaskConstant.IS_CHANGE_NO);
            condition.put("task_status", HfEmpTaskConstant.TASK_STATUS_HANDLED);
            condition.put("is_active", hfEmpTask.getActive());
            List<HfEmpTask> hfEmpTaskList = this.selectByMap(condition);

            if (CollectionUtils.isNotEmpty(hfEmpTaskList)) {
                if (hfEmpTaskList.size() > 1) {
                    return JsonResultKit.ofError("相同雇员的雇员新增任务单已办理多次，数据不正确");
                }

                List<Long> empTaskIdList = new ArrayList<>();
                empTaskIdList.add(hfEmpTaskList.get(0).getEmpTaskId());

                hfMonthChargeService.deleteHfMonthCharges(empTaskIdList);
                hfArchiveBaseAdjustService.deleteHfArchiveBaseAdjusts(empTaskIdList);
                hfArchiveBasePeriodService.deleteHfArchiveBasePeriods(empTaskIdList);
                hfEmpArchiveService.deleteHfEmpArchiveByEmpTaskIds(empTaskIdList);
            }
        }

        HfEmpTask inputHfEmpTask = new HfEmpTask();
        inputHfEmpTask.setEmpTaskId(empTaskId);
//        Integer dictTaskCategory = params.getInteger("dictTaskCategory");
        Integer taskCategory = params.getInteger("taskCategory");
        inputHfEmpTask.setTaskCategory(taskCategory);

        String dicItemCode = DictUtil.DICT_ITEM_ID_FUND_BASIC;
        if (hfEmpTask.getHfType() == 2) {
            dicItemCode = DictUtil.DICT_ITEM_ID_FUND_ADDED;
        }
        String policyId = hfEmpTask.getPolicyDetailId();
        Integer welfareUnit = hfEmpTask.getWelfareUnit();
        String effectiveMonth = hfEmpTask.getStartMonth();
        if (StringUtils.isEmpty(effectiveMonth)) {
            effectiveMonth = hfEmpTask.getEndMonth();
        }
        int[] roundTypes = null;
        if (StringUtils.isNotEmpty(policyId)) {
            roundTypes = getRoundTypeProxy(policyId, welfareUnit, effectiveMonth, dicItemCode);
            if (roundTypes != null) {
                if (roundTypes.length != 2) {
                    return JsonResultKit.ofError("内控中心取得进位方式不正确");
                } else {
                    if (roundTypes[0] < 1 || roundTypes[0] > 10) {
                        roundTypes[0] = 1;
                    }
                    if (roundTypes[1] < 1 || roundTypes[1] > 10) {
                        roundTypes[1] = 1;
                    }
                }
            }
        }
        if (roundTypes == null) {
            roundTypes = new int[] {1, 1};
        }
        int roundTypeInWeight = CalculateSocialUtils.getRoundTypeInWeight(roundTypes[0], roundTypes[1]);

        // 任务单费用段是否存在判断
        JSONArray operatorListData = params.getJSONArray("operatorListData");
        switch (inputHfEmpTask.getTaskCategory()) {
            case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
            case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
            case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
            case HfEmpTaskConstant.TASK_CATEGORY_ADJUST:
            case HfEmpTaskConstant.TASK_CATEGORY_REPAIR:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_ADD:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_IN:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_OPEN:
                if (CollectionUtils.isEmpty(operatorListData)) {
                    return JsonResultKit.ofError("当前任务单费用段信息为空");
                }
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_OUT:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_CLOSE:
                if (isHandle) {
                    YearMonth hfMonthDate = YearMonth.parse(params.getString("hfMonth"), formatter);
                    YearMonth endMonthDate = YearMonth.parse(params.getString("endMonth"), formatter);

                    if (hfMonthDate.isBefore(endMonthDate) || hfMonthDate.equals(endMonthDate)) {
                        return JsonResultKit.ofError("请于客户汇缴月到达截止缴费月的次月来办理当前任务单");
                    } else if (hfMonthDate.isAfter(endMonthDate.plusMonths(1))) {
                        return JsonResultKit.ofError("当前任务单已逾期，不能办理");
                    }
                }
                break;
            default:
                break;
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
        inputHfEmpTask.setModifiedBy(UserContext.getUserId());
        inputHfEmpTask.setModifiedDisplayName(UserContext.getUser().getDisplayName());
        inputHfEmpTask.setModifiedTime(LocalDateTime.now());

        if (isHandle) {
            ComAccountParamExtBo comAccountParamExtBo = new ComAccountParamExtBo();
            comAccountParamExtBo.setCompanyId(hfEmpTask.getCompanyId());
            List<ComAccountExtBo> hfComAccountList = hfComAccountService.queryHfComAccountList(comAccountParamExtBo);
            if (CollectionUtils.isNotEmpty(hfComAccountList)) {
                if (hfComAccountList.size() > 1) {
                    return JsonResultKit.ofError("当前雇员任务单所属的企业账户数据有误");
                }
            } else {
                return JsonResultKit.ofError("当前雇员任务单所属的企业账户不存在");
            }

            inputHfEmpTask.setHfType(hfEmpTask.getHfType());
            inputHfEmpTask.setIsChange(hfEmpTask.getIsChange());
            JsonResult rlt = setEmpTask(params, inputHfEmpTask);
            if (rlt.getCode() != 200) {
                return rlt;
            }

            // 雇员档案处理
            Long existEmpArchive = (Long) rlt.getData();
            Long newEmpArchive = handleEmpArchive(params, existEmpArchive, inputHfEmpTask);
            this.updateById(inputHfEmpTask);

            if (newEmpArchive == null) {
                inputHfEmpTask.setEmpArchiveId(existEmpArchive);
            }
            String startMonth = "999912";
            String endMonth = "190001";
            String dataStartMonth;
            String dataEndMonth;

            if (operatorListData != null) {
                for (int i = 0; i < operatorListData.size(); i++) {
                    JSONObject data = operatorListData.getJSONObject(i);
                    dataStartMonth = data.getString("startMonth");
                    dataEndMonth = data.getString("endMonth");

                    if (StringUtils.isNotEmpty(dataStartMonth)) {
                        if (Integer.valueOf(startMonth) > Integer.valueOf(dataStartMonth)) {
                            startMonth = dataStartMonth;
                        }
                    } else {
                        startMonth = "190001";
                    }

                    if (StringUtils.isNotEmpty(dataEndMonth)) {
                        if (Integer.valueOf(endMonth) < Integer.valueOf(dataEndMonth)) {
                            endMonth = dataEndMonth;
                        }
                    } else {
                        endMonth = "999912";
                    }
                }

                if (!"190001".equals(startMonth)) {
                    inputHfEmpTask.setStartMonth(startMonth);
                }
                if (!"999912".equals(endMonth)) {
                    inputHfEmpTask.setEndMonth(endMonth);
                }
            }
            inputHfEmpTask.setCompanyId(hfEmpTask.getCompanyId());
            inputHfEmpTask.setEmployeeId(hfEmpTask.getEmployeeId());
            inputHfEmpTask.setTaskId(hfEmpTask.getTaskId());
            inputHfEmpTask.setBusinessInterfaceId(hfEmpTask.getBusinessInterfaceId());
            inputHfEmpTask.setOldAgreementId(hfEmpTask.getOldAgreementId());
            inputHfEmpTask.setWelfareUnit(hfEmpTask.getWelfareUnit());
        } else {
            this.updateById(inputHfEmpTask);
        }

        List<HfArchiveBasePeriod> hfArchiveBasePeriodList = null;
        List<HfEmpTaskPeriod> hfEmpTaskPeriodList;
        // 任务单费用段操作处理
        if (operatorListData != null) {
            hfEmpTaskPeriodList = operatorListData.toJavaList(HfEmpTaskPeriod.class);
            List<Long> empTaskPeriodIdList = new ArrayList<>();
            hfEmpTaskPeriodList.stream().forEach(e -> {
                if (e.getEmpTaskPeriodId() != null) {
                    e.setModifiedBy(inputHfEmpTask.getModifiedBy());
                    e.setModifiedTime(LocalDateTime.now());
                    empTaskPeriodIdList.add(e.getEmpTaskPeriodId());
                } else {
                    e.setEmpTaskId(empTaskId);
                    e.setCreatedBy(inputHfEmpTask.getModifiedBy());
                    e.setModifiedBy(inputHfEmpTask.getModifiedBy());
                }
            });
            Wrapper<HfEmpTaskPeriod> wrapper = new EntityWrapper<>();
            wrapper.eq("emp_task_id", empTaskId);
            wrapper.eq("is_active", 1);
            wrapper.notIn("emp_task_period_id", empTaskPeriodIdList);
            hfEmpTaskPeriodService.delete(wrapper);
            hfEmpTaskPeriodService.insertOrUpdateBatch(hfEmpTaskPeriodList);

            if (isHandle) {
                switch (inputHfEmpTask.getTaskCategory()) {
                    case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
                    case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
                    case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
                    case HfEmpTaskConstant.TASK_CATEGORY_FLOP_ADD:
                    case HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_IN:
                    case HfEmpTaskConstant.TASK_CATEGORY_FLOP_OPEN:
                    case HfEmpTaskConstant.TASK_CATEGORY_ADJUST:
                        if (inputHfEmpTask.getTaskCategory() != HfEmpTaskConstant.TASK_CATEGORY_ADJUST) {
                            List<HfEmpTaskPeriod> createEmpBasePeriodList = hfEmpTaskPeriodList.stream().filter(e
                                    -> e.getRemitWay() == HfEmpTaskPeriodConstant.REMIT_WAY_NORMAL
                            ).collect(Collectors.toList());
                            if (CollectionUtils.isNotEmpty(createEmpBasePeriodList)) {
                                hfArchiveBasePeriodList = createEmpBasePeriod(inputHfEmpTask, createEmpBasePeriodList, roundTypes, roundTypeInWeight);
                            } else {
                                throw new BusinessException("正常汇缴的任务单费用段数据创建失败");
                            }

                            List<HfEmpTaskPeriod> repairEmpBasePeriodList =  hfEmpTaskPeriodList.stream().filter(e
                                -> e.getRemitWay() == HfEmpTaskPeriodConstant.REMIT_WAY_REPAIR).collect(Collectors.toList());
                            if (CollectionUtils.isNotEmpty(repairEmpBasePeriodList)) {
                                List<HfArchiveBasePeriod> repairArchiveBasePeriodList = repairEmpBasePeriod(inputHfEmpTask, repairEmpBasePeriodList, roundTypes, roundTypeInWeight);
                                if (CollectionUtils.isNotEmpty(repairArchiveBasePeriodList)) {
                                    hfArchiveBasePeriodList.addAll(repairArchiveBasePeriodList);
                                } else {
                                    LogMessage logMessage = LogMessage.create().setTitle("雇员调整任务单办理")
                                        .setContent("补缴费用段返回为空")
                                        .setTags(new HashMap<String, String>() { { put("empTaskId", String.valueOf(inputHfEmpTask.getEmpTaskId())); } });
                                    logApiUtil.error(logMessage);
                                    throw new BusinessException("补缴公积金费用段数据取得失败");
                                }
                            }
                            createHfMonthCharge(inputHfEmpTask, hfArchiveBasePeriodList, roundTypes, roundTypeInWeight);
                        } else {
//                            List<HfEmpTaskPeriod> adjustEmpBasePeriodList = hfEmpTaskPeriodList.stream().filter(e
//                                    -> e.getRemitWay() == HfEmpTaskPeriodConstant.REMIT_WAY_ADJUST
//                            ).collect(Collectors.toList());
                            if (CollectionUtils.isNotEmpty(hfEmpTaskPeriodList)) {
                                hfArchiveBasePeriodList = adjustEmpBasePeriod(inputHfEmpTask, hfEmpTaskPeriodList, roundTypes, roundTypeInWeight);
                            } else {
                                throw new BusinessException("调整的任务单费用段数据创建失败");
                            }
                            createHfMonthCharge(inputHfEmpTask, hfArchiveBasePeriodList, roundTypes, roundTypeInWeight);
                        }
                        break;
                    case HfEmpTaskConstant.TASK_CATEGORY_REPAIR:
                        hfArchiveBasePeriodList = repairEmpBasePeriod(inputHfEmpTask, hfEmpTaskPeriodList, roundTypes, roundTypeInWeight);
                        createHfMonthCharge(inputHfEmpTask, hfArchiveBasePeriodList, roundTypes, roundTypeInWeight);
                        break;

                    default:
                        break;
                }
            }
        } else {
            String endMonth = params.getString("endMonth");
            inputHfEmpTask.setEndMonth(endMonth);
            Map<String, Object> condition = new HashMap<>();
            condition.put("emp_task_id", empTaskId);
            condition.put("is_active", 1);

            HfEmpTaskPeriod hfEmpTaskPeriod = new HfEmpTaskPeriod();
            hfEmpTaskPeriod.setCreatedBy(inputHfEmpTask.getModifiedBy());
            hfEmpTaskPeriod.setEndMonth(endMonth);
            hfEmpTaskPeriod.setHfMonth(params.getString("hfMonth"));

            hfEmpTaskPeriodList = hfEmpTaskPeriodService.selectByMap(condition);
            if (CollectionUtils.isNotEmpty(hfEmpTaskPeriodList)) {
                if (hfEmpTaskPeriodList.size() > 1) {
                    return JsonResultKit.ofError("当前雇员任务单费用段数据不正确");
                }

                hfEmpTaskPeriod.setEmpTaskPeriodId(hfEmpTaskPeriodList.get(0).getEmpTaskPeriodId());
            } else {
                hfEmpTaskPeriod.setEmpTaskId(empTaskId);
                hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_NONE);
                hfEmpTaskPeriod.setModifiedTime(LocalDateTime.now());
                hfEmpTaskPeriod.setModifiedBy(inputHfEmpTask.getModifiedBy());
            }
            hfEmpTaskPeriodService.insertOrUpdate(hfEmpTaskPeriod);

            if (isHandle) {
                // 转出或封存任务单时，没有任务单费用段信息
                switch (inputHfEmpTask.getTaskCategory()) {
                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
                    case HfEmpTaskConstant.TASK_CATEGORY_FLOP_CLOSE:
                    case HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_OUT:
                        HfArchiveBasePeriod hfArchiveBasePeriod = stopEmpBasePeriod(inputHfEmpTask, hfEmpTaskPeriod);
                        hfArchiveBasePeriodList = new ArrayList<>();
                        hfArchiveBasePeriodList.add(hfArchiveBasePeriod);
                        createHfMonthCharge(inputHfEmpTask, hfArchiveBasePeriodList, roundTypes, roundTypeInWeight);
                        break;
                    default:
                        break;
                }
            }
        }
        if (isHandle) {
            try {
                int rtnCode = apiUpdateConfirmDate(inputHfEmpTask.getCompanyId(),
                    Long.valueOf(inputHfEmpTask.getBusinessInterfaceId()),
                    inputHfEmpTask,
                    hfArchiveBasePeriodList,
                    false);
            } catch (Exception e) {
                LogMessage logMessage = LogMessage.create().setTitle("访问接口").
                    setContent("访问客服中心的雇员任务单实缴金额回调接口失败,ExceptionMessage:" + e.getMessage());
                logApiUtil.error(logMessage);
                throw new BusinessException("访问客服中心的雇员任务单实缴金额回调接口失败");
            }
            try {
                Result result = apiCompleteTask(inputHfEmpTask.getTaskId(),
                    inputHfEmpTask.getModifiedBy());
            } catch (Exception e) {
                LogMessage logMessage = LogMessage.create().setTitle("访问接口").
                    setContent("访问客服中心的完成任务接口失败,ExceptionMessage:" + e.getMessage());
                logApiUtil.error(logMessage);
                throw new BusinessException("访问客服中心的完成任务接口失败");
            }
        }
        return JsonResultKit.of();
    }

    /**
     * 雇员任务单批退处理
     *
     * @param hfEmpTaskBatchRejectBo 页面提交参数对象
     * @return 处理结果
     */
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public JsonResult handleReject(HfEmpTaskBatchRejectBo hfEmpTaskBatchRejectBo) {
        Long empTaskId = hfEmpTaskBatchRejectBo.getSelectedData()[0];
        HfEmpTask hfEmpTask = this.selectById(empTaskId);
        if (hfEmpTask == null || !hfEmpTask.getActive()) {
            return JsonResultKit.ofError("当前任务单已不存在");
        }
        if (StringUtils.isEmpty(hfEmpTask.getTaskId())) {
            return JsonResultKit.ofError("当前任务单TaskId为空");
        }
        if (StringUtils.isEmpty(hfEmpTask.getBusinessInterfaceId())) {
            return JsonResultKit.ofError("当前任务单BusinessInterfaceId为空");
        }

        HfEmpTask inputHfEmpTask = new HfEmpTask();
        inputHfEmpTask.setEmpTaskId(empTaskId);
        inputHfEmpTask.setStartMonth(hfEmpTask.getStartMonth());
        inputHfEmpTask.setEndMonth(hfEmpTask.getEndMonth());
        inputHfEmpTask.setTaskStatus(HfEmpTaskConstant.TASK_STATUS_REJECTED);
        inputHfEmpTask.setRejectionRemark(hfEmpTaskBatchRejectBo.getRejectionRemark());
        inputHfEmpTask.setModifiedTime(LocalDateTime.now());
        inputHfEmpTask.setModifiedBy(UserContext.getUserId());
        inputHfEmpTask.setModifiedDisplayName(UserContext.getUser().getDisplayName());

        this.updateById(inputHfEmpTask);

        try {
            int rtnCode = apiUpdateConfirmDate(hfEmpTask.getCompanyId(),
                Long.valueOf(hfEmpTask.getBusinessInterfaceId()),
                inputHfEmpTask,
                new ArrayList<HfArchiveBasePeriod>(1) { { add(new HfArchiveBasePeriod()); } },
                true);
        } catch (Exception e) {
            LogMessage logMessage = LogMessage.create().setTitle("访问接口").
                setContent("访问客服中心的雇员任务单实缴金额回调接口失败,ExceptionMessage:" + e.getMessage());
            logApiUtil.error(logMessage);
            throw new BusinessException("访问客服中心的雇员任务单实缴金额回调接口失败");
        }
        try {
            Result result = apiCompleteTask(hfEmpTask.getTaskId(),
                UserContext.getUserId());
        } catch (Exception e) {
            LogMessage logMessage = LogMessage.create().setTitle("访问接口").
                setContent("访问客服中心的完成任务接口失败,ExceptionMessage:" + e.getMessage());
            logApiUtil.error(logMessage);
            throw new BusinessException("访问客服中心的完成任务接口失败");
        }

        return JsonResultKit.of();
    }

    /**
     * 雇员任务单撤销处理
     * @param empTaskIdList
     * @return
     */
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public JsonResult handleCancel(List<Long> empTaskIdList, String currentUser) {
        List<HfEmpTask> hfEmpTaskList = this.selectBatchIds(empTaskIdList);
        if (CollectionUtils.isNotEmpty(hfEmpTaskList)) {
            List<Long> outEmpTaskIdList = new ArrayList<>();
            List<HfEmpArchive> adjustEmpArchiveIdList = new ArrayList<>();
            List<Long> repairEmpTaskIdList = new ArrayList<>();
            List<Long> inEmpTaskIdList = new ArrayList<>();
            List<HfEmpTask> updateHfEmpTaskList = new ArrayList<>(hfEmpTaskList.size());

            Map<Long, Long> empTaskIdEmpArchiveIdMap = hfEmpArchiveService.queryHfEmpArchiveByEmpTaskIds(empTaskIdList);

            for (HfEmpTask hfEmpTask : hfEmpTaskList) {
                if (!hfEmpTask.getActive()) {
                    String taskCategoryName = DictUtil.getInstance().getTextByItemValueAndTypeValue(
                        String.valueOf(hfEmpTask.getTaskCategory()),
                        DictUtil.TYPE_VALUE_HF_LOCAL_TASK_CATEGORY,
                        false);
                    return JsonResultKit.ofError(
                         String.format("客户编号：%1$s雇员编号：%2$s的雇员%3$s任务单（ID：%4$d）已经被删除",
                             hfEmpTask.getCompanyId(),
                             hfEmpTask.getEmployeeId(),
                             taskCategoryName,
                             hfEmpTask.getEmpTaskId()
                         )
                    );
                } else if (hfEmpTask.getTaskStatus() != HfEmpTaskConstant.TASK_STATUS_HANDLED) {
                    String taskCategoryName = DictUtil.getInstance().getTextByItemValueAndTypeValue(
                        String.valueOf(hfEmpTask.getTaskCategory()),
                        DictUtil.TYPE_VALUE_HF_LOCAL_TASK_CATEGORY,
                        false);
                    return JsonResultKit.ofError(
                        String.format("客户编号：%1$s雇员编号：%2$s的雇员%3$s任务单（ID：%4$d）不是已办状态",
                            hfEmpTask.getCompanyId(),
                            hfEmpTask.getEmployeeId(),
                            taskCategoryName,
                            hfEmpTask.getEmpTaskId()
                        )
                    );
                }
                HfEmpArchive hfEmpArchive;
                switch (hfEmpTask.getTaskCategory()) {
                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
                    case HfEmpTaskConstant.TASK_CATEGORY_FLOP_CLOSE:
                    case HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_OUT:
                        hfEmpArchive = new HfEmpArchive();
                        if (empTaskIdEmpArchiveIdMap != null && hfEmpTask.getEmpArchiveId() == null) {
                            hfEmpArchive.setEmpArchiveId(empTaskIdEmpArchiveIdMap.get(hfEmpTask.getEmpArchiveId()));
                        } else {
                            hfEmpArchive.setEmpArchiveId(hfEmpTask.getEmpArchiveId());
                        }

                        hfEmpArchive.setArchiveStatus(HfEmpArchiveConstant.ARCHIVE_STATUS_COMPLETED);
                        adjustEmpArchiveIdList.add(hfEmpArchive);
                        outEmpTaskIdList.add(hfEmpTask.getEmpTaskId());
                        break;
                    case HfEmpTaskConstant.TASK_CATEGORY_REPAIR:
                        repairEmpTaskIdList.add(hfEmpTask.getEmpTaskId());
                        break;
                    case HfEmpTaskConstant.TASK_CATEGORY_ADJUST:
//                        hfEmpArchive = new HfEmpArchive();
//                        if (empTaskIdEmpArchiveIdMap != null && hfEmpTask.getEmpArchiveId() == null) {
//                            hfEmpArchive.setEmpArchiveId(empTaskIdEmpArchiveIdMap.get(hfEmpTask.getEmpArchiveId()));
//                        } else {
//                            hfEmpArchive.setEmpArchiveId(hfEmpTask.getEmpArchiveId());
//                        }
//                        hfEmpArchive.setArchiveStatus(HfEmpArchiveConstant.ARCHIVE_STATUS_CLOSED);
//                        adjustEmpArchiveIdList.add(hfEmpArchive);
//                        inEmpTaskIdList.add(hfEmpTask.getEmpTaskId());
                        break;
                    default:
                        inEmpTaskIdList.add(hfEmpTask.getEmpTaskId());
                        break;
                }

                HfEmpTask updateHfEmpTask = new HfEmpTask();
                updateHfEmpTask.setEmpTaskId(hfEmpTask.getEmpTaskId());
                updateHfEmpTask.setTaskStatus(HfEmpTaskConstant.TASK_STATUS_UNHANDLED);
                updateHfEmpTask.setModifiedBy(UserContext.getUserId());
                updateHfEmpTask.setModifiedDisplayName(UserContext.getUser().getDisplayName());
                updateHfEmpTask.setModifiedTime(LocalDateTime.now());
                updateHfEmpTaskList.add(updateHfEmpTask);
            }

            if (CollectionUtils.isNotEmpty(outEmpTaskIdList)) {
                HfMonthChargeBo hfMonthChargeBo = new HfMonthChargeBo();
                hfMonthChargeBo.setEmpTaskIdList(outEmpTaskIdList);
                hfMonthChargeBo.setChgPaymentType(HfMonthChargeConstant.PAYMENT_TYPE_NORMAL);
                hfMonthChargeBo.setModifiedBy(UserContext.getUserId());
                hfMonthChargeService.updateHfMonthCharge(hfMonthChargeBo);
                HfArchiveBasePeriodUpdateBo hfArchiveBasePeriodUpdateBo = new HfArchiveBasePeriodUpdateBo();
                hfArchiveBasePeriodUpdateBo.setEmpTaskIdList(outEmpTaskIdList);
                hfArchiveBasePeriodUpdateBo.setModifiedBy(UserContext.getUserId());
                hfArchiveBasePeriodService.updateHfArchiveBasePeriods(hfArchiveBasePeriodUpdateBo);

                if (CollectionUtils.isNotEmpty(adjustEmpArchiveIdList)) {
                    hfEmpArchiveService.updateBatchById(adjustEmpArchiveIdList);
                }
            }
            if (CollectionUtils.isNotEmpty(inEmpTaskIdList)) {
                hfMonthChargeService.deleteHfMonthCharges(inEmpTaskIdList);
                hfArchiveBasePeriodService.deleteHfArchiveBasePeriods(inEmpTaskIdList);

                if (CollectionUtils.isNotEmpty(adjustEmpArchiveIdList)) {
                    hfEmpArchiveService.updateBatchById(adjustEmpArchiveIdList);
                } else {
                    hfEmpArchiveService.deleteHfEmpArchiveByEmpTaskIds(inEmpTaskIdList);
                }
            }
            if (CollectionUtils.isNotEmpty(repairEmpTaskIdList)) {
                hfMonthChargeService.deleteHfMonthCharges(repairEmpTaskIdList);
                hfArchiveBaseAdjustService.deleteHfArchiveBaseAdjusts(repairEmpTaskIdList);
                hfArchiveBasePeriodService.deleteHfArchiveBasePeriods(repairEmpTaskIdList);
            }

            HfEmpTaskPeriodInactiveBo hfEmpTaskPeriodInactiveBo = new HfEmpTaskPeriodInactiveBo();
            hfEmpTaskPeriodInactiveBo.setEmpTaskIdList(empTaskIdList);
            hfEmpTaskPeriodInactiveBo.setModifiedBy(UserContext.getUserId());
            hfEmpTaskPeriodService.inactiveHfEmpTaskPeriods(hfEmpTaskPeriodInactiveBo);
            this.updateBatchById(updateHfEmpTaskList);
        } else {
            return JsonResultKit.ofError("雇员任务单数据不存在");
        }
        return JsonResultKit.of();
    }

    /**
     * 根据任务单费用分段表数据实装雇员档案费用分段表数据
     *
     * @param hfArchiveBasePeriodList 雇员档案费用分段表数据列表
     * @param hfEmpTask 任务单表数据
     * @param hfEmpTaskPeriod 任务费用分段表数据
     * @param repairHfArchiveBasePeriod 差额补缴的雇员档案费用分段表数据
     * @param roundTypes 进位方式
     * @param roundTypeInWeight 权重进位方式
     */
    private void setHfArchiveBasePeriodList(List<HfArchiveBasePeriod> hfArchiveBasePeriodList,
                                            HfEmpTask hfEmpTask,
                                            HfEmpTaskPeriod hfEmpTaskPeriod,
                                            HfArchiveBasePeriod repairHfArchiveBasePeriod,
                                            int[] roundTypes,
                                            int roundTypeInWeight) {
        HfArchiveBasePeriod hfArchiveBasePeriod = new HfArchiveBasePeriod();
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
        hfArchiveBasePeriod.setRatio(ratioCom.add(ratioEmp).setScale(3, BigDecimal.ROUND_HALF_UP));
//        if (hfArchiveBasePeriod.getRatio().compareTo(BigDecimal.ZERO) > 0) {
        if (repairHfArchiveBasePeriod == null) {
            hfArchiveBasePeriod.setAmount(CalculateSocialUtils.calculateByRoundType(hfEmpTaskPeriod.getAmount(), roundTypeInWeight));
            hfArchiveBasePeriod.setAmountEmp(
                CalculateSocialUtils.calculateByRoundType(
                    amount.multiply(ratioEmp.divide(hfArchiveBasePeriod.getRatio(), 3, BigDecimal.ROUND_HALF_UP)),
                    roundTypes[1]));
            hfArchiveBasePeriod.setComAmount(
                CalculateSocialUtils.calculateByRoundType(
                    amount.multiply(ratioCom.divide(hfArchiveBasePeriod.getRatio(), 3, BigDecimal.ROUND_HALF_UP)),
                    roundTypes[0]));
        } else {
            // 代码特殊处理，仅为了差额补缴时，不重新计算单月差额
            hfArchiveBasePeriod.setAmountEmp(repairHfArchiveBasePeriod.getAmountEmp());
            hfArchiveBasePeriod.setComAmount(repairHfArchiveBasePeriod.getComAmount());
            hfArchiveBasePeriod.setAmount(
                CalculateSocialUtils.calculateByRoundType(
                    repairHfArchiveBasePeriod.getAmountEmp()
                        .add(repairHfArchiveBasePeriod.getComAmount()).setScale(3, BigDecimal.ROUND_HALF_UP)
                    , roundTypeInWeight));
            hfArchiveBasePeriod.setDiffRepair(true);
        }

        hfArchiveBasePeriod.setStartMonth(hfEmpTaskPeriod.getStartMonth());
        hfArchiveBasePeriod.setEndMonth(hfEmpTaskPeriod.getEndMonth());
        hfArchiveBasePeriod.setHfMonth(hfEmpTaskPeriod.getHfMonth());
        hfArchiveBasePeriod.setRepairReason(hfEmpTaskPeriod.getRepairReason());
        hfArchiveBasePeriod.setModifiedBy(hfEmpTask.getModifiedBy());

        if (hfArchiveBasePeriod.getArchiveBasePeriodId() == null) {
            hfArchiveBasePeriod.setCreatedBy(hfEmpTask.getModifiedBy());
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
     * @param roundTypes 进位方式
     * @param roundTypeInWeight 权重进位方式
     * @return 雇员档案费用分段表数据
     */
    private List<HfArchiveBasePeriod> createEmpBasePeriod(HfEmpTask hfEmpTask, List<HfEmpTaskPeriod> hfEmpTaskPeriodList, int[] roundTypes, int roundTypeInWeight) {
        List<HfArchiveBasePeriod> hfArchiveBasePeriodList = new ArrayList<>(hfEmpTaskPeriodList.size());
        hfEmpTaskPeriodList.stream().forEach(e -> setHfArchiveBasePeriodList(hfArchiveBasePeriodList, hfEmpTask, e, null, roundTypes, roundTypeInWeight));
        hfArchiveBasePeriodService.insertOrUpdateBatch(hfArchiveBasePeriodList);
        return hfArchiveBasePeriodList;
    }

    /**
     * 将雇员费用段时间段连续的组合到一个对象中
     *
     * @param existsHfArchiveBasePeriodList 雇员费用段列表
     * @return 雇员费用段组合对象列表
     */
    public List<ComposedEmpBasePeriodBO> composeEmpBasePeriod(List<HfArchiveBasePeriod> existsHfArchiveBasePeriodList) {
        String startMonth;
        String endMonth = null;
        YearMonth startMonthDate = null;
        YearMonth endMonthDate;
        List<ComposedEmpBasePeriodBO> composedEmpBasePeriodBOList = new ArrayList<>();

        for(HfArchiveBasePeriod hfArchiveBasePeriod : existsHfArchiveBasePeriodList) {
            startMonth = hfArchiveBasePeriod.getStartMonth();
            if (StringUtils.isNotEmpty(startMonth)) {
                startMonthDate = YearMonth.parse(startMonth, formatter);
            }

            if (StringUtils.isNotEmpty(endMonth)) {
                endMonthDate = YearMonth.parse(endMonth, formatter);
                ComposedEmpBasePeriodBO composedEmpBasePeriodBO = composedEmpBasePeriodBOList.get(composedEmpBasePeriodBOList.size() - 1);

                if (endMonthDate.plusMonths(1).equals(startMonthDate)) {
                    endMonth = hfArchiveBasePeriod.getEndMonth();
                } else {
                    composedEmpBasePeriodBO.setEndMonth(endMonthDate);
                    endMonth = null;
                }
                composedEmpBasePeriodBO.getContainsHfArchiveBasePeriods().add(hfArchiveBasePeriod);
            } else {
                ComposedEmpBasePeriodBO composedEmpBasePeriodBO = new ComposedEmpBasePeriodBO();
                composedEmpBasePeriodBO.setStartMonth(startMonthDate);
                composedEmpBasePeriodBOList.add(composedEmpBasePeriodBO);
                endMonth = hfArchiveBasePeriod.getEndMonth();

                if (StringUtils.isEmpty(endMonth)) {
                    composedEmpBasePeriodBO.getContainsHfArchiveBasePeriods().add(hfArchiveBasePeriod);
                }
            }
        }

        return composedEmpBasePeriodBOList;
    }

    /**
     * 补缴任务时雇员档案费用分段表数据处理
     * 分全额补缴及差额补缴
     *
     * @param hfEmpTask 任务单表数据
     * @param hfEmpTaskPeriodList 任务费用分段表数据列表
     * @param roundTypes 进位方式
     * @param roundTypeInWeight 权重进位方式
     * @return 雇员档案费用分段表数据
     */
    private List<HfArchiveBasePeriod> repairEmpBasePeriod(HfEmpTask hfEmpTask, List<HfEmpTaskPeriod> hfEmpTaskPeriodList, int[] roundTypes, int roundTypeInWeight) {
        List<HfArchiveBasePeriod> hfArchiveBasePeriodList;
        List<HfArchiveBasePeriod> diffHfArchiveBasePeriodList;
        EntityWrapper<HfArchiveBasePeriod> wrapper = new EntityWrapper<>();
        wrapper.where("employee_id={0} AND company_id={1} AND hf_type={2} AND is_active = 1", hfEmpTask.getEmployeeId(), hfEmpTask.getCompanyId(), hfEmpTask.getHfType());
        wrapper.orderBy("start_month", true);
        List<HfArchiveBasePeriod> existHfArchiveBasePeriodList = hfArchiveBasePeriodService.selectList(wrapper);

        if (CollectionUtils.isNotEmpty(existHfArchiveBasePeriodList)) {
            hfArchiveBasePeriodList = new ArrayList<>();
            diffHfArchiveBasePeriodList = new ArrayList<>();
            List<HfArchiveBaseAdjust> hfArchiveBaseAdjustList = new ArrayList<>();

            for (HfEmpTaskPeriod e : hfEmpTaskPeriodList) {
                if (StringUtils.isEmpty(e.getStartMonth()) || StringUtils.isEmpty(e.getEndMonth()) || StringUtils.isEmpty(e.getHfMonth())) {
                    throw new BusinessException("补缴任务费用分段中缴费起始年月或缴费截止年月或汇缴年月为空");
                }
                final YearMonth hfMonth = YearMonth.parse(e.getHfMonth(), formatter);
                YearMonth repairStartMonth = YearMonth.parse(e.getStartMonth(), formatter);
                YearMonth repairEndMonth = YearMonth.parse(e.getEndMonth(), formatter);

                if (HfEmpTaskConstant.WELFARE_UNIT_INDEPENDENT == hfEmpTask.getWelfareUnit()) {
                    if ((hfEmpTask.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_IN_ADD
                         || hfEmpTask.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_FLOP_ADD)
                        && repairEndMonth.isAfter(hfMonth.minusMonths(2))) {
                        throw new BusinessException("新开任务单，补缴任务费用分段中的缴费截止年月必须小于等于汇缴年月的前两月（独立户）");
                    }
                } else {
                    if (repairEndMonth.isAfter(hfMonth.minusMonths(1))) {
                        throw new BusinessException("补缴任务费用分段中的缴费截止年月必须小于等于汇缴年月的前月（大库）");
                    }
                }
//                if (repairEndMonth.equals(hfMonth) || repairEndMonth.isAfter(hfMonth)) {
//                    throw new BusinessException("补缴任务费用分段中缴费截止年月不能在汇缴年月以后");
//                }

//                YearMonth permitYearMonth = null;
//                boolean isMatch = false;

                List<ComposedEmpBasePeriodBO> composedEmpBasePeriodBOList = composeEmpBasePeriod(existHfArchiveBasePeriodList);
                for (ComposedEmpBasePeriodBO composedEmpBasePeriodBO : composedEmpBasePeriodBOList) {
                    YearMonth startMonth = composedEmpBasePeriodBO.getStartMonth();
                    YearMonth endMonth = composedEmpBasePeriodBO.getEndMonth();
                    if (endMonth == null) {
                        endMonth = hfMonth;
                    }
                    List<HfArchiveBasePeriod> containsHfArchiveBasePeriodList = composedEmpBasePeriodBO.getContainsHfArchiveBasePeriods();

                    // 如果补缴起始年月小于费用段起始月
                    if (repairStartMonth.isBefore(startMonth)) {
                        // 补缴截止年月小于费用段起始月，说明整个补缴段都不存在，则全额补缴
                        if (repairEndMonth.isBefore(startMonth)) {
                            setHfArchiveBasePeriodList(hfArchiveBasePeriodList, hfEmpTask, e, null, roundTypes, roundTypeInWeight);
                            break;
                        } else { // 补缴截止年月大于等于费用段起始月，说明补缴段部分在费用段中，部分全额补缴，部分差额补缴
                            // 此时肯定有一段全额补缴，一段差额补缴
                            e.setEndMonth(startMonth.format(formatter));   // 全额补缴段：从补缴起始年月到费用段起始年月
                            setHfArchiveBasePeriodList(hfArchiveBasePeriodList, hfEmpTask, e, null, roundTypes, roundTypeInWeight);

                            // 差额补缴段：从费用段起始年月到补缴截止年月
                            e.setStartMonth(startMonth.format(formatter));
                            e.setEndMonth(repairEndMonth.format(formatter));
                            for (HfArchiveBasePeriod hfArchiveBasePeriod : containsHfArchiveBasePeriodList) {
                                setHfArchiveBaseAdjust(hfArchiveBaseAdjustList, hfEmpTask, e, hfArchiveBasePeriod, roundTypes, roundTypeInWeight);
                                setHfArchiveBasePeriodList(diffHfArchiveBasePeriodList, hfEmpTask, e, hfArchiveBasePeriod, roundTypes, roundTypeInWeight);
                            }

                            // 补缴截止年月小于等于费用段截止月时，说明只有一段全额补缴，一段差额补缴
                            if (repairEndMonth.isBefore(endMonth) || repairEndMonth.equals(endMonth)) {
                                break;
                            } else { // 补缴截止年月大于费用段截止年月时，说明需判断下一个连续费用段
                                // 补缴截止年月大于费用段截止年月时，后面从当前费用段截止年月开始判断
                                repairStartMonth = endMonth;
                            }
                        }
                    } else { // 如果补缴起始年月大于等于费用段起始年月
                        // 补缴起始年月小于等于费用段截止年月时
                        if (repairStartMonth.isBefore(endMonth) || repairStartMonth.equals(endMonth) ) {
                            // 此时肯定有一段差额补缴
                            e.setStartMonth(startMonth.format(formatter));
                            e.setEndMonth(repairEndMonth.format(formatter));
                            for (HfArchiveBasePeriod hfArchiveBasePeriod : containsHfArchiveBasePeriodList) {
                                setHfArchiveBaseAdjust(hfArchiveBaseAdjustList, hfEmpTask, e, hfArchiveBasePeriod, roundTypes, roundTypeInWeight);
                                setHfArchiveBasePeriodList(diffHfArchiveBasePeriodList, hfEmpTask, e, hfArchiveBasePeriod, roundTypes, roundTypeInWeight);
                            }

                            // 补缴截止年月小于等于费用段截止年月时，说明整段段差额补缴
                            if (repairEndMonth.isBefore(endMonth) || repairEndMonth.equals(endMonth)) {
                                break;
                            } else { // 补缴截止年月大于费用段截止年月时，说明需判断下一个连续费用段
                                // 补缴截止年月大于费用段截止年月时，后面从当前费用段截止年月开始判断
                                repairStartMonth = endMonth;
                            }
                        }

                        // 补缴起始年月大于费用段截止年月时，直接判断下一个连续费用段
                    }

//                    // 匹配费用段，如果任务单费用段包含在雇员档案费用段中，那么支持差额补缴；
//                    // 差额补缴时，雇员档案费用段不变，增加雇员调整差异数据
//                    if ((repairStartMonth.isAfter(startMonth) || repairStartMonth.equals(startMonth))
//                        && (repairEndMonth.isBefore(endMonth) || repairEndMonth.equals(endMonth))) {
//                        YearMonth subStartMonth;
//                        for (HfArchiveBasePeriod hfArchiveBasePeriod : containsHfArchiveBasePeriodList) {
//                            subStartMonth = YearMonth.parse(hfArchiveBasePeriod.getStartMonth(), formatter);
//
//                            if (subStartMonth.isBefore(repairEndMonth) || subStartMonth.equals(repairEndMonth)) {
//                                if (!hfEmpTask.getCompanyId().equals(hfArchiveBasePeriod.getCompanyId())) {
//                                    throw new BusinessException("补缴任务费用分段中缴费期间与所匹配的费用段不属于同一个客户");
//                                }
//                                setHfArchiveBaseAdjust(hfArchiveBaseAdjustList, hfEmpTask, e, hfArchiveBasePeriod, roundTypes, roundTypeInWeight);
//                                setHfArchiveBasePeriodList(diffHfArchiveBasePeriodList, hfEmpTask, e, hfArchiveBasePeriod, roundTypes, roundTypeInWeight);
//                            }
//                        }
//                        isMatch = true;
//                        break;
//                    } else {
//                        // 匹配费用段空档区间，如果任务单费用段包含在雇员档案费用段空档区间中，那么支持全额补缴；
//                        // 全额补缴时，更新雇员档案费用段，不需要增加雇员调整差异数据
//                        if (permitYearMonth == null) {
//                            // 匹配最早的费用段，判断补缴段的截止年月是否早于雇员档案费用段的起始年月
//                            if (repairEndMonth.isBefore(startMonth)) {
//                                if (!hfEmpTask.getCompanyId().equals(containsHfArchiveBasePeriodList.get(0).getCompanyId())) {
//                                    throw new BusinessException("补缴任务费用分段中缴费期间与所匹配的费用段不属于同一个客户");
//                                }
//                                permitYearMonth = endMonth;
//                                setHfArchiveBasePeriodList(hfArchiveBasePeriodList, hfEmpTask, e, null, roundTypes, roundTypeInWeight);
//                                isMatch = true;
//                                break;
//                            }
//                        } else if (endMonth.isBefore(hfMonth)) {
//                            // 如果雇员档案费用段的截止年月早于当前汇缴年月
//                            // 那么判断补缴段是否属于两个雇员档案费用段之间的空档期间
//                            if (repairStartMonth.isAfter(permitYearMonth) && repairEndMonth.isBefore(startMonth)) {
//                                if (!hfEmpTask.getCompanyId().equals(containsHfArchiveBasePeriodList.get(0).getCompanyId())) {
//                                    throw new BusinessException("补缴任务费用分段中缴费期间与所匹配的费用段不属于同一个客户");
//                                }
//                                permitYearMonth = endMonth;
//                                setHfArchiveBasePeriodList(hfArchiveBasePeriodList, hfEmpTask, e, null, roundTypes, roundTypeInWeight);
//                                isMatch = true;
//                                break;
//                            }
//                        }
//                    }
                }

//                if (!isMatch) {
//                    throw new BusinessException("补缴任务费用分段期间不正确");
//                }
            }

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
     * 调整任务时雇员档案费用分段表数据处理（类似于差额补缴）
     *
     * @param hfEmpTask 任务单数据
     * @param hfEmpTaskPeriodList 任务单费用段数据
     * @param roundTypes 进位方式
     * @param roundTypeInWeight 权重进位方式
     * @return 雇员档案费用分段数据
     */
    private List<HfArchiveBasePeriod> adjustEmpBasePeriod(HfEmpTask hfEmpTask, List<HfEmpTaskPeriod> hfEmpTaskPeriodList, int[] roundTypes, int roundTypeInWeight) {
        List<HfArchiveBasePeriod> hfArchiveBasePeriodList;
        List<HfArchiveBasePeriod> diffHfArchiveBasePeriodList;
        EntityWrapper<HfArchiveBasePeriod> wrapper = new EntityWrapper<>();
        wrapper.where("employee_id={0} AND company_id={1} AND hf_type={2} AND is_active = 1", hfEmpTask.getEmployeeId(), hfEmpTask.getCompanyId(), hfEmpTask.getHfType());
        wrapper.orderBy("start_month", true);
        List<HfArchiveBasePeriod> existHfArchiveBasePeriodList = hfArchiveBasePeriodService.selectList(wrapper);

        if (CollectionUtils.isNotEmpty(existHfArchiveBasePeriodList)) {
            hfArchiveBasePeriodList = new ArrayList<>();
            diffHfArchiveBasePeriodList = new ArrayList<>();

            for (HfEmpTaskPeriod e : hfEmpTaskPeriodList) {
                if (StringUtils.isEmpty(e.getStartMonth()) || StringUtils.isEmpty(e.getHfMonth())) {
                    throw new BusinessException("调整任务费用分段中缴费起始年月或汇缴年月为空");
                }
                YearMonth hfMonth = YearMonth.parse(e.getHfMonth(), formatter);
                YearMonth adjustStartMonth = YearMonth.parse(e.getStartMonth(), formatter);

                // 调整任务费用分段中调整段处理
                if (e.getRemitWay() == HfEmpTaskPeriodConstant.REMIT_WAY_ADJUST) {
                    if (!adjustStartMonth.equals(hfMonth)) {
                        throw new BusinessException("调整任务费用分段中调整段的缴费起始年月必须等于汇缴年月");
                    }

                    for (HfArchiveBasePeriod existHfArchiveBasePeriod : existHfArchiveBasePeriodList) {
                        if (StringUtils.isEmpty(existHfArchiveBasePeriod.getEndMonth())) {
                            YearMonth startMonth = YearMonth.parse(existHfArchiveBasePeriod.getStartMonth(), formatter);
                            if (startMonth.isAfter(hfMonth)) {
                                throw new BusinessException("雇员档案费用段中缴费起始年月不能大于汇缴年月");
                            }

                            HfArchiveBasePeriod hfArchiveBasePeriod = new HfArchiveBasePeriod();
                            hfArchiveBasePeriod.setEmpTaskId(hfEmpTask.getEmpTaskId());
                            hfArchiveBasePeriod.setRemitWay(e.getRemitWay());
                            hfArchiveBasePeriod.setArchiveBasePeriodId(existHfArchiveBasePeriod.getArchiveBasePeriodId());
                            hfArchiveBasePeriod.setHfMonth(e.getHfMonth());
                            hfArchiveBasePeriod.setEndMonth(hfMonth.minusMonths(1).format(formatter));
                            hfArchiveBasePeriod.setModifiedBy(hfEmpTask.getModifiedBy());
                            hfArchiveBasePeriod.setModifiedTime(LocalDateTime.now());
                            hfArchiveBasePeriod.setBaseAmount(existHfArchiveBasePeriod.getBaseAmount());
                            hfArchiveBasePeriod.setRatio(existHfArchiveBasePeriod.getRatio());
                            hfArchiveBasePeriod.setRatioCom(existHfArchiveBasePeriod.getRatioCom());
                            hfArchiveBasePeriod.setRatioEmp(existHfArchiveBasePeriod.getRatioEmp());
                            hfArchiveBasePeriod.setAmount(existHfArchiveBasePeriod.getAmount());
                            hfArchiveBasePeriod.setAmountEmp(existHfArchiveBasePeriod.getAmountEmp());
                            hfArchiveBasePeriod.setComAmount(existHfArchiveBasePeriod.getComAmount());
                            hfArchiveBasePeriod.setCompanyId(existHfArchiveBasePeriod.getCompanyId());
                            hfArchiveBasePeriod.setEmployeeId(existHfArchiveBasePeriod.getEmployeeId());
                            hfArchiveBasePeriod.setEmpArchiveId(existHfArchiveBasePeriod.getEmpArchiveId());
                            hfArchiveBasePeriod.setHfType(existHfArchiveBasePeriod.getHfType());
                            hfArchiveBasePeriodList.add(hfArchiveBasePeriod);

                            setHfArchiveBasePeriodList(hfArchiveBasePeriodList, hfEmpTask, e, null, roundTypes, roundTypeInWeight);
                            break;
                        }
                    }

                    if (CollectionUtils.isEmpty(hfArchiveBasePeriodList)) {
                        throw new BusinessException("当前雇员的雇员汇缴月份段数据不正确，已封存或转出的雇员不能进行调整");
                    }
                } else { // 调整任务费用分段中补缴段处理（差额补缴）
                    if (StringUtils.isEmpty(e.getEndMonth())) {
                        throw new BusinessException("调整任务费用分段中补缴段的缴费截止年月为空");
                    }
                    YearMonth adjustEndMonth = YearMonth.parse(e.getEndMonth(), formatter);

                    if (HfEmpTaskConstant.WELFARE_UNIT_INDEPENDENT == hfEmpTask.getWelfareUnit()) {
//                        if (hfEmpTask.getTaskCategory() == HfEmpTaskConstant.PROCESS_CATEGORY_ADD && adjustEndMonth.isAfter(hfMonth.minusMonths(2))) {
//                            throw new BusinessException("调整任务费用分段中补缴段的缴费截止年月必须小于等于汇缴年月的前两月（独立户）");
//                        }
//                    } else {
                        if (adjustEndMonth.isAfter(hfMonth.minusMonths(1))) {
                            throw new BusinessException("调整任务费用分段中补缴段的缴费截止年月必须小于等于汇缴年月的前月（大库）");
                        }
                    }
                    boolean isStart = false;
                    List<ComposedEmpBasePeriodBO> composedEmpBasePeriodBOList = this.composeEmpBasePeriod(existHfArchiveBasePeriodList);
                    for (ComposedEmpBasePeriodBO composedEmpBasePeriodBO : composedEmpBasePeriodBOList) {
                        if (!isStart && adjustStartMonth.isBefore(composedEmpBasePeriodBO.getStartMonth())) {
                            adjustStartMonth = composedEmpBasePeriodBO.getStartMonth();
                            isStart = true;
                        }
                        if (composedEmpBasePeriodBO.getEndMonth() != null && adjustEndMonth.isAfter(composedEmpBasePeriodBO.getEndMonth())) {
                            throw new BusinessException("调整任务费用分段中补缴段的缴费截止年月必须不大于当前连续费用段的截止年月");
                        }
                    }

                    YearMonth startMonth;
                    YearMonth endMonth = adjustEndMonth;
                    List<HfArchiveBaseAdjust> hfArchiveBaseAdjustList = new ArrayList<>();
                    boolean isEnd = false;

                    for (HfArchiveBasePeriod existHfArchiveBasePeriod : existHfArchiveBasePeriodList) {
                        startMonth = YearMonth.parse(existHfArchiveBasePeriod.getStartMonth(), formatter);

                        if (StringUtils.isNotEmpty(existHfArchiveBasePeriod.getEndMonth())) {
                            endMonth = YearMonth.parse(existHfArchiveBasePeriod.getEndMonth(), formatter);
                        }
                        HfEmpTaskPeriod hfEmpTaskPeriod = new HfEmpTaskPeriod();
                        hfEmpTaskPeriod.setHfMonth(e.getHfMonth());
                        hfEmpTaskPeriod.setBaseAmount(e.getBaseAmount());
                        hfEmpTaskPeriod.setRatio(e.getRatio());
                        hfEmpTaskPeriod.setRatioCom(e.getRatioCom());
                        hfEmpTaskPeriod.setRatioEmp(e.getRatioEmp());
                        hfEmpTaskPeriod.setAmount(e.getAmount());
                        hfEmpTaskPeriod.setRemitWay(e.getRemitWay());
                        hfEmpTaskPeriod.setRepairReason(e.getRepairReason());

                        if (adjustStartMonth.isBefore(startMonth)) {
                            hfEmpTaskPeriod.setStartMonth(existHfArchiveBasePeriod.getStartMonth());
                        } else {
                            hfEmpTaskPeriod.setStartMonth(e.getStartMonth());
                        }

                        if (adjustEndMonth.isAfter(endMonth) && StringUtils.isNotEmpty(existHfArchiveBasePeriod.getEndMonth())) {
                            hfEmpTaskPeriod.setEndMonth(existHfArchiveBasePeriod.getEndMonth());
                            adjustStartMonth = endMonth;
                        } else {
                            hfEmpTaskPeriod.setEndMonth(e.getEndMonth());
                            isEnd = true;
                        }
                        setHfArchiveBaseAdjust(hfArchiveBaseAdjustList, hfEmpTask, hfEmpTaskPeriod, existHfArchiveBasePeriod, roundTypes, roundTypeInWeight);
                        setHfArchiveBasePeriodList(diffHfArchiveBasePeriodList, hfEmpTask, hfEmpTaskPeriod, existHfArchiveBasePeriod, roundTypes, roundTypeInWeight);

                        if (isEnd) {
                            break;
                        }
                    }

                    if (CollectionUtils.isNotEmpty(hfArchiveBaseAdjustList)) {
                        hfArchiveBaseAdjustService.insertBatch(hfArchiveBaseAdjustList);
                    }
                }
            }

            if (CollectionUtils.isNotEmpty(hfArchiveBasePeriodList)) {
                hfArchiveBasePeriodService.insertOrUpdateBatch(hfArchiveBasePeriodList);
                if (CollectionUtils.isNotEmpty(diffHfArchiveBasePeriodList)) {
                    hfArchiveBasePeriodList.addAll(diffHfArchiveBasePeriodList);
                }
            } else {
                hfArchiveBasePeriodList = diffHfArchiveBasePeriodList;
            }
        } else {
            throw new BusinessException("当前雇员的雇员汇缴月份段数据不存在，不能调整");
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
        wrapper.where("employee_id={0} AND company_id={1} AND hf_type={2} AND is_active = 1", hfEmpTask.getEmployeeId(), hfEmpTask.getCompanyId(), hfEmpTask.getHfType());
        wrapper.orderBy("start_month", false);
        List<HfArchiveBasePeriod> existHfArchiveBasePeriodList = hfArchiveBasePeriodService.selectList(wrapper);

        if (CollectionUtils.isNotEmpty(existHfArchiveBasePeriodList)) {
            HfArchiveBasePeriod hfArchiveBasePeriod = new HfArchiveBasePeriod();
            HfArchiveBasePeriod lastHfArchiveBasePeriod = existHfArchiveBasePeriodList.get(0);
            if (StringUtils.isNotEmpty(lastHfArchiveBasePeriod.getEndMonth())) {
                throw new BusinessException("当前雇员最后汇缴月份段的缴费截止月已经存在");
            } else {
                YearMonth startMonth = YearMonth.parse(lastHfArchiveBasePeriod.getStartMonth(), formatter);
                YearMonth endMonth = YearMonth.parse(hfEmpTaskPeriod.getEndMonth(), formatter);

                // 如果开始年月大于截止年月，说明是当月转入当月转出，无效费用段，逻辑删除
                if (startMonth.isAfter(endMonth)) {
                    hfArchiveBasePeriod.setActive(false);
                }
            }
            hfArchiveBasePeriod.setEmpTaskId(hfEmpTask.getEmpTaskId());
            hfArchiveBasePeriod.setRemitWay(lastHfArchiveBasePeriod.getRemitWay());
            hfArchiveBasePeriod.setArchiveBasePeriodId(lastHfArchiveBasePeriod.getArchiveBasePeriodId());
            hfArchiveBasePeriod.setHfMonth(hfEmpTaskPeriod.getHfMonth());
            hfArchiveBasePeriod.setEndMonth(hfEmpTaskPeriod.getEndMonth());
            hfArchiveBasePeriod.setModifiedBy(hfEmpTask.getModifiedBy());
            hfArchiveBasePeriod.setModifiedTime(LocalDateTime.now());
            hfArchiveBasePeriod.setBaseAmount(lastHfArchiveBasePeriod.getBaseAmount());
            hfArchiveBasePeriod.setRatio(lastHfArchiveBasePeriod.getRatio());
            hfArchiveBasePeriod.setRatioCom(lastHfArchiveBasePeriod.getRatioCom());
            hfArchiveBasePeriod.setRatioEmp(lastHfArchiveBasePeriod.getRatioEmp());
            hfArchiveBasePeriod.setAmount(lastHfArchiveBasePeriod.getAmount());
            hfArchiveBasePeriod.setAmountEmp(lastHfArchiveBasePeriod.getAmountEmp());
            hfArchiveBasePeriod.setComAmount(lastHfArchiveBasePeriod.getComAmount());
            hfArchiveBasePeriod.setCompanyId(lastHfArchiveBasePeriod.getCompanyId());
            hfArchiveBasePeriod.setEmployeeId(lastHfArchiveBasePeriod.getEmployeeId());
            hfArchiveBasePeriod.setEmpArchiveId(lastHfArchiveBasePeriod.getEmpArchiveId());
            hfArchiveBasePeriod.setHfType(lastHfArchiveBasePeriod.getHfType());
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
     * @param roundTypes 进位方式
     * @param roundTypeInWeight 权重进位方式
     */
    private void createHfMonthCharge(HfEmpTask hfEmpTask, List<HfArchiveBasePeriod> hfArchiveBasePeriodList, int[] roundTypes, int roundTypeInWeight) {
        int paymentType = 0;
        switch (hfEmpTask.getTaskCategory()) {
            case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_ADD:
                paymentType = HfMonthChargeConstant.PAYMENT_TYPE_NEW;
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_IN:
                paymentType = HfMonthChargeConstant.PAYMENT_TYPE_TRANS_IN;
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_OPEN:
                paymentType = HfMonthChargeConstant.PAYMENT_TYPE_OPEN;
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_REPAIR:
                paymentType = HfMonthChargeConstant.PAYMENT_TYPE_REPAIR;
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_ADJUST:
                paymentType = HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_OPEN;
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_CLOSE:
                paymentType = HfMonthChargeConstant.PAYMENT_TYPE_CLOSE;
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_OUT:
                paymentType = HfMonthChargeConstant.PAYMENT_TYPE_TRANS_OUT;
                break;
            default:
                break;
        }

        // 根据缴费开始年月至缴费截止年月的区间生成相应的雇员月度汇缴明细记录；
        // 而新进、转入、调整启封、启封、集体转入时，任务单没有缴费截止年月，那么仅根据缴费开始年月生成一条相应的雇员月度汇缴明细记录；
        for (HfArchiveBasePeriod e : hfArchiveBasePeriodList) {
            String startMonth = e.getStartMonth();
            String endMonth = e.getEndMonth();
            if (e.getRemitWay() == HfEmpTaskPeriodConstant.REMIT_WAY_ADJUST) {
                if (StringUtils.isNotEmpty(endMonth)) {
                    paymentType = HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_CLOSE;
//                        addCloseMonthRecharge(hfEmpTask, e.getHfMonth(), HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_CLOSE);
//                        continue;
                } else {
                    paymentType = HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_OPEN;
                }
            }
            YearMonth startMonthDate;
            YearMonth endMonthDate;

            if (paymentType != HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_CLOSE
                && paymentType != HfMonthChargeConstant.PAYMENT_TYPE_TRANS_OUT
                && paymentType != HfMonthChargeConstant.PAYMENT_TYPE_CLOSE
                ) {
                if (StringUtils.isEmpty(startMonth)) {
                    throw new BusinessException("雇员档案费用分段表中缴费起始月为空");
                }
                startMonthDate = YearMonth.parse(startMonth, formatter);
                endMonthDate = startMonthDate;

                if (StringUtils.isNotEmpty(endMonth)) {
                    endMonthDate = YearMonth.parse(endMonth, formatter);
                }

                // 如果是转出任务单，雇员月度汇缴明细库转入数据可能被删除（当月转入当月转出）
                HfMonthChargeBo hfMonthChargeBo = new HfMonthChargeBo();
                hfMonthChargeBo.setInactive(true);
                hfMonthChargeBo.setEmpArchiveId(e.getEmpArchiveId());
                hfMonthChargeBo.setHfType(e.getHfType());
                hfMonthChargeBo.setHfMonth(e.getHfMonth());
                hfMonthChargeBo.setSsMonthBelongStart(e.getHfMonth());
                hfMonthChargeBo.setSsMonthBelongEnd(e.getHfMonth());
                hfMonthChargeBo.setModifiedBy(hfEmpTask.getModifiedBy());
                hfMonthChargeService.updateHfMonthCharge(hfMonthChargeBo);
            } else {
                if (StringUtils.isEmpty(endMonth)) {
                    throw new BusinessException("雇员档案费用分段表中缴费截止月为空");
                }
                endMonthDate = YearMonth.parse(endMonth, formatter);
                startMonthDate = endMonthDate;
            }

            long months = startMonthDate.until(endMonthDate, ChronoUnit.MONTHS);
            List<HfMonthCharge> hfMonthChargeList = new ArrayList<>();
            BigDecimal comAmount = e.getComAmount();
            BigDecimal empAmount = e.getAmountEmp();
            BigDecimal amount = e.getAmount();

            if (e.getRemitWay() == HfEmpTaskPeriodConstant.REMIT_WAY_REPAIR) {
                paymentType = HfMonthChargeConstant.PAYMENT_TYPE_REPAIR;
            }

            if (e.getDiffRepair() != null && e.getDiffRepair()) {
                paymentType = HfMonthChargeConstant.PAYMENT_TYPE_DIFF_REPAIR;

                // 如果是补缴任务单，雇员月度汇缴明细库差额数据可能会被覆盖（某些年月被多次补缴）
                HfMonthChargeBo hfMonthChargeBo = new HfMonthChargeBo();
                hfMonthChargeBo.setInactive(true);
                hfMonthChargeBo.setEmpArchiveId(e.getEmpArchiveId());
                hfMonthChargeBo.setHfType(e.getHfType());
                hfMonthChargeBo.setHfMonth(e.getHfMonth()); // 按汇缴月份来覆盖
                hfMonthChargeBo.setSsMonthBelongStart(e.getStartMonth());
                hfMonthChargeBo.setSsMonthBelongEnd(e.getEndMonth());
//                    hfMonthChargeBo.setPaymentTypes(StringUtils.join(new Integer[] {
//                        HfMonthChargeConstant.PAYMENT_TYPE_REPAIR,
//                        HfMonthChargeConstant.PAYMENT_TYPE_DIFF_REPAIR
//                    }, ','));
                hfMonthChargeBo.setPaymentTypes(String.valueOf(HfMonthChargeConstant.PAYMENT_TYPE_DIFF_REPAIR));
                hfMonthChargeBo.setModifiedBy(hfEmpTask.getModifiedBy());
                hfMonthChargeService.updateHfMonthCharge(hfMonthChargeBo);

                HfMonthChargeDiffBo hfMonthChargeDiffBo = hfMonthChargeService.getHfMonthChargeDiffSum(hfMonthChargeBo);
                if (hfMonthChargeDiffBo != null) {
                    // 往期汇缴月份保留，新增数据需计算与原有雇员所属公积金月份相同数据的差额
                    amount = CalculateSocialUtils.calculateByRoundType(amount.subtract(hfMonthChargeDiffBo.getAmount()).setScale(3, BigDecimal.ROUND_HALF_UP), roundTypeInWeight);
                    comAmount = CalculateSocialUtils.calculateByRoundType(comAmount.subtract(hfMonthChargeDiffBo.getComAmount()), roundTypes[0]);
                    empAmount = CalculateSocialUtils.calculateByRoundType(empAmount.subtract(hfMonthChargeDiffBo.getEmpAmount()), roundTypes[1]);
                }
            }

            // 根据雇员费用段期间，每月生成一条雇员月度汇缴明细记录
            for (long i = 0; i <= months; i++) {
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
                hfMonthCharge.setPaymentType(paymentType);
                hfMonthCharge.setRepairReason(e.getRepairReason());
                hfMonthCharge.setCreatedBy(hfEmpTask.getModifiedBy());
                hfMonthCharge.setModifiedBy(hfEmpTask.getModifiedBy());
                hfMonthChargeList.add(hfMonthCharge);
            }
            hfMonthChargeService.insertBatch(hfMonthChargeList);
        }
    }

//    private void addCloseMonthRecharge(HfEmpTask hfEmpTask, String hfMonth, int paymentType) {
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("is_active", 1);
//        condition.put("hf_month", hfMonth);
//        condition.put("emp_archive_id", hfEmpTask.getEmpArchiveId());
//        condition.put("hf_type", hfEmpTask.getHfType());
//        condition.put("payment_type", HfMonthChargeConstant.PAYMENT_TYPE_NORMAL);
//        List<HfMonthCharge> hfMonthChargeList = hfMonthChargeService.selectByMap(condition);
//        if (CollectionUtils.isNotEmpty(hfMonthChargeList)) {
//            if (hfMonthChargeList.size() > 1) {
//                throw new BusinessException("开票时自动生成的标准雇员月度汇缴明细记录重复");
//            }
//        } else {
//            // 新进或转入当月就转出或封存时？NG
//            throw new BusinessException("开票时自动生成的标准雇员月度汇缴明细记录不存在");
//        }
//        HfMonthCharge hfMonthCharge = hfMonthChargeList.get(0);
//        hfMonthCharge.setMonthChargeId(null);
//        hfMonthCharge.setPaymentType(paymentType);
//        hfMonthCharge.setCreatedBy(hfEmpTask.getModifiedBy());
//        hfMonthCharge.setModifiedBy(hfEmpTask.getModifiedBy());
//        hfMonthChargeService.insert(hfMonthCharge);
//    }

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
        condition.put("hf_type", inputHfEmpTask.getHfType());
        condition.put("is_active", 1);
        boolean isNothing = false;
        Long empArchiveId = null;
        List<HfEmpArchive> hfEmpArchiveList = hfEmpArchiveService.selectByMap(condition);
        if (CollectionUtils.isNotEmpty(hfEmpArchiveList)) {
            if (hfEmpArchiveList.size() > 1) {
                return JsonResultKit.ofError("该雇员的雇员档案数据不正确");
            }
            empArchiveId = hfEmpArchiveList.get(0).getEmpArchiveId();

            hfEmpArchiveList = hfEmpArchiveList.stream()
                .filter(e -> e.getArchiveStatus() == null || e.getArchiveStatus() != HfEmpArchiveConstant.ARCHIVE_STATUS_CLOSED)
                .collect(Collectors.toList());
        } else {
            isNothing = true;
        }

        switch (inputHfEmpTask.getTaskCategory()) {
            case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
            case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
            case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_ADD:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_IN:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_OPEN:
                if (CollectionUtils.isNotEmpty(hfEmpArchiveList)) {
                    return JsonResultKit.ofError("雇员档案已存在，且非封存状态");
                }
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_REPAIR:
            case HfEmpTaskConstant.TASK_CATEGORY_ADJUST:
                if (isNothing) {
                    return JsonResultKit.ofError("雇员档案不存在");
                }
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
            case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_OUT:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_CLOSE:
                if (CollectionUtils.isEmpty(hfEmpArchiveList)) {
                    return JsonResultKit.ofError("非封存状态雇员档案不存在");
                }
                break;
            case HfEmpTaskConstant.TASK_CATEGORY_TRANSFER_TASK:
                return JsonResultKit.ofError("非雇员日常操作任务单类型");
//                break;
            default:
                break;
        }

        inputHfEmpTask.setTaskStatus(HfEmpTaskConstant.TASK_STATUS_HANDLED);
//        inputHfEmpTask.setHandleStatus(2);
        inputHfEmpTask.setHandleDate(YearMonth.now().format(formatter));
        inputHfEmpTask.setHandleUserId(inputHfEmpTask.getModifiedBy());
        inputHfEmpTask.setHandleUserName(inputHfEmpTask.getModifiedBy());

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
        LogMessage logMessage = LogMessage.create().setTitle("办理任务单")
            .setContent("雇员档案数据新增或更新").setTags(new HashMap<String, String>() {{
                put("empArchiveId", String.valueOf(inputHfEmpTask.getEmpArchiveId()) );
            }});
        logApiUtil.debug(logMessage);
        hfEmpArchive.setStartMonth(inputHfEmpTask.getStartMonth());
//        hfEmpArchive.setEndMonth(inputHfEmpTask.getEndMonth());
        hfEmpArchive.setOperationRemind(inputHfEmpTask.getOperationRemind());
        hfEmpArchive.setOperationRemindDate(inputHfEmpTask.getOperationRemindDate());

        // 办理状态变更
        setEmpArchiveStatus(hfEmpArchive, inputHfEmpTask.getTaskCategory());

        hfEmpArchive.setModifiedBy(inputHfEmpTask.getModifiedBy());
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
            hfEmpArchive.setCreatedBy(inputHfEmpTask.getModifiedBy());
            isNew = true;
        } else {
            hfEmpArchive.setEmpArchiveId(empArchiveId);
            hfEmpArchive.setModifiedTime(LocalDateTime.now());
        }
        hfEmpArchiveService.insertOrUpdate(hfEmpArchive);

        if (isNew) {
            inputHfEmpTask.setEmpArchiveId(hfEmpArchive.getEmpArchiveId());
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
     * @param roundTypes 进位方式
     * @param roundTypeInWeight 权重进位方式
     */
    private void setHfArchiveBaseAdjust(List<HfArchiveBaseAdjust> hfArchiveBaseAdjustList,
                                        HfEmpTask hfEmpTask,
                                        HfEmpTaskPeriod hfEmpTaskPeriod,
                                        HfArchiveBasePeriod hfArchiveBasePeriod,
                                        int[] roundTypes,
                                        int roundTypeInWeight) {
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
        BigDecimal ratio = hfEmpTaskPeriod.getRatioCom().add(hfEmpTaskPeriod.getRatioEmp()).setScale(3, BigDecimal.ROUND_HALF_UP);
        hfArchiveBaseAdjust.setRatio(ratio);
        hfArchiveBaseAdjust.setRatioCom(hfEmpTaskPeriod.getRatioCom());
        hfArchiveBaseAdjust.setRatioEmp(hfEmpTaskPeriod.getRatioEmp());

        BigDecimal existRatio = hfArchiveBasePeriod.getRatioCom().add(hfArchiveBasePeriod.getRatioEmp()).setScale(3, BigDecimal.ROUND_HALF_UP);
        BigDecimal months = BigDecimal.valueOf(YearMonth.parse(startMonth, formatter).until(YearMonth.parse(endMonth, formatter), ChronoUnit.MONTHS) + 1);
        BigDecimal singleComDiffAmount = hfEmpTaskPeriod.getAmount().multiply(hfEmpTaskPeriod.getRatioCom()).divide(ratio, 3, BigDecimal.ROUND_HALF_UP)
            .subtract(hfArchiveBasePeriod.getAmount().multiply(hfArchiveBasePeriod.getRatioCom()).divide(existRatio, 3, BigDecimal.ROUND_HALF_UP));
        BigDecimal singleEmpDiffAmount = hfEmpTaskPeriod.getAmount().multiply(hfEmpTaskPeriod.getRatioEmp()).divide(ratio, 3, BigDecimal.ROUND_HALF_UP)
            .subtract(hfArchiveBasePeriod.getAmount().multiply(hfArchiveBasePeriod.getRatioEmp()).divide(existRatio, 3, BigDecimal.ROUND_HALF_UP));

        singleComDiffAmount = CalculateSocialUtils.calculateByRoundType(singleComDiffAmount, roundTypes[0]);
        singleEmpDiffAmount = CalculateSocialUtils.calculateByRoundType(singleEmpDiffAmount, roundTypes[1]);

        BigDecimal comDiffAmount = CalculateSocialUtils.calculateByRoundType(singleComDiffAmount.multiply(months), roundTypes[0]);
        BigDecimal empDiffAmount = CalculateSocialUtils.calculateByRoundType(singleEmpDiffAmount.multiply(months), roundTypes[1]);
        hfArchiveBaseAdjust.setComDiffSumAmount(comDiffAmount);
        hfArchiveBaseAdjust.setEmpDiffSumAmount(empDiffAmount);
        hfArchiveBaseAdjust.setComempSumDiffAmount(CalculateSocialUtils.calculateByRoundType(comDiffAmount.add(empDiffAmount), roundTypeInWeight));
        hfArchiveBaseAdjust.setCreatedBy(hfEmpTask.getModifiedBy());
        hfArchiveBaseAdjust.setModifiedBy(hfEmpTask.getModifiedBy());
        hfArchiveBaseAdjustList.add(hfArchiveBaseAdjust);

        // 仅是为了提高执行效率，在后面生成雇员月度汇缴明细库时无需重新计算，借用该两个字段（差额补缴时不更新档案费用分段表）
        hfArchiveBasePeriod.setComAmount(singleComDiffAmount);
        hfArchiveBasePeriod.setAmountEmp(singleEmpDiffAmount);
    }

    /**
     * 访问客服中心的完成任务接口
     *
     * @param taskId 任务单ID
     * @param assignee 办理人
     * @return 接口返回结果
     * @throws Exception 接口throws出的Exception
     */
    public Result apiCompleteTask(String taskId, String assignee) throws Exception {
        TaskSheetRequestDTO taskSheetRequestDTO = new TaskSheetRequestDTO();
        taskSheetRequestDTO.setTaskId(taskId);
        taskSheetRequestDTO.setAssignee(assignee);
        Result result = commonApiUtils.completeTask(taskSheetRequestDTO);
        LogMessage logMessage = LogMessage.create().setTitle("访问接口")
            .setContent("访问客服中心的完成任务接口")
            .setTags(new HashMap<String, String>() {{
            put("code", String.valueOf(result.getCode()));
        }});
        logApiUtil.info(logMessage);
        return result;
    }

    /**
     * 访问雇员任务单实缴金额回调接口（支持中心调用客服中心）
     *
     * @param companyId 客户编号
     * @param empAgreementId 业务接口ID
     * @param hfEmpTask 任务单
     * @param hfArchiveBasePeriodList 雇员费用段列表
     * @param isReject 是否批退
     * @return 接口返回结果
     * @throws Exception 接口throws出的Exception
     */
    public int apiUpdateConfirmDate(String companyId,
                                       Long empAgreementId,
                                       HfEmpTask hfEmpTask,
                                       List<HfArchiveBasePeriod> hfArchiveBasePeriodList,
                                       boolean isReject) throws Exception {
        List<AfEmpSocialUpdateDateDTO> afEmpSocialUpdateDateDTOList = new ArrayList<>();
        DateKit.setDatePattern("yyyyMMdd");
        BigDecimal companyConfirmAmount = BigDecimal.ZERO;
        BigDecimal personalConfirmAmount = BigDecimal.ZERO;
        String startMonth = hfEmpTask.getStartMonth();
        String endMonth = hfEmpTask.getEndMonth();
        Map<String, String> tags = new HashMap<>();

        if (isReject) {
            // 批退的处理方式
            AfEmpSocialUpdateDateDTO afEmpSocialUpdateDateDTO = new AfEmpSocialUpdateDateDTO();
            afEmpSocialUpdateDateDTO.setCompanyId(companyId);
            if (hfEmpTask.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
                afEmpSocialUpdateDateDTO.setItemCode(DictUtil.DICT_ITEM_ID_FUND_BASIC);
            } else {
                afEmpSocialUpdateDateDTO.setItemCode(DictUtil.DICT_ITEM_ID_FUND_ADDED);
            }
            afEmpSocialUpdateDateDTO.setCompanyConfirmAmount(companyConfirmAmount);
            afEmpSocialUpdateDateDTO.setPersonalConfirmAmount(personalConfirmAmount);
            if (StringUtils.isNotEmpty(startMonth)) {
                afEmpSocialUpdateDateDTO.setStartConfirmDate(DateKit.toDate(startMonth + "01"));
                LocalDate endMonthDate = LocalDate.parse(startMonth + "01", yyyyMMddFormatter);
                // 关闭日期为开始月的前一个月的最后一天
                afEmpSocialUpdateDateDTO.setEndConfirmDate(DateKit.toDate(endMonthDate.minusDays(1).format(yyyyMMddFormatter)));
            } else {
                throw new BusinessException("停办不能批退");
            }
            afEmpSocialUpdateDateDTO.setEmpAgreementId(empAgreementId);
            afEmpSocialUpdateDateDTOList.add(afEmpSocialUpdateDateDTO);
            tags.put("rejectAfEmpSocialUpdateDateDTO", JsonKit.toStr(afEmpSocialUpdateDateDTO));
        } else {
            if (CollectionUtils.isNotEmpty(hfArchiveBasePeriodList)) {
                String hfMonth = null;

                for (HfArchiveBasePeriod hfArchiveBasePeriod : hfArchiveBasePeriodList) {
                    if (!isReject && companyConfirmAmount == BigDecimal.ZERO) {
                        companyConfirmAmount = hfArchiveBasePeriod.getComAmount();
                        personalConfirmAmount = hfArchiveBasePeriod.getAmountEmp();
                    }

                    // 正常或调整的费用段的汇缴月份
                    if (HfEmpTaskPeriodConstant.REMIT_WAY_NORMAL == hfArchiveBasePeriod.getRemitWay()
                        || HfEmpTaskPeriodConstant.REMIT_WAY_ADJUST == hfArchiveBasePeriod.getRemitWay()) {
                        hfMonth = hfArchiveBasePeriod.getHfMonth();
                    }
                }

                // 以下为调整前的费用段处理：
                // 如果oldAgreementId存在时，则要回调接口，通知前道关闭费用段
                if (StringUtils.isNotEmpty(hfEmpTask.getOldAgreementId())) {
                    AfEmpSocialUpdateDateDTO afEmpSocialUpdateDateDTO = new AfEmpSocialUpdateDateDTO();
                    afEmpSocialUpdateDateDTO.setCompanyId(companyId);
                    if (hfEmpTask.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
                        afEmpSocialUpdateDateDTO.setItemCode(DictUtil.DICT_ITEM_ID_FUND_BASIC);
                    } else {
                        afEmpSocialUpdateDateDTO.setItemCode(DictUtil.DICT_ITEM_ID_FUND_ADDED);
                    }
                    afEmpSocialUpdateDateDTO.setCompanyConfirmAmount(companyConfirmAmount);
                    afEmpSocialUpdateDateDTO.setPersonalConfirmAmount(personalConfirmAmount);
                    if (StringUtils.isNotEmpty(hfMonth)) {
                        LocalDate hfMonthDate = LocalDate.parse(hfMonth + "01", yyyyMMddFormatter);
                        // 关闭日期为汇缴月的前一个月的最后一天
                        afEmpSocialUpdateDateDTO.setEndConfirmDate(DateKit.toDate(hfMonthDate.minusDays(1).format(yyyyMMddFormatter)));
                    }

                    afEmpSocialUpdateDateDTO.setEmpAgreementId(Long.valueOf(hfEmpTask.getOldAgreementId()));
                    afEmpSocialUpdateDateDTOList.add(afEmpSocialUpdateDateDTO);
                    tags.put("oldAfEmpSocialUpdateDateDTO", JsonKit.toStr(afEmpSocialUpdateDateDTO));
                }

                // 以下为调整后的费用段回调处理：
                // 如果oldAgreementId存在，且是转出或封存时，说明是调整非0转0
                if (StringUtils.isNotEmpty(hfEmpTask.getOldAgreementId()) && (
                    hfEmpTask.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT
                        || hfEmpTask.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE
                )) {
                    // 此时需要回调一个只有开始确认时间的，金额为0的费用段
                    AfEmpSocialUpdateDateDTO afEmpSocialUpdateDateDTO = new AfEmpSocialUpdateDateDTO();
                    afEmpSocialUpdateDateDTO.setCompanyId(companyId);
                    if (hfEmpTask.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
                        afEmpSocialUpdateDateDTO.setItemCode(DictUtil.DICT_ITEM_ID_FUND_BASIC);
                    } else {
                        afEmpSocialUpdateDateDTO.setItemCode(DictUtil.DICT_ITEM_ID_FUND_ADDED);
                    }
                    afEmpSocialUpdateDateDTO.setCompanyConfirmAmount(BigDecimal.ZERO);
                    afEmpSocialUpdateDateDTO.setPersonalConfirmAmount(BigDecimal.ZERO);
                    if (StringUtils.isNotEmpty(endMonth)) {
                        afEmpSocialUpdateDateDTO.setStartConfirmDate(DateKit.toDate(endMonth + "01"));
                    }
                    afEmpSocialUpdateDateDTO.setEmpAgreementId(empAgreementId);
                    afEmpSocialUpdateDateDTOList.add(afEmpSocialUpdateDateDTO);
                    tags.put("newAfEmpSocialUpdateDateDTO", JsonKit.toStr(afEmpSocialUpdateDateDTO));
                } else {
                    // 通常的处理方式
                    AfEmpSocialUpdateDateDTO afEmpSocialUpdateDateDTO = new AfEmpSocialUpdateDateDTO();
                    afEmpSocialUpdateDateDTO.setCompanyId(companyId);
                    if (hfEmpTask.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
                        afEmpSocialUpdateDateDTO.setItemCode(DictUtil.DICT_ITEM_ID_FUND_BASIC);
                    } else {
                        afEmpSocialUpdateDateDTO.setItemCode(DictUtil.DICT_ITEM_ID_FUND_ADDED);
                    }
                    afEmpSocialUpdateDateDTO.setCompanyConfirmAmount(companyConfirmAmount);
                    afEmpSocialUpdateDateDTO.setPersonalConfirmAmount(personalConfirmAmount);
                    if (StringUtils.isNotEmpty(startMonth)) {
                        afEmpSocialUpdateDateDTO.setStartConfirmDate(DateKit.toDate(startMonth + "01"));
                    }
                    if (StringUtils.isNotEmpty(endMonth)) {
                        afEmpSocialUpdateDateDTO.setEndConfirmDate(DateKit.toDate(endMonth + "01"));
                    }
                    afEmpSocialUpdateDateDTO.setEmpAgreementId(empAgreementId);
                    afEmpSocialUpdateDateDTOList.add(afEmpSocialUpdateDateDTO);
                    tags.put("newAfEmpSocialUpdateDateDTO", JsonKit.toStr(afEmpSocialUpdateDateDTO));
                }
            }

            int code = commonApiUtils.updateConfirmDate(afEmpSocialUpdateDateDTOList);
            tags.put("code", String.valueOf(code));

            LogMessage logMessage = LogMessage.create().setTitle("访问接口")
                .setContent("访问雇员任务单实缴金额回调接口")
                .setTags(tags);
            logApiUtil.info(logMessage);
            return code;
        }
        return 0;
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
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_ADD:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_IN:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_OPEN:
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
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_OUT:
            case HfEmpTaskConstant.TASK_CATEGORY_FLOP_CLOSE:
                hfEmpArchive.setArchiveTaskStatus(HfEmpArchiveConstant.ARCHIVE_TASK_STATUS_CLOSED);
                hfEmpArchive.setArchiveStatus(HfEmpArchiveConstant.ARCHIVE_STATUS_CLOSED);
                break;
            default:
                break;
        }
    }

    @Override
    public int createTransEmpTask(@RequestBody  HfEmpTaskCreateTransBo hfEmpTaskCreateTransBo) {
        return baseMapper.createTransEmpTask(hfEmpTaskCreateTransBo);
    }

    @Override
    public int[] getRoundTypeProxy(String policyId, Integer payAccountType, String effectiveMonth, String hfTypeDicItemCode) {
        GetSSPItemsRequestDTO getSSPItemsRequestDTO = new GetSSPItemsRequestDTO();
        getSSPItemsRequestDTO.setSsPolicyId(policyId);
        getSSPItemsRequestDTO.setPayAccountType(payAccountType);
        getSSPItemsRequestDTO.setEffectiveMonth(effectiveMonth);
        com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.JsonResult<GetSSPItemsResposeDTO> result = commonApiUtils.getRoundingType(getSSPItemsRequestDTO);

        if (result.getCode() == 0) {
            GetSSPItemsResposeDTO getSSPItemsResposeDTO = result.getData();
            if (getSSPItemsResposeDTO != null) {
                List<SSPItemDTO> list = getSSPItemsResposeDTO.getItems();
                if (CollectionUtils.isNotEmpty(list)) {
                    for (SSPItemDTO sspItemDTO : list) {
                        if (hfTypeDicItemCode.equals(sspItemDTO.getItemcode())) {
                            return new int[] { sspItemDTO.getCompanyRoundType(), sspItemDTO.getPersonRoundType() };
                        }
                    }
                }
            }
        } else {
            LogMessage logMessage = LogMessage.create().setTitle("访问接口").
                setContent("访问内控中心的获取进位方式接口返回值不正确,Code:" + result.getCode() + "Message:" + result.getMessage());
            logApiUtil.error(logMessage);
        }
        return null;
    }
}
