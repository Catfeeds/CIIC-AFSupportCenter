package com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员公积金历史月份调整差异表
 * </p>
 */
@TableName("hf_archive_base_adjust")
public class HfArchiveBaseAdjust implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="emp_base_adjust_id", type= IdType.AUTO)
	private Long empBaseAdjustId;
    /**
     * 外键，雇员公积金档案Id
     */
	@TableField("emp_archive_id")
	private Long empArchiveId;
    /**
     * 雇员任务单ID
     */
	@TableField("emp_task_id")
	private Long empTaskId;
    /**
     * 客户主表ID
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 雇员主表ID
     */
    @TableField("employee_id")
    private String employeeId;
    /**
     * 调整新基数
     */
	@TableField("new_base_amount")
	private BigDecimal newBaseAmount;
    /**
     * 所属公积金汇缴月份yyyyMM
     */
	@TableField("hf_month")
	private String hfMonth;
    /**
     * 开始月份yyyyMM
     */
	@TableField("start_month")
	private String startMonth;
    /**
     * 截至月份yyyyMM
     */
	@TableField("end_month")
	private String endMonth;
    /**
     * 比例
     */
	private BigDecimal ratio;
    /**
     * 和上一次年调后的企业缴纳部分金额的差额合计
     */
	@TableField("com_diff_sum_amount")
	private BigDecimal comDiffSumAmount;
    /**
     * 和上一次年调后的雇员缴纳部分金额的差额合计
     */
	@TableField("emp_diff_sum_amount")
	private BigDecimal empDiffSumAmount;
    /**
     * 企业个人缴纳金额的差额合计
     */
	@TableField("comemp_sum_diff_amount")
	private BigDecimal comempSumDiffAmount;
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

	public Long getEmpArchiveId() {
		return empArchiveId;
	}

	public void setEmpArchiveId(Long empArchiveId) {
		this.empArchiveId = empArchiveId;
	}

	public Long getEmpTaskId() {
		return empTaskId;
	}

	public void setEmpTaskId(Long empTaskId) {
		this.empTaskId = empTaskId;
	}

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getNewBaseAmount() {
		return newBaseAmount;
	}

	public void setNewBaseAmount(BigDecimal newBaseAmount) {
		this.newBaseAmount = newBaseAmount;
	}

	public String getHfMonth() {
		return hfMonth;
	}

	public void setHfMonth(String hfMonth) {
		this.hfMonth = hfMonth;
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

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
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

	public BigDecimal getComempSumDiffAmount() {
		return comempSumDiffAmount;
	}

	public void setComempSumDiffAmount(BigDecimal comempSumDiffAmount) {
		this.comempSumDiffAmount = comempSumDiffAmount;
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
		return "HfArchiveBaseAdjust{" +
			", empBaseAdjustId=" + empBaseAdjustId +
			", empArchiveId=" + empArchiveId +
			", empTaskId=" + empTaskId +
            ", companyId=" + companyId +
            ", employeeId=" + employeeId +
			", newBaseAmount=" + newBaseAmount +
			", hfMonth=" + hfMonth +
			", startMonth=" + startMonth +
			", endMonth=" + endMonth +
			", ratio=" + ratio +
			", comDiffSumAmount=" + comDiffSumAmount +
			", empDiffSumAmount=" + empDiffSumAmount +
			", comempSumDiffAmount=" + comempSumDiffAmount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
