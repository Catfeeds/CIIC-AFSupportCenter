package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmpBasePeriodMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsEmpBasePeriod;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《雇员正常汇缴社保的基数分段》业务
 */
@Service
@Transactional
public class SsEmpBasePeriodBusiness extends Business<SsEmpBasePeriod, SsEmpBasePeriodMapper>{
}
