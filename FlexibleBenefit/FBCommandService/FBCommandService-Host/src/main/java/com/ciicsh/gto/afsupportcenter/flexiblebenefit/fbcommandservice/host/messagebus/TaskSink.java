package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.messagebus;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * @author bill
 * @date 17/10/23
 */
@Service
public interface TaskSink {

    String TOPIC_PREFIX = "common_taskservice_";

    /**
     * kafka消息
     *
     * @return
     */
    @Input(GIFT_APPLY)
    MessageChannel giftApply();

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
     * 礼品申请
     */
    String GIFT_APPLY = TOPIC_PREFIX + "sal_gift_apply_audit";


    /**
     * kafka消息
     *
     * @return
     */
    @Input(AF_EMP_IN)
    MessageChannel afEmpIn();

    /**
     * kafka消息
     *
     * @return
     */
    @Input(AF_EMP_OUT)
    MessageChannel afEmpOut();

    /**
     * kafka消息
     *
     * @return
     */
    @Input(CHARGE_RESUME)
    MessageChannel chargeResume();

    /**
     * kafka消息
     *
     * @return
     */
    @Input(CHARGE_STOP)
    MessageChannel chargeStop();

    /**
     * kafka消息
     *
     * @return
     */
    @Input(EMP_COMPANY_CHANGE)
    MessageChannel empCompanyChange();

    /**
     * kafka消息
     *
     * @return
     */
    @Input(PRE_IN)
    MessageChannel preIn();

    /**
     * kafka消息
     *
     * @return
     */
    @Input(NONLOCAL_TO_SH)
    MessageChannel nonlocalToSh();

    /**
     * kafka消息
     *
     * @return
     */
    @Input(SH_TO_NONLOCAL)
    MessageChannel shToNonlocal();


}
