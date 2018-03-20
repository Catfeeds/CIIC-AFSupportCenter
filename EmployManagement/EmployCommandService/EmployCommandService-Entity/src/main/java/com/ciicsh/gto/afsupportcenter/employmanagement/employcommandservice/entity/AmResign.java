package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用工表
 * </p>
 */
@TableName("am_resign")
public class AmResign implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="resign_id", type= IdType.AUTO)
	private Long resignId;
    /**
     * 外键:用工id
     */
	@TableField("employment_id")
	private Long employmentId;
    /**
     * 任务单id
     */
    @TableField("emp_task_id")
    private Long  empTaskId;
    /**
     * 客户Id
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 雇员id
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 退工日期
     */
	@TableField("resign_date")
	private LocalDate resignDate;
    /**
     * 退工原因
     */
	@TableField("resign_reason")
	private String resignReason;
    /**
     * 档案保管意向
     */
	@TableField("doc_hold_intent")
	private String docHoldIntent;
    /**
     * 用工档案缴费止
     */
	@TableField("employ_doc_payment_to")
	private String employDocPaymentTo;
    /**
     * 终止类型:空、合同终止、合同解除
     */
	@TableField("end_type")
	private String endType;
    /**
     * 打印日期
     */
	@TableField("print_date")
	private LocalDate printDate;
    /**
     * 空、上海户籍、居住证、外来从业人员
     */
	@TableField("emp_character")
	private String empCharacter;
    /**
     * 户口所在地、市区人才、徐汇、科技人才、暂存、其他、转公司用工
     */
	@TableField("return_doc_direct")
	private String returnDocDirect;
    /**
     * 退档日期
     */
	@TableField("return_doc_date")
	private LocalDate returnDocDate;
    /**
     * 空、职介所、其他
     */
	@TableField("transfer_way")
	private String transferWay;
    /**
     * 空、档案未退先退工、徐职、转公司、转公司/暂存、转户口转档、无档,退工单自行归档、用工不调、退工不调档、居住证、用工不调,红联自归、退工不调,红联自归、属地管理、农村富裕劳动力、自开退工单
     */
	@TableField("transfer_remark")
	private String transferRemark;
    /**
     * 转移备注补充
     */
	@TableField("transfer_remark1")
	private String transferRemark1;
    /**
     * 空、公司自行管理、外服、四达、北京外企
     */
	@TableField("com_group_out_direct")
	private String comGroupOutDirect;
    /**
     * 暂存日期
     */
	@TableField("cache_date")
	private LocalDate cacheDate;
    /**
     * 退工送办日期
     */
	@TableField("resign_handle_date")
	private LocalDate resignHandleDate;
    /**
     * 退工成功、档案未到先退工、退工盖章未返回、退工失败、前道要求批退、自开退工单,未交、用工已办未反馈、等翻牌联系单、Ukey外借、单项服务,原退工成功、转外地社保,原退工成功、转人员性质无需退工、退工成功,改社保、重复任务单、等修改备案表
     */
	@TableField("resign_feedback1")
	private String resignFeedback1;
    /**
     * 退工反馈日期
     */
	@TableField("resign_feedback_date1")
	private LocalDate resignFeedbackDate1;
    /**
     * “前道要求批退”、“重打退工单”、“退工成功”、“撤销退工”、“撤销退工等修改表”
     */
	@TableField("resign_feedback2")
	private String resignFeedback2;
    /**
     * 退工反馈2日期
     */
	@TableField("resign_feedback_date2")
	private LocalDate resignFeedbackDate2;
    /**
     * 退工操作员
     */
	@TableField("resign_operate_man")
	private String resignOperateMan;
    /**
     * ?用工有,退工怎么还有?
     */
	@TableField("ukey_borrow_date")
	private LocalDate ukeyBorrowDate;
    /**
     * Ukey返回日期
     */
	@TableField("ukey_return_date")
	private LocalDate ukeyReturnDate;
    /**
     * 是否网办1 是 0 否
     */
	@TableField("if_network")
	private Integer ifNetwork;
    /**
     * 职介反馈日期
     */
	@TableField("job_centre_feedback_date")
	private LocalDate jobCentreFeedbackDate;
    /**
     * 退工材料交付日期
     */
	@TableField("resign_material_delivery_date")
	private LocalDate resignMaterialDeliveryDate;
    /**
     * 1 已交 0未交
     */
	@TableField("if_labor_manual_return")
	private Integer ifLaborManualReturn;
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

    public Long getResignId() {
		return resignId;
	}

	public void setResignId(Long resignId) {
		this.resignId = resignId;
	}

	public Long getEmploymentId() {
		return employmentId;
	}

	public void setEmploymentId(Long employmentId) {
		this.employmentId = employmentId;
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

	public LocalDate getResignDate() {
		return resignDate;
	}

	public void setResignDate(LocalDate resignDate) {
		this.resignDate = resignDate;
	}

	public String getResignReason() {
		return resignReason;
	}

	public void setResignReason(String resignReason) {
		this.resignReason = resignReason;
	}

	public String getDocHoldIntent() {
		return docHoldIntent;
	}

	public void setDocHoldIntent(String docHoldIntent) {
		this.docHoldIntent = docHoldIntent;
	}

	public String getEmployDocPaymentTo() {
		return employDocPaymentTo;
	}

	public void setEmployDocPaymentTo(String employDocPaymentTo) {
		this.employDocPaymentTo = employDocPaymentTo;
	}

	public String getEndType() {
		return endType;
	}

	public void setEndType(String endType) {
		this.endType = endType;
	}

	public LocalDate getPrintDate() {
		return printDate;
	}

	public void setPrintDate(LocalDate printDate) {
		this.printDate = printDate;
	}

	public String getEmpCharacter() {
		return empCharacter;
	}

	public void setEmpCharacter(String empCharacter) {
		this.empCharacter = empCharacter;
	}

	public String getReturnDocDirect() {
		return returnDocDirect;
	}

	public void setReturnDocDirect(String returnDocDirect) {
		this.returnDocDirect = returnDocDirect;
	}

	public LocalDate getReturnDocDate() {
		return returnDocDate;
	}

	public void setReturnDocDate(LocalDate returnDocDate) {
		this.returnDocDate = returnDocDate;
	}

	public String getTransferWay() {
		return transferWay;
	}

	public void setTransferWay(String transferWay) {
		this.transferWay = transferWay;
	}

	public String getTransferRemark() {
		return transferRemark;
	}

	public void setTransferRemark(String transferRemark) {
		this.transferRemark = transferRemark;
	}

	public String getTransferRemark1() {
		return transferRemark1;
	}

	public void setTransferRemark1(String transferRemark1) {
		this.transferRemark1 = transferRemark1;
	}

	public String getComGroupOutDirect() {
		return comGroupOutDirect;
	}

	public void setComGroupOutDirect(String comGroupOutDirect) {
		this.comGroupOutDirect = comGroupOutDirect;
	}

	public LocalDate getCacheDate() {
		return cacheDate;
	}

	public void setCacheDate(LocalDate cacheDate) {
		this.cacheDate = cacheDate;
	}

	public LocalDate getResignHandleDate() {
		return resignHandleDate;
	}

	public void setResignHandleDate(LocalDate resignHandleDate) {
		this.resignHandleDate = resignHandleDate;
	}

	public String getResignFeedback1() {
		return resignFeedback1;
	}

	public void setResignFeedback1(String resignFeedback1) {
		this.resignFeedback1 = resignFeedback1;
	}

	public LocalDate getResignFeedbackDate1() {
		return resignFeedbackDate1;
	}

	public void setResignFeedbackDate1(LocalDate resignFeedbackDate1) {
		this.resignFeedbackDate1 = resignFeedbackDate1;
	}

	public String getResignFeedback2() {
		return resignFeedback2;
	}

	public void setResignFeedback2(String resignFeedback2) {
		this.resignFeedback2 = resignFeedback2;
	}

	public LocalDate getResignFeedbackDate2() {
		return resignFeedbackDate2;
	}

	public void setResignFeedbackDate2(LocalDate resignFeedbackDate2) {
		this.resignFeedbackDate2 = resignFeedbackDate2;
	}

	public String getResignOperateMan() {
		return resignOperateMan;
	}

	public void setResignOperateMan(String resignOperateMan) {
		this.resignOperateMan = resignOperateMan;
	}

	public LocalDate getUkeyBorrowDate() {
		return ukeyBorrowDate;
	}

	public void setUkeyBorrowDate(LocalDate ukeyBorrowDate) {
		this.ukeyBorrowDate = ukeyBorrowDate;
	}

	public LocalDate getUkeyReturnDate() {
		return ukeyReturnDate;
	}

	public void setUkeyReturnDate(LocalDate ukeyReturnDate) {
		this.ukeyReturnDate = ukeyReturnDate;
	}

	public Integer getIfNetwork() {
		return ifNetwork;
	}

	public void setIfNetwork(Integer ifNetwork) {
		this.ifNetwork = ifNetwork;
	}

	public LocalDate getJobCentreFeedbackDate() {
		return jobCentreFeedbackDate;
	}

	public void setJobCentreFeedbackDate(LocalDate jobCentreFeedbackDate) {
		this.jobCentreFeedbackDate = jobCentreFeedbackDate;
	}

	public LocalDate getResignMaterialDeliveryDate() {
		return resignMaterialDeliveryDate;
	}

	public void setResignMaterialDeliveryDate(LocalDate resignMaterialDeliveryDate) {
		this.resignMaterialDeliveryDate = resignMaterialDeliveryDate;
	}

	public Integer getIfLaborManualReturn() {
		return ifLaborManualReturn;
	}

	public void setIfLaborManualReturn(Integer ifLaborManualReturn) {
		this.ifLaborManualReturn = ifLaborManualReturn;
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
		return "AmResign{" +
			", resignId=" + resignId +
			", employmentId=" + employmentId +
			", companyId=" + companyId +
			", employeeId=" + employeeId +
			", resignDate=" + resignDate +
			", resignReason=" + resignReason +
			", docHoldIntent=" + docHoldIntent +
			", employDocPaymentTo=" + employDocPaymentTo +
			", endType=" + endType +
			", printDate=" + printDate +
			", empCharacter=" + empCharacter +
			", returnDocDirect=" + returnDocDirect +
			", returnDocDate=" + returnDocDate +
			", transferWay=" + transferWay +
			", transferRemark=" + transferRemark +
			", transferRemark1=" + transferRemark1 +
			", comGroupOutDirect=" + comGroupOutDirect +
			", cacheDate=" + cacheDate +
			", resignHandleDate=" + resignHandleDate +
			", resignFeedback1=" + resignFeedback1 +
			", resignFeedbackDate1=" + resignFeedbackDate1 +
			", resignFeedback2=" + resignFeedback2 +
			", resignFeedbackDate2=" + resignFeedbackDate2 +
			", resignOperateMan=" + resignOperateMan +
			", ukeyBorrowDate=" + ukeyBorrowDate +
			", ukeyReturnDate=" + ukeyReturnDate +
			", ifNetwork=" + ifNetwork +
			", jobCentreFeedbackDate=" + jobCentreFeedbackDate +
			", resignMaterialDeliveryDate=" + resignMaterialDeliveryDate +
			", ifLaborManualReturn=" + ifLaborManualReturn +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
