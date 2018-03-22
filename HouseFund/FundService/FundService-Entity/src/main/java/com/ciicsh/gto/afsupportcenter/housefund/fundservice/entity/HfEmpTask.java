package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员任务单总表
 * </p>
 */
@TableName("hf_emp_task")
public class HfEmpTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员任务单ID
     */
	@TableId(value="emp_task_id", type= IdType.AUTO)
	private Long empTaskId;
    /**
     * 外键
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 外键
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 外键:雇员公积金档案主表
     */
	@TableField("emp_archive_id")
	private Long empArchiveId;
    /**
     * 入职日期
     */
	@TableField("in_date")
	private LocalDateTime inDate;
    /**
     * 离职时间
     */
    @TableField("out_date")
    private LocalDateTime outDate;

    /**
     * 是否更正 1 是 0 否
     */
	@TableField("is_change")
	private Integer isChange;
    /**
     * 公积金类型:1 基本 2 补充
            
     */
	@TableField("hf_type")
	private Integer hfType;
    /**
     * 雇员公积金账号
     */
	@TableField("hf_emp_account")
	private String hfEmpAccount;


    /**
     * 任务单处理类别：1. 雇员新增 2. 雇员终止 3. 雇员补缴 4. 雇员翻牌 5. 雇员服务协议调整 6. 雇员服务协议更正
     7. 集体操作 8. 特殊操作 9. 其它操作
     */
    @TableField("process_category")
    private Integer processCategory;


    /**
     * 任务类型：1 新增(新开) 、2 新增（转入） 、3 新增（启封）、4 调整（封存）、5 调整（启封）、
            6 补缴、7 离职（转出）、8 离职（封存）、9 转移、 10 特殊任务  11 集体转入  12  集体转出  13 翻牌
     */
	@TableField("task_category")
	private Integer taskCategory;
    /**
     * 是否加急  1 是  0  否
     */
	private Integer urgent;
    /**
     * 操作提示:  1 要做、2 中心、3 中智、4 原单位、5 其他独立开户公司、6 外包 
     */
	@TableField("operation_remind")
	private Integer operationRemind;
    /**
     * 操作提示日期
     */
	@TableField("operation_remind_date")
	private LocalDate operationRemindDate;
    /**
     * 任务单提交人SysUserId
     */
	@TableField("submitter_id")
	private String submitterId;
    /**
     * 任务单提交人所属部门
     */
	@TableField("submitter_dept_name")
	private String submitterDeptName;
    /**
     * 发起时间
     */
	@TableField("submit_time")
	private LocalDate submitTime;
    /**
     * 任务截止日期
     */
	@TableField("expire_date")
	private LocalDate expireDate;
    /**
     * 任务发起人备注
     */
	@TableField("submitter_remark")
	private String submitterRemark;
    /**
     * 任务单扩展字段，Json格式描述
            {
            "新开":{"工资":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "转入":{"工资":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "补缴":{"社保补缴基数":"","社保起缴月份":"201706","任务执行日期":""},
            "调整":{"新社保基数":"","调整月份":"201706","任务执行日期":""},
            "转出":{"转出月份":"201706","任务执行日期":""}
            }
     */
	@TableField("task_form_content")
	private String taskFormContent;
    /**
     * 经办人ID
     */
	@TableField("handle_user_id")
	private String handleUserId;
    /**
     * 经办人姓名
     */
	@TableField("handle_user_name")
	private String handleUserName;
    /**
     * 办理日期，为何不像社保那样是办理月份？公积金的办理月份是细化到费用段级别，比如新进的雇员办补缴单月无法办理，必须是下月办理
     */
	@TableField("handle_date")
	private String handleDate;
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
     * 雇员材料签收来往记录
     */
	@TableField("material_sign_record")
	private String materialSignRecord;
    /**
     * 雇员特殊操作的变更内容:
            个人信息修改
            账户合并
            外地公积金转入
            特殊补缴
            错缴更正
            贷款复议
            公积金提取
            ……
     */
	@TableField("special_task")
	private String specialTask;
    /**
     * 办理状态：1、未处理 2 、处理中(已办)  3 已完成(已做) 4、批退 5、不需处理
     */
	@TableField("task_status")
	private Integer taskStatus;
    /**
     * 办理状态：1、材料收缴  2 、受理中  3、送审中  4 已完成  
     */
	@TableField("handle_status")
	private Integer handleStatus;
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
     * 雇员基数：多重身份，如果是新进转入那么就是雇员缴纳基数，如果是补缴，就补缴基数，如果是调整就是调整基数
     */
	@TableField("emp_base")
	private BigDecimal empBase;
    /**
     * 缴费段开始月份YYYYMM
     */
	@TableField("start_month")
	private String startMonth;
    /**
     * 缴费段结束月份YYYYMM
     */
	@TableField("end_month")
	private String endMonth;
    /**
     * 个人比例
     */
	@TableField("ratio_emp")
	private BigDecimal ratioEmp;
    /**
     * 企业比例
     */
	@TableField("ratio_com")
	private BigDecimal ratioCom;
    /**
     * 金额
     */
	private BigDecimal amount;
    /**
     * 转出单位(来源地)
     */
	@TableField("transfer_out_unit")
	private String transferOutUnit;
    /**
     * 转出单位账号
     */
	@TableField("transfer_out_unit_account")
	private String transferOutUnitAccount;
    /**
     * 转入账单
     */
	@TableField("transfer_in_unit")
	private String transferInUnit;
    /**
     * 转入单位账号
     */
	@TableField("transfer_in_unit_account")
	private String transferInUnitAccount;
    /**
     * 转移日期
     */
	@TableField("transfer_date")
	private LocalDate transferDate;
    /**
     * 回单日期：机构办理成功后反馈日期
     */
	@TableField("feedback_date")
	private LocalDate feedbackDate;
    /**
     * 操作日期
     */
	@TableField("operate_date")
	private LocalDate operateDate;
    /**
     * 福利办理方
     */
	@TableField("welfare_unit")
	private Integer welfareUnit;
    /**
     * 业务接口ID  missionId
     */
	@TableField("business_interface_id")
	private String businessInterfaceId;
    /**
     * 前道传递的政策明细ID,用它调用系统中心获取进位方式
     */
	@TableField("policy_detail_id")
	private String policyDetailId;
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
     * 创建者姓名
     */
    @TableField("created_display_name")
    private String createdDisplayName;
    /**
     * 修改者ID
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



	public Long getEmpTaskId() {
		return empTaskId;
	}

	public void setEmpTaskId(Long empTaskId) {
		this.empTaskId = empTaskId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Long getEmpArchiveId() {
		return empArchiveId;
	}

	public void setEmpArchiveId(Long empArchiveId) {
		this.empArchiveId = empArchiveId;
	}

	public LocalDateTime getInDate() {
		return inDate;
	}

	public void setInDate(LocalDateTime inDate) {
		this.inDate = inDate;
	}

    public LocalDateTime getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDateTime outDate) {
        this.outDate = outDate;
    }

    public Integer getIsChange() {
		return isChange;
	}

	public void setIsChange(Integer isChange) {
		this.isChange = isChange;
	}

	public Integer getHfType() {
		return hfType;
	}

	public void setHfType(Integer hfType) {
		this.hfType = hfType;
	}

	public String getHfEmpAccount() {
		return hfEmpAccount;
	}

	public void setHfEmpAccount(String hfEmpAccount) {
		this.hfEmpAccount = hfEmpAccount;
	}

    public Integer getProcessCategory() {
        return processCategory;
    }

    public void setProcessCategory(Integer processCategory) {
        this.processCategory = processCategory;
    }

	public Integer getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(Integer taskCategory) {
		this.taskCategory = taskCategory;
	}

	public Integer getUrgent() {
		return urgent;
	}

	public void setUrgent(Integer urgent) {
		this.urgent = urgent;
	}

	public Integer getOperationRemind() {
		return operationRemind;
	}

	public void setOperationRemind(Integer operationRemind) {
		this.operationRemind = operationRemind;
	}

	public LocalDate getOperationRemindDate() {
		return operationRemindDate;
	}

	public void setOperationRemindDate(LocalDate operationRemindDate) {
		this.operationRemindDate = operationRemindDate;
	}

	public String getSubmitterId() {
		return submitterId;
	}

	public void setSubmitterId(String submitterId) {
		this.submitterId = submitterId;
	}

	public String getSubmitterDeptName() {
		return submitterDeptName;
	}

	public void setSubmitterDeptName(String submitterDeptName) {
		this.submitterDeptName = submitterDeptName;
	}

	public LocalDate getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(LocalDate submitTime) {
		this.submitTime = submitTime;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
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

	public String getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
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

	public String getMaterialSignRecord() {
		return materialSignRecord;
	}

	public void setMaterialSignRecord(String materialSignRecord) {
		this.materialSignRecord = materialSignRecord;
	}

	public String getSpecialTask() {
		return specialTask;
	}

	public void setSpecialTask(String specialTask) {
		this.specialTask = specialTask;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Integer getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(Integer handleStatus) {
		this.handleStatus = handleStatus;
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

	public BigDecimal getEmpBase() {
		return empBase;
	}

	public void setEmpBase(BigDecimal empBase) {
		this.empBase = empBase;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public BigDecimal getRatioEmp() {
		return ratioEmp;
	}

	public void setRatioEmp(BigDecimal ratioEmp) {
		this.ratioEmp = ratioEmp;
	}

	public BigDecimal getRatioCom() {
		return ratioCom;
	}

	public void setRatioCom(BigDecimal ratioCom) {
		this.ratioCom = ratioCom;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTransferOutUnit() {
		return transferOutUnit;
	}

	public void setTransferOutUnit(String transferOutUnit) {
		this.transferOutUnit = transferOutUnit;
	}

	public String getTransferOutUnitAccount() {
		return transferOutUnitAccount;
	}

	public void setTransferOutUnitAccount(String transferOutUnitAccount) {
		this.transferOutUnitAccount = transferOutUnitAccount;
	}

	public String getTransferInUnit() {
		return transferInUnit;
	}

	public void setTransferInUnit(String transferInUnit) {
		this.transferInUnit = transferInUnit;
	}

	public String getTransferInUnitAccount() {
		return transferInUnitAccount;
	}

	public void setTransferInUnitAccount(String transferInUnitAccount) {
		this.transferInUnitAccount = transferInUnitAccount;
	}

	public LocalDate getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(LocalDate transferDate) {
		this.transferDate = transferDate;
	}

	public LocalDate getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(LocalDate feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public LocalDate getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(LocalDate operateDate) {
		this.operateDate = operateDate;
	}

	public Integer getWelfareUnit() {
		return welfareUnit;
	}

	public void setWelfareUnit(Integer welfareUnit) {
		this.welfareUnit = welfareUnit;
	}

	public String getBusinessInterfaceId() {
		return businessInterfaceId;
	}

	public void setBusinessInterfaceId(String businessInterfaceId) {
		this.businessInterfaceId = businessInterfaceId;
	}

	public String getPolicyDetailId() {
		return policyDetailId;
	}

	public void setPolicyDetailId(String policyDetailId) {
		this.policyDetailId = policyDetailId;
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
		return "HfEmpTask{" +
			", empTaskId=" + empTaskId +
			", companyId=" + companyId +
			", employeeId=" + employeeId +
			", empArchiveId=" + empArchiveId +
			", inDate=" + inDate +
            ", outDate" + outDate +
			", isChange=" + isChange +
			", hfType=" + hfType +
			", hfEmpAccount=" + hfEmpAccount +
            ", processCategory=" + processCategory +
			", taskCategory=" + taskCategory +
			", urgent=" + urgent +
			", operationRemind=" + operationRemind +
			", operationRemindDate=" + operationRemindDate +
			", submitterId=" + submitterId +
			", submitterDeptName=" + submitterDeptName +
			", submitTime=" + submitTime +
			", expireDate=" + expireDate +
			", submitterRemark=" + submitterRemark +
			", taskFormContent=" + taskFormContent +
			", handleUserId=" + handleUserId +
			", handleUserName=" + handleUserName +
			", handleDate=" + handleDate +
			", handleRemark=" + handleRemark +
			", rejectionRemark=" + rejectionRemark +
			", materialSignRecord=" + materialSignRecord +
			", specialTask=" + specialTask +
			", taskStatus=" + taskStatus +
			", handleStatus=" + handleStatus +
			", startHandleDate=" + startHandleDate +
			", sendCheckDate=" + sendCheckDate +
			", finishDate=" + finishDate +
			", empBase=" + empBase +
			", startMonth=" + startMonth +
			", endMonth=" + endMonth +
			", ratioEmp=" + ratioEmp +
			", ratioCom=" + ratioCom +
			", amount=" + amount +
			", transferOutUnit=" + transferOutUnit +
			", transferOutUnitAccount=" + transferOutUnitAccount +
			", transferInUnit=" + transferInUnit +
			", transferInUnitAccount=" + transferInUnitAccount +
			", transferDate=" + transferDate +
			", feedbackDate=" + feedbackDate +
			", operateDate=" + operateDate +
			", welfareUnit=" + welfareUnit +
			", businessInterfaceId=" + businessInterfaceId +
			", policyDetailId=" + policyDetailId +
			", taskId=" + taskId +
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
