package com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo;

import java.math.BigDecimal;

/**
 * <p>
 * 雇员付款
 * </p>
 *
 * @author chenpb
 * @since 2018-01-30
 */
public class EmployeePaymentBO {

    /** 付款申请记录编号 */
    private String paymentApplyId;
    /** 业务ID */
    private Integer businessId;
    /** 公司编号 */
    private String companyId;
    /** 公司名称 */
    private String companyName;
    /** 雇员编号 */
    private String employeeId;
    /** 雇员姓名 */
    private String employeeName;
    /** 支付金额 */
    private BigDecimal payAmount;
    /** 税金 */
    private BigDecimal taxAmount;
    /** 雇员银行账号 */
    private String bankAccount;
    /** 雇员银行名字 */
    private String accountName;
    /** 银行类别 */
    private Integer bankId;
    /** 雇员银行名称 */
    private String bankCode;
    /** 雇员银行名称 */
    private String bankName;
    /** 省code*/
    private String provinceCode;
    /** 城市code */
    private String cityCode;

    public String getPaymentApplyId() {
        return paymentApplyId;
    }

    public void setPaymentApplyId(String paymentApplyId) {
        this.paymentApplyId = paymentApplyId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public String toString() {
        return "EmployeePaymentBO{" +
            "paymentApplyId=" + paymentApplyId +
            ", businessId=" + businessId +
            ", companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", employeeId='" + employeeId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", payAmount=" + payAmount +
            ", bankAccount='" + bankAccount + '\'' +
            ", accountName='" + accountName + '\'' +
            ", bankId=" + bankId +
            ", bankCode='" + bankCode + '\'' +
            ", bankName='" + bankName + '\'' +
            ", provinceCode='" + provinceCode + '\'' +
            ", cityCode='" + cityCode + '\'' +
            '}';
    }
}
