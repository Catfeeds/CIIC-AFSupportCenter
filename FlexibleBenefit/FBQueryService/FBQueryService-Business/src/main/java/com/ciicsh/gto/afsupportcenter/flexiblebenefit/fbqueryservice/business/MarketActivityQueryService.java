package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.GiftFormSendWay;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.MarketActivityPO;

/**
 * <p>
 * 市场活动表 服务类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
public interface MarketActivityQueryService extends IService<MarketActivityPO> {
    /**
     * 查询活动分页列表
     *
     * @param page
     * @param entity
     * @return
     */
    Page<MarketActivityPO> queryMarketList(Page<MarketActivityPO> page, MarketActivityPO entity);

    /**
     * 查询活动发放详细信息
     *
     * @param marketActivityPO
     * @return
     */
    MarketActivityPO queryMarketInformation(MarketActivityPO marketActivityPO);

    /**
     * 查询活动--派送方式--礼品形式下拉框
     *
     * @param marketActivityPO
     * @return
     */
    GiftFormSendWay queryGiftFormAndSendWayList(MarketActivityPO marketActivityPO);
}
