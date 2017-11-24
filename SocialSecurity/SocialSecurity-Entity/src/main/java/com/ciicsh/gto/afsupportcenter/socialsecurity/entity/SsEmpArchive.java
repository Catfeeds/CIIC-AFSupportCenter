package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 雇员本地社保档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产生
 */
@Table(name = "SS_EmpArchive")
public class SsEmpArchive implements Serializable {
    /**
     * 雇员本地社保档案Id
     */
    @Id
    @Column(name = "EmpArchiveId")
    private Long empArchiveId;

    /**
     * EntityId
     */
    @Column(name = "EntityId")
    private String entityId;

    /**
     * 多租户
     */
    @Column(name = "CustomerId")
    private String customerId;

    /**
     * 外键,终身雇员编号，关联至gtofrontdb.EMP_Employee
     */
    @Column(name = "EmployeeId")
    private String employeeId;

    /**
     * 外键,客户Id,关联至gtofrontdb.CMY_Company
     */
    @Column(name = "CompanyId")
    private String companyId;

    /**
     * 企业社保账户Id, 关联至SOC_SSAccount
     */
    @Column(name = "ComAccountId")
    private String comAccountId;

    /**
     * 社保序号 
     */
    @Column(name = "SOCSerial")
    private String SOCSerial;

    /**
     * 实际工资
     */
    @Column(name = "Salary")
    private BigDecimal salary;

    /**
     * 开AF单日期 (档案部办理日期)
     */
    @Column(name = "CreateAFDate")
    private LocalDate createAFDate;

    /**
     * 存档地
     */
    @Column(name = "ArchivePlace")
    private String archivePlace;

    /**
     * 用工状态:1 已用工 2 已退工
     */
    @Column(name = "EmploymentStatus")
    private Boolean employmentStatus;

    /**
     * 用工日期
     */
    @Column(name = "OnboardDate")
    private LocalDate onboardDate;

    /**
     * 退工日期
     */
    @Column(name = "ExitDate")
    private LocalDate exitDate;

    /**
     * 人员属性：本地、外地、外籍三险、外籍五险、延迟退休人员
            本地、外地、外籍五险：有五个险种
            外籍三险、延迟退休人员：有三个险种
            
     */
    @Column(name = "EmployeeClassify")
    private Byte employeeClassify;

    /**
     * 社保档案状态 : 0-未办理  1-已办  2-已做 3-转出
     */
    @Column(name = "ArchiveStatus")
    private Boolean archiveStatus;

    /**
     * 社保档案状态 : 1-已办  2-已做 3-转出
     */
    @Column(name = "ArchiveTaskStatus")
    private Boolean archiveTaskStatus;

    /**
     * 社保起缴月份
     */
    @Column(name = "StartMonth")
    private String startMonth;

    /**
     * 社保结束（转出）月份
     */
    @Column(name = "EndMonth")
    private String endMonth;

    /**
     * 正常汇缴月份（办理月份）
     */
    @Column(name = "SOCMonth")
    private String SOCMonth;

    /**
     * 是否可用
     */
    @Column(name = "IsActive")
    private Boolean isActive;

    /**
     * 创建时间
     */
    @Column(name = "DataChange_CreateTime")
    private LocalDateTime dataChangeCreateTime;

    /**
     * 最后更新时间
     */
    @Column(name = "DataChange_LastTime")
    private LocalDateTime dataChangeLastTime;

    /**
     * 创建者登录名
     */
    @Column(name = "CreatedBy")
    private String createdBy;

    /**
     * 修改者登录名
     */
    @Column(name = "ModifiedBy")
    private String modifiedBy;

    private static final long serialVersionUID = 1L;

    /**
     * 获取雇员本地社保档案Id
     *
     * @return EmpArchiveId - 雇员本地社保档案Id
     */
    public Long getEmpArchiveId() {
        return empArchiveId;
    }

    /**
     * 设置雇员本地社保档案Id
     *
     * @param empArchiveId 雇员本地社保档案Id
     */
    public void setEmpArchiveId(Long empArchiveId) {
        this.empArchiveId = empArchiveId;
    }

    /**
     * 获取EntityId
     *
     * @return EntityId - EntityId
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * 设置EntityId
     *
     * @param entityId EntityId
     */
    public void setEntityId(String entityId) {
        this.entityId = entityId == null ? null : entityId.trim();
    }

    /**
     * 获取多租户
     *
     * @return CustomerId - 多租户
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置多租户
     *
     * @param customerId 多租户
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 获取外键,终身雇员编号，关联至gtofrontdb.EMP_Employee
     *
     * @return EmployeeId - 外键,终身雇员编号，关联至gtofrontdb.EMP_Employee
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * 设置外键,终身雇员编号，关联至gtofrontdb.EMP_Employee
     *
     * @param employeeId 外键,终身雇员编号，关联至gtofrontdb.EMP_Employee
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    /**
     * 获取外键,客户Id,关联至gtofrontdb.CMY_Company
     *
     * @return CompanyId - 外键,客户Id,关联至gtofrontdb.CMY_Company
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置外键,客户Id,关联至gtofrontdb.CMY_Company
     *
     * @param companyId 外键,客户Id,关联至gtofrontdb.CMY_Company
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * 获取企业社保账户Id, 关联至SOC_SSAccount
     *
     * @return ComAccountId - 企业社保账户Id, 关联至SOC_SSAccount
     */
    public String getComAccountId() {
        return comAccountId;
    }

    /**
     * 设置企业社保账户Id, 关联至SOC_SSAccount
     *
     * @param comAccountId 企业社保账户Id, 关联至SOC_SSAccount
     */
    public void setComAccountId(String comAccountId) {
        this.comAccountId = comAccountId == null ? null : comAccountId.trim();
    }

    /**
     * 获取社保序号 
     *
     * @return SOCSerial - 社保序号 
     */
    public String getSOCSerial() {
        return SOCSerial;
    }

    /**
     * 设置社保序号 
     *
     * @param SOCSerial 社保序号 
     */
    public void setSOCSerial(String SOCSerial) {
        this.SOCSerial = SOCSerial == null ? null : SOCSerial.trim();
    }

    /**
     * 获取实际工资
     *
     * @return Salary - 实际工资
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * 设置实际工资
     *
     * @param salary 实际工资
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * 获取开AF单日期 (档案部办理日期)
     *
     * @return CreateAFDate - 开AF单日期 (档案部办理日期)
     */
    public LocalDate getCreateAFDate() {
        return createAFDate;
    }

    /**
     * 设置开AF单日期 (档案部办理日期)
     *
     * @param createAFDate 开AF单日期 (档案部办理日期)
     */
    public void setCreateAFDate(LocalDate createAFDate) {
        this.createAFDate = createAFDate;
    }

    /**
     * 获取存档地
     *
     * @return ArchivePlace - 存档地
     */
    public String getArchivePlace() {
        return archivePlace;
    }

    /**
     * 设置存档地
     *
     * @param archivePlace 存档地
     */
    public void setArchivePlace(String archivePlace) {
        this.archivePlace = archivePlace == null ? null : archivePlace.trim();
    }

    /**
     * 获取用工状态:1 已用工 2 已退工
     *
     * @return EmploymentStatus - 用工状态:1 已用工 2 已退工
     */
    public Boolean getEmploymentStatus() {
        return employmentStatus;
    }

    /**
     * 设置用工状态:1 已用工 2 已退工
     *
     * @param employmentStatus 用工状态:1 已用工 2 已退工
     */
    public void setEmploymentStatus(Boolean employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    /**
     * 获取用工日期
     *
     * @return OnboardDate - 用工日期
     */
    public LocalDate getOnboardDate() {
        return onboardDate;
    }

    /**
     * 设置用工日期
     *
     * @param onboardDate 用工日期
     */
    public void setOnboardDate(LocalDate onboardDate) {
        this.onboardDate = onboardDate;
    }

    /**
     * 获取退工日期
     *
     * @return ExitDate - 退工日期
     */
    public LocalDate getExitDate() {
        return exitDate;
    }

    /**
     * 设置退工日期
     *
     * @param exitDate 退工日期
     */
    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }

    /**
     * 获取人员属性：本地、外地、外籍三险、外籍五险、延迟退休人员
            本地、外地、外籍五险：有五个险种
            外籍三险、延迟退休人员：有三个险种
            
     *
     * @return EmployeeClassify - 人员属性：本地、外地、外籍三险、外籍五险、延迟退休人员
            本地、外地、外籍五险：有五个险种
            外籍三险、延迟退休人员：有三个险种
            
     */
    public Byte getEmployeeClassify() {
        return employeeClassify;
    }

    /**
     * 设置人员属性：本地、外地、外籍三险、外籍五险、延迟退休人员
            本地、外地、外籍五险：有五个险种
            外籍三险、延迟退休人员：有三个险种
            
     *
     * @param employeeClassify 人员属性：本地、外地、外籍三险、外籍五险、延迟退休人员
            本地、外地、外籍五险：有五个险种
            外籍三险、延迟退休人员：有三个险种
            
     */
    public void setEmployeeClassify(Byte employeeClassify) {
        this.employeeClassify = employeeClassify;
    }

    /**
     * 获取社保档案状态 : 0-未办理  1-已办  2-已做 3-转出
     *
     * @return ArchiveStatus - 社保档案状态 : 0-未办理  1-已办  2-已做 3-转出
     */
    public Boolean getArchiveStatus() {
        return archiveStatus;
    }

    /**
     * 设置社保档案状态 : 0-未办理  1-已办  2-已做 3-转出
     *
     * @param archiveStatus 社保档案状态 : 0-未办理  1-已办  2-已做 3-转出
     */
    public void setArchiveStatus(Boolean archiveStatus) {
        this.archiveStatus = archiveStatus;
    }

    /**
     * 获取社保档案状态 : 1-已办  2-已做 3-转出
     *
     * @return ArchiveTaskStatus - 社保档案状态 : 1-已办  2-已做 3-转出
     */
    public Boolean getArchiveTaskStatus() {
        return archiveTaskStatus;
    }

    /**
     * 设置社保档案状态 : 1-已办  2-已做 3-转出
     *
     * @param archiveTaskStatus 社保档案状态 : 1-已办  2-已做 3-转出
     */
    public void setArchiveTaskStatus(Boolean archiveTaskStatus) {
        this.archiveTaskStatus = archiveTaskStatus;
    }

    /**
     * 获取社保起缴月份
     *
     * @return StartMonth - 社保起缴月份
     */
    public String getStartMonth() {
        return startMonth;
    }

    /**
     * 设置社保起缴月份
     *
     * @param startMonth 社保起缴月份
     */
    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth == null ? null : startMonth.trim();
    }

    /**
     * 获取社保结束（转出）月份
     *
     * @return EndMonth - 社保结束（转出）月份
     */
    public String getEndMonth() {
        return endMonth;
    }

    /**
     * 设置社保结束（转出）月份
     *
     * @param endMonth 社保结束（转出）月份
     */
    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth == null ? null : endMonth.trim();
    }

    /**
     * 获取正常汇缴月份（办理月份）
     *
     * @return SOCMonth - 正常汇缴月份（办理月份）
     */
    public String getSOCMonth() {
        return SOCMonth;
    }

    /**
     * 设置正常汇缴月份（办理月份）
     *
     * @param SOCMonth 正常汇缴月份（办理月份）
     */
    public void setSOCMonth(String SOCMonth) {
        this.SOCMonth = SOCMonth == null ? null : SOCMonth.trim();
    }

    /**
     * 获取是否可用
     *
     * @return IsActive - 是否可用
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * 设置是否可用
     *
     * @param isActive 是否可用
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取创建时间
     *
     * @return DataChange_CreateTime - 创建时间
     */
    public LocalDateTime getDataChangeCreateTime() {
        return dataChangeCreateTime;
    }

    /**
     * 设置创建时间
     *
     * @param dataChangeCreateTime 创建时间
     */
    public void setDataChangeCreateTime(LocalDateTime dataChangeCreateTime) {
        this.dataChangeCreateTime = dataChangeCreateTime;
    }

    /**
     * 获取最后更新时间
     *
     * @return DataChange_LastTime - 最后更新时间
     */
    public LocalDateTime getDataChangeLastTime() {
        return dataChangeLastTime;
    }

    /**
     * 设置最后更新时间
     *
     * @param dataChangeLastTime 最后更新时间
     */
    public void setDataChangeLastTime(LocalDateTime dataChangeLastTime) {
        this.dataChangeLastTime = dataChangeLastTime;
    }

    /**
     * 获取创建者登录名
     *
     * @return CreatedBy - 创建者登录名
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建者登录名
     *
     * @param createdBy 创建者登录名
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * 获取修改者登录名
     *
     * @return ModifiedBy - 修改者登录名
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * 设置修改者登录名
     *
     * @param modifiedBy 修改者登录名
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }
}