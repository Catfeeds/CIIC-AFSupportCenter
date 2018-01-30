package com.ciicsh.gto.afsupportcenter.socjob.task;

import com.ciicsh.gto.afsupportcenter.socjob.service.PaymentService;
import com.ciicsh.gto.afsupportcenter.socjob.service.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socjob.util.CommonUtils;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by houwanhua on 2018/1/17.
 */
@Component
public class SocScheduledTask {

    private final static Logger logger = LoggerFactory.getLogger(SocScheduledTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private SsPaymentComService paymentComService;

    @Autowired
    private PaymentService paymentService;


//    @Scheduled(cron = "0 0 20 L * ?")
    @Scheduled(fixedRate = 60000)
    public void generateSocPayment() throws InterruptedException{
        logger.info("开始，当前时间：" + dateFormat.format(new Date()));
        String paymentMonth = CommonUtils.getPaymentMonth();
        paymentComService.generateSocPaymentInfo(paymentMonth);
        logger.info("结束，当前时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 1/3 * * * ?") // 每天凌晨启动一次，"0 0 0 * * ?"
    public void execEnquireFinanceComAccount() {
        logger.info("每日询问财务是否可付定时任务启动，当前时间：" + dateFormat.format(new Date()));
        paymentService.enquireFinanceComAccount(StringUtil.getYear_Month(new Date()));
        logger.info("每日询问财务是否可付定时任务结束，当前时间：" + dateFormat.format(new Date()));
    }


}
