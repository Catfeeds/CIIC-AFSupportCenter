package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.ApplyRecordDetailPO;

public interface ApplyRecordDetailQueryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyRecordDetailPO record);

    int insertSelective(ApplyRecordDetailPO record);

    ApplyRecordDetailPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyRecordDetailPO record);

    int updateByPrimaryKey(ApplyRecordDetailPO record);
}