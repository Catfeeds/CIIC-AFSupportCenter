package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.messagebus;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordDetailPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApprovalStepPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApplyRecordDetailCommandService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApprovalStepCommandService;
import com.ciicsh.gto.sheetservice.api.dto.TaskMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author songjt
 * @date 17/12/18
 */
@EnableBinding(value = TaskSink.class)
@Component
public class KafkaReceiver {
    private final static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    private static final String CENTER = "centerMangerAudit";
    @Autowired
    private ApprovalStepCommandService approvalStepCommandService;
    @Autowired
    private ApplyRecordDetailCommandService applyRecordDetailCommandService;

    /**
     * 礼品申请、审批任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.GIFT_APPLY)
    public void giftApply(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from GIFT_APPLY-useWork: " + returnInfo);
        updateStepList(taskMsgDTO);
    }

    /**
     * 弹性福利更新审批公共方法
     *
     * @param taskMsgDTO
     */
    private void updateStepList(TaskMsgDTO taskMsgDTO) {
        if (CENTER.equals(taskMsgDTO.getTaskType())) {
            ApplyRecordDetailPO applyRecordDetailPO = new ApplyRecordDetailPO();
            applyRecordDetailPO.setApplyRecordDetailId(Integer.valueOf(taskMsgDTO.getMissionId()));
            applyRecordDetailPO.setTaskId(taskMsgDTO.getTaskId());
            /**更新详情表记录*/
            applyRecordDetailCommandService.updateById(applyRecordDetailPO);
        }
        /**
         * taskType,查询审批人，新增审批记录
         */
        String positionCode = taskMsgDTO.getTaskType();

        List<ApprovalStepPO> approvalStepList = approvalStepCommandService.selectList(Integer.valueOf(taskMsgDTO.getMissionId()));
        if (approvalStepList.size() == 1) {
            approvalStepList.get(0).setApproveName("xwz");
            approvalStepList.get(0).setApproverId("xwz");
            approvalStepCommandService.updateById(approvalStepList.get(0));
        } else if (approvalStepList.size() == 2) {
            approvalStepList.get(1).setApproveName("xwz");
            approvalStepList.get(1).setApproverId("xwz");
            approvalStepCommandService.updateById(approvalStepList.get(1));
        }
    }

    /**
     * 活动申请、审批任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.MARKETING_APPLY)
    public void marketApply(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from MARKETING_APPLY-useWork: " + returnInfo);

        updateStepList(taskMsgDTO);
    }

    @StreamListener(TaskSink.AF_EMP_IN)
    public void receiveEmpIn(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from AF_EMP_IN-useWork: " + returnInfo);
    }

    @StreamListener(TaskSink.AF_EMP_OUT)
    public void receiveEmpOut(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from AF_EMP_OUT-useWork: " + returnInfo);
    }

    @StreamListener(TaskSink.CHARGE_RESUME)
    public void receiveChargeResume(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from CHARGE_RESUME-useWork: " + returnInfo);
    }

    @StreamListener(TaskSink.CHARGE_STOP)
    public void receiveChargeStop(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from CHARGE_STOP-useWork: " + returnInfo);
    }

    @StreamListener(TaskSink.EMP_COMPANY_CHANGE)
    public void receiveEmpCompanyChange(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from EMP_COMPANY_CHANGE-useWork: " + returnInfo);
    }

    @StreamListener(TaskSink.PRE_IN)
    public void receivePreIn(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from PRE_IN-useWork: " + returnInfo);
    }

    @StreamListener(TaskSink.NONLOCAL_TO_SH)
    public void receiveNonlocalToSh(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from NONLOCAL_TO_SH-useWork: " + returnInfo);
    }

    @StreamListener(TaskSink.SH_TO_NONLOCAL)
    public void receiveSh(Message<TaskMsgDTO> message) {
        TaskMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from SH_TO_NONLOCAL-useWork: " + returnInfo);
    }

}
