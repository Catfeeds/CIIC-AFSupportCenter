package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmpBaseDetailMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsEmpBaseDetail;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsEmpBaseDetailBusiness extends Business<SsEmpBaseDetail, SsEmpBaseDetailMapper>{

}
