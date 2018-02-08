package com.ciicsh.gto.adsupportcenter.employcommandservice.host.messageBus;

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
