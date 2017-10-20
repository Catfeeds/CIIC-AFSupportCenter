package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsStatementBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsStatementMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsStatement;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsStatementBusiness extends Business<SsStatement, SsStatementMapper>
    implements ISsStatementBusiness {

}
