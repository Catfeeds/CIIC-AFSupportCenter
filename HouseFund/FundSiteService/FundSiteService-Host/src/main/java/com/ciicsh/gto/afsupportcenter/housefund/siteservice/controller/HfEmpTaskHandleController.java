package com.ciicsh.gto.afsupportcenter.housefund.siteservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskHandleBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskRemarkBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfArchiveBasePeriodService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfEmpTaskPeriodService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfEmpTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto.HfEmpTaskHandleDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpTaskPeriod;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fundcommandservice/hfEmpTaskHandle")
public class HfEmpTaskHandleController extends BasicController<HfEmpTaskService> {

    @Autowired
    private HfArchiveBasePeriodService hfArchiveBasePeriodService;
    @Autowired
    private HfEmpTaskPeriodService hfEmpTaskPeriodService;
    @Autowired
    private HfEmpTaskService hfEmpTaskService;

    @RequestMapping("/empTaskHandleDataQuery")
    public JsonResult<HfEmpTaskHandleBo> empTaskHandleDataQuery(HfEmpTaskHandleDTO hfEmpTaskHandleDTO) {
        List<HfEmpTaskHandleBo> list = business.getEmpTaskHandleData(hfEmpTaskHandleDTO);
        if (CollectionUtils.isNotEmpty(list)) {
            if (list.size() > 1) {
                return JsonResultKit.ofError("当前雇员任务单相关数据有问题，请检查");
            }

            HfEmpTaskHandleBo hfEmpTaskHandleBo = list.get(0);
            if (hfEmpTaskHandleBo.getTaskCategory() == 9) {
                return JsonResultKit.ofError("当前雇员任务单的任务类型不正确");
            }
            if (hfEmpTaskHandleBo.getTaskStatus() != null && hfEmpTaskHandleBo.getTaskStatus() != 1) { // TODO Constants
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
            wrapper.where("company_id={0} AND employee_id={1} AND emp_task_id != {2} AND is_active = 1", hfEmpTaskHandleBo.getCompanyId(), hfEmpTaskHandleBo.getEmployeeId(), hfEmpTaskHandleBo.getEmpTaskId());
            wrapper.orderBy("emp_task_id", false);
            List<HfEmpTask> hfEmpTasks =  hfEmpTaskService.selectList(wrapper);
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

    @RequestMapping("/empTaskHandleDataSave")
    public JsonResult empTaskHandleDataSave(@RequestBody JSONObject params) {
        Long empTaskId = params.getLong("empTaskId");
        Integer taskCategory = params.getInteger("taskCategory");
        String hfEmpAccount = params.getString("hfEmpAccount");
        String startMonth = params.getString("startMonth");
        Integer operationRemind = params.getInteger("operationRemind");
        String operationRemindDateStr = params.getString("operationRemindDate");
        if (StringUtils.isNotBlank(operationRemindDateStr)) {
            LocalDate operationRemindDate = LocalDate.parse(operationRemindDateStr);
        }
        String handleRemark = params.getString("handleRemark");
        String rejectionRemark = params.getString("rejectionRemark");
        JSONArray operatorListData = params.getJSONArray("operatorListData");

        return JsonResultKit.of();
    }
}
