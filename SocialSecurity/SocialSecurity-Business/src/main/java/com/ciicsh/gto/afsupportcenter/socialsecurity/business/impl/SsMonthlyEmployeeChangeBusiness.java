package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsMonthlyEmployeeChangeBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsMonthlyEmployeeChangeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsMonthlyEmployeeChange;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsMonthlyEmployeeChangeBusiness extends Business<SsMonthlyEmployeeChange, SsMonthlyEmployeeChangeMapper>
    implements ISsMonthlyEmployeeChangeBusiness {

}
