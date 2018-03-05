package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 */
@TableName("ss_annual_adjust_company")
public class SsAnnualAdjustCompany implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="annual_adjust_company_id", type= IdType.AUTO)
	private Long annualAdjustCompanyId;
    /**
     * 客户编号ID
     */
	@TableField("company_id")
	private String companyId;
	@TableField("company_name")
	private String companyName;
	@TableField("adjust_year")
	private String adjustYear;
	@TableField("data_collect_status")
	private Integer dataCollectStatus;
	@TableField("report_status")
	private Integer reportStatus;
	@TableField("data_collect_time")
	private LocalDateTime dataCollectTime;
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

	@TableField(exist = false)
	private Long comAccountId;

	@TableField("account_avg_month_salary")
	private BigDecimal accountAvgMonthSalary;

    @TableField("account_salary_amount")
    private BigDecimal accountSalaryAmount;

    @TableField("account_emp_count")
    private BigDecimal accountEmpCount;

	public Long getAnnualAdjustCompanyId() {
		return annualAdjustCompanyId;
	}

	public void setAnnualAdjustCompanyId(Long annualAdjustCompanyId) {
		this.annualAdjustCompanyId = annualAdjustCompanyId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAdjustYear() {
		return adjustYear;
	}

	public void setAdjustYear(String adjustYear) {
		this.adjustYear = adjustYear;
	}

	public Integer getDataCollectStatus() {
		return dataCollectStatus;
	}

	public void setDataCollectStatus(Integer dataCollectStatus) {
		this.dataCollectStatus = dataCollectStatus;
	}

	public Integer getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	public LocalDateTime getDataCollectTime() {
		return dataCollectTime;
	}

	public void setDataCollectTime(LocalDateTime dataCollectTime) {
		this.dataCollectTime = dataCollectTime;
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

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public BigDecimal getAccountAvgMonthSalary() {
        return accountAvgMonthSalary;
    }

    public void setAccountAvgMonthSalary(BigDecimal accountAvgMonthSalary) {
        this.accountAvgMonthSalary = accountAvgMonthSalary;
    }

    public BigDecimal getAccountSalaryAmount() {
        return accountSalaryAmount;
    }

    public void setAccountSalaryAmount(BigDecimal accountSalaryAmount) {
        this.accountSalaryAmount = accountSalaryAmount;
    }

    public BigDecimal getAccountEmpCount() {
        return accountEmpCount;
    }

    public void setAccountEmpCount(BigDecimal accountEmpCount) {
        this.accountEmpCount = accountEmpCount;
    }

    @Override
	public String toString() {
		return "SsAnnualAdjustCompany{" +
			", annualAdjustCompanyId=" + annualAdjustCompanyId +
			", companyId=" + companyId +
			", companyName=" + companyName +
			", adjustYear=" + adjustYear +
			", dataCollectStatus=" + dataCollectStatus +
			", reportStatus=" + reportStatus +
			", dataCollectTime=" + dataCollectTime +
            ", comAccountId=" + comAccountId +
            ", accountAvgMonthSalary=" + accountAvgMonthSalary +
            ", accountSalaryAmount=" + accountSalaryAmount +
            ", accountEmpCount=" + accountEmpCount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
