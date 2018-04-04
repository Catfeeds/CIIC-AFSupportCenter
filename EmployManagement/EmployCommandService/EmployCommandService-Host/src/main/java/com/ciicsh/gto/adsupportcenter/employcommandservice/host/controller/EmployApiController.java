package com.ciicsh.gto.adsupportcenter.employcommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.EmployApiProxy;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmEmpTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by zhangzhiwen on 2018/3/30.
 */

@RestController
@RequestMapping("/api/employ")
@Api(value = "soc-api-service",description = "support interface for other center")
public class EmployApiController implements EmployApiProxy {

   @Autowired
   private IAmEmpTaskService amEmpTaskService;

    @Override
    @ApiOperation(value = "根据任务Id查询用工信息",notes = "根据TaskParamDTO对象创建")
    @ApiImplicitParam(name = "taskParamDTO",value = "根据任务Id查询用工信息",required = true,dataType = "TaskParamDTO")
    @PostMapping("/getEmploymentByTaskId")
    public EmploymentDTO getEmploymentByTaskId(@RequestBody TaskParamDTO taskParamDTO) {
        return  amEmpTaskService.getEmploymentByTaskId(taskParamDTO);
    }

    @Override
    @ApiOperation(value = "根据任务Id查询退工信息",notes = "根据TaskParamDTO对象创建")
    @ApiImplicitParam(name = "taskParamDTO",value = "根据任务Id查询退工信息",required = true,dataType = "TaskParamDTO")
    @PostMapping("/getResignByTaskId")
    public ResignDTO getResignByTaskId(@RequestBody TaskParamDTO taskParamDTO) {
       return  amEmpTaskService.getResignByTaskId(taskParamDTO);
    }

    @Override
    @ApiOperation(value = "根据雇员Id查询档案信息",notes = "根据TaskParamDTO对象创建")
    @ApiImplicitParam(name = "taskParamDTO",value = "根据雇员Id查询档案信息",required = true,dataType = "TaskParamDTO")
    @PostMapping("/getArchiveByEmployeeId")
    public ArchiveDTO getArchiveByEmployeeId(@RequestBody TaskParamDTO taskParamDTO) {
        return  amEmpTaskService.getArchiveByEmployeeId(taskParamDTO);
    }
}
