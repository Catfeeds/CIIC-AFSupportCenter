package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 对账差异结果表
 */
@Table(name = "SS_StatementResult")
public class SsStatementResult implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "StatementDetailId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statementDetailId;

    /**
     * 外键，对账单Id
     */
    @Column(name = "StatementId")
    private Long statementId;

    /**
     * 外键:雇员Id
     */
    @Column(name = "EmployeeId")
    private String employeeId;

    /**
     * 项目名称
     */
    @Column(name = "ProjectName")
    private String projectName;

    /**
     * 外键：险种
     */
    @Column(name = "SSType")
    private Byte SSType;

    /**
     * 从社保局导入金额
     */
    @Column(name = "ImpAmount")
    private BigDecimal impAmount;

    /**
     * 社保系统计算出的金额
     */
    @Column(name = "SOCAmount")
    private BigDecimal SOCAmount;

    /**
     * 差值
     */
    @Column(name = "DiffAmount")
    private BigDecimal diffAmount;

    /**
     * 是否有效
     */
    @Column(name = "IsActive")
    private Boolean isActive;

    /**
     * 创建时间
     */
    @Column(name = "DataChange_CreateTime")
    private LocalDateTime dataChangeCreateTime;

    /**
     * 更新时间
     */
    @Column(name = "DataChange_lastTime")
    private LocalDateTime dataChangelastTime;

    /**
     * 创建者用户Id
     */
    @Column(name = "CreatedBy")
    private String createdBy;

    /**
     * 更新者用户Id
     */
    @Column(name = "ModifiedBy")
    private String modifiedBy;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id
     *
     * @return StatementDetailId - 记录Id
     */
    public Long getStatementDetailId() {
        return statementDetailId;
    }

    /**
     * 设置记录Id
     *
     * @param statementDetailId 记录Id
     */
    public void setStatementDetailId(Long statementDetailId) {
        this.statementDetailId = statementDetailId;
    }

    /**
     * 获取外键，对账单Id
     *
     * @return StatementId - 外键，对账单Id
     */
    public Long getStatementId() {
        return statementId;
    }

    /**
     * 设置外键，对账单Id
     *
     * @param statementId 外键，对账单Id
     */
    public void setStatementId(Long statementId) {
        this.statementId = statementId;
    }

    /**
     * 获取外键:雇员Id
     *
     * @return EmployeeId - 外键:雇员Id
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * 设置外键:雇员Id
     *
     * @param employeeId 外键:雇员Id
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    /**
     * 获取项目名称
     *
     * @return ProjectName - 项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置项目名称
     *
     * @param projectName 项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    /**
     * 获取外键：险种
     *
     * @return SSType - 外键：险种
     */
    public Byte getSSType() {
        return SSType;
    }

    /**
     * 设置外键：险种
     *
     * @param SSType 外键：险种
     */
    public void setSSType(Byte SSType) {
        this.SSType = SSType;
    }

    /**
     * 获取从社保局导入金额
     *
     * @return ImpAmount - 从社保局导入金额
     */
    public BigDecimal getImpAmount() {
        return impAmount;
    }

    /**
     * 设置从社保局导入金额
     *
     * @param impAmount 从社保局导入金额
     */
    public void setImpAmount(BigDecimal impAmount) {
        this.impAmount = impAmount;
    }

    /**
     * 获取社保系统计算出的金额
     *
     * @return SOCAmount - 社保系统计算出的金额
     */
    public BigDecimal getSOCAmount() {
        return SOCAmount;
    }

    /**
     * 设置社保系统计算出的金额
     *
     * @param SOCAmount 社保系统计算出的金额
     */
    public void setSOCAmount(BigDecimal SOCAmount) {
        this.SOCAmount = SOCAmount;
    }

    /**
     * 获取差值
     *
     * @return DiffAmount - 差值
     */
    public BigDecimal getDiffAmount() {
        return diffAmount;
    }

    /**
     * 设置差值
     *
     * @param diffAmount 差值
     */
    public void setDiffAmount(BigDecimal diffAmount) {
        this.diffAmount = diffAmount;
    }

    /**
     * 获取是否有效
     *
     * @return IsActive - 是否有效
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * 设置是否有效
     *
     * @param isActive 是否有效
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
     * 获取更新时间
     *
     * @return DataChange_lastTime - 更新时间
     */
    public LocalDateTime getDataChangelastTime() {
        return dataChangelastTime;
    }

    /**
     * 设置更新时间
     *
     * @param dataChangelastTime 更新时间
     */
    public void setDataChangelastTime(LocalDateTime dataChangelastTime) {
        this.dataChangelastTime = dataChangelastTime;
    }

    /**
     * 获取创建者用户Id
     *
     * @return CreatedBy - 创建者用户Id
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建者用户Id
     *
     * @param createdBy 创建者用户Id
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * 获取更新者用户Id
     *
     * @return ModifiedBy - 更新者用户Id
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * 设置更新者用户Id
     *
     * @param modifiedBy 更新者用户Id
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }
}