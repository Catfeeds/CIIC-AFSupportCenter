package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto;

import com.baomidou.mybatisplus.annotations.TableField;

import java.util.Date;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 15:10 2018/3/7
 */
public class AfPeTaskDTO {

    /**
     * 体检任务单编号
     */
    private Integer peTaskId;
    /**
     * 流程编号
     */
    private String processId;
    private Integer peRuleId;
    /**
     * 任务单状态（1-待发放，2-已同步至中盈，3-未预约，4-已预约，5-预约失败，6-已到检，7-已出报告，8-已过期，9-已退订）
     */
    private Integer status;
    /**
     * 体检预约id
     */
    private String bespeakPeId;
    /**
     * 体检产品编号
     */
    private String productId;
    /**
     * 老系统客户id
     */
    private String comNo;
    /**
     * 客户编号
     */
    private String companyId;
    /**
     * 客户名称
     */
    private String companyName;
    /**
     * 雇员编号
     */
    @TableField("employee_id")
    private String employeeId;
    /**
     * 雇员姓名
     */
    private String employeeName;
    /**
     * 雇员性别（1-男，2-女）
     */
    private Integer gender;
    /**
     * 证件类型
     */
    private Integer idType;
    /**
     * 雇员身份证
     */
    private String idNum;
    /**
     * 雇员生日
     */
    private Date birthday;
    /**
     * 雇员邮箱
     */
    private String email;
    /**
     * 雇员婚姻状况（1-已婚，2-未婚）
     */
    private Integer marital;
    /**
     * 支付类型（必填1个人支付，2公司支付）
     */
    private Integer payType;
    /**
     * 体检类别（必填1普通体检，2入职体检）
     */
    private Integer peType;
    /**
     * 体检任务单有效期开始时间
     */
    private Date effectStartDate;
    /**
     * 体检任务单有效期结束时间
     */
    private Date effectEndDate;
    /**
     * 是否企业统一收取报告
     */
    private Boolean companyReport;
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 最后更新时间
     */
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
    private String modifiedBy;

    public String getComNo() {
        return comNo;
    }

    public void setComNo(String comNo) {
        this.comNo = comNo;
    }

    public String getBespeakPeId() {
        return bespeakPeId;
    }

    public void setBespeakPeId(String bespeakPeId) {
        this.bespeakPeId = bespeakPeId;
    }

    public Integer getPeTaskId() {
        return peTaskId;
    }

    public void setPeTaskId(Integer peTaskId) {
        this.peTaskId = peTaskId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Integer getPeRuleId() {
        return peRuleId;
    }

    public void setPeRuleId(Integer peRuleId) {
        this.peRuleId = peRuleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMarital() {
        return marital;
    }

    public void setMarital(Integer marital) {
        this.marital = marital;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPeType() {
        return peType;
    }

    public void setPeType(Integer peType) {
        this.peType = peType;
    }

    public Date getEffectStartDate() {
        return effectStartDate;
    }

    public void setEffectStartDate(Date effectStartDate) {
        this.effectStartDate = effectStartDate;
    }

    public Date getEffectEndDate() {
        return effectEndDate;
    }

    public void setEffectEndDate(Date effectEndDate) {
        this.effectEndDate = effectEndDate;
    }

    public Boolean getCompanyReport() {
        return companyReport;
    }

    public void setCompanyReport(Boolean companyReport) {
        this.companyReport = companyReport;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
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
        return "AfPeTaskDTO{" +
            "peTaskId=" + peTaskId +
            ", bespeakPeId='" + bespeakPeId + '\'' +
            ", processId='" + processId + '\'' +
            ", peRuleId=" + peRuleId +
            ", status=" + status +
            ", productId='" + productId + '\'' +
            ", companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", employeeId='" + employeeId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", gender=" + gender +
            ", idType=" + idType +
            ", idNum='" + idNum + '\'' +
            ", birthday=" + birthday +
            ", email='" + email + '\'' +
            ", marital=" + marital +
            ", payType=" + payType +
            ", peType=" + peType +
            ", effectStartDate=" + effectStartDate +
            ", effectEndDate=" + effectEndDate +
            ", companyReport=" + companyReport +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            '}';
    }
}
