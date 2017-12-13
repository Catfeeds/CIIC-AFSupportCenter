package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsMonthEmpChangeDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsStatementDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthEmpChangePO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsMonthEmpChangeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsMonthEmpChangeService;
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
public class SsMonthEmpChangeServiceImpl extends ServiceImpl<SsMonthEmpChangeMapper, SsMonthEmpChangePO> implements ISsMonthEmpChangeService {

    @Override
    public SsMonthEmpChangeDTO serachMonthEmpChangeByStatementId(SsMonthEmpChangeDTO ssMonthEmpChangeDTO) {
        return baseMapper.serachMonthEmpChangeByStatementId(ssMonthEmpChangeDTO);
    }
}
