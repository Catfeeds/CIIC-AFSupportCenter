package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 保单表
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
@TableName("hm_insurance_policy")
public class InsurancePolicyPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 保单编号
     */
	@TableId(value="insurance_policy_id", type= IdType.AUTO)
	private Integer insurancePolicyId;
    /**
     * 保单名称
     */
	@TableField("insurance_policy_name")
	private String insurancePolicyName;
    /**
     * 保险公司编号
     */
	@TableField("insurance_company_id")
	private Integer insuranceCompanyId;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalTime createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
	private LocalTime modifiedTime;
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


	public Integer getInsurancePolicyId() {
		return insurancePolicyId;
	}

	public void setInsurancePolicyId(Integer insurancePolicyId) {
		this.insurancePolicyId = insurancePolicyId;
	}

	public String getInsurancePolicyName() {
		return insurancePolicyName;
	}

	public void setInsurancePolicyName(String insurancePolicyName) {
		this.insurancePolicyName = insurancePolicyName;
	}

	public Integer getInsuranceCompanyId() {
		return insuranceCompanyId;
	}

	public void setInsuranceCompanyId(Integer insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalTime modifiedTime) {
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
		return "InsurancePolicy{" +
			", insurancePolicyId=" + insurancePolicyId +
			", insurancePolicyName=" + insurancePolicyName +
			", insuranceCompanyId=" + insuranceCompanyId +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
