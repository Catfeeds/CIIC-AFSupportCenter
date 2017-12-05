package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.MarketActivityService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.bo.JsonResult;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.MarketActivityPO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    private MarketActivityService marketActivityService;

    @PostMapping("/marketList")
    public JsonResult marketList(MarketActivityPO entity) {
        int page = Integer.parseInt((String) entity.getPage().get("current"));
        int pageSize = Integer.parseInt((String) entity.getPage().get("pageSize"));
        PageHelper.startPage(page, pageSize);
        PageInfo<MarketActivityPO> pageData = new PageInfo<>(marketActivityService.findByEntity(entity));
        logger.info("query服务--活动分页列表查询");
        JsonResult jr = new JsonResult();
        jr.setCode("200");
        jr.setData(pageData);
        return jr;
    }
}
