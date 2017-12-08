package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsComTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 独立库客户任务单 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssComTask")
public class SsComTaskController extends BasicController<ISsComTaskService> {

    @Log("查询未处理企业任务单")
    @RequestMapping(value = "getNoProgressTask")
    public JsonResult<List<SsComTaskDTO>> getNoProgressCompanyTask(PageInfo pageInfo) {
        //mybatis 分页插件
        PageRows<SsComTaskDTO> pageRows = business.queryNoProgressCompanyTask(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    @Log("查询处理中企业任务单")
    @RequestMapping(value = "getProgressingTask")
    public JsonResult<List<SsComTaskDTO>> getNoProgressingCompanyTask(PageInfo pageInfo) {
        //mybatis 分页插件
        PageRows<SsComTaskDTO> pageRows = business.queryProgressingCompanyTask(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    @Log("查询已完成企业任务单")
    @RequestMapping(value = "getFinshedTask")
    public JsonResult<List<SsComTaskDTO>> getFinshedCompanyTask(PageInfo pageInfo) {
        //mybatis 分页插件
        PageRows<SsComTaskDTO> pageRows = business.queryFinshedCompanyTask(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    @Log("查询批退企业任务单")
    @RequestMapping(value = "getRefusedTask")
    public JsonResult<List<SsComTaskDTO>> getRefusedCompanyTask(PageInfo pageInfo) {
        //mybatis 分页插件
        PageRows<SsComTaskDTO> pageRows = business.queryRefusedCompanyTask(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    @Log("批退任务单")
    @RequestMapping(value = "refusingTask")
    public JsonResult<Boolean> refusingTask(@RequestParam(value = "taskIdStr", required = true) String taskIdStr,
                                            @RequestParam(value = "refuseReason", required = true) String refuseReason) {
        //mybatis 使用mybatis 的自带的批量修改
        List<SsComTask> dataList = new ArrayList<SsComTask>();
        //将前台传过来的参数解析成 任务单对象的数组
        if (StringUtils.isNotBlank(taskIdStr)) {
            String[] taskIdArr = taskIdStr.split(",");
            for (int i = 0; i < taskIdArr.length; i++) {
                SsComTask ssComTask = new SsComTask();
                //批退的任务单ID
                ssComTask.setCompanyTaskId(Long.parseLong(taskIdArr[i]));
                //批退状态 4
                ssComTask.setTaskStatus(4);
                //批退原因
                if (StringUtils.isNotBlank(refuseReason))
                    ssComTask.setRejectionRemark(refuseReason);
                dataList.add(ssComTask);
            }
        }
        boolean result = business.updateBatchById(dataList);
        JsonResult<Boolean> returnResult = JsonResultKit.of(result);
        return returnResult;
    }

    @Log("获得企业信息和材料收缴信息")
    @RequestMapping(value = "getCompanyInfoAndMaterial")
    public JsonResult<SsComTaskDTO> getCompanyInfoAndMaterial(SsComTaskDTO ssComTaskDTO){
        if(null!=ssComTaskDTO.getCompanyTaskId()){
            SsComTaskDTO ssComTaskDTORes =  business.queryComInfoAndMaterial(ssComTaskDTO);
            JsonResult<SsComTaskDTO> result = JsonResultKit.of(ssComTaskDTORes);
            return result;
            //前台传ID 为空 返回空
        }else return  JsonResultKit.of();
    }



}

