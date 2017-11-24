package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsPaymentMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsPayment;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsPaymentBusiness extends Business<SsPayment, SsPaymentMapper> {

}
