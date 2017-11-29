package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.ApplyRecordDetailPO;

public interface ApplyRecordDetailCommandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyRecordDetailPO record);

    int insertSelective(ApplyRecordDetailPO record);

    ApplyRecordDetailPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyRecordDetailPO record);

    int updateByPrimaryKey(ApplyRecordDetailPO record);
}