package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/3/29.
 */

@FeignClient("support-center-employee-site-service")
@RequestMapping("/api/employ")
public interface EmployApiProxy {

    @PostMapping({"/getEmploymentByTaskId"})
    EmploymentDTO getEmploymentByTaskId(@RequestBody TaskParamDTO taskParamDTO);

    @PostMapping({"/getResignByTaskId"})
    ResignDTO  getResignByTaskId(@RequestBody TaskParamDTO taskParamDTO);


    @PostMapping({"/getArchiveByEmployeeId"})
    ArchiveDTO  getArchiveByEmployeeId(@RequestBody TaskParamDTO taskParamDTO);

    @PostMapping({"/queryMaterialByTaskId"})
    List<MaterialDTO> queryMaterialByTaskId(@RequestBody Long empTaskId);

    @PostMapping({"/updateMaterialByTaskId"})
    boolean updateMaterialByTaskId(@RequestBody MaterialUpdateDTO materialUpdateDTO);


}
