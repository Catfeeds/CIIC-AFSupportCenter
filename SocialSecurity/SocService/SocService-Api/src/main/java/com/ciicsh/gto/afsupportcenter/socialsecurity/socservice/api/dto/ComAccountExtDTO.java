package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;


import java.util.Date;

/**
 * Created by houwanhua on 2018/3/2.
 */
public class ComAccountExtDTO {
    /**
     * 企业社保账户Id
     */
    private Long comAccountId;
    /**
     * 多租户Id
     */
    private String customerId;

    /**
     * 客户名称
     */
    private String companyId;

    /**
     * 客户Id
     */
    private String companyName;

    /**
     * 参保户登记码（账号）
     */
    private String ssAccount;

    /**
     * 养老金账户公司名称
     */
    private String comAccountName;

    /**
     * 银行账号(牡丹卡号)
     */
    private String bankAccount;

    /**
     * 结算区县(社保局所在上海地区)
     */
    private String settlementArea;
    /**
     * 付款银行(一般情况是工商银行)
     */
    private String paymentBank;
    /**
     * 付款方式：.
     1-我司代付款
     2-客户自付
     3-我司垫付
     */
    private Integer paymentWay;
    /**
     * 社保帐单寄到哪里: 1 我司，2 客户公司
     */
    private Integer billReceiver;

    /**
     * 发起人要求任务完成截止日期
     */
    private Date expireDate;
    /**
     * 法人
     */
    private String legalPerson;
    /**
     * 联系地址
     */
    private String contactAddress;

    /**
     * 发出材料:正式通知书、预生成通知书、收据、银行对账单、汇总表、实时表
     */
    private String dispatchMaterial;
    /**
     * 备注
     */
    private String remark;


    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public String getSettlementArea() {
        return settlementArea;
    }

    public void setSettlementArea(String settlementArea) {
        this.settlementArea = settlementArea;
    }

    public String getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(String paymentBank) {
        this.paymentBank = paymentBank;
    }

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    public Integer getBillReceiver() {
        return billReceiver;
    }

    public void setBillReceiver(Integer billReceiver) {
        this.billReceiver = billReceiver;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getDispatchMaterial() {
        return dispatchMaterial;
    }

    public void setDispatchMaterial(String dispatchMaterial) {
        this.dispatchMaterial = dispatchMaterial;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
