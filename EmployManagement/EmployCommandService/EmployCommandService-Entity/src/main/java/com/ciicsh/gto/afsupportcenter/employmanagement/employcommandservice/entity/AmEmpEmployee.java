package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
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
    /**
     * 用工任务id
     */
	@TableField("task_id")
	private String taskId;
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


	public Long getEmpEmployeeId() {
		return empEmployeeId;
	}

	public void setEmpEmployeeId(Long empEmployeeId) {
		this.empEmployeeId = empEmployeeId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
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

	@Override
	public String toString() {
		return "AmEmpEmployee{" +
			"empEmployeeId=" + empEmployeeId +
			", taskId=" + taskId +
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
