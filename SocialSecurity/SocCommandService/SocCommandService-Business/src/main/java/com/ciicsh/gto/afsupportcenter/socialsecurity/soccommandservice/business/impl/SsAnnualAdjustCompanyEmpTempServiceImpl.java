package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustCompanyEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustCompanyEmpTemp;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsAnnualAdjustCompanyEmpTempMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsAnnualAdjustCompanyEmpTempService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class SsAnnualAdjustCompanyEmpTempServiceImpl extends ServiceImpl<SsAnnualAdjustCompanyEmpTempMapper, SsAnnualAdjustCompanyEmpTemp> implements ISsAnnualAdjustCompanyEmpTempService {

    @Override
    public void updateErrorMsgForRepeatingEmployeeId(SsAnnualAdjustCompanyEmpTempDTO ssAnnualAdjustCompanyEmpTempDTO) {
        baseMapper.updateErrorMsgForRepeatingEmployeeId(ssAnnualAdjustCompanyEmpTempDTO);
    }

    @Override
    public void updateErrorMsgForNotExistsEmployee(SsAnnualAdjustCompanyEmpTempDTO ssAnnualAdjustCompanyEmpTempDTO) {
        baseMapper.updateErrorMsgForNotExistsEmployee(ssAnnualAdjustCompanyEmpTempDTO);
    }

}
