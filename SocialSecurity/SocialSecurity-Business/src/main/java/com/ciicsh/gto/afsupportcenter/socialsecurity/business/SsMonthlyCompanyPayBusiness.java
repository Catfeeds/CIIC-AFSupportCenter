package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsMonthlyCompanyPayMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsMonthlyCompanyPay;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《月度缴费明细报》业务
 */
@Service
@Transactional
public class SsMonthlyCompanyPayBusiness extends Business<SsMonthlyCompanyPay, SsMonthlyCompanyPayMapper> {

}
