package com.ciicsh.gto.afsupportcenter.socialsecurity.messageservice.host.controller;

import com.ciicsh.gto.RedisManager;
import com.ciicsh.gto.afsupportcenter.socialsecurity.messageservice.host.message.KafkaSender;
import com.ciicsh.gto.afsupportcenter.util.kafkaMessage.SocReportMessage;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.util.ExpireTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by houwanhua on 2018/2/24.
 */
@RestController
@RequestMapping("/soc/messageservice")
public class MessageController {

    @Autowired
    private KafkaSender sender;

    @RequestMapping(value = "/summarycalculate")
    public JsonResult<String> summaryCalculate(@RequestParam Long comAccountId, @RequestParam String ssMonth){
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
