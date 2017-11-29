package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsComAccountMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《企业社保账户信息》业务
 */
@Service
@Transactional
public class SsComAccountBusiness extends Business<SsComAccount, SsComAccountMapper> {

}
