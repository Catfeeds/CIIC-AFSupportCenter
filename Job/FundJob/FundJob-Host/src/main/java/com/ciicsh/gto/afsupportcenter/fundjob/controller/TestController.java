package com.ciicsh.gto.afsupportcenter.fundjob.controller;

import com.ciicsh.gto.afsupportcenter.fundjob.service.HfPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private HfPaymentService hfPaymentService;

    /*
    * 每日按雇员询问财务是否可付
    * */
    @RequestMapping("/dailyEmpPaymentStatus")
    public  String dailyEmpPaymentStatus() throws ParseException{
            hfPaymentService.enquireFinanceComAccount();
            return "完成";
    }
    /*
       * 每日按雇员询问财务是否可付
       * */
    @RequestMapping("/testDailyEmpPaymentStatus")
    public  String testDailyEmpPaymentStatus(@RequestParam String ssMonth,@RequestParam Long comAccountId) throws ParseException{
        if(Optional.ofNullable(ssMonth).isPresent()) {
            hfPaymentService.enquireFinanceComAccountTest(ssMonth,comAccountId);
            return "完成";
        }else{
            return "请传参数";
        }
    }
}
