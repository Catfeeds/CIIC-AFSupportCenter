package com.ciicsh.gto.afsupportcenter.fundjob.message;

import com.ciicsh.gto.RedisManager;

import com.ciicsh.gto.afsupportcenter.fundjob.service.HfPaymentService;
import com.ciicsh.gto.afsupportcenter.util.kafkaMessage.SocReportMessage;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
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


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private LogApiUtil logApiUtil;
    @Autowired
    private HfPaymentService paymentService;

    @StreamListener(TaskSink.HF_REPORT_INPUT)
    public void receive(SocReportMessage message) {
        logApiUtil.info(LogMessage.create().setTitle("MessageReceiver开始").setContent(
            "received from comAccountId : " + message.getComAccountId() + ", received from ssMonth: " + message.getSsMonth()
        ));


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
        logApiUtil.info(LogMessage.create().setTitle("MessageReceiver结束"));

    }
}
