package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmpBaseDetailMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsEmpBaseDetail;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《雇员社保汇缴基数明细》业务
 */
@Service
@Transactional
public class SsEmpBaseDetailBusiness extends Business<SsEmpBaseDetail, SsEmpBaseDetailMapper>{

}
