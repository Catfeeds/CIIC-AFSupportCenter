package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.MarketCommandProxy;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto.JsonResult;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.MarketActivityService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.MarketActivityPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xiweizhen
 * @date 2017/12/6 18:57
 */
@FeignClient("afsupportcenter-center-command-service")
@RestController
@RequestMapping("/api/market")
public class MarketApiController implements MarketCommandProxy {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(MarketApiController.class);

    @Autowired
    private MarketActivityService marketActivityService;


    @Override
    @GetMapping("/findById/{id}")
    public JsonResult findById(@PathVariable Integer id) {
        JsonResult json = new JsonResult();
        MarketActivityPO entity = marketActivityService.findById(id);
        json.setData(entity);
        return json;
    }
}
