package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto;

import java.math.BigDecimal;

/**
 * <p>
 * 支付申请详情表
 * </p>
 *
 * @author chenpb
 * @since 2018-01-31
 */
public class PaymentDetailDTO {

    /** 公司编号 */
    private String companyId;
    /** 公司名称 */
    private String companyName;
    /** 雇员编号 */
    private String employeeId;
    /** 雇员姓名 */
    private String employeeName;
    /** 雇员银行账号 */
    private String employeeBankAccount;
    /** 雇员银行名称 */
    private String bankName;
    /** 雇员银行编号 */
    private String bankCode;
    /** 支付金额 */
    private BigDecimal payAmount;
    /** 退票原因\备注 */
    private String remark;


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

    public String getEmployeeBankAccount() {
        return employeeBankAccount;
    }

    public void setEmployeeBankAccount(String employeeBankAccount) {
        this.employeeBankAccount = employeeBankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "PaymentDetailDTO{" +
            "companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", employeeId='" + employeeId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", bankAccount='" + employeeBankAccount + '\'' +
            ", bankName='" + bankName + '\'' +
            ", bankCode='" + bankCode + '\'' +
            ", payAmount=" + payAmount +
            '}';
    }
}
