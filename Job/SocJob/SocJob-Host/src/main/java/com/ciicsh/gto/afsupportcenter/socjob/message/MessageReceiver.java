package com.ciicsh.gto.afsupportcenter.socjob.message;

import com.ciicsh.gto.RedisManager;
import com.ciicsh.gto.afsupportcenter.socjob.service.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.util.kafkaMessage.SocReportMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by houwanhua on 2018/2/1.
 */
@EnableBinding(value = TaskSink.class)
@Component
public class MessageReceiver {

    private final static Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private SsPaymentComService paymentComService;

    @StreamListener(TaskSink.SOC_REPORT_INPUT)
    public void receive(SocReportMessage message){
        logger.info("开始，当前时间：" + dateFormat.format(new Date()));
        logger.info("received from comAccountId : " + message.getComAccountId()+", received from ssMonth: " + message.getSsMonth());
        paymentComService.generateSocPaymentInfo(message.getComAccountId(),message.getSsMonth());
        String key = "-com-account-"+message.getComAccountId()+"-"+message.getSsMonth()+"-";
        if(RedisManager.get(key,SocReportMessage.class) != null){
            RedisManager.del(key);
        }
        logger.info("结束，当前时间：" + dateFormat.format(new Date()));
    }
}
