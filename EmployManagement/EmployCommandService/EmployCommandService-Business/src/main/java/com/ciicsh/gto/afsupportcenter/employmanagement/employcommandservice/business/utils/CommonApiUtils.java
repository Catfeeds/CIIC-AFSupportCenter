package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils;



import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeQueryDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeCompanyProxy;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.*;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import com.ciicsh.gto.employeecenter.util.JsonResult;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
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

    @Autowired
    AfEmployeeCompanyProxy afEmployeeCompanyProxy;


    public  JsonResult<EmployeeInfoDTO> getEmployeeInfo(@RequestBody EmployeeQueryDTO var1){
       return employeeInfoProxy.getEmployeeInfo(var1);
    }

    public JsonResult<EmployeeHireInfoDTO> getEmployeeHireInfo(@RequestBody EmployeeHireInfoQueryDTO var1){
        return  employeeInfoProxy.getEmployeeHireInfo(var1);
    }

    /**
     * 获取雇员信息api
     *
     * @param taskMsgDTO
     * @return
     */
    public AfEmployeeInfoDTO callInf(TaskCreateMsgDTO taskMsgDTO) {
        AfEmployeeInfoDTO resDto = null;
        try {
            AfEmployeeQueryDTO taskRequestDTO = new AfEmployeeQueryDTO();
            String missionId = taskMsgDTO.getMissionId();
            taskRequestDTO.setEmpAgreementId(Long.parseLong(missionId));
            resDto = afEmployeeCompanyProxy.getEmployeeCompany(taskRequestDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resDto;
    }

}
