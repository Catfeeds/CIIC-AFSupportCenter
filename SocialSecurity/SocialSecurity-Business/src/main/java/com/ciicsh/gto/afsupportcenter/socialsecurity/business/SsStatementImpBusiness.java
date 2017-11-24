package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsStatementImpMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsStatementImp;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsStatementImpBusiness extends Business<SsStatementImp, SsStatementImpMapper> {
}
