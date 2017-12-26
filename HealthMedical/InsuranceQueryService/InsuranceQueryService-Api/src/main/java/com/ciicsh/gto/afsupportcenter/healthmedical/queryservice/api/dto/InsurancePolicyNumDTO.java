package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.api.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <p>
 * 保单号表
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
@TableName("hm_insurance_policy_num")
public class InsurancePolicyNumDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 保单号（中盈定义）
     */
	@TableId(value="insurance_policy_num_id", type= IdType.AUTO)
	private Integer insurancePolicyNumId;
    /**
     * 保单号名称（健康医疗定义）
     */
	@TableField("insurance_policy_num_name")
	private String insurancePolicyNumName;
    /**
     * 所属保单
     */
	@TableField("insurance_policy_id")
	private Integer insurancePolicyId;
    /**
     * 保单号开始日期
     */
	@TableField("insurance_policy_num_start_date")
	private LocalDate insurancePolicyNumStartDate;
    /**
     * 保单号结束日期
     */
	@TableField("insurance_policy_num_end_date")
	private LocalDate insurancePolicyNumEndDate;
    /**
     * 缴费周期（
1 月缴
2 季缴
3 年缴）
     */
	@TableField("payment_cycle")
	private Integer paymentCycle;
    /**
     * 保费计算规则
（1-固定费率，2-保额*费率，3-不固定保费）
     */
	@TableField("payment_calculation_rule")
	private Integer paymentCalculationRule;
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


	public Integer getInsurancePolicyNumId() {
		return insurancePolicyNumId;
	}

	public void setInsurancePolicyNumId(Integer insurancePolicyNumId) {
		this.insurancePolicyNumId = insurancePolicyNumId;
	}

	public String getInsurancePolicyNumName() {
		return insurancePolicyNumName;
	}

	public void setInsurancePolicyNumName(String insurancePolicyNumName) {
		this.insurancePolicyNumName = insurancePolicyNumName;
	}

	public Integer getInsurancePolicyId() {
		return insurancePolicyId;
	}

	public void setInsurancePolicyId(Integer insurancePolicyId) {
		this.insurancePolicyId = insurancePolicyId;
	}

	public LocalDate getInsurancePolicyNumStartDate() {
		return insurancePolicyNumStartDate;
	}

	public void setInsurancePolicyNumStartDate(LocalDate insurancePolicyNumStartDate) {
		this.insurancePolicyNumStartDate = insurancePolicyNumStartDate;
	}

	public LocalDate getInsurancePolicyNumEndDate() {
		return insurancePolicyNumEndDate;
	}

	public void setInsurancePolicyNumEndDate(LocalDate insurancePolicyNumEndDate) {
		this.insurancePolicyNumEndDate = insurancePolicyNumEndDate;
	}

	public Integer getPaymentCycle() {
		return paymentCycle;
	}

	public void setPaymentCycle(Integer paymentCycle) {
		this.paymentCycle = paymentCycle;
	}

	public Integer getPaymentCalculationRule() {
		return paymentCalculationRule;
	}

	public void setPaymentCalculationRule(Integer paymentCalculationRule) {
		this.paymentCalculationRule = paymentCalculationRule;
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
		return "InsurancePolicyNum{" +
			", insurancePolicyNumId=" + insurancePolicyNumId +
			", insurancePolicyNumName=" + insurancePolicyNumName +
			", insurancePolicyId=" + insurancePolicyId +
			", insurancePolicyNumStartDate=" + insurancePolicyNumStartDate +
			", insurancePolicyNumEndDate=" + insurancePolicyNumEndDate +
			", paymentCycle=" + paymentCycle +
			", paymentCalculationRule=" + paymentCalculationRule +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
