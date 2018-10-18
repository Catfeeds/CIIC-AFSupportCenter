package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.RedisManager;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountTransBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfComAccountConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskPeriodConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fundcommandservice/hfEmpTaskHandle")
public class HfEmpTaskHandleController extends BasicController<HfEmpTaskHandleService> {

    @Autowired
    private HfArchiveBasePeriodService hfArchiveBasePeriodService;
    @Autowired
    private HfEmpTaskPeriodService hfEmpTaskPeriodService;
    @Autowired
    private HfComAccountService hfComAccountService;
    @Autowired
    private HfEmpArchiveService hfEmpArchiveService;
    @Autowired
    private HfEmpPreInputService hfEmpPreInputService;
    @Autowired
    private HfMonthChargeService hfMonthChargeService;
    @Autowired
    private HfCalcSettingService hfCalcSettingService;
    @Autowired
    private LogApiUtil logApiUtil;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");

    /**
     * 雇员任务单办理信息查询
     *
     * @param hfEmpTaskHandlePostBo 前端提交参数
     * @return JSON格式处理结果
     */
    @RequestMapping("/empTaskHandleDataQuery")
    public JsonResult<HfEmpTaskHandleBo> empTaskHandleDataQuery(HfEmpTaskHandlePostBo hfEmpTaskHandlePostBo) {
        hfEmpTaskHandlePostBo.setUserId(UserContext.getUserId());
        // 获取当前任务单主体信息
        List<HfEmpTaskHandleBo> list = business.getEmpTaskHandleData(hfEmpTaskHandlePostBo);
        if (CollectionUtils.isNotEmpty(list)) {
            if (list.size() > 1) {
                return JsonResultKit.ofError("当前雇员任务单相关数据有问题，请检查");
            }

            HfEmpTaskHandleBo hfEmpTaskHandleBo = list.get(0);
            if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_TRANSFER_TASK) {
                return JsonResultKit.ofError("当前雇员任务单的任务类型不正确");
            }
            if ((hfEmpTaskHandleBo.getTaskStatus() != null && !hfEmpTaskHandleBo.getTaskStatus().equals(hfEmpTaskHandlePostBo.getTaskStatus()))
                || (hfEmpTaskHandleBo.getTaskStatus() == null && hfEmpTaskHandlePostBo.getTaskStatus() != HfEmpTaskConstant.TASK_STATUS_UNHANDLED)) {
                return JsonResultKit.ofError("当前雇员任务单状态已变更，请到其他状态页签中查找");
            }

            String effectiveMonth = hfEmpTaskHandleBo.getStartMonth();
            if (StringUtils.isEmpty(effectiveMonth)) {
                effectiveMonth = hfEmpTaskHandleBo.getEndMonth();
            }

            BigDecimal[] empAmountAndRatio = hfCalcSettingService.getEmpAmountAndRatio(hfEmpTaskHandleBo.getEmpBase(),
                hfEmpTaskHandleBo.getRatioCom(),
                hfEmpTaskHandleBo.getRatioEmp(),
                hfEmpTaskHandleBo.getHfType(),
                effectiveMonth
            );

            if (empAmountAndRatio == null) {
                return JsonResultKit.ofError("缺少适合的金额进位方式配置，请联系系统管理员");
            }

            hfEmpTaskHandleBo.setCanHandle(false);
            if (hfEmpTaskHandleBo.getComAccountId() != null) {
                if (hfEmpTaskHandleBo.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC && hfEmpTaskHandleBo.getBasicComAccountClassId() != null) {
                    hfEmpTaskHandleBo.setCanHandle(true);
                } else if (hfEmpTaskHandleBo.getHfType() == HfEmpTaskConstant.HF_TYPE_ADDED && hfEmpTaskHandleBo.getAddedComAccountClassId() != null) {
                    hfEmpTaskHandleBo.setCanHandle(true);
                }
            }

            if (StringUtils.isEmpty(hfEmpTaskHandleBo.getHfEmpAccount())) {
                String empAccount = hfEmpArchiveService.getEmpAccountByEmployeeId(hfEmpTaskHandleBo.getEmployeeId(), hfEmpTaskHandleBo.getHfType());

                if (StringUtils.isEmpty(empAccount)) {
                    EntityWrapper<HfEmpPreInput> wrapper = new EntityWrapper<>();
                    wrapper.where("is_active = 1").and("employee_id = {0}", hfEmpTaskHandleBo.getEmployeeId());
                    HfEmpPreInput hfEmpPreInput = hfEmpPreInputService.selectOne(wrapper);

                    if (hfEmpPreInput != null) {
                        if (hfEmpTaskHandleBo.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
                            empAccount = hfEmpPreInput.getHfEmpBasAccount();
                        } else {
                            empAccount = hfEmpPreInput.getHfEmpAddAccount();
                        }
                    }
                }
                hfEmpTaskHandleBo.setHfEmpAccount(empAccount);
            }

            hfMonthChargeService.getMonthChargeByInOut(hfEmpTaskHandleBo);

            // 根据雇员档案ID获取雇员基本公积金汇缴月份段信息
            Map<String, Object> condition = new HashMap<>();
//            if (hfEmpTaskHandleBo.getBasicEmpArchiveId() != null) {
            condition.put("is_active", 1);
            condition.put("company_id", hfEmpTaskHandleBo.getCompanyId());
            condition.put("employee_id", hfEmpTaskHandleBo.getEmployeeId());
//                condition.put("emp_archive_id", hfEmpTaskHandleBo.getBasicEmpArchiveId());
            condition.put("hf_type", 1);
            hfEmpTaskHandleBo.setBasicArchiveBasePeriods(hfArchiveBasePeriodService.selectByMap(condition));
//            }

            // 根据雇员档案ID获取雇员补充公积金汇缴月份段信息
//            if (hfEmpTaskHandleBo.getAddedEmpArchiveId() != null) {
//                condition.put("emp_archive_id", hfEmpTaskHandleBo.getAddedEmpArchiveId());
            condition.put("hf_type", 2);
            hfEmpTaskHandleBo.setAddedArchiveBasePeriods(hfArchiveBasePeriodService.selectByMap(condition));
//            }

            // 根据任务单ID获取任务单费用段信息
            condition.clear();
            condition.put("emp_task_id", hfEmpTaskHandleBo.getEmpTaskId());
            condition.put("is_active", 1);
            List<HfEmpTaskPeriod> hfEmpTaskPeriods = hfEmpTaskPeriodService.selectByMap(condition);
            if (CollectionUtils.isEmpty(hfEmpTaskPeriods)) {
                String hfMonth = null;
                String startMonth = hfEmpTaskHandleBo.getStartMonth();
                if (hfEmpTaskHandlePostBo.getHfType() == 1) {
                    if (hfEmpTaskHandleBo.getBasicComHfMonth() != null) {
                        hfMonth = String.valueOf(hfEmpTaskHandleBo.getBasicComHfMonth());
                    }
                } else {
                    if (hfEmpTaskHandleBo.getAddedComHfMonth() != null) {
                        hfMonth = String.valueOf(hfEmpTaskHandleBo.getAddedComHfMonth());
                    }
                }

                if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_IN_ADD
                    || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN
                    || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN
                    || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_FLOP_ADD
                    || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_IN
                    || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_FLOP_OPEN
                    || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_ADJUST) {
                    if (StringUtils.isNotEmpty(hfMonth) && StringUtils.isNotEmpty(startMonth)) {
                        YearMonth hfMonthDate = YearMonth.parse(hfMonth, formatter);
                        YearMonth startMonthDate = YearMonth.parse(startMonth, formatter);
                        HfEmpTaskPeriod hfEmpTaskPeriod = new HfEmpTaskPeriod();

                        // 如果任务单起缴月份小于客户汇缴月，则小于的月份自动分成一个补缴的费用段
                        if (startMonthDate.isBefore(hfMonthDate)) {
                            YearMonth endMonthDate = hfMonthDate.minusMonths(1);
//                            boolean isBreak = false;
//                            boolean isStart = false;
//
//                            if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_ADJUST) {  // 如果是调整类型的补缴费用段
//                                EntityWrapper<HfArchiveBasePeriod> wrapper = new EntityWrapper<>();
//                                wrapper.where("employee_id={0} AND company_id={1} AND hf_type={2} AND is_active = 1 AND start_month >= {1}",
//                                    hfEmpTaskHandleBo.getEmployeeId(),
//                                    hfEmpTaskHandleBo.getCompanyId(),
//                                    hfEmpTaskHandleBo.getHfType(),
//                                    startMonth);
//                                wrapper.orderBy("start_month", true);
//                                // 首先按照费用段的开始年月排序取出当前雇员的所有费用段
//                                List<HfArchiveBasePeriod> existHfArchiveBasePeriodList = hfArchiveBasePeriodService.selectList(wrapper);
//                                if (CollectionUtils.isNotEmpty(existHfArchiveBasePeriodList)) {
//                                    // 然后将雇员费用段连续的时间段进行组合（由于存在全额补缴，可能将原本不连续的时间段重新连接起来，但是费用段本身还是多条记录）
//                                    List<ComposedEmpBasePeriodBO> composedEmpBasePeriodBOList = business.composeEmpBasePeriod(existHfArchiveBasePeriodList);
//                                    for (ComposedEmpBasePeriodBO composedEmpBasePeriodBO : composedEmpBasePeriodBOList) {
//                                        // 如果费用段开始年月在雇员某费用连续段中
//                                        if (!isStart && startMonthDate.isBefore(composedEmpBasePeriodBO.getStartMonth())) {
//                                            startMonthDate = composedEmpBasePeriodBO.getStartMonth();
//                                            isStart = true;
//                                        }
//                                        // 且费用段截止年月也在雇员某费用连续段中（说明该连续费用段已截止，对当前费用段差额补缴，而整个调整任务仅逆调）
//                                        if (composedEmpBasePeriodBO.getEndMonth() != null && endMonthDate.isAfter(composedEmpBasePeriodBO.getEndMonth())) {
//                                            endMonthDate = composedEmpBasePeriodBO.getEndMonth();
//                                            isBreak = true;
//                                            break;
//                                        }
//                                    }
//                                }
//                            }
//                            if (!isBreak) {   // 如果所在雇员的连续费用段未截止，那么说明存在顺调（调整任务单）；或者正常汇缴（其它类型任务单）
                            hfEmpTaskPeriod.setEmpTaskId(hfEmpTaskHandleBo.getEmpTaskId());
                            if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_ADJUST) {
                                hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_ADJUST);
                            } else {
                                hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_NORMAL);
                            }
                            hfEmpTaskPeriod.setStartMonth(hfMonth);
                            hfEmpTaskPeriod.setHfMonth(hfMonth);
                            hfEmpTaskPeriod.setBaseAmount(hfEmpTaskHandleBo.getEmpBase());
                            hfEmpTaskPeriod.setRatioCom(empAmountAndRatio[1]);
                            hfEmpTaskPeriod.setRatioEmp(empAmountAndRatio[1]);
                            hfEmpTaskPeriod.setAmount(empAmountAndRatio[2]);
                            hfEmpTaskPeriods.add(hfEmpTaskPeriod);
//                            }
                            // 补缴费用段
                            hfEmpTaskPeriod = new HfEmpTaskPeriod();
                            hfEmpTaskPeriod.setEmpTaskId(hfEmpTaskHandleBo.getEmpTaskId());
                            hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_REPAIR);
                            hfEmpTaskPeriod.setStartMonth(startMonthDate.format(formatter));
                            hfEmpTaskPeriod.setEndMonth(endMonthDate.format(formatter));
//                            if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_ADJUST) {
//                                hfEmpTaskPeriod.setHfMonth(hfMonth);
//                            } else {
                            // 如果任务单类型是新开且账户分类是独立户则客户汇缴月=企业末次汇缴月 + 1
                            if ((hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_IN_ADD
                                || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_FLOP_ADD)
                                && hfEmpTaskHandleBo.getHfAccountType() == HfComAccountConstant.HF_ACCOUNT_TYPE_INDEPENDENT
                                && hfEmpTaskHandleBo.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
                                hfEmpTaskPeriod.setHfMonth(hfMonthDate.plusMonths(1).format(formatter));
                            } else {
                                // 否则客户汇缴月=企业末次汇缴月
                                hfEmpTaskPeriod.setHfMonth(hfMonth);
                            }
//                            }
                            hfEmpTaskPeriod.setBaseAmount(hfEmpTaskHandleBo.getEmpBase());
                            hfEmpTaskPeriod.setRatioCom(empAmountAndRatio[1]);
                            hfEmpTaskPeriod.setRatioEmp(empAmountAndRatio[1]);
                            hfEmpTaskPeriod.setAmount(empAmountAndRatio[2]);
                            hfEmpTaskPeriods.add(hfEmpTaskPeriod);
                        } else {  // 否则，说明是正常汇缴费用段
                            hfEmpTaskPeriod.setEmpTaskId(hfEmpTaskHandleBo.getEmpTaskId());
                            if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_ADJUST) {
                                hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_ADJUST);
                            } else {
                                hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_NORMAL);
                            }
                            hfEmpTaskPeriod.setStartMonth(startMonth);
                            if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_ADJUST) {
                                hfEmpTaskPeriod.setHfMonth(hfMonth);
                            } else {
                                hfEmpTaskPeriod.setHfMonth(startMonth);
                            }
                            hfEmpTaskPeriod.setBaseAmount(hfEmpTaskHandleBo.getEmpBase());
                            hfEmpTaskPeriod.setRatioCom(empAmountAndRatio[1]);
                            hfEmpTaskPeriod.setRatioEmp(empAmountAndRatio[1]);
                            hfEmpTaskPeriod.setAmount(empAmountAndRatio[2]);
                            hfEmpTaskPeriods.add(hfEmpTaskPeriod);
                        }
                    }
                }

                if (hfEmpTaskPeriods.size() == 0) {
                    HfEmpTaskPeriod hfEmpTaskPeriod = new HfEmpTaskPeriod();
                    hfEmpTaskPeriod.setEmpTaskId(hfEmpTaskHandleBo.getEmpTaskId());
                    if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_ADJUST) {
                        hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_ADJUST);
                    } else if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_REPAIR) {
                        hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_REPAIR);
                    } else {
                        hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_NORMAL);
                    }

                    hfEmpTaskPeriod.setStartMonth(hfEmpTaskHandleBo.getStartMonth());
                    hfEmpTaskPeriod.setEndMonth(hfEmpTaskHandleBo.getEndMonth());
                    hfEmpTaskPeriod.setHfMonth(hfMonth);
                    hfEmpTaskPeriod.setBaseAmount(hfEmpTaskHandleBo.getEmpBase());
                    hfEmpTaskPeriod.setRatioCom(empAmountAndRatio[1]);
                    hfEmpTaskPeriod.setRatioEmp(empAmountAndRatio[1]);
                    hfEmpTaskPeriod.setAmount(empAmountAndRatio[2]);
                    hfEmpTaskPeriods.add(hfEmpTaskPeriod);
                }
            } else if (
                    hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE
                    || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT
                    || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_FLOP_TRANS_OUT
                    || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_FLOP_CLOSE
                ) {
                if (hfEmpTaskPeriods.size() > 1) {
                    return JsonResultKit.ofError("当前雇员任务单费用段数据不正确");
                }
//                hfEmpTaskHandleBo.setEndMonth(hfEmpTaskPeriods.get(0).getEndMonth());
            }
            hfEmpTaskHandleBo.setEmpTaskPeriods(hfEmpTaskPeriods);

//            // 查询当前雇员除该任务单之外的所有任务单信息
//            EntityWrapper<HfEmpTask> wrapper = new EntityWrapper<>();
//            wrapper.where("company_id={0} AND employee_id={1} AND emp_task_id != {2} AND task_status = 1 AND task_category != 8 AND is_active = 1",
//                hfEmpTaskHandleBo.getCompanyId(), hfEmpTaskHandleBo.getEmployeeId(), hfEmpTaskHandleBo.getEmpTaskId());
//            wrapper.orderBy("created_time", false);
//            List<HfEmpTask> hfEmpTasks = business.selectList(wrapper);
//            if (CollectionUtils.isNotEmpty(hfEmpTasks)) {
//                List<HfEmpTaskRemarkBo> hfEmpTaskRemarkBos = new ArrayList<>();
//                hfEmpTasks.stream().forEach((e) -> {
//                    HfEmpTaskRemarkBo bo = new HfEmpTaskRemarkBo();
//                    bo.setEmpTaskId(e.getEmpTaskId());
//                    bo.setHfType(e.getHfType());
//                    bo.setTaskCategory(e.getTaskCategory());
//                    bo.setTaskStatus(e.getTaskStatus());
//                    bo.setModifiedBy(e.getModifiedBy());
//                    bo.setModifiedDisplayName(e.getModifiedDisplayName());
//                    bo.setModifiedTime(e.getModifiedTime());
//                    bo.setHandleRemark(e.getHandleRemark());
//                    bo.setRejectionRemark(e.getRejectionRemark());
//                    hfEmpTaskRemarkBos.add(bo);
//                });
//                hfEmpTaskHandleBo.setEmpTaskRemarks(hfEmpTaskRemarkBos);
//            }

            return JsonResultKit.of(hfEmpTaskHandleBo);
        } else {
            return JsonResultKit.ofError("获取当前任务单失败");
        }
    }

    /**
     * 雇员任务单办理信息保存
     *
     * @param params 前端提交JSON格式参数
     * @return JSON格式处理结果
     */
    @RequestMapping("/empTaskHandleDataSave")
    public JsonResult empTaskHandleDataSave(@RequestBody JSONObject params) {
        try {
            return business.inputDataSave(params, false);
        } catch (BusinessException e) {
            return JsonResultKit.ofError(e.getMessage());
        }
    }

    /**
     * 雇员任务单办理
     *
     * @param params 前端提交JSON格式参数
     * @return JSON格式处理结果
     */
    @RequestMapping("/empTaskHandle")
    public JsonResult empTaskHandle(@RequestBody JSONObject params) {
        try {
            return business.inputDataSave(params, true);
        } catch (BusinessException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logApiUtil.error(LogMessage.create().setTitle("HfEmpTaskHandleController#empTaskHandle").setContent(sw.toString()));
            return JsonResultKit.ofError(e.getMessage());
        }
    }

    /**
     * 雇员任务单不需处理
     *
     * @param empTaskId 当前任务单ID
     * @return JSON格式处理结果
     */
    @RequestMapping("/empTaskNotHandle")
    public JsonResult empTaskNotHandle(Long empTaskId) {
        HfEmpTask hfEmpTask = business.selectById(empTaskId);
        if (hfEmpTask == null || !hfEmpTask.getActive()) {
            return JsonResultKit.ofError("当前任务单已不存在");
        }
        // TODO 可进行“不需处理”的判断条件？
        hfEmpTask = new HfEmpTask();
        hfEmpTask.setEmpTaskId(empTaskId);
        hfEmpTask.setTaskStatus(HfEmpTaskConstant.TASK_STATUS_NOT_HANDLE);
        hfEmpTask.setModifiedTime(LocalDateTime.now());
        hfEmpTask.setModifiedBy(UserContext.getUserId());
        hfEmpTask.setModifiedDisplayName(UserContext.getUser().getDisplayName());
        if (!business.updateById(hfEmpTask)) {
            return JsonResultKit.ofError("当前任务单数据更新失败");
        }
        return JsonResultKit.of();
    }

    /**
     * 雇员任务单下月处理
     *
     * @param empTaskId 当前任务单ID
     * @return JSON格式处理结果
     */
    @RequestMapping("/empTaskHandleDelay")
    public JsonResult empTaskHandleDelay(Long empTaskId) {
        HfEmpTask hfEmpTask = business.selectById(empTaskId);
        if (hfEmpTask == null || !hfEmpTask.getActive()) {
            return JsonResultKit.ofError("当前任务单已不存在");
        }
        ComAccountParamExtBo paramExtBo = new ComAccountParamExtBo();
        paramExtBo.setCompanyId(hfEmpTask.getCompanyId());
        List<ComAccountExtBo> hfComAccountList = hfComAccountService.queryHfComAccountList(paramExtBo);
        if (CollectionUtils.isNotEmpty(hfComAccountList)) {
            if (hfComAccountList.size() > 1) {
                return JsonResultKit.ofError("当前雇员任务单所属的企业账户数据有误");
            }

            Integer closeDay = hfComAccountList.get(0).getCloseDay();
            if (closeDay == null || closeDay <= 0 || closeDay >= 29) {
                return JsonResultKit.ofError("当前雇员任务单所属的企业账户的每月关账日设置有误");
            }

            Calendar calendar = Calendar.getInstance();
            int date = calendar.get(Calendar.DATE);
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), closeDay);
            if (date > closeDay) {
                calendar.add(Calendar.MONTH, 1);
            }
            calendar.add(Calendar.DATE, 1);

            hfEmpTask = new HfEmpTask();
            hfEmpTask.setEmpTaskId(empTaskId);
            hfEmpTask.setSubmitTime(LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE)));
            hfEmpTask.setModifiedTime(LocalDateTime.now());
            hfEmpTask.setModifiedBy(UserContext.getUserId());
            hfEmpTask.setModifiedDisplayName(UserContext.getUser().getDisplayName());
            if (!business.updateById(hfEmpTask)) {
                return JsonResultKit.ofError("当前任务单数据更新失败");
            }
        } else {
            return JsonResultKit.ofError("当前雇员任务单所属的企业账户不存在");
        }

        return JsonResultKit.of();
    }

    /**
     * 雇员任务单批退
     *
     * @param hfEmpTaskBatchRejectBo 前端提交参数
     * @return JSON格式处理结果
     */
    @RequestMapping("/empTaskHandleReject")
    public JsonResult empTaskHandleReject(@RequestBody HfEmpTaskBatchRejectBo hfEmpTaskBatchRejectBo) {
        try {
            hfEmpTaskBatchRejectBo.setModifiedBy(UserContext.getUserId());
            hfEmpTaskBatchRejectBo.setModifiedDisplayName(UserContext.getUser().getDisplayName());
            return business.handleReject(hfEmpTaskBatchRejectBo);
        } catch (BusinessException e) {
            return JsonResultKit.ofError(e.getMessage());
        }
    }

    /**
     * 雇员任务单撤销
     *
     * @param array 雇员任务单ID集合
     * @return JSON格式处理结果
     */
    @RequestMapping("/empTaskHandleCancel")
    public JsonResult empTaskHandleCancel(@RequestBody JSONArray array) {
        List<Long> empTaskIdList = array.toJavaList(Long.class);

        if (CollectionUtils.isNotEmpty(empTaskIdList)) {
            return business.handleCancel(empTaskIdList, UserContext.getUserId());
        } else {
            return JsonResultKit.ofError("提交的雇员任务单ID集合为空");
        }
    }

    /**
     * 查询转出单位（或转入单位）的企业公积金账户
     * 此接口有性能问题，不建议调用 MDF by Max Yu 2018-03-09
     * @param comAccountTransBo
     * @return
     */
    @Deprecated
    @RequestMapping("/comAccountQuery")
    public JsonResult comAccountQuery(ComAccountTransBo comAccountTransBo) {
        String key = "-HfEmpTaskHandleController-comAccountQuery-ComAccountTransBo-list-";
        List<ComAccountTransBo> rtnList = null;
        List<ComAccountTransBo> comAccountTransBoList = (List<ComAccountTransBo>) RedisManager.getObj(key);
        if(comAccountTransBo.getComAccountName()==null){
            comAccountTransBo.setComAccountName("");
        }
//        if (CollectionUtils.isNotEmpty(comAccountTransBoList)) {
//            rtnList = comAccountTransBoList.stream().filter(e ->
//                e.getComAccountName().contains( comAccountTransBo.getComAccountName())).limit(5).collect(Collectors.toList());
//        }

        if (CollectionUtils.isEmpty(rtnList)) {
            if("原单位".equals(comAccountTransBo.getComAccountName())){
                comAccountTransBoList = hfComAccountService.queryComAccountByCompanyIdTransBoList(comAccountTransBo);
                rtnList =comAccountTransBoList;
            }else {
                comAccountTransBoList = hfComAccountService.queryComAccountTransBoList(comAccountTransBo);
                comAccountTransBoList.stream().forEach(ComAccountTransBo->{
                        ComAccountTransBo.setHfType(null);
                        ComAccountTransBo.setComAccountId(null);
                        ComAccountTransBo.setCompanyId(null);
                        ComAccountTransBo.setComAccountClassId(null);
                        ComAccountTransBo.setWelfareUnit(null);
                    }
                );
                if (CollectionUtils.isNotEmpty(comAccountTransBoList)) {
                   // RedisManager.set(key, comAccountTransBoList, ExpireTime.TEN_MIN);
                    rtnList = comAccountTransBoList.stream().filter(e ->
                        e.getComAccountName().contains(comAccountTransBo.getComAccountName())).limit(5).collect(Collectors.toList());
                }
            }
        }

        return JsonResultKit.of(rtnList);
    }

    @RequestMapping("/transEmpTaskQuery")
    public JsonResult<HfEmpTask> transEmpTaskQuery(@RequestBody HfEmpTaskCreateTransBo hfEmpTaskCreateTransBo) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("company_id", hfEmpTaskCreateTransBo.getCompanyId());
        condition.put("employee_id", hfEmpTaskCreateTransBo.getEmployeeId());
        condition.put("task_category", HfEmpTaskConstant.TASK_CATEGORY_TRANSFER_TASK);
        condition.put("hf_type", hfEmpTaskCreateTransBo.getHfType());
        condition.put("is_active", 1);
        List<HfEmpTask> hfEmpTaskList = business.selectByMap(condition);
        if (CollectionUtils.isNotEmpty(hfEmpTaskList)) {
            if (hfEmpTaskList.size() > 1) {
                return JsonResultKit.ofError("该雇员的转移任务单数据有重复记录");
            }
            return JsonResultKit.of(hfEmpTaskList.get(0));
        }
        return JsonResultKit.of();
    }

    @RequestMapping("/createTransEmpTask")
    public JsonResult<HfEmpTask> createTransEmpTask(@RequestBody HfEmpTaskCreateTransBo hfEmpTaskCreateTransBo) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("company_id", hfEmpTaskCreateTransBo.getCompanyId());
        condition.put("employee_id", hfEmpTaskCreateTransBo.getEmployeeId());
        condition.put("task_category", HfEmpTaskConstant.TASK_CATEGORY_TRANSFER_TASK);
        condition.put("hf_type", hfEmpTaskCreateTransBo.getHfType());
        condition.put("is_active", 1);
        List<HfEmpTask> hfEmpTaskList = business.selectByMap(condition);
        if (CollectionUtils.isNotEmpty(hfEmpTaskList)) {
            if (hfEmpTaskList.size() > 1) {
                return JsonResultKit.ofError("该雇员的转移任务单数据有重复记录");
            }
            return JsonResultKit.of(hfEmpTaskList.get(0));
        } else {
            hfEmpTaskCreateTransBo.setTaskStatus(HfEmpTaskConstant.TASK_STATUS_COMPLETED);
            hfEmpTaskCreateTransBo.setModifiedBy(UserContext.getUserId());
            hfEmpTaskCreateTransBo.setModifiedDisplayName(UserContext.getUser().getDisplayName());
            hfEmpTaskCreateTransBo.setHandleUserId(UserContext.getUserId());
            hfEmpTaskCreateTransBo.setHandleUserName(UserContext.getUser().getDisplayName());
            int rtn = business.createTransEmpTask(hfEmpTaskCreateTransBo);
            if (rtn == 1) {
                hfEmpTaskList = business.selectByMap(condition);
                return JsonResultKit.of(hfEmpTaskList.get(0));
            }
        }

        return JsonResultKit.ofError("该雇员的转移任务单数据不存在");
    }

    @PostMapping("/queryHistoryEmpTaskList")
    public JsonResult<List<HfEmpTaskExportBo>> queryHistoryEmpTaskList(@RequestParam("empTaskId") String empTaskId) {
        Long taskId = Long.parseLong(empTaskId);
        List<HfEmpTaskExportBo> hfEmpTaskList = business.queryHistoryEmpTask(false, taskId);
        return JsonResultKit.of(hfEmpTaskList);
    }

    @PostMapping("/getOriginEmpTask")
    public JsonResult<HfEmpTaskExportBo> getOriginEmpTask(@RequestParam("empTaskId") String empTaskId) {
        Long taskId = Long.parseLong(empTaskId);
        HfEmpTaskExportBo hfEmpTaskExportBo = business.getOriginEmpTaskById(taskId);
        return JsonResultKit.of(hfEmpTaskExportBo);
    }
}
