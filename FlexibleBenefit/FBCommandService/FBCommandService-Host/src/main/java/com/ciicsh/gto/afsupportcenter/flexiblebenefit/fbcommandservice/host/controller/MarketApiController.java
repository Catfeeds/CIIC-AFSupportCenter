package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.MarketCommandProxy;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.MarketActivityCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xiweizhen
 * @date 2017/12/6 18:57
 */
@RestController
@RequestMapping("/api/market")
public class MarketApiController implements MarketCommandProxy {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(MarketApiController.class);

    @Autowired
    private MarketActivityCommandService marketActivityCommandService;


    @Override
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable Integer id) {
        Result json = new Result();
        return json;
    }
}
