package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.ApplyGiftRecordPO;

public interface ApplyGiftRecordQueryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyGiftRecordPO record);

    int insertSelective(ApplyGiftRecordPO record);

    ApplyGiftRecordPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyGiftRecordPO record);

    int updateByPrimaryKey(ApplyGiftRecordPO record);
}