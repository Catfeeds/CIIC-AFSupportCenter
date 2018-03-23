package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import java.util.Objects;

public class FundStatementDetailPO
{
    /**
     * 对账单Id
     */
    private long statementId;


    /**
     * 公积金月份
     */
    private String hfMonth;

    /**
     * 公积金企业账户名称
     */
    private String comAccountName;

    /**
     * 导入记录总数
     */
    private int impRecordCount;

    /**
     * 差异总数
     */
    private int diffCount;

    public long getStatementId() {
        return statementId;
    }

    public void setStatementId(long statementId) {
        this.statementId = statementId;
    }

    public String getHfMonth() {
        return hfMonth;
    }

    public void setHfMonth(String hfMonth) {
        this.hfMonth = hfMonth;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public int getImpRecordCount() {
        return impRecordCount;
    }

    public void setImpRecordCount(int impRecordCount) {
        this.impRecordCount = impRecordCount;
    }

    public int getDiffCount() {
        return diffCount;
    }

    public void setDiffCount(int diffCount) {
        this.diffCount = diffCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FundStatementDetailPO that = (FundStatementDetailPO) o;
        return statementId == that.statementId &&
            impRecordCount == that.impRecordCount &&
            diffCount == that.diffCount &&
            Objects.equals(hfMonth, that.hfMonth) &&
            Objects.equals(comAccountName, that.comAccountName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(statementId, hfMonth, comAccountName, impRecordCount, diffCount);
    }
}
