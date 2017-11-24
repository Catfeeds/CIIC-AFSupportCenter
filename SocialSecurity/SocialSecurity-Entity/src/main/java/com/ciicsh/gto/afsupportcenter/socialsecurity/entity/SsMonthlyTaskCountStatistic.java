package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 首页中，系统用户执行的任务单数的月度分类统计。
 */
@Table(name = "SS_MonthlyTaskCountStatistic")
public class SsMonthlyTaskCountStatistic implements Serializable {
    @Id
    @Column(name = "RecId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recId;

    /**
     * 多租户Id
     */
    @Column(name = "CustomerId")
    private String customerId;

    /**
     * 用户所属部门Id
     */
    @Column(name = "DepartmentId")
    private String departmentId;

    /**
     * 所属年月YYYY
     */
    @Column(name = "SOCYear")
    private Short SOCYear;

    /**
     * 季度1-4
     */
    @Column(name = "SOCQuarter")
    private Byte SOCQuarter;

    /**
     * 月1-12
     */
    @Column(name = "SOCMonth")
    private Byte SOCMonth;

    /**
     * 系统用户Id
     */
    @Column(name = "SysUserId")
    private String sysUserId;

    /**
     * 系统用户姓名
     */
    @Column(name = "SysUserName")
    private String sysUserName;

    /**
     * 环比上月
     */
    @Column(name = "VsLastMonth")
    private String vsLastMonth;

    /**
     * 任务单总数
     */
    @Column(name = "TaskCount")
    private Short taskCount;

    /**
     * 统计分类：1 - 新办 2 - 调整补缴 3 - 转出 4 - 批退
     */
    @Column(name = "TaskCategory")
    private Short taskCategory;

    /**
     * 是否可用
     */
    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "DataChange_CreateTime")
    private LocalDateTime dataChangeCreateTime;

    @Column(name = "DataChange_LastTime")
    private LocalDateTime dataChangeLastTime;

    @Column(name = "CreatedBy")
    private String createdBy;

    /**
     * 修改者登录名
     */
    @Column(name = "ModifiedBy")
    private String modifiedBy;

    private static final long serialVersionUID = 1L;

    /**
     * @return RecId
     */
    public Long getRecId() {
        return recId;
    }

    /**
     * @param recId
     */
    public void setRecId(Long recId) {
        this.recId = recId;
    }

    /**
     * 获取多租户Id
     *
     * @return CustomerId - 多租户Id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置多租户Id
     *
     * @param customerId 多租户Id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 获取用户所属部门Id
     *
     * @return DepartmentId - 用户所属部门Id
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置用户所属部门Id
     *
     * @param departmentId 用户所属部门Id
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    /**
     * 获取所属年月YYYY
     *
     * @return SOCYear - 所属年月YYYY
     */
    public Short getSOCYear() {
        return SOCYear;
    }

    /**
     * 设置所属年月YYYY
     *
     * @param SOCYear 所属年月YYYY
     */
    public void setSOCYear(Short SOCYear) {
        this.SOCYear = SOCYear;
    }

    /**
     * 获取季度1-4
     *
     * @return SOCQuarter - 季度1-4
     */
    public Byte getSOCQuarter() {
        return SOCQuarter;
    }

    /**
     * 设置季度1-4
     *
     * @param SOCQuarter 季度1-4
     */
    public void setSOCQuarter(Byte SOCQuarter) {
        this.SOCQuarter = SOCQuarter;
    }

    /**
     * 获取月1-12
     *
     * @return SOCMonth - 月1-12
     */
    public Byte getSOCMonth() {
        return SOCMonth;
    }

    /**
     * 设置月1-12
     *
     * @param SOCMonth 月1-12
     */
    public void setSOCMonth(Byte SOCMonth) {
        this.SOCMonth = SOCMonth;
    }

    /**
     * 获取系统用户Id
     *
     * @return SysUserId - 系统用户Id
     */
    public String getSysUserId() {
        return sysUserId;
    }

    /**
     * 设置系统用户Id
     *
     * @param sysUserId 系统用户Id
     */
    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId == null ? null : sysUserId.trim();
    }

    /**
     * 获取系统用户姓名
     *
     * @return SysUserName - 系统用户姓名
     */
    public String getSysUserName() {
        return sysUserName;
    }

    /**
     * 设置系统用户姓名
     *
     * @param sysUserName 系统用户姓名
     */
    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName == null ? null : sysUserName.trim();
    }

    /**
     * 获取环比上月
     *
     * @return VsLastMonth - 环比上月
     */
    public String getVsLastMonth() {
        return vsLastMonth;
    }

    /**
     * 设置环比上月
     *
     * @param vsLastMonth 环比上月
     */
    public void setVsLastMonth(String vsLastMonth) {
        this.vsLastMonth = vsLastMonth == null ? null : vsLastMonth.trim();
    }

    /**
     * 获取任务单总数
     *
     * @return TaskCount - 任务单总数
     */
    public Short getTaskCount() {
        return taskCount;
    }

    /**
     * 设置任务单总数
     *
     * @param taskCount 任务单总数
     */
    public void setTaskCount(Short taskCount) {
        this.taskCount = taskCount;
    }

    /**
     * 获取统计分类：1 - 新办 2 - 调整补缴 3 - 转出 4 - 批退
     *
     * @return TaskCategory - 统计分类：1 - 新办 2 - 调整补缴 3 - 转出 4 - 批退
     */
    public Short getTaskCategory() {
        return taskCategory;
    }

    /**
     * 设置统计分类：1 - 新办 2 - 调整补缴 3 - 转出 4 - 批退
     *
     * @param taskCategory 统计分类：1 - 新办 2 - 调整补缴 3 - 转出 4 - 批退
     */
    public void setTaskCategory(Short taskCategory) {
        this.taskCategory = taskCategory;
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