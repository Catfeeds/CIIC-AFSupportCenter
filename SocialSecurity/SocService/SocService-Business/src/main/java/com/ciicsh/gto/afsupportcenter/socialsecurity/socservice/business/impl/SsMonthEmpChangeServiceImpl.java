package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthEmpChange;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsMonthEmpChangeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsMonthEmpChangeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 雇员月度变更主表 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
@Service
public class SsMonthEmpChangeServiceImpl extends ServiceImpl<SsMonthEmpChangeMapper, SsMonthEmpChange> implements SsMonthEmpChangeService {

    @Override
    public SsMonthEmpChangeBO serachMonthEmpChangeByStatementId(SsMonthEmpChangeBO ssMonthEmpChangeBO) {
        return baseMapper.serachMonthEmpChangeByStatementId(ssMonthEmpChangeBO);
    }
}
