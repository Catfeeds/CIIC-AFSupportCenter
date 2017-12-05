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
 * 雇员社保基数调整历史月差异表
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-05
 */
@TableName("ss_emp_base_adjust")
public class SsEmpBaseAdjust implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="emp_base_adjust_id", type= IdType.AUTO)
	private Long empBaseAdjustId;
    /**
     * 外键，雇员社保档案Id
     */
	@TableField("emp_archived_id")
	private String empArchivedId;
    /**
     * 雇员本地社保任务单Id
     */
	@TableField("emp_task_id")
	private String empTaskId;
    /**
     * 处理方式 1 -网上申报 2 柜面办理
     */
	@TableField("process_way")
	private Integer processWay;
    /**
     * 调整新基数
     */
	@TableField("new_base_amount")
	private BigDecimal newBaseAmount;
    /**
     * 社保执行月份yyyyMM
     */
	@TableField("ss_month")
	private String ssMonth;
    /**
     * 开始月份yyyyMM
     */
	@TableField("start_month")
	private String startMonth;
    /**
     * 结束月份yyyyMM
     */
	@TableField("end_month")
	private String endMonth;
    /**
     * 和上一次调整后的企业缴纳部分金额的差额合计
     */
	@TableField("com_diff_sum_amount")
	private BigDecimal comDiffSumAmount;
    /**
     * 和上一次调整后的雇员缴纳部分金额的差额合计
     */
	@TableField("emp_diff_sum_amount")
	private BigDecimal empDiffSumAmount;
    /**
     * 企业个人缴纳金额的差额合计
     */
	@TableField("comemp_diff_amount")
	private BigDecimal comempDiffAmount;
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


	public Long getEmpBaseAdjustId() {
		return empBaseAdjustId;
	}

	public void setEmpBaseAdjustId(Long empBaseAdjustId) {
		this.empBaseAdjustId = empBaseAdjustId;
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

	public Integer getProcessWay() {
		return processWay;
	}

	public void setProcessWay(Integer processWay) {
		this.processWay = processWay;
	}

	public BigDecimal getNewBaseAmount() {
		return newBaseAmount;
	}

	public void setNewBaseAmount(BigDecimal newBaseAmount) {
		this.newBaseAmount = newBaseAmount;
	}

	public String getSsMonth() {
		return ssMonth;
	}

	public void setSsMonth(String ssMonth) {
		this.ssMonth = ssMonth;
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

	public BigDecimal getComDiffSumAmount() {
		return comDiffSumAmount;
	}

	public void setComDiffSumAmount(BigDecimal comDiffSumAmount) {
		this.comDiffSumAmount = comDiffSumAmount;
	}

	public BigDecimal getEmpDiffSumAmount() {
		return empDiffSumAmount;
	}

	public void setEmpDiffSumAmount(BigDecimal empDiffSumAmount) {
		this.empDiffSumAmount = empDiffSumAmount;
	}

	public BigDecimal getComempDiffAmount() {
		return comempDiffAmount;
	}

	public void setComempDiffAmount(BigDecimal comempDiffAmount) {
		this.comempDiffAmount = comempDiffAmount;
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
		return "SsEmpBaseAdjust{" +
			", empBaseAdjustId=" + empBaseAdjustId +
			", empArchivedId=" + empArchivedId +
			", empTaskId=" + empTaskId +
			", processWay=" + processWay +
			", newBaseAmount=" + newBaseAmount +
			", ssMonth=" + ssMonth +
			", startMonth=" + startMonth +
			", endMonth=" + endMonth +
			", comDiffSumAmount=" + comDiffSumAmount +
			", empDiffSumAmount=" + empDiffSumAmount +
			", comempDiffAmount=" + comempDiffAmount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
