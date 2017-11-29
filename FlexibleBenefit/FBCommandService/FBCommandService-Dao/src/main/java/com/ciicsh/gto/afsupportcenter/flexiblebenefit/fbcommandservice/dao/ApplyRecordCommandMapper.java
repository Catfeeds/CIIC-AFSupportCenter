package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.ApplyRecordPO;

public interface ApplyRecordCommandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyRecordPO record);

    int insertSelective(ApplyRecordPO record);

    ApplyRecordPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyRecordPO record);

    int updateByPrimaryKey(ApplyRecordPO record);
}