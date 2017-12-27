package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpTaskPeriodService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTaskPeriod;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public JsonResult<SsEmpTaskBO> empTaskById(@RequestParam("empTaskId") Long empTaskId
        , @RequestParam(value = "operatorType", defaultValue = "-1") Integer operatorType // 1 任务单费用段
    ) {
        SsEmpTask empTask = business.selectById(empTaskId);
        SsEmpTaskBO dto = JsonKit.castToObject(empTask, SsEmpTaskBO.class);

        // operatorType == 1 查询任务单费用段
        if (operatorType == 1) {
            List<SsEmpTaskPeriod> periods = ssEmpTaskPeriodService.queryByEmpTaskId(String.valueOf(empTaskId));
            dto.setEmpTaskPeriods(periods);
        }
        return JsonResultKit.of(dto);
    }


    /**
     * 雇员任务办理
     */
    @Log("雇员任务办理")
    @PostMapping("/handle")
    public JsonResult<Boolean> handle(@RequestBody SsEmpTaskBO param) {
        {// 更新任务单费用段
            List<SsEmpTaskPeriod> periods = param.getEmpTaskPeriods();
            if (periods != null) {
                ssEmpTaskPeriodService.saveForEmpTaskId(periods, param.getEmpTaskId());
            }
        }
        {// 更新雇员任务信息
            // 备注时间
            LocalDate now = LocalDate.now();
            param.setHandleRemarkDate(now);
            param.setRejectionRemarkDate(now);
            // 更新雇员任务信息
            business.updateById(param);
        }
        {
            int taskStatus = param.getTaskStatus();
            // 未处理
            if (TaskStatusConst.PROCESSING == taskStatus) {

            }
        }
        return JsonResultKit.of(true);
    }

    private void progressing() {

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
class RejectionParam {

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

