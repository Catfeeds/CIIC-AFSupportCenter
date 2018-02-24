package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustAccountEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustAccountEmpTemp;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsAnnualAdjustAccountEmpTempMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAnnualAdjustAccountEmpTempService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class SsAnnualAdjustAccountEmpTempServiceImpl extends ServiceImpl<SsAnnualAdjustAccountEmpTempMapper, SsAnnualAdjustAccountEmpTemp> implements SsAnnualAdjustAccountEmpTempService {

    @Override
    public void updateErrorMsgForRepeatingEmployeeId(SsAnnualAdjustAccountEmpTempDTO ssAnnualAdjustAccountEmpTempDTO) {
        baseMapper.updateErrorMsgForRepeatingEmployeeId(ssAnnualAdjustAccountEmpTempDTO);
    }
}
