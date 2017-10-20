package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsPaymentDetailBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsPaymentDetailMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsPaymentDetail;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsPaymentDetailBusiness extends Business<SsPaymentDetail, SsPaymentDetailMapper>
    implements ISsPaymentDetailBusiness {

}
