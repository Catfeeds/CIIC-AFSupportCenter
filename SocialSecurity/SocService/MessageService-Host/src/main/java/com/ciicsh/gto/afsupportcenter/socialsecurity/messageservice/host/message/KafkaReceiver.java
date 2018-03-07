package com.ciicsh.gto.afsupportcenter.socialsecurity.messageservice.host.message;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpSocialDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeQueryDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeSocialQueryDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeCompanyProxy;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeSocialProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpTaskFrontService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyPayStatusDTO;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by houwanhua on 2018/2/24
 */
@EnableBinding(value = TaskSink.class)
@Component
public class KafkaReceiver {
    private final static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    @Autowired
    private SsEmpTaskService ssEmpTaskService;
    @Autowired
    private SsEmpTaskFrontService ssEmpTaskFrontService;
    @Autowired
    private SsComTaskService ssComTaskService;
    @Autowired
    private SsPaymentComService ssPaymentComService;
    @Autowired
    private AfEmployeeCompanyProxy afEmployeeCompanyProxy;

    @Autowired
    private AfEmployeeSocialProxy afEmployeeSocialProxy;

    /**
     * 订阅社保新进任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_IN)
    public void receiveSocialNew(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        logger.info("receiveSocialNew: " + JSON.toJSONString(taskMsgDTO));
        //判断taskType是否是社保新进(social_new)，如果不是则无需处理
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())) {
            //获取任务单参数
            Map<String, Object> paramMap = taskMsgDTO.getVariables();
            if (paramMap != null && paramMap.get("socialType") != null) {
                String socialType = paramMap.get("socialType").toString();
                saveSsEmpTask(taskMsgDTO, Integer.parseInt(socialType));
            }
        }
    }

    /**
     * 订阅雇员终止任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_OUT)
    public void receiveEmpOut(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        logger.info("receiveEmpOut: " + JSON.toJSONString(taskMsgDTO));
        //判断taskType是否是社保新进(social_stop)，如果不是则无需处理
        if (TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            saveSsEmpTask(taskMsgDTO, Integer.parseInt(SocialSecurityConst.TASK_TYPE_5));
        }
    }

    /**
     * 订阅雇员补缴任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_MAKE_UP)
    public void receiveChargeResume(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        logger.info("entering receiveChargeResume: " + JSON.toJSONString(taskMsgDTO));
        //判断taskType是否是社保新进(social_make_up)，如果不是则无需处理
        if (TaskSink.SOCIAL_MAKE_UP.equals(taskMsgDTO.getTaskType())) {
            saveSsEmpMakeUp(taskMsgDTO,Integer.parseInt(SocialSecurityConst.TASK_TYPE_4));
        }
    }

     /**
     * 订阅雇员翻牌任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_COMPANY_CHANGE)
    public void receiveEmpCompanyChange(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //社保翻牌新进或转入
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())) {
            //获取任务单参数
            Map<String, Object> paramMap = taskMsgDTO.getVariables();
            if (paramMap != null && paramMap.get("socialType") != null) {
                int socialType = Integer.parseInt(paramMap.get("socialType").toString());
                //新进转入判断，任务单保存时转换成支持中心的翻牌新进&翻牌转入类型
                if (SocialSecurityConst.SOCIAL_TYPE_1 == socialType) {
                    saveSsEmpTask(taskMsgDTO, Integer.parseInt(SocialSecurityConst.TASK_TYPE_12));
                } else if (SocialSecurityConst.SOCIAL_TYPE_2 == socialType) {
                    saveSsEmpTask(taskMsgDTO, Integer.parseInt(SocialSecurityConst.TASK_TYPE_13));
                }
            }
            logger.info("receiveEmpCompanyChange: " + JSON.toJSONString(taskMsgDTO));
            //翻牌转出
        } else if (TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            saveSsEmpTask(taskMsgDTO, Integer.parseInt(SocialSecurityConst.TASK_TYPE_14));
            logger.info("receiveEmpCompanyChange: " + JSON.toJSONString(taskMsgDTO));
        }
    }


    /**
     * 订阅雇员服务协议调整任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_AGREEMENT_ADJUST)
    public void receiveAdjust(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        logger.info("receiveAdjust: " + JSON.toJSONString(taskMsgDTO));
        //判断taskType是否是社保新进或停办(social_new或social_stop)，如果不是则无需处理
        //注：只有当社保缴纳地从上海变成外地时，才会收到social_stop类型，其他都是social_new
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType()) || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            saveSsEmpTask(taskMsgDTO, Integer.parseInt(SocialSecurityConst.TASK_TYPE_3));
        }
    }

    /**
     * 订阅雇员服务协议更正任务单
     *
     * @param message
     * @return
     */
    @StreamListener(TaskSink.AF_EMP_AGREEMENT_UPDATE)
    public void receiveUpdate(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        logger.info("entering receiveUpdate:" + JSON.toJSONString(taskMsgDTO));
        //判断taskType是否是社保新进或停办(social_new或social_stop)，如果不是则无需处理
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType()) || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            try {
                //调用接口-调用客服中心接口，获取任务单表单信息
                AfEmployeeInfoDTO dto = callEmpAgreement(taskMsgDTO);
                //taskId为空，该消息则是由af客服中心发出，表示支持中心历史任务单未完成
                if (StringUtils.isBlank(taskMsgDTO.getTaskId())) {
                    Map<String, Object> paramMap = taskMsgDTO.getVariables();
                    SsEmpTaskBO ssEmpTaskBO = new SsEmpTaskBO();
                    ssEmpTaskBO.setTaskId(paramMap.get("oldTaskId").toString());
                    ssEmpTaskBO.setEmployeeId(paramMap.get("employeeId").toString());
                    ssEmpTaskBO.setCompanyId(paramMap.get("companyId").toString());
                    List<SsEmpTaskBO> resList = ssEmpTaskService.queryByTaskId(ssEmpTaskBO);
                    //如果查询到历史任务单，则直接进行更新操作
                    if (resList != null && resList.size() > 0) {
                        ssEmpTaskFrontService.updateEmpTaskTc(taskMsgDTO, dto);
                    }
                    //已办理任务单
                } else {//taskId不为空，该kafka则是由任务单中心发出，表示支持中心历史任务单已完成
                    Integer taskCategory = 0;
                    Map<String, Object> paramMap = taskMsgDTO.getVariables();
                    SsEmpTaskBO ssEmpTaskBO = new SsEmpTaskBO();
                    ssEmpTaskBO.setBusinessInterfaceId(paramMap.get("oldEmpAgreementId").toString());
                    ssEmpTaskBO.setEmployeeId(paramMap.get("employeeId").toString());
                    ssEmpTaskBO.setCompanyId(paramMap.get("companyId").toString());

                    //查询旧的任务类型保存到新的任务单
                    List<SsEmpTaskBO> resList = ssEmpTaskService.queryByBusinessInterfaceId(ssEmpTaskBO);
                    if (resList.size() > 0) {
                        ssEmpTaskBO = resList.get(0);
                        taskCategory = ssEmpTaskBO.getTaskCategory();
                    }
                    ssEmpTaskFrontService.saveEmpTaskTc(taskMsgDTO, taskCategory, 1, dto);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 订阅财务付款申请回调任务单
     *
     * @param message
     * @return
     */
    @StreamListener(TaskSink.PAY_APPLY_PAY_STATUS_STREAM)
    public void applyFinancePayment(Message<PayApplyPayStatusDTO> message) {
        PayApplyPayStatusDTO taskMsgDTO = message.getPayload();
        logger.info("start applyFinancePayment:" + JSON.toJSONString(taskMsgDTO));
        if (taskMsgDTO.getBusinessType() == 1) {
            try {
                ssPaymentComService.savePaymentInfo(taskMsgDTO.getBusinessPkId(), taskMsgDTO.getRemark(), taskMsgDTO.getPayStatus());
                logger.info("end applyFinancePayment:" + JSON.toJSONString(taskMsgDTO));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 订阅客服中心调用更新企业任务单
     *
     * @param message
     * @return
     */
    @StreamListener(TaskSink.AF_COMPANY_SOCIAL_ACCOUNT_ONCE)
    public void updateComTask(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        logger.info("start updateComTask: " + JSON.toJSONString(taskMsgDTO));
        try {
            SsComTask comTask = ssComTaskService.selectById(taskMsgDTO.getMissionId());
            comTask.setTaskId(taskMsgDTO.getTaskId());
            ssComTaskService.updateById(comTask);
            logger.info("end updateComTask: " + JSON.toJSONString(comTask));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

//    /**
//     * 从接口获取数据并保存到社保雇员任务单表
//     *
//     * @param taskCreateMsgDTO
//     * @return
//     */
//    private AfEmployeeInfoDTO callEmployeeCompany(TaskCreateMsgDTO taskCreateMsgDTO) {
//        logger.info("entering callEmployeeCompany：" + JSON.toJSONString(taskCreateMsgDTO));
//        AfEmployeeInfoDTO afEmployeeInfoDTO = null;
//        try {
//            AfEmployeeQueryDTO taskRequestDTO = new AfEmployeeQueryDTO();
//            taskRequestDTO.setEmpAgreementId(Long.parseLong(taskCreateMsgDTO.getMissionId()));
//            afEmployeeInfoDTO = afEmployeeSocialProxy.getByEmpAgreement(Long.parseLong(taskCreateMsgDTO.getMissionId()));
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return afEmployeeInfoDTO;
//    }

    /**
     * 从接口获取数据并保存到社保雇员任务单表
     *
     * @param taskCreateMsgDTO
     * @return
     */
    private AfEmployeeInfoDTO callEmpAgreement(TaskCreateMsgDTO taskCreateMsgDTO) {
        logger.info("entering callEmployeeCompany：" + JSON.toJSONString(taskCreateMsgDTO));
        AfEmployeeInfoDTO afEmployeeInfoDTO = null;
        try {
            AfEmployeeQueryDTO taskRequestDTO = new AfEmployeeQueryDTO();
            taskRequestDTO.setEmpAgreementId(Long.parseLong(taskCreateMsgDTO.getMissionId()));
            afEmployeeInfoDTO = afEmployeeSocialProxy.getByEmpAgreement(Long.parseLong(taskCreateMsgDTO.getMissionId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return afEmployeeInfoDTO;
    }

    /**
     * 从接口获取数据并保存到社保雇员任务单表
     *
     * @param taskMsgDTO
     * @param socialType
     * @return
     */
    private void saveSsEmpTask(TaskCreateMsgDTO taskMsgDTO, Integer socialType) {
        try {
            //调用客服中心接口获取任务单表单信息
            AfEmployeeInfoDTO dto = callEmpAgreement(taskMsgDTO);
            //保存雇员任务单表数据
            ssEmpTaskFrontService.saveSsEmpTask(taskMsgDTO, socialType, 0, dto);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 从接口获取数据并保存到社保雇员任务单表
     *
     * @param taskMsgDTO
     * @param socialType
     * @return
     */
    private void saveSsEmpMakeUp(TaskCreateMsgDTO taskMsgDTO, Integer socialType) {
        try {
            logger.info("entering saveSsEmpMakeUp");
            //调用客服中心接口获取任务单表单信息
            AfEmployeeInfoDTO dto = callEmpAgreement(taskMsgDTO);
            //保存雇员任务单表数据
            ssEmpTaskFrontService.saveSsEmpTask(taskMsgDTO, socialType, 0, dto);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
