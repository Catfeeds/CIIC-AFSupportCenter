package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto;

import java.util.Date;

/**
 * 雇员转移信息
 */
public class HfEmpTaskTransferInfoDTO {
    /**
     * 雇员任务单ID
     */
    private Long empTaskId;
    /**
     * 客户编号
     */
    private String companyId;
    /**
     * 雇员编号
     */
    private String employeeId;
    /**
     * 公积金类型: 1 基本  2 补充
     */
    private Integer hfType;
    /**
     * 发起时间
     */
    private Date submitTime;
    /**
     * 发起人ID
     */
    private String submitterId;
    /**
     * 转入单位
     */
    private String transferInUnit;
    /**
     * 转入单位账号
     */
    private String transferInUnitAccount;
    /**
     * 转出单位
     */
    private String transferOutUnit;
    /**
     * 转出单位账号
     */
    private String transferOutUnitAccount;
    /**
     * 雇员公积金账号
     */
    private String hfEmpAccount;
    /**
     * 雇员档案ID
     */
    private Long empArchiveId;
    /**
     * 创建人
     */
    private String createdDisplayName;
    /**
     * 转移日期
     */
    private Date transferDate;
    /**
     * 回单日期
     */
    private Date feedbackDate;
    /**
     * 经办人
     */
    private String handleUserName;

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getHfType() {
        return hfType;
    }

    public void setHfType(Integer hfType) {
        this.hfType = hfType;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public String getTransferInUnit() {
        return transferInUnit;
    }

    public void setTransferInUnit(String transferInUnit) {
        this.transferInUnit = transferInUnit;
    }

    public String getTransferInUnitAccount() {
        return transferInUnitAccount;
    }

    public void setTransferInUnitAccount(String transferInUnitAccount) {
        this.transferInUnitAccount = transferInUnitAccount;
    }

    public String getTransferOutUnit() {
        return transferOutUnit;
    }

    public void setTransferOutUnit(String transferOutUnit) {
        this.transferOutUnit = transferOutUnit;
    }

    public String getTransferOutUnitAccount() {
        return transferOutUnitAccount;
    }

    public void setTransferOutUnitAccount(String transferOutUnitAccount) {
        this.transferOutUnitAccount = transferOutUnitAccount;
    }

    public String getHfEmpAccount() {
        return hfEmpAccount;
    }

    public void setHfEmpAccount(String hfEmpAccount) {
        this.hfEmpAccount = hfEmpAccount;
    }

    public Long getEmpArchiveId() {
        return empArchiveId;
    }

    public void setEmpArchiveId(Long empArchiveId) {
        this.empArchiveId = empArchiveId;
    }

    public String getCreatedDisplayName() {
        return createdDisplayName;
    }

    public void setCreatedDisplayName(String createdDisplayName) {
        this.createdDisplayName = createdDisplayName;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getHandleUserName() {
        return handleUserName;
    }

    public void setHandleUserName(String handleUserName) {
        this.handleUserName = handleUserName;
    }
}
