package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.GiftPO;

/**
 * <p>
 * 礼品库 Mapper 接口
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
public interface GiftCommandMapper extends BaseMapper<GiftPO> {

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    GiftPO selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新数据
     *
     * @param giftPO
     * @return
     */
    Integer updateByPrimaryKeySelective(GiftPO giftPO);

}