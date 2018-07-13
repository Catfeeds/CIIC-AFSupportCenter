package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity;

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
 * @author xsj
 * @since 2018-02-23
 */
@TableName("ss_com_task")
public class SsComTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务单编号
     */
	@TableId(value="com_task_id", type= IdType.AUTO)
	private Long comTaskId;
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
     * 引用：DicItem.DicItemValue 1:开户：2：转入 3：变更 4：终止
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
     * 业务接口ID
     */
	@TableField("business_interface_id")
	private String businessInterfaceId;
	@TableField("task_id")
	private String taskId;
    /**
     * 参保户登记码（前道传递）
     */
	@TableField("ss_account")
	private String ssAccount;
    /**
     * 银行账号(牡丹卡号)（前道传递）
     */
	@TableField("bank_account")
	private String bankAccount;
    /**
     * 养老金账户公司名称（前道传递）
     */
	@TableField("com_account_name")
	private String comAccountName;
    /**
     * 付款银行(一般情况是工商银行)（前道传递）
     */
	@TableField("payment_bank")
	private String paymentBank;
    /**
     * 付款方式：.（前道传递）
            1-我司代付款
            2-客户自付
            3-我司垫付
     */
	@TableField("payment_way")
	private Integer paymentWay;

    @TableField("payment_type")
    private Integer paymentType;
    /**
     * 社保帐单寄到哪里: 1 我司，2 客户公司（前道传递）
     */
	@TableField("bill_receiver")
	private Integer billReceiver;
    /**
     * 行业类别（前道传递）
     */
	@TableField("industry_category")
	private String industryCategory;
    /**
     * 开始月份（前道传递）
     */
	@TableField("start_month")
	private String startMonth;
    /**
     * 客户交付社保费用给中智的截止日（前道传递）
     */
	@TableField("expire_date_front")
	private Integer expireDateFront;
    /**
     * 结算区县(社保局所在上海地区)（前道传递）
     */
	@TableField("settlement_area")
	private String settlementArea;
    /**
     * 法人(前道传递)
     */
	@TableField("legal_person")
	private String legalPerson;
    /**
     * 联系地址(前道传递)
     */
	@TableField("contact_address")
	private String contactAddress;
    /**
     * 发出材料(前道传递):正式通知书、预生成通知书、收据、银行对账单、汇总表、实时表
     */
	@TableField("dispatch_material")
	private String dispatchMaterial;
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
     * 创建者姓名
     */
    @TableField("created_display_name")
    private String createdDisplayName;
    /**
     * 修改者登录名
     */
	@TableField("modified_by")
	private String modifiedBy;
    /**
     * 修改者姓名
     */
    @TableField("modified_display_name")
    private String modifiedDisplayName;
    /**
     * 领导ID
     */
    @TableField("leader_ship_id")
    private String leaderShipId;
    /**
     * 领导姓名
     */
    @TableField("leader_ship_name")
    private String leaderShipName;

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

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

	public String getDispatchMaterial() {
		return dispatchMaterial;
	}

	public void setDispatchMaterial(String dispatchMaterial) {
		this.dispatchMaterial = dispatchMaterial;
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

    public String getCreatedDisplayName() {
        return createdDisplayName;
    }

    public void setCreatedDisplayName(String createdDisplayName) {
        this.createdDisplayName = createdDisplayName;
    }

    public String getModifiedDisplayName() {
        return modifiedDisplayName;
    }

    public void setModifiedDisplayName(String modifiedDisplayName) {
        this.modifiedDisplayName = modifiedDisplayName;
    }

    public String getLeaderShipId() {
        return leaderShipId;
    }

    public void setLeaderShipId(String leaderShipId) {
        this.leaderShipId = leaderShipId;
    }

    public String getLeaderShipName() {
        return leaderShipName;
    }

    public void setLeaderShipName(String leaderShipName) {
        this.leaderShipName = leaderShipName;
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
            ", createdDisplayName=" + createdDisplayName +
            ", modifiedBy=" + modifiedBy +
            ", modifiedDisplayName=" + modifiedDisplayName +
            ", leaderShipId=" + leaderShipId +
            ", leaderShipName=" + leaderShipName +
			"}";
	}
}
