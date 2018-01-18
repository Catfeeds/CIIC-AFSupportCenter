package com.ciicsh.gto.afsupportcenter.socjob.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 雇员月度变更主表
 * </p>
 */
@TableName("ss_month_emp_change")
public class SsMonthEmpChange implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="month_emp_change_id", type= IdType.AUTO)
	private Long monthEmpChangeId;
    /**
     * 外键：对账单Id
     */
	@TableField("statement_id")
	private Long statementId;
    /**
     * 社保缴费月份，格式是yyyyMM
     */
	@TableField("ss_month")
	private String ssMonth;
    /**
     * 上次计算时间
     */
	@TableField("last_compute_time")
	private LocalDateTime lastComputeTime;
    /**
     * 计算用户Id
     */
	@TableField("compute_user_id")
	private String computeUserId;
    /**
     * 变更汇总表类型:YYS\GSY
     */
	@TableField("compute_type")
	private String computeType;
    /**
     * 客户账号Id
     */
	@TableField("com_account_id")
	private Long comAccountId;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
	private LocalDateTime modifiedTime;
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


	public Long getMonthEmpChangeId() {
		return monthEmpChangeId;
	}

	public void setMonthEmpChangeId(Long monthEmpChangeId) {
		this.monthEmpChangeId = monthEmpChangeId;
	}

	public Long getStatementId() {
		return statementId;
	}

	public void setStatementId(Long statementId) {
		this.statementId = statementId;
	}

	public String getSsMonth() {
		return ssMonth;
	}

	public void setSsMonth(String ssMonth) {
		this.ssMonth = ssMonth;
	}

	public LocalDateTime getLastComputeTime() {
		return lastComputeTime;
	}

	public void setLastComputeTime(LocalDateTime lastComputeTime) {
		this.lastComputeTime = lastComputeTime;
	}

	public String getComputeUserId() {
		return computeUserId;
	}

	public void setComputeUserId(String computeUserId) {
		this.computeUserId = computeUserId;
	}

	public String getComputeType() {
		return computeType;
	}

	public void setComputeType(String computeType) {
		this.computeType = computeType;
	}

	public Long getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(Long comAccountId) {
		this.comAccountId = comAccountId;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
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
		return "SsMonthEmpChange{" +
			", monthEmpChangeId=" + monthEmpChangeId +
			", statementId=" + statementId +
			", ssMonth=" + ssMonth +
			", lastComputeTime=" + lastComputeTime +
			", computeUserId=" + computeUserId +
			", computeType=" + computeType +
			", comAccountId=" + comAccountId +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
