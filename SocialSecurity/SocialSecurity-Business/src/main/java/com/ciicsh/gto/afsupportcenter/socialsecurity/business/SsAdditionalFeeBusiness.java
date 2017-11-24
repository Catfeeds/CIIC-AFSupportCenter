package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsAdditionalFeeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsAdditionalFee;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsAdditionalFeeBusiness extends Business<SsAdditionalFee, SsAdditionalFeeMapper> {

}
