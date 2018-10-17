package com.ciicsh.gto.afsupportcenter.employmanagement.apiservice.host.controller;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.EmployApiProxy;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by zhangzhiwen on 2018/3/30.
 */

@RestController
@RequestMapping("/api/employ")
@Api(value = "employ-api-service",description = "support interface for other center")
public class EmployApiController implements EmployApiProxy {

   @Autowired
   private IAmEmpTaskService amEmpTaskService;

   @Autowired
   private IAmEmpMaterialService amEmpMaterialService;

   @Autowired
   private IAmCompanySetService  amCompanySetService;

   @Autowired
   private IAmResignService amResignService;

   @Autowired
   private IAmRemarkService amRemarkService;

    @ApiOperation(value = "根据任务Id查询用工信息",notes = "根据TaskParamDTO对象创建")
    @ApiImplicitParam(name = "taskParamDTO",value = "根据任务Id查询用工信息",required = true,dataType = "TaskParamDTO")
    @PostMapping("/getEmploymentByTaskId")
    public EmploymentDTO getEmploymentByTaskId(@RequestBody TaskParamDTO taskParamDTO) {
        return  amEmpTaskService.getEmploymentByTaskId(taskParamDTO);
    }

    @ApiOperation(value = "根据任务Id查询退工信息",notes = "根据TaskParamDTO对象创建")
    @ApiImplicitParam(name = "taskParamDTO",value = "根据任务Id查询退工信息",required = true,dataType = "TaskParamDTO")
    @PostMapping("/getResignByTaskId")
    public ResignDTO getResignByTaskId(@RequestBody TaskParamDTO taskParamDTO) {
       return  amEmpTaskService.getResignByTaskId(taskParamDTO);
    }

    @ApiOperation(value = "根据雇员Id查询档案信息",notes = "根据TaskParamDTO对象创建")
    @ApiImplicitParam(name = "taskParamDTO",value = "根据雇员Id查询档案信息",required = true,dataType = "TaskParamDTO")
    @PostMapping("/getArchiveByEmployeeId")
    public ArchiveDTO getArchiveByEmployeeId(@RequestBody TaskParamDTO taskParamDTO) {
        return  amEmpTaskService.getArchiveByEmployeeId(taskParamDTO);
    }

    @Override
    public List<MaterialDTO> queryMaterialByTaskId(@PathVariable(value = "empTaskId") Long empTaskId) {
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

    @Override
    public MaterialOperationLogDTO queryMaterialLastOperationLog(@PathVariable(value = "empTaskId") String empTaskId) {
        return amEmpMaterialService.queryMaterialLastOperationLog(empTaskId);
    }

    @Override
    public List<MaterialOperationLogDTO> queryMaterialOperationLogList(@PathVariable(value = "empTaskId") String empTaskId) {
        return amEmpMaterialService.queryMaterialOperationLogList(empTaskId);
    }

    @Override
    public TerminateDTO getResignByEmpCompanyId(@PathVariable(value = "empTaskId") Long  empTaskId) {
        return amResignService.getResignByEmpEmpTaskId(empTaskId);
    }

    @Override
    public List<RemarkDTO> getRemarkByTaskId(@RequestBody RemarkParamDTO taskParamDTO) {
        return amRemarkService.queryRemarkList(taskParamDTO);
    }

    @Override
    public ArchiveDTO getArchiveByEmpTaskId(@PathVariable(value = "empTaskId") Long empTaskId) {
        return amEmpTaskService.getArchiveByTaskId(empTaskId);
    }

    @Override
    public EmploymentDTO getEmploymentByEmpTaskId(@PathVariable(value = "empTaskId") Long empTaskId) {
        return amEmpTaskService.getEmploymentByEmpTaskId(empTaskId);
    }


}
