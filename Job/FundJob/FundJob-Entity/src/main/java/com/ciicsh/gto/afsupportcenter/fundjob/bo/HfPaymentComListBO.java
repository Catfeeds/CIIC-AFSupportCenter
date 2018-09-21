package com.ciicsh.gto.afsupportcenter.fundjob.bo;


import java.math.BigDecimal;


public class HfPaymentComListBO {
    private Integer comAccountId;
    private BigDecimal paymentAmount;
    private String companyId;
    private String isCompanyEnjoyAdvance;
    private String payMonth;
    private BigDecimal payAmount;

    public Integer getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Integer comAccountId) {
        this.comAccountId = comAccountId;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getIsCompanyEnjoyAdvance() {
        return isCompanyEnjoyAdvance;
    }

    public void setIsCompanyEnjoyAdvance(String isCompanyEnjoyAdvance) {
        this.isCompanyEnjoyAdvance = isCompanyEnjoyAdvance;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}
