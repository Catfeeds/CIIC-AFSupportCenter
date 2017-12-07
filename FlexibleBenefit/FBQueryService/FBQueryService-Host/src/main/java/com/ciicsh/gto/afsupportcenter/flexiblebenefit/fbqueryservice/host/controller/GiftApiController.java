package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.GiftCommandProxy;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.dto.JsonResult;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.GiftService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.GiftPO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gift")
public class GiftApiController implements GiftCommandProxy {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(GiftApiController.class);

    @Autowired
    private GiftService giftService;

    @Override
    public JsonResult findGiftList(Byte giftType, Integer pegeNum, Integer pageSize) {
        GiftPO entity = new GiftPO();
        JsonResult jr = new JsonResult();
        PageHelper.startPage(pegeNum, pageSize);
        entity.setGiftType(giftType);
        PageInfo<GiftPO> pageData = new PageInfo<>(giftService.findByEntity(entity));
        logger.info("query服务-查询礼品分页列表");
        jr.setData(pageData);
        return jr;
    }
}
