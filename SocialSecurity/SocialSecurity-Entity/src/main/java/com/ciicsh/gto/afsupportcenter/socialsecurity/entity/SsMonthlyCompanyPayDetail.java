package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 雇员月度费用明细
 */
@Table(name = "SS_MonthlyCompanyPayDetail")
public class SsMonthlyCompanyPayDetail implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "MonthlyCompanyPayDetailId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monthlyCompanyPayDetailId;

    /**
     * 外键, 关联到月度缴费明细报表
     */
    @Column(name = "MonthlyCompanyPayId")
    private Long monthlyCompanyPayId;

    /**
     * 外键,雇员社保档案Id
     */
    @Column(name = "EmpArchiveId")
    private String empArchiveId;

    /**
     * 社保基数
     */
    @Column(name = "BaseAmount")
    private BigDecimal baseAmount;

    /**
     * 总金额
     */
    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    /**
     * 社保种类：1标准 2新进 3补缴 4调整 5转出
     */
    @Column(name = "CostCategory")
    private Boolean costCategory;

    /**
     * 是否有效, 0-无效 1-有效
     */
    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "DataChange_CreateTime")
    private LocalDateTime dataChangeCreateTime;

    @Column(name = "DataChange_LastTime")
    private LocalDateTime dataChangeLastTime;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedBy")
    private String modifiedBy;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id
     *
     * @return MonthlyCompanyPayDetailId - 记录Id
     */
    public Long getMonthlyCompanyPayDetailId() {
        return monthlyCompanyPayDetailId;
    }

    /**
     * 设置记录Id
     *
     * @param monthlyCompanyPayDetailId 记录Id
     */
    public void setMonthlyCompanyPayDetailId(Long monthlyCompanyPayDetailId) {
        this.monthlyCompanyPayDetailId = monthlyCompanyPayDetailId;
    }

    /**
     * 获取外键, 关联到月度缴费明细报表
     *
     * @return MonthlyCompanyPayId - 外键, 关联到月度缴费明细报表
     */
    public Long getMonthlyCompanyPayId() {
        return monthlyCompanyPayId;
    }

    /**
     * 设置外键, 关联到月度缴费明细报表
     *
     * @param monthlyCompanyPayId 外键, 关联到月度缴费明细报表
     */
    public void setMonthlyCompanyPayId(Long monthlyCompanyPayId) {
        this.monthlyCompanyPayId = monthlyCompanyPayId;
    }

    /**
     * 获取外键,雇员社保档案Id
     *
     * @return EmpArchiveId - 外键,雇员社保档案Id
     */
    public String getEmpArchiveId() {
        return empArchiveId;
    }

    /**
     * 设置外键,雇员社保档案Id
     *
     * @param empArchiveId 外键,雇员社保档案Id
     */
    public void setEmpArchiveId(String empArchiveId) {
        this.empArchiveId = empArchiveId == null ? null : empArchiveId.trim();
    }

    /**
     * 获取社保基数
     *
     * @return BaseAmount - 社保基数
     */
    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    /**
     * 设置社保基数
     *
     * @param baseAmount 社保基数
     */
    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    /**
     * 获取总金额
     *
     * @return TotalAmount - 总金额
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置总金额
     *
     * @param totalAmount 总金额
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取社保种类：1标准 2新进 3补缴 4调整 5转出
     *
     * @return CostCategory - 社保种类：1标准 2新进 3补缴 4调整 5转出
     */
    public Boolean getCostCategory() {
        return costCategory;
    }

    /**
     * 设置社保种类：1标准 2新进 3补缴 4调整 5转出
     *
     * @param costCategory 社保种类：1标准 2新进 3补缴 4调整 5转出
     */
    public void setCostCategory(Boolean costCategory) {
        this.costCategory = costCategory;
    }

    /**
     * 获取是否有效, 0-无效 1-有效
     *
     * @return IsActive - 是否有效, 0-无效 1-有效
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * 设置是否有效, 0-无效 1-有效
     *
     * @param isActive 是否有效, 0-无效 1-有效
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return DataChange_CreateTime
     */
    public LocalDateTime getDataChangeCreateTime() {
        return dataChangeCreateTime;
    }

    /**
     * @param dataChangeCreateTime
     */
    public void setDataChangeCreateTime(LocalDateTime dataChangeCreateTime) {
        this.dataChangeCreateTime = dataChangeCreateTime;
    }

    /**
     * @return DataChange_LastTime
     */
    public LocalDateTime getDataChangeLastTime() {
        return dataChangeLastTime;
    }

    /**
     * @param dataChangeLastTime
     */
    public void setDataChangeLastTime(LocalDateTime dataChangeLastTime) {
        this.dataChangeLastTime = dataChangeLastTime;
    }

    /**
     * @return CreatedBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * @return ModifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }
}