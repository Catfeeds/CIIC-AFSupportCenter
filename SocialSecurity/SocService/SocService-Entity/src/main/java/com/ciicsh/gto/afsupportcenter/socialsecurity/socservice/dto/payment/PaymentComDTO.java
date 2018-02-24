package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.payment;

public class PaymentComDTO {
    String companyId;
    String companyName;
    Long companyBankAccountId;
    String companyBankAccount;
    double noticebillAmount;
    double deductionAmount;
    String payMonth;
    String payAmount;
    int isAdvance;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getCompanyBankAccountId() {
        return companyBankAccountId;
    }

    public void setCompanyBankAccountId(Long companyBankAccountId) {
        this.companyBankAccountId = companyBankAccountId;
    }

    public String getCompanyBankAccount() {
        return companyBankAccount;
    }

    public void setCompanyBankAccount(String companyBankAccount) {
        this.companyBankAccount = companyBankAccount;
    }

    public double getNoticebillAmount() {
        return noticebillAmount;
    }

    public void setNoticebillAmount(double noticebillAmount) {
        this.noticebillAmount = noticebillAmount;
    }

    public double getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(double deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public int getIsAdvance() {
        return isAdvance;
    }

    public void setIsAdvance(int isAdvance) {
        this.isAdvance = isAdvance;
    }
}
