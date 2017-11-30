package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.MarketActivityPO;

import java.util.List;

public interface MarketActivityQueryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MarketActivityPO record);

    int insertSelective(MarketActivityPO record);

    MarketActivityPO selectByPrimaryKey(Integer id);

    List<MarketActivityPO> findByEntity(MarketActivityPO entity);

    int updateByPrimaryKeySelective(MarketActivityPO record);

    int updateByPrimaryKey(MarketActivityPO record);
}