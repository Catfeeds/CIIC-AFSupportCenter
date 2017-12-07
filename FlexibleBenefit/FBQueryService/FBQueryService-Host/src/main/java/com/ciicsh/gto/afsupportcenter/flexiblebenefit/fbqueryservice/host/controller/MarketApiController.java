package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.MarketCommandProxy;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.dto.JsonResult;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.MarketActivityService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.MarketActivityPO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class MarketApiController implements MarketCommandProxy {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(MarketApiController.class);

    @Autowired
    private MarketActivityService marketActivityService;

    @Override
    public JsonResult findMarketList(String activityName, String publish, Byte status, Integer pegeNum, Integer pageSize) {
        logger.info("query服务--活动分页列表查询");
        MarketActivityPO entity = new MarketActivityPO();
        entity.setActivityTitle(activityName);
        entity.setPublisher(publish);
        entity.setStatus(status);
        JsonResult jr = new JsonResult();
        PageHelper.startPage(pegeNum, pageSize);
        PageInfo<MarketActivityPO> pageData = new PageInfo<>(marketActivityService.findByEntity(entity));
        jr.setData(pageData);
        return jr;
    }
}
