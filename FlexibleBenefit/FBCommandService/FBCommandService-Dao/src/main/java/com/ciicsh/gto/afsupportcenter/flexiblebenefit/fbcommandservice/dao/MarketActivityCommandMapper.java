package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.MarketActivityPO;

public interface MarketActivityCommandMapper {
    int insertSelective(MarketActivityPO record);

    MarketActivityPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MarketActivityPO record);

}