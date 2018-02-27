package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.time.LocalDate;
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
@TableName("ss_annual_adjust_account")
public class SsAnnualAdjustAccount implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="annual_adjust_account_id", type= IdType.AUTO)
	private Long annualAdjustAccountId;
	@TableField("com_account_id")
	private Long comAccountId;
	@TableField("com_account_name")
	private String comAccountName;
	@TableField("ss_account")
	private String ssAccount;
	@TableField("adjust_year")
	private String adjustYear;
    /**
     * 0-未完成 1-收集中 2-收集完成
     */
	@TableField("data_collect_status")
	private Integer dataCollectStatus;
	@TableField("adjust_status")
	private Integer adjustStatus;
	@TableField("adjust_time")
	private LocalDateTime adjustTime;
    /**
     * 一般人员总数
     */
	@TableField("in_emp_total")
	private Integer inEmpTotal;
    /**
     * 转出人员总数
     */
	@TableField("out_emp_total")
	private Integer outEmpTotal;
    /**
     * 年度单位月平均工资
     */
	@TableField("avg_month_salary")
	private BigDecimal avgMonthSalary;
    /**
     * 年度全部职工工资总额
     */
	@TableField("all_total_amount")
	private BigDecimal allTotalAmount;
    /**
     * 年度全部职工平均人数
     */
	@TableField("all_emp_total")
	private Integer allEmpTotal;
    /**
     * 打印日期
     */
	@TableField("print_date")
	private LocalDate printDate;
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
    private Integer afImportTotal;
    @TableField(exist = false)
    private String ssImportTotal;
    @TableField(exist = false)
    private Integer matchTotal;
    @TableField(exist = false)
    private Integer unMatchTotal;

	public Long getAnnualAdjustAccountId() {
		return annualAdjustAccountId;
	}

	public void setAnnualAdjustAccountId(Long annualAdjustAccountId) {
		this.annualAdjustAccountId = annualAdjustAccountId;
	}

	public Long getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(Long comAccountId) {
		this.comAccountId = comAccountId;
	}

	public String getComAccountName() {
		return comAccountName;
	}

	public void setComAccountName(String comAccountName) {
		this.comAccountName = comAccountName;
	}

	public String getSsAccount() {
		return ssAccount;
	}

	public void setSsAccount(String ssAccount) {
		this.ssAccount = ssAccount;
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

	public Integer getAdjustStatus() {
		return adjustStatus;
	}

	public void setAdjustStatus(Integer adjustStatus) {
		this.adjustStatus = adjustStatus;
	}

	public LocalDateTime getAdjustTime() {
		return adjustTime;
	}

	public void setAdjustTime(LocalDateTime adjustTime) {
		this.adjustTime = adjustTime;
	}

	public Integer getInEmpTotal() {
		return inEmpTotal;
	}

	public void setInEmpTotal(Integer inEmpTotal) {
		this.inEmpTotal = inEmpTotal;
	}

	public Integer getOutEmpTotal() {
		return outEmpTotal;
	}

	public void setOutEmpTotal(Integer outEmpTotal) {
		this.outEmpTotal = outEmpTotal;
	}

	public BigDecimal getAvgMonthSalary() {
		return avgMonthSalary;
	}

	public void setAvgMonthSalary(BigDecimal avgMonthSalary) {
		this.avgMonthSalary = avgMonthSalary;
	}

	public BigDecimal getAllTotalAmount() {
		return allTotalAmount;
	}

	public void setAllTotalAmount(BigDecimal allTotalAmount) {
		this.allTotalAmount = allTotalAmount;
	}

	public Integer getAllEmpTotal() {
		return allEmpTotal;
	}

	public void setAllEmpTotal(Integer allEmpTotal) {
		this.allEmpTotal = allEmpTotal;
	}

	public LocalDate getPrintDate() {
		return printDate;
	}

	public void setPrintDate(LocalDate printDate) {
		this.printDate = printDate;
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

    public Integer getAfImportTotal() {
        return afImportTotal;
    }

    public void setAfImportTotal(Integer afImportTotal) {
        this.afImportTotal = afImportTotal;
    }

    public String getSsImportTotal() {
        return ssImportTotal;
    }

    public void setSsImportTotal(String ssImportTotal) {
        this.ssImportTotal = ssImportTotal;
    }

    public Integer getMatchTotal() {
        return matchTotal;
    }

    public void setMatchTotal(Integer matchTotal) {
        this.matchTotal = matchTotal;
    }

    public Integer getUnMatchTotal() {
        return unMatchTotal;
    }

    public void setUnMatchTotal(Integer unMatchTotal) {
        this.unMatchTotal = unMatchTotal;
    }

    @Override
	public String toString() {
		return "SsAnnualAdjustAccount{" +
			", annualAdjustAccountId=" + annualAdjustAccountId +
			", comAccountId=" + comAccountId +
			", comAccountName=" + comAccountName +
			", ssAccount=" + ssAccount +
			", adjustYear=" + adjustYear +
			", dataCollectStatus=" + dataCollectStatus +
			", adjustStatus=" + adjustStatus +
			", adjustTime=" + adjustTime +
			", inEmpTotal=" + inEmpTotal +
			", outEmpTotal=" + outEmpTotal +
			", avgMonthSalary=" + avgMonthSalary +
			", allTotalAmount=" + allTotalAmount +
			", allEmpTotal=" + allEmpTotal +
			", printDate=" + printDate +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
