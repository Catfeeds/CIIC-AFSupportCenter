package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.MarketActivityPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.MarketActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiweizhen
 */
@RestController
@RequestMapping("/market")
public class MarketController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(MarketController.class);

    @Autowired
    private MarketActivityService marketActivityService;

    @PostMapping("/marketList")
   public List<MarketActivityPO> marketList(MarketActivityPO entity) {
        return marketActivityService.findByEntity(entity);
    }
}
