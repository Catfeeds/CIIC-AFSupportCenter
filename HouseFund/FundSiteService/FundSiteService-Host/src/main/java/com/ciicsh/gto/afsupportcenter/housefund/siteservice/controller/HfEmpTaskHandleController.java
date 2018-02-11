package com.ciicsh.gto.afsupportcenter.housefund.siteservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskHandleBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskRemarkBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfArchiveBasePeriodService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfEmpTaskPeriodService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfEmpTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto.HfEmpTaskHandleDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfArchiveBasePeriod;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpArchive;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpTaskPeriod;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fundcommandservice/hfEmpTaskHandle")
public class HfEmpTaskHandleController extends BasicController<HfEmpTaskService> {
    @Autowired
    private HfEmpArchiveService hfEmpArchiveService;
    @Autowired
    private HfArchiveBasePeriodService hfArchiveBasePeriodService;
    @Autowired
    private HfEmpTaskPeriodService hfEmpTaskPeriodService;

    @RequestMapping("/empTaskHandleDataQuery")
    public JsonResult<HfEmpTaskHandleBo> empTaskHandleDataQuery(HfEmpTaskHandleDTO hfEmpTaskHandleDTO) {
        List<HfEmpTaskHandleBo> list = business.getEmpTaskHandleData(hfEmpTaskHandleDTO);
        if (CollectionUtils.isNotEmpty(list)) {
            if (list.size() > 1) {
                return JsonResultKit.ofError("当前雇员任务单相关数据有问题，请检查");
            }

            HfEmpTaskHandleBo hfEmpTaskHandleBo = list.get(0);
            if (hfEmpTaskHandleBo.getTaskCategory() == 9 || hfEmpTaskHandleBo.getTaskCategory() == 10) { // TODO Constants
                return JsonResultKit.ofError("当前雇员任务单的任务类型不正确");
            }
            if ((hfEmpTaskHandleBo.getTaskStatus() != null && !hfEmpTaskHandleBo.getTaskStatus().equals(hfEmpTaskHandleDTO.getTaskStatus()))
                || (hfEmpTaskHandleBo.getTaskStatus() == null && hfEmpTaskHandleDTO.getTaskStatus() != 1)) { // TODO Constants
                return JsonResultKit.ofError("当前雇员任务单状态已变更，请到其他状态页签中查找");
            }
            Map<String, Object> condition = new HashMap<>();
            condition.put("emp_archive_id", hfEmpTaskHandleBo.getBasicEmpArchiveId());
            condition.put("hf_type", 1);
            hfEmpTaskHandleBo.setBasicArchiveBasePeriods(hfArchiveBasePeriodService.selectByMap(condition));

            condition.put("emp_archive_id", hfEmpTaskHandleBo.getAddedEmpArchiveId());
            condition.put("hf_type", 2);
            hfEmpTaskHandleBo.setAddedArchiveBasePeriods(hfArchiveBasePeriodService.selectByMap(condition));

            condition.clear();
            condition.put("emp_task_id", hfEmpTaskHandleBo.getEmpTaskId());
            condition.put("is_active", 1);
            List<HfEmpTaskPeriod> hfEmpTaskPeriods = hfEmpTaskPeriodService.selectByMap(condition);
            if (CollectionUtils.isEmpty(hfEmpTaskPeriods)) {
                HfEmpTaskPeriod hfEmpTaskPeriod = new HfEmpTaskPeriod();
                hfEmpTaskPeriod.setEmpTaskId(hfEmpTaskHandleBo.getEmpTaskId());
                if (hfEmpTaskHandleBo.getTaskCategory() == 4 || hfEmpTaskHandleBo.getTaskCategory() == 5) { // TODO Constants
                    hfEmpTaskPeriod.setRemitWay(3); // TODO
                } else if (hfEmpTaskHandleBo.getTaskCategory() == 6) { // TODO
                    hfEmpTaskPeriod.setRemitWay(2);
                } else {
                    hfEmpTaskPeriod.setRemitWay(1);
                }

                hfEmpTaskPeriod.setStartMonth(hfEmpTaskHandleBo.getStartMonth());
                hfEmpTaskPeriod.setEndMonth(hfEmpTaskHandleBo.getEndMonth());
                if (hfEmpTaskHandleDTO.getHfType() == 1) {
                    hfEmpTaskPeriod.setHfMonth(String.valueOf(hfEmpTaskHandleBo.getBasicComHfMonth()));
                } else {
                    hfEmpTaskPeriod.setHfMonth(String.valueOf(hfEmpTaskHandleBo.getAddedComHfMonth()));
                }
                hfEmpTaskPeriod.setBaseAmount(hfEmpTaskHandleBo.getEmpBase());
                hfEmpTaskPeriod.setRatioCom(hfEmpTaskHandleBo.getRatioCom());
                hfEmpTaskPeriod.setRatioEmp(hfEmpTaskHandleBo.getRatioEmp());
                hfEmpTaskPeriod.setAmount(hfEmpTaskHandleBo.getAmount());
                hfEmpTaskPeriods.add(hfEmpTaskPeriod);
            }
            hfEmpTaskHandleBo.setEmpTaskPeriods(hfEmpTaskPeriods);

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

    @Transactional
    @RequestMapping("/empTaskHandleDataSave")
    public JsonResult empTaskHandleDataSave(@RequestBody JSONObject params) {
        return inputDataSave(params, false);
    }

    @Transactional
    @RequestMapping("/empTaskHandle")
    public JsonResult empTaskHandle(@RequestBody JSONObject params) {
        JsonResult result = inputDataSave(params, true);
        if (result.getCode() == 200) {
            // Call Complete Task interface
        }
        return  JsonResultKit.of();
    }

    private JsonResult inputDataSave(JSONObject params, boolean isHandle) {
        Long empTaskId = params.getLong("empTaskId");
        Integer taskStatus = params.getInteger("taskStatus");
        HfEmpTask hfEmpTask = business.selectById(empTaskId);
        if (hfEmpTask == null || !hfEmpTask.getActive()) {
            return JsonResultKit.ofError("当前任务单已不存在");
        }
        if ((hfEmpTask.getTaskStatus() != null && !hfEmpTask.getTaskStatus().equals(taskStatus))
            || (hfEmpTask.getTaskStatus() == null && taskStatus != 1)) { // TODO Constants
            return JsonResultKit.ofError("当前雇员任务单状态已变更，请到其他状态页签中查找");
        }

        HfEmpTask inputHfEmpTask = new HfEmpTask();
        if (isHandle) {
            JsonResult result = handleEmpTask(hfEmpTask, inputHfEmpTask);
            if (result.getCode() != 200) {
                return result;
            }
        }
        inputHfEmpTask.setEmpTaskId(empTaskId);
        inputHfEmpTask.setHfType(hfEmpTask.getHfType());
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

        if (!business.updateById(inputHfEmpTask)) {
            return JsonResultKit.ofError("当前雇员任务单数据更新失败");
        }

        if (isHandle) {
            JsonResult result = handleEmpArchive(params, inputHfEmpTask);
            if (result.getCode() != 200) {
                return result;
            }
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
                if (!hfEmpTaskPeriodService.insertOrUpdateBatch(hfEmpTaskPeriodList)) {
                    return JsonResultKit.ofError("当前雇员任务单费用段数据更新失败");
                }

                if (isHandle) {
                    JsonResult result = handleEmpBasePeriod(inputHfEmpTask, hfEmpTaskPeriodList);
                    if (result.getCode() != 200) {
                        return result;
                    }
                }
            }
        }
        return JsonResultKit.of();
    }

    /**
     * 雇员任务表数据处理
     *
     * @param origEmpTask 既存的雇员任务数据
     * @param inputHfEmpTask 画面输入的雇员任务数据
     * @return
     */
    private JsonResult handleEmpTask(HfEmpTask origEmpTask, HfEmpTask inputHfEmpTask) {
        System.out.println("origEmpTask.getEmpArchiveId() = " + origEmpTask.getEmpArchiveId()); // TODO log
        inputHfEmpTask.setEmpArchiveId(origEmpTask.getEmpArchiveId());
        inputHfEmpTask.setTaskStatus(2); // TODO Constants
        inputHfEmpTask.setHandleStatus(2); // TODO 1.收缴材料 skip？
        inputHfEmpTask.setHandleDate(LocalDate.now().format(DateTimeFormatter.ofPattern("uuuuMM")));
        inputHfEmpTask.setHandleUserId("test"); // TODO
        inputHfEmpTask.setHandleUserName("test"); // TODO
        return JsonResultKit.of();
    }

    /**
     * 雇员档案费用分段表数据处理
     *
     * @param inputHfEmpTask 已保存的任务表数据
     * @param hfEmpTaskPeriodList 已保存的任务费用分段表数据
     * @return
     */
    private JsonResult handleEmpBasePeriod(HfEmpTask inputHfEmpTask, List<HfEmpTaskPeriod> hfEmpTaskPeriodList) {
        List<HfArchiveBasePeriod> hfArchiveBasePeriodList = new ArrayList<>();
        hfEmpTaskPeriodList.stream().forEach(e -> {
            HfArchiveBasePeriod hfArchiveBasePeriod = new HfArchiveBasePeriod();
            System.out.println("e.getArchiveBasePeriodId() = " + e.getArchiveBasePeriodId()); // TODO log
            hfArchiveBasePeriod.setArchiveBasePeriodId(e.getArchiveBasePeriodId());
            hfArchiveBasePeriod.setEmpTaskId(e.getEmpTaskId());
            hfArchiveBasePeriod.setAmount(e.getAmount());
            hfArchiveBasePeriod.setBaseAmount(e.getBaseAmount());
            hfArchiveBasePeriod.setRatioCom(e.getRatioCom());
            hfArchiveBasePeriod.setRatioEmp(e.getRatioEmp());
            BigDecimal ratioCom = BigDecimal.ZERO;
            BigDecimal ratioEmp = BigDecimal.ZERO;
            BigDecimal amount = BigDecimal.ZERO;
            if (e.getRatioCom() != null) {
                ratioCom = e.getRatioCom();
            }
            if (e.getRatioEmp() != null) {
                ratioEmp = e.getRatioEmp();
            }
            if (e.getAmount() != null) {
                amount = e.getAmount();
            }
            hfArchiveBasePeriod.setRatio(ratioCom.add(ratioEmp).setScale(2, BigDecimal.ROUND_HALF_UP));
            if (hfArchiveBasePeriod.getRatio().compareTo(BigDecimal.ZERO) > 0) {
                hfArchiveBasePeriod.setAmountEmp(amount.multiply(ratioEmp.divide(hfArchiveBasePeriod.getRatio())).setScale(2, BigDecimal.ROUND_HALF_UP));
                hfArchiveBasePeriod.setComAmount(amount.multiply(ratioCom.divide(hfArchiveBasePeriod.getRatio())).setScale(2, BigDecimal.ROUND_HALF_UP));
            } else {
                hfArchiveBasePeriod.setAmountEmp(BigDecimal.ZERO);
                hfArchiveBasePeriod.setComAmount(BigDecimal.ZERO);
            }

            hfArchiveBasePeriod.setStartMonth(e.getStartMonth());
            hfArchiveBasePeriod.setEndMonth(e.getEndMonth());
            hfArchiveBasePeriod.setHfMonth(e.getHfMonth());
            hfArchiveBasePeriod.setRepairReason(e.getRepairReason());
            hfArchiveBasePeriod.setModifiedBy("test"); // TODO

            if (hfArchiveBasePeriod.getArchiveBasePeriodId() == null) {
                hfArchiveBasePeriod.setCreatedBy("test"); // TODO
                hfArchiveBasePeriod.setEmpArchiveId(inputHfEmpTask.getEmpArchiveId());
                hfArchiveBasePeriod.setRemitWay(e.getRemitWay());
                hfArchiveBasePeriod.setHfType(inputHfEmpTask.getHfType());
            } else {
                hfArchiveBasePeriod.setModifiedTime(LocalDateTime.now());
            }
            hfArchiveBasePeriodList.add(hfArchiveBasePeriod);
        });
        if (!hfArchiveBasePeriodService.insertOrUpdateBatch(hfArchiveBasePeriodList)) {
            return JsonResultKit.ofError("当前雇员汇缴月份段数据更新失败");
        }
        return JsonResultKit.of();
    }

    /**
     * 雇员档案表数据处理
     *
     * @param params 画面传入参数
     * @param inputHfEmpTask 已保存的任务单表数据
     * @return
     */
    private JsonResult handleEmpArchive(JSONObject params, HfEmpTask inputHfEmpTask) {
        HfEmpArchive hfEmpArchive = new HfEmpArchive();
        System.out.println("inputHfEmpTask.getEmpArchiveId() = " + inputHfEmpTask.getEmpArchiveId());
        hfEmpArchive.setEmpArchiveId(inputHfEmpTask.getEmpArchiveId());
        hfEmpArchive.setStartMonth(inputHfEmpTask.getStartMonth());
//        hfEmpArchive.setEndMonth(inputHfEmpTask.getEndMonth());
        hfEmpArchive.setOperationRemind(inputHfEmpTask.getOperationRemind());
        hfEmpArchive.setOperationRemindDate(inputHfEmpTask.getOperationRemindDate());
        setEmpArchiveStatus(hfEmpArchive, inputHfEmpTask.getTaskCategory());

        hfEmpArchive.setModifiedBy("test"); // TODO
        if (hfEmpArchive.getEmpArchiveId() == null) {
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
        if (!hfEmpArchiveService.insertOrUpdate(hfEmpArchive)) {
            return JsonResultKit.ofError("当前雇员档案数据更新失败");
        }
        inputHfEmpTask.setEmpArchiveId(hfEmpArchive.getEmpArchiveId());
        return JsonResultKit.of();
    }

    /**
     * 根据任务单类型及雇员档案当前原始状态来设置雇员档案中的任务单状态及原始状态
     *
     * @param hfEmpArchive
     * @param taskCategory
     */
    private void setEmpArchiveStatus(HfEmpArchive hfEmpArchive, Integer taskCategory) {
        Integer origStatus = hfEmpArchive.getArchiveStatus();

        // TODO Constants
        switch (taskCategory) {
            case 1:
            case 2:
            case 3:
            case 11:
                hfEmpArchive.setArchiveTaskStatus(1);
                hfEmpArchive.setArchiveStatus(1);
                break;
            case 4:
                hfEmpArchive.setArchiveTaskStatus(3);
                hfEmpArchive.setArchiveStatus(3);
                break;
            case 5:
                hfEmpArchive.setArchiveTaskStatus(1);
                hfEmpArchive.setArchiveStatus(1);
                break;
            case 6:
                if (origStatus == null || origStatus == 3) {
                    hfEmpArchive.setArchiveTaskStatus(3);
                    hfEmpArchive.setArchiveStatus(3);
                } else if (origStatus == 1 || origStatus == 2) {
                    hfEmpArchive.setArchiveTaskStatus(1);
                    hfEmpArchive.setArchiveStatus(1);
                }
                break;
            case 7:
            case 8:
            case 12:
                hfEmpArchive.setArchiveTaskStatus(3);
                hfEmpArchive.setArchiveStatus(3);
                break;
            default:
                break;
        }
    }
}
