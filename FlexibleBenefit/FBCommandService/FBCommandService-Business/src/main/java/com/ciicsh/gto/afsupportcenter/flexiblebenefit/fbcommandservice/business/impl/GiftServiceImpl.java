package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.GiftService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao.GiftCommandMapper;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.GiftPO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author xiweizhen
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class GiftServiceImpl implements GiftService {

    @Resource
    private GiftCommandMapper giftMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GiftPO findById(Integer id) {
        return giftMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertGift(GiftPO entity) {
        //修改方法
        if (entity.getId() != null) {
            return giftMapper.updateByPrimaryKeySelective(entity);
        } else {
            return giftMapper.insertSelective(entity);
        }
    }
}
