package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.messageBus;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * Created by bill on 17/10/23.
 */
@Service
public interface TaskSink {


    static final String TOPIC_PREFIX = "common_taskservice_";

    /**
     * 雇员新增
     */
    String AF_EMP_IN = TOPIC_PREFIX + "af_empin";

    /**
     * 雇员终止
     */
    String AF_EMP_OUT = TOPIC_PREFIX + "af_empout";

    /**
     * 雇员补缴
     */
    String AF_EMP_MAKE_UP = TOPIC_PREFIX + "af_emp_make_up";

    /**
     * 雇员翻牌
     */
    String AF_EMP_COMPANY_CHANGE = TOPIC_PREFIX + "af_emp_company_change";

    /**
     * 雇员服务协议调整
     */
    String AF_EMP_AGREEMENT_ADJUST = TOPIC_PREFIX + "af_emp_agreement_adjust";

    /**
     * 雇员服务协议更正
     */
    String AF_EMP_AGREEMENT_UPDATE = TOPIC_PREFIX + "af_emp_agreement_update";

    /**
     * 财务中心付款申请返回
     */
    String PAY_APPLY_PAY_STATUS_STREAM = "pay-apply-pay-status-stream";

    /**
     * 社保办理
     */
    String SOCIAL_NEW = "social_new";

    /**
     * 社保停办
     */
    String SOCIAL_STOP = "social_stop";

    /**
     * 社保补缴
     */
    String SOCIAL_MAKE_UP = "social_make_up";

    @Input(AF_EMP_IN)
    MessageChannel afEmpIn();

    @Input(AF_EMP_OUT)
    MessageChannel afEmpOut();

    @Input(AF_EMP_MAKE_UP)
    MessageChannel afEmpMakeUp();

    @Input(AF_EMP_COMPANY_CHANGE)
    MessageChannel afEmpCompanyChange();

    @Input(AF_EMP_AGREEMENT_ADJUST)
    MessageChannel afEmpAgreementAdjust();

    @Input(AF_EMP_AGREEMENT_UPDATE)
    MessageChannel afEmpAgreementUpdate();

    @Input(PAY_APPLY_PAY_STATUS_STREAM)
    MessageChannel rejectPayApplyIdStream();
}
