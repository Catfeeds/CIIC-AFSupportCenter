package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.MarketActivityPO;

public interface MarketActivityCommandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MarketActivityPO record);

    int insertSelective(MarketActivityPO record);

    MarketActivityPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MarketActivityPO record);

    int updateByPrimaryKey(MarketActivityPO record);
}