package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api;

import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by houwanhua on 2018/3/8.
 */
@FeignClient("support-center-soc-api-service")
@RequestMapping("/api/soc")
public interface SocApiProxy {
    /**
     * 保存企业任务单
     *
     * @param ssComTaskDTO
     * @return
     */
    @PostMapping("/saveComTask")
    JsonResult saveComTask(@RequestBody SsComTaskDTO ssComTaskDTO);

    /**
     * 获取企业社保账户信息表
     *
     * @param paramDto
     * @return
     */
    @PostMapping("/getAccountList")
    JsonResult<List<SsComAccountDTO>> getComAccountList(@RequestBody SsComAccountParamDTO paramDto);


    @PostMapping("/getAccountByCompany")
    JsonResult<ComAccountExtDTO> getAccountByCompany(@RequestBody ComTaskParamDTO paramDTO);

    @PostMapping("/getSsEmpInfo")
    JsonResult<List<SsEmpInfoDTO>> getSsEmpInfo(@RequestBody List<SsEmpInfoParamDTO> paramDTOList);

    /**
     * 接口调用方：雇员中心
     * 根据客户Id获取客户社保账户信息
     * @param companyId
     * @return
     */
    @PostMapping("/getSsComAccountByComId")
    JsonResult<SsComAccountDTO> getSsComAccountByComId(@RequestParam("companyId")String companyId);


    /**
     * 接口调用方：雇员中心
     * 根据客户Id和雇员Id获取雇员社保信息
     * @param companyId
     * @param employeeId
     * @return
     */
    @PostMapping("/getSsEmpInfoById")
    JsonResult<SsEmpInfoDTO> getSsEmpInfoById(@RequestParam("companyId")String companyId,@RequestParam("employeeId")String employeeId);

}
