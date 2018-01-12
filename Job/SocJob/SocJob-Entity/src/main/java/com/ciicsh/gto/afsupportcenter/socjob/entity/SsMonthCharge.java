package com.ciicsh.gto.afsupportcenter.socjob.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 月度缴费明细
系统在每月26日晚上自动生成每月的标准和非标明细数据，用户也可重新生成，
 * </p>
 */
@TableName("ss_month_charge")
public class SsMonthCharge implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="month_charge_id", type= IdType.AUTO)
	private Long monthChargeId;
    /**
     * 外键：企业社保账户
     */
	@TableField("com_account_id")
	private Long comAccountId;
    /**
     * 所属社保月份，格式为yyyyMM
     */
	@TableField("ss_month_belong")
	private String ssMonthBelong;
    /**
     * 实际社保缴纳发生月份，格式为yyyyMM
     */
	@TableField("ss_month")
	private String ssMonth;
    /**
     * 冗余EmployeeId
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 外键,雇员社保档案Id
     */
	@TableField("emp_archive_id")
	private String empArchiveId;
    /**
     * 社保基数
     */
	@TableField("base_amount")
	private BigDecimal baseAmount;
    /**
     * 总金额
     */
	@TableField("total_amount")
	private BigDecimal totalAmount;
    /**
     * 费用种类：1标准 2 新进 3 转入  4 补缴 5 调整 6转出
     */
	@TableField("cost_category")
	private Integer costCategory;
    /**
     * 财务接口要求的雇员支付状态
     */
	@TableField("emp_payment_status")
	private Integer empPaymentStatus;
    /**
     * 是否有效, 0-无效 1-有效
     */
	@TableField("is_active")
	private Boolean isActive;
	@TableField("created_time")
	private LocalDateTime createdTime;
	@TableField("modified_time")
	private LocalDateTime modifiedTime;
	@TableField("created_by")
	private String createdBy;
	@TableField("modified_by")
	private String modifiedBy;


	public Long getMonthChargeId() {
		return monthChargeId;
	}

	public void setMonthChargeId(Long monthChargeId) {
		this.monthChargeId = monthChargeId;
	}

	public Long getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(Long comAccountId) {
		this.comAccountId = comAccountId;
	}

	public String getSsMonthBelong() {
		return ssMonthBelong;
	}

	public void setSsMonthBelong(String ssMonthBelong) {
		this.ssMonthBelong = ssMonthBelong;
	}

	public String getSsMonth() {
		return ssMonth;
	}

	public void setSsMonth(String ssMonth) {
		this.ssMonth = ssMonth;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpArchiveId() {
		return empArchiveId;
	}

	public void setEmpArchiveId(String empArchiveId) {
		this.empArchiveId = empArchiveId;
	}

	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getCostCategory() {
		return costCategory;
	}

	public void setCostCategory(Integer costCategory) {
		this.costCategory = costCategory;
	}

	public Integer getEmpPaymentStatus() {
		return empPaymentStatus;
	}

	public void setEmpPaymentStatus(Integer empPaymentStatus) {
		this.empPaymentStatus = empPaymentStatus;
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
		return "SsMonthCharge{" +
			", monthChargeId=" + monthChargeId +
			", comAccountId=" + comAccountId +
			", ssMonthBelong=" + ssMonthBelong +
			", ssMonth=" + ssMonth +
			", employeeId=" + employeeId +
			", empArchiveId=" + empArchiveId +
			", baseAmount=" + baseAmount +
			", totalAmount=" + totalAmount +
			", costCategory=" + costCategory +
			", empPaymentStatus=" + empPaymentStatus +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
