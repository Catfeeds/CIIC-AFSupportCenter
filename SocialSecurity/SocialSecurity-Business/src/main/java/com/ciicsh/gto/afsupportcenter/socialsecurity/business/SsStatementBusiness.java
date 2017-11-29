package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsStatementMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsStatement;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《本地社保对账单》业务
 */
@Service
@Transactional
public class SsStatementBusiness extends Business<SsStatement, SsStatementMapper> {

}
