package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员月度变更主表
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
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
     * 社保缴费月份，格式是yyyyMM
     */
	@TableField("ss_month")
	private String ssMonth;
    /**
     * 上次计算时间
     */
	@TableField("last_compute_time")
	private LocalTime lastComputeTime;
    /**
     * 计算用户Id
     */
	@TableField("compute_user_id")
	private String computeUserId;
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


	public Long getMonthEmpChangeId() {
		return monthEmpChangeId;
	}

	public void setMonthEmpChangeId(Long monthEmpChangeId) {
		this.monthEmpChangeId = monthEmpChangeId;
	}

	public String getSsMonth() {
		return ssMonth;
	}

	public void setSsMonth(String ssMonth) {
		this.ssMonth = ssMonth;
	}

	public LocalTime getLastComputeTime() {
		return lastComputeTime;
	}

	public void setLastComputeTime(LocalTime lastComputeTime) {
		this.lastComputeTime = lastComputeTime;
	}

	public String getComputeUserId() {
		return computeUserId;
	}

	public void setComputeUserId(String computeUserId) {
		this.computeUserId = computeUserId;
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
		return "SsMonthEmpChange{" +
			", monthEmpChangeId=" + monthEmpChangeId +
			", ssMonth=" + ssMonth +
			", lastComputeTime=" + lastComputeTime +
			", computeUserId=" + computeUserId +
			", comAccountId=" + comAccountId +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
