package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.GiftFormSendWay;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.MarketActivityPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.dto.MarketActivityDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.MarketActivityQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Result marketList(@RequestBody MarketActivityDTO marketActivityDTO) {
        try {
            Page<MarketActivityPO> page = new Page<>(marketActivityDTO.getCurrent(), marketActivityDTO.getSize());
            MarketActivityPO marketActivityPO = new MarketActivityPO();
            BeanUtils.copyProperties(marketActivityDTO, marketActivityPO);
            page = marketActivityQueryService.queryMarketList(page, marketActivityPO);
            BeanUtils.copyProperties(page, marketActivityDTO);

            logger.info("查询活动分页列表");
            return ResultGenerator.genSuccessResult(marketActivityDTO);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    @PostMapping("/queryGiftFormAndSendWayList")
    public Result queryGiftFormAndSendWayList(@RequestBody MarketActivityDTO marketActivityDTO) {
        try {
            MarketActivityPO marketActivityPO = new MarketActivityPO();
            BeanUtils.copyProperties(marketActivityDTO, marketActivityPO);
            GiftFormSendWay giftFormSendWay = marketActivityQueryService.queryGiftFormAndSendWayList(marketActivityPO);

            logger.info("查询活动--派送方式--礼品形式下拉框");
            return ResultGenerator.genSuccessResult(giftFormSendWay);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }


}
