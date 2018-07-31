package com.ciicsh.gto.afsupportcenter.fundjob.message;

import com.ciicsh.gto.RedisManager;

import com.ciicsh.gto.afsupportcenter.fundjob.service.HfPaymentService;
import com.ciicsh.gto.afsupportcenter.util.kafkaMessage.SocReportMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@EnableBinding(value = TaskSink.class)
@Component
public class MessageReceiver {

    private final static Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Autowired
    private HfPaymentService paymentService;

    @StreamListener(TaskSink.HF_REPORT_INPUT)
    public void receive(SocReportMessage message) {
        logger.info("MessageReceiver开始，当前时间：" + dateFormat.format(new Date()));
        logger.info("received from comAccountId : " + message.getComAccountId() + ", received from ssMonth: " + message.getSsMonth());
        String key = "-com-account-" + message.getComAccountId() + "-" + message.getSsMonth() + "-" + message.getGeneralMethod() + "-";
        try {
            switch (message.getGeneralMethod()) {
                case "enquireFinanceComAccount":
                    paymentService.enquireFinanceComAccount();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (RedisManager.get(key, SocReportMessage.class) != null) {
                RedisManager.del(key);
            }
        }
        logger.info("MessageReceiver结束，当前时间：" + dateFormat.format(new Date()));
    }
}
