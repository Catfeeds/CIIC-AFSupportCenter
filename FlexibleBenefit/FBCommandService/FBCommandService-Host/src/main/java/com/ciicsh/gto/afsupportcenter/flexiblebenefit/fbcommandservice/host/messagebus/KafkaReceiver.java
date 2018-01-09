package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.messagebus;

import com.ciicsh.gto.sheetservice.api.dto.TaskMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Created by songjt on 17/12/18.
 */
@EnableBinding(value = TaskSink.class)
@Component
public class KafkaReceiver {
    private final static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    @StreamListener(TaskSink.AF_EMP_IN)
    public void receiveEmpIn(Message<TaskMsgDTO> message){
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from AF_EMP_IN-useWork: " + returnInfo);
    }

    @StreamListener(TaskSink.AF_EMP_OUT)
    public void receiveEmpOut(Message<TaskMsgDTO> message){
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from AF_EMP_OUT-useWork: " + returnInfo);
    }
    @StreamListener(TaskSink.CHARGE_RESUME)
    public void receiveChargeResume(Message<TaskMsgDTO> message){
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from CHARGE_RESUME-useWork: " + returnInfo);
    }
    @StreamListener(TaskSink.CHARGE_STOP)
    public void receiveChargeStop(Message<TaskMsgDTO> message){
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from CHARGE_STOP-useWork: " + returnInfo);
    }
    @StreamListener(TaskSink.EMP_COMPANY_CHANGE)
    public void receiveEmpCompanyChange(Message<TaskMsgDTO> message){
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from EMP_COMPANY_CHANGE-useWork: " + returnInfo);
    }
    @StreamListener(TaskSink.PRE_IN)
    public void receivePreIn(Message<TaskMsgDTO> message){
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from PRE_IN-useWork: " + returnInfo);
    }
    @StreamListener(TaskSink.NONLOCAL_TO_SH)
    public void receiveNonlocalToSh(Message<TaskMsgDTO> message){
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from NONLOCAL_TO_SH-useWork: " + returnInfo);
    }
    @StreamListener(TaskSink.SH_TO_NONLOCAL)
    public void receiveSh(Message<TaskMsgDTO> message){
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from SH_TO_NONLOCAL-useWork: " + returnInfo);
    }

    @StreamListener(TaskSink.GIFT_APPLY)
    public void giftApply(Message<TaskMsgDTO> message){
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from GIFT_APPLY-useWork: " + returnInfo);
    }

}
