package com.ciicsh.gto.afsupportcenter.fundjob.task;

import com.ciicsh.gto.afsupportcenter.fundjob.service.HfPaymentService;
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
public class FundScheduledTask {

    private final static Logger logger = LoggerFactory.getLogger(FundScheduledTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private HfPaymentService hfPaymentService;

    @Scheduled(cron = "0 0 0 * * ?") // 每天凌晨启动一次，"0 0 0 * * ?"
    public void execEnquireFinanceComAccount() {
        logger.info("公积金每日询问财务是否可付定时任务启动，当前时间：" + dateFormat.format(new Date()));
        
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        hfPaymentService.enquireFinanceComAccount();

        logger.info("公积金每日询问财务是否可付定时任务结束，当前时间：" + dateFormat.format(new Date()));
    }

    //@Scheduled(cron = "0 1/3 * * * ?") // 每天凌晨启动一次，"0 0 0 * * ?"
    //@Scheduled(cron = "0 56 19 ? * *")//暂定每天上午10:15触发
    @Scheduled(cron = "0 0 */1 * * ?")//间隔每1小时自动运行
    public void createPaymentAccount() {
        logger.info("公积金每日生成公积金支付账户付，定时任务启动，当前时间：" + dateFormat.format(new Date()));
        hfPaymentService.createPaymentAccount();
        logger.info("公积金每日生成公积金支付账户付，定时任务结束，当前时间：" + dateFormat.format(new Date()));
    }

}
