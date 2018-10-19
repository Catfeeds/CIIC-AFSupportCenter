package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 雇员信息表
 * </p>
 *
 * @author ${author}
 * @since 2018-04-09
 */
@TableName("am_emp_employee")
public class AmEmpEmployee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键,可作为任务单序号
     */
	@TableId(value="emp_employee_id", type= IdType.AUTO)
	private Long empEmployeeId;

    @TableField("emp_task_id")
    private Long  empTaskId;
    /**
     * 客户Id
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 雇员id
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 用工属性
     */
	@TableField("hire_unit")
	private Integer hireUnit;
    /**
     * 合同起始日期
     */
	@TableField("labor_start_date")
	private Date laborStartDate;
    /**
     * 合同结束日期
     */
	@TableField("labor_end_date")
	private Date laborEndDate;
    /**
     * 职位
     */
	private String position;
    /**
     * 证件号码
     */
	@TableField("id_num")
	private String idNum;
    /**
     * 雇员姓名
     */
	@TableField("employee_name")
	private String employeeName;
    /**
     * 性别
     */
	private Integer gender;

    @TableField("mobile")
	private  String mobile;
    /**
     * 户籍地址
     */
	@TableField("residence_address")
	private String residenceAddress;
    /**
     * 首进中智日期
     */
	@TableField("first_in_date")
	private Date firstInDate;
    /**
     * 首进公司日期
     */
	@TableField("first_in_company_date")
	private Date firstInCompanyDate;
    /**
     * 组织机构代码
     */
	@TableField("organization_code")
	private String organizationCode;

    /**
     * 档案费
     */
    @TableField("file_fee")
    private  String fileFee;

    /**
     *
     */
    @TableField("employee_center_operator")
    private  String employeeCenterOperator;

    /**
     * 公司社保登记码
     */
    @TableField("ss_account")
    private String ssAccount;
    /**
     * 缴费区县
     */
    @TableField("settlement_area")
    private  String settlementArea;

    @TableField("account_repair_date")
    private  LocalDate accountRepairDate;

    @TableField("ss_pwd")
    private String ssPwd;

    @TableField("company_type")
    private  String companyType;

    @TableField("in_date")
    private  Date inDate;

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }

    public String getSettlementArea() {
        return settlementArea;
    }

    public void setSettlementArea(String settlementArea) {
        this.settlementArea = settlementArea;
    }

    public LocalDate getAccountRepairDate() {
        return accountRepairDate;
    }

    public void setAccountRepairDate(LocalDate accountRepairDate) {
        this.accountRepairDate = accountRepairDate;
    }

    public String getSsPwd() {
        return ssPwd;
    }

    public void setSsPwd(String ssPwd) {
        this.ssPwd = ssPwd;
    }

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

    public String getEmployeeCenterOperator() {
        return employeeCenterOperator;
    }

    public void setEmployeeCenterOperator(String employeeCenterOperator) {
        this.employeeCenterOperator = employeeCenterOperator;
    }

    public String getFileFee() {
        return fileFee;
    }

    public void setFileFee(String fileFee) {
        this.fileFee = fileFee;
    }

    public Long getEmpEmployeeId() {
		return empEmployeeId;
	}

	public void setEmpEmployeeId(Long empEmployeeId) {
		this.empEmployeeId = empEmployeeId;
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

	public Integer getHireUnit() {
		return hireUnit;
	}

	public void setHireUnit(Integer hireUnit) {
		this.hireUnit = hireUnit;
	}

	public Date getLaborStartDate() {
		return laborStartDate;
	}

	public void setLaborStartDate(Date laborStartDate) {
		this.laborStartDate = laborStartDate;
	}

	public Date getLaborEndDate() {
		return laborEndDate;
	}

	public void setLaborEndDate(Date laborEndDate) {
		this.laborEndDate = laborEndDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

	public Date getFirstInDate() {
		return firstInDate;
	}

	public void setFirstInDate(Date firstInDate) {
		this.firstInDate = firstInDate;
	}

	public Date getFirstInCompanyDate() {
		return firstInCompanyDate;
	}

	public void setFirstInCompanyDate(Date firstInCompanyDate) {
		this.firstInCompanyDate = firstInCompanyDate;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
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

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    @Override
	public String toString() {
		return "AmEmpEmployee{" +
			"empEmployeeId=" + empEmployeeId +
			", companyId=" + companyId +
			", employeeId=" + employeeId +
			", hireUnit=" + hireUnit +
			", laborStartDate=" + laborStartDate +
			", laborEndDate=" + laborEndDate +
			", position=" + position +
			", idNum=" + idNum +
			", employeeName=" + employeeName +
			", gender=" + gender +
			", residenceAddress=" + residenceAddress +
			", firstInDate=" + firstInDate +
			", firstInCompanyDate=" + firstInCompanyDate +
			", organizationCode=" + organizationCode +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
