package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.RedisManager;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.messageBus.KafkaSender;
import com.ciicsh.gto.afsupportcenter.util.kafkaMessage.SocReportMessage;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.util.ExpireTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 月度缴费明细
系统在每月26日晚上自动生成每月的标准和非标明细数据，用户也可重新生成， 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-23
 */
@RestController
@RequestMapping("/api/soccommandservice/ssMonthCharge")
public class SsMonthChargeController{

    @Autowired
    private KafkaSender sender;

    @RequestMapping(value = "/summarycalculate")
    public JsonResult<String> summaryCalculate(@RequestParam Long comAccountId,@RequestParam String ssMonth){
        String key = "-com-account-"+comAccountId+"-"+ssMonth+"-";
        SocReportMessage message = RedisManager.get(key,SocReportMessage.class);
        JsonResult<String> json = new JsonResult<String>();
        if(null != message){
            json.setCode(1);
            json.setMessage("正在计算中，请稍后再试！");
        }
        else{
            message = new SocReportMessage();
            message.setComAccountId(comAccountId);
            message.setSsMonth(ssMonth);
            sender.sendSocReportMsg(message);
            RedisManager.set(key,message, ExpireTime.NONE);
            json.setCode(0);
            json.setMessage("开始计算！");
        }
        return json;
    }


}

