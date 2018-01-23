package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.messagebus;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordDetailPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApprovalStepPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApplyRecordDetailCommandService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApprovalStepCommandService;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
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
    public void giftApply(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from GIFT_APPLY-useWork: " + returnInfo);
        updateStepList(taskMsgDTO);
    }

    /**
     * 弹性福利更新审批公共方法
     *
     * @param taskMsgDTO
     */
    private void updateStepList(TaskCreateMsgDTO taskMsgDTO) {
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
    public void marketApply(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        String returnInfo = taskMsgDTO.toString();
        logger.info("收到消息from MARKETING_APPLY-useWork: " + returnInfo);

        updateStepList(taskMsgDTO);
    }

}
