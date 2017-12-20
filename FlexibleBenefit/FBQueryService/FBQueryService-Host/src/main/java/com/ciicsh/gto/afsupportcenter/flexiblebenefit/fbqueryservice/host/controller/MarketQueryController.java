package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.page.PageParam;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.MarketActivityPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.MarketActivityQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private MarketActivityQueryService marketActivityQueryService;

    @PostMapping("/marketList")
    public Result marketList(PageParam pageParam) {
        try {
            Page<MarketActivityPO> page = new Page<>(pageParam.getCurrent(), pageParam.getSize());
            MarketActivityPO entity = pageParam.getJsonObjectParams().toJavaObject(MarketActivityPO.class);
            page = marketActivityQueryService.queryMarketList(page, entity);
            logger.info("查询活动分页列表");
            return ResultGenerator.genSuccessResult(page);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }
}
