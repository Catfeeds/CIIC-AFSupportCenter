package com.ciicsh.gto.afsupportcenter.socjob.controller;

import com.ciicsh.gto.afsupportcenter.socjob.service.PaymentService;
import com.ciicsh.gto.afsupportcenter.socjob.service.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socjob.service.TaskStatusService;
import com.ciicsh.gto.afsupportcenter.socjob.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Optional;

/**
 * Created by houwanhua on 2018/1/16.
 */
@RestController
public class TestController {

    @Autowired
    private SsPaymentComService paymentComService;
    @Autowired
    private TaskStatusService taskStatusService;
    @Autowired
    private PaymentService paymentService;



    @RequestMapping("/createPaymentCom")
    public String createPaymentCom(@RequestParam String ssMonth) throws ParseException{
        String paymentMonth = CommonUtils.getPaymentMonth(); //默认当前系统年月
        if(Optional.ofNullable(ssMonth).isPresent()){
            paymentMonth=ssMonth;
        }
        paymentComService.generateSocPaymentInfo(paymentMonth);
        return "完成";
    }

    @RequestMapping("/taskStatus")
    public String taskStatus(@RequestParam String ssMonth) throws ParseException{
        if(Optional.ofNullable(ssMonth).isPresent()) {
            taskStatusService.updateTaskStatus(ssMonth);
            return "完成";
        }else{
            return "请传参数";
        }
    }

    @RequestMapping("dailyEmpPaymentStatus")
    public  String dailyEmpPaymentStatus(@RequestParam String ssMonth) throws ParseException{
        if(Optional.ofNullable(ssMonth).isPresent()) {
            paymentService.enquireFinanceComAccount(ssMonth);
            return "完成";
        }else{
            return "请传参数";
        }
    }
}
