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
    private Integer paymentApplyId;
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
    /** 雇员银行账号 */
    private String bankAccount;
    /** 雇员银行名称 */
    private String bankName;
    /** 雇员银行编号 */
    private Integer bankId;
    /** 支付金额 */
    private BigDecimal payAmount;

    public Integer getPaymentApplyId() {
        return paymentApplyId;
    }

    public void setPaymentApplyId(Integer paymentApplyId) {
        this.paymentApplyId = paymentApplyId;
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

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    @Override
    public String toString() {
        return "EmployeePaymentBO{" +
            "paymentApplyId=" + paymentApplyId +
            ", companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", employeeId='" + employeeId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", bankAccount='" + bankAccount + '\'' +
            ", bankName='" + bankName + '\'' +
            ", bankCode='" + bankId + '\'' +
            ", payAmount=" + payAmount +
            '}';
    }
}
