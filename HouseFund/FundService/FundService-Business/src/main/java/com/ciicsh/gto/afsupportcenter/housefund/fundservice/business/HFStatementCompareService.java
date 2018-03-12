package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFStatementCompareBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.ComFundAccountDetailDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.FundStatementDetailDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.NewStatementDTO;

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

    /**
     * 添加新的对账
     * @param
     * @return
     */
    void addStatement(String filePath);

    /**
     * 获取对账单明细记录
     * @param statementId
     * @return
     */
    FundStatementDetailDTO getStatementDetail(long statementId);

    /**
     * 删除对账单
     * @param statementId
     * @return
     */
    int delStatement(long statementId);

    /**
     * 执行对账
     * @param statementId
     * @return
     */
    boolean execStatementDetail(long statementId);
}
