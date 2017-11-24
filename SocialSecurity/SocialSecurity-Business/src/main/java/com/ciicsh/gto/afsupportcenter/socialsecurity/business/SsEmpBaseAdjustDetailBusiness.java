package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmpBaseAdjustDetailMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsEmpBaseAdjustDetail;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsEmpBaseAdjustDetailBusiness extends Business<SsEmpBaseAdjustDetail, SsEmpBaseAdjustDetailMapper> {

}
