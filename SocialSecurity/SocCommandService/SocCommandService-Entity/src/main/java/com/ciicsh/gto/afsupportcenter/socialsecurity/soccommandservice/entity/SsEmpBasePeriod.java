package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员正常汇缴社保的基数分段表(每段一个基数)， 每次社保基数变更（比如年调）或补缴都会更新这张表
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-12
 */
@TableName("ss_emp_base_period")
public class SsEmpBasePeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="emp_base_period_id", type= IdType.AUTO)
	private Long empBasePeriodId;
    /**
     * 外键，雇员本地社保档案Id
     */
	@TableField("emp_archived_id")
	private String empArchivedId;
    /**
     * 本地社保的雇员任务单Id
     */
	@TableField("emp_task_id")
	private String empTaskId;
    /**
     * 基数, 五险合一(基数一致）时有效
     */
	@TableField("base_amount")
	private BigDecimal baseAmount;
    /**
     * 缴费段开始月份
     */
	@TableField("start_month")
	private String startMonth;
    /**
     * 缴费段结束月份
     */
	@TableField("end_month")
	private String endMonth;
    /**
     * 缴纳的社保月份
     */
	@TableField("ss_month")
	private String ssMonth;
    /**
     * 汇缴方式:1 - 正常 2 - 补缴
     */
	@TableField("remit_way")
	private Integer remitWay;
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


	public Long getEmpBasePeriodId() {
		return empBasePeriodId;
	}

	public void setEmpBasePeriodId(Long empBasePeriodId) {
		this.empBasePeriodId = empBasePeriodId;
	}

	public String getEmpArchivedId() {
		return empArchivedId;
	}

	public void setEmpArchivedId(String empArchivedId) {
		this.empArchivedId = empArchivedId;
	}

	public String getEmpTaskId() {
		return empTaskId;
	}

	public void setEmpTaskId(String empTaskId) {
		this.empTaskId = empTaskId;
	}

	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getSsMonth() {
		return ssMonth;
	}

	public void setSsMonth(String ssMonth) {
		this.ssMonth = ssMonth;
	}

	public Integer getRemitWay() {
		return remitWay;
	}

	public void setRemitWay(Integer remitWay) {
		this.remitWay = remitWay;
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
		return "SsEmpBasePeriod{" +
			", empBasePeriodId=" + empBasePeriodId +
			", empArchivedId=" + empArchivedId +
			", empTaskId=" + empTaskId +
			", baseAmount=" + baseAmount +
			", startMonth=" + startMonth +
			", endMonth=" + endMonth +
			", ssMonth=" + ssMonth +
			", remitWay=" + remitWay +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
