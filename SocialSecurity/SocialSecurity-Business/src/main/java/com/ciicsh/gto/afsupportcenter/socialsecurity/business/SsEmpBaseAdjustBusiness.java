package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmpBaseAdjustMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsEmpBaseAdjust;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsEmpBaseAdjustBusiness extends Business<SsEmpBaseAdjust, SsEmpBaseAdjustMapper>{
}
