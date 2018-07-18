package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api;

import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by houwanhua on 2018/2/27.
 */
@FeignClient("support-center-housefund-api-service")
@RequestMapping("/api/fund")
public interface FundApiProxy {
    /**
     * 获取企业公积金账户信息表
     * @param paramDto
     * @return
     */
    @PostMapping("/getAccountList")
    JsonResult<List<HfComAccountDTO>> getComAccountList(@RequestBody HfComAccountParamDTO paramDto);

    /**
     * 接口调用方：客服中心
     * 保存企业任务单
     * @param comTaskDTO
     * @return
     */
    @PostMapping("/saveComTask")
    JsonResult saveComTask(@RequestBody HfComTaskDTO comTaskDTO);


    /**
     * 接口调用方：客服中心
     * 根据公司ID和公积金类别获取公积金账户信息
     * @param companyId 公司ID
     * @return
     */
    @GetMapping("/getAccountByCompany")
    JsonResult<HfComAccountExtDTO> getAccountByCompany(@RequestParam("companyId") String companyId);

    /**
     * 接口调用方：薪酬结算中心
     *
     * @param paramDTOList
     * @return
     */
    @PostMapping("/getHfEmpInfo")
    JsonResult<List<HfEmpInfoDTO>> getHfEmpInfo(@RequestBody List<HfEmpInfoParamDTO> paramDTOList);

    /**
     * 接口调用方：雇员中心
     * 根据客户Id获取客户公积金账户信息
     * @param companyId
     * @return
     */
    @PostMapping("/getHfComAccountByComId")
    JsonResult<HfComAccountDTO> getHfComAccountByComId(@RequestParam("companyId") String companyId);

    /**
     * 接口调用方：雇员中心
     * 根据客户Id和雇员Id获取雇员公积金信息
     * @param companyId
     * @param employeeId
     * @return
     */
    @PostMapping("/getHfEmpInfoById")
    JsonResult<HfEmpInfoDTO> getHfEmpInfoById(@RequestParam("companyId")String companyId,@RequestParam("employeeId")String employeeId);

}
