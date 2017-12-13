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
 * 雇员本地社保档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产生
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-12
 */
@TableName("ss_emp_archive")
public class SsEmpArchive implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员本地社保档案Id
     */
	@TableId(value="emp_archive_id", type= IdType.AUTO)
	private Long empArchiveId;
    /**
     * 多租户
     */
	@TableField("customer_id")
	private String customerId;
    /**
     * 客户编号ID
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 雇员编号ID
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 企业社保账户Id, 关联至SOC_SSAccount
     */
	@TableField("com_account_id")
	private Long comAccountId;
    /**
     * 社保序号 :一个雇员在不同库之间序号可以相同,但在同一个库下面不可以重复,
            老系统的社保序号取数比较大而且还用到所有独立户,搞得序号特别稀有
            
            
     */
	@TableField("ss_serial")
	private String ssSerial;
    /**
     * 实际工资
     */
	private BigDecimal salary;
    /**
     * 人员属性：本地、外地、外籍三险、外籍五险、延迟退休人员
            本地、外地、外籍五险：有五个险种
            外籍三险、延迟退休人员：有三个险种
            
     */
	@TableField("emp_classify")
	private Integer empClassify;
    /**
     * 社保档案状态 : 0-未办理  1-已办  2-已做 3-转出
     */
	@TableField("archive_status")
	private Integer archiveStatus;
    /**
     * 社保档案任务状态 : 1-已办  2-已做 3-转出
     */
	@TableField("archive_task_status")
	private Integer archiveTaskStatus;
    /**
     * 社保起缴月份YYYYMM
     */
	@TableField("start_month")
	private String startMonth;
    /**
     * 社保结束（转出）月份YYYYMM
     */
	@TableField("end_month")
	private String endMonth;
    /**
     * 正常汇缴月份（办理月份）YYYYMM
     */
	@TableField("ss_month")
	private String ssMonth;
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


	public Long getEmpArchiveId() {
		return empArchiveId;
	}

	public void setEmpArchiveId(Long empArchiveId) {
		this.empArchiveId = empArchiveId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public Long getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(Long comAccountId) {
		this.comAccountId = comAccountId;
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

	public Integer getEmpClassify() {
		return empClassify;
	}

	public void setEmpClassify(Integer empClassify) {
		this.empClassify = empClassify;
	}

	public Integer getArchiveStatus() {
		return archiveStatus;
	}

	public void setArchiveStatus(Integer archiveStatus) {
		this.archiveStatus = archiveStatus;
	}

	public Integer getArchiveTaskStatus() {
		return archiveTaskStatus;
	}

	public void setArchiveTaskStatus(Integer archiveTaskStatus) {
		this.archiveTaskStatus = archiveTaskStatus;
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

	public String getSsMonth() {
		return ssMonth;
	}

	public void setSsMonth(String ssMonth) {
		this.ssMonth = ssMonth;
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
		return "SsEmpArchive{" +
			", empArchiveId=" + empArchiveId +
			", customerId=" + customerId +
			", companyId=" + companyId +
			", employeeId=" + employeeId +
			", comAccountId=" + comAccountId +
			", ssSerial=" + ssSerial +
			", salary=" + salary +
			", empClassify=" + empClassify +
			", archiveStatus=" + archiveStatus +
			", archiveTaskStatus=" + archiveTaskStatus +
			", startMonth=" + startMonth +
			", endMonth=" + endMonth +
			", ssMonth=" + ssMonth +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
