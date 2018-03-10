package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
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
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.util.ExpireTime;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");

    /**
     * 雇员任务单办理信息查询
     *
     * @param hfEmpTaskHandlePostBo 前端提交参数
     * @return JSON格式处理结果
     */
    @RequestMapping("/empTaskHandleDataQuery")
    public JsonResult<HfEmpTaskHandleBo> empTaskHandleDataQuery(HfEmpTaskHandlePostBo hfEmpTaskHandlePostBo) {
        // 获取当前任务单主体信息
        List<HfEmpTaskHandleBo> list = business.getEmpTaskHandleData(hfEmpTaskHandlePostBo);
        if (CollectionUtils.isNotEmpty(list)) {
            if (list.size() > 1) {
                return JsonResultKit.ofError("当前雇员任务单相关数据有问题，请检查");
            }

            HfEmpTaskHandleBo hfEmpTaskHandleBo = list.get(0);
            if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_TRANS_TASK || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_SPEC_TASK) {
                return JsonResultKit.ofError("当前雇员任务单的任务类型不正确");
            }
            if ((hfEmpTaskHandleBo.getTaskStatus() != null && !hfEmpTaskHandleBo.getTaskStatus().equals(hfEmpTaskHandlePostBo.getTaskStatus()))
                || (hfEmpTaskHandleBo.getTaskStatus() == null && hfEmpTaskHandlePostBo.getTaskStatus() != HfEmpTaskConstant.TASK_STATUS_UNHANDLED)) {
                return JsonResultKit.ofError("当前雇员任务单状态已变更，请到其他状态页签中查找");
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
                Wrapper<HfEmpArchive> wrapper = new EntityWrapper<>();
                wrapper.where(" is_active = 1 AND employee_id={0} AND hf_type={1}", hfEmpTaskHandleBo.getEmployeeId(), hfEmpTaskHandleBo.getHfType());
                wrapper.orderBy("created_time", false);
                List<HfEmpArchive> hfEmpArchiveList = hfEmpArchiveService.selectList(wrapper);
                if (CollectionUtils.isNotEmpty(hfEmpArchiveList)) {
                    hfEmpTaskHandleBo.setHfEmpAccount(hfEmpArchiveList.get(0).getHfEmpAccount());
                }
            }

            // 根据雇员档案ID获取雇员基本公积金汇缴月份段信息
            Map<String, Object> condition = new HashMap<>();
            if (hfEmpTaskHandleBo.getBasicEmpArchiveId() != null) {
                condition.put("emp_archive_id", hfEmpTaskHandleBo.getBasicEmpArchiveId());
                condition.put("hf_type", 1);
                hfEmpTaskHandleBo.setBasicArchiveBasePeriods(hfArchiveBasePeriodService.selectByMap(condition));
            }

            // 根据雇员档案ID获取雇员补充公积金汇缴月份段信息
            if (hfEmpTaskHandleBo.getAddedEmpArchiveId() != null) {
                condition.put("emp_archive_id", hfEmpTaskHandleBo.getAddedEmpArchiveId());
                condition.put("hf_type", 2);
                hfEmpTaskHandleBo.setAddedArchiveBasePeriods(hfArchiveBasePeriodService.selectByMap(condition));
            }

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
                    || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_IN_MULTI_TRANS_IN
                    || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_ADJUST) {
                    if (StringUtils.isNotEmpty(hfMonth) && StringUtils.isNotEmpty(startMonth)) {
                        YearMonth hfMonthDate = YearMonth.parse(hfMonth, formatter);
                        YearMonth startMonthDate = YearMonth.parse(startMonth, formatter);
                        HfEmpTaskPeriod hfEmpTaskPeriod = new HfEmpTaskPeriod();

                        // 如果任务单起缴月份小于客户汇缴月，则小于的月份自动分成一个补缴的费用段
                        if (startMonthDate.isBefore(hfMonthDate)) {
                            YearMonth endMonthDate = hfMonthDate.minusMonths(1);
                            // 正常汇缴费用段
                            hfEmpTaskPeriod.setEmpTaskId(hfEmpTaskHandleBo.getEmpTaskId());
                            if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_ADJUST) {
                                hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_ADJUST);
                            } else {
                                hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_NORMAL);
                            }
                            hfEmpTaskPeriod.setStartMonth(hfMonth);
                            hfEmpTaskPeriod.setHfMonth(hfMonth);
                            hfEmpTaskPeriod.setBaseAmount(hfEmpTaskHandleBo.getEmpBase());
                            hfEmpTaskPeriod.setRatioCom(hfEmpTaskHandleBo.getRatioCom());
                            hfEmpTaskPeriod.setRatioEmp(hfEmpTaskHandleBo.getRatioEmp());
                            hfEmpTaskPeriod.setAmount(hfEmpTaskHandleBo.getAmount());
                            hfEmpTaskPeriods.add(hfEmpTaskPeriod);
                            // 补缴费用段
                            hfEmpTaskPeriod = new HfEmpTaskPeriod();
                            hfEmpTaskPeriod.setEmpTaskId(hfEmpTaskHandleBo.getEmpTaskId());
                            hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_REPAIR);
                            hfEmpTaskPeriod.setStartMonth(startMonth);
                            hfEmpTaskPeriod.setEndMonth(endMonthDate.format(formatter));
                            if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_ADJUST) {
                                hfEmpTaskPeriod.setHfMonth(hfMonth);
                            } else {
                                // 如果账户分类是独立户则客户汇缴月=企业末次汇缴月 + 1
                                if (hfEmpTaskHandleBo.getHfAccountType() == HfComAccountConstant.HF_ACCOUNT_TYPE_INDEPENDENT) {
                                    hfEmpTaskPeriod.setHfMonth(hfMonthDate.plusMonths(1).format(formatter));
                                } else {
                                    // 如果账户分类非独立户则客户汇缴月=企业末次汇缴月
                                    hfEmpTaskPeriod.setHfMonth(hfMonth);
                                }
                            }
                            hfEmpTaskPeriod.setBaseAmount(hfEmpTaskHandleBo.getEmpBase());
                            hfEmpTaskPeriod.setRatioCom(hfEmpTaskHandleBo.getRatioCom());
                            hfEmpTaskPeriod.setRatioEmp(hfEmpTaskHandleBo.getRatioEmp());
                            hfEmpTaskPeriod.setAmount(hfEmpTaskHandleBo.getAmount());
                            hfEmpTaskPeriods.add(hfEmpTaskPeriod);
                        } else {
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
                            hfEmpTaskPeriod.setRatioCom(hfEmpTaskHandleBo.getRatioCom());
                            hfEmpTaskPeriod.setRatioEmp(hfEmpTaskHandleBo.getRatioEmp());
                            hfEmpTaskPeriod.setAmount(hfEmpTaskHandleBo.getAmount());
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
                    hfEmpTaskPeriod.setRatioCom(hfEmpTaskHandleBo.getRatioCom());
                    hfEmpTaskPeriod.setRatioEmp(hfEmpTaskHandleBo.getRatioEmp());
                    hfEmpTaskPeriod.setAmount(hfEmpTaskHandleBo.getAmount());
                    hfEmpTaskPeriods.add(hfEmpTaskPeriod);
                }
            } else if (
                hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE
                || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT
                || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_OUT_MULTI_TRANS_OUT
                ) {
                if (hfEmpTaskPeriods.size() > 1) {
                    return JsonResultKit.ofError("当前雇员任务单费用段数据不正确");
                }
                hfEmpTaskHandleBo.setEndMonth(hfEmpTaskPeriods.get(0).getEndMonth());
            }
            hfEmpTaskHandleBo.setEmpTaskPeriods(hfEmpTaskPeriods);

            // 查询当前雇员除该任务单之外的所有任务单信息
            EntityWrapper<HfEmpTask> wrapper = new EntityWrapper<>();
            wrapper.where("company_id={0} AND employee_id={1} AND emp_task_id != {2} AND is_active = 1",
                hfEmpTaskHandleBo.getCompanyId(), hfEmpTaskHandleBo.getEmployeeId(), hfEmpTaskHandleBo.getEmpTaskId());
            wrapper.orderBy("emp_task_id", false);
            List<HfEmpTask> hfEmpTasks = business.selectList(wrapper);
            if (CollectionUtils.isNotEmpty(hfEmpTasks)) {
                List<HfEmpTaskRemarkBo> hfEmpTaskRemarkBos = new ArrayList<>();
                hfEmpTasks.stream().forEach((e) -> {
                    HfEmpTaskRemarkBo bo = new HfEmpTaskRemarkBo();
                    bo.setEmpTaskId(e.getEmpTaskId());
                    bo.setHfType(e.getHfType());
                    bo.setTaskCategory(e.getTaskCategory());
                    bo.setTaskStatus(e.getTaskStatus());
                    bo.setModifiedBy(e.getModifiedBy());
                    bo.setModifiedTime(e.getModifiedTime());
                    bo.setHandleRemark(e.getHandleRemark());
                    bo.setRejectionRemark(e.getRejectionRemark());
                    hfEmpTaskRemarkBos.add(bo);
                });
                hfEmpTaskHandleBo.setEmpTaskRemarks(hfEmpTaskRemarkBos);
            }

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
        hfEmpTask.setModifiedBy("test"); // TODO current user
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
            hfEmpTask.setModifiedBy("test"); // TODO current user
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
            return business.handleCancel(empTaskIdList, "test"); // TODO currentUser
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
        if (CollectionUtils.isNotEmpty(comAccountTransBoList)) {
            rtnList = comAccountTransBoList.stream().filter(e ->
                e.getComAccountName().contains(comAccountTransBo.getComAccountName())).limit(5).collect(Collectors.toList());
        }

        if (CollectionUtils.isEmpty(rtnList)) {
            comAccountTransBoList = hfComAccountService.queryComAccountTransBoList(comAccountTransBo);

            if (CollectionUtils.isNotEmpty(comAccountTransBoList)) {
                RedisManager.set(key, comAccountTransBoList, ExpireTime.TEN_MIN);
                rtnList = comAccountTransBoList.stream().filter(e ->
                    e.getComAccountName().contains(comAccountTransBo.getComAccountName())).limit(5).collect(Collectors.toList());
            }
        }

        return JsonResultKit.of(rtnList);
    }

    @RequestMapping("/transEmpTaskQuery")
    public JsonResult<HfEmpTask> transEmpTaskQuery(@RequestBody HfEmpTaskCreateTransBo hfEmpTaskCreateTransBo) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("company_id", hfEmpTaskCreateTransBo.getCompanyId());
        condition.put("employee_id", hfEmpTaskCreateTransBo.getEmployeeId());
        condition.put("task_category", 9);
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
        condition.put("task_category", 9);
        condition.put("hf_type", hfEmpTaskCreateTransBo.getHfType());
        condition.put("is_active", 1);
        List<HfEmpTask> hfEmpTaskList = business.selectByMap(condition);
        if (CollectionUtils.isNotEmpty(hfEmpTaskList)) {
            if (hfEmpTaskList.size() > 1) {
                return JsonResultKit.ofError("该雇员的转移任务单数据有重复记录");
            }
            return JsonResultKit.of(hfEmpTaskList.get(0));
        } else {
            hfEmpTaskCreateTransBo.setCreatedBy("test"); // TODO currentUser
            int rtn = business.createTransEmpTask(hfEmpTaskCreateTransBo);
            if (rtn == 1) {
                hfEmpTaskList = business.selectByMap(condition);
                return JsonResultKit.of(hfEmpTaskList.get(0));
            }
        }

        return JsonResultKit.ofError("该雇员的转移任务单数据不存在");
    }
}
