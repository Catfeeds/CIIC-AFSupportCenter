package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.MarketActivityPO;

/**
 * <p>
 * 市场活动表 服务类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
public interface MarketActivityQueryService extends IService<MarketActivityPO> {
    /**
     * 查询活动分页列表
     *
     * @param page
     * @param entity
     * @return
     */
    Page<MarketActivityPO> queryMarketList(Page<MarketActivityPO> page, MarketActivityPO entity);
}
