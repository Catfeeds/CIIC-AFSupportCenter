package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;


import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 对账差异结果表
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
public class SsStatementResultBO {

    private static final long serialVersionUID = 1L;


    /**
     * 雇员名
     */
    private String employeeName;

    /**
     * 记录Id
     */
    private Long statementResultId;
    /**
     * 外键，对账单Id
     */
    private Long statementId;
    /**
     * 外键:雇员Id
     */
    private String employeeId;
    /**
     * 变更类型
     */
    private Integer changeType;
    /**
     * 变更类型名称
     */
    private String changeTypeName;
    /**
     * 项目类型
     */
    private Integer projectType;
    /**
     * 项目名称
     */
    private String projectTypeName;
    /**
     * 外键：险种
     */
    private String ssType;
    /**
     * 社保险种名称
     */
    private String ssTypeName;
    /**
     * 从社保局导入金额
     */
    private BigDecimal impAmount;
    /**
     * 社保系统计算出的金额
     */
    private BigDecimal ssAmount;
    /**
     * 差值
     */
    private BigDecimal diffAmount;
    /**
     * 0 正常差异 1 系统不存在  2 导入不存在
     */
    private Integer diffHeadcount;
    /**
     * 是否有效
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 更新时间
     */
    private LocalDateTime modifiedTime;
    /**
     * 创建者用户Id
     */
    private String createdBy;
    /**
     * 更新者用户Id
     */
    private String modifiedBy;


    public Long getStatementDetailId() {
        return statementResultId;
    }

    public void setStatementDetailId(Long statementDetailId) {
        this.statementResultId = statementDetailId;
    }

    public Long getStatementId() {
        return statementId;
    }

    public void setStatementId(Long statementId) {
        this.statementId = statementId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public String getChangeTypeName() {
        return changeTypeName;
    }

    public void setChangeTypeName(String changeTypeName) {
        this.changeTypeName = changeTypeName;
    }

    public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public String getSsTypeName() {
        return ssTypeName;
    }

    public void setSsTypeName(String ssTypeName) {
        this.ssTypeName = ssTypeName;
    }

    public BigDecimal getImpAmount() {
        return impAmount;
    }

    public void setImpAmount(BigDecimal impAmount) {
        this.impAmount = impAmount;
    }

    public BigDecimal getSsAmount() {
        return ssAmount;
    }

    public void setSsAmount(BigDecimal ssAmount) {
        this.ssAmount = ssAmount;
    }

    public BigDecimal getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(BigDecimal diffAmount) {
        this.diffAmount = diffAmount;
    }

    public Integer getDiffHeadcount() {
        return diffHeadcount;
    }

    public void setDiffHeadcount(Integer diffHeadcount) {
        this.diffHeadcount = diffHeadcount;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getSsType() {
        return ssType;
    }

    public void setSsType(String ssType) {
        this.ssType = ssType;
    }
}
