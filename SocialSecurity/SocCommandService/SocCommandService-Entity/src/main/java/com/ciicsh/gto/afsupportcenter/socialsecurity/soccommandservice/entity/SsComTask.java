package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 独立库客户任务单
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-16
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
     * 引用：DicItem.DicItemValue 1:开户：2：转移 3：变更 4：终止
     */
	@TableField("task_category")
	private String taskCategory;
    /**
     * 发起人要求任务完成截止日期
     */
	@TableField("expire_date")
	private LocalDate expireDate;
    /**
     * 发起人id
     */
	@TableField("submitter_id")
	private String submitterId;
    /**
     * 发起人姓名
     */
	@TableField("submitter_name")
	private String submitterName;
    /**
     * 发起人当时所在部门Id
     */
	@TableField("submitter_dept_id")
	private String submitterDeptId;
    /**
     * 发起人当时所在部门名称
     */
	@TableField("submitter_dept_name")
	private String submitterDeptName;
    /**
     * 发起时间
     */
	@TableField("submit_time")
	private LocalDateTime submitTime;
    /**
     * 发起人备注信息
     */
	@TableField("submit_remark")
	private String submitRemark;
    /**
     * 任务单上前道系统传递过来的内容，Json格式
     */
	@TableField("task_form_content")
	private String taskFormContent;
    /**
     * 对话记录, Json,
            格式：部门名称 姓名 时间 内容
     */
	@TableField("chat_history")
	private String chatHistory;
    /**
     * 动态扩展办理数据
     */
	@TableField("dynamic_extend")
	private String dynamicExtend;
    /**
     * 任务单处理状态：0、初始（材料收缴） 1、受理中  2、送审中  3 、已完成  4、批退
     */
	@TableField("task_status")
	private Integer taskStatus;
    /**
     * 任务处理人用户Id
     */
	@TableField("handle_user_id")
	private String handleUserId;
	@TableField("handle_user_name")
	private String handleUserName;
    /**
     * 受理日期
     */
	@TableField("start_handle_date")
	private LocalDate startHandleDate;
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
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
	private LocalDateTime modifiedTime;
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

	@Override
	public String toString() {
		return "SsComTask{" +
			", companyTaskId=" + companyTaskId +
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
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
