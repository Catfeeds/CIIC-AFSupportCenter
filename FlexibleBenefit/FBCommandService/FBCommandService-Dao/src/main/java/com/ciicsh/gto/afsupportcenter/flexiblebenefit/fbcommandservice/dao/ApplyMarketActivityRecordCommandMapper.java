package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.ApplyMarketActivityRecordPO;

public interface ApplyMarketActivityRecordCommandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyMarketActivityRecordPO record);

    int insertSelective(ApplyMarketActivityRecordPO record);

    ApplyMarketActivityRecordPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyMarketActivityRecordPO record);

    int updateByPrimaryKey(ApplyMarketActivityRecordPO record);
}