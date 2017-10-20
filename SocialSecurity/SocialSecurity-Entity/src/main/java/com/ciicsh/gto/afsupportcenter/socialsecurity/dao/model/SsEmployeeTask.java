package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 本地社保的雇员任务单
 */
@Table(name = "SS_EmployeeTask")
public class SsEmployeeTask extends BasicModel implements Serializable {
    /**
     * 雇员任务单编号
     */
    @Id
    @Column(name = "EmployeeTaskId")
    private String employeeTaskId;

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
     * 外键, 雇员Id
     */
    @Column(name = "EmployeeID")
    private String employeeID;

    /**
     * 外键, 客户Id
     */
    @Column(name = "CompanyID")
    private String companyID;

    /**
     * 任务大类，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账 8 提取
     */
    @Column(name = "TaskCategory")
    private Byte taskCategory;

    /**
     * 任务名称
     */
    @Column(name = "TaskName")
    private String taskName;

    /**
     * 截止日期
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
             1-待处理 
            2-已完成 
            3-批退
     */
    @Column(name = "TaskStatus")
    private Integer taskStatus;

    /**
     * 经办人用户ID
     */
    @Column(name = "AgentUserId")
    private String agentUserId;

    /**
     * 办理时间
     */
    @Column(name = "ProcessTime")
    private LocalDateTime processTime;

    /**
     * 外键, 工作流Id, gtoworkflowdb.WE_Workflow
     */
    @Column(name = "WFId")
    private Integer WFId;

    /**
     * 外键, 工作流任务节点Id, gtoworkflowdb.WE_Node
     */
    @Column(name = "NodeId")
    private Integer nodeId;

    /**
     * 社保序号 8 位数字，不足8位按实际位数显示
     */
    @Column(name = "SOCSerial")
    private String SOCSerial;

    /**
     * 办理方式：1 网上申报 2 柜面办理
     */
    @Column(name = "ProcessWay")
    private Byte processWay;

    /**
     * 办理月份
     */
    @Column(name = "ProcessMonth")
    private String processMonth;

    /**
     * 办理备注
     */
    @Column(name = "ProcessRemark")
    private String processRemark;

    /**
     * 批退备注
     */
    @Column(name = "RejectionRemark")
    private String rejectionRemark;

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

    private static final long serialVersionUID = 1L;

    /**
     * 获取雇员任务单编号
     *
     * @return EmployeeTaskId - 雇员任务单编号
     */
    public String getEmployeeTaskId() {
        return employeeTaskId;
    }

    /**
     * 设置雇员任务单编号
     *
     * @param employeeTaskId 雇员任务单编号
     */
    public void setEmployeeTaskId(String employeeTaskId) {
        this.employeeTaskId = employeeTaskId == null ? null : employeeTaskId.trim();
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
     * 获取外键, 雇员Id
     *
     * @return EmployeeID - 外键, 雇员Id
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * 设置外键, 雇员Id
     *
     * @param employeeID 外键, 雇员Id
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID == null ? null : employeeID.trim();
    }

    /**
     * 获取外键, 客户Id
     *
     * @return CompanyID - 外键, 客户Id
     */
    public String getCompanyID() {
        return companyID;
    }

    /**
     * 设置外键, 客户Id
     *
     * @param companyID 外键, 客户Id
     */
    public void setCompanyID(String companyID) {
        this.companyID = companyID == null ? null : companyID.trim();
    }

    /**
     * 获取任务大类，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账 8 提取
     *
     * @return TaskCategory - 任务大类，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账 8 提取
     */
    public Byte getTaskCategory() {
        return taskCategory;
    }

    /**
     * 设置任务大类，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账 8 提取
     *
     * @param taskCategory 任务大类，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账 8 提取
     */
    public void setTaskCategory(Byte taskCategory) {
        this.taskCategory = taskCategory;
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
     * 获取截止日期
     *
     * @return ExpireDate - 截止日期
     */
    public LocalDate getExpireDate() {
        return expireDate;
    }

    /**
     * 设置截止日期
     *
     * @param expireDate 截止日期
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
             1-待处理 
            2-已完成 
            3-批退
     *
     * @return TaskStatus - 任务处理状态:
             1-待处理 
            2-已完成 
            3-批退
     */
    public Integer getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置任务处理状态:
             1-待处理 
            2-已完成 
            3-批退
     *
     * @param taskStatus 任务处理状态:
             1-待处理 
            2-已完成 
            3-批退
     */
    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 获取经办人用户ID
     *
     * @return AgentUserId - 经办人用户ID
     */
    public String getAgentUserId() {
        return agentUserId;
    }

    /**
     * 设置经办人用户ID
     *
     * @param agentUserId 经办人用户ID
     */
    public void setAgentUserId(String agentUserId) {
        this.agentUserId = agentUserId == null ? null : agentUserId.trim();
    }

    /**
     * 获取办理时间
     *
     * @return ProcessTime - 办理时间
     */
    public LocalDateTime getProcessTime() {
        return processTime;
    }

    /**
     * 设置办理时间
     *
     * @param processTime 办理时间
     */
    public void setProcessTime(LocalDateTime processTime) {
        this.processTime = processTime;
    }

    /**
     * 获取外键, 工作流Id, gtoworkflowdb.WE_Workflow
     *
     * @return WFId - 外键, 工作流Id, gtoworkflowdb.WE_Workflow
     */
    public Integer getWFId() {
        return WFId;
    }

    /**
     * 设置外键, 工作流Id, gtoworkflowdb.WE_Workflow
     *
     * @param WFId 外键, 工作流Id, gtoworkflowdb.WE_Workflow
     */
    public void setWFId(Integer WFId) {
        this.WFId = WFId;
    }

    /**
     * 获取外键, 工作流任务节点Id, gtoworkflowdb.WE_Node
     *
     * @return NodeId - 外键, 工作流任务节点Id, gtoworkflowdb.WE_Node
     */
    public Integer getNodeId() {
        return nodeId;
    }

    /**
     * 设置外键, 工作流任务节点Id, gtoworkflowdb.WE_Node
     *
     * @param nodeId 外键, 工作流任务节点Id, gtoworkflowdb.WE_Node
     */
    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * 获取社保序号 8 位数字，不足8位按实际位数显示
     *
     * @return SOCSerial - 社保序号 8 位数字，不足8位按实际位数显示
     */
    public String getSOCSerial() {
        return SOCSerial;
    }

    /**
     * 设置社保序号 8 位数字，不足8位按实际位数显示
     *
     * @param SOCSerial 社保序号 8 位数字，不足8位按实际位数显示
     */
    public void setSOCSerial(String SOCSerial) {
        this.SOCSerial = SOCSerial == null ? null : SOCSerial.trim();
    }

    /**
     * 获取办理方式：1 网上申报 2 柜面办理
     *
     * @return ProcessWay - 办理方式：1 网上申报 2 柜面办理
     */
    public Byte getProcessWay() {
        return processWay;
    }

    /**
     * 设置办理方式：1 网上申报 2 柜面办理
     *
     * @param processWay 办理方式：1 网上申报 2 柜面办理
     */
    public void setProcessWay(Byte processWay) {
        this.processWay = processWay;
    }

    /**
     * 获取办理月份
     *
     * @return ProcessMonth - 办理月份
     */
    public String getProcessMonth() {
        return processMonth;
    }

    /**
     * 设置办理月份
     *
     * @param processMonth 办理月份
     */
    public void setProcessMonth(String processMonth) {
        this.processMonth = processMonth == null ? null : processMonth.trim();
    }

    /**
     * 获取办理备注
     *
     * @return ProcessRemark - 办理备注
     */
    public String getProcessRemark() {
        return processRemark;
    }

    /**
     * 设置办理备注
     *
     * @param processRemark 办理备注
     */
    public void setProcessRemark(String processRemark) {
        this.processRemark = processRemark == null ? null : processRemark.trim();
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
}