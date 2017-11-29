package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.GiftPO;

import java.util.List;

/**
 * @author xwz created on 2017年11月29日17:20:49
 */
public interface GiftCommandMapper {
    /**
     * 删除数据
     *
     * @param id
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

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
     * 根据实体查询数据
     *
     * @param gift
     * @return List<GiftDTO>
     */
    List<GiftPO> findByEntity(GiftPO gift);

    /**
     * 修改数据
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(GiftPO record);

}