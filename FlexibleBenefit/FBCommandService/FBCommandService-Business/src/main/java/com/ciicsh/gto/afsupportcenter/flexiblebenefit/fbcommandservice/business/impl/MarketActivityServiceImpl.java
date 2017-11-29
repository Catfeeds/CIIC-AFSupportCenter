package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.impl;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.MarketActivityPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.MarketActivityService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao.MarketActivityCommandMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xwz on 2017年11月29日19:29:03
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class MarketActivityServiceImpl implements MarketActivityService {

    @Resource
    private MarketActivityCommandMapper marketActivityMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<MarketActivityPO> findByEntity(MarketActivityPO entity) {
        return marketActivityMapper.findByEntity(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MarketActivityPO findById(Integer id) {
        return null;
    }
}
