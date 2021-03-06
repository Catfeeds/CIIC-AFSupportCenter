package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto;

/**
 * 公司公积金账户名称数据传输对象
 */
public class ComFundAccountClassNameDTO {

    /**
     * 企业公积金账户Id
     */
    private int comAccountId;

    /**
     * 企业账户名称
     */
    private String comAccountName;


    /**
     * 企业公积金账号
     */
    private String hfComAccount;

    /**
     * 1 基本公积金、2 补充公积金
     */
    private Byte hfType;

    /**
     * 账户状态:0初始 1有效 2 终止
     */
    private Byte state;

    public int getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(int comAccountId) {
        this.comAccountId = comAccountId;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public String getHfComAccount() {
        return hfComAccount;
    }

    public void setHfComAccount(String hfComAccount) {
        this.hfComAccount = hfComAccount;
    }

    public Byte getHfType() {
        return hfType;
    }

    public void setHfType(Byte hfType) {
        this.hfType = hfType;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}
