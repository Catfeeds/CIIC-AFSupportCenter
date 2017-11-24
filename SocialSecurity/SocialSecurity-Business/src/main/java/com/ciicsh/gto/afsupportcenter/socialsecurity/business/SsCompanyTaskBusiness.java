package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsCompanyTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsCompanyTask;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsCompanyTaskBusiness extends Business<SsCompanyTask, SsCompanyTaskMapper>{

}
