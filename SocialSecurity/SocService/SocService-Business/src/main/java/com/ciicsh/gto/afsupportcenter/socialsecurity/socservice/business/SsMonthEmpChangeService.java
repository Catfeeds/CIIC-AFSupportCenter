package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthEmpChange;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 雇员月度变更主表 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
public interface SsMonthEmpChangeService extends IService<SsMonthEmpChange> {

    /**
     * <p>Description: 根据对账单ID查询社保汇总数据主表</p>
     *
     * @author wengxk
     * @date 2017-12-13
     * @param ssMonthEmpChangeBO 检索条件
     * @return  SsMonthEmpChangeBO
     */
    SsMonthEmpChangeBO serachMonthEmpChangeByStatementId(SsMonthEmpChangeBO ssMonthEmpChangeBO);
    String getSsMonthChangeId(String ssMonth,String comAccountId);
}
