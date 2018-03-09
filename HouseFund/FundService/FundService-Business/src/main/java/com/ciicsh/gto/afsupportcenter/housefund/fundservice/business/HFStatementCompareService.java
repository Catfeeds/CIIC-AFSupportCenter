package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFStatementCompareBO;

import java.util.List;

/**
 * 对账单业务接口
 */
public interface HFStatementCompareService
{
    /**
     * 根据筛选条件获取公积金对账单记录
     * @param hfMonth
     * @param hfComAccount
     * @return
     */
    List<HFStatementCompareBO> getHFStatementCompareRecord(String hfMonth, String hfComAccount);
}
