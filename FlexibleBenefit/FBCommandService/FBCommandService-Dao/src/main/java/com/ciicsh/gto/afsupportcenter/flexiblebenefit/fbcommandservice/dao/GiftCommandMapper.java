package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.GiftPO;

/**
 * @author xwz created on 2017年11月29日17:20:49
 */
public interface GiftCommandMapper {
    /**
     * 新增数据
     *
     * @param record
     * @return int
     */
    int insertSelective(GiftPO record);

    /**
     * 根据主键查询数据
     *
     * @param id
     * @return GiftDTO
     */
    GiftPO selectByPrimaryKey(Integer id);


    /**
     * 修改数据
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(GiftPO record);

}