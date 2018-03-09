package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeCommonInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeIdQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;

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

    @Autowired
    private RestTemplate restTemplate;

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

    @GetMapping("/restTemplate")
    public JsonResult test2() throws NoSuchFieldException {

        String url = "http://10.17.2.159:6025/api/orgPolicy/find?type=3";
        JSONObject obj = restTemplate.getForObject(url, JSONObject.class);
        JSONObject data = obj.getJSONObject("data");
        JSONArray records = data.getJSONArray("records");
//        Object o = records.get(0);
//        Field name = o.getClass().getDeclaredField("name");
//        name.setAccessible(true);
//        name.get(o);
        System.out.println(obj.getInteger("total"));
        return JsonResult.success(null);
    }
}
