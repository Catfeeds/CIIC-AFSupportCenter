package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;

/**
 * 企业账户信息
 */
public class ComFundAccountPO {

    /**
     * 企业公积金账户分类Id
     */
    private int comAccountClassId;


    /**
     * 企业公积金账户Id
     */
    private int comAccountId;



    /**
     * 企业账户名称
     */
    @Excel(name = "企业公积金名称", orderNum = "1")
    private String comAccountName;

    /**
     * 1 基本公积金、2 补充公积金
     */
    @Excel(name = "公积金类型",replace = {"基本公积金_1","补充公积金_2"},  orderNum = "3")
    private Byte hfType;

    /**
     * 付款方式:
     * 1 自付（客户自己汇缴给银行，雇员由中智办理）
     * 2 我司付款（客户预付）
     * 3 垫付
     */
    private Byte paymentWay;

    /**
     * 1 大库 2 外包 3 独立户
     */
    private Byte accountType;

    /**
     * 每月关账日, 1-31
     */
    private Byte closeDay;


    /**
     * 公积金企业U盾代管情况
     * 0-没有 1-有(客户自办)  2-有(中智代办)
     */
    @Excel(name = "U盾代管情况",replace = {"没有_0","有(客户自办)_1","有(中智代办)_2"}, orderNum = "4")
    private Byte ukeyStore;

    /**
     * 缴费支行编号
     * "徐汇—X_15","西郊—C_16","东方路—P_17","卢湾—L_18","黄浦—H_19"
     */
    @Excel(name = "缴费银行", replace = {"徐汇—X_15","西郊—C_16","东方路—P_17","卢湾—L_18","黄浦—H_0"},orderNum = "6")
    private Byte paymentBank;

    /**
     * 账户备注说明
     */
    @Excel(name = "备注说明", orderNum = "10")
    private String remark;

    /**
     * 基本公积金账户编号
     */
    @Excel(name = "公积金账号", orderNum = "2")
    private String comAccount;


    /**
     * 公积金缴费起始年月
     */
    private String payStartMonth;

    /**
     * 公积金缴费截止年月
     */
    private String payEndMonth;
    @Excel(name = "客户汇缴月", orderNum = "5")
    private  String comHfMonth;
    /**
     * 公积金账号是否属于临时保管
     * 1-临时保管状态 0-非临时保管状态
     */
    private Byte accountTempStore;
    @Excel(name = "客户编号", orderNum = "7")
    private String companyIds;
    @Excel(name = "组织机构代码", orderNum = "8")
    private String orgCode;
    @Excel(name = "客服", orderNum = "9")
    private String kf;
    /**
     * 账户状态:0初始 1有效 2 终止
     */
    @Excel(name = "终止", replace = {"有效_1","终止_2"},orderNum = "6")
    private Byte state;
    public ComFundAccountPO()
    {
        comAccountClassId = 0;
        accountTempStore = 0;
        accountType = 0;
        closeDay = 0;
        comAccount = "";
        comAccountId = 0;
        state = 0;
        comAccountName = "";
        hfType = 0;
        payEndMonth = "";
        paymentBank = 0;
        paymentWay = 0;
        payStartMonth = "";
        remark = "";
        ukeyStore = 0;
        comHfMonth="";
        companyIds="";
        kf="";
        orgCode="";
    }

    public String getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(String companyIds) {
        this.companyIds = companyIds;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getKf() {
        return kf;
    }

    public void setKf(String kf) {
        this.kf = kf;
    }

    public String getComHfMonth() {
        return comHfMonth;
    }

    public void setComHfMonth(String comHfMonth) {
        this.comHfMonth = comHfMonth;
    }

    public int getComAccountClassId() {
        return comAccountClassId;
    }

    public void setComAccountClassId(int comAccountClassId) {
        this.comAccountClassId = comAccountClassId;
    }

    public int getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(int comAccountId) {
        this.comAccountId = comAccountId;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public Byte getHfType() {
        return hfType;
    }

    public void setHfType(Byte hfType) {
        this.hfType = hfType;
    }

    public Byte getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Byte paymentWay) {
        this.paymentWay = paymentWay;
    }

    public Byte getAccountType() {
        return accountType;
    }

    public void setAccountType(Byte accountType) {
        this.accountType = accountType;
    }

    public Byte getCloseDay() {
        return closeDay;
    }

    public void setCloseDay(Byte closeDay) {
        this.closeDay = closeDay;
    }

    public Byte getUkeyStore() {
        return ukeyStore;
    }

    public void setUkeyStore(Byte ukeyStore) {
        this.ukeyStore = ukeyStore;
    }

    public Byte getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(Byte paymentBank) {
        this.paymentBank = paymentBank;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getComAccount() {
        return comAccount;
    }

    public void setComAccount(String comAccount) {
        this.comAccount = comAccount;
    }

    public String getPayStartMonth() {
        return payStartMonth;
    }

    public void setPayStartMonth(String payStartMonth) {
        this.payStartMonth = payStartMonth;
    }

    public String getPayEndMonth() {
        return payEndMonth;
    }

    public void setPayEndMonth(String payEndMonth) {
        this.payEndMonth = payEndMonth;
    }

    public Byte getAccountTempStore() {
        return accountTempStore;
    }

    public void setAccountTempStore(Byte accountTempStore) {
        this.accountTempStore = accountTempStore;
    }
}
