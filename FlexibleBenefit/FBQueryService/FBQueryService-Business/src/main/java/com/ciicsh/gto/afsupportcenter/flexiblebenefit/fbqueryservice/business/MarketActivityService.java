package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.MarketActivityPO;

import java.util.List;

/**
 * @author created by xiweizhen 2017-11-16 14:02:26
 */
public interface MarketActivityService {
    /**
     * 根据实体查询礼品集合列表
     * @param entity
     * @return List<GiftDTO>
     */
    List<MarketActivityPO> findByEntity(MarketActivityPO entity);

    /**
     * 根据主键查询礼品数据
     * @param id
     * @return GiftDTO
     */
    MarketActivityPO findById(Integer id);
}
