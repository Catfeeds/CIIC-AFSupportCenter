package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
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
 * @since 2017-12-01
 */
@TableName("ss_emp_archive")
public class SsEmpArchive implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员本地社保档案Id
     */
	@TableId(value="emp_archived_id", type= IdType.AUTO)
	private Long empArchivedId;
    /**
     * EntityId
     */
	@TableField("entity_id")
	private String entityId;
    /**
     * 多租户
     */
	@TableField("customer_id")
	private String customerId;
    /**
     * 外键,雇员上下岗Id
     */
	@TableField("empcom_id")
	private String empcomId;
    /**
     * 企业社保账户Id, 关联至SOC_SSAccount
     */
	@TableField("com_account_id")
	private String comAccountId;
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
     * 开AF单日期 (档案部办理日期)
     */
	@TableField("create_afdate")
	private LocalDate createAfdate;
    /**
     * 存档地
     */
	@TableField("archive_place")
	private String archivePlace;
    /**
     * 用工状态:1 已用工 2 已退工
     */
	@TableField("employment_status")
	private Integer employmentStatus;
    /**
     * 用工日期
     */
	@TableField("onboard_date")
	private LocalDate onboardDate;
    /**
     * 退工日期
     */
	@TableField("exit_date")
	private LocalDate exitDate;
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
     * 社保档案状态 : 1-已办  2-已做 3-转出
     */
	@TableField("archive_task_status")
	private Integer archiveTaskStatus;
    /**
     * 社保起缴月份
     */
	@TableField("start_month")
	private String startMonth;
    /**
     * 社保结束（转出）月份
     */
	@TableField("end_month")
	private String endMonth;
    /**
     * 正常汇缴月份（办理月份）
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
	private LocalTime createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
	private LocalTime modifiedTime;
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


	public Long getEmpArchivedId() {
		return empArchivedId;
	}

	public void setEmpArchivedId(Long empArchivedId) {
		this.empArchivedId = empArchivedId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getEmpcomId() {
		return empcomId;
	}

	public void setEmpcomId(String empcomId) {
		this.empcomId = empcomId;
	}

	public String getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(String comAccountId) {
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

	public LocalDate getCreateAfdate() {
		return createAfdate;
	}

	public void setCreateAfdate(LocalDate createAfdate) {
		this.createAfdate = createAfdate;
	}

	public String getArchivePlace() {
		return archivePlace;
	}

	public void setArchivePlace(String archivePlace) {
		this.archivePlace = archivePlace;
	}

	public Integer getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(Integer employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public LocalDate getOnboardDate() {
		return onboardDate;
	}

	public void setOnboardDate(LocalDate onboardDate) {
		this.onboardDate = onboardDate;
	}

	public LocalDate getExitDate() {
		return exitDate;
	}

	public void setExitDate(LocalDate exitDate) {
		this.exitDate = exitDate;
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

	public LocalTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalTime modifiedTime) {
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
			", empArchivedId=" + empArchivedId +
			", entityId=" + entityId +
			", customerId=" + customerId +
			", empcomId=" + empcomId +
			", comAccountId=" + comAccountId +
			", ssSerial=" + ssSerial +
			", salary=" + salary +
			", createAfdate=" + createAfdate +
			", archivePlace=" + archivePlace +
			", employmentStatus=" + employmentStatus +
			", onboardDate=" + onboardDate +
			", exitDate=" + exitDate +
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
