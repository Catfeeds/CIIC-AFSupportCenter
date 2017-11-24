package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsMonthlyEmployeeChangeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsMonthlyEmployeeChange;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsMonthlyEmployeeChangeBusiness extends Business<SsMonthlyEmployeeChange, SsMonthlyEmployeeChangeMapper> {

}
