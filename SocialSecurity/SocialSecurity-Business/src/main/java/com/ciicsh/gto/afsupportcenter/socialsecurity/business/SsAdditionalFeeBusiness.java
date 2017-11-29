package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsAdditionalFeeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsAdditionalFee;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《产品险种》业务
 */
@Service
@Transactional
public class SsAdditionalFeeBusiness extends Business<SsAdditionalFee, SsAdditionalFeeMapper> {

}
