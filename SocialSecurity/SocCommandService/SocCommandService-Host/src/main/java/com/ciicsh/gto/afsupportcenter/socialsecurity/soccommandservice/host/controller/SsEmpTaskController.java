package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskRollInBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskRollOutBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustEmployeeDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.dto.emptask.EmpTaskBatchParameter;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import utils.TaskCommonUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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
public class SsEmpTaskController extends BasicController<SsEmpTaskService> {

    @Autowired
    private SsEmpTaskPeriodService ssEmpTaskPeriodService;
    @Autowired
    private SsEmpRefundService ssEmpRefundService;
    @Autowired
    private CommonApiUtils commonApiUtils;
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
            //调用工作流
            //TaskCommonUtils.completeTask(String.valueOf(task.getEmpTaskId()),commonApiUtils,"xsj");
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
        , @RequestParam(value = "operatorType", defaultValue = "-1") Integer operatorType ,// 1 任务单费用段
        @RequestParam(value = "isNeedSerial", defaultValue = "-1") Integer isNeedSerial
    ) {
        SsEmpTask empTask = business.selectById(empTaskId);
        SsEmpTaskBO dto = JsonKit.castToObject(empTask, SsEmpTaskBO.class);
        // operatorType == 1 查询任务单费用段
        if (operatorType == 1) {
            List<SsEmpTaskPeriod> periods = ssEmpTaskPeriodService.queryByEmpTaskId(empTaskId);
            dto.setEmpTaskPeriods(periods);
        }
        //表示新进和转入 需要社保序号 并且任务单为 初始状态
        if(isNeedSerial==1 && dto.getTaskStatus()==1){
            String ssSerial = business.selectMaxSsSerialByTaskId(empTaskId);
            dto.setEmpSsSerial(ssSerial);
        }
        //查询该雇员是否还有其他已办理或者未办理的任务
       EntityWrapper<SsEmpTask> ew =  new EntityWrapper<SsEmpTask>();
        ew.where("employee_id={0}",dto.getEmployeeId())
            .and("task_category={0}",dto.getTaskCategory())
            .and("is_active=1").and("emp_task_id!={0}",dto.getEmpTaskId()).and("task_status=1");
        List<SsEmpTask> ssEmpTaskList = business.selectList(ew);
        if(ssEmpTaskList.size()>0){
            dto.setIsHaveSameTask(1);
        }else{
            dto.setIsHaveSameTask(0);
        }
        return JsonResultKit.of(dto);
    }



    /**
     * 雇员任务办理（新进和转入）
     */
    @Log("雇员任务办理")
    @PostMapping("/handle")
    public JsonResult<Boolean> handle(@RequestBody SsEmpTaskBO bo) {
       boolean result =  business.saveHandleData(bo);
        return JsonResultKit.of(result);
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

    @Log("通过任务单id查询退账金额")
    @RequestMapping("/queryRefundAmountByTaskId")
    public JsonResult<Object> queryRefundAmountByTaskId(SsEmpTaskBO ssEmpTaskBO){
        EntityWrapper<SsEmpRefund> ew = new EntityWrapper();
        ew.where("emp_task_id={0}",ssEmpTaskBO.getEmpTaskId()).and("is_active=1");
        Object obj =  ssEmpRefundService.selectOne(ew);
        return JsonResultKit.of(obj);
    }
    @Log("查询批量任务信息")
    @RequestMapping("/queryBatchEmpArchiveByEmpTaskIds")
    public JsonResult<Object> queryBatchEmpArchiveByEmpTaskIds(@RequestBody SsEmpTaskBO ssEmpTaskBO){
        List<SsEmpTaskBO> result =business.queryBatchEmpArchiveByEmpTaskIds(ssEmpTaskBO);
        return JsonResultKit.of(result);
    }
    @Log("查询批量任务信息")
    @RequestMapping("/handleBatchTask")
    public JsonResult<Object> handleBatchTask(@RequestBody EmpTaskBatchParameter empTaskBatchParameter){
        Assert.notNull(empTaskBatchParameter,"参数异常");
        Assert.notNull(empTaskBatchParameter.getSsEmpTaskBOList(),"参数异常");
        LocalDate now = LocalDate.now();
        StringBuffer handleMonth = TaskCommonUtils.getMonthStr(now);
        empTaskBatchParameter.getSsEmpTaskBOList().forEach(p->{
            //1新进  2  转入 3  调整 4 补缴 5 转出 6封存 7退账
            if(1==p.getTaskCategory() || 2==p.getTaskCategory()){
                String ssSerial =business.selectMaxSsSerialByTaskId(p.getEmpTaskId());
                //社保序号
                p.setEmpSsSerial(ssSerial);
            }
            //退账 表示已完成
            if(7==p.getTaskCategory()){
                p.setTaskStatus(TaskStatusConst.FINISH);
            }else{
                p.setTaskStatus(TaskStatusConst.PROCESSING);
            }
            //讨论说 批量办理的 办理月份为当前月份
            p.setHandleMonth(handleMonth.toString());
            p.setModifiedTime(LocalDateTime.now());
            p.setModifiedBy("xsj");
            business.saveHandleData(p);
        });
        return JsonResultKit.of(true);
    }

    /**
     * 雇员日常操作转入盘片导出
     *
     * @param
     * @return
     */
    @RequestMapping("/employeeDailyOperatorDiskExport")
    @Log("雇员日常操作盘片导出")
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
            pageInfo.setPageNum(0);
            PageRows<T> result = business.employeeDailyOperatorQueryForDisk(pageInfo, isRollIn);
            long total = result.getTotal();
            ExportParams exportParams = new ExportParams();
            exportParams.setType(ExcelType.XSSF);
            exportParams.setSheetName(sheetName);
            Workbook workbook;

            if (total <= pageInfo.getPageSize()) {
                workbook = ExcelExportUtil.exportExcel(exportParams, clazz, result.getRows());
            } else {
                workbook = ExcelExportUtil.exportBigExcel(exportParams, clazz, result.getRows());
                int pageNum = (int) Math.ceil(total / pageInfo.getPageSize());
                for(int i = 1; i < pageNum; i++) {
                    pageInfo.setPageNum(i);
                    result = business.employeeDailyOperatorQueryForDisk(pageInfo, isRollIn);
                    workbook = ExcelExportUtil.exportBigExcel(exportParams, clazz, result.getRows());
                }
                ExcelExportUtil.closeExportBigExcel();
            }

            String fileName = URLEncoder.encode("sheetName.xlsx"
                .replace("sheetName", sheetName), "UTF-8");

            response.reset();
            response.setCharacterEncoding("UTF-8");
//            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition",
                "attachment;filename=" + fileName);
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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


