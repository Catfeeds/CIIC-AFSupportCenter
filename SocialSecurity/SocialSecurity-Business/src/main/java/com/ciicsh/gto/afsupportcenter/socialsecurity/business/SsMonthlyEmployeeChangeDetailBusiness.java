package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsMonthlyEmployeeChangeDetailMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsMonthlyEmployeeChangeDetail;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《雇员月度变更表明细》业务
 */
@Service
@Transactional
public class SsMonthlyEmployeeChangeDetailBusiness extends Business<SsMonthlyEmployeeChangeDetail, SsMonthlyEmployeeChangeDetailMapper>{

}
