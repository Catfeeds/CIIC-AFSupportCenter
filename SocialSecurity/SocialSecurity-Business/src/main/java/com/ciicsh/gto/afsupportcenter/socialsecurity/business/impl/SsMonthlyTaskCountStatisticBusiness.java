package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsMonthlyTaskCountStatisticBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsMonthlyTaskCountStatisticMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsMonthlyTaskCountStatistic;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsMonthlyTaskCountStatisticBusiness extends Business<SsMonthlyTaskCountStatistic, SsMonthlyTaskCountStatisticMapper>
    implements ISsMonthlyTaskCountStatisticBusiness {

}
