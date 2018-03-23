package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto;

/**
 * 新建对账单实体
 */
public class NewStatementDTO
{
    /**
     * 公积金汇缴月份
     */
    private String hfMonth;

    /**
     * 公积金账户Id
     */
    private int comAccountId;

    /**
     * 企业基本/补充公积金账号
     */
    private String hfComAccount;

    /**
     * 1 大库 2 外包 3 独立户
     */
    private Byte hfAccountType;

    /**
     * 公积金类型: 1 基本  2 补充
     */
    private int hfType;

    /**
     * 创建者登录名
     */
    private String createdBy;


    public String getHfMonth() {
        return hfMonth;
    }

    public void setHfMonth(String hfMonth) {
        this.hfMonth = hfMonth;
    }

    public int getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(int comAccountId) {
        this.comAccountId = comAccountId;
    }

    public String getHfComAccount() {
        return hfComAccount;
    }

    public void setHfComAccount(String hfComAccount) {
        this.hfComAccount = hfComAccount;
    }

    public Byte getHfAccountType() {
        return hfAccountType;
    }

    public void setHfAccountType(Byte hfAccountType) {
        this.hfAccountType = hfAccountType;
    }

    public int getHfType() {
        return hfType;
    }

    public void setHfType(int hfType) {
        this.hfType = hfType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
