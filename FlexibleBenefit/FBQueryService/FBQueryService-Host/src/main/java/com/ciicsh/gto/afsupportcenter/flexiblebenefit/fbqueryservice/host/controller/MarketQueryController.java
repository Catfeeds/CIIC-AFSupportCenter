package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.MarketActivityPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.MarketActivityService;
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
@RequestMapping("/marketQueryService")
public class MarketQueryController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(MarketQueryController.class);

    @Autowired
    private MarketActivityService marketActivityService;

    @PostMapping("/marketList")
    public List<MarketActivityPO> marketList(MarketActivityPO entity) {
        List<MarketActivityPO> list = marketActivityService.findByEntity(entity);
        logger.info("query服务--活动列表查询");
        return list;
    }
}
