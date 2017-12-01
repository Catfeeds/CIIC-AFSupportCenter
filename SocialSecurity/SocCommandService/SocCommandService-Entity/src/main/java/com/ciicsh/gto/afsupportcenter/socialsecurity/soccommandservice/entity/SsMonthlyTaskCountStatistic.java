package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 首页中，系统用户执行的任务单数的月度分类统计。
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@TableName("ss_monthly_task_count_statistic")
public class SsMonthlyTaskCountStatistic implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="record_id", type= IdType.AUTO)
	private Long recordId;
    /**
     * 多租户Id
     */
	@TableField("customer_id")
	private String customerId;
    /**
     * 用户所属部门Id
     */
	@TableField("department_id")
	private String departmentId;
    /**
     * 所属年月YYYY
     */
	@TableField("ss_year")
	private Integer ssYear;
    /**
     * 季度1-4
     */
	@TableField("ss_quarter")
	private Integer ssQuarter;
    /**
     * 月1-12
     */
	@TableField("ss_month")
	private Integer ssMonth;
    /**
     * 系统用户Id
     */
	@TableField("sys_user_id")
	private String sysUserId;
    /**
     * 系统用户姓名
     */
	@TableField("sys_user_name")
	private String sysUserName;
    /**
     * 环比上月
     */
	@TableField("vs_last_month")
	private String vsLastMonth;
    /**
     * 任务单总数
     */
	@TableField("task_count")
	private Integer taskCount;
    /**
     * 统计分类：1 - 新办 2 - 调整补缴 3 - 转出 4 - 批退
     */
	@TableField("task_category")
	private Integer taskCategory;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
	@TableField("created_time")
	private LocalTime createdTime;
	@TableField("modified_time")
	private LocalTime modifiedTime;
	@TableField("created_by")
	private String createdBy;
    /**
     * 修改者登录名
     */
	@TableField("modified_by")
	private String modifiedBy;


	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getSsYear() {
		return ssYear;
	}

	public void setSsYear(Integer ssYear) {
		this.ssYear = ssYear;
	}

	public Integer getSsQuarter() {
		return ssQuarter;
	}

	public void setSsQuarter(Integer ssQuarter) {
		this.ssQuarter = ssQuarter;
	}

	public Integer getSsMonth() {
		return ssMonth;
	}

	public void setSsMonth(Integer ssMonth) {
		this.ssMonth = ssMonth;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public String getVsLastMonth() {
		return vsLastMonth;
	}

	public void setVsLastMonth(String vsLastMonth) {
		this.vsLastMonth = vsLastMonth;
	}

	public Integer getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public Integer getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(Integer taskCategory) {
		this.taskCategory = taskCategory;
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
		return "SsMonthlyTaskCountStatistic{" +
			", recordId=" + recordId +
			", customerId=" + customerId +
			", departmentId=" + departmentId +
			", ssYear=" + ssYear +
			", ssQuarter=" + ssQuarter +
			", ssMonth=" + ssMonth +
			", sysUserId=" + sysUserId +
			", sysUserName=" + sysUserName +
			", vsLastMonth=" + vsLastMonth +
			", taskCount=" + taskCount +
			", taskCategory=" + taskCategory +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
