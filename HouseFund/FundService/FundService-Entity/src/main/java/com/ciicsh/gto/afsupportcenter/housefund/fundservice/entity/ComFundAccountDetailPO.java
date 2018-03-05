package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

public class ComFundAccountDetailPO
{
    /**
     * 缴费支行编号
     * 1 徐汇—X、2 西郊—C、3 东方路—P、4 卢湾—L、5 黄浦—H
     *
     */
    private Byte paymentBank;

    /**
     * 付款方式:
     1 自付（客户自己汇缴给银行，雇员由中智办理）
     2 我司付款（客户预付）
     3 垫付
     */
    private Byte paymentWay;


    /**
     * 企业基本公积金账户编号
     */
    private String basicComAccount;

    /**
     * 企业补充公积金账户编号
     */
    private String compensativeComAccount;

    /**
     * 公积金企业U盾代管情况
     * 0-没有 1-有(客户自办)  2-有(中智代办)
     */
    private Byte ukeyStore;

    /**
     * 每月关账日, 1-31
     */
    private Byte closeDay;


    /**
     * 企业缴费起始年月,yyyyMM
     */
    private String comStartMonth;

    /**
     * 企业账户备注
     */
    private String remark;


    /**
     * 基本公积金账号是否属于临时保管
     * 1-临时保管状态 0-非临时保管状态
     *
     * */
    private Byte basicAccountTempStore;


    /**
     * 补充公积金账号是否属于临时保管
     * 1-临时保管状态 0-非临时保管状态
     *
     * */
    private Byte compensativeAccountTempStore;


    public ComFundAccountDetailPO()
    {
        paymentBank = 0;
        paymentWay = 0;
        basicComAccount = "";
        compensativeComAccount = "";
        ukeyStore = 0;
        closeDay = 0;
        comStartMonth = "";
        remark = "";
        basicAccountTempStore = 0;
        compensativeAccountTempStore = 0;

    }

    public Byte getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(Byte paymentBank) {
        this.paymentBank = paymentBank;
    }

    public Byte getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Byte paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getBasicComAccount() {
        return basicComAccount;
    }

    public void setBasicComAccount(String basicComAccount) {
        this.basicComAccount = basicComAccount;
    }

    public String getCompensativeComAccount() {
        return compensativeComAccount;
    }

    public void setCompensativeComAccount(String compensativeComAccount) {
        this.compensativeComAccount = compensativeComAccount;
    }

    public Byte getUkeyStore() {
        return ukeyStore;
    }

    public void setUkeyStore(Byte ukeyStore) {
        this.ukeyStore = ukeyStore;
    }

    public Byte getCloseDay() {
        return closeDay;
    }

    public void setCloseDay(Byte closeDay) {
        this.closeDay = closeDay;
    }

    public String getComStartMonth() {
        return comStartMonth;
    }

    public void setComStartMonth(String comStartMonth) {
        this.comStartMonth = comStartMonth;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getBasicAccountTempStore() {
        return basicAccountTempStore;
    }

    public void setBasicAccountTempStore(Byte basicAccountTempStore) {
        this.basicAccountTempStore = basicAccountTempStore;
    }

    public Byte getCompensativeAccountTempStore() {
        return compensativeAccountTempStore;
    }

    public void setCompensativeAccountTempStore(Byte compensativeAccountTempStore) {
        this.compensativeAccountTempStore = compensativeAccountTempStore;
    }
}
