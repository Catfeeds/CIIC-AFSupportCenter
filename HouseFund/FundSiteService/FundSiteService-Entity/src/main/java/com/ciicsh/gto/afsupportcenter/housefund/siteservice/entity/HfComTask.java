package com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 企业任务单总表：有关于企业所有任务单的表单字段都集中记录当前表
Com：公司简写
 * </p>
 */
@TableName("hf_com_task")
public class HfComTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务单id
     */
	@TableId(value="com_task_id", type= IdType.AUTO)
	private Long comTaskId;
    /**
     * 外键：企业公积金账户
            开户任务单，该字段可以为空，直到开始办理，补充该字段
     */
	@TableField("com_account_id")
	private Long comAccountId;
    /**
     * 外键：企业公积金账户分类
            开户任务单，该字段可以为空，直到开始办理，补充该字段
     */
	@TableField("com_account_class_id")
	private Long comAccountClassId;
    /**
     * 客户Id
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 1 开户 2 转入  3 变更 4 终止 5销户
     */
	@TableField("task_category")
	private Integer taskCategory;
    /**
     * 发起人用户名
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
     * 任务发起人备注
     */
	@TableField("submit_remark")
	private String submitRemark;
    /**
     * 任务处理人用户Id
     */
	@TableField("handle_user_id")
	private String handleUserId;
    /**
     * 经办人姓名
     */
	@TableField("handle_user_name")
	private String handleUserName;
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
     * 任务办理状态：0、初始（材料收缴） 1、受理中  2、送审中  3 、已完成  4、批退
     */
	@TableField("task_status")
	private Integer taskStatus;
    /**
     * 客户材料签收来往记录
     */
	@TableField("material_sign_record")
	private String materialSignRecord;
    /**
     * 转移日期
     */
	@TableField("transfer_date")
	private LocalDate transferDate;
    /**
     * 备注（前道传递）
     */
	private String remark;
    /**
     * 企业账户名称（前道传递）
     */
	@TableField("com_account_name")
	private String comAccountName;
    /**
     * 付款方式:
            1 自付（客户自己汇缴给银行，雇员由中智办理）
            2 我司付款（客户预付）
            3 垫付（前道传递）
     */
	@TableField("payment_way")
	private Integer paymentWay;
    /**
     * 客户公积金账户 每月的关账到哪一天1-31（前道传递）
     */
	@TableField("close_day")
	private Integer closeDay;
    /**
     * 企业基本补充公积金账号（前道传递）
     */
	@TableField("hf_com_account")
	private String hfComAccount;
    /**
     * 客户缴费起始年月（前道传递）
     */
	@TableField("com_start_month")
	private String comStartMonth;
    /**
     * 截止缴费年月（截单日）（前道传递）
     */
	@TableField("end_month")
	private String endMonth;
    /**
     * 1 销户 2 公司自做 3 转其他代理商（前道传递）
     */
	@TableField("end_type")
	private Integer endType;
    /**
     * TaskService 反馈的 task_id  流程下的任务ID
     */
	@TableField("task_id")
	private String taskId;
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
		return "HfComTask{" +
			", comTaskId=" + comTaskId +
			", comAccountId=" + comAccountId +
			", comAccountClassId=" + comAccountClassId +
			", companyId=" + companyId +
			", taskCategory=" + taskCategory +
			", submitterId=" + submitterId +
			", submitterName=" + submitterName +
			", submitterDeptId=" + submitterDeptId +
			", submitterDeptName=" + submitterDeptName +
			", submitTime=" + submitTime +
			", submitRemark=" + submitRemark +
			", handleUserId=" + handleUserId +
			", handleUserName=" + handleUserName +
			", strartHandleDate=" + strartHandleDate +
			", sendCheckDate=" + sendCheckDate +
			", finishDate=" + finishDate +
			", taskStatus=" + taskStatus +
			", materialSignRecord=" + materialSignRecord +
			", transferDate=" + transferDate +
			", remark=" + remark +
			", comAccountName=" + comAccountName +
			", paymentWay=" + paymentWay +
			", closeDay=" + closeDay +
			", hfComAccount=" + hfComAccount +
			", comStartMonth=" + comStartMonth +
			", endMonth=" + endMonth +
			", endType=" + endType +
			", taskId=" + taskId +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
