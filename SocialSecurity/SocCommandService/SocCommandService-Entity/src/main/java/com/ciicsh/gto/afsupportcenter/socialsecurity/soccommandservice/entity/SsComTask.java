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
 * 独立库客户任务单
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@TableName("ss_com_task")
public class SsComTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务单编号
     */
	@TableId(value="company_task_id", type= IdType.AUTO)
	private Long companyTaskId;
    /**
     * 多租户Id
     */
	@TableField("customer_id")
	private String customerId;
    /**
     * 外键,企业社保账户Id
     */
	@TableField("com_account_id")
	private Long comAccountId;
    /**
     * 客户Id
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 引用：DicItem.DicItemValue 1:开户：2：终止
     */
	@TableField("task_category")
	private String taskCategory;
    /**
     * 任务名称
     */
	@TableField("task_name")
	private String taskName;
    /**
     * 发起人要求任务完成截止日期
     */
	@TableField("expire_date")
	private LocalDate expireDate;
    /**
     * 发起人用户名
     */
	@TableField("submitter_id")
	private String submitterId;
    /**
     * 发起人当时所在部门Id
     */
	@TableField("submitter_dept_id")
	private String submitterDeptId;
    /**
     * 发起时间
     */
	@TableField("submit_time")
	private LocalTime submitTime;
    /**
     * 发起时间备注
     */
	@TableField("submit_remark")
	private String submitRemark;
    /**
     * 任务单上前道系统传递过来的内容，Json格式，如
            {材料名称，收到状态，备注说明}
     */
	@TableField("task_form_content")
	private String taskFormContent;
    /**
     * 对话记录, Json,
            格式：部门名称 姓名 时间 内容
     */
	@TableField("chat_histroy")
	private String chatHistroy;
    /**
     * 任务单处理状态
     */
	@TableField("task_status")
	private Integer taskStatus;
    /**
     * 任务处理人用户Id
     */
	@TableField("agent_user_id")
	private String agentUserId;
    /**
     * 任务处理时间
     */
	@TableField("process_time")
	private LocalTime processTime;
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
    /**
     * EntityId
     */
	@TableField("entity_id")
	private String entityId;


	public Long getCompanyTaskId() {
		return companyTaskId;
	}

	public void setCompanyTaskId(Long companyTaskId) {
		this.companyTaskId = companyTaskId;
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

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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

	public LocalTime getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(LocalTime submitTime) {
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

	public String getChatHistroy() {
		return chatHistroy;
	}

	public void setChatHistroy(String chatHistroy) {
		this.chatHistroy = chatHistroy;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getAgentUserId() {
		return agentUserId;
	}

	public void setAgentUserId(String agentUserId) {
		this.agentUserId = agentUserId;
	}

	public LocalTime getProcessTime() {
		return processTime;
	}

	public void setProcessTime(LocalTime processTime) {
		this.processTime = processTime;
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

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	@Override
	public String toString() {
		return "SsComTask{" +
			", companyTaskId=" + companyTaskId +
			", customerId=" + customerId +
			", comAccountId=" + comAccountId +
			", companyId=" + companyId +
			", taskCategory=" + taskCategory +
			", taskName=" + taskName +
			", expireDate=" + expireDate +
			", submitterId=" + submitterId +
			", submitterDeptId=" + submitterDeptId +
			", submitTime=" + submitTime +
			", submitRemark=" + submitRemark +
			", taskFormContent=" + taskFormContent +
			", chatHistroy=" + chatHistroy +
			", taskStatus=" + taskStatus +
			", agentUserId=" + agentUserId +
			", processTime=" + processTime +
			", handleStatus=" + handleStatus +
			", strartHandleDate=" + strartHandleDate +
			", sendCheckDate=" + sendCheckDate +
			", finishDate=" + finishDate +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			", entityId=" + entityId +
			"}";
	}
}
