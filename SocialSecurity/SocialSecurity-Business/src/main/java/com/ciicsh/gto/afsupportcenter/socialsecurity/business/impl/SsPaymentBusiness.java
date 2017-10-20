package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsPaymentBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsPaymentMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsPayment;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsPaymentBusiness extends Business<SsPayment, SsPaymentMapper>
    implements ISsPaymentBusiness {

}
