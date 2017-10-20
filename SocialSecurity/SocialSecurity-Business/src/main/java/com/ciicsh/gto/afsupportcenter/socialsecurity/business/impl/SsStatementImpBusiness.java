package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsStatementImpBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsStatementImpMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsStatementImp;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsStatementImpBusiness extends Business<SsStatementImp, SsStatementImpMapper>
    implements ISsStatementImpBusiness {
}
