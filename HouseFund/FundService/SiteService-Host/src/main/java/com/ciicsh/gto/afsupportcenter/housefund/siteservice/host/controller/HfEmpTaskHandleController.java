package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.TaskSheetRequestDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskBatchRejectBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskHandleBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskRemarkBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskPeriodConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskHandlePostBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    private CommonApiUtils commonApiUtils;

    /**
     * 雇员任务单办理信息查询
     *
     * @param hfEmpTaskHandleDTO 前端提交参数
     * @return JSON格式处理结果
     */
    @RequestMapping("/empTaskHandleDataQuery")
    public JsonResult<HfEmpTaskHandleBo> empTaskHandleDataQuery(HfEmpTaskHandlePostBo hfEmpTaskHandleDTO) {
        // 获取当前任务单主体信息
        List<HfEmpTaskHandleBo> list = business.getEmpTaskHandleData(hfEmpTaskHandleDTO);
        if (CollectionUtils.isNotEmpty(list)) {
            if (list.size() > 1) {
                return JsonResultKit.ofError("当前雇员任务单相关数据有问题，请检查");
            }

            HfEmpTaskHandleBo hfEmpTaskHandleBo = list.get(0);
            if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_TRANS_TASK || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_SPEC_TASK) {
                return JsonResultKit.ofError("当前雇员任务单的任务类型不正确");
            }
            if ((hfEmpTaskHandleBo.getTaskStatus() != null && !hfEmpTaskHandleBo.getTaskStatus().equals(hfEmpTaskHandleDTO.getTaskStatus()))
                || (hfEmpTaskHandleBo.getTaskStatus() == null && hfEmpTaskHandleDTO.getTaskStatus() != HfEmpTaskConstant.TASK_STATUS_UNHANDLED)) {
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
                HfEmpTaskPeriod hfEmpTaskPeriod = new HfEmpTaskPeriod();
                hfEmpTaskPeriod.setEmpTaskId(hfEmpTaskHandleBo.getEmpTaskId());
                if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_ADJUST_CLOSE || hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_ADJUST_OPEN) {
                    hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_ADJUST);
                } else if (hfEmpTaskHandleBo.getTaskCategory() == HfEmpTaskConstant.TASK_CATEGORY_REPAIR) {
                    hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_REPAIR);
                } else {
                    hfEmpTaskPeriod.setRemitWay(HfEmpTaskPeriodConstant.REMIT_WAY_NORMAL);
                }

                hfEmpTaskPeriod.setStartMonth(hfEmpTaskHandleBo.getStartMonth());
                hfEmpTaskPeriod.setEndMonth(hfEmpTaskHandleBo.getEndMonth());
                if (hfEmpTaskHandleDTO.getHfType() == 1) {
                    if (hfEmpTaskHandleBo.getBasicComHfMonth() != null) {
                        hfEmpTaskPeriod.setHfMonth(String.valueOf(hfEmpTaskHandleBo.getBasicComHfMonth()));
                    }
                } else {
                    if (hfEmpTaskHandleBo.getAddedComHfMonth() != null) {
                        hfEmpTaskPeriod.setHfMonth(String.valueOf(hfEmpTaskHandleBo.getAddedComHfMonth()));
                    }
                }
                hfEmpTaskPeriod.setBaseAmount(hfEmpTaskHandleBo.getEmpBase());
                hfEmpTaskPeriod.setRatioCom(hfEmpTaskHandleBo.getRatioCom());
                hfEmpTaskPeriod.setRatioEmp(hfEmpTaskHandleBo.getRatioEmp());
                hfEmpTaskPeriod.setAmount(hfEmpTaskHandleBo.getAmount());
                hfEmpTaskPeriods.add(hfEmpTaskPeriod);
            }
            hfEmpTaskHandleBo.setEmpTaskPeriods(hfEmpTaskPeriods);

            // 查询当前雇员除该任务单之外的所有任务单信息
            EntityWrapper<HfEmpTask> wrapper = new EntityWrapper<>();
            wrapper.where("company_id={0} AND employee_id={1} AND emp_task_id != {2} AND is_active = 1",
                hfEmpTaskHandleBo.getCompanyId(), hfEmpTaskHandleBo.getEmployeeId(), hfEmpTaskHandleBo.getEmpTaskId());
            wrapper.orderBy("emp_task_id", false);
            List<HfEmpTask> hfEmpTasks =  business.selectList(wrapper);
            if (CollectionUtils.isNotEmpty(hfEmpTasks)) {
                List<HfEmpTaskRemarkBo> hfEmpTaskRemarkBos = new ArrayList<>();
                hfEmpTasks.stream().forEach((e) -> {
                    HfEmpTaskRemarkBo bo = new HfEmpTaskRemarkBo();
                    bo.setEmpTaskId(e.getEmpTaskId());
                    bo.setHfType(e.getHfType());
                    bo.setTaskCategory(e.getTaskCategory());
                    bo.setSubmitterId(e.getSubmitterId());
                    bo.setSubmitTime(e.getSubmitTime());
                    bo.setSubmitterRemark(e.getSubmitterRemark());
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
            JsonResult result = business.inputDataSave(params, true);
            if (result.getCode() == 200) {
                if (result.getData() != null) {
                    String taskId = result.getData().toString();
                    System.out.println("empTaskHandle.taskId = " + taskId); // TODO log
                    TaskSheetRequestDTO taskSheetRequestDTO = new TaskSheetRequestDTO();
                    taskSheetRequestDTO.setTaskId(taskId);
                    taskSheetRequestDTO.setAssignee("test"); // TODO currentUser
                    commonApiUtils.completeTask(taskSheetRequestDTO);
                }
            } else {
                return result;
            }
        } catch (BusinessException e) {
            return JsonResultKit.ofError(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // TODO log
            return JsonResultKit.ofError("雇员任务单办理出现异常");
        }
        return  JsonResultKit.of();
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
        List<ComAccountExtBo> hfComAccountList = hfComAccountService.getHfComAccountList(paramExtBo);
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
        Long empTaskId = hfEmpTaskBatchRejectBo.getSelectedData()[0];
        HfEmpTask hfEmpTask = business.selectById(empTaskId);
        if (hfEmpTask == null || !hfEmpTask.getActive()) {
            return JsonResultKit.ofError("当前任务单已不存在");
        }

        hfEmpTask = new HfEmpTask();
        hfEmpTask.setEmpTaskId(empTaskId);
        hfEmpTask.setTaskStatus(HfEmpTaskConstant.TASK_STATUS_REJECTED);
        hfEmpTask.setRejectionRemark(hfEmpTaskBatchRejectBo.getRejectionRemark());
        hfEmpTask.setModifiedTime(LocalDateTime.now());
        hfEmpTask.setModifiedBy("test"); // TODO current user

        if (!business.updateById(hfEmpTask)) {
            return JsonResultKit.ofError("当前任务单数据更新失败");
        }
        return JsonResultKit.of();
    }

}
