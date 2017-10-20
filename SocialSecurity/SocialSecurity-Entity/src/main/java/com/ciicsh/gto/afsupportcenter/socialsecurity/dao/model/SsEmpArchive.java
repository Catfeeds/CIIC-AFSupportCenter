package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * 雇员本地社保档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产生
 */
@Table(name = "SS_EmpArchive")
public class SsEmpArchive extends BasicModel implements Serializable {
    /**
     * 雇员本地社保档案Id
     */
    @Id
    @Column(name = "EmpArchiveId")
    private String empArchiveId;

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
     * 社保序号 8 位数字，不足8位按实际位数显示
     */
    @Column(name = "SOCSerial")
    private String SOCSerial;

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
     * 外键,人员属性 ,关联至 gtosmdb.SM_PersonalProperty.PersonalPropertyId
     */
    @Column(name = "PersonalPropertyId")
    private String personalPropertyId;

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

    private static final long serialVersionUID = 1L;

    /**
     * 获取雇员本地社保档案Id
     *
     * @return EmpArchiveId - 雇员本地社保档案Id
     */
    public String getEmpArchiveId() {
        return empArchiveId;
    }

    /**
     * 设置雇员本地社保档案Id
     *
     * @param empArchiveId 雇员本地社保档案Id
     */
    public void setEmpArchiveId(String empArchiveId) {
        this.empArchiveId = empArchiveId == null ? null : empArchiveId.trim();
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
     * 获取社保序号 8 位数字，不足8位按实际位数显示
     *
     * @return SOCSerial - 社保序号 8 位数字，不足8位按实际位数显示
     */
    public String getSOCSerial() {
        return SOCSerial;
    }

    /**
     * 设置社保序号 8 位数字，不足8位按实际位数显示
     *
     * @param SOCSerial 社保序号 8 位数字，不足8位按实际位数显示
     */
    public void setSOCSerial(String SOCSerial) {
        this.SOCSerial = SOCSerial == null ? null : SOCSerial.trim();
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
     * 获取外键,人员属性 ,关联至 gtosmdb.SM_PersonalProperty.PersonalPropertyId
     *
     * @return PersonalPropertyId - 外键,人员属性 ,关联至 gtosmdb.SM_PersonalProperty.PersonalPropertyId
     */
    public String getPersonalPropertyId() {
        return personalPropertyId;
    }

    /**
     * 设置外键,人员属性 ,关联至 gtosmdb.SM_PersonalProperty.PersonalPropertyId
     *
     * @param personalPropertyId 外键,人员属性 ,关联至 gtosmdb.SM_PersonalProperty.PersonalPropertyId
     */
    public void setPersonalPropertyId(String personalPropertyId) {
        this.personalPropertyId = personalPropertyId == null ? null : personalPropertyId.trim();
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
}