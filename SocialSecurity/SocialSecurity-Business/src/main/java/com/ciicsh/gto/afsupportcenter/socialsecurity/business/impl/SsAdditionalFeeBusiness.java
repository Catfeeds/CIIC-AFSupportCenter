package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsAdditionalFeeBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsAdditionalFeeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsAdditionalFee;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsAdditionalFeeBusiness extends Business<SsAdditionalFee, SsAdditionalFeeMapper>
    implements ISsAdditionalFeeBusiness {

}
