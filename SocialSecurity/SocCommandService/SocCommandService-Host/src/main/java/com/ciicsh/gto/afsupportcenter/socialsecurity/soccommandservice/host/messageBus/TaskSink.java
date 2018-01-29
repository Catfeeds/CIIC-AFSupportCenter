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
     * 雇员新增TOPIC
     */
    String AF_EMP_IN = TOPIC_PREFIX + "af_empin";

    /**
     * 雇员终止TOPIC
     */
    String AF_EMP_OUT = TOPIC_PREFIX + "af_empout";

    /**
     * 恢复缴费TOPIC
     */
    String CHARGE_RESUME = TOPIC_PREFIX + "af_charge_resume";

    /**
     * 暂停缴费TOPIC
     */
    String CHARGE_STOP = TOPIC_PREFIX + "af_charge_stop";

    /**
     * 雇员翻牌TOPIC
     */
    String EMP_COMPANY_CHANGE = TOPIC_PREFIX + "af_emp_company_change";

    /**
     * 预录用TOPIC
     */
    String PRE_IN = TOPIC_PREFIX + "af_emp_pre_in";

    /**
     * 外地转上海TOPIC
     */
    String NONLOCAL_TO_SH = TOPIC_PREFIX + "af_nonlocal_to_sh";

    /**
     * 上海转外地TOPIC
     */
    String SH_TO_NONLOCAL = TOPIC_PREFIX + "af_sh_to_nonlocal";

    /**
     * 上海基数年调TOPIC
     */
    String BASE_ADJUST_YEARLY_SH = TOPIC_PREFIX + "af_base_adjust_yearly_sh";

    /**
     * 财务中心付款申请拒绝
     */
    String PAY_APPLY_PAY_STATUS_STREAM =  "pay-apply-pay-status-stream";

    /**
     * 社保办理
     */
    String SOCIAL_NEW="socialNew";

    /**
     * 社保停办
     */
    String SOCIAL_STOP="socialStop";

    /**
     * 社保年调
     */
    String SOCIAL_ADJUST="socialAdjust";

    @Input(AF_EMP_IN)
    MessageChannel afEmpIn();


    @Input(AF_EMP_OUT)
    MessageChannel afEmpOut();

    @Input(CHARGE_RESUME)
    MessageChannel chargeResume();

    @Input(CHARGE_STOP)
    MessageChannel chargeStop();

    @Input(EMP_COMPANY_CHANGE)
    MessageChannel empCompanyChange();

    @Input(PRE_IN)
    MessageChannel preIn();

    @Input(NONLOCAL_TO_SH)
    MessageChannel nonlocalToSh();

    @Input(SH_TO_NONLOCAL)
    MessageChannel shToNonlocal();

    @Input(BASE_ADJUST_YEARLY_SH)
    MessageChannel baseAdjustYearlySh();

    @Input(PAY_APPLY_PAY_STATUS_STREAM)
    MessageChannel rejectPayApplyIdStream();
}
