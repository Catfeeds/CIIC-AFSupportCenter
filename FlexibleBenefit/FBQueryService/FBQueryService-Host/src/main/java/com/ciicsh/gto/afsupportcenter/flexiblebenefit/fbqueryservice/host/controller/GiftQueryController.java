package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.GiftService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.bo.JsonResult;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.GiftPO;
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
@RequestMapping("/giftQueryService")
public class GiftQueryController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(GiftQueryController.class);

    @Autowired
    private GiftService giftService;

    /**
     * 礼品分页列表查询
     *
     * @param entity
     * @return
     */
    @RequestMapping("/giftList")
    public JsonResult giftList(GiftPO entity) {
       /* int page = Integer.parseInt((String) entity.getPage().get("current"));
        int pageSize = Integer.parseInt((String) entity.getPage().get("pageSize"));*/
        PageHelper.startPage(1, 10);
        PageInfo<GiftPO> pageData = new PageInfo<>(giftService.findByEntity(entity));
        JsonResult jr = new JsonResult();
        jr.setCode("200");
        jr.setData(pageData);
        return jr;
    }

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
