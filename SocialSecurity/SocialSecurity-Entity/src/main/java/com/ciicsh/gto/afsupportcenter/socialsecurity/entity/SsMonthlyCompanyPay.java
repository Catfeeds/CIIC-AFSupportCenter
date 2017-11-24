package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 月度缴费明细报表，系统在每月26日晚上自动生成每月的明细数据，用户可重新生成
 */
@Table(name = "SS_MonthlyCompanyPay")
public class SsMonthlyCompanyPay implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "MonthlyCompanyPayId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monthlyCompanyPayId;

    /**
     * 外键：企业社保账户
     */
    @Column(name = "ComAccountId")
    private String comAccountId;

    /**
     * 社保缴纳月份，格式为yyyyMM
     */
    @Column(name = "SSMonth")
    private String SSMonth;

    /**
     * 外键：gtofrontdb.CMY_COMPANY
     */
    @Column(name = "CompanyId")
    private String companyId;

    /**
     * 计算人用户Id
     */
    @Column(name = "ComputeUserId")
    private String computeUserId;

    /**
     * 计算时间
     */
    @Column(name = "LastComputeTime")
    private LocalDateTime lastComputeTime;

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
     * @return MonthlyCompanyPayId - 记录Id
     */
    public Long getMonthlyCompanyPayId() {
        return monthlyCompanyPayId;
    }

    /**
     * 设置记录Id
     *
     * @param monthlyCompanyPayId 记录Id
     */
    public void setMonthlyCompanyPayId(Long monthlyCompanyPayId) {
        this.monthlyCompanyPayId = monthlyCompanyPayId;
    }

    /**
     * 获取外键：企业社保账户
     *
     * @return ComAccountId - 外键：企业社保账户
     */
    public String getComAccountId() {
        return comAccountId;
    }

    /**
     * 设置外键：企业社保账户
     *
     * @param comAccountId 外键：企业社保账户
     */
    public void setComAccountId(String comAccountId) {
        this.comAccountId = comAccountId == null ? null : comAccountId.trim();
    }

    /**
     * 获取社保缴纳月份，格式为yyyyMM
     *
     * @return SSMonth - 社保缴纳月份，格式为yyyyMM
     */
    public String getSSMonth() {
        return SSMonth;
    }

    /**
     * 设置社保缴纳月份，格式为yyyyMM
     *
     * @param SSMonth 社保缴纳月份，格式为yyyyMM
     */
    public void setSSMonth(String SSMonth) {
        this.SSMonth = SSMonth == null ? null : SSMonth.trim();
    }

    /**
     * 获取外键：gtofrontdb.CMY_COMPANY
     *
     * @return CompanyId - 外键：gtofrontdb.CMY_COMPANY
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置外键：gtofrontdb.CMY_COMPANY
     *
     * @param companyId 外键：gtofrontdb.CMY_COMPANY
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * 获取计算人用户Id
     *
     * @return ComputeUserId - 计算人用户Id
     */
    public String getComputeUserId() {
        return computeUserId;
    }

    /**
     * 设置计算人用户Id
     *
     * @param computeUserId 计算人用户Id
     */
    public void setComputeUserId(String computeUserId) {
        this.computeUserId = computeUserId == null ? null : computeUserId.trim();
    }

    /**
     * 获取计算时间
     *
     * @return LastComputeTime - 计算时间
     */
    public LocalDateTime getLastComputeTime() {
        return lastComputeTime;
    }

    /**
     * 设置计算时间
     *
     * @param lastComputeTime 计算时间
     */
    public void setLastComputeTime(LocalDateTime lastComputeTime) {
        this.lastComputeTime = lastComputeTime;
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