package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 用工退工任务单
 * </p>
 *
 * @author xsj
 * @since 2018-03-28
 */
@TableName("am_emp_task")
public class AmEmpTask implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键,可作为任务单序号
	 */
	@TableId(value="emp_task_id", type= IdType.AUTO)
	private Long empTaskId;
	/**
	 * 雇员id
	 */
	@TableField("employee_id")
	private String employeeId;
	/**
	 * 客户Id
	 */
	@TableField("company_id")
	private String companyId;
	/**
	 * 任务类型 1用工  2 退工 3变更 4 特殊

	 */
	@TableField("task_category")
	private Integer taskCategory;
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
	 * 发起时间
	 */
	@TableField("submit_time")
	private LocalDateTime submitTime;
	/**
	 * 任务发起人备注
	 */
	@TableField("submitter_remark")
	private String submitterRemark;
	/**
	 * 任务处理状态：
	 用工状态：1未反馈  2退工成功  3档未到先退工  退工单盖章未返回  退工失败  前道要求批退  其它（重打退工单 | 撤销退工  | 自开退工单  | 未交  |用工已办,未反馈|等翻牌联系单（暂留）|UKey外借|单项服务,原退工成功|转外地社保,原退工成功|转人员性质无需退工|退工成功,改社保|重复任务单|等修改备案表)
	 退工状态：用工材料未签收  已开F单未完成  用工成功  用工失败  前道要求撤消用工 其它（用工材料批退  | 用工成功查无档  |  用工成功，前道已中止  |  重复任务单 |  UKey外借）
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
	private LocalDateTime handleTime;
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
	 * TaskService 反馈的 task_id  流程下的任务ID
	 */
	@TableField("task_id")
	private String taskId;
	@TableField("task_form_content")
	private String taskFormContent;
	/**
	 * 人员性质
	 */
	@TableField("employee_nature")
	private String employeeNature;
	/**
	 * 档案方向
	 */
	@TableField("archive_direction")
	private String archiveDirection;
	/**
	 * 业务主键
	 */
	@TableField("business_interface_id")
	private String businessInterfaceId;
	/**
	 * 离职原因
	 */
	@TableField("out_reason")
	private String outReason;
	/**
	 * 离职原因code
	 */
	@TableField("out_reason_code")
	private String outReasonCode;
	/**
	 * 离职原因
	 */
	@TableField("out_date")
	private Date outDate;
	/**
	 * 用工属性
	 */
	@TableField("employ_property")
	private String employProperty;
	/**
	 * 用工属性编码
	 */
	@TableField("employ_code")
	private Integer employCode;
	/**
	 * 是否翻盘
	 */
	@TableField("change_company")
	private  String  changeCompany;
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

	@TableField("emp_company_id")
	private String empCompanyId;

	@TableField("is_finish")
	private  Boolean isFinish;

    @TableField("hire_task_id")
    private String hireTaskId;

    @TableField("job")
    private  String job;

    @TableField("is_cancel")
    private  String isCancel;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHireTaskId() {
        return hireTaskId;
    }

    public void setHireTaskId(String hireTaskId) {
        this.hireTaskId = hireTaskId;
    }

	public Boolean getFinish() {
		return isFinish;
	}

	public void setFinish(Boolean finish) {
		isFinish = finish;
	}

    public String getChangeCompany() {
        return changeCompany;
    }

    public void setChangeCompany(String changeCompany) {
        this.changeCompany = changeCompany;
    }

    public String getOutReasonCode() {
		return outReasonCode;
	}

	public void setOutReasonCode(String outReasonCode) {
		this.outReasonCode = outReasonCode;
	}

	public Long getEmpTaskId() {
		return empTaskId;
	}

	public void setEmpTaskId(Long empTaskId) {
		this.empTaskId = empTaskId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public String getSubmitterDeptId() {
		return submitterDeptId;
	}

	public void setSubmitterDeptId(String submitterDeptId) {
		this.submitterDeptId = submitterDeptId;
	}

	public LocalDateTime getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(LocalDateTime submitTime) {
		this.submitTime = submitTime;
	}

	public String getSubmitterRemark() {
		return submitterRemark;
	}

	public void setSubmitterRemark(String submitterRemark) {
		this.submitterRemark = submitterRemark;
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

	public LocalDateTime getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(LocalDateTime handleTime) {
		this.handleTime = handleTime;
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

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskFormContent() {
		return taskFormContent;
	}

	public void setTaskFormContent(String taskFormContent) {
		this.taskFormContent = taskFormContent;
	}

	public String getEmployeeNature() {
		return employeeNature;
	}

	public void setEmployeeNature(String employeeNature) {
		this.employeeNature = employeeNature;
	}

	public String getArchiveDirection() {
		return archiveDirection;
	}

	public void setArchiveDirection(String archiveDirection) {
		this.archiveDirection = archiveDirection;
	}

	public String getBusinessInterfaceId() {
		return businessInterfaceId;
	}

	public void setBusinessInterfaceId(String businessInterfaceId) {
		this.businessInterfaceId = businessInterfaceId;
	}

	public String getOutReason() {
		return outReason;
	}

	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getEmployProperty() {
		return employProperty;
	}

	public void setEmployProperty(String employProperty) {
		this.employProperty = employProperty;
	}

	public Integer getEmployCode() {
		return employCode;
	}

	public void setEmployCode(Integer employCode) {
		this.employCode = employCode;
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

    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    public String getEmpCompanyId() {
		return empCompanyId;
	}

	public void setEmpCompanyId(String empCompanyId) {
		this.empCompanyId = empCompanyId;
	}

	@Override
	public String toString() {
		return "AmEmpTask{" +
			"empTaskId=" + empTaskId +
			", employeeId=" + employeeId +
			", companyId=" + companyId +
			", taskCategory=" + taskCategory +
			", submitterId=" + submitterId +
			", submitterDeptId=" + submitterDeptId +
			", submitTime=" + submitTime +
			", submitterRemark=" + submitterRemark +
			", taskStatus=" + taskStatus +
			", handleUserId=" + handleUserId +
			", handleTime=" + handleTime +
			", handleRemark=" + handleRemark +
			", rejectionRemark=" + rejectionRemark +
			", taskId=" + taskId +
			", taskFormContent=" + taskFormContent +
			", employeeNature=" + employeeNature +
			", archiveDirection=" + archiveDirection +
			", businessInterfaceId=" + businessInterfaceId +
			", outReason=" + outReason +
			", outDate=" + outDate +
			", employProperty=" + employProperty +
			", employCode=" + employCode +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			", empCompanyId=" + empCompanyId +
			"}";
	}
}
