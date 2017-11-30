package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.ApplyRecordPO;

public interface ApplyRecordQueryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyRecordPO record);

    int insertSelective(ApplyRecordPO record);

    ApplyRecordPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyRecordPO record);

    int updateByPrimaryKey(ApplyRecordPO record);
}