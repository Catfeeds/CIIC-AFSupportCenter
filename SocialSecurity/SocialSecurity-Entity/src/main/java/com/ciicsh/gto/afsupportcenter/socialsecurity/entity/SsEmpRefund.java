package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 雇员社保退账受理表。
如果为某雇员多缴纳了社保金额，则需向社保局提出退账申请，退账申请受理后落地到此表。
                                               
 */
@Table(name = "SS_EmpRefund")
public class SsEmpRefund implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "EmpRefundId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empRefundId;

    /**
     * 冗余，雇员任务表
     */
    @Column(name = "EmployeeTaskId")
    private String employeeTaskId;

    /**
     * 外键，关联雇员社保档案Id
     */
    @Column(name = "EmpArchiveId")
    private String empArchiveId;

    /**
     * 退账金额
     */
    @Column(name = "Amount")
    private BigDecimal amount;

    /**
     * 处理完成的时间
     */
    @Column(name = "ProcessTime")
    private LocalDateTime processTime;

    /**
     * 处理方式 1 -网上申报 2 柜面办理
     */
    @Column(name = "ProcessWay")
    private Boolean processWay;

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
     * 获取记录Id
     *
     * @return EmpRefundId - 记录Id
     */
    public Integer getEmpRefundId() {
        return empRefundId;
    }

    /**
     * 设置记录Id
     *
     * @param empRefundId 记录Id
     */
    public void setEmpRefundId(Integer empRefundId) {
        this.empRefundId = empRefundId;
    }

    /**
     * 获取冗余，雇员任务表
     *
     * @return EmployeeTaskId - 冗余，雇员任务表
     */
    public String getEmployeeTaskId() {
        return employeeTaskId;
    }

    /**
     * 设置冗余，雇员任务表
     *
     * @param employeeTaskId 冗余，雇员任务表
     */
    public void setEmployeeTaskId(String employeeTaskId) {
        this.employeeTaskId = employeeTaskId == null ? null : employeeTaskId.trim();
    }

    /**
     * 获取外键，关联雇员社保档案Id
     *
     * @return EmpArchiveId - 外键，关联雇员社保档案Id
     */
    public String getEmpArchiveId() {
        return empArchiveId;
    }

    /**
     * 设置外键，关联雇员社保档案Id
     *
     * @param empArchiveId 外键，关联雇员社保档案Id
     */
    public void setEmpArchiveId(String empArchiveId) {
        this.empArchiveId = empArchiveId == null ? null : empArchiveId.trim();
    }

    /**
     * 获取退账金额
     *
     * @return Amount - 退账金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置退账金额
     *
     * @param amount 退账金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取处理完成的时间
     *
     * @return ProcessTime - 处理完成的时间
     */
    public LocalDateTime getProcessTime() {
        return processTime;
    }

    /**
     * 设置处理完成的时间
     *
     * @param processTime 处理完成的时间
     */
    public void setProcessTime(LocalDateTime processTime) {
        this.processTime = processTime;
    }

    /**
     * 获取处理方式 1 -网上申报 2 柜面办理
     *
     * @return ProcessWay - 处理方式 1 -网上申报 2 柜面办理
     */
    public Boolean getProcessWay() {
        return processWay;
    }

    /**
     * 设置处理方式 1 -网上申报 2 柜面办理
     *
     * @param processWay 处理方式 1 -网上申报 2 柜面办理
     */
    public void setProcessWay(Boolean processWay) {
        this.processWay = processWay;
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