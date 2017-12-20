package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.MarketActivityPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.MarketActivityQueryService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao.MarketActivityQueryMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 市场活动表 服务实现类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
@Service
public class MarketActivityQueryServiceImpl extends ServiceImpl<MarketActivityQueryMapper, MarketActivityPO> implements MarketActivityQueryService {

    @Override
    public Page<MarketActivityPO> queryMarketList(Page<MarketActivityPO> page, MarketActivityPO entity) {
        EntityWrapper<MarketActivityPO> entityWrapper = new EntityWrapper<>();
        //排除活动主题空数据
        entityWrapper.where(!StringUtils.isEmpty(entity.getActivityTitle()), "activity_title={0}", entity.getActivityTitle());
        page.setRecords(baseMapper.selectPage(page, entityWrapper));
        return page;
    }
}
