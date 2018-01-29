package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpRefundBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpRefund;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpRefundMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsEmpRefundService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 雇员社保退账受理表。
如果为某雇员多缴纳了社保金额，则需向社保局提出退账申请，退账申请受理后落地到此表。
                                                 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsEmpRefundServiceImpl extends ServiceImpl<SsEmpRefundMapper, SsEmpRefund> implements SsEmpRefundService {

    @Override
    public List<SsEmpRefundBO> selectRefundDetail(SsEmpRefundBO ssEmpRefundBO) {
        return baseMapper.selectRefundDetail(ssEmpRefundBO);
    }
}
