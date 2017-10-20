package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsComAccountBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsComAccountMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsComAccount;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsComAccountBusiness extends Business<SsComAccount, SsComAccountMapper>
    implements ISsComAccountBusiness {

}
