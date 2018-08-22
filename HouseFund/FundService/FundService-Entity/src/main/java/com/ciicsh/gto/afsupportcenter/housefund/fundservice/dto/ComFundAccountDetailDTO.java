package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto;

/**
 * 企业账户详情
 */
public class ComFundAccountDetailDTO
{

    /**
     * 缴费支行编号
     * 15 徐汇—X、16 西郊—C、17东方路—P、18 卢湾—L、0 黄浦—H
     *
     */
    private Integer paymentBank;

    /**
     * 付款方式:
     1 自付（客户自己汇缴给银行，雇员由中智办理）
     2 我司付款（客户预付）
     3 垫付
     */
    private Integer paymentWay;


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
    private Integer ukeyStore;

    /**
     * 每月关账日, 1-31
     */
    private Integer closeDay;


    /**
     * 企业缴费起始年月,yyyyMM
     */
    private String comStartMonth;
    private String comStartMonthValue;

    private String comHfMonth;
    private String comHfMonthValue;

    /**
     * 企业账户备注
     */
    private String remark;


    /**
     * 基本公积金账号是否属于临时保管
     * 1-临时保管状态 0-非临时保管状态
     *
     * */
    private Integer basicAccountTempStore;


    /**
     * 补充公积金账号是否属于临时保管
     * 1-临时保管状态 0-非临时保管状态
     *
     * */
    private Integer compensativeAccountTempStore;

    private Long  comAccountClassId;
    private Long comAccountId;
    private Integer hfType;
    private String[] tmpStore;

    public String[] getTmpStore() {
        return tmpStore;
    }

    public void setTmpStore(String[] tmpStore) {
        this.tmpStore = tmpStore;
    }

    public String getComStartMonthValue() {
        return comStartMonthValue;
    }

    public void setComStartMonthValue(String comStartMonthValue) {
        this.comStartMonthValue = comStartMonthValue;
    }

    public Integer getHfType() {
        return hfType;
    }

    public void setHfType(Integer hfType) {
        this.hfType = hfType;
    }

    public ComFundAccountDetailDTO()
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

    public Long getComAccountClassId() {
        return comAccountClassId;
    }

    public void setComAccountClassId(Long comAccountClassId) {
        this.comAccountClassId = comAccountClassId;
    }

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public Integer getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(Integer paymentBank) {
        this.paymentBank = paymentBank;
    }

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Integer paymentWay) {
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

    public Integer getUkeyStore() {
        return ukeyStore;
    }

    public void setUkeyStore(Integer ukeyStore) {
        this.ukeyStore = ukeyStore;
    }

    public Integer getCloseDay() {
        return closeDay;
    }

    public void setCloseDay(Integer closeDay) {
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

    public Integer getBasicAccountTempStore() {
        return basicAccountTempStore;
    }

    public void setBasicAccountTempStore(Integer basicAccountTempStore) {
        this.basicAccountTempStore = basicAccountTempStore;
    }

    public Integer getCompensativeAccountTempStore() {
        return compensativeAccountTempStore;
    }

    public void setCompensativeAccountTempStore(Integer compensativeAccountTempStore) {
        this.compensativeAccountTempStore = compensativeAccountTempStore;
    }

    public String getComHfMonth() {
        return comHfMonth;
    }

    public void setComHfMonth(String comHfMonth) {
        this.comHfMonth = comHfMonth;
    }

    public String getComHfMonthValue() {
        return comHfMonthValue;
    }

    public void setComHfMonthValue(String comHfMonthValue) {
        this.comHfMonthValue = comHfMonthValue;
    }
}
