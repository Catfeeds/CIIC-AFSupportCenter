package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer;

/**
 * Created by houwanhua on 2018/3/7.
 */
public class AccountInfoBO {
    /**
     * 企业账户名称（前道传递）
     */
    private String comAccountName;
    /**
     * 付款方式:
     * 1 自付（客户自己汇缴给银行，雇员由中智办理）
     * 2 我司付款（客户预付）
     * 3 垫付（前道传递）
     */
    private Integer paymentWay;

    /**
     * 客户公积金账户 每月的关账到哪一天1-31（前道传递）
     */
    private Integer closeDay;
    /**
     * 客户Id
     */
    private String companyId;

    /**
     * 1 基本公积金、2 补充公积金
     */
    private Integer hfType;

    /**
     * 企业基本补充公积金账号（前道传递）
     */
    private String hfComAccount;
    /**
     * 客户缴费起始年月（前道传递）
     */
    private String comStartMonth;
    /**
     * 截止缴费年月（截单日）（前道传递）
     */
    private String endMonth;

    /**
     * 1 销户 2 公司自做 3 转其他代理商（前道传递）
     */
    private Integer endType;

    /**
     * 备注（前道传递）
     */
    private String remark;

    private Integer paymentBank;

    private String paymentBankValue;

    public String getPaymentBankValue() {
        return paymentBankValue;
    }

    public void setPaymentBankValue(String paymentBankValue) {
        this.paymentBankValue = paymentBankValue;
    }

    public Integer getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(Integer paymentBank) {
        this.paymentBank = paymentBank;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getHfType() {
        return hfType;
    }

    public void setHfType(Integer hfType) {
        this.hfType = hfType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    public Integer getCloseDay() {
        return closeDay;
    }

    public void setCloseDay(Integer closeDay) {
        this.closeDay = closeDay;
    }

    public String getHfComAccount() {
        return hfComAccount;
    }

    public void setHfComAccount(String hfComAccount) {
        this.hfComAccount = hfComAccount;
    }

    public String getComStartMonth() {
        return comStartMonth;
    }

    public void setComStartMonth(String comStartMonth) {
        this.comStartMonth = comStartMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public Integer getEndType() {
        return endType;
    }

    public void setEndType(Integer endType) {
        this.endType = endType;
    }
}
