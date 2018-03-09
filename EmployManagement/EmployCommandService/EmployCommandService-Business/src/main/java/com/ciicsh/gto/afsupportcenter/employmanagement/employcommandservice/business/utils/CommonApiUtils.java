package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils;



import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeQueryDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeCompanyProxy;
import com.ciicsh.gto.commonservice.util.dto.Result;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.*;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import com.ciicsh.gto.employeecenter.util.JsonResult;
import com.ciicsh.gto.sheetservice.api.SheetServiceProxy;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import com.ciicsh.gto.sheetservice.api.dto.request.TaskRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;


@Component
public class CommonApiUtils {

    private final static Logger logger = LoggerFactory.getLogger(CommonApiUtils.class);

    @Autowired
    SheetServiceProxy sheetServiceProxy;

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
            Map<String, Object> variables = taskMsgDTO.getVariables();
            taskRequestDTO.setEmployeeCompanyId(Long.parseLong(variables.get("empCompanyId").toString()));
            resDto = afEmployeeCompanyProxy.getEmployeeCompany(taskRequestDTO);
        } catch (Exception e) {
            logger.error("call kehuzhongxin jiekou error!");
            logger.error(e.getMessage(),e);
        }
        return resDto;
    }

    /**
     * 调用客服中心的完成任务接口
     * @param requestDTO
     * @return
     */
    public Result completeTask(@RequestBody TaskRequestDTO requestDTO) throws Exception {
        logger.info("customer系统调用完成任务接口：" + requestDTO.toString());
        com.ciicsh.gto.commonservice.util.dto.Result restResult = sheetServiceProxy.completeTask(requestDTO);
        logger.info("customer系统收到完成任务接口返回：" + String.valueOf("code:" + restResult.getCode() + "message:") + restResult.getMessage());
        return restResult;
    }


}
