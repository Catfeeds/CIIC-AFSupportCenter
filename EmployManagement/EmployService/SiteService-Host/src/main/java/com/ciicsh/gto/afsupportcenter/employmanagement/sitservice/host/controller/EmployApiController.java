package com.ciicsh.gto.afsupportcenter.employmanagement.sitservice.host.controller;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.EmployApiProxy;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmCompanySetService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmEmpMaterialService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmEmpTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by zhangzhiwen on 2018/3/30.
 */

@RestController
@RequestMapping("/api/employ")
@Api(value = "soc-api-service",description = "support interface for other center")
public class EmployApiController implements EmployApiProxy {

   @Autowired
   private IAmEmpTaskService amEmpTaskService;

   @Autowired
   private IAmEmpMaterialService amEmpMaterialService;

   @Autowired
   private IAmCompanySetService  amCompanySetService;

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

    @Override
    public List<MaterialDTO> queryMaterialByTaskId(@RequestBody Long empTaskId) {
        return amEmpMaterialService.queryMaterialByTaskId(empTaskId);
    }

    @Override
    public boolean updateMaterialByTaskId(@RequestBody MaterialUpdateDTO materialUpdateDTO) {
        return amEmpMaterialService.updateMaterialByTaskId(materialUpdateDTO);
    }

    @Override
    public boolean saveCompanyDTO(@RequestBody CompanyDTO companyDTO) {

        return amCompanySetService.saveCompanyDTO(companyDTO);
    }

    @Override
    public CompanyDTO queryCompanyDTO(@RequestBody CompanyParamDTO companyParamDTO) {

        return amCompanySetService.queryCompanyDTO(companyParamDTO);
    }
}