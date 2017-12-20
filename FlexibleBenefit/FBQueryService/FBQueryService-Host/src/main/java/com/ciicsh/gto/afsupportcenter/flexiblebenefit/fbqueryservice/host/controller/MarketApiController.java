package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.MarketQueryProxy;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.MarketActivityQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiweizhen
 * @date 2017/12/6 18:57
 */
@RestController
@RequestMapping("/api/market")
public class MarketApiController implements MarketQueryProxy {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(MarketApiController.class);

    @Autowired
    private MarketActivityQueryService marketActivityQueryService;

    @Override
    public Result findMarketList(String activityName, String publish, Byte status, Integer pageNum, Integer pageSize) {
        logger.info("query服务--活动分页列表查询");
        Result result = new Result();
        return result;
    }
}
