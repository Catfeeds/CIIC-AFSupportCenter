package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.statement;


import java.time.LocalDateTime;

/**
 * <p>
 * 雇员月度变更主表
 * </p>
 *
 * @author wengxk
 * @since 2017-12-07
 */
public class SsMonthEmpChangeDTO{

    private static final long serialVersionUID = 1L;

    /**
     * 外键, 企业社保账户名
     */
    private String comAccountName;

    /**
     * 记录Id
     */
    private Long monthEmpChangeId;
    /**
     * 外键：对账单Id
     */
    private Long statementId;
    /**
     * 社保缴费月份，格式是yyyyMM
     */
    private String ssMonth;
    /**
     * 上次计算时间
     */
    private LocalDateTime lastComputeTime;
    /**
     * 计算用户Id
     */
    private String computeUserId;
    /**
     * 变更汇总表类型:YYS\GSY
     */
    private String computeType;
    /**
     * 客户账号Id
     */
    private Long comAccountId;
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */
    private LocalDateTime modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
    private String modifiedBy;


    public Long getMonthEmpChangeId() {
        return monthEmpChangeId;
    }

    public void setMonthEmpChangeId(Long monthEmpChangeId) {
        this.monthEmpChangeId = monthEmpChangeId;
    }

    public Long getStatementId() {
        return statementId;
    }

    public void setStatementId(Long statementId) {
        this.statementId = statementId;
    }

    public String getSsMonth() {
        return ssMonth;
    }

    public void setSsMonth(String ssMonth) {
        this.ssMonth = ssMonth;
    }

    public LocalDateTime getLastComputeTime() {
        return lastComputeTime;
    }

    public void setLastComputeTime(LocalDateTime lastComputeTime) {
        this.lastComputeTime = lastComputeTime;
    }

    public String getComputeUserId() {
        return computeUserId;
    }

    public void setComputeUserId(String computeUserId) {
        this.computeUserId = computeUserId;
    }

    public String getComputeType() {
        return computeType;
    }

    public void setComputeType(String computeType) {
        this.computeType = computeType;
    }

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }
}
