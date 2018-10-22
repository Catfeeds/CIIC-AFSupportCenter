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
     * 接口调用方：客服中心
     * 保存企业任务单
     *
     * @param ssComTaskDTO
     * @return
     */
    @PostMapping("/saveComTask")
    JsonResult saveComTask(@RequestBody SsComTaskDTO ssComTaskDTO);

    /**
     * 接口调用方：客服中心
     * 获取企业社保账户信息表
     *
     * @param paramDto
     * @return
     */
    @PostMapping("/getAccountList")
    JsonResult<List<SsComAccountDTO>> getComAccountList(@RequestBody SsComAccountParamDTO paramDto);

    /**
     * 接口调用方：客服中心
     * 根据参数公司ID获取社保账户信息
     * @return
     */
    @PostMapping("/getAccountByCompany")
    JsonResult<ComAccountExtDTO> getAccountByCompany(@RequestBody ComTaskParamDTO paramDTO);

    /**
     * 接口调用方：薪酬结算中心
     * @param paramDTOList
     * @return
     */
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

    /**
     * 接口调用方：雇员中心
     * 根据客户Id和雇员Id获取雇员已办理的社保信息
     * @param companyId
     * @param employeeId
     * @return
     */
    @PostMapping("/getSocialSecurity")
    JsonResult<SocialSecurityDTO> getSocialSecurity(@RequestParam("companyId")String companyId,@RequestParam("employeeId")String employeeId);

    /**
     * 接口调用方：雇员中心
     * 根据客户Id和雇员Id及年份获取雇员该年的社保变更记录
     * @param companyId
     * @param employeeId
     * @param year
     * @return
     */
    @PostMapping("/getSocialSecurityChangeInformation")
    JsonResult<List<SocialSecurityChangeInformationDTO>> getSocialSecurityChangeInformation(@RequestParam("companyId")String companyId,
                                                                                      @RequestParam("employeeId")String employeeId,
                                                                                      @RequestParam("year")String year);

    /**
     * 接口调用方：雇员中心
     * 根据客户Id，雇员Id获取雇员的社保信息
     * @param companyId
     * @param employeeId
     * @return
     */
    @PostMapping("/getEmpSocialSecurityInfo")
    JsonResult<EmpSocialSecurityInfoDTO> getEmpSocialSecurityInfo(@RequestParam("companyId")String companyId,
                                                                  @RequestParam("employeeId")String employeeId);

    /**
     * 接口调用方：证件管理中心
     * 根据客户Id，雇员Id获取雇员的社保福利段信息
     * @param companyId
     * @param employeeId
     * @return
     */
    @PostMapping("/getEmpBasePeriodInfo")
    JsonResult<List<SsEmpBasePeriodDTO>> getEmpBasePeriodInfo(@RequestParam("companyId")String companyId,
                                                        @RequestParam("employeeId")String employeeId);


    /**
     * 接口调用方：雇员中心 -> 雇员管理—>雇员查询—>详情-->社保公积金调整->社保调整->养老金任务单
     * 参数：前道的入离职ID emp_company_id
     */
    @PostMapping("/apiGetSsEmpTaskByTaskId")
    JsonResult<SsEmpTaskArchiveDTO> apiGetSsEmpTaskByTaskId(@RequestParam("taskId")String taskId);

    /**
     * 接口调用方：雇员中心 -> 雇员管理—>雇员查询—>详情-->社保公积金调整->社保调整->养老金基本信息
     * 参数：前道的入离职ID emp_company_id
     */
    @PostMapping("/apiGetSsEmpArchiveByEmpCompanyId")
    JsonResult<SsEmpTaskArchiveDTO> apiGetSsEmpArchiveByEmpCompanyId(@RequestParam("empCompanyId")String empCompanyId);

    /**
     * 获取工伤比例信息
     * 接口调用方：客服中心
     * 参数：客户编号、年月
     */
    @PostMapping("/getSsComRatioByDate")
    JsonResult<SsComRatioDTO> getSsComRatioByDate(@RequestParam("companyId")String companyId, @RequestParam("date")String date);

}
