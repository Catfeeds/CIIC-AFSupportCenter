package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.ApplyGiftRecordPO;

public interface ApplyGiftRecordCommandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyGiftRecordPO record);

    int insertSelective(ApplyGiftRecordPO record);

    ApplyGiftRecordPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyGiftRecordPO record);

    int updateByPrimaryKey(ApplyGiftRecordPO record);
}