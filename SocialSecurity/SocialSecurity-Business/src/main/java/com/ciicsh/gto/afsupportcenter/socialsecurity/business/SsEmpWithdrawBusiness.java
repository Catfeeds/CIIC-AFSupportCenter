package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmpWithdrawMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsEmpWithdraw;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsEmpWithdrawBusiness extends Business<SsEmpWithdraw, SsEmpWithdrawMapper>{

}
