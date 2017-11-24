package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 雇员正常汇缴社保的基数分段表(每段一个基数)， 每次社保基数变更（比如年调）或补缴都会更新这张表
 */
@Table(name = "SS_EmpBasePeriod")
public class SsEmpBasePeriod implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "EmpBasePeriodId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empBasePeriodId;

    /**
     * 外键，雇员本地社保档案Id
     */
    @Column(name = "EmpArchiveId")
    private String empArchiveId;

    /**
     * 本地社保的雇员任务单Id
     */
    @Column(name = "EmployeeTaskId")
    private String employeeTaskId;

    /**
     * 基数, 五险合一(基数一致）时有效
     */
    @Column(name = "BaseAmount")
    private BigDecimal baseAmount;

    /**
     * 缴费段开始月份
     */
    @Column(name = "StartMonth")
    private String startMonth;

    /**
     * 缴费段结束月份
     */
    @Column(name = "EndMonth")
    private String endMonth;

    /**
     * 缴纳的社保月份
     */
    @Column(name = "SOCMonth")
    private String SOCMonth;

    /**
     * 汇缴方式:1 - 正常 2 - 补缴
     */
    @Column(name = "RemitWay")
    private Boolean remitWay;

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
     * @return EmpBasePeriodId - 记录Id
     */
    public Long getEmpBasePeriodId() {
        return empBasePeriodId;
    }

    /**
     * 设置记录Id
     *
     * @param empBasePeriodId 记录Id
     */
    public void setEmpBasePeriodId(Long empBasePeriodId) {
        this.empBasePeriodId = empBasePeriodId;
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
     * 获取基数, 五险合一(基数一致）时有效
     *
     * @return BaseAmount - 基数, 五险合一(基数一致）时有效
     */
    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    /**
     * 设置基数, 五险合一(基数一致）时有效
     *
     * @param baseAmount 基数, 五险合一(基数一致）时有效
     */
    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    /**
     * 获取缴费段开始月份
     *
     * @return StartMonth - 缴费段开始月份
     */
    public String getStartMonth() {
        return startMonth;
    }

    /**
     * 设置缴费段开始月份
     *
     * @param startMonth 缴费段开始月份
     */
    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth == null ? null : startMonth.trim();
    }

    /**
     * 获取缴费段结束月份
     *
     * @return EndMonth - 缴费段结束月份
     */
    public String getEndMonth() {
        return endMonth;
    }

    /**
     * 设置缴费段结束月份
     *
     * @param endMonth 缴费段结束月份
     */
    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth == null ? null : endMonth.trim();
    }

    /**
     * 获取缴纳的社保月份
     *
     * @return SOCMonth - 缴纳的社保月份
     */
    public String getSOCMonth() {
        return SOCMonth;
    }

    /**
     * 设置缴纳的社保月份
     *
     * @param SOCMonth 缴纳的社保月份
     */
    public void setSOCMonth(String SOCMonth) {
        this.SOCMonth = SOCMonth == null ? null : SOCMonth.trim();
    }

    /**
     * 获取汇缴方式:1 - 正常 2 - 补缴
     *
     * @return RemitWay - 汇缴方式:1 - 正常 2 - 补缴
     */
    public Boolean getRemitWay() {
        return remitWay;
    }

    /**
     * 设置汇缴方式:1 - 正常 2 - 补缴
     *
     * @param remitWay 汇缴方式:1 - 正常 2 - 补缴
     */
    public void setRemitWay(Boolean remitWay) {
        this.remitWay = remitWay;
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