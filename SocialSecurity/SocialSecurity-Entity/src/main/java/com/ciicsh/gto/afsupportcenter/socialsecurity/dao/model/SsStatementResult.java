package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 对账差异结果表
 */
@Table(name = "SS_StatementResult")
public class SsStatementResult extends BasicModel implements Serializable {
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
     * 外键：险种
     */
    @Column(name = "SocialSecurityType")
    private Byte socialSecurityType;

    /**
     * 项目名称
     */
    @Column(name = "ProjectName")
    private String projectName;

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
     * 更新时间
     */
    @Column(name = "DataChange_lastTime")
    private LocalDateTime dataChangelastTime;

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
     * 获取外键：险种
     *
     * @return SocialSecurityType - 外键：险种
     */
    public Byte getSocialSecurityType() {
        return socialSecurityType;
    }

    /**
     * 设置外键：险种
     *
     * @param socialSecurityType 外键：险种
     */
    public void setSocialSecurityType(Byte socialSecurityType) {
        this.socialSecurityType = socialSecurityType;
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
}