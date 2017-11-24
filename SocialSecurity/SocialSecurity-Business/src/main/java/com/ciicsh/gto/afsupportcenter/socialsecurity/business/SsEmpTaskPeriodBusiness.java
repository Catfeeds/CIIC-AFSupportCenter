package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmpTaskPeriodMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsEmpTaskPeriod;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsEmpTaskPeriodBusiness extends Business<SsEmpTaskPeriod, SsEmpTaskPeriodMapper>{

}
