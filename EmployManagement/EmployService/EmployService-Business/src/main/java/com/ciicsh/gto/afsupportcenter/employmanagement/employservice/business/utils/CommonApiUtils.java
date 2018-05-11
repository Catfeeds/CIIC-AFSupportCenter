package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils;



import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeQueryDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeCompanyProxy;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.SMUserInfoProxy;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMUserInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.*;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.CompanyTypeDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.CompanyProxy;
import com.ciicsh.gto.sheetservice.api.SheetServiceProxy;
import com.ciicsh.gto.sheetservice.api.dto.Result;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import com.ciicsh.gto.sheetservice.api.dto.request.TaskRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
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

    @Autowired
    private CompanyProxy companyProxy;

    @Autowired
    private SMUserInfoProxy smUserInfoProxy;


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
            if("fire".equals(taskMsgDTO.getTaskType()))
            {
                taskRequestDTO.setEmpAgreementId(Long.parseLong(variables.get("oldEmpAgreementId").toString()));
            }else{
                taskRequestDTO.setEmpAgreementId(Long.parseLong(taskMsgDTO.getMissionId()));
            }
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
        Result restResult = sheetServiceProxy.completeTask(requestDTO);
        logger.info("customer系统收到完成任务接口返回：" + String.valueOf("code:" + restResult.getCode() + "message:") + restResult.getMessage());
        return restResult;
    }

    public AfEmployeeInfoDTO callInfByMissId(TaskCreateMsgDTO taskMsgDTO) {
        AfEmployeeInfoDTO resDto = null;
        try {
            AfEmployeeQueryDTO taskRequestDTO = new AfEmployeeQueryDTO();
            String missionId = taskMsgDTO.getMissionId();
            taskRequestDTO.setEmpAgreementId(Long.parseLong(missionId));
            resDto = afEmployeeCompanyProxy.getEmployeeCompany(taskRequestDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return resDto;
    }

    public CompanyTypeDTO  getCompanyType(String companyId){
        CompanyTypeDTO companyTypeDTO = null;
        try {
            com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<CompanyTypeDTO> companyTypeDTOJsonResult = companyProxy.getCompanyType(companyId);
            companyTypeDTO = companyTypeDTOJsonResult.getObject();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return  companyTypeDTO;
    }

    public AfCompanyDetailResponseDTO  getCompanyDetail(String companyId){
        AfCompanyDetailResponseDTO afCompanyDetailResponseDTO = null;
        try {
            com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<AfCompanyDetailResponseDTO>  afCompanyDetailResponseDTOJsonResult = companyProxy.afDetail(companyId);
            afCompanyDetailResponseDTO = afCompanyDetailResponseDTOJsonResult.getObject();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return  afCompanyDetailResponseDTO;
    }

    public SMUserInfoDTO  getUserInfo(String userId){

        List<SMUserInfoDTO> smUserInfoDTOList = null;
        try {
            com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.JsonResult<List<SMUserInfoDTO>> listJsonResult = smUserInfoProxy.getUsersByUserId(userId);
            smUserInfoDTOList = listJsonResult.getData();
            if(null!=smUserInfoDTOList&&smUserInfoDTOList.size()>0)
            {
                SMUserInfoDTO smUserInfoDTO = smUserInfoDTOList.get(0);
                return smUserInfoDTO;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
      return  null;
    }


}
