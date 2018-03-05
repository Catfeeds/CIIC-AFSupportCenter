package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 */
@TableName("ss_annual_adjust_account_emp")
public class SsAnnualAdjustAccountEmp implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="annual_adjust_account_emp_id", type= IdType.AUTO)
	private Long annualAdjustAccountEmpId;
	@TableField("annual_adjust_account_id")
	private Long annualAdjustAccountId;

	@TableField("account_status")
	private Integer accountStatus;
	@TableField("ss_serial")
	private Integer ssSerial;
	@TableField("employee_name")
	private String employeeName;
	@TableField("id_num")
	private String idNum;
	@TableField("payment_months")
	private Integer paymentMonths;
	@TableField("avg_month_salary")
	private BigDecimal avgMonthSalary;
    /**
     * 是否有效, 0-无效 1-有效
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalDateTime createdTime;
    /**
     * 更新时间
     */
	@TableField("modified_time")
	private LocalDateTime modifiedTime;
    /**
     * 创建者用户Id
     */
	@TableField("created_by")
	private String createdBy;
    /**
     * 修改者用户Id
     */
	@TableField("modified_by")
	private String modifiedBy;


	public Long getAnnualAdjustAccountEmpId() {
		return annualAdjustAccountEmpId;
	}

	public void setAnnualAdjustAccountEmpId(Long annualAdjustAccountEmpId) {
		this.annualAdjustAccountEmpId = annualAdjustAccountEmpId;
	}

	public Long getAnnualAdjustAccountId() {
		return annualAdjustAccountId;
	}

	public void setAnnualAdjustAccountId(Long annualAdjustAccountId) {
		this.annualAdjustAccountId = annualAdjustAccountId;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Integer getSsSerial() {
		return ssSerial;
	}

	public void setSsSerial(Integer ssSerial) {
		this.ssSerial = ssSerial;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public Integer getPaymentMonths() {
		return paymentMonths;
	}

	public void setPaymentMonths(Integer paymentMonths) {
		this.paymentMonths = paymentMonths;
	}

	public BigDecimal getAvgMonthSalary() {
		return avgMonthSalary;
	}

	public void setAvgMonthSalary(BigDecimal avgMonthSalary) {
		this.avgMonthSalary = avgMonthSalary;
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
		return "SsAnnualAdjustAccountEmp{" +
			", annualAdjustAccountEmpId=" + annualAdjustAccountEmpId +
			", annualAdjustAccountId=" + annualAdjustAccountId +
			", accountStatus=" + accountStatus +
			", ssSerial=" + ssSerial +
			", employeeName=" + employeeName +
			", idNum=" + idNum +
			", paymentMonths=" + paymentMonths +
			", avgMonthSalary=" + avgMonthSalary +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
