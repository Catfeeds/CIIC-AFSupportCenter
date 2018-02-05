package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SsAnnualAdjustAccountBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer accountStatus;
    private Integer matchStatus;
    private Integer cnt = 0;
    private BigDecimal accountAvgMonthSalary;
    private BigDecimal accountSalaryAmount;
    private BigDecimal accountEmpCount;

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(Integer matchStatus) {
        this.matchStatus = matchStatus;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public BigDecimal getAccountAvgMonthSalary() {
        return accountAvgMonthSalary;
    }

    public void setAccountAvgMonthSalary(BigDecimal accountAvgMonthSalary) {
        this.accountAvgMonthSalary = accountAvgMonthSalary;
    }

    public BigDecimal getAccountSalaryAmount() {
        return accountSalaryAmount;
    }

    public void setAccountSalaryAmount(BigDecimal accountSalaryAmount) {
        this.accountSalaryAmount = accountSalaryAmount;
    }

    public BigDecimal getAccountEmpCount() {
        return accountEmpCount;
    }

    public void setAccountEmpCount(BigDecimal accountEmpCount) {
        this.accountEmpCount = accountEmpCount;
    }
}
