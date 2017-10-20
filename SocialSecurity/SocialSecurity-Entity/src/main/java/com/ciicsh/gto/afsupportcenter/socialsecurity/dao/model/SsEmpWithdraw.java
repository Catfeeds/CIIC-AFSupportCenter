package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 记录本地社保和全国委托社保中，向社保局提取雇员社保金额的业务记录，这是一种特殊业务。
 */
@Table(name = "SS_EmpWithdraw")
public class SsEmpWithdraw extends BasicModel implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "EmpWithdrawId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empWithdrawId;

    /**
     * 本地社保的雇员任务单Id
     */
    @Column(name = "EmployeeTaskId")
    private String employeeTaskId;

    /**
     * 外键，雇员本地社保档案Id
     */
    @Column(name = "EmpArchiveId")
    private String empArchiveId;

    /**
     * 提取金额
     */
    @Column(name = "Amount")
    private BigDecimal amount;

    /**
     * 处理方式：1 网上申报 2 柜面办理
     */
    @Column(name = "ProcessWay")
    private Boolean processWay;

    /**
     * 处理时间
     */
    @Column(name = "ProcessTime")
    private LocalDateTime processTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id
     *
     * @return EmpWithdrawId - 记录Id
     */
    public Long getEmpWithdrawId() {
        return empWithdrawId;
    }

    /**
     * 设置记录Id
     *
     * @param empWithdrawId 记录Id
     */
    public void setEmpWithdrawId(Long empWithdrawId) {
        this.empWithdrawId = empWithdrawId;
    }

    /**
     * 获取本地社保的雇员任务单Id
     *
     * @return EmployeeTaskId - 本地社保的雇员任务单Id
     */
    public String getEmployeeTaskId() {
        return employeeTaskId;
    }

    /**
     * 设置本地社保的雇员任务单Id
     *
     * @param employeeTaskId 本地社保的雇员任务单Id
     */
    public void setEmployeeTaskId(String employeeTaskId) {
        this.employeeTaskId = employeeTaskId == null ? null : employeeTaskId.trim();
    }

    /**
     * 获取外键，雇员本地社保档案Id
     *
     * @return EmpArchiveId - 外键，雇员本地社保档案Id
     */
    public String getEmpArchiveId() {
        return empArchiveId;
    }

    /**
     * 设置外键，雇员本地社保档案Id
     *
     * @param empArchiveId 外键，雇员本地社保档案Id
     */
    public void setEmpArchiveId(String empArchiveId) {
        this.empArchiveId = empArchiveId == null ? null : empArchiveId.trim();
    }

    /**
     * 获取提取金额
     *
     * @return Amount - 提取金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置提取金额
     *
     * @param amount 提取金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取处理方式：1 网上申报 2 柜面办理
     *
     * @return ProcessWay - 处理方式：1 网上申报 2 柜面办理
     */
    public Boolean getProcessWay() {
        return processWay;
    }

    /**
     * 设置处理方式：1 网上申报 2 柜面办理
     *
     * @param processWay 处理方式：1 网上申报 2 柜面办理
     */
    public void setProcessWay(Boolean processWay) {
        this.processWay = processWay;
    }

    /**
     * 获取处理时间
     *
     * @return ProcessTime - 处理时间
     */
    public LocalDateTime getProcessTime() {
        return processTime;
    }

    /**
     * 设置处理时间
     *
     * @param processTime 处理时间
     */
    public void setProcessTime(LocalDateTime processTime) {
        this.processTime = processTime;
    }
}