package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.ApplyMarketActivityRecordPO;

public interface ApplyMarketActivityRecordQueryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyMarketActivityRecordPO record);

    int insertSelective(ApplyMarketActivityRecordPO record);

    ApplyMarketActivityRecordPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyMarketActivityRecordPO record);

    int updateByPrimaryKey(ApplyMarketActivityRecordPO record);
}