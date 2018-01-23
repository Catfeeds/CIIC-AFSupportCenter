package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private ISsEmpRefundService ssEmpRefundService;
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
        System.out.println(obj);
        return JsonResultKit.of(obj);
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


