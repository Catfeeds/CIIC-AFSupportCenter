package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto;


import java.io.Serializable;
import java.util.Date;

public class HfComAccountDTO implements Serializable {

    private static final long serialVersionUID = 4237696411983562922L;
    /**
     * 主键
     */
    private Long comAccountId;
    /**
     * 企业账户名称
     */
    private String comAccountName;
    /**
     * 付款方式:
     * 1 自付（客户自己汇缴给银行，雇员由中智办理）
     * 2 我司付款（客户预付）
     * 3 垫付
     */
    private Integer paymentWay;
    /**
     * 1 独立户 2 大库、3 外包
     */
    private Integer hfAccountType;
    /**
     * 客户公积金账户 每月的关账到哪一天1-31
     */
    private Integer closeDay;
    /**
     * 公积金企业U盾代管
     */
    private Integer ukeyStore;
    /**
     * 缴费区县：1 徐汇—X、2 西郊—C、3 东方路—P、4 卢湾—L、5 黄浦—H
     */
    private Integer paymentBank;
    /**
     * 备注
     */
    private String remark;
    /**
     * 账户状态:0初始 1有效 2 终止
     */
    private Integer state;
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 最后更新时间
     */
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
    private String modifiedBy;
    private String Column15;

    // 客户编号
    private String companyId;
    // 客户名称
    private String title;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
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

    public Integer getHfAccountType() {
        return hfAccountType;
    }

    public void setHfAccountType(Integer hfAccountType) {
        this.hfAccountType = hfAccountType;
    }

    public Integer getCloseDay() {
        return closeDay;
    }

    public void setCloseDay(Integer closeDay) {
        this.closeDay = closeDay;
    }

    public Integer getUkeyStore() {
        return ukeyStore;
    }

    public void setUkeyStore(Integer ukeyStore) {
        this.ukeyStore = ukeyStore;
    }

    public Integer getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(Integer paymentBank) {
        this.paymentBank = paymentBank;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getColumn15() {
        return Column15;
    }

    public void setColumn15(String column15) {
        Column15 = column15;
    }
}