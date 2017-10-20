package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsSSAccountCompanyBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsSSAccountCompanyMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsSSAccountCompany;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsSSAccountCompanyBusiness extends Business<SsSSAccountCompany, SsSSAccountCompanyMapper>
    implements ISsSSAccountCompanyBusiness {

}
