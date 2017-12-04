package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.MarketActivityPO;

/**
 * @author created by xiweizhen 2017-11-16 14:02:26
 */
public interface MarketActivityService {
    /**
     * 根据主键查询礼品数据
     *
     * @param id
     * @return GiftDTO
     */
    MarketActivityPO findById(Integer id);

    /**
     * @author xiweizhen
     * @date 2017/12/4 10:33
     */
    int addMarketActivity(MarketActivityPO entity);
}
