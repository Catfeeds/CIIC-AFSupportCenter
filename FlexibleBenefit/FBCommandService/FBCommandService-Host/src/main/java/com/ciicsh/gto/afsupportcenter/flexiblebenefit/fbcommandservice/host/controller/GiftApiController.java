package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.GiftCommandProxy;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto.JsonResult;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.GiftService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.GiftPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    public JsonResult findById(@PathVariable Integer id) {
        JsonResult result = new JsonResult();
        GiftPO entity = giftService.findById(id);
        if (entity == null || entity.getId() == null) {
            result.setErrorcode("400");
            result.setErrormsg("没有相应数据");
        }
        result.setData(entity);
        return result;
    }

    @Override
    public JsonResult updateById(Integer id, Integer num) {
        JsonResult result = new JsonResult();
        GiftPO entity = new GiftPO();
        entity.setId(id);
        entity.setNumber(num);
        /**判定礼品状态,库存为0，礼品自动下架*/
        if (entity.getNumber() == 0) {
            entity.setStatus((byte) 1);
        }
        int t = giftService.insertGift(entity);
        if (t != 1) {
            result.setErrorcode("400");
            result.setErrormsg("数据没有更新");
        }
        return result;
    }
}
