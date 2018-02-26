package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils;

import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import com.ciicsh.gto.employeecenter.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class CommonApiUtils {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    EmployeeInfoProxy employeeInfoProxy;


    public JsonResult<EmployeeInfoDTO> getEmployeeInfo(@RequestBody EmployeeQueryDTO var1)
    {
        return  employeeInfoProxy.getEmployeeInfo(var1);
    }

}
