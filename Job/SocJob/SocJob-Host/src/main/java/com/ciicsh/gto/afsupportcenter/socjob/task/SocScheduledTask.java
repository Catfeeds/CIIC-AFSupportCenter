package com.ciicsh.gto.afsupportcenter.socjob.task;

import com.ciicsh.gto.afsupportcenter.socjob.service.PaymentService;
import com.ciicsh.gto.afsupportcenter.socjob.service.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socjob.service.TaskStatusService;
import com.ciicsh.gto.afsupportcenter.socjob.util.CommonUtils;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    @Autowired
    private TaskStatusService taskStatusService;

    /**
     * 每月1日定时生成支付数据
     * @throws InterruptedException
     */
    @Scheduled(cron = "0 0 1 1 * ?")  //每月1号凌晨1点
    //@Scheduled(fixedRate = 60000)
    public void generateSocPayment() throws InterruptedException{
        logger.info("开始，当前时间：" + dateFormat.format(new Date()));
        String paymentMonth = CommonUtils.getPaymentMonth();
        try {
            paymentComService.generateSocPaymentInfo(paymentMonth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("结束，当前时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 0 4 ? * *") //暂定每天上午4:00触发
    public void execEnquireFinanceComAccount() {
        logger.info("社保每日询问财务是否可付定时任务启动，当前时间：" + dateFormat.format(new Date()));
        paymentService.enquireFinanceComAccount(StringUtil.getYear_Month(new Date()));
        logger.info("社保每日询问财务是否可付定时任务结束，当前时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 15 10 ? * *")//暂定每天上午10:15触发
    public void execUpdateTaskStatus(){
        String log="社保每月1日更新办理状态，当前时间：";
        logger.info(log+dateFormat.format(new Date()));
        String exeMonth= CommonUtils.getPaymentMonth();
        taskStatusService.updateTaskStatus(exeMonth);
        logger.info(log+dateFormat.format(new Date()));
    }
}
