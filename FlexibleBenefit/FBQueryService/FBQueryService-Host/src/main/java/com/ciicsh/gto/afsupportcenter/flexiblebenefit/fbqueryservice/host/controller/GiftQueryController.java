package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.GiftPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.dto.GiftDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.GiftQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
     * @param giftDTO
     * @return
     */
    @RequestMapping("/giftList")
    public Result giftList(GiftDTO giftDTO) {
        try {
            Page<GiftPO> page = new Page<>(giftDTO.getCurrent(), giftDTO.getSize());
            GiftPO giftPO = new GiftPO();
            BeanUtils.copyProperties(giftDTO, giftPO);
            page = giftQueryService.queryGiftList(page, giftPO);
            logger.info("查询礼品分页列表");
            return ResultGenerator.genSuccessResult(page);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

}
