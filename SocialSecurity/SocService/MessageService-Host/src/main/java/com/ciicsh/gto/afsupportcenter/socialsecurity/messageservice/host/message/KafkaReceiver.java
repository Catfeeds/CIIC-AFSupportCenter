package com.ciicsh.gto.afsupportcenter.socialsecurity.messageservice.host.message;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeSocialProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.messageservice.host.enumeration.LogInfo;
import com.ciicsh.gto.afsupportcenter.socialsecurity.messageservice.host.enumeration.ProcessCategory;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpTaskFrontService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.afsupportcenter.util.logService.LogContext;
import com.ciicsh.gto.afsupportcenter.util.logService.LogService;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyPayStatusDTO;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
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
import java.util.Optional;

/**
 * Created by houwanhua on 2018/2/24
 */
@EnableBinding(value = TaskSink.class)
@Component
public class KafkaReceiver {

    @Autowired
    private SsEmpTaskService ssEmpTaskService;
    @Autowired
    private SsEmpTaskFrontService ssEmpTaskFrontService;
    @Autowired
    private SsComTaskService ssComTaskService;
    @Autowired
    private SsPaymentComService ssPaymentComService;
    @Autowired
    private AfEmployeeSocialProxy afEmployeeSocialProxy;

    @Autowired
    LogService logService;

    /**
     * 订阅社保新进任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_IN)
    public void receiveSocialNew(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //判断taskType是否是社保新进(social_new)，如果不是则无需处理
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())) {
            logService.info(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle(TaskSink.AF_EMP_IN).setTextContent(TaskSink.SOCIAL_NEW + " JSON: " + JSON.toJSONString(taskMsgDTO)));
            //获取任务单参数
            Map<String, Object> paramMap = taskMsgDTO.getVariables();
            if(Optional.ofNullable(paramMap).isPresent() && Optional.ofNullable(paramMap.get("socialType")).isPresent()){
                String socialType = paramMap.get("socialType").toString();
                //雇员服务协议ID
                String empAgreementId = taskMsgDTO.getMissionId();
                saveSsEmpTask(taskMsgDTO, Integer.parseInt(socialType), ProcessCategory.AF_EMP_IN.getCategory(), empAgreementId);
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
        //判断taskType是否是社保新进(social_stop)，如果不是则无需处理
        if (TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            logService.info(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle(TaskSink.AF_EMP_OUT).setTextContent(TaskSink.SOCIAL_STOP + " JSON: " + JSON.toJSONString(taskMsgDTO)));
            String empAgreementId = taskMsgDTO.getMissionId();
            saveSsEmpTask(taskMsgDTO, Integer.parseInt(SocialSecurityConst.TASK_TYPE_5), ProcessCategory.AF_EMP_OUT.getCategory(), empAgreementId);
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
        //判断taskType是否是社保新进(social_make_up)，如果不是则无需处理
        if (TaskSink.SOCIAL_MAKE_UP.equals(taskMsgDTO.getTaskType())) {
            logService.info(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle(TaskSink.AF_EMP_MAKE_UP).setTextContent(TaskSink.SOCIAL_MAKE_UP + " JSON: " + JSON.toJSONString(taskMsgDTO)));
            String empAgreementId = taskMsgDTO.getMissionId();
            saveSsEmpTask(taskMsgDTO, Integer.parseInt(SocialSecurityConst.TASK_TYPE_4), ProcessCategory.AF_EMP_MAKE_UP.getCategory(), empAgreementId);
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
        //获取任务单参数
        Map<String, Object> paramMap = taskMsgDTO.getVariables();
        String empAgreementId;
        //社保翻牌新进或转入
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())) {
            logService.info(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle(TaskSink.AF_EMP_COMPANY_CHANGE).setTextContent(TaskSink.SOCIAL_NEW + " JSON: " + JSON.toJSONString(taskMsgDTO)));
            if (paramMap != null && paramMap.get("socialType") != null) {
                int socialType = Integer.parseInt(paramMap.get("socialType").toString());
                empAgreementId = taskMsgDTO.getMissionId();
                //新进转入判断，任务单保存时转换成支持中心的翻牌新进&翻牌转入类型
                if (SocialSecurityConst.SOCIAL_TYPE_1 == socialType) {
                    saveSsEmpTask(taskMsgDTO, Integer.parseInt(SocialSecurityConst.TASK_TYPE_12), ProcessCategory.AF_EMP_COMPANY_CHANGE.getCategory(), empAgreementId);
                } else if (SocialSecurityConst.SOCIAL_TYPE_2 == socialType) {
                    saveSsEmpTask(taskMsgDTO, Integer.parseInt(SocialSecurityConst.TASK_TYPE_13), ProcessCategory.AF_EMP_COMPANY_CHANGE.getCategory(), empAgreementId);
                }
            }
            //翻牌转出
        } else if (TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            logService.info(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle(TaskSink.AF_EMP_COMPANY_CHANGE).setTextContent(TaskSink.SOCIAL_STOP + " JSON: " + JSON.toJSONString(taskMsgDTO)));
            empAgreementId = paramMap.get("oldEmpAgreementId").toString();
            saveSsEmpTask(taskMsgDTO, Integer.parseInt(SocialSecurityConst.TASK_TYPE_14), ProcessCategory.AF_EMP_COMPANY_CHANGE.getCategory(), empAgreementId);
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
        Map<String, Object> paramMap = taskMsgDTO.getVariables();
        //判断taskType是否是社保新进或停办(social_new或social_stop)，如果不是则无需处理
        //注：客服中心调整基数从0变非0时，收到的任务单是调整还是新开或转入，根据雇员中心传入的参数确定
        // 客服中心调整基数从非0变0时，收到的任务单就是社保停办任务单
        String socialType;
        String empAgreementId;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())) {
            logService.info(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle(TaskSink.AF_EMP_AGREEMENT_ADJUST).setTextContent(TaskSink.SOCIAL_NEW + " JSON: " + JSON.toJSONString(taskMsgDTO)));
            //获取社保办理类型
            socialType = paramMap.get("socialType").toString();
            empAgreementId = taskMsgDTO.getMissionId();
            saveSsEmpTask(taskMsgDTO, Integer.parseInt(socialType), ProcessCategory.AF_EMP_AGREEMENT_ADJUST.getCategory(), empAgreementId);
        } else if (TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            logService.info(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle(TaskSink.AF_EMP_AGREEMENT_ADJUST).setTextContent(TaskSink.SOCIAL_STOP + " JSON: " + JSON.toJSONString(taskMsgDTO)));
            socialType = SocialSecurityConst.TASK_TYPE_5;
            empAgreementId = paramMap.get("oldEmpAgreementId").toString();
            saveSsEmpTask(taskMsgDTO, Integer.parseInt(socialType), ProcessCategory.AF_EMP_AGREEMENT_ADJUST.getCategory(), empAgreementId);
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
        //判断taskType是否是社保新进或停办(social_new或social_stop)，如果不是则无需处理
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType()) || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            logService.info(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle(TaskSink.AF_EMP_AGREEMENT_UPDATE).setTextContent(" JSON: " + JSON.toJSONString(taskMsgDTO)));
            try {
                //调用接口-调用客服中心接口，获取任务单表单信息
                AfEmployeeInfoDTO dto = callEmpAgreement(Long.parseLong(taskMsgDTO.getMissionId()));
                //taskId为空，该消息则是由af客服中心发出，表示支持中心历史任务单未完成
                if (StringUtils.isBlank(taskMsgDTO.getTaskId())) {
                    Map<String, Object> paramMap = taskMsgDTO.getVariables();
                    SsEmpTaskBO ssEmpTaskBO = new SsEmpTaskBO();
                    ssEmpTaskBO.setTaskId(paramMap.get("oldTaskId").toString());
                    ssEmpTaskBO.setEmployeeId(dto.getEmployeeCompany().getEmployeeId());
                    ssEmpTaskBO.setCompanyId(dto.getEmployeeCompany().getCompanyId());
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
                    //查询旧的任务类型保存到新的任务单
                    List<SsEmpTaskBO> resList = ssEmpTaskService.queryByBusinessInterfaceId(ssEmpTaskBO);
                    if (resList.size() > 0) {
                        ssEmpTaskBO = resList.get(0);
                        taskCategory = ssEmpTaskBO.getTaskCategory();
                    }
                    ssEmpTaskFrontService.saveEmpTaskTc(taskMsgDTO, taskCategory, ProcessCategory.AF_EMP_AGREEMENT_UPDATE.getCategory(),1, dto);
                }
            } catch (Exception e) {
                logService.error(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle(TaskSink.AF_EMP_AGREEMENT_UPDATE).setTextContent(e.getMessage()));
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
        logService.info(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle(TaskSink.PAY_APPLY_PAY_STATUS_STREAM).setTextContent(" JSON: " + JSON.toJSONString(taskMsgDTO)));
        if (taskMsgDTO.getBusinessType() == 1) {
            try {
                ssPaymentComService.savePaymentInfo(taskMsgDTO.getBusinessPkId(), taskMsgDTO.getRemark(), taskMsgDTO.getPayStatus());
            } catch (Exception e) {
                logService.error(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle(TaskSink.PAY_APPLY_PAY_STATUS_STREAM).setTextContent(e.getMessage()));
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
        logService.info(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle(TaskSink.AF_COMPANY_SOCIAL_ACCOUNT_ONCE).setTextContent(" JSON: " + JSON.toJSONString(taskMsgDTO)));
        try {
            SsComTask comTask = ssComTaskService.selectById(taskMsgDTO.getMissionId());
            comTask.setTaskId(taskMsgDTO.getTaskId());
            ssComTaskService.updateById(comTask);
        } catch (Exception e) {
            logService.error(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle(TaskSink.AF_COMPANY_SOCIAL_ACCOUNT_ONCE).setTextContent(e.getMessage()));
        }
    }

    /**
     * 从接口获取数据并保存到社保雇员任务单表
     *
     * @param empAgreementId
     * @return
     */
    private AfEmployeeInfoDTO callEmpAgreement(Long empAgreementId) {
        logService.info(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle("callEmpAgreement").setTextContent("empAgreementId：" + empAgreementId));
        AfEmployeeInfoDTO afEmployeeInfoDTO = null;
        try {
//            logger.info("sleep 2000 millis start");
//            Thread.sleep(2000);
//            logger.info("sleep 2000 millis end");
            afEmployeeInfoDTO = afEmployeeSocialProxy.getByEmpAgreement(empAgreementId);
            logService.info(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle("callEmpAgreement").setTextContent("afEmployeeInfoDTO：" + JSON.toJSONString(afEmployeeInfoDTO)));
        } catch (Exception e) {
            logService.error(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle("callEmpAgreement").setTextContent(e.getMessage()));
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
    private void saveSsEmpTask(TaskCreateMsgDTO taskMsgDTO, Integer socialType, Integer processCategory, String empAgreementId) {
        try {
            AfEmployeeInfoDTO dto = callEmpAgreement(Long.parseLong(empAgreementId));
            //保存雇员任务单表数据
            ssEmpTaskFrontService.saveSsEmpTask(taskMsgDTO, socialType, processCategory, 0, dto);
        } catch (Exception e) {
            logService.error(LogContext.of().setSource(LogInfo.SOURCE.getKey()).setTitle("saveSsEmpTask").setTextContent(e.getMessage()));
        }
    }

}
