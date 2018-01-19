package com.ciicsh.gto.afsupportcenter.socjob.handler;

import com.ciicsh.gto.afsupportcenter.socjob.service.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socjob.util.CommonUtils;
//import com.xxl.job.core.biz.model.ReturnT;
//import com.xxl.job.core.handler.IJobHandler;
//import com.xxl.job.core.handler.annotation.JobHandler;
//import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by houwanhua on 2018/1/19.
 */
//@JobHandler(value = "socScheduledHandler")
//@Component
//public class SocScheduledHandler extends IJobHandler{
//
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    @Autowired
//    private SsPaymentComService paymentComService;
//
//    @Override
//    public ReturnT<String> execute(String s) throws Exception {
//
//        XxlJobLogger.log("开始，当前时间：" + dateFormat.format(new Date()));
//        String paymentMonth = CommonUtils.getPaymentMonth();
//        paymentComService.generateSocPaymentInfo(paymentMonth);
//        XxlJobLogger.log("结束，当前时间：" + dateFormat.format(new Date()));
//        return SUCCESS;
//    }
//}
