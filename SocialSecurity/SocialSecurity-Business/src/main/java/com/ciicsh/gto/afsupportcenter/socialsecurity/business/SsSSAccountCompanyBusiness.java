package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsSSAccountCompanyMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsSSAccountCompany;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsSSAccountCompanyBusiness extends Business<SsSSAccountCompany, SsSSAccountCompanyMapper> {

}
