package com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.message;

import com.ciicsh.gto.afsupportcenter.util.kafkaMessage.SocReportMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by houwanhua on 2018/2/24
 */
@EnableBinding(value = TaskSource.class)
@Component
public class KafkaSender {
    private final Logger logger = LoggerFactory.getLogger(KafkaSender.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private TaskSource taskSource;

    public void sendHfReportMsg(SocReportMessage message){
        logger.info("sendHfReportMsg开始，当前时间：" + dateFormat.format(new Date()));
        taskSource.hfReportOutput().send(MessageBuilder.withPayload(message).build());
        logger.info("sendHfReportMsg结束，当前时间：" + dateFormat.format(new Date()));
    }
}
