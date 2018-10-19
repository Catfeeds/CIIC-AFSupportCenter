package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.dto.emptask.EmpTaskBatchParameter;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpTaskRollInBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpTaskRollOutBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.TaskCommonUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.AmEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.ExportResponseUtil;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 本地社保的雇员任务单 前端控制器
 */
@RestController
@RequestMapping("/api/soccommandservice/ssEmpTask")
public class SsEmpTaskController extends BasicController<SsEmpTaskService> {

    @Autowired
    private SsEmpTaskPeriodService ssEmpTaskPeriodService;
    @Autowired
    private SsEmpRefundService ssEmpRefundService;
    @Autowired
    private CommonApiUtils commonApiUtils;
    @Autowired
    private AmEmpTaskOfSsService amEmpTaskOfSsService;
    @Autowired
    private SsEmpTaskFrontService ssEmpTaskFrontService;
    @Autowired
    private LogApiUtil logApiUtil;
    @Autowired
    private SsEmpArchiveService ssEmpArchiveService;

    /**
     * 雇员日常操作查询
     */
    @PostMapping("/employeeOperatorQuery")
    public JsonResult<List<SsEmpTaskBO>> employeeOperatorQuery(PageInfo pageInfo) {
        PageRows<SsEmpTaskBO> pageRows = business.employeeOperatorQuery(pageInfo, UserContext.getUserId());
        return JsonResultKit.ofPage(pageRows);
    }

    @RequestMapping("/employeeOperatorQueryExport")
    public void employeeOperatorQueryExport(HttpServletResponse response, PageInfo pageInfo) {
        Date date = new Date();
        String fileNme = "雇员日常社保_" + StringUtil.getDateString(date) + ".xls";
        PageRows<SsEmpTaskBO> pageRows = business.employeeOperatorQuery(pageInfo, UserContext.getUserId());
        ExcelUtil.exportExcel(pageRows.getRows(), SsEmpTaskBO.class, fileNme, response);
    }

    /**
     * 雇员任务批退
     */
    @PostMapping("/rejection")
    public JsonResult<Boolean> rejection(RejectionParam param) {
        List<Long> ids = Optional.ofNullable(param.getIds()).orElse(Collections.emptyList());
        String remark = param.getRemark();
        business.batchRejection(ids, remark, UserContext.getUserId(), UserContext.getUser().getDisplayName(), false);
        return JsonResultKit.of();
    }

    /**
     * 雇员任务查询
     */
    @PostMapping("/empTaskById")
    public JsonResult<SsEmpTaskBO> empTaskById(
        @RequestParam("empTaskId") Long empTaskId
        , @RequestParam(value = "operatorType", defaultValue = "-1") Integer operatorType,// 1 任务单费用段
        @RequestParam(value = "isNeedSerial", defaultValue = "-1") Integer isNeedSerial
    ) {
//        SsEmpTask empTask = business.selectById(empTaskId);
        SsEmpTask empTask;
        List<SsEmpTask> empTaskList = business.queryEmpTaskById(empTaskId, null);
        if (CollectionUtils.isNotEmpty(empTaskList)) {
            if (empTaskList.size() > 1) {
                return JsonResultKit.ofError("该任务单所关联的权限数据有问题");
            }
            empTask = empTaskList.get(0);
        } else {
            return JsonResultKit.ofError("获取当前任务单失败");
        }

        SsEmpTaskBO dto = JsonKit.castToObject(empTask, SsEmpTaskBO.class);
        // operatorType == 1 查询任务单费用段
        if (operatorType == 1) {
            List<SsEmpTaskPeriod> periods = ssEmpTaskPeriodService.queryByEmpTaskId(empTaskId);
            dto.setEmpTaskPeriods(periods);
        }
        if(!StringUtil.isEmpty(dto.getPolicyDetailId())){
            dto.setPolicyName(TaskCommonUtils.getPolicyName(commonApiUtils,dto.getPolicyDetailId()));
        }
        //表示新进和转入 需要社保序号 并且任务单为 初始状态
//        if(isNeedSerial==1 && dto.getTaskStatus()==1){
//            String ssSerial = business.selectMaxSsSerialByTaskId(empTaskId);
//            dto.setEmpSsSerial(ssSerial);
//         }
        if (isNeedSerial==1 && dto.getTaskStatus()==1) { // 如果存在雇员档案时，说明是更正任务，社保序号可复用
            Wrapper<SsEmpArchive> ssEmpArchiveWrapper = new EntityWrapper<>();
            ssEmpArchiveWrapper.where("is_active = 1");
            ssEmpArchiveWrapper.and("company_id = {0}", empTask.getCompanyId());
            ssEmpArchiveWrapper.and("employee_id = {0}", empTask.getEmployeeId());
            ssEmpArchiveWrapper.and("emp_company_id = {0}", empTask.getEmpCompanyId());
            SsEmpArchive ssEmpArchive = ssEmpArchiveService.selectOne(ssEmpArchiveWrapper);

            if (ssEmpArchive != null) {
                dto.setEmpSsSerial(ssEmpArchive.getSsSerial());
            }
        }

        Integer amEmpTaskTaskCategory = 1;
        String feedback;
        AmEmpTaskDTO amEmpTaskDTO = null;

        if (empTask.getTaskCategory().equals(Integer.valueOf(SocialSecurityConst.TASK_TYPE_5)) ||
            empTask.getTaskCategory().equals(Integer.valueOf(SocialSecurityConst.TASK_TYPE_6))
            ) {
            amEmpTaskTaskCategory = 2;
            feedback = amEmpTaskOfSsService.queryResignFeedback(empTask.getEmployeeId(), empTask.getCompanyId());
        } else {
            feedback = amEmpTaskOfSsService.queryEmployFeedback(empTask.getEmployeeId(), empTask.getCompanyId());
            //任务单参考信息 用工
            amEmpTaskDTO = amEmpTaskOfSsService.queryReworkInfo(empTask.getEmployeeId(), empTask.getCompanyId(), amEmpTaskTaskCategory);
        }

        if (amEmpTaskDTO == null) {
            amEmpTaskDTO = new AmEmpTaskDTO();
            amEmpTaskDTO.setTaskCategory(amEmpTaskTaskCategory);
        }

        if (StringUtils.isNotEmpty(feedback)) {
            amEmpTaskDTO.setTaskStatus(Integer.parseInt(feedback));
        }
        dto.setAmEmpTaskDTO(amEmpTaskDTO);

        //查询该雇员是否还有其他已办理或者未办理的任务
        EntityWrapper<SsEmpTask> ew = new EntityWrapper<SsEmpTask>();
        ew.where("employee_id={0}", dto.getEmployeeId())
            .and("company_id={0}",dto.getCompanyId())
            .and("is_active=1")
            .and("emp_task_id!={0}", dto.getEmpTaskId())
            .and("task_status=1")
            .orderBy("created_time", true);
        List<SsEmpTask> ssEmpTaskList = business.selectList(ew);
        if (ssEmpTaskList.size() > 0) {
            dto.setTheSameTask(ssEmpTaskList);
        } else {
            dto.setTheSameTask(new ArrayList());
        }
        // 查询备注
        List<SsEmpRemark> remarks = ssEmpArchiveService.querySsEmpRemarkList(dto.getCompanyId(), dto.getEmployeeId());
        dto.setRemarks(remarks);
        return JsonResultKit.of(dto);
    }


    /**
     * 雇员任务办理（新进和转入）
     */
    @PostMapping("/handle")
    public JsonResult<String> handle(@RequestBody SsEmpTaskBO bo) {
        bo.setModifiedBy(UserContext.getUserId());
        bo.setModifiedDisplayName(UserContext.getUser().getDisplayName());
        //false 表示单个办理
        String result;
        try {
            result = business.saveHandleData(bo, false);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logApiUtil.error(LogMessage.create().setTitle("SsEmpTaskController#handle").setContent(sw.toString()));
            throw e;
        }
        if("SUCC".equals(result)){
            return JsonResultKit.of(result);
        }
        return JsonResultKit.ofError(result);
    }

    /**
     * 获得社保序号
     */
    @PostMapping("/getSerial")
    public JsonResult<Object> getSerial(String comAccountId) {
        Long ssSerial = business.getSerial(Long.valueOf(comAccountId));
        return JsonResultKit.of(ssSerial);
    }

    /**
     * 特殊任务查询
     * @param empTaskId
     * @return
     */
    @PostMapping("/queryEmpSpecialTaskById")
    public JsonResult<SsEmpTask> queryEmpSpecialTask(String empTaskId) {
        // 查询
        SsEmpTask ssEmpTask = business.selectById(StringUtils.isNotBlank(empTaskId) ? Long.valueOf(empTaskId) : null);
        return JsonResultKit.of(ssEmpTask);
    }

    /**
     * 特殊任务办理
     * @param
     * @return
     */
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
        ssEmpTask.setModifiedBy(UserContext.getUserId());
        boolean result = business.updateById(ssEmpTask);
        return JsonResultKit.of(result);
    }

    /**
     * 通过任务单id查询退账金额
     * @param ssEmpTaskBO
     * @return
     */
    @RequestMapping("/queryRefundAmountByTaskId")
    public JsonResult<Object> queryRefundAmountByTaskId(SsEmpTaskBO ssEmpTaskBO) {
        EntityWrapper<SsEmpRefund> ew = new EntityWrapper();
        ew.where("emp_task_id={0}", ssEmpTaskBO.getEmpTaskId()).and("is_active=1");
        Object obj = ssEmpRefundService.selectOne(ew);
        return JsonResultKit.of(obj);
    }

    /**
     * 查询批量任务信息
     * @param ssEmpTaskBO
     * @return
     */
    @RequestMapping("/queryBatchEmpArchiveByEmpTaskIds")
    public JsonResult<Object> queryBatchEmpArchiveByEmpTaskIds(@RequestBody SsEmpTaskBO ssEmpTaskBO) {
        List<SsEmpTaskBO> result = business.queryBatchEmpArchiveByEmpTaskIds(ssEmpTaskBO);
        return JsonResultKit.of(result);
    }

    /**
     * 通过条件查询批量任务信息
     * @param ssEmpTaskBO
     * @return
     */
    @RequestMapping("/queryBatchTaskByCondition")
    public JsonResult<Object> queryBatchTaskByCondition(@RequestBody SsEmpTaskBO ssEmpTaskBO) {
        List<SsEmpTaskBO> result = business.queryBatchTaskByCondition(ssEmpTaskBO);
        return JsonResultKit.of(result);
    }

    /**
     * 批量任务办理
     * @param empTaskBatchParameter
     * @return
     */
    @RequestMapping("/handleBatchTask")
    public JsonResult<Object> handleBatchTask(@RequestBody EmpTaskBatchParameter empTaskBatchParameter) {
        long beginTime = new Date().getTime();
        Assert.notNull(empTaskBatchParameter, "参数异常");
        Assert.notNull(empTaskBatchParameter.getSsEmpTaskBOList(), "参数异常");
        LocalDate now = LocalDate.now();
        StringBuffer handleMonth = TaskCommonUtils.getMonthStr(now);
        empTaskBatchParameter.getSsEmpTaskBOList().forEach(p -> {
            // 1新进  2转入 12 翻牌新进 13翻牌转入
            if ((1 == p.getTaskCategory() || 2 == p.getTaskCategory() || 12 == p.getTaskCategory() || 13 == p.getTaskCategory()) && p.getComAccountId() != null) {
                Long ssSerial = business.getSerial(p.getComAccountId());
                //社保序号
                p.setEmpSsSerial(String.valueOf(ssSerial));
            }
            //退账 表示已完成
            if (7 == p.getTaskCategory()) {
                p.setTaskStatus(TaskStatusConst.FINISH);
            } else {
                p.setTaskStatus(TaskStatusConst.PROCESSING);
            }
            //讨论说 批量办理的 办理月份为当前月份
            p.setHandleMonth(handleMonth.toString());
            p.setModifiedTime(LocalDateTime.now());
            p.setModifiedBy(UserContext.getUserId());
            //true 表示批量办理
            String ret = business.saveHandleData(p, true);
            if (!"SUCC".equals(ret)){
                throw new BusinessException("该雇员存在多个未转出的雇员档案，数据不正确");
            }
        });
        long endTime = new Date().getTime();
        logApiUtil.info(LogMessage.create().setTitle("本地社保雇员任务单批量办理").setContent("cost time(ms): " + (endTime - beginTime)));
        return JsonResultKit.of(true);
    }

    /**
     * 雇员日常操作转入盘片导出
     * @param
     * @return
     */
    @RequestMapping("/employeeDailyOperatorDiskExport")
    public void employeeDailyOperatorDiskExport(HttpServletResponse response, PageInfo pageInfo) {
        try {
            SsEmpTaskBO ssEmpTaskBO = pageInfo.toJavaObject(SsEmpTaskBO.class);
            String sheetName;
            boolean isRollIn = false;
            Class clazz;
            if (ssEmpTaskBO.getTaskCategory() == 2) { // TODO dictService
                sheetName = "转入盘片";
                isRollIn = true;
                clazz = SsEmpTaskRollInBO.class;
            } else {
                sheetName = "转出盘片";
                clazz = SsEmpTaskRollOutBO.class;
            }
            pageInfo.setPageSize(10000);
            pageInfo.setPageNum(1);
            PageRows<T> result = business.employeeDailyOperatorQueryForDisk(pageInfo, UserContext.getUserId(), isRollIn);
            long total = result.getTotal();
            ExportParams exportParams = new ExportParams();
            exportParams.setType(ExcelType.XSSF);
            exportParams.setSheetName(sheetName);
            Workbook workbook;

            if (total <= pageInfo.getPageSize()) {
                workbook = ExcelExportUtil.exportExcel(exportParams, clazz, result.getRows());
            } else {
                workbook = ExcelExportUtil.exportBigExcel(exportParams, clazz, result.getRows());
                int pageNum = (int) Math.ceil(total * 1.0 / pageInfo.getPageSize());
                for (int i = 2; i <= pageNum; i++) {
                    pageInfo.setPageNum(i);
                    result = business.employeeDailyOperatorQueryForDisk(pageInfo, UserContext.getUserId(), isRollIn);
                    workbook = ExcelExportUtil.exportBigExcel(exportParams, clazz, result.getRows());
                }
                ExcelExportUtil.closeExportBigExcel();
            }

            String fileName = "sheetName.xlsx".replace("sheetName", sheetName);

            response.reset();
            response.setCharacterEncoding("UTF-8");
//            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setHeader("Content-Disposition",
//                "attachment;filename=" + fileName);
            ExportResponseUtil.encodeExportFileName(response, fileName);
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            logApiUtil.error(LogMessage.create().setTitle("SsEmpTaskController#employeeDailyOperatorDiskExport").setContent(e.getMessage()));
        }
    }

    @PostMapping("/queryHistoryEmpTaskList")
    public JsonResult<List<SsEmpTask>> queryHistoryEmpTaskList(@RequestParam("empTaskId") String empTaskId) {
        Long taskId = Long.parseLong(empTaskId);
        SsEmpTask ssEmpTask = business.selectById(taskId);

        //查询该雇员是否还有其他已办理或者未办理的任务
        EntityWrapper<SsEmpTask> ew = new EntityWrapper<SsEmpTask>();
        ew.where("employee_id={0}", ssEmpTask.getEmployeeId())
            .and("company_id={0}",ssEmpTask.getCompanyId())
            .and("is_active=1")
            .and("emp_task_id!={0}", taskId)
            .and("task_status>1")
            .orderBy("created_time", false);
        List<SsEmpTask> ssEmpTaskList = business.selectList(ew);
        return JsonResultKit.of(ssEmpTaskList);
    }

    @PostMapping("/getOriginEmpTask")
    public JsonResult<SsEmpTaskFront> getOriginEmpTask(@RequestParam("empTaskId") String empTaskId) {
        EntityWrapper<SsEmpTaskFront> ew = new EntityWrapper<>();
        ew.where("emp_task_id={0}", Long.parseLong(empTaskId))
            .and("is_active=1")
            .last("limit 1");
        SsEmpTaskFront ssEmpTaskFront = ssEmpTaskFrontService.selectOne(ew);
        return JsonResultKit.of(ssEmpTaskFront);
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


