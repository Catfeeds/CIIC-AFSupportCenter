package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordDetailPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApplyRecordDetailCommandService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao.ApplyRecordDetailCommandMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 申请记录详细表（一个赠送对象一条记录，存放记录的审批和发放记录） 服务实现类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
@Service
public class ApplyRecordDetailCommandServiceImpl extends ServiceImpl<ApplyRecordDetailCommandMapper, ApplyRecordDetailPO> implements ApplyRecordDetailCommandService {

    @Override
    public Integer insertSelective(ApplyRecordDetailPO applyRecordDetailPO) {
        return baseMapper.insertSelective(applyRecordDetailPO);
    }
}
