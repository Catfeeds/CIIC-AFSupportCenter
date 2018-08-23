package com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.message;

import com.ciicsh.gto.afsupportcenter.util.kafkaMessage.SocReportMessage;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
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
    @Autowired
    private LogApiUtil logApiUtil;
    public void sendHfReportMsg(SocReportMessage message){

        logApiUtil.info(LogMessage.create().setTitle("KafkaSender开始"));
        taskSource.hfReportOutput().send(MessageBuilder.withPayload(message).build());
        logApiUtil.info(LogMessage.create().setTitle("KafkaSender结束"));
    }
}
