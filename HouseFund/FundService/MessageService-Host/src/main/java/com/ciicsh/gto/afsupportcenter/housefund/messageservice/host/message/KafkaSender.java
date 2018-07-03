package com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.message;

import com.ciicsh.gto.afsupportcenter.util.kafkaMessage.SocReportMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by houwanhua on 2018/2/24
 */
@EnableBinding(value = TaskSource.class)
@Component
public class KafkaSender {
    private final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private TaskSource taskSource;

    public void sendHfReportMsg(SocReportMessage message){
        taskSource.hfReportOutput().send(MessageBuilder.withPayload(message).build());
    }
}
