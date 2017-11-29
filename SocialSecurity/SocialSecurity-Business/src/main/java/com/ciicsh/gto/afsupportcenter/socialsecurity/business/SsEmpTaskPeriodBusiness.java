package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmpTaskPeriodMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsEmpTaskPeriod;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《任务单费用段》业务
 */
@Service
@Transactional
public class SsEmpTaskPeriodBusiness extends Business<SsEmpTaskPeriod, SsEmpTaskPeriodMapper>{

}
