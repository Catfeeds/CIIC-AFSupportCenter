package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyGiftRecordPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.ApplyGiftRecordQueryService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao.ApplyGiftRecordQueryMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 礼品申请记录表 服务实现类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
@Service
public class ApplyGiftRecordQueryServiceImpl extends ServiceImpl<ApplyGiftRecordQueryMapper, ApplyGiftRecordPO> implements ApplyGiftRecordQueryService {

    @Override
    public ApplyGiftRecordPO queryApplyGiftRecord(ApplyGiftRecordPO applyGiftRecordPO) {
        return baseMapper.selectOne(applyGiftRecordPO);
    }
}
