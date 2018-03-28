package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer;

import java.math.BigDecimal;

/**
 * Created by houwanhua on 2018/3/28.
 */
public class PaymentComBO {
    private String companyId;
    private String companyName;
    private String companyBankAccount;
    private String payMonth;
    private BigDecimal payAmount;
    private String businessTypeDesc;
    private Integer isAdvance;

    public String getCompanyId() {
        return companyId;
    }

    public PaymentComBO setCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public PaymentComBO setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getCompanyBankAccount() {
        return companyBankAccount;
    }

    public PaymentComBO setCompanyBankAccount(String companyBankAccount) {
        this.companyBankAccount = companyBankAccount;
        return this;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public PaymentComBO setPayMonth(String payMonth) {
        this.payMonth = payMonth;
        return this;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public PaymentComBO setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
        return this;
    }

    public String getBusinessTypeDesc() {
        return businessTypeDesc;
    }

    public PaymentComBO setBusinessTypeDesc(String businessTypeDesc) {
        this.businessTypeDesc = businessTypeDesc;
        return this;
    }

    public Integer getIsAdvance() {
        return isAdvance;
    }

    public PaymentComBO setIsAdvance(Integer isAdvance) {
        this.isAdvance = isAdvance;
        return this;
    }
}
