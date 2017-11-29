package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsSSAccountCompanyMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsSSAccountCompany;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《企业社保账户与公司关系》业务
 */
@Service
@Transactional
public class SsSSAccountCompanyBusiness extends Business<SsSSAccountCompany, SsSSAccountCompanyMapper> {

}
