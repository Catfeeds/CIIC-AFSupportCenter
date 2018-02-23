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

}
