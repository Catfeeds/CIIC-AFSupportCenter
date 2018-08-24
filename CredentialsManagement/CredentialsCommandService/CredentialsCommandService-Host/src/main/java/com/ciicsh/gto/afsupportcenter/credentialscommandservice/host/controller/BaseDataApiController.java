package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import com.ciicsh.gto.basicdataservice.api.CountryServiceProxy;
import com.ciicsh.gto.basicdataservice.api.DicItemServiceProxy;
import com.ciicsh.gto.basicdataservice.api.dto.CountryDTO;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeHireInfoQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeInfoForCredentialsDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.CompanyProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.List;

/**
 * @Author: guwei
 * @Description: 基础数据控制器
 * @Date: Created in 11:18 2018/2/13
 */
@RestController
@RequestMapping("/api/baseData")
public class BaseDataApiController {

    @Autowired
    private CountryServiceProxy countryServiceProxy;

    @Autowired
    private EmployeeInfoProxy employeeInfoProxy;

    @Autowired
    private CompanyProxy companyProxy;

    @Autowired
    private DicItemServiceProxy dicItemServiceProxy;

    /**
     * 获取国家字典
     * @return
     */
    @GetMapping("/getCountry")
    public JsonResult getCountry(){
        List<CountryDTO> countryDTOS = countryServiceProxy.listAll();
        return JsonResult.success(countryDTOS);
    }

    /**
     * 从根据字典项value获得字典列表
     *
     * @param dicValue
     * @return
     */
    @GetMapping("/dic/{dicValue}")
    public List<DicItemDTO> listDicItemByDicValue(@PathVariable("dicValue") String dicValue) {
        return dicItemServiceProxy.listByDicValue(dicValue);
    }

    /**
     * 查询雇员详情
     * @param companyId
     * @param employeeId
     * @return
     */
    @GetMapping("/getEmpInfo")
    public JsonResult getEmpInfo(String companyId,String employeeId){
        EmployeeHireInfoQueryDTO employeeHireInfoQueryDTO = new EmployeeHireInfoQueryDTO();
        employeeHireInfoQueryDTO.setEmployeeId(employeeId);
        employeeHireInfoQueryDTO.setCompanyId(companyId);
        EmployeeInfoForCredentialsDTO data = employeeInfoProxy.getEmployeeInfoForCredentials(employeeHireInfoQueryDTO).getData();
        return JsonResult.success(data);
    }

    /**
     * 查询客户详情
     * @param companyId
     * @return
     */
    @GetMapping("/getCompanyInfo")
    public JsonResult getCompanyInfo(String companyId){
        AfCompanyDetailResponseDTO afCompanyInfo = companyProxy.afDetail(companyId).getObject();
        return JsonResult.success(afCompanyInfo);
    }
}
