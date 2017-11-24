package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmpRefundMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsEmpRefund;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsEmpRefundBusiness extends Business<SsEmpRefund, SsEmpRefundMapper>{

}
