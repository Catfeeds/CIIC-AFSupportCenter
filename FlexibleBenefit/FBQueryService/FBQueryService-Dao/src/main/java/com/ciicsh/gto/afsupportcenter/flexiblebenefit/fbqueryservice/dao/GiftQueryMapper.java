package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.GiftPO;

import java.util.List;

/**
 * @author xwz created on 2017年11月29日17:20:49
 */
public interface GiftQueryMapper {

    /**
     * 根据实体查询数据
     *
     * @param gift
     * @return List<GiftDTO>
     */
    List<GiftPO> findByEntity(GiftPO gift);


}