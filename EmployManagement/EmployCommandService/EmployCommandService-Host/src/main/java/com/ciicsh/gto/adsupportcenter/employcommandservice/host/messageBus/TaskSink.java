package com.ciicsh.gto.adsupportcenter.employcommandservice.host.messageBus;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * Created by bill on 17/10/23.
 */
@Service
public interface TaskSink {

    /**
     * 雇员新增
     */
    String AF_EMP_IN = "common_taskservice_af_empin_channel";

    /**
     * 雇员终止
     */
    String AF_EMP_OUT = "common_taskservice_af_empout_channel";

    /**
     * 雇员翻牌
     */
    String AF_EMP_COMPANY_CHANGE = "common_taskservice_af_emp_company_change_channel";

    /**
     * 雇员服务协议调整
     */
    String AF_EMP_AGREEMENT_ADJUST = "common_taskservice_af_emp_agreement_adjust_channel";

    /**
     * 雇员服务协议更正
     */
    String AF_EMP_AGREEMENT_UPDATE = "common_taskservice_af_emp_agreement_update_channel";

    /**
     * 用工办理
     */
    String HIRE = "hire";
    /**
     * 退工
     */
    String FIRE = "fire";

    @Input(AF_EMP_IN)
    MessageChannel afEmpIn();

    @Input(AF_EMP_OUT)
    MessageChannel afEmpOut();

    @Input(AF_EMP_COMPANY_CHANGE)
    MessageChannel afEmpCompanyChange();

    @Input(AF_EMP_AGREEMENT_ADJUST)
    MessageChannel afEmpAgreementAdjust();

    @Input(AF_EMP_AGREEMENT_UPDATE)
    MessageChannel afEmpAgreementUpdate();



}
