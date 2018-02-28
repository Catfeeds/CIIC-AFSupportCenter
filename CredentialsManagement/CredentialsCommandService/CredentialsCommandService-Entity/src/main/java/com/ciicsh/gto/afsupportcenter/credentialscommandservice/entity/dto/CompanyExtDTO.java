package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 10:29 2018/1/19
 */
public class CompanyExtDTO implements Serializable{

    /**
     * 主键：客户数据维护id
     */
    private Long companyExtId;
    /**
     * 客户id
     */
    private String companyId;
    /**
     * 证件类型
     */
    private Integer credentialsType;
    /**
     * 机构政策id
     */
    private Integer orgPoilcyId;
    /**
     * 办理机构
     */
    private String name;
    /**
     * 操作账号
     */
    private String operateAccount;
    /**
     * 操作密码
     */
    private String operatePwd;
    /**
     * 操作方式 (1：待审代交、2：待审不代交、3：不待审代交)
     */
    private String operateType;
    /**
     * 费用类型(1：免费、2：常规收费、3：特殊收费)
     */
    private String chargeType;
    /**
     * 特殊收费备注
     */
    private String specialChargeRemark;
    /**
     * 支付方式(1：台帐、2：员工自付)
     */
    private String payType;
    /**
     * 介绍信
     */
    private Boolean introduceMail;
    /**
     * 网上联系人身份证复印件
     */
    private Boolean onlineContactIdCard;
    /**
     * 网上联系人是否秘书台人员
     */
    private Boolean onlineContactIsSecretariat;
    /**
     * 网上联系人
     */
    private String onlineContact;
    /**
     * 营业执照复印件或三证合一复印件
     */
    private Boolean businessLicence;
    /**
     * 机构代码证复印件
     */
    private Boolean organizationCode;
    /**
     * 外商企业批准证书复印件
     */
    private Boolean foreignBusinessApprovalCertificate;
    /**
     * 工商局企业更名通知复印件
     */
    private Boolean businessRenameNotice;
    /**
     * 特殊情况备注
     */
    private String specialMaterialRemark;
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date createdTime;
    /**
     * 最后更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
    private String modifiedBy;

    /**
     * 证件类型UI
     */
    private String lab;
    /**
     * 操作方式UI
     */
    private String operateTypeN;
    /**
     *费用类型UI
     */
    private String chargeTypeN;
    /**
     *支付方式UI
     */
    private String payTypeN;

    public Integer getOrgPoilcyId() {
        return orgPoilcyId;
    }

    public void setOrgPoilcyId(Integer orgPoilcyId) {
        this.orgPoilcyId = orgPoilcyId;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getOperateTypeN() {
        return operateTypeN;
    }

    public void setOperateTypeN(String operateTypeN) {
        this.operateTypeN = operateTypeN;
    }

    public String getChargeTypeN() {
        return chargeTypeN;
    }

    public void setChargeTypeN(String chargeTypeN) {
        this.chargeTypeN = chargeTypeN;
    }

    public String getPayTypeN() {
        return payTypeN;
    }

    public void setPayTypeN(String payTypeN) {
        this.payTypeN = payTypeN;
    }

    public Long getCompanyExtId() {
        return companyExtId;
    }

    public void setCompanyExtId(Long companyExtId) {
        this.companyExtId = companyExtId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(Integer credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperateAccount() {
        return operateAccount;
    }

    public void setOperateAccount(String operateAccount) {
        this.operateAccount = operateAccount;
    }

    public String getOperatePwd() {
        return operatePwd;
    }

    public void setOperatePwd(String operatePwd) {
        this.operatePwd = operatePwd;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getSpecialChargeRemark() {
        return specialChargeRemark;
    }

    public void setSpecialChargeRemark(String specialChargeRemark) {
        this.specialChargeRemark = specialChargeRemark;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Boolean getIntroduceMail() {
        return introduceMail;
    }

    public void setIntroduceMail(Boolean introduceMail) {
        this.introduceMail = introduceMail;
    }

    public Boolean getOnlineContactIdCard() {
        return onlineContactIdCard;
    }

    public void setOnlineContactIdCard(Boolean onlineContactIdCard) {
        this.onlineContactIdCard = onlineContactIdCard;
    }

    public Boolean getOnlineContactIsSecretariat() {
        return onlineContactIsSecretariat;
    }

    public void setOnlineContactIsSecretariat(Boolean onlineContactIsSecretariat) {
        this.onlineContactIsSecretariat = onlineContactIsSecretariat;
    }

    public String getOnlineContact() {
        return onlineContact;
    }

    public void setOnlineContact(String onlineContact) {
        this.onlineContact = onlineContact;
    }

    public Boolean getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(Boolean businessLicence) {
        this.businessLicence = businessLicence;
    }

    public Boolean getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(Boolean organizationCode) {
        this.organizationCode = organizationCode;
    }

    public Boolean getForeignBusinessApprovalCertificate() {
        return foreignBusinessApprovalCertificate;
    }

    public void setForeignBusinessApprovalCertificate(Boolean foreignBusinessApprovalCertificate) {
        this.foreignBusinessApprovalCertificate = foreignBusinessApprovalCertificate;
    }

    public Boolean getBusinessRenameNotice() {
        return businessRenameNotice;
    }

    public void setBusinessRenameNotice(Boolean businessRenameNotice) {
        this.businessRenameNotice = businessRenameNotice;
    }

    public String getSpecialMaterialRemark() {
        return specialMaterialRemark;
    }

    public void setSpecialMaterialRemark(String specialMaterialRemark) {
        this.specialMaterialRemark = specialMaterialRemark;
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

    @Override
    public String toString() {
        return "CompanyExtDTO{" +
            "companyExtId=" + companyExtId +
            ", companyId='" + companyId + '\'' +
            ", credentialsType=" + credentialsType +
            ", orgPoilcyId=" + orgPoilcyId +
            ", name='" + name + '\'' +
            ", operateAccount='" + operateAccount + '\'' +
            ", operatePwd='" + operatePwd + '\'' +
            ", operateType=" + operateType +
            ", chargeType=" + chargeType +
            ", specialChargeRemark='" + specialChargeRemark + '\'' +
            ", payType=" + payType +
            ", introduceMail=" + introduceMail +
            ", onlineContactIdCard=" + onlineContactIdCard +
            ", onlineContactIsSecretariat=" + onlineContactIsSecretariat +
            ", onlineContact='" + onlineContact + '\'' +
            ", businessLicence=" + businessLicence +
            ", organizationCode=" + organizationCode +
            ", foreignBusinessApprovalCertificate=" + foreignBusinessApprovalCertificate +
            ", businessRenameNotice=" + businessRenameNotice +
            ", specialMaterialRemark='" + specialMaterialRemark + '\'' +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            '}';
    }
}
