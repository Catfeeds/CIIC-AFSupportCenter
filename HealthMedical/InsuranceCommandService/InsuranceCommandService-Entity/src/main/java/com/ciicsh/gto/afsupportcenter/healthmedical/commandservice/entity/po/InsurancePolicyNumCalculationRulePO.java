package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 保单号保费计算规则表
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-29
 */
@TableName("hm_insurance_policy_num_calculation_rule")
public class InsurancePolicyNumCalculationRulePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
	@TableId(value="rule_id", type= IdType.AUTO)
	private Integer ruleId;
    /**
     * 保单号（中盈定义）
     */
	@TableField("insurance_policy_num_id")
	private Integer insurancePolicyNumId;
    /**
     * 理赔比例
     */
	@TableField("claim_rate")
	private BigDecimal claimRate;
    /**
     * 收费
     */
	private BigDecimal charge;
    /**
     * 代理费
     */
	@TableField("agency_fee")
	private BigDecimal agencyFee;
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


	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public Integer getInsurancePolicyNumId() {
		return insurancePolicyNumId;
	}

	public void setInsurancePolicyNumId(Integer insurancePolicyNumId) {
		this.insurancePolicyNumId = insurancePolicyNumId;
	}

	public BigDecimal getClaimRate() {
		return claimRate;
	}

	public void setClaimRate(BigDecimal claimRate) {
		this.claimRate = claimRate;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public BigDecimal getAgencyFee() {
		return agencyFee;
	}

	public void setAgencyFee(BigDecimal agencyFee) {
		this.agencyFee = agencyFee;
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
		return "InsurancePolicyNumCalculationRule{" +
			", ruleId=" + ruleId +
			", insurancePolicyNumId=" + insurancePolicyNumId +
			", claimRate=" + claimRate +
			", charge=" + charge +
			", agencyFee=" + agencyFee +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
