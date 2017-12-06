package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.GiftPO;

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

}
