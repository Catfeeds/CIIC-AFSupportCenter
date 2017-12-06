package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.impl;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.MarketActivityService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao.MarketActivityQueryMapper;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.MarketActivityPO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xwz on 2017年11月29日19:29:03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MarketActivityServiceImpl implements MarketActivityService {

    @Resource
    private MarketActivityQueryMapper marketActivityMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<MarketActivityPO> findByEntity(MarketActivityPO entity) {
        return marketActivityMapper.findByEntity(entity);
    }

}
