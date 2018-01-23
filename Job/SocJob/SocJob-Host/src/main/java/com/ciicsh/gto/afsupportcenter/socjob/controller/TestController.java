package com.ciicsh.gto.afsupportcenter.socjob.controller;

import com.ciicsh.gto.afsupportcenter.socjob.service.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socjob.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * Created by houwanhua on 2018/1/16.
 */
@RestController
public class TestController {

    @Autowired
    private SsPaymentComService paymentComService;

    @RequestMapping("/create")
    public String create() throws ParseException{
        String paymentMonth = CommonUtils.getPaymentMonth();
        paymentComService.generateSocPaymentInfo(paymentMonth);
        return "完成";
    }
}
