package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.impl;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.GiftService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao.GiftQueryMapper;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.GiftPO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiweizhen
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class GiftServiceImpl implements GiftService {

    @Resource
    private GiftQueryMapper giftMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GiftPO findById(Integer id) {
        return giftMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertGift(GiftPO entity) {
        return giftMapper.insertSelective(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<GiftPO> findByEntity(GiftPO entity) {
        return giftMapper.findByEntity(entity);
    }
}
