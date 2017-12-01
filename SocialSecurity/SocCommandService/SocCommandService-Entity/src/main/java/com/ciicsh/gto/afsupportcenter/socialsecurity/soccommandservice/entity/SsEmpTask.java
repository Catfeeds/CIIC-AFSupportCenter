package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 本地社保的雇员任务单
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@TableName("ss_emp_task")
public class SsEmpTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员任务单编号
     */
	@TableId(value="emp_task_id", type= IdType.AUTO)
	private Long empTaskId;
    /**
     * EntityId
     */
	@TableField("entity_id")
	private String entityId;
    /**
     * 多租户ID
     */
	@TableField("customer_id")
	private String customerId;
    /**
     * 外键，雇员社保档案Id
     */
	@TableField("emp_archived_id")
	private String empArchivedId;
    /**
     * 任务类型，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账 8 提取
     */
	@TableField("task_category")
	private Integer taskCategory;
    /**
     * 任务截止日期
     */
	@TableField("expire_date")
	private LocalDate expireDate;
    /**
     * 任务单提交人SysUserId
     */
	@TableField("submitter_id")
	private String submitterId;
    /**
     * 任务单提交人所属部门Id
     */
	@TableField("submitter_dept_id")
	private String submitterDeptId;
    /**
     * 是否加急
     */
	private Integer urgent;
    /**
     * 发起时间
     */
	@TableField("submit_time")
	private LocalTime submitTime;
    /**
     * 任务发起人备注
     */
	@TableField("submitter_remark")
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
	@TableField("task_form_content")
	private String taskFormContent;
    /**
     * 聊天历史记录, 
            格式：序号、部门名称、 用户姓名、 时间、 内容
     */
	@TableField("chat_history")
	private String chatHistory;
    /**
     * 任务处理状态:
            1 本月未处理
            2 下月未处理
            3 处理中
            4 已完成 
            5 批退
     */
	@TableField("task_status")
	private Integer taskStatus;
    /**
     * 经办人用户ID
     */
	@TableField("handle_user_id")
	private String handleUserId;
    /**
     * 办理时间
     */
	@TableField("handle_time")
	private LocalTime handleTime;
    /**
     * 社保序号 8 位数字，不足8位按实际位数显示
     */
	@TableField("emp_ss_serial")
	private String empSsSerial;
    /**
     * 办理方式：1 网上申报 2 柜面办理
     */
	@TableField("handle_way")
	private Integer handleWay;
    /**
     * 办理月份
     */
	@TableField("handle_month")
	private String handleMonth;
    /**
     * 办理备注
     */
	@TableField("handle_remark")
	private String handleRemark;
    /**
     * 批退备注
     */
	@TableField("rejection_remark")
	private String rejectionRemark;
    /**
     * 办理状态：1、材料收缴  2 、受理中  3、送审中  4 已完成
     */
	@TableField("handle_status")
	private Integer handleStatus;
    /**
     * 受理日期
     */
	@TableField("strart_handle_date")
	private LocalDate strartHandleDate;
    /**
     * 送审日期
     */
	@TableField("send_check_date")
	private LocalDate sendCheckDate;
    /**
     * 完成日期
     */
	@TableField("finish_date")
	private LocalDate finishDate;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalTime createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
	private LocalTime modifiedTime;
    /**
     * 创建者登录名
     */
	@TableField("created_by")
	private String createdBy;
    /**
     * 修改者登录名
     */
	@TableField("modified_by")
	private String modifiedBy;


	public Long getEmpTaskId() {
		return empTaskId;
	}

	public void setEmpTaskId(Long empTaskId) {
		this.empTaskId = empTaskId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getEmpArchivedId() {
		return empArchivedId;
	}

	public void setEmpArchivedId(String empArchivedId) {
		this.empArchivedId = empArchivedId;
	}

	public Integer getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(Integer taskCategory) {
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

	public String getSubmitterDeptId() {
		return submitterDeptId;
	}

	public void setSubmitterDeptId(String submitterDeptId) {
		this.submitterDeptId = submitterDeptId;
	}

	public Integer getUrgent() {
		return urgent;
	}

	public void setUrgent(Integer urgent) {
		this.urgent = urgent;
	}

	public LocalTime getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(LocalTime submitTime) {
		this.submitTime = submitTime;
	}

	public String getSubmitterRemark() {
		return submitterRemark;
	}

	public void setSubmitterRemark(String submitterRemark) {
		this.submitterRemark = submitterRemark;
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

	public LocalTime getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(LocalTime handleTime) {
		this.handleTime = handleTime;
	}

	public String getEmpSsSerial() {
		return empSsSerial;
	}

	public void setEmpSsSerial(String empSsSerial) {
		this.empSsSerial = empSsSerial;
	}

	public Integer getHandleWay() {
		return handleWay;
	}

	public void setHandleWay(Integer handleWay) {
		this.handleWay = handleWay;
	}

	public String getHandleMonth() {
		return handleMonth;
	}

	public void setHandleMonth(String handleMonth) {
		this.handleMonth = handleMonth;
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

	public Integer getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(Integer handleStatus) {
		this.handleStatus = handleStatus;
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

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalTime modifiedTime) {
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

	@Override
	public String toString() {
		return "SsEmpTask{" +
			", empTaskId=" + empTaskId +
			", entityId=" + entityId +
			", customerId=" + customerId +
			", empArchivedId=" + empArchivedId +
			", taskCategory=" + taskCategory +
			", expireDate=" + expireDate +
			", submitterId=" + submitterId +
			", submitterDeptId=" + submitterDeptId +
			", urgent=" + urgent +
			", submitTime=" + submitTime +
			", submitterRemark=" + submitterRemark +
			", taskFormContent=" + taskFormContent +
			", chatHistory=" + chatHistory +
			", taskStatus=" + taskStatus +
			", handleUserId=" + handleUserId +
			", handleTime=" + handleTime +
			", empSsSerial=" + empSsSerial +
			", handleWay=" + handleWay +
			", handleMonth=" + handleMonth +
			", handleRemark=" + handleRemark +
			", rejectionRemark=" + rejectionRemark +
			", handleStatus=" + handleStatus +
			", strartHandleDate=" + strartHandleDate +
			", sendCheckDate=" + sendCheckDate +
			", finishDate=" + finishDate +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
