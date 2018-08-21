package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

public class ComFundAccountDetailPO
{
    /**
     * 缴费支行编号
     * 15 徐汇—X、16 西郊—C、17东方路—P、18 卢湾—L、0 黄浦—H
     *
     */
    private int paymentBank;

    /**
     * 付款方式:
     1 自付（客户自己汇缴给银行，雇员由中智办理）
     2 我司付款（客户预付）
     3 垫付
     */
    private int paymentWay;


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
    private int ukeyStore;

    /**
     * 每月关账日, 1-31
     */
    private int closeDay;


    /**
     * 企业缴费起始年月,yyyyMM
     */
    private String comStartMonth;

    private String comHfMonth;

    /**
     * 企业账户备注
     */
    private String remark;


    /**
     * 基本公积金账号是否属于临时保管
     * 1-临时保管状态 0-非临时保管状态
     *
     * */
    private int basicAccountTempStore;


    /**
     * 补充公积金账号是否属于临时保管
     * 1-临时保管状态 0-非临时保管状态
     *
     * */
    private int compensativeAccountTempStore;


    public ComFundAccountDetailPO()
    {
        paymentBank = 0;
        paymentWay = 0;
        basicComAccount = "";
        compensativeComAccount = "";
        ukeyStore = 0;
        closeDay = 0;
        comStartMonth = "";
        comHfMonth = "";
        remark = "";
        basicAccountTempStore = 0;
        compensativeAccountTempStore = 0;

    }

    public int getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(int paymentBank) {
        this.paymentBank = paymentBank;
    }

    public int getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(int paymentWay) {
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

    public int getUkeyStore() {
        return ukeyStore;
    }

    public void setUkeyStore(int ukeyStore) {
        this.ukeyStore = ukeyStore;
    }

    public int getCloseDay() {
        return closeDay;
    }

    public void setCloseDay(int closeDay) {
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

    public int getBasicAccountTempStore() {
        return basicAccountTempStore;
    }

    public void setBasicAccountTempStore(int basicAccountTempStore) {
        this.basicAccountTempStore = basicAccountTempStore;
    }

    public int getCompensativeAccountTempStore() {
        return compensativeAccountTempStore;
    }

    public void setCompensativeAccountTempStore(int compensativeAccountTempStore) {
        this.compensativeAccountTempStore = compensativeAccountTempStore;
    }

    public String getComHfMonth() {
        return comHfMonth;
    }

    public void setComHfMonth(String comHfMonth) {
        this.comHfMonth = comHfMonth;
    }
}
