package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: guwei
 * @Description: 客户数据维护（客户扩展表）
 * @Date: Created in 15:08 2018/1/15
 */
@TableName("cm_company_ext")
public class CompanyExt extends Model<CompanyExt> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键：客户数据维护id
     */
    @TableId(value="company_ext_id", type= IdType.AUTO)
    private Long companyExtId;
    /**
     * 客户id
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 证件类型
     */
    @TableField("credentials_type")
    private Integer credentialsType;
    /**
     * 办理机构
     */
    private String name;
    /**
     * 操作账号
     */
    @TableField("operate_account")
    private String operateAccount;
    /**
     * 操作密码
     */
    @TableField("operate_pwd")
    private String operatePwd;
    /**
     * 操作方式 (1：待审代交、2：待审不代交、3：不待审代交)
     */
    @TableField("operate_type")
    private Integer operateType;
    /**
     * 费用类型(1：免费、2：常规收费、3：特殊收费)
     */
    @TableField("charge _type")
    private Integer chargeType;
    /**
     * 特殊收费备注
     */
    @TableField("special_charge_remark")
    private String specialChargeRemark;
    /**
     * 支付方式(1：台帐、2：员工自付)
     */
    @TableField("pay_type")
    private Integer payType;
    /**
     * 介绍信
     */
    @TableField("introduce_mail")
    private Boolean introduceMail;
    /**
     * 网上联系人身份证复印件
     */
    @TableField("online_contact_id_card")
    private Boolean onlineContactIdCard;
    /**
     * 网上联系人是否秘书台人员
     */
    @TableField("online_contact_is_secretariat")
    private Boolean onlineContactIsSecretariat;
    /**
     * 网上联系人
     */
    @TableField("online_contact")
    private String onlineContact;
    /**
     * 营业执照复印件或三证合一复印件
     */
    @TableField("business_licence")
    private Boolean businessLicence;
    /**
     * 机构代码证复印件
     */
    @TableField("organization_code")
    private Boolean organizationCode;
    /**
     * 外商企业批准证书复印件
     */
    @TableField("foreign_business_approval_certificate")
    private Boolean foreignBusinessApprovalCertificate;
    /**
     * 工商局企业更名通知复印件
     */
    @TableField("business_rename_notice")
    private Boolean businessRenameNotice;
    /**
     * 特殊情况备注
     */
    @TableField("special_material_remark")
    private String specialMaterialRemark;
    /**
     * 是否可用
     */
    @TableField("is_active")
    private Boolean isActive;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;
    /**
     * 最后更新时间
     */
    @TableField("modified_time")
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    @TableField("created_by")
    private String createdBy;
    /**
     * 修改者登录名
     */
    @TableField("modified_by")
    private String modifiedBy;


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

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public String getSpecialChargeRemark() {
        return specialChargeRemark;
    }

    public void setSpecialChargeRemark(String specialChargeRemark) {
        this.specialChargeRemark = specialChargeRemark;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
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

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
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
    protected Serializable pkVal() {
        return this.companyExtId;
    }

    @Override
    public String toString() {
        return "CompanyExt{" +
            "companyExtId=" + companyExtId +
            ", companyId=" + companyId +
            ", credentialsType=" + credentialsType +
            ", name=" + name +
            ", operateAccount=" + operateAccount +
            ", operatePwd=" + operatePwd +
            ", operateType=" + operateType +
            ", charge Type=" + chargeType +
            ", specialChargeRemark=" + specialChargeRemark +
            ", payType=" + payType +
            ", introduceMail=" + introduceMail +
            ", onlineContactIdCard=" + onlineContactIdCard +
            ", onlineContactIsSecretariat=" + onlineContactIsSecretariat +
            ", onlineContact=" + onlineContact +
            ", businessLicence=" + businessLicence +
            ", organizationCode=" + organizationCode +
            ", foreignBusinessApprovalCertificate=" + foreignBusinessApprovalCertificate +
            ", businessRenameNotice=" + businessRenameNotice +
            ", specialMaterialRemark=" + specialMaterialRemark +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }

}
