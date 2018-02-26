package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.customer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 独立库客户任务单
 * </p>
 */
public class SsComTask implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long comTaskId;
    /**
     * 多租户Id
     */

    private String customerId;
    /**
     * 外键,企业社保账户Id
     */

    private Long comAccountId;
    /**
     * 客户Id
     */

    private String companyId;
    /**
     * 引用：DicItem.DicItemValue 1:开户：2：转移 3：变更 4：终止
     */

    private String taskCategory;
    /**
     * 发起人要求任务完成截止日期
     */

    private LocalDate expireDate;
    /**
     * 发起人id
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
     * 发起人备注信息
     */

    private String submitRemark;
    /**
     * 任务单上前道系统传递过来的内容，Json格式
     */

    private String taskFormContent;
    /**
     * 对话记录, Json,
     * 格式：部门名称 姓名 时间 内容
     */

    private String chatHistory;
    /**
     * 动态扩展办理数据
     */

    private String dynamicExtend;
    /**
     * 任务单处理状态：0、初始（材料收缴） 1、受理中  2、送审中  3 、已完成  4、批退
     */

    private Integer taskStatus;
    /**
     * 任务处理人用户Id
     */

    private String handleUserId;

    private String handleUserName;
    /**
     * 受理日期
     */

    private LocalDate startHandleDate;
    /**
     * 送审日期
     */

    private LocalDate sendCheckDate;
    /**
     * 完成日期
     */

    private LocalDate finishDate;
    /**
     * 办理备注
     */

    private String handleRemark;
    /**
     * 批退备注
     */

    private String rejectionRemark;
    /**
     * 业务接口ID
     */

    private String businessInterfaceId;

    private String taskId;
    /**
     * 参保户登记码（前道传递）
     */

    private String ssAccount;
    /**
     * 银行账号(牡丹卡号)（前道传递）
     */

    private String bankAccount;
    /**
     * 养老金账户公司名称（前道传递）
     */

    private String comAccountName;
    /**
     * 付款银行(一般情况是工商银行)（前道传递）
     */

    private String paymentBank;
    /**
     * 付款方式：.（前道传递）
     * 1-我司代付款
     * 2-客户自付
     * 3-我司垫付
     */

    private Integer paymentWay;
    /**
     * 社保帐单寄到哪里: 1 我司，2 客户公司（前道传递）
     */

    private Integer billReceiver;
    /**
     * 行业类别（前道传递）
     */

    private String industryCategory;
    /**
     * 开始月份（前道传递）
     */

    private String startMonth;
    /**
     * 客户交付社保费用给中智的截止日（前道传递）
     */

    private Integer expireDateFront;
    /**
     * 结算区县(社保局所在上海地区)（前道传递）
     */

    private String settlementArea;
    /**
     * 法人(前道传递)
     */

    private String legalPerson;
    /**
     * 联系地址(前道传递)
     */

    private String contactAddress;
    /**
     * 发出材料(前道传递):正式通知书、预生成通知书、收据、银行对账单、汇总表、实时表
     */

    private String dispatchMaterial;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
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

    public String getTaskFormContent() {
        return taskFormContent;
    }

    public void setTaskFormContent(String taskFormContent) {
        this.taskFormContent = taskFormContent;
    }

    public String getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(String chatHistory) {
        this.chatHistory = chatHistory;
    }

    public String getDynamicExtend() {
        return dynamicExtend;
    }

    public void setDynamicExtend(String dynamicExtend) {
        this.dynamicExtend = dynamicExtend;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
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

    public LocalDate getStartHandleDate() {
        return startHandleDate;
    }

    public void setStartHandleDate(LocalDate startHandleDate) {
        this.startHandleDate = startHandleDate;
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

    public String getHandleRemark() {
        return handleRemark;
    }

    public void setHandleRemark(String handleRemark) {
        this.handleRemark = handleRemark;
    }

    public String getRejectionRemark() {
        return rejectionRemark;
    }

    public void setRejectionRemark(String rejectionRemark) {
        this.rejectionRemark = rejectionRemark;
    }

    public String getBusinessInterfaceId() {
        return businessInterfaceId;
    }

    public void setBusinessInterfaceId(String businessInterfaceId) {
        this.businessInterfaceId = businessInterfaceId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public String getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(String paymentBank) {
        this.paymentBank = paymentBank;
    }

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    public Integer getBillReceiver() {
        return billReceiver;
    }

    public void setBillReceiver(Integer billReceiver) {
        this.billReceiver = billReceiver;
    }

    public String getIndustryCategory() {
        return industryCategory;
    }

    public void setIndustryCategory(String industryCategory) {
        this.industryCategory = industryCategory;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getExpireDateFront() {
        return expireDateFront;
    }

    public void setExpireDateFront(Integer expireDateFront) {
        this.expireDateFront = expireDateFront;
    }

    public String getSettlementArea() {
        return settlementArea;
    }

    public void setSettlementArea(String settlementArea) {
        this.settlementArea = settlementArea;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
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

    public String getDispatchMaterial() {
        return dispatchMaterial;
    }

    public void setDispatchMaterial(String dispatchMaterial) {
        this.dispatchMaterial = dispatchMaterial;
    }

    @Override
    public String toString() {
        return "SsComTask{" +
            ", comTaskId=" + comTaskId +
            ", customerId=" + customerId +
            ", comAccountId=" + comAccountId +
            ", companyId=" + companyId +
            ", taskCategory=" + taskCategory +
            ", expireDate=" + expireDate +
            ", submitterId=" + submitterId +
            ", submitterName=" + submitterName +
            ", submitterDeptId=" + submitterDeptId +
            ", submitterDeptName=" + submitterDeptName +
            ", submitTime=" + submitTime +
            ", submitRemark=" + submitRemark +
            ", taskFormContent=" + taskFormContent +
            ", chatHistory=" + chatHistory +
            ", dynamicExtend=" + dynamicExtend +
            ", taskStatus=" + taskStatus +
            ", handleUserId=" + handleUserId +
            ", handleUserName=" + handleUserName +
            ", startHandleDate=" + startHandleDate +
            ", sendCheckDate=" + sendCheckDate +
            ", finishDate=" + finishDate +
            ", handleRemark=" + handleRemark +
            ", rejectionRemark=" + rejectionRemark +
            ", businessInterfaceId=" + businessInterfaceId +
            ", taskId=" + taskId +
            ", ssAccount=" + ssAccount +
            ", bankAccount=" + bankAccount +
            ", comAccountName=" + comAccountName +
            ", paymentBank=" + paymentBank +
            ", paymentWay=" + paymentWay +
            ", billReceiver=" + billReceiver +
            ", industryCategory=" + industryCategory +
            ", startMonth=" + startMonth +
            ", expireDateFront=" + expireDateFront +
            ", settlementArea=" + settlementArea +
            ", legalPerson=" + legalPerson +
            ", contactAddress=" + contactAddress +
            ", dispatchMaterial=" + dispatchMaterial +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
