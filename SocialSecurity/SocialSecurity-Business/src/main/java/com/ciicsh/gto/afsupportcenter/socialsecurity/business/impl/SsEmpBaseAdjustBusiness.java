package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsEmpBaseAdjustBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmpBaseAdjustMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsEmpBaseAdjust;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsEmpBaseAdjustBusiness extends Business<SsEmpBaseAdjust, SsEmpBaseAdjustMapper>
    implements ISsEmpBaseAdjustBusiness {
}
