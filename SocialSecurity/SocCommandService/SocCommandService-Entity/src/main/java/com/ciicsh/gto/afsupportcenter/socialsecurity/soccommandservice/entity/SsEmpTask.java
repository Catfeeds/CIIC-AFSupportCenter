package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

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
 * 本地社保的雇员任务单
 * </p>
 */
@TableName("ss_emp_task")
public class SsEmpTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员任务单编号
     */
	@TableId(value="emp_task_id", type= IdType.AUTO)
	private Long empTaskId;
	@TableField("company_id")
	private String companyId;
	@TableField("employee_id")
	private String employeeId;
    /**
     * 多租户ID
     */
	@TableField("customer_id")
	private String customerId;
    /**
     * 外键，雇员社保档案主表Id
     */
	@TableField("emp_archive_id")
	private Long empArchiveId;
    /**
     * 任务类型，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6封存 7退账 8 提取 9特殊操作
     */
	@TableField("task_category")
	private Integer taskCategory;
    /**
     * 特殊操作任务类型:
0 无
1 死亡
2 终止
3 修改个人信息
4 修改人员信息
5 退账
6工龄认定
7 特殊工种认定
8 账户合并
9 遗属
10 退休
11 因病丧劳提前退休
12 特殊工种提前退休
13 退休高级专家增资
14 退休军转增资
15 外国人终止提取
16 外来人员医保卡领取
17 医保帐户提取
18 生育津贴领取
     */
	@TableField("task_category_special")
	private Integer taskCategorySpecial;
    /**
     * 是否加急 1 加急 0 空
     */
	private Integer urgent;
    /**
     * 任务单提交人SysUserId
     */
	@TableField("submitter_id")
	private String submitterId;
    /**
     * 发起人姓名
     */
	@TableField("submitter_name")
	private String submitterName;
    /**
     * 任务单提交人所属部门Id
     */
	@TableField("submitter_dept_id")
	private String submitterDeptId;
    /**
     * 发起人当时所在部门名称
     */
	@TableField("submitter_dept_name")
	private String submitterDeptName;
    /**
     * 任务发起时间，通过该日期和客户社保截至日判断本月下月处理
     */
	@TableField("submit_time")
	private LocalDateTime submitTime;
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
            "新进":{"工资":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "转入":{"工资":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
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
     * 实际工资
     */
	private BigDecimal salary;
    /**
     * 人员属性：本地、外地、外籍三险、外籍五险、延迟退休人员
            本地、外地、外籍五险：有五个险种
            外籍三险、延迟退休人员：有三个险种
            
     */
	@TableField("emp_classify")
	private Integer empClassify;
    /**
     * 经办人用户ID
     */
	@TableField("handle_user_id")
	private String handleUserId;
    /**
     * 经办人姓名
     */
	@TableField("handle_user_name")
	private String handleUserName;
    /**
     * 社保序号 10 位数字，不足10位按实际位数显示
     */
	@TableField("emp_ss_serial")
	private String empSsSerial;
    /**
     * 办理方式：1 网上申报 2 柜面办理
     */
	@TableField("handle_way")
	private Integer handleWay;
    /**
     * 办理月份YYYYMM
     */
	@TableField("handle_month")
	private String handleMonth;
    /**
     * 办理备注
     */
	@TableField("handle_remark")
	private String handleRemark;
	@TableField("handle_remark_man")
	private String handleRemarkMan;
	@TableField("handle_remark_date")
	private LocalDate handleRemarkDate;
    /**
     * 批退备注
     */
	@TableField("rejection_remark")
	private String rejectionRemark;
	@TableField("rejection_remark_man")
	private String rejectionRemarkMan;
	@TableField("rejection_remark_date")
	private LocalDate rejectionRemarkDate;
    /**
     * 办理状态：1、未处理 2 、处理中(已办)  3 已完成(已做) 4、批退 5、不需处理
     */
	@TableField("task_status")
	private Integer taskStatus;
    /**
     * 特殊任务办理状态：1、材料收缴  2 、受理中  3、送审中  4 已完成  
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
     * 站内信历史记录, 
            格式：序号、部门名称、 用户姓名、 时间、 内容
     */
	@TableField("chat_history")
	private String chatHistory;
    /**
     * 业务接口ID
     */
	@TableField("business_interface_id")
	private String businessInterfaceId;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Long getEmpArchiveId() {
		return empArchiveId;
	}

	public void setEmpArchiveId(Long empArchiveId) {
		this.empArchiveId = empArchiveId;
	}

	public Integer getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(Integer taskCategory) {
		this.taskCategory = taskCategory;
	}

	public Integer getTaskCategorySpecial() {
		return taskCategorySpecial;
	}

	public void setTaskCategorySpecial(Integer taskCategorySpecial) {
		this.taskCategorySpecial = taskCategorySpecial;
	}

	public Integer getUrgent() {
		return urgent;
	}

	public void setUrgent(Integer urgent) {
		this.urgent = urgent;
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

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Integer getEmpClassify() {
		return empClassify;
	}

	public void setEmpClassify(Integer empClassify) {
		this.empClassify = empClassify;
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

	public String getHandleRemarkMan() {
		return handleRemarkMan;
	}

	public void setHandleRemarkMan(String handleRemarkMan) {
		this.handleRemarkMan = handleRemarkMan;
	}

	public LocalDate getHandleRemarkDate() {
		return handleRemarkDate;
	}

	public void setHandleRemarkDate(LocalDate handleRemarkDate) {
		this.handleRemarkDate = handleRemarkDate;
	}

	public String getRejectionRemark() {
		return rejectionRemark;
	}

	public void setRejectionRemark(String rejectionRemark) {
		this.rejectionRemark = rejectionRemark;
	}

	public String getRejectionRemarkMan() {
		return rejectionRemarkMan;
	}

	public void setRejectionRemarkMan(String rejectionRemarkMan) {
		this.rejectionRemarkMan = rejectionRemarkMan;
	}

	public LocalDate getRejectionRemarkDate() {
		return rejectionRemarkDate;
	}

	public void setRejectionRemarkDate(LocalDate rejectionRemarkDate) {
		this.rejectionRemarkDate = rejectionRemarkDate;
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

	public String getChatHistory() {
		return chatHistory;
	}

	public void setChatHistory(String chatHistory) {
		this.chatHistory = chatHistory;
	}

	public String getBusinessInterfaceId() {
		return businessInterfaceId;
	}

	public void setBusinessInterfaceId(String businessInterfaceId) {
		this.businessInterfaceId = businessInterfaceId;
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
		return "SsEmpTask{" +
			", empTaskId=" + empTaskId +
			", companyId=" + companyId +
			", employeeId=" + employeeId +
			", customerId=" + customerId +
			", empArchiveId=" + empArchiveId +
			", taskCategory=" + taskCategory +
			", taskCategorySpecial=" + taskCategorySpecial +
			", urgent=" + urgent +
			", submitterId=" + submitterId +
			", submitterName=" + submitterName +
			", submitterDeptId=" + submitterDeptId +
			", submitterDeptName=" + submitterDeptName +
			", submitTime=" + submitTime +
			", expireDate=" + expireDate +
			", submitterRemark=" + submitterRemark +
			", taskFormContent=" + taskFormContent +
			", salary=" + salary +
			", empClassify=" + empClassify +
			", handleUserId=" + handleUserId +
			", handleUserName=" + handleUserName +
			", empSsSerial=" + empSsSerial +
			", handleWay=" + handleWay +
			", handleMonth=" + handleMonth +
			", handleRemark=" + handleRemark +
			", handleRemarkMan=" + handleRemarkMan +
			", handleRemarkDate=" + handleRemarkDate +
			", rejectionRemark=" + rejectionRemark +
			", rejectionRemarkMan=" + rejectionRemarkMan +
			", rejectionRemarkDate=" + rejectionRemarkDate +
			", taskStatus=" + taskStatus +
			", handleStatus=" + handleStatus +
			", startHandleDate=" + startHandleDate +
			", sendCheckDate=" + sendCheckDate +
			", finishDate=" + finishDate +
			", startMonth=" + startMonth +
			", endMonth=" + endMonth +
			", chatHistory=" + chatHistory +
			", businessInterfaceId=" + businessInterfaceId +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}