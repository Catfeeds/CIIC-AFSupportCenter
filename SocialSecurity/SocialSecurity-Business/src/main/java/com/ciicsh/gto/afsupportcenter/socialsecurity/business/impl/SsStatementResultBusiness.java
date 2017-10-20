package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsStatementResultBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsStatementResultMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsStatementResult;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsStatementResultBusiness extends Business<SsStatementResult, SsStatementResultMapper>
    implements ISsStatementResultBusiness {

}
