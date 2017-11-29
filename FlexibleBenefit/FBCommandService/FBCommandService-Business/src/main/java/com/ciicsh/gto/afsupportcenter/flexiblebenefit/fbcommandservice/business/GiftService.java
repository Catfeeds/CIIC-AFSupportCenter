package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.GiftPO;

import java.util.List;

/**
 * @author created by xiweizhen 2017-11-16 14:02:26
 */
public interface GiftService {

    /**
     * 根据实体查询礼品集合列表
     *
     * @param entity
     * @return List<GiftDTO>
     */
    List<GiftPO> findByEntity(GiftPO entity);

    /**
     * 根据主键查询礼品数据
     *
     * @param id
     * @return GiftDTO
     */
    GiftPO findById(Integer id);

    /**
     * 新增礼品
     *
     * @param entity
     * @return int
     */
    int insertGift(GiftPO entity);
}
