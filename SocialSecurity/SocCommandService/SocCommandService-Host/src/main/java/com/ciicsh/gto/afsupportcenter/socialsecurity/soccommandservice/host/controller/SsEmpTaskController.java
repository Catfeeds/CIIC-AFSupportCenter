package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpBaseDetailService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpBasePeriodService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpTaskPeriodService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpBaseDetail;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpBasePeriod;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTaskPeriod;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 本地社保的雇员任务单 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssEmpTask")
@Log("本地社保的雇员任务单")
public class SsEmpTaskController extends BasicController<ISsEmpTaskService> {

    @Autowired
    private ISsEmpTaskPeriodService ssEmpTaskPeriodService;
    @Autowired
    private ISsEmpBasePeriodService ssEmpBasePeriodService;
    @Autowired
    private ISsEmpBaseDetailService ssEmpBaseDetailService;

    /**
     * 雇员日常操作查询
     */
    @Log("雇员日常操作查询")
    @PostMapping("/employeeOperatorQuery")
    public JsonResult<List<SsEmpTaskBO>> employeeOperatorQuery(PageInfo pageInfo) {
        PageRows<SsEmpTaskBO> pageRows = business.employeeOperatorQuery(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    /**
     * 雇员任务批退
     */
    @Log("雇员任务批退")
    @PostMapping("/rejection")
    public JsonResult<Boolean> rejection(RejectionParam param) {
        List<Long> ids = Optional.ofNullable(param.getIds()).orElse(Collections.emptyList());
        int length = ids.size();
        String remark = param.getRemark();
        List<SsEmpTask> list = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            SsEmpTask task = new SsEmpTask();
            task.setEmpTaskId(ids.get(i));
            task.setRejectionRemark(remark);
            task.setTaskStatus(TaskStatusConst.REJECTION);
            list.add(task);
        }
        boolean isSuccess = business.updateBatchById(list);
        return JsonResultKit.of(isSuccess);
    }

    /**
     * 雇员任务查询
     */
    @Log("雇员任务查询")
    @PostMapping("/empTaskById")
    public JsonResult<SsEmpTaskBO> empTaskById(
        @RequestParam("empTaskId") Long empTaskId
        , @RequestParam(value = "operatorType", defaultValue = "-1") Integer operatorType // 1 任务单费用段
    ) {
        SsEmpTask empTask = business.selectById(empTaskId);
        SsEmpTaskBO dto = JsonKit.castToObject(empTask, SsEmpTaskBO.class);

        // operatorType == 1 查询任务单费用段
        if (operatorType == 1) {
            List<SsEmpTaskPeriod> periods = ssEmpTaskPeriodService.queryByEmpTaskId(empTaskId);
            dto.setEmpTaskPeriods(periods);
        }
        return JsonResultKit.of(dto);
    }


    /**
     * 雇员任务办理
     */
    @Log("雇员任务办理")
    @PostMapping("/handle")
    public JsonResult<Boolean> handle(@RequestBody SsEmpTaskBO bo) {
        {// 更新任务单费用段
            List<SsEmpTaskPeriod> periods = bo.getEmpTaskPeriods();
            if (periods != null) {
                ssEmpTaskPeriodService.saveForEmpTaskId(periods, bo.getEmpTaskId());
                periods = ssEmpTaskPeriodService.queryByEmpTaskId(bo.getEmpTaskId());
                bo.setEmpTaskPeriods(periods);
            }
        }
        {// 更新雇员任务信息
            // 备注时间
            LocalDate now = LocalDate.now();
            bo.setHandleRemarkDate(now);
            bo.setRejectionRemarkDate(now);
            business.updateById(bo);
        }
        {
            int taskStatus = bo.getTaskStatus();
            // 处理中，正式把数据写入到 ss_emp_base_period and ss_emp_base_detail(雇员社)
            if (TaskStatusConst.PROCESSING == taskStatus) {
                progressing(bo);
            }
        }
        return JsonResultKit.of(true);
    }

    /**
     * 处理中
     *
     * @param bo
     */
    private void progressing(SsEmpTaskBO bo) {
        List<SsEmpTaskPeriod> taskPeriods = bo.getEmpTaskPeriods();
        if (taskPeriods == null) {
            return;
        }

        Long empArchiveId = bo.getEmpArchiveId();
        Long empTaskId = bo.getEmpTaskId();

        // 险种
        List<SsEmpBasePeriod> basePeriods = new ArrayList<>(taskPeriods.size());
        List<JSONObject> empSocials = getEmpSocials(bo);
        // 删除 old 费用段和明细
        ssEmpBasePeriodService.deleteByEmpTaskId(empTaskId);

        {// 更新任务单费用段
            int taskCategory = bo.getTaskCategory();
            String handleMonth = bo.getHandleMonth();

            taskPeriods.forEach(p -> {
                SsEmpBasePeriod basePeriod = Adapter.ssEmpBasePeriod(p);
                basePeriod.setEmpArchiveId(empArchiveId);
                basePeriod.setEmpTaskId(empTaskId);

                if (taskCategoryConst.INTO == taskCategory) {
                    basePeriod.setSsMonthStop(handleMonth);
                } else {
                    basePeriod.setSsMonth(handleMonth);
                }
                by(basePeriod);
                basePeriods.add(basePeriod);
            });
            ssEmpBasePeriodService.saveForEmpTaskId(basePeriods, empTaskId);
        }

        {// 更新雇员社保汇缴基数明细
            basePeriods.forEach(p -> {
                // 组合险种和费用段
                List<SsEmpBaseDetail> details = new ArrayList<>();
                Long empBasePeriodId = p.getEmpBasePeriodId();
                empSocials.forEach(empSocial -> {
                    SsEmpBaseDetail detail = Adapter.ssEmpBaseDetail(empSocial);
                    detail.setEmpArchiveId(empArchiveId);
                    detail.setEmpBasePeriodId(empBasePeriodId);
                    by(detail);
                    details.add(detail);
                });
                SsEmpBaseDetail detail = new SsEmpBaseDetail();
                detail.setEmpArchiveId(empArchiveId);
                detail.setEmpBasePeriodId(empBasePeriodId);

                ssEmpBaseDetailService.saveForSsEmpBaseDetail(details, detail);
            });
        }
    }

    /**
     * 特殊任务查询
     *
     * @param empTaskId
     * @return
     */
    @Log("特殊任务查询")
    @PostMapping("/queryEmpSpecialTaskById")
    public JsonResult<SsEmpTask> queryEmpSpecialTask(String empTaskId) {
        // 查询
        SsEmpTask ssEmpTask = business.selectById(StringUtils.isNotBlank(empTaskId) ? Long.valueOf(empTaskId) : null);

        return JsonResultKit.of(ssEmpTask);
    }

    /**
     * 特殊任务办理
     *
     * @param
     * @return
     */
    @Log("特殊任务办理")
    @PostMapping("/empSpecialTaskHandle")
    public JsonResult empSpecialTaskHandle(SsEmpTask ssEmpTask) {

        //参数对象为空
        if (null == ssEmpTask) return JsonResultKit.ofError("参数为空");
        //状态为空
        if (null == ssEmpTask.getHandleStatus()) return JsonResultKit.ofError("状态错误");

        //1、材料收缴  2 、受理中  3、送审中  4 已完成
        if (1 == ssEmpTask.getHandleStatus()) {
            ssEmpTask.setTaskStatus(new Integer(1));
        } else if (2 == ssEmpTask.getHandleStatus() || 3 == ssEmpTask.getHandleStatus()) {
            ssEmpTask.setTaskStatus(new Integer(2));
        } else if (4 == ssEmpTask.getHandleStatus()) {
            ssEmpTask.setTaskStatus(new Integer(3));
        } else {
            return JsonResultKit.ofError("状态错误");
        }
        ssEmpTask.setModifiedTime(LocalDateTime.now());
        ssEmpTask.setModifiedBy("xsj");
        boolean result = business.updateById(ssEmpTask);
        return JsonResultKit.of(result);
    }

    /*
     * 获得险种，根据业务接口 ID 查询险种或解析任务单扩展字段
     *
     * @param bo
     * @return
     */
    List<JSONObject> getEmpSocials(SsEmpTaskBO bo) {
        List<JSONObject> list = new ArrayList<>();
        {
            JSONObject obj = new JSONObject();
            obj.put("itemDicId", "1");
            obj.put("policyName", "1");
            obj.put("empBase", "1");
            obj.put("comBase", "1");
            obj.put("personalRatio", "1");
            obj.put("companyRatio", "1");
            list.add(obj);
        }
        {
            JSONObject obj = new JSONObject();
            obj.put("itemDicId", "2");
            obj.put("policyName", "2");
            obj.put("empBase", "2");
            obj.put("comBase", "2");
            obj.put("personalRatio", "2");
            obj.put("companyRatio", "2");
            list.add(obj);
        }
        {
            JSONObject obj = new JSONObject();
            obj.put("itemDicId", "3");
            obj.put("policyName", "3");
            obj.put("empBase", "3");
            obj.put("comBase", "3");
            obj.put("personalRatio", "3");
            obj.put("companyRatio", "3");
            list.add(obj);
        }

//        bo.setTaskFormContent("");
        return list;
    }

    void by(Object entity){
        BeanMap bm = new BeanMap(entity);
        bm.put("createdBy","admin");
        bm.put("modifiedBy","admin");
    }

    /**
     * Stashed changes
     * 适配器
     */
    static class Adapter {

        /**
         * 适配《雇员社保汇缴基数明细》
         *
         * @param empSocial
         * @return
         */
        public static SsEmpBaseDetail ssEmpBaseDetail(JSONObject empSocial) {
            SsEmpBaseDetail detail = new SsEmpBaseDetail();
            detail.setSsType(empSocial.getString("itemDicId"));
            detail.setSsTypeName(empSocial.getString("policyName"));
            detail.setEmpBase(empSocial.getBigDecimal("empBase"));
            detail.setComBase(empSocial.getBigDecimal("comBase"));
            detail.setEmpRatio(empSocial.getBigDecimal("personalRatio"));
            detail.setComRatio(empSocial.getBigDecimal("companyRatio"));
            return detail;
        }

        /**
         * 适配《雇员正常汇缴社保的基数分段》
         *
         * @param taskPeriod
         * @return
         */
        public static SsEmpBasePeriod ssEmpBasePeriod(SsEmpTaskPeriod taskPeriod) {
            SsEmpBasePeriod basePeriod = new SsEmpBasePeriod();
            basePeriod.setBaseAmount(taskPeriod.getBaseAmount());
            basePeriod.setEmpTaskId(taskPeriod.getEmpTaskId());
            basePeriod.setEndMonth(taskPeriod.getEndMonth());
            basePeriod.setStartMonth(taskPeriod.getStartMonth());
            basePeriod.setRemitWay(taskPeriod.getRemitWay());
            return basePeriod;
        }
    }

    // ---------------------------------------------------------------------------------------------
    // ------------------------------ 内部接口和内部类----------------------------------------------
    // ---------------------------------------------------------------------------------------------

    /**
     * 任务类型，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6封存 7退账 8 提取 9特殊操作
     */
    interface taskCategoryConst {
        int INTO = 2;// 转入
    }

    /**
     * 办理状态：1、未处理 2 、处理中(已办)  3 已完成(已做) 4、批退 5、不需处理
     */
    interface TaskStatusConst {

        int NOTPROGRESS = 1;// 未处理
        int PROCESSING = 2;// 处理中
        int FINISH = 3;// 已完成
        int REJECTION = 4;// 批退
        int NOPROGRESS = 1;// 不需处理
    }


    /**
     * 批退 请求参数
     */
    static class RejectionParam {

        // 批退 id 列表
        private List<Long> ids;
        // 批退备注
        private String remark;

        public List<Long> getIds() {
            return ids;
        }

        public void setIds(List<Long> ids) {
            this.ids = ids;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}


