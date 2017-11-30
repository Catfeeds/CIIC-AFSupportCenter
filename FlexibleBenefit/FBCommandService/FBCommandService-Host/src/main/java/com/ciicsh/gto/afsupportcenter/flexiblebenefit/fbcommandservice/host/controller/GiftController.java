package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.GiftService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.GiftPO;
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
@RequestMapping("/giftCommandService")
public class GiftController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(GiftController.class);

    @Autowired
    private GiftService giftService;

    /**
     * 礼品新增功能
     *
     * @param entity
     * @return
     */
    @PostMapping("/giftInsert")
    public int giftInsert(GiftPO entity) {
        int t = giftService.insertGift(entity);
        if (t == 1) {
            logger.info("礼品添加成功");
        } else {
            logger.info("礼品添加失败");
        }
        return t;
    }


}
