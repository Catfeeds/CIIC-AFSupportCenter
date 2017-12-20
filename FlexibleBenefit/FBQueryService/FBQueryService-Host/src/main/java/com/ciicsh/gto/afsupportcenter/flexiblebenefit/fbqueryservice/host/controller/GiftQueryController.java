package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.page.PageParam;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.GiftPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.GiftQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiweizhen
 */
@RestController
@RequestMapping("/giftQueryService")
public class GiftQueryController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(GiftQueryController.class);

    @Autowired
    private GiftQueryService giftQueryService;

    /**
     * 礼品分页列表查询
     *
     * @param pageParam
     * @return
     */
    @RequestMapping("/giftList")
    public Result giftList(PageParam pageParam) {
        try {
            Page<GiftPO> page = new Page<>(pageParam.getCurrent(), pageParam.getSize());
            GiftPO entity = pageParam.getJsonObjectParams().toJavaObject(GiftPO.class);
            page = giftQueryService.queryGiftList(page, entity);
            logger.info("查询礼品分页列表");
            return ResultGenerator.genSuccessResult(page);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

}
