package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsCompanyTaskBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsCompanyTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsCompanyTask;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsCompanyTaskBusiness extends Business<SsCompanyTask, SsCompanyTaskMapper>
    implements ISsCompanyTaskBusiness {

}
