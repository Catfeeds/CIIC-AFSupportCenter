package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
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
@TableName("ss_annual_adjust_employee")
@ExcelTarget("annualAdjustEmployee")
public class SsAnnualAdjustEmployee implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="annual_adjust_employee_id", type= IdType.AUTO)
	private Long annualAdjustEmployeeId;
	@TableField("adjust_year")
	private String adjustYear;
    /**
     * 雇员编号ID
     */
	@TableField("employee_id")
    @Excel(name = "雇员编号", width = 20)
	private String employeeId;
	@TableField("employee_name")
    @Excel(name = "雇员姓名", orderNum = "1", width = 15)
	private String employeeName;
    /**
     * 社保序号
     */
	@TableField("ss_serial")
    @Excel(name = "社保序号", orderNum = "2", width = 20)
	private String ssSerial;
	@Excel(name = "工资", orderNum = "3", width = 15)
	private BigDecimal salary;
	@TableField("id_num")
    @Excel(name = "身份证号", orderNum = "4", width = 25)
	private String idNum;
	@TableField("archive_status")
    @Excel(name = "社保状态", replace = {"初始_0", "有效_1", "终止_2", "封存_3"}, orderNum = "5", width = 15)
	private Integer archiveStatus;
    /**
     * 基数, 五险合一(基数一致）时有效
     */
	@TableField("base_amount")
    @Excel(name = "社保基数", orderNum = "8", width = 15)
	private BigDecimal baseAmount;
    /**
     * 人员属性：本地、外地、外籍三险、外籍五险、延迟退休人员
     本地、外地、外籍五险：有五个险种
     外籍三险、延迟退休人员：有三个险种

     */
    @TableField("emp_classify")
    @Excel(name="人员属性", replace = {"本地_1", "外地_2", "外籍三险_3", "外籍五险_4", "延迟退休人员_5"}, orderNum = "7", width = 15)
    private Integer empClassify;
    /**
     * 账户类型：1:中智大库 2中智外包 3独立户
     */
	@TableField("ss_account_type")
    @Excel(name = "账户类型", replace = {"中智大库_1", "中智外包_2", "独立户_3"}, orderNum = "6", width = 10)
	private Integer ssAccountType;
    /**
     * 结算区县(社保局所在上海地区)
     */
	@TableField("settlement_area")
    @Excel(name = "结算区县", orderNum = "9", width = 10)
	private String settlementArea;
    /**
     * 企业社保账户
     */
	@TableField("ss_account")
    @Excel(name = "企业社保账户", orderNum = "10", width = 20)
	private String ssAccount;
    /**
     * 养老金独立开户用户名（使用U盾登陆的用户名）
     */
	@TableField("ss_username")
    @Excel(name = "养老金独立开户用户名", orderNum = "11", width = 20)
	private String ssUsername;
    /**
     * 养老金独立开户密码（使用U盾登陆的密码）
     */
	@TableField("ss_pwd")
    @Excel(name = "养老金独立开户密码", orderNum = "12", width = 20)
	private String ssPwd;
    /**
     * 雇员本地社保档案Id
     */
	@TableField("emp_archive_id")
	private Long empArchiveId;
    /**
     * 企业社保账户Id, 关联至SOC_SSAccount
     */
	@TableField("com_account_id")
	private Long comAccountId;
    /**
     * 客户编号ID
     */
	@TableField("company_id")
    @Excel(name = "客户编号", orderNum = "13", width = 20)
	private String companyId;
	@TableField("company_name")
	private String companyName;
    @TableField("low_department_id")
	private Integer lowDepartmentId;
    @TableField("low_department_name")
    @Excel(name = "所属小组", orderNum = "14", width = 20)
    private String lowDepartmentName;
    @TableField("high_department_id")
    private Integer highDepartmentId;
    @TableField("high_department_name")
    @Excel(name = "所属大组", orderNum = "15", width = 20)
    private String highDepartmentName;
    @TableField("city_code")
    private String cityCode;
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


	public Long getAnnualAdjustEmployeeId() {
		return annualAdjustEmployeeId;
	}

	public void setAnnualAdjustEmployeeId(Long annualAdjustEmployeeId) {
		this.annualAdjustEmployeeId = annualAdjustEmployeeId;
	}

	public String getAdjustYear() {
		return adjustYear;
	}

	public void setAdjustYear(String adjustYear) {
		this.adjustYear = adjustYear;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSsSerial() {
		return ssSerial;
	}

	public void setSsSerial(String ssSerial) {
		this.ssSerial = ssSerial;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public Integer getArchiveStatus() {
		return archiveStatus;
	}

	public void setArchiveStatus(Integer archiveStatus) {
		this.archiveStatus = archiveStatus;
	}

	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}

    public Integer getEmpClassify() {
        return empClassify;
    }

    public void setEmpClassify(Integer empClassify) {
        this.empClassify = empClassify;
    }

    public Integer getSsAccountType() {
		return ssAccountType;
	}

	public void setSsAccountType(Integer ssAccountType) {
		this.ssAccountType = ssAccountType;
	}

	public String getSettlementArea() {
		return settlementArea;
	}

	public void setSettlementArea(String settlementArea) {
		this.settlementArea = settlementArea;
	}

	public String getSsAccount() {
		return ssAccount;
	}

	public void setSsAccount(String ssAccount) {
		this.ssAccount = ssAccount;
	}

	public String getSsUsername() {
		return ssUsername;
	}

	public void setSsUsername(String ssUsername) {
		this.ssUsername = ssUsername;
	}

	public String getSsPwd() {
		return ssPwd;
	}

	public void setSsPwd(String ssPwd) {
		this.ssPwd = ssPwd;
	}

	public Long getEmpArchiveId() {
		return empArchiveId;
	}

	public void setEmpArchiveId(Long empArchiveId) {
		this.empArchiveId = empArchiveId;
	}

	public Long getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(Long comAccountId) {
		this.comAccountId = comAccountId;
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

    public Integer getLowDepartmentId() {
        return lowDepartmentId;
    }

    public void setLowDepartmentId(Integer lowDepartmentId) {
        this.lowDepartmentId = lowDepartmentId;
    }

    public String getLowDepartmentName() {
        return lowDepartmentName;
    }

    public void setLowDepartmentName(String lowDepartmentName) {
        this.lowDepartmentName = lowDepartmentName;
    }

    public Integer getHighDepartmentId() {
        return highDepartmentId;
    }

    public void setHighDepartmentId(Integer highDepartmentId) {
        this.highDepartmentId = highDepartmentId;
    }

    public String getHighDepartmentName() {
        return highDepartmentName;
    }

    public void setHighDepartmentName(String highDepartmentName) {
        this.highDepartmentName = highDepartmentName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Override
	public String toString() {
		return "SsAnnualAdjustEmployee{" +
			", annualAdjustEmployeeId=" + annualAdjustEmployeeId +
			", adjustYear=" + adjustYear +
			", employeeId=" + employeeId +
			", employeeName=" + employeeName +
			", ssSerial=" + ssSerial +
			", salary=" + salary +
			", idNum=" + idNum +
			", archiveStatus=" + archiveStatus +
			", baseAmount=" + baseAmount +
            ", empClassify=" + empClassify +
			", ssAccountType=" + ssAccountType +
			", settlementArea=" + settlementArea +
			", ssAccount=" + ssAccount +
			", ssUsername=" + ssUsername +
			", ssPwd=" + ssPwd +
			", empArchiveId=" + empArchiveId +
			", comAccountId=" + comAccountId +
			", companyId=" + companyId +
			", companyName=" + companyName +
            ", lowDepartmentId=" + lowDepartmentId +
            ", lowDepartmentName=" + lowDepartmentName +
            ", highDepartmentId=" + highDepartmentId +
            ", highDepartmentName=" + highDepartmentName +
            ", cityCode=" + cityCode +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
