package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 本地社保的雇员任务单
 */
@Table(name = "SS_EmployeeTask")
public class SsEmployeeTask implements Serializable {
    /**
     * 雇员任务单编号
     */
    @Id
    @Column(name = "EmployeeTaskId")
    private Long employeeTaskId;

    /**
     * EntityId
     */
    @Column(name = "EntityId")
    private String entityId;

    /**
     * 多租户ID
     */
    @Column(name = "CustomerId")
    private String customerId;

    /**
     * 外键，雇员社保档案Id
     */
    @Column(name = "EmpArchiveId")
    private String empArchiveId;

    /**
     * 任务类型，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账 8 提取
     */
    @Column(name = "TaskCategory")
    private Byte taskCategory;

    /**
     * 任务截止日期
     */
    @Column(name = "ExpireDate")
    private LocalDate expireDate;

    /**
     * 任务单提交人SysUserId
     */
    @Column(name = "SubmitterId")
    private String submitterId;

    /**
     * 任务单提交人所属部门Id
     */
    @Column(name = "SubmitterDeptId")
    private String submitterDeptId;

    /**
     * 是否加急
     */
    @Column(name = "Urgent")
    private Byte urgent;

    /**
     * 发起时间
     */
    @Column(name = "SubmitTime")
    private LocalDateTime submitTime;

    /**
     * 任务发起人备注
     */
    @Column(name = "SubmitterRemark")
    private String submitterRemark;

    /**
     * 任务单内容，Json格式描述
            {
            "新进":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "转入":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "补缴":{"社保补缴基数":"","社保起缴月份":"201706","任务执行日期":""},
            "调整":{"新社保基数":"","调整月份":"201706","任务执行日期":""},
            "转出":{"转出月份":"201706","任务执行日期":""},
            "退账":{},
            "终止":{},
            "提取":{}
            }
     */
    @Column(name = "TaskFormContent")
    private String taskFormContent;

    /**
     * 聊天历史记录, 
            格式：序号、部门名称、 用户姓名、 时间、 内容
     */
    @Column(name = "ChatHistory")
    private String chatHistory;

    /**
     * 任务处理状态:
            1 本月未处理
            2 下月未处理
            3 处理中
            4 已完成 
            5 批退
     */
    @Column(name = "TaskStatus")
    private Integer taskStatus;

    /**
     * 经办人用户ID
     */
    @Column(name = "HandleUserId")
    private String handleUserId;

    /**
     * 办理时间
     */
    @Column(name = "HandleTime")
    private LocalDateTime handleTime;

    /**
     * 社保序号 8 位数字，不足8位按实际位数显示
     */
    @Column(name = "EmpSOCSerial")
    private String empSOCSerial;

    /**
     * 办理方式：1 网上申报 2 柜面办理
     */
    @Column(name = "HandleWay")
    private Byte handleWay;

    /**
     * 办理月份
     */
    @Column(name = "HandleMonth")
    private String handleMonth;

    /**
     * 办理备注
     */
    @Column(name = "HandleRemark")
    private String handleRemark;

    /**
     * 批退备注
     */
    @Column(name = "RejectionRemark")
    private String rejectionRemark;

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

    private static final long serialVersionUID = 1L;

    /**
     * 获取雇员任务单编号
     *
     * @return EmployeeTaskId - 雇员任务单编号
     */
    public Long getEmployeeTaskId() {
        return employeeTaskId;
    }

    /**
     * 设置雇员任务单编号
     *
     * @param employeeTaskId 雇员任务单编号
     */
    public void setEmployeeTaskId(Long employeeTaskId) {
        this.employeeTaskId = employeeTaskId;
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

    /**
     * 获取多租户ID
     *
     * @return CustomerId - 多租户ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置多租户ID
     *
     * @param customerId 多租户ID
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 获取外键，雇员社保档案Id
     *
     * @return EmpArchiveId - 外键，雇员社保档案Id
     */
    public String getEmpArchiveId() {
        return empArchiveId;
    }

    /**
     * 设置外键，雇员社保档案Id
     *
     * @param empArchiveId 外键，雇员社保档案Id
     */
    public void setEmpArchiveId(String empArchiveId) {
        this.empArchiveId = empArchiveId == null ? null : empArchiveId.trim();
    }

    /**
     * 获取任务类型，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账 8 提取
     *
     * @return TaskCategory - 任务类型，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账 8 提取
     */
    public Byte getTaskCategory() {
        return taskCategory;
    }

    /**
     * 设置任务类型，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账 8 提取
     *
     * @param taskCategory 任务类型，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账 8 提取
     */
    public void setTaskCategory(Byte taskCategory) {
        this.taskCategory = taskCategory;
    }

    /**
     * 获取任务截止日期
     *
     * @return ExpireDate - 任务截止日期
     */
    public LocalDate getExpireDate() {
        return expireDate;
    }

    /**
     * 设置任务截止日期
     *
     * @param expireDate 任务截止日期
     */
    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 获取任务单提交人SysUserId
     *
     * @return SubmitterId - 任务单提交人SysUserId
     */
    public String getSubmitterId() {
        return submitterId;
    }

    /**
     * 设置任务单提交人SysUserId
     *
     * @param submitterId 任务单提交人SysUserId
     */
    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId == null ? null : submitterId.trim();
    }

    /**
     * 获取任务单提交人所属部门Id
     *
     * @return SubmitterDeptId - 任务单提交人所属部门Id
     */
    public String getSubmitterDeptId() {
        return submitterDeptId;
    }

    /**
     * 设置任务单提交人所属部门Id
     *
     * @param submitterDeptId 任务单提交人所属部门Id
     */
    public void setSubmitterDeptId(String submitterDeptId) {
        this.submitterDeptId = submitterDeptId == null ? null : submitterDeptId.trim();
    }

    /**
     * 获取是否加急
     *
     * @return Urgent - 是否加急
     */
    public Byte getUrgent() {
        return urgent;
    }

    /**
     * 设置是否加急
     *
     * @param urgent 是否加急
     */
    public void setUrgent(Byte urgent) {
        this.urgent = urgent;
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
     * 获取任务发起人备注
     *
     * @return SubmitterRemark - 任务发起人备注
     */
    public String getSubmitterRemark() {
        return submitterRemark;
    }

    /**
     * 设置任务发起人备注
     *
     * @param submitterRemark 任务发起人备注
     */
    public void setSubmitterRemark(String submitterRemark) {
        this.submitterRemark = submitterRemark == null ? null : submitterRemark.trim();
    }

    /**
     * 获取任务单内容，Json格式描述
            {
            "新进":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "转入":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "补缴":{"社保补缴基数":"","社保起缴月份":"201706","任务执行日期":""},
            "调整":{"新社保基数":"","调整月份":"201706","任务执行日期":""},
            "转出":{"转出月份":"201706","任务执行日期":""},
            "退账":{},
            "终止":{},
            "提取":{}
            }
     *
     * @return TaskFormContent - 任务单内容，Json格式描述
            {
            "新进":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "转入":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "补缴":{"社保补缴基数":"","社保起缴月份":"201706","任务执行日期":""},
            "调整":{"新社保基数":"","调整月份":"201706","任务执行日期":""},
            "转出":{"转出月份":"201706","任务执行日期":""},
            "退账":{},
            "终止":{},
            "提取":{}
            }
     */
    public String getTaskFormContent() {
        return taskFormContent;
    }

    /**
     * 设置任务单内容，Json格式描述
            {
            "新进":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "转入":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "补缴":{"社保补缴基数":"","社保起缴月份":"201706","任务执行日期":""},
            "调整":{"新社保基数":"","调整月份":"201706","任务执行日期":""},
            "转出":{"转出月份":"201706","任务执行日期":""},
            "退账":{},
            "终止":{},
            "提取":{}
            }
     *
     * @param taskFormContent 任务单内容，Json格式描述
            {
            "新进":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "转入":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "补缴":{"社保补缴基数":"","社保起缴月份":"201706","任务执行日期":""},
            "调整":{"新社保基数":"","调整月份":"201706","任务执行日期":""},
            "转出":{"转出月份":"201706","任务执行日期":""},
            "退账":{},
            "终止":{},
            "提取":{}
            }
     */
    public void setTaskFormContent(String taskFormContent) {
        this.taskFormContent = taskFormContent == null ? null : taskFormContent.trim();
    }

    /**
     * 获取聊天历史记录, 
            格式：序号、部门名称、 用户姓名、 时间、 内容
     *
     * @return ChatHistory - 聊天历史记录, 
            格式：序号、部门名称、 用户姓名、 时间、 内容
     */
    public String getChatHistory() {
        return chatHistory;
    }

    /**
     * 设置聊天历史记录, 
            格式：序号、部门名称、 用户姓名、 时间、 内容
     *
     * @param chatHistory 聊天历史记录, 
            格式：序号、部门名称、 用户姓名、 时间、 内容
     */
    public void setChatHistory(String chatHistory) {
        this.chatHistory = chatHistory == null ? null : chatHistory.trim();
    }

    /**
     * 获取任务处理状态:
            1 本月未处理
            2 下月未处理
            3 处理中
            4 已完成 
            5 批退
     *
     * @return TaskStatus - 任务处理状态:
            1 本月未处理
            2 下月未处理
            3 处理中
            4 已完成 
            5 批退
     */
    public Integer getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置任务处理状态:
            1 本月未处理
            2 下月未处理
            3 处理中
            4 已完成 
            5 批退
     *
     * @param taskStatus 任务处理状态:
            1 本月未处理
            2 下月未处理
            3 处理中
            4 已完成 
            5 批退
     */
    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 获取经办人用户ID
     *
     * @return HandleUserId - 经办人用户ID
     */
    public String getHandleUserId() {
        return handleUserId;
    }

    /**
     * 设置经办人用户ID
     *
     * @param handleUserId 经办人用户ID
     */
    public void setHandleUserId(String handleUserId) {
        this.handleUserId = handleUserId == null ? null : handleUserId.trim();
    }

    /**
     * 获取办理时间
     *
     * @return HandleTime - 办理时间
     */
    public LocalDateTime getHandleTime() {
        return handleTime;
    }

    /**
     * 设置办理时间
     *
     * @param handleTime 办理时间
     */
    public void setHandleTime(LocalDateTime handleTime) {
        this.handleTime = handleTime;
    }

    /**
     * 获取社保序号 8 位数字，不足8位按实际位数显示
     *
     * @return EmpSOCSerial - 社保序号 8 位数字，不足8位按实际位数显示
     */
    public String getEmpSOCSerial() {
        return empSOCSerial;
    }

    /**
     * 设置社保序号 8 位数字，不足8位按实际位数显示
     *
     * @param empSOCSerial 社保序号 8 位数字，不足8位按实际位数显示
     */
    public void setEmpSOCSerial(String empSOCSerial) {
        this.empSOCSerial = empSOCSerial == null ? null : empSOCSerial.trim();
    }

    /**
     * 获取办理方式：1 网上申报 2 柜面办理
     *
     * @return HandleWay - 办理方式：1 网上申报 2 柜面办理
     */
    public Byte getHandleWay() {
        return handleWay;
    }

    /**
     * 设置办理方式：1 网上申报 2 柜面办理
     *
     * @param handleWay 办理方式：1 网上申报 2 柜面办理
     */
    public void setHandleWay(Byte handleWay) {
        this.handleWay = handleWay;
    }

    /**
     * 获取办理月份
     *
     * @return HandleMonth - 办理月份
     */
    public String getHandleMonth() {
        return handleMonth;
    }

    /**
     * 设置办理月份
     *
     * @param handleMonth 办理月份
     */
    public void setHandleMonth(String handleMonth) {
        this.handleMonth = handleMonth == null ? null : handleMonth.trim();
    }

    /**
     * 获取办理备注
     *
     * @return HandleRemark - 办理备注
     */
    public String getHandleRemark() {
        return handleRemark;
    }

    /**
     * 设置办理备注
     *
     * @param handleRemark 办理备注
     */
    public void setHandleRemark(String handleRemark) {
        this.handleRemark = handleRemark == null ? null : handleRemark.trim();
    }

    /**
     * 获取批退备注
     *
     * @return RejectionRemark - 批退备注
     */
    public String getRejectionRemark() {
        return rejectionRemark;
    }

    /**
     * 设置批退备注
     *
     * @param rejectionRemark 批退备注
     */
    public void setRejectionRemark(String rejectionRemark) {
        this.rejectionRemark = rejectionRemark == null ? null : rejectionRemark.trim();
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
}