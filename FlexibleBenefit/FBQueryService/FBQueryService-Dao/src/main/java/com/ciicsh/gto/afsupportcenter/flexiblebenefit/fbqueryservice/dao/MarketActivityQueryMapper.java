package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.MarketActivityPO;

import java.util.List;

/**
 * @author xiweizhen
 * @date 2017/12/6 10:55
 */
public interface MarketActivityQueryMapper {
    /**
     * @return List
     * @author xiweizhen
     * @date 2017/12/6 10:54
     */
    List<MarketActivityPO> findByEntity(MarketActivityPO entity);

}