package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.MarketActivityService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.MarketActivityPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiweizhen
 */
@RestController
@RequestMapping("/marketCommandService")
public class MarketController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(MarketController.class);

    @Autowired
    private MarketActivityService marketActivityService;

    @PostMapping("/addMarketActivity")
    public int addMarketActivity(MarketActivityPO entity) {
        int t = marketActivityService.addMarketActivity(entity);
        if (t == 1) {
            logger.info("command服务--活动添加成功");
        } else {
            logger.info("command服务--活动添加失败");
        }
        return t;
    }

    @GetMapping("/findById/{id}")
    public MarketActivityPO findById(@PathVariable Integer id) {
        return marketActivityService.findById(id);
    }

}
