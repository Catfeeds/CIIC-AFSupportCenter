package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsMonthlyTaskCountStatisticMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsMonthlyTaskCountStatistic;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《首页任务单数的月度分类统计》业务
 */
@Service
@Transactional
public class SsMonthlyTaskCountStatisticBusiness extends Business<SsMonthlyTaskCountStatistic, SsMonthlyTaskCountStatisticMapper>{

}
