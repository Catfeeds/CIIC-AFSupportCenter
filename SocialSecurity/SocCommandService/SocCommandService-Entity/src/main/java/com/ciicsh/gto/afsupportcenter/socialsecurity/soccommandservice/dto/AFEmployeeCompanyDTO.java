package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The type Af employee company dto.
 *
 * @Description:
 * @author: wujinglei /吴敬磊
 * @date: 2017 /12/4 11:13
 */
public class AFEmployeeCompanyDTO extends BaseEntity {

    private static final long serialVersionUID = -2569837361011495821L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 管理方ID(冗余)
     */
    private String managementId;

    /**
     * 管理方名称(冗余)
     */
    private String managementName;

    /**
     * 公司ID(冗余)
     */
    private String companyId;

    /**
     * 公司名称(冗余)
     */
    private String companyName;

    /**
     * 雇员ID(冗余)
     */
    private String empId;

    /**
     * 雇员姓名(冗余)
     */
    private String empName;

    /**
     * 雇员类型 1:派遣 2:代理
     */
    @NotNull(message = "雇员类型不允许为空")
    private Integer templateType;

    /**
     * 入职日期
     */
    @NotNull(message = "入职日期不允许为空")
    private Date inDate;

    /**
     * 是否代办银行卡
     */
    private Integer isTransActBankCard;

    /**
     * 是否有入职通知
     */
    private Integer isNotice;

    /**
     * 薪资
     */
    private BigDecimal salary;

    /**
     * 工资支付方式(1:用人单位,2:中智)
     */
    private Integer salaryPayType;

    /**
     * 劳动合同开始时间
     */
    private Date laborStartDate;

    /**
     * 劳动合同结束时间
     */
    private Date laborEndDate;

    /**
     * 职务
     */
    private String position;

    /**
     * 试用期薪资
     */
    private BigDecimal trySalary;

    /**
     * 试用期开始时间
     */
    private Date tryStartDate;

    /**
     * 试用期结束时间
     */
    private Date tryEndDate;

    /**
     * 工时（1:定时工作制,2:弹性工作制）
     */
    private Integer workType;

    /**
     * 社保福利办理方 0:大库 1:独立户
     */
    private Integer socialUnit;

    /**
     * 公积金福利办理方 0:大库 1:独立户
     */
    private Integer fundUnit;

    /**
     * 用退工福利办理方  0:大库 1:独立户
     */
    private Integer hireUnit;

    /**
     * 社保缴纳地
     */
    private String socialCityCode;

    /**
     * 公积金缴纳地
     */
    private String fundCityCode;

    /**
     * 离职时间
     */
    private Date outDate;

    /**
     * 离职原因
     */
    private Integer outReason;

    /**
     * 入职状态 0:预增 1:报入职 2:在职 3:报离职 4:离职
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效
     */
    private Integer isAcitive;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets management id.
     *
     * @return the management id
     */
    public String getManagementId() {
        return managementId;
    }

    /**
     * Sets management id.
     *
     * @param managementId the management id
     */
    public void setManagementId(String managementId) {
        this.managementId = managementId;
    }

    /**
     * Gets management name.
     *
     * @return the management name
     */
    public String getManagementName() {
        return managementName;
    }

    /**
     * Sets management name.
     *
     * @param managementName the management name
     */
    public void setManagementName(String managementName) {
        this.managementName = managementName;
    }

    /**
     * Gets company id.
     *
     * @return the company id
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * Sets company id.
     *
     * @param companyId the company id
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * Gets company name.
     *
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets company name.
     *
     * @param companyName the company name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Gets emp id.
     *
     * @return the emp id
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * Sets emp id.
     *
     * @param empId the emp id
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * Gets emp name.
     *
     * @return the emp name
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * Sets emp name.
     *
     * @param empName the emp name
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    /**
     * Gets in date.
     *
     * @return the in date
     */
    public Date getInDate() {
        return inDate;
    }

    /**
     * Sets in date.
     *
     * @param inDate the in date
     */
    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Integer getIsTransActBankCard() {
        return isTransActBankCard;
    }

    public void setIsTransActBankCard(Integer isTransActBankCard) {
        this.isTransActBankCard = isTransActBankCard;
    }

    public Integer getIsNotice() {
        return isNotice;
    }

    public void setIsNotice(Integer isNotice) {
        this.isNotice = isNotice;
    }

    /**
     * Gets salary.
     *
     * @return the salary
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Sets salary.
     *
     * @param salary the salary
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Gets salary pay type.
     *
     * @return the salary pay type
     */
    public Integer getSalaryPayType() {
        return salaryPayType;
    }

    /**
     * Sets salary pay type.
     *
     * @param salaryPayType the salary pay type
     */
    public void setSalaryPayType(Integer salaryPayType) {
        this.salaryPayType = salaryPayType;
    }

    /**
     * Gets labor start date.
     *
     * @return the labor start date
     */
    public Date getLaborStartDate() {
        return laborStartDate;
    }

    /**
     * Sets labor start date.
     *
     * @param laborStartDate the labor start date
     */
    public void setLaborStartDate(Date laborStartDate) {
        this.laborStartDate = laborStartDate;
    }

    /**
     * Gets labor end date.
     *
     * @return the labor end date
     */
    public Date getLaborEndDate() {
        return laborEndDate;
    }

    /**
     * Sets labor end date.
     *
     * @param laborEndDate the labor end date
     */
    public void setLaborEndDate(Date laborEndDate) {
        this.laborEndDate = laborEndDate;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Gets try salary.
     *
     * @return the try salary
     */
    public BigDecimal getTrySalary() {
        return trySalary;
    }

    /**
     * Sets try salary.
     *
     * @param trySalary the try salary
     */
    public void setTrySalary(BigDecimal trySalary) {
        this.trySalary = trySalary;
    }

    /**
     * Gets try start date.
     *
     * @return the try start date
     */
    public Date getTryStartDate() {
        return tryStartDate;
    }

    /**
     * Sets try start date.
     *
     * @param tryStartDate the try start date
     */
    public void setTryStartDate(Date tryStartDate) {
        this.tryStartDate = tryStartDate;
    }

    /**
     * Gets try end date.
     *
     * @return the try end date
     */
    public Date getTryEndDate() {
        return tryEndDate;
    }

    /**
     * Sets try end date.
     *
     * @param tryEndDate the try end date
     */
    public void setTryEndDate(Date tryEndDate) {
        this.tryEndDate = tryEndDate;
    }

    /**
     * Gets work type.
     *
     * @return the work type
     */
    public Integer getWorkType() {
        return workType;
    }

    /**
     * Sets work type.
     *
     * @param workType the work type
     */
    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    /**
     * Gets social unit.
     *
     * @return the social unit
     */
    public Integer getSocialUnit() {
        return socialUnit;
    }

    /**
     * Sets social unit.
     *
     * @param socialUnit the social unit
     */
    public void setSocialUnit(Integer socialUnit) {
        this.socialUnit = socialUnit;
    }

    /**
     * Gets fund unit.
     *
     * @return the fund unit
     */
    public Integer getFundUnit() {
        return fundUnit;
    }

    /**
     * Sets fund unit.
     *
     * @param fundUnit the fund unit
     */
    public void setFundUnit(Integer fundUnit) {
        this.fundUnit = fundUnit;
    }

    /**
     * Gets hire unit.
     *
     * @return the hire unit
     */
    public Integer getHireUnit() {
        return hireUnit;
    }

    /**
     * Sets hire unit.
     *
     * @param hireUnit the hire unit
     */
    public void setHireUnit(Integer hireUnit) {
        this.hireUnit = hireUnit;
    }

    /**
     * Gets social city code.
     *
     * @return the social city code
     */
    public String getSocialCityCode() {
        return socialCityCode;
    }

    /**
     * Sets social city code.
     *
     * @param socialCityCode the social city code
     */
    public void setSocialCityCode(String socialCityCode) {
        this.socialCityCode = socialCityCode;
    }

    /**
     * Gets fund city code.
     *
     * @return the fund city code
     */
    public String getFundCityCode() {
        return fundCityCode;
    }

    /**
     * Sets fund city code.
     *
     * @param fundCityCode the fund city code
     */
    public void setFundCityCode(String fundCityCode) {
        this.fundCityCode = fundCityCode;
    }

    /**
     * Gets out date.
     *
     * @return the out date
     */
    public Date getOutDate() {
        return outDate;
    }

    /**
     * Sets out date.
     *
     * @param outDate the out date
     */
    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    /**
     * Gets out reason.
     *
     * @return the out reason
     */
    public Integer getOutReason() {
        return outReason;
    }

    /**
     * Sets out reason.
     *
     * @param outReason the out reason
     */
    public void setOutReason(Integer outReason) {
        this.outReason = outReason;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Gets remark.
     *
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets remark.
     *
     * @param remark the remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsAcitive() {
        return isAcitive;
    }

    public void setIsAcitive(Integer isAcitive) {
        this.isAcitive = isAcitive;
    }
}
