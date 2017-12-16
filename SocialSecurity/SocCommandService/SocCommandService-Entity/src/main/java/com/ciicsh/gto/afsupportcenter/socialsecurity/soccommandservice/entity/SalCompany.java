package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户基础表
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-16
 */
@TableName("sal_company")
public class SalCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户ID
     */
    @TableId("company_id")
	private String companyId;
    /**
     * 管理方ID
     */
	@TableField("management_id")
	private String managementId;
    /**
     * 公司名称
     */
	private String title;
    /**
     * 客户证件类型（1：三证  2：三证合一  3：其他）
     */
	@TableField("license_type")
	private String licenseType;
    /**
     * 营业执照编号
     */
	@TableField("license_code")
	private String licenseCode;
    /**
     * 营业执照生效开始时间
     */
	@TableField("license_start_time")
	private LocalDateTime licenseStartTime;
    /**
     * 营业执照生效结束时间
     */
	@TableField("license_end_time")
	private LocalDateTime licenseEndTime;
    /**
     * 组织机构编码
     */
	@TableField("organization_code")
	private String organizationCode;
    /**
     * 组织机构证件生效开始时间
     */
	@TableField("organization_start_time")
	private LocalDateTime organizationStartTime;
    /**
     * 组织机构证件生效结束时间
     */
	@TableField("organization_end_time")
	private LocalDateTime organizationEndTime;
    /**
     * 税务登记证号
     */
	@TableField("tax_registration_code")
	private String taxRegistrationCode;
    /**
     * 注册地址
     */
	@TableField("registered_address")
	private String registeredAddress;
    /**
     * 注册资金
     */
	@TableField("registered_capital")
	private String registeredCapital;
    /**
     * 法人
     */
	@TableField("legal_person")
	private String legalPerson;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建者
     */
	@TableField("created_by")
	private String createdBy;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalDateTime createdTime;
    /**
     * 最后修改者
     */
	@TableField("modified_by")
	private String modifiedBy;
    /**
     * 最后修改时间
     */
	@TableField("modified_time")
	private LocalDateTime modifiedTime;


	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getManagementId() {
		return managementId;
	}

	public void setManagementId(String managementId) {
		this.managementId = managementId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public LocalDateTime getLicenseStartTime() {
		return licenseStartTime;
	}

	public void setLicenseStartTime(LocalDateTime licenseStartTime) {
		this.licenseStartTime = licenseStartTime;
	}

	public LocalDateTime getLicenseEndTime() {
		return licenseEndTime;
	}

	public void setLicenseEndTime(LocalDateTime licenseEndTime) {
		this.licenseEndTime = licenseEndTime;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public LocalDateTime getOrganizationStartTime() {
		return organizationStartTime;
	}

	public void setOrganizationStartTime(LocalDateTime organizationStartTime) {
		this.organizationStartTime = organizationStartTime;
	}

	public LocalDateTime getOrganizationEndTime() {
		return organizationEndTime;
	}

	public void setOrganizationEndTime(LocalDateTime organizationEndTime) {
		this.organizationEndTime = organizationEndTime;
	}

	public String getTaxRegistrationCode() {
		return taxRegistrationCode;
	}

	public void setTaxRegistrationCode(String taxRegistrationCode) {
		this.taxRegistrationCode = taxRegistrationCode;
	}

	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "SalCompany{" +
			", companyId=" + companyId +
			", managementId=" + managementId +
			", title=" + title +
			", licenseType=" + licenseType +
			", licenseCode=" + licenseCode +
			", licenseStartTime=" + licenseStartTime +
			", licenseEndTime=" + licenseEndTime +
			", organizationCode=" + organizationCode +
			", organizationStartTime=" + organizationStartTime +
			", organizationEndTime=" + organizationEndTime +
			", taxRegistrationCode=" + taxRegistrationCode +
			", registeredAddress=" + registeredAddress +
			", registeredCapital=" + registeredCapital +
			", legalPerson=" + legalPerson +
			", isActive=" + isActive +
			", createdBy=" + createdBy +
			", createdTime=" + createdTime +
			", modifiedBy=" + modifiedBy +
			", modifiedTime=" + modifiedTime +
			"}";
	}
}
