package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordDetailPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.ApplyRecordDetailQueryService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao.ApplyRecordDetailQueryMapper;
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
public class ApplyRecordDetailQueryServiceImpl extends ServiceImpl<ApplyRecordDetailQueryMapper, ApplyRecordDetailPO> implements ApplyRecordDetailQueryService {
	
}
