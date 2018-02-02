package com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo;

import java.math.BigDecimal;

/**
 * <p>
 * 结算中心用支付申请
 * </p>
 *
 * @author chenpb
 * @since 2018-01-31
 */
public class PaymentApplyDetailBO {

    /** 支付批次号 */
    private Integer applyBatchId;
    /** 付款申请记录编号 */
    private Integer paymentApplyId;
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
    /** 雇员银行账号 */
    private String employeeBankAccount;
    /** 开户名 */
    private String bankAccountName;
    /** 银行类别 */
    private Integer bankId;
    /** 银行地区码 */
    private String areaCode;
    /** 开户行名称 */
    private String bankName;
    /** 省名称 */
    private String provinceName;
    /** 省名称 */
    private String cityName;


    public Integer getApplyBatchId() {
        return applyBatchId;
    }

    public void setApplyBatchId(Integer applyBatchId) {
        this.applyBatchId = applyBatchId;
    }

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

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getEmployeeBankAccount() {
        return employeeBankAccount;
    }

    public void setEmployeeBankAccount(String employeeBankAccount) {
        this.employeeBankAccount = employeeBankAccount;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "PaymentApplyDetailBO{" +
            "applyBatchId=" + applyBatchId +
            ", paymentApplyId=" + paymentApplyId +
            ", companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", employeeId='" + employeeId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", payAmount=" + payAmount +
            ", employeeBankAccount='" + employeeBankAccount + '\'' +
            ", bankAccountName='" + bankAccountName + '\'' +
            ", bankId=" + bankId +
            ", areaCode='" + areaCode + '\'' +
            ", bankName='" + bankName + '\'' +
            ", provinceName='" + provinceName + '\'' +
            ", cityName='" + cityName + '\'' +
            '}';
    }
}
