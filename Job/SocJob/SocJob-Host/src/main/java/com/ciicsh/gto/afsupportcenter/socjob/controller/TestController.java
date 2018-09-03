package com.ciicsh.gto.afsupportcenter.socjob.controller;

import com.ciicsh.gto.afsupportcenter.socjob.service.PaymentService;
import com.ciicsh.gto.afsupportcenter.socjob.service.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socjob.service.TaskStatusService;
import com.ciicsh.gto.afsupportcenter.socjob.util.CommonUtils;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.EmployeeMonthlyDataProxy;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.PayapplyServiceProxy;
//import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.EmployeeMonthlyDataProxyDTO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyProxyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
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
    public String createPaymentCom(@RequestParam String ssMonth,@RequestParam Long comAccountId) throws Exception{

//        if(Optional.ofNullable(ssMonth).isPresent()){
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");
//            YearMonth hfMonthDate = YearMonth.parse(ssMonth , formatter);
//            paymentMonth= hfMonthDate.plusMonths(1).format(formatter);
//        }
        if(Optional.ofNullable(comAccountId).isPresent()){
            paymentComService.generateSocPaymentInfo(comAccountId,ssMonth);
        }else{
            paymentComService.generateSocPaymentInfo(ssMonth);
        }
        return "完成";
    }
    //数据导入，客户要求先生成4个月的支付数据
    @RequestMapping("/createPaymentComForImpData")
    public String createPaymentComForImpData(@RequestParam String ssMonth) throws Exception{

            paymentComService.generateSocPaymentInfoForImpData(ssMonth);
        return "完成";
    }
    /*
    * 任务单状已办批量修改已做
    * */
    @RequestMapping("/taskStatus")
    public String taskStatus(@RequestParam String ssMonth) throws ParseException{
        if(Optional.ofNullable(ssMonth).isPresent()) {
            taskStatusService.updateTaskStatus(ssMonth);
            return "完成";
        }else{
            return "请传参数";
        }
    }
    /*
    * 每日按雇员询问财务是否可付
    * */
    @RequestMapping("/dailyEmpPaymentStatus")
    public  String dailyEmpPaymentStatus(@RequestParam String ssMonth) throws ParseException{
        if(Optional.ofNullable(ssMonth).isPresent()) {
            paymentService.enquireFinanceComAccount(ssMonth);
            return "完成";
        }else{
            return "请传参数";
        }
    }
    /*
       * 每日按雇员询问财务是否可付
       * */
    @RequestMapping("/testDailyEmpPaymentStatus")
    public  String testDailyEmpPaymentStatus(@RequestParam String ssMonth,@RequestParam Long paymentComId,@RequestParam Long comAccountId) throws ParseException{
        if(Optional.ofNullable(ssMonth).isPresent()) {
            paymentService.enquireFinanceComAccountTest(ssMonth,paymentComId,comAccountId);
            return "完成";
        }else{
            return "请传参数";
        }
    }

}
