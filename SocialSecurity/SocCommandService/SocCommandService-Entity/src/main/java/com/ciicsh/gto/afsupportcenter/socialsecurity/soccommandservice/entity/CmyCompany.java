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
 * @since 2017-12-11
 */
@TableName("cmy_company")
public class CmyCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户id
     */
    @TableId("company_id")
	private String companyId;
    /**
     * 管理方id
     */
	@TableField("management_id")
	private String managementId;
    /**
     * 公司名称
     */
	private String title;
    /**
     * 营业执照
     */
	@TableField("license_code")
	private String licenseCode;
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

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
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
		return "CmyCompany{" +
			", companyId=" + companyId +
			", managementId=" + managementId +
			", title=" + title +
			", licenseCode=" + licenseCode +
			", isActive=" + isActive +
			", createdBy=" + createdBy +
			", createdTime=" + createdTime +
			", modifiedBy=" + modifiedBy +
			", modifiedTime=" + modifiedTime +
			"}";
	}
}
