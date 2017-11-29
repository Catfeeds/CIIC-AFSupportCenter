package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsMonthlyCompanyPayItemMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsMonthlyCompanyPayItem;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《雇员月度费用明细项目》业务
 */
@Service
@Transactional
public class SsMonthlyCompanyPayItemBusiness extends Business<SsMonthlyCompanyPayItem, SsMonthlyCompanyPayItemMapper> {

}
