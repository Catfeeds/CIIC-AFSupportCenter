package com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.controller;

import com.ciicsh.gto.RedisManager;
import com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.message.KafkaSender;
import com.ciicsh.gto.afsupportcenter.util.kafkaMessage.SocReportMessage;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.util.ExpireTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hf/messageservice")
public class MessageController {

    @Autowired
    private KafkaSender sender;

    @RequestMapping(value = "/summarycalculate")
    public JsonResult<String> summaryCalculate(@RequestParam Long comAccountId, @RequestParam String ssMonth, @RequestParam String generalMethod,
                                               @RequestParam(required=false) String userName){
        String key = "-com-account-"+comAccountId+"-"+ssMonth+"-"+generalMethod+"-";
        SocReportMessage message = RedisManager.get(key,SocReportMessage.class);
        JsonResult<String> json = new JsonResult();
        if(null != message){
            json.setCode(1);
            json.setMessage("正在计算中，请稍后再试！");
        }else{
            message = new SocReportMessage();
            message.setComAccountId(comAccountId);
            message.setSsMonth(ssMonth);
            message.setGeneralMethod(generalMethod);
            if(null==userName || userName.equals("")){
                userName = "system";
            }
            message.setLastComputeUser(userName);
            sender.sendHfReportMsg(message);
            ExpireTime expireTime = ExpireTime.ONE_MIN;
            if("enquireFinanceComAccount".equals(generalMethod)){ // 询问财务是否可付 设置 redis 5分钟过期
                expireTime = ExpireTime.FIVE_MIN;
            }
            RedisManager.set(key,message, expireTime);
            json.setCode(0);
            json.setMessage("开始计算！");
        }
        return json;
    }
}
