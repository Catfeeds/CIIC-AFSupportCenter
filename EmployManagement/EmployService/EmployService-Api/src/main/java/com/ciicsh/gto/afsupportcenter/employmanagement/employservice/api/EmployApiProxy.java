package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by zhangzhiwen on 2018/3/29.
 */

@FeignClient("support-center-employmanagement-api-service")
@RequestMapping("/api/employ")
public interface EmployApiProxy {

    @PostMapping({"/getEmploymentByTaskId"})
    EmploymentDTO getEmploymentByTaskId(@RequestBody TaskParamDTO taskParamDTO);

    @PostMapping({"/getResignByTaskId"})
    ResignDTO  getResignByTaskId(@RequestBody TaskParamDTO taskParamDTO);


    @PostMapping({"/getArchiveByEmployeeId"})
    ArchiveDTO  getArchiveByEmployeeId(@RequestBody TaskParamDTO taskParamDTO);

    @GetMapping({"/queryMaterialByTaskId/{empTaskId}"})
    List<MaterialDTO> queryMaterialByTaskId(@PathVariable(value = "empTaskId") Long empTaskId);

    @PostMapping({"/updateMaterialByTaskId"})
    boolean updateMaterialByTaskId(@RequestBody MaterialUpdateDTO materialUpdateDTO);

    @PostMapping({"/saveCompanyDTO"})
    boolean saveCompanyDTO(@RequestBody CompanyDTO companyDTO);

    @PostMapping({"/queryCompanyDTO"})
    CompanyDTO queryCompanyDTO(@RequestBody CompanyParamDTO companyParamDTO);

    @GetMapping({"/queryMaterialLastOperationLog/{empTaskId}"})
    MaterialOperationLogDTO queryMaterialLastOperationLog(@PathVariable(value = "empTaskId") String empTaskId);

    @GetMapping({"/queryMaterialOperationLogList/{empTaskId}"})
    List<MaterialOperationLogDTO> queryMaterialOperationLogList(@PathVariable(value = "empTaskId") String empTaskId);
}
