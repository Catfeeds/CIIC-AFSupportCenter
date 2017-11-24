package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 独立库客户任务单
 */
@Table(name = "SS_CompanyTask")
public class SsCompanyTask implements Serializable {
    /**
     * 任务单编号
     */
    @Id
    @Column(name = "CompanyTaskId")
    private Long companyTaskId;

    /**
     * 多租户Id
     */
    @Column(name = "CustomerId")
    private String customerId;

    /**
     * 外键,企业社保账户Id
     */
    @Column(name = "ComAccountId")
    private String comAccountId;

    /**
     * 客户Id
     */
    @Column(name = "CompanyId")
    private String companyId;

    /**
     * 引用：DicItem.DicItemValue 1:开户：2：终止
     */
    @Column(name = "TaskCategory")
    private String taskCategory;

    /**
     * 任务名称
     */
    @Column(name = "TaskName")
    private String taskName;

    /**
     * 发起人要求任务完成截止日期
     */
    @Column(name = "ExpireDate")
    private LocalDate expireDate;

    /**
     * 发起人用户名
     */
    @Column(name = "SubmitterId")
    private String submitterId;

    /**
     * 发起人当时所在部门Id
     */
    @Column(name = "SubmitterDeptId")
    private String submitterDeptId;

    /**
     * 发起时间
     */
    @Column(name = "SubmitTime")
    private LocalDateTime submitTime;

    /**
     * 发起时间备注
     */
    @Column(name = "SubmitRemark")
    private String submitRemark;

    /**
     * 任务单上前道系统传递过来的内容，Json格式，如
            {材料名称，收到状态，备注说明}
     */
    @Column(name = "TaskFormContent")
    private String taskFormContent;

    /**
     * 对话记录, Json,
            格式：部门名称 姓名 时间 内容
     */
    @Column(name = "ChatHistroy")
    private String chatHistroy;

    /**
     * 任务单处理状态
     */
    @Column(name = "TaskStatus")
    private Byte taskStatus;

    /**
     * 任务处理人用户Id
     */
    @Column(name = "AgentUserId")
    private String agentUserId;

    /**
     * 任务处理时间
     */
    @Column(name = "ProcessTime")
    private LocalDateTime processTime;

    /**
     * 办理状态：1、材料收缴  2 、受理中  3、送审中  4 已完成
     */
    @Column(name = "HandleStatus")
    private Byte handleStatus;

    /**
     * 受理日期
     */
    @Column(name = "StrartHandleDate")
    private LocalDate strartHandleDate;

    /**
     * 送审日期
     */
    @Column(name = "SendCheckDate")
    private LocalDate sendCheckDate;

    /**
     * 完成日期
     */
    @Column(name = "FinishDate")
    private LocalDate finishDate;

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

    /**
     * EntityId
     */
    @Column(name = "EntityId")
    private String entityId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取任务单编号
     *
     * @return CompanyTaskId - 任务单编号
     */
    public Long getCompanyTaskId() {
        return companyTaskId;
    }

    /**
     * 设置任务单编号
     *
     * @param companyTaskId 任务单编号
     */
    public void setCompanyTaskId(Long companyTaskId) {
        this.companyTaskId = companyTaskId;
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
     * 获取外键,企业社保账户Id
     *
     * @return ComAccountId - 外键,企业社保账户Id
     */
    public String getComAccountId() {
        return comAccountId;
    }

    /**
     * 设置外键,企业社保账户Id
     *
     * @param comAccountId 外键,企业社保账户Id
     */
    public void setComAccountId(String comAccountId) {
        this.comAccountId = comAccountId == null ? null : comAccountId.trim();
    }

    /**
     * 获取客户Id
     *
     * @return CompanyId - 客户Id
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置客户Id
     *
     * @param companyId 客户Id
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * 获取引用：DicItem.DicItemValue 1:开户：2：终止
     *
     * @return TaskCategory - 引用：DicItem.DicItemValue 1:开户：2：终止
     */
    public String getTaskCategory() {
        return taskCategory;
    }

    /**
     * 设置引用：DicItem.DicItemValue 1:开户：2：终止
     *
     * @param taskCategory 引用：DicItem.DicItemValue 1:开户：2：终止
     */
    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory == null ? null : taskCategory.trim();
    }

    /**
     * 获取任务名称
     *
     * @return TaskName - 任务名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置任务名称
     *
     * @param taskName 任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    /**
     * 获取发起人要求任务完成截止日期
     *
     * @return ExpireDate - 发起人要求任务完成截止日期
     */
    public LocalDate getExpireDate() {
        return expireDate;
    }

    /**
     * 设置发起人要求任务完成截止日期
     *
     * @param expireDate 发起人要求任务完成截止日期
     */
    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 获取发起人用户名
     *
     * @return SubmitterId - 发起人用户名
     */
    public String getSubmitterId() {
        return submitterId;
    }

    /**
     * 设置发起人用户名
     *
     * @param submitterId 发起人用户名
     */
    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId == null ? null : submitterId.trim();
    }

    /**
     * 获取发起人当时所在部门Id
     *
     * @return SubmitterDeptId - 发起人当时所在部门Id
     */
    public String getSubmitterDeptId() {
        return submitterDeptId;
    }

    /**
     * 设置发起人当时所在部门Id
     *
     * @param submitterDeptId 发起人当时所在部门Id
     */
    public void setSubmitterDeptId(String submitterDeptId) {
        this.submitterDeptId = submitterDeptId == null ? null : submitterDeptId.trim();
    }

    /**
     * 获取发起时间
     *
     * @return SubmitTime - 发起时间
     */
    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置发起时间
     *
     * @param submitTime 发起时间
     */
    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 获取发起时间备注
     *
     * @return SubmitRemark - 发起时间备注
     */
    public String getSubmitRemark() {
        return submitRemark;
    }

    /**
     * 设置发起时间备注
     *
     * @param submitRemark 发起时间备注
     */
    public void setSubmitRemark(String submitRemark) {
        this.submitRemark = submitRemark == null ? null : submitRemark.trim();
    }

    /**
     * 获取任务单上前道系统传递过来的内容，Json格式，如
            {材料名称，收到状态，备注说明}
     *
     * @return TaskFormContent - 任务单上前道系统传递过来的内容，Json格式，如
            {材料名称，收到状态，备注说明}
     */
    public String getTaskFormContent() {
        return taskFormContent;
    }

    /**
     * 设置任务单上前道系统传递过来的内容，Json格式，如
            {材料名称，收到状态，备注说明}
     *
     * @param taskFormContent 任务单上前道系统传递过来的内容，Json格式，如
            {材料名称，收到状态，备注说明}
     */
    public void setTaskFormContent(String taskFormContent) {
        this.taskFormContent = taskFormContent == null ? null : taskFormContent.trim();
    }

    /**
     * 获取对话记录, Json,
            格式：部门名称 姓名 时间 内容
     *
     * @return ChatHistroy - 对话记录, Json,
            格式：部门名称 姓名 时间 内容
     */
    public String getChatHistroy() {
        return chatHistroy;
    }

    /**
     * 设置对话记录, Json,
            格式：部门名称 姓名 时间 内容
     *
     * @param chatHistroy 对话记录, Json,
            格式：部门名称 姓名 时间 内容
     */
    public void setChatHistroy(String chatHistroy) {
        this.chatHistroy = chatHistroy == null ? null : chatHistroy.trim();
    }

    /**
     * 获取任务单处理状态
     *
     * @return TaskStatus - 任务单处理状态
     */
    public Byte getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置任务单处理状态
     *
     * @param taskStatus 任务单处理状态
     */
    public void setTaskStatus(Byte taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 获取任务处理人用户Id
     *
     * @return AgentUserId - 任务处理人用户Id
     */
    public String getAgentUserId() {
        return agentUserId;
    }

    /**
     * 设置任务处理人用户Id
     *
     * @param agentUserId 任务处理人用户Id
     */
    public void setAgentUserId(String agentUserId) {
        this.agentUserId = agentUserId == null ? null : agentUserId.trim();
    }

    /**
     * 获取任务处理时间
     *
     * @return ProcessTime - 任务处理时间
     */
    public LocalDateTime getProcessTime() {
        return processTime;
    }

    /**
     * 设置任务处理时间
     *
     * @param processTime 任务处理时间
     */
    public void setProcessTime(LocalDateTime processTime) {
        this.processTime = processTime;
    }

    /**
     * 获取办理状态：1、材料收缴  2 、受理中  3、送审中  4 已完成
     *
     * @return HandleStatus - 办理状态：1、材料收缴  2 、受理中  3、送审中  4 已完成
     */
    public Byte getHandleStatus() {
        return handleStatus;
    }

    /**
     * 设置办理状态：1、材料收缴  2 、受理中  3、送审中  4 已完成
     *
     * @param handleStatus 办理状态：1、材料收缴  2 、受理中  3、送审中  4 已完成
     */
    public void setHandleStatus(Byte handleStatus) {
        this.handleStatus = handleStatus;
    }

    /**
     * 获取受理日期
     *
     * @return StrartHandleDate - 受理日期
     */
    public LocalDate getStrartHandleDate() {
        return strartHandleDate;
    }

    /**
     * 设置受理日期
     *
     * @param strartHandleDate 受理日期
     */
    public void setStrartHandleDate(LocalDate strartHandleDate) {
        this.strartHandleDate = strartHandleDate;
    }

    /**
     * 获取送审日期
     *
     * @return SendCheckDate - 送审日期
     */
    public LocalDate getSendCheckDate() {
        return sendCheckDate;
    }

    /**
     * 设置送审日期
     *
     * @param sendCheckDate 送审日期
     */
    public void setSendCheckDate(LocalDate sendCheckDate) {
        this.sendCheckDate = sendCheckDate;
    }

    /**
     * 获取完成日期
     *
     * @return FinishDate - 完成日期
     */
    public LocalDate getFinishDate() {
        return finishDate;
    }

    /**
     * 设置完成日期
     *
     * @param finishDate 完成日期
     */
    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
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

    /**
     * 获取EntityId
     *
     * @return EntityId - EntityId
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * 设置EntityId
     *
     * @param entityId EntityId
     */
    public void setEntityId(String entityId) {
        this.entityId = entityId == null ? null : entityId.trim();
    }
}