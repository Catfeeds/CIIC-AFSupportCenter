package com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 公积金企业任务单表
 */
public class HfComTaskDTO implements Serializable {

    private static final long serialVersionUID = 8496749472681315054L;

    /**
     * 任务单id
     */
    private Long comTaskId;
    /**
     * 外键：企业公积金账户
     * 开户任务单，该字段可以为空，直到开始办理，补充该字段
     */
    private Long comAccountId;
    /**
     * 外键：企业公积金账户分类
     * 开户任务单，该字段可以为空，直到开始办理，补充该字段
     */
    private Long comAccountClassId;
    /**
     * 客户Id
     */
    private String companyId;
    /**
     * 1 基本公积金、2 补充公积金
     */
    private Integer hfType;
    /**
     * 1 开户 2 转入  3 变更 4 终止 5销户
     */
    private Integer taskCategory;
    /**
     * 发起人用户名
     */
    private String submitterId;
    /**
     * 发起人姓名
     */
    private String submitterName;
    /**
     * 发起人当时所在部门Id
     */
    private String submitterDeptId;
    /**
     * 发起人当时所在部门名称
     */
    private String submitterDeptName;
    /**
     * 发起时间
     */
    private LocalDateTime submitTime;
    /**
     * 任务发起人备注
     */
    private String submitRemark;
    /**
     * 任务处理人用户Id
     */
    private String handleUserId;
    /**
     * 经办人姓名
     */
    private String handleUserName;
    /**
     * 受理日期
     */
    private LocalDate strartHandleDate;
    /**
     * 送审日期
     */
    private LocalDate sendCheckDate;
    /**
     * 完成日期
     */
    private LocalDate finishDate;
    /**
     * 任务办理状态：0、初始（材料收缴） 1、受理中  2、送审中  3 、已完成  4、批退
     */
    private Integer taskStatus;
    /**
     * 客户材料签收来往记录
     */
    private String materialSignRecord;
    /**
     * 转移日期
     */
    private LocalDate transferDate;
    /**
     * 备注（前道传递）
     */
    private String remark;
    /**
     * 企业账户名称（前道传递）
     */
    private String comAccountName;
    /**
     * 付款方式:
     * 1 自付（客户自己汇缴给银行，雇员由中智办理）
     * 2 我司付款（客户预付）
     * 3 垫付（前道传递）
     */
    private Integer paymentWay;
    /**
     * 客户公积金账户 每月的关账到哪一天1-31（前道传递）
     */
    private Integer closeDay;
    /**
     * 企业基本补充公积金账号（前道传递）
     */
    private String hfComAccount;
    /**
     * 客户缴费起始年月（前道传递）
     */
    private String comStartMonth;
    /**
     * 截止缴费年月（截单日）（前道传递）
     */
    private String endMonth;
    /**
     * 1 销户 2 公司自做 3 转其他代理商（前道传递）
     */
    private Integer endType;
    /**
     * TaskService 反馈的 task_id  流程下的任务ID
     */
    private String taskId;
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


    public Long getComTaskId() {
        return comTaskId;
    }

    public void setComTaskId(Long comTaskId) {
        this.comTaskId = comTaskId;
    }

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public Long getComAccountClassId() {
        return comAccountClassId;
    }

    public void setComAccountClassId(Long comAccountClassId) {
        this.comAccountClassId = comAccountClassId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getHfType() {
        return hfType;
    }

    public void setHfType(Integer hfType) {
        this.hfType = hfType;
    }

    public Integer getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(Integer taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName;
    }

    public String getSubmitterDeptId() {
        return submitterDeptId;
    }

    public void setSubmitterDeptId(String submitterDeptId) {
        this.submitterDeptId = submitterDeptId;
    }

    public String getSubmitterDeptName() {
        return submitterDeptName;
    }

    public void setSubmitterDeptName(String submitterDeptName) {
        this.submitterDeptName = submitterDeptName;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    public String getSubmitRemark() {
        return submitRemark;
    }

    public void setSubmitRemark(String submitRemark) {
        this.submitRemark = submitRemark;
    }

    public String getHandleUserId() {
        return handleUserId;
    }

    public void setHandleUserId(String handleUserId) {
        this.handleUserId = handleUserId;
    }

    public String getHandleUserName() {
        return handleUserName;
    }

    public void setHandleUserName(String handleUserName) {
        this.handleUserName = handleUserName;
    }

    public LocalDate getStrartHandleDate() {
        return strartHandleDate;
    }

    public void setStrartHandleDate(LocalDate strartHandleDate) {
        this.strartHandleDate = strartHandleDate;
    }

    public LocalDate getSendCheckDate() {
        return sendCheckDate;
    }

    public void setSendCheckDate(LocalDate sendCheckDate) {
        this.sendCheckDate = sendCheckDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getMaterialSignRecord() {
        return materialSignRecord;
    }

    public void setMaterialSignRecord(String materialSignRecord) {
        this.materialSignRecord = materialSignRecord;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    public Integer getCloseDay() {
        return closeDay;
    }

    public void setCloseDay(Integer closeDay) {
        this.closeDay = closeDay;
    }

    public String getHfComAccount() {
        return hfComAccount;
    }

    public void setHfComAccount(String hfComAccount) {
        this.hfComAccount = hfComAccount;
    }

    public String getComStartMonth() {
        return comStartMonth;
    }

    public void setComStartMonth(String comStartMonth) {
        this.comStartMonth = comStartMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public Integer getEndType() {
        return endType;
    }

    public void setEndType(Integer endType) {
        this.endType = endType;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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
}
