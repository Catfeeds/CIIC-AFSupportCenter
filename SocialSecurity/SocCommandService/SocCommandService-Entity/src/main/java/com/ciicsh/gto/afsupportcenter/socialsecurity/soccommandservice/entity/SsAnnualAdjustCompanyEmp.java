package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 */
@TableName("ss_annual_adjust_company_emp")
public class SsAnnualAdjustCompanyEmp implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="annual_adjust_company_emp_id", type= IdType.AUTO)
	private Long annualAdjustCompanyEmpId;
	@TableField("annual_adjust_company_id")
	private Long annualAdjustCompanyId;
    /**
     * 雇员编号ID
     */
	@TableField("employee_id")
	private String employeeId;
	@TableField("employee_name")
	private String employeeName;
    /**
     * 社保序号
     */
	@TableField("ss_serial")
	private String ssSerial;
	private BigDecimal salary;
    /**
     * 待调工资
     */
    @TableField("chg_salary")
    private BigDecimal chgSalary;
	@TableField("id_num")
	private String idNum;
	@TableField("archive_status")
	private Integer archiveStatus;
    @TableField(exist = false)
    private String archiveStatusName;
    /**
     * 基数, 五险合一(基数一致）时有效
     */
	@TableField("base_amount")
	private BigDecimal baseAmount;
    /**
     * 人员属性：本地、外地、外籍三险、外籍五险、延迟退休人员
     本地、外地、外籍五险：有五个险种
     外籍三险、延迟退休人员：有三个险种

     */
    @TableField("emp_classify")
    private Integer empClassify;
    @TableField(exist = false)
    private String empClassifyName;
    /**
     * 账户类型：1:中智大库 2中智外包 3独立户
     */
	@TableField("ss_account_type")
	private Integer ssAccountType;
    @TableField(exist = false)
    private String ssAccountTypeName;
    /**
     * 结算区县(社保局所在上海地区)
     */
	@TableField("settlement_area")
	private String settlementArea;
    @TableField(exist = false)
    private String settlementAreaName;
    /**
     * 企业社保账户
     */
	@TableField("ss_account")
	private String ssAccount;
    /**
     * 养老金独立开户用户名（使用U盾登陆的用户名）
     */
	@TableField("ss_username")
	private String ssUsername;
    /**
     * 养老金独立开户密码（使用U盾登陆的密码）
     */
	@TableField("ss_pwd")
	private String ssPwd;
    @TableField("low_department_id")
    private Integer lowDepartmentId;
    @TableField("low_department_name")
    private String lowDepartmentName;
    @TableField("high_department_id")
    private Integer highDepartmentId;
    @TableField("high_department_name")
    private String highDepartmentName;
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

	public Long getAnnualAdjustCompanyEmpId() {
		return annualAdjustCompanyEmpId;
	}

	public void setAnnualAdjustCompanyEmpId(Long annualAdjustCompanyEmpId) {
		this.annualAdjustCompanyEmpId = annualAdjustCompanyEmpId;
	}

	public Long getAnnualAdjustCompanyId() {
		return annualAdjustCompanyId;
	}

	public void setAnnualAdjustCompanyId(Long annualAdjustCompanyId) {
		this.annualAdjustCompanyId = annualAdjustCompanyId;
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

    public Integer getEmpClassify() {
        return empClassify;
    }

    public void setEmpClassify(Integer empClassify) {
        this.empClassify = empClassify;
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

    public BigDecimal getChgSalary() {
        return chgSalary;
    }

    public void setChgSalary(BigDecimal chgSalary) {
        this.chgSalary = chgSalary;
    }

    public String getArchiveStatusName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.archiveStatus), DictUtil.TYPE_VALUE_SOCIAL_SECURITY_STATUS, false);
    }

    public void setArchiveStatusName(String archiveStatusName) {
        this.archiveStatusName = archiveStatusName;
    }

    public String getSsAccountTypeName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.ssAccountType), DictUtil.TYPE_VALUE_SOCIAL_SECURITY_ACCOUNT_TYPE, false);
    }

    public void setSsAccountTypeName(String ssAccountTypeName) {
        this.ssAccountTypeName = ssAccountTypeName;
    }

    public String getEmpClassifyName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.empClassify), DictUtil.TYPE_VALUE_SOCIAL_SECURITY_EMPLOYEE_CLASSIFY, false);
    }

    public void setEmpClassifyName(String empClassifyName) {
        this.empClassifyName = empClassifyName;
    }

    public String getSettlementAreaName() {
        return SocialSecurityConst.DISTRICT_MAP.get(this.settlementArea);
    }

    public void setSettlementAreaName(String settlementAreaName) {
        this.settlementAreaName = settlementAreaName;
    }

    @Override
	public String toString() {
		return "SsAnnualAdjustCompanyEmp{" +
			", annualAdjustCompanyEmpId=" + annualAdjustCompanyEmpId +
			", annualAdjustCompanyId=" + annualAdjustCompanyId +
			", employeeId=" + employeeId +
			", employeeName=" + employeeName +
			", ssSerial=" + ssSerial +
			", salary=" + salary +
            ", chgSalary=" + chgSalary +
			", idNum=" + idNum +
			", archiveStatus=" + archiveStatus +
			", baseAmount=" + baseAmount +
            ", empClassify=" + empClassify +
			", ssAccountType=" + ssAccountType +
			", settlementArea=" + settlementArea +
			", ssAccount=" + ssAccount +
			", ssUsername=" + ssUsername +
			", ssPwd=" + ssPwd +
            ", lowDepartmentId=" + lowDepartmentId +
            ", lowDepartmentName=" + lowDepartmentName +
            ", highDepartmentId=" + highDepartmentId +
            ", highDepartmentName=" + highDepartmentName +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
