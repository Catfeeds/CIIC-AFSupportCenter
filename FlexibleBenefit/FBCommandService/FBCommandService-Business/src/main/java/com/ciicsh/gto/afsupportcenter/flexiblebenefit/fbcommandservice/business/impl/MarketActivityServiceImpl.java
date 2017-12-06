package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.impl;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.MarketActivityService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao.MarketActivityCommandMapper;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.MarketActivityPO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author xwz on 2017年11月29日19:29:03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MarketActivityServiceImpl implements MarketActivityService {

    @Resource
    private MarketActivityCommandMapper marketActivityMapper;

    @Override
    public MarketActivityPO findById(Integer id) {
        return marketActivityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addMarketActivity(MarketActivityPO entity) {
        if (entity.getId() == null) {
            return marketActivityMapper.insertSelective(entity);
        } else {
            return marketActivityMapper.updateByPrimaryKeySelective(entity);
        }
    }
}
