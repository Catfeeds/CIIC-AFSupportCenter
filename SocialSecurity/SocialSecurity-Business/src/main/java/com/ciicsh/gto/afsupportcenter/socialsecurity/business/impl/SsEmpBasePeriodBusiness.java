package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsEmpBasePeriodBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmpBasePeriodMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsEmpBasePeriod;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsEmpBasePeriodBusiness extends Business<SsEmpBasePeriod, SsEmpBasePeriodMapper>
    implements ISsEmpBasePeriodBusiness {
}
