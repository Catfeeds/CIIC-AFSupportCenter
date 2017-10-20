package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 月度缴费明细报表
 */
@Table(name = "SS_MonthlyCompanyPay")
public class SsMonthlyCompanyPay extends BasicModel implements Serializable {
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
    @Column(name = "SOCMonth")
    private String SOCMonth;

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
     * @return SOCMonth - 社保缴纳月份，格式为yyyyMM
     */
    public String getSOCMonth() {
        return SOCMonth;
    }

    /**
     * 设置社保缴纳月份，格式为yyyyMM
     *
     * @param SOCMonth 社保缴纳月份，格式为yyyyMM
     */
    public void setSOCMonth(String SOCMonth) {
        this.SOCMonth = SOCMonth == null ? null : SOCMonth.trim();
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
}