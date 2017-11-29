package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.MarketActivityPO;

import java.util.List;

public interface MarketActivityCommandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MarketActivityPO record);

    int insertSelective(MarketActivityPO record);

    MarketActivityPO selectByPrimaryKey(Integer id);

    List<MarketActivityPO> findByEntity(MarketActivityPO entity);

    int updateByPrimaryKeySelective(MarketActivityPO record);

    int updateByPrimaryKey(MarketActivityPO record);
}