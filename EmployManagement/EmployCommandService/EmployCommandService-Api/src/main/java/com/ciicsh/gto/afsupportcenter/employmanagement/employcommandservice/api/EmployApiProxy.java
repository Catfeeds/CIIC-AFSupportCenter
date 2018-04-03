package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.ArchiveDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.EmploymentDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.ResignDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.TaskParamDTO;
import com.ciicsh.common.entity.JsonResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by zhangzhiwen on 2018/3/29.
 */

@FeignClient("support-center-employee-site-service")
@RequestMapping("/api/employ")
public interface EmployApiProxy {

    @PostMapping({"/getEmploymentByTaskId"})
    JsonResult<EmploymentDTO> getEmploymentByTaskId(@RequestBody TaskParamDTO taskParamDTO);

    @PostMapping({"/getResignByTaskId"})
    JsonResult<ResignDTO> getResignByTaskId(@RequestBody TaskParamDTO taskParamDTO);


    @PostMapping({"/getArchiveByEmployeeId"})
    JsonResult<ArchiveDTO> getArchiveByEmployeeId(@RequestBody TaskParamDTO taskParamDTO);

}
