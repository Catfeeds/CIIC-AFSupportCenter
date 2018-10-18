package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeCompanyDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeQueryDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeCompanyProxy;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmEmpTaskService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmEmploymentService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmployeChange;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmEmployeChangeMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmEmployeChangeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 雇员信息变更表 服务实现类
 * </p>
 */
@Service
public class AmEmployeChangeServiceImpl extends ServiceImpl<AmEmployeChangeMapper, AmEmployeChange> implements IAmEmployeChangeService {

    private final static Logger logger = LoggerFactory.getLogger(AmEmployeChangeServiceImpl.class);

    @Autowired
    private  IAmEmpTaskService amEmpTaskService;

    @Autowired
    private CommonApiUtils employeeInfoProxy;

    @Autowired
    private LogApiUtil logApiUtil;

    @Autowired
    AfEmployeeCompanyProxy afEmployeeCompanyProxy;

    @Override
    public AmEmployeChange getEmployeeChange(Long empTaskId) {
        List<AmEmployeChange> list = baseMapper.getEmployeeChange(empTaskId);
        if(null!=list&&list.size()>0)
        {
            return  list.get(0);
        }
        return null;
    }

    @Override
    public boolean taskHireUpdate(TaskCreateMsgDTO taskMsgDTO) throws Exception {
        AmEmpTaskBO amEmpTaskBO = null;
        if(!StringUtil.isEmpty(taskMsgDTO.getTaskId()))
        {
            try {
                List<AmEmpTaskBO> taskBOList = amEmpTaskService.queryByTaskId(taskMsgDTO.getTaskId());
                amEmpTaskBO = taskBOList.get(0);
            } catch (Exception e) {

            }
        }
        if(amEmpTaskBO==null)
        {
            LogMessage logMessage = LogMessage.create().setTitle("用工信息更新").setContent("无对应的用工单");
            logApiUtil.info(logMessage);
            return  false;
        }
        Map<String,Object> param = new HashMap<>();
        param.put("empTaskId",amEmpTaskBO.getEmpTaskId());


        //TODO 调用吴敬磊接口传入taskMsgDTO.getMissionId()返回数据
        AfEmployeeInfoDTO dto = null;
        AfEmployeeCompanyDTO employeeCompany=null;
        try {
            dto = employeeInfoProxy.callInf(taskMsgDTO);
            employeeCompany = dto.getEmployeeCompany();
            Map<String,Object> variables = taskMsgDTO.getVariables();

            if(variables.containsKey("sender_client"))
            {
                AmEmployeChange amEmployeChange = new AmEmployeChange();
                amEmployeChange.setEmpTaskId(amEmpTaskBO.getEmpTaskId());
                amEmployeChange.setType(variables.get("operType").toString());
                amEmployeChange.setCreatedTime(LocalDateTime.now());
                if("all".equals(variables.get("operType")))
                {
                    amEmployeChange.setLaborStartDate(employeeCompany.getLaborStartDate());
                    amEmployeChange.setLaborEndDate(employeeCompany.getLaborEndDate());
                    amEmployeChange.setInDate(employeeCompany.getInDate());
                }
                if("inDate".equals(variables.get("operType")))
                {
                    amEmployeChange.setInDate(employeeCompany.getInDate());
                }
                if("laborDate".equals(variables.get("operType")))
                {
                    amEmployeChange.setLaborStartDate(employeeCompany.getLaborStartDate());
                    amEmployeChange.setLaborEndDate(employeeCompany.getLaborEndDate());
                }
                baseMapper.insert(amEmployeChange);

                return  true;
            }

        } catch (Exception e) {
            try {
                LogMessage logMessage = LogMessage.create().setTitle("用工信息更新").setContent(taskMsgDTO.getTaskId()+e.getMessage());
                logApiUtil.info(logMessage);
            } catch (Exception e1) {

            }
            logger.error(e.getMessage(), e);
        }

        return false;

    }

    @Override
    public boolean taskFireUpdate(TaskCreateMsgDTO taskMsgDTO) throws Exception {
        AmEmpTaskBO amEmpTaskBO = null;
        if(!StringUtil.isEmpty(taskMsgDTO.getTaskId()))
        {
            List<AmEmpTaskBO> taskBOList = amEmpTaskService.queryByTaskId(taskMsgDTO.getTaskId());
            amEmpTaskBO = taskBOList.get(0);
        }

        //TODO 调用吴敬磊接口传入taskMsgDTO.getMissionId()返回数据
        AfEmployeeInfoDTO dto = null;
        AfEmployeeCompanyDTO employeeCompany = null;
        try {
            AfEmployeeQueryDTO taskRequestDTO = new AfEmployeeQueryDTO();
            taskRequestDTO.setEmpAgreementId(Long.parseLong(taskMsgDTO.getMissionId()));
            dto = afEmployeeCompanyProxy.getEmployeeCompany(taskRequestDTO);
            employeeCompany = dto.getEmployeeCompany();
            Map<String,Object> variables = taskMsgDTO.getVariables();
            if(variables.containsKey("sender_client"))
            {
                AmEmployeChange amEmployeChange = new AmEmployeChange();
                amEmployeChange.setEmpTaskId(amEmpTaskBO.getEmpTaskId());
                amEmployeChange.setType(variables.get("operType").toString());
                amEmployeChange.setCreatedTime(LocalDateTime.now());
                if("all".equals(variables.get("operType")))
                {
                    amEmployeChange.setOutReason(ReasonUtil.getReasonOut(employeeCompany.getOutReason().toString()));
                    amEmployeChange.setOutDate(employeeCompany.getOutDate());
                }
                if("reason".equals(variables.get("operType")))
                {
                    amEmployeChange.setOutReason(ReasonUtil.getReasonOut(employeeCompany.getOutReason().toString()));
                }
                if("time".equals(variables.get("operType")))
                {
                    amEmployeChange.setOutDate(employeeCompany.getOutDate());
                }
                baseMapper.insert(amEmployeChange);

                return  true;
            }

        }catch (Exception e){
            LogMessage logMessage = LogMessage.create().setTitle("离职原因更新").setContent(e.getMessage());
            logApiUtil.error(logMessage);
        }

        return false;
    }
}
