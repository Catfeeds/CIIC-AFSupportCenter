package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsMonthlyCompanyPayBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsMonthlyCompanyPayMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsMonthlyCompanyPay;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsMonthlyCompanyPayBusiness extends Business<SsMonthlyCompanyPay, SsMonthlyCompanyPayMapper>
    implements ISsMonthlyCompanyPayBusiness {

}
