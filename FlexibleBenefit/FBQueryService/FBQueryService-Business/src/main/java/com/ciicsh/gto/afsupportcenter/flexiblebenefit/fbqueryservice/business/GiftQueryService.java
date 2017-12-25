package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.GiftPO;

/**
 * <p>
 * 礼品库 服务类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
public interface GiftQueryService extends IService<GiftPO> {

    /**
     * 查询列表
     *
     * @param page
     * @param entity
     * @return
     */
    Page<GiftPO> queryGiftList(Page<GiftPO> page, GiftPO entity);



}
