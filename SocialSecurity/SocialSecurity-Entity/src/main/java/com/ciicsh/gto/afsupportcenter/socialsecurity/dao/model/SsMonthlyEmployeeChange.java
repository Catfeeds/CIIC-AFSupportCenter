package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 雇员月度变更主表
 */
@Table(name = "SS_MonthlyEmployeeChange")
public class SsMonthlyEmployeeChange extends BasicModel implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "MonthlyEmployeeChangeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monthlyEmployeeChangeId;

    /**
     * 社保缴费月份，格式是yyyyMM
     */
    @Column(name = "SOCMonth")
    private String SOCMonth;

    /**
     * 上次计算时间
     */
    @Column(name = "LastComputeTime")
    private LocalDateTime lastComputeTime;

    /**
     * 计算用户Id
     */
    @Column(name = "ComputeUserId")
    private String computeUserId;

    /**
     * 客户账号Id
     */
    @Column(name = "ComAccountId")
    private String comAccountId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id
     *
     * @return MonthlyEmployeeChangeId - 记录Id
     */
    public Long getMonthlyEmployeeChangeId() {
        return monthlyEmployeeChangeId;
    }

    /**
     * 设置记录Id
     *
     * @param monthlyEmployeeChangeId 记录Id
     */
    public void setMonthlyEmployeeChangeId(Long monthlyEmployeeChangeId) {
        this.monthlyEmployeeChangeId = monthlyEmployeeChangeId;
    }

    /**
     * 获取社保缴费月份，格式是yyyyMM
     *
     * @return SOCMonth - 社保缴费月份，格式是yyyyMM
     */
    public String getSOCMonth() {
        return SOCMonth;
    }

    /**
     * 设置社保缴费月份，格式是yyyyMM
     *
     * @param SOCMonth 社保缴费月份，格式是yyyyMM
     */
    public void setSOCMonth(String SOCMonth) {
        this.SOCMonth = SOCMonth == null ? null : SOCMonth.trim();
    }

    /**
     * 获取上次计算时间
     *
     * @return LastComputeTime - 上次计算时间
     */
    public LocalDateTime getLastComputeTime() {
        return lastComputeTime;
    }

    /**
     * 设置上次计算时间
     *
     * @param lastComputeTime 上次计算时间
     */
    public void setLastComputeTime(LocalDateTime lastComputeTime) {
        this.lastComputeTime = lastComputeTime;
    }

    /**
     * 获取计算用户Id
     *
     * @return ComputeUserId - 计算用户Id
     */
    public String getComputeUserId() {
        return computeUserId;
    }

    /**
     * 设置计算用户Id
     *
     * @param computeUserId 计算用户Id
     */
    public void setComputeUserId(String computeUserId) {
        this.computeUserId = computeUserId == null ? null : computeUserId.trim();
    }

    /**
     * 获取客户账号Id
     *
     * @return ComAccountId - 客户账号Id
     */
    public String getComAccountId() {
        return comAccountId;
    }

    /**
     * 设置客户账号Id
     *
     * @param comAccountId 客户账号Id
     */
    public void setComAccountId(String comAccountId) {
        this.comAccountId = comAccountId == null ? null : comAccountId.trim();
    }
}