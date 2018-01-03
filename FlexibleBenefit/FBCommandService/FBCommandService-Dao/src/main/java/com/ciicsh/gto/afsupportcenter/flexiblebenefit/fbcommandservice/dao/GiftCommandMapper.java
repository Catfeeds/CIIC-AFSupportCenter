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

    GiftPO selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(GiftPO giftPO);

}