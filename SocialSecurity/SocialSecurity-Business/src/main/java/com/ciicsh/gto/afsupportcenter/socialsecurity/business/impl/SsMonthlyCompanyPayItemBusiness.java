package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsMonthlyCompanyPayItemBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsMonthlyCompanyPayItemMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsMonthlyCompanyPayItem;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsMonthlyCompanyPayItemBusiness extends Business<SsMonthlyCompanyPayItem, SsMonthlyCompanyPayItemMapper>
    implements ISsMonthlyCompanyPayItemBusiness {

}
