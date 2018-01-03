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
 * 保单号批次表
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-29
 */
@TableName("hm_insurance_policy_batch")
public class InsurancePolicyBatchPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 保单号批次ID
     */
	@TableId(value="insurance_policy_num_batch_id", type= IdType.AUTO)
	private Integer insurancePolicyNumBatchId;
    /**
     * 所属保单号
     */
	@TableField("insurance_policy_num_id")
	private Integer insurancePolicyNumId;
    /**
     * 费用所属期
（由于缴费周期的不一样存放的信息含义不一样:
按月：201701,201702
按季度：201701,201702
按年：2017,2018）
     */
	@TableField("expense_period")
	private Integer expensePeriod;
    /**
     * 总人数
     */
	@TableField("total_head_count")
	private Integer totalHeadCount;
    /**
     * 总保费
     */
	@TableField("overall_premium")
	private BigDecimal overallPremium;
    /**
     * 代理费总人数
     */
	@TableField("agency_total_head_count")
	private Integer agencyTotalHeadCount;
    /**
     * 代理费总额
     */
	@TableField("agency_overall_premium")
	private BigDecimal agencyOverallPremium;
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

	public Integer getExpensePeriod() {
		return expensePeriod;
	}

	public void setExpensePeriod(Integer expensePeriod) {
		this.expensePeriod = expensePeriod;
	}

	public Integer getTotalHeadCount() {
		return totalHeadCount;
	}

	public void setTotalHeadCount(Integer totalHeadCount) {
		this.totalHeadCount = totalHeadCount;
	}

	public BigDecimal getOverallPremium() {
		return overallPremium;
	}

	public void setOverallPremium(BigDecimal overallPremium) {
		this.overallPremium = overallPremium;
	}

	public Integer getAgencyTotalHeadCount() {
		return agencyTotalHeadCount;
	}

	public void setAgencyTotalHeadCount(Integer agencyTotalHeadCount) {
		this.agencyTotalHeadCount = agencyTotalHeadCount;
	}

	public BigDecimal getAgencyOverallPremium() {
		return agencyOverallPremium;
	}

	public void setAgencyOverallPremium(BigDecimal agencyOverallPremium) {
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
			", expensePeriod=" + expensePeriod +
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
