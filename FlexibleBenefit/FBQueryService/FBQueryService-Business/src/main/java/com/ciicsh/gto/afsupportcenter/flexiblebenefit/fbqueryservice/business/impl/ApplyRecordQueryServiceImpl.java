package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.ApplyRecordBO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.ApplyRecordQueryService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao.ApplyRecordQueryMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 申请记录表 服务实现类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
@Service
public class ApplyRecordQueryServiceImpl extends ServiceImpl<ApplyRecordQueryMapper, ApplyRecordPO> implements ApplyRecordQueryService {
    @Override
    public Page<ApplyRecordBO> selectGiftList(Page<ApplyRecordBO> page, ApplyRecordBO applyRecordBO) {
        page.setRecords(baseMapper.selectApplyList(page, applyRecordBO));
        return page;
    }
}
