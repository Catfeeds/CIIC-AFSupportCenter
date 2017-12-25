package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po;

import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 保单号批次表
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-21
 */
@TableName("hm_insurance_policy_batch")
public class InsurancePolicyBatchPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("insurance_policy_num_batch_id")
	private Integer insurancePolicyNumBatchId;
    /**
     * 所属保单号
     */
	@TableField("insurance_policy_num_id")
	private Integer insurancePolicyNumId;
    /**
     * 保单号开始日期
     */
	@TableField("insurance_policy_num_start_date")
	private LocalTime insurancePolicyNumStartDate;
    /**
     * 保单号结束日期
     */
	@TableField("insurance_policy_num_end_date")
	private LocalTime insurancePolicyNumEndDate;
    /**
     * 缴费周期：
1 月缴
2 季缴
3 年缴
     */
	@TableField("payment_cycle")
	private Integer paymentCycle;
    /**
     * 保费计算规则
     */
	@TableField("payment_calculation_rule")
	private String paymentCalculationRule;
    /**
     * 总人数
     */
	@TableField("total_head_count")
	private Integer totalHeadCount;
    /**
     * 总保费
     */
	@TableField("overall_premium")
	private String overallPremium;
    /**
     * 代理费总人数
     */
	@TableField("agency_total_head_count")
	private Integer agencyTotalHeadCount;
    /**
     * 代理费总额
     */
	@TableField("agency_overall_premium")
	private String agencyOverallPremium;
    /**
     * 操作人
     */
	private String operator;
    /**
     * 操作状态：
0-第一步
1-第二步
2-已提交
     */
	@TableField("operation_status")
	private Integer operationStatus;
    /**
     * 操作时间
     */
	@TableField("operating_time")
	private LocalTime operatingTime;
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


	public Integer getInsurancePolicyNumBatchId() {
		return insurancePolicyNumBatchId;
	}

	public void setInsurancePolicyNumBatchId(Integer insurancePolicyNumBatchId) {
		this.insurancePolicyNumBatchId = insurancePolicyNumBatchId;
	}

	public Integer getInsurancePolicyNumId() {
		return insurancePolicyNumId;
	}

	public void setInsurancePolicyNumId(Integer insurancePolicyNumId) {
		this.insurancePolicyNumId = insurancePolicyNumId;
	}

	public LocalTime getInsurancePolicyNumStartDate() {
		return insurancePolicyNumStartDate;
	}

	public void setInsurancePolicyNumStartDate(LocalTime insurancePolicyNumStartDate) {
		this.insurancePolicyNumStartDate = insurancePolicyNumStartDate;
	}

	public LocalTime getInsurancePolicyNumEndDate() {
		return insurancePolicyNumEndDate;
	}

	public void setInsurancePolicyNumEndDate(LocalTime insurancePolicyNumEndDate) {
		this.insurancePolicyNumEndDate = insurancePolicyNumEndDate;
	}

	public Integer getPaymentCycle() {
		return paymentCycle;
	}

	public void setPaymentCycle(Integer paymentCycle) {
		this.paymentCycle = paymentCycle;
	}

	public String getPaymentCalculationRule() {
		return paymentCalculationRule;
	}

	public void setPaymentCalculationRule(String paymentCalculationRule) {
		this.paymentCalculationRule = paymentCalculationRule;
	}

	public Integer getTotalHeadCount() {
		return totalHeadCount;
	}

	public void setTotalHeadCount(Integer totalHeadCount) {
		this.totalHeadCount = totalHeadCount;
	}

	public String getOverallPremium() {
		return overallPremium;
	}

	public void setOverallPremium(String overallPremium) {
		this.overallPremium = overallPremium;
	}

	public Integer getAgencyTotalHeadCount() {
		return agencyTotalHeadCount;
	}

	public void setAgencyTotalHeadCount(Integer agencyTotalHeadCount) {
		this.agencyTotalHeadCount = agencyTotalHeadCount;
	}

	public String getAgencyOverallPremium() {
		return agencyOverallPremium;
	}

	public void setAgencyOverallPremium(String agencyOverallPremium) {
		this.agencyOverallPremium = agencyOverallPremium;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(Integer operationStatus) {
		this.operationStatus = operationStatus;
	}

	public LocalTime getOperatingTime() {
		return operatingTime;
	}

	public void setOperatingTime(LocalTime operatingTime) {
		this.operatingTime = operatingTime;
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
		return "InsurancePolicyBatch{" +
			", insurancePolicyNumBatchId=" + insurancePolicyNumBatchId +
			", insurancePolicyNumId=" + insurancePolicyNumId +
			", insurancePolicyNumStartDate=" + insurancePolicyNumStartDate +
			", insurancePolicyNumEndDate=" + insurancePolicyNumEndDate +
			", paymentCycle=" + paymentCycle +
			", paymentCalculationRule=" + paymentCalculationRule +
			", totalHeadCount=" + totalHeadCount +
			", overallPremium=" + overallPremium +
			", agencyTotalHeadCount=" + agencyTotalHeadCount +
			", agencyOverallPremium=" + agencyOverallPremium +
			", operator=" + operator +
			", operationStatus=" + operationStatus +
			", operatingTime=" + operatingTime +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
