package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeCommonInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeIdQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 16:22 2018/3/1
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private EmployeeInfoProxy employeeInfoProxy;

    @GetMapping("/getId")
    public void test(){
        EmployeeIdQueryDTO employeeIdQueryDTO = new EmployeeIdQueryDTO();
        EmployeeCommonInfoDTO employeeInfoDTO = employeeInfoProxy.getEmployeeCommon(employeeIdQueryDTO).getData();
        System.out.println(employeeInfoDTO.getEmployeeId());
    }

    @GetMapping("/getemp")
    public JsonResult test1(){
        EmployeeIdQueryDTO employeeIdQueryDTO = new EmployeeIdQueryDTO();
        employeeIdQueryDTO.setIdNum("213");
        employeeIdQueryDTO.setIdCardType(2);
        EmployeeCommonInfoDTO employeeInfoDTO = employeeInfoProxy.getEmployeeCommon(employeeIdQueryDTO).getData();
        return JsonResult.success(employeeIdQueryDTO);
    }
}
