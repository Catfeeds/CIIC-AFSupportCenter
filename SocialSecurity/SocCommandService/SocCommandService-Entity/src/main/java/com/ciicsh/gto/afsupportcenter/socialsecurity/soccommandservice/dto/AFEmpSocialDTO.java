package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: wujinglei
 * @Date: 2017/12/8 13:25
 */
public class AFEmpSocialDTO extends BaseEntity {

    private static final long serialVersionUID = 1247127019458102057L;
    /**
     * 主键
     */
    private Long empSocialId;

    /**
     * 雇员服务协议ID
     */
    private Long empAgreementId;

    /**
     * 入离职ID
     */
    private Long empCompanyId;

    /**
     * 公司ID(冗余)
     */
    private String companyId;

    /**
     * 社保ID
     */
    private String policyId;

    private String itemDicId;

    private BigDecimal empCompanyBase;

    /**
     * 社保类型
     */
    private Integer policyType;

    /**
     * 产品名称
     */
    private String policyName;

    /**
     * 报入雇员基数
     */
    private BigDecimal empBase;
    /**
     * 企业比例
     */
    private BigDecimal companyRatio;

    /**
     * 企业基数
     */
    private BigDecimal companyBase;

    /**
     * 企业小数点进位方式
     */
    private Integer companyRoundType;

    /**
     * 企业金额
     */
    private BigDecimal companyAmount;

    /**
     * 企业实缴金额
     */
    private BigDecimal companyComfirmAmount;

    /**
     * 个人比例
     */
    private BigDecimal personalRatio;

    /**
     * 个人基数
     */
    private BigDecimal personalBase;

    /**
     * 个人金额
     */
    private BigDecimal personalAmount;

    /**
     * 个人实缴金额
     */
    private BigDecimal personalComfirmAmount;

    /**
     * 个人小数点进位方式
     */
    private Integer personalRoundType;

    /**
     * 缴纳开始月(yyyyMM格式)
     */
    private Integer startMonth;

    /**
     * 缴纳结束月(yyyyMM格式)
     */
    private Integer endMonth;

    /**
     * 缴纳开始月确认月(yyyy-MM格式)
     */
    private Integer startComfirmMonth;

    /**
     * 缴纳结束月确认月(yyyy-MM格式)
     */
    private Integer endComfirmMonth;

    /**
     * 账单最后出账月(yyyyMM格式)
     */
    private Integer lastBillMonth;

    /**
     * 账号(公积金)
     */
    private String account;

    /**
     *
     */
    private String cityCode;

    /**
     * 办理方 1:独立户 0:大库
     */
    private Integer unit;

    // --------------------扩展 数据 字典内容--------------------
    private String itemType;
    private String companyRatioList;
    private String companyComputeType;
    private String companyFixedAmount;
    private String personalRatioList;
    private String personalComputeType;
    private String personalFixedAmount;
    // --------------------扩展 数据 字典内容--------------------

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public BigDecimal getEmpBase() {
        return empBase;
    }

    public void setEmpBase(BigDecimal empBase) {
        this.empBase = empBase;
    }

    public BigDecimal getCompanyAmount() {
        return companyAmount;
    }

    public void setCompanyAmount(BigDecimal companyAmount) {
        this.companyAmount = companyAmount;
    }

    public BigDecimal getCompanyComfirmAmount() {
        return companyComfirmAmount;
    }

    public void setCompanyComfirmAmount(BigDecimal companyComfirmAmount) {
        this.companyComfirmAmount = companyComfirmAmount;
    }

    public BigDecimal getPersonalAmount() {
        return personalAmount;
    }

    public void setPersonalAmount(BigDecimal personalAmount) {
        this.personalAmount = personalAmount;
    }

    public BigDecimal getPersonalComfirmAmount() {
        return personalComfirmAmount;
    }

    public void setPersonalComfirmAmount(BigDecimal personalComfirmAmount) {
        this.personalComfirmAmount = personalComfirmAmount;
    }

    public Long getEmpSocialId() {
        return empSocialId;
    }

    public void setEmpSocialId(Long empSocialId) {
        this.empSocialId = empSocialId;
    }

    public Long getEmpAgreementId() {
        return empAgreementId;
    }

    public void setEmpAgreementId(Long empAgreementId) {
        this.empAgreementId = empAgreementId;
    }

    public Long getEmpCompanyId() {
        return empCompanyId;
    }

    public void setEmpCompanyId(Long empCompanyId) {
        this.empCompanyId = empCompanyId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public Integer getPolicyType() {
        return policyType;
    }

    public void setPolicyType(Integer policyType) {
        this.policyType = policyType;
    }

    public BigDecimal getCompanyRatio() {
        return companyRatio;
    }

    public void setCompanyRatio(BigDecimal companyRatio) {
        this.companyRatio = companyRatio;
    }

    public BigDecimal getCompanyBase() {
        return companyBase;
    }

    public void setCompanyBase(BigDecimal companyBase) {
        this.companyBase = companyBase;
    }

    public Integer getCompanyRoundType() {
        return companyRoundType;
    }

    public void setCompanyRoundType(Integer companyRoundType) {
        this.companyRoundType = companyRoundType;
    }

    public BigDecimal getPersonalRatio() {
        return personalRatio;
    }

    public void setPersonalRatio(BigDecimal personalRatio) {
        this.personalRatio = personalRatio;
    }

    public BigDecimal getPersonalBase() {
        return personalBase;
    }

    public void setPersonalBase(BigDecimal personalBase) {
        this.personalBase = personalBase;
    }

    public Integer getPersonalRoundType() {
        return personalRoundType;
    }

    public void setPersonalRoundType(Integer personalRoundType) {
        this.personalRoundType = personalRoundType;
    }

    public Integer getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }

    public Integer getStartComfirmMonth() {
        return startComfirmMonth;
    }

    public void setStartComfirmMonth(Integer startComfirmMonth) {
        this.startComfirmMonth = startComfirmMonth;
    }

    public Integer getEndComfirmMonth() {
        return endComfirmMonth;
    }

    public void setEndComfirmMonth(Integer endComfirmMonth) {
        this.endComfirmMonth = endComfirmMonth;
    }

    public Integer getLastBillMonth() {
        return lastBillMonth;
    }

    public void setLastBillMonth(Integer lastBillMonth) {
        this.lastBillMonth = lastBillMonth;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getCompanyRatioList() {
        return companyRatioList;
    }

    public void setCompanyRatioList(String companyRatioList) {
        this.companyRatioList = companyRatioList;
    }

    public String getCompanyComputeType() {
        return companyComputeType;
    }

    public void setCompanyComputeType(String companyComputeType) {
        this.companyComputeType = companyComputeType;
    }

    public String getCompanyFixedAmount() {
        return companyFixedAmount;
    }

    public void setCompanyFixedAmount(String companyFixedAmount) {
        this.companyFixedAmount = companyFixedAmount;
    }

    public String getPersonalRatioList() {
        return personalRatioList;
    }

    public void setPersonalRatioList(String personalRatioList) {
        this.personalRatioList = personalRatioList;
    }

    public String getPersonalComputeType() {
        return personalComputeType;
    }

    public void setPersonalComputeType(String personalComputeType) {
        this.personalComputeType = personalComputeType;
    }

    public String getPersonalFixedAmount() {
        return personalFixedAmount;
    }

    public void setPersonalFixedAmount(String personalFixedAmount) {
        this.personalFixedAmount = personalFixedAmount;
    }

    public String getItemDicId() {
        return itemDicId;
    }

    public void setItemDicId(String itemDicId) {
        this.itemDicId = itemDicId;
    }

    public BigDecimal getEmpCompanyBase() {
        return empCompanyBase;
    }

    public void setEmpCompanyBase(BigDecimal empCompanyBase) {
        this.empCompanyBase = empCompanyBase;
    }
}