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
 * 社保支付批次
 * </p>
 */
@TableName("ss_payment")
public class SsPayment implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="payment_id", type= IdType.AUTO)
	private Long paymentId;
    /**
     * 出账批号
     */
	@TableField("payment_batch_num")
	private String paymentBatchNum;
    /**
     * 申请总金额
     */
	@TableField("total_application_amount")
	private BigDecimal totalApplicationAmount;
    /**
     * 支付年月YYYYMM
     */
	@TableField("payment_month")
	private String paymentMonth;
    /**
     * 支付状态: 3 ,可付(默认)   4,申请中  5,内部审批批退 6,已申请到财务部  7,财务部批退  8,财务部支付成功
     */
	@TableField("payment_state")
	private Integer paymentState;
    /**
     * 制单人
     */
	@TableField("create_payment_user")
	private String createPaymentUser;
    /**
     * 制单日期
     */
	@TableField("create_payment_date")
	private LocalDate createPaymentDate;
    /**
     * 财务支付日期
     */
	@TableField("finance_payment_date")
	private LocalDate financePaymentDate;
    /**
     * 1 大库、2 外包、3独立户
     */
	@TableField("account_type")
	private Integer accountType;
    /**
     * 总雇员数
     */
	@TableField("total_emp_count")
	private Integer totalEmpCount;
    /**
     * 账户总数
     */
	@TableField("total_account")
	private Integer totalAccount;
    /**
     * 客户总数
     */
	@TableField("total_com")
	private Integer totalCom;
    /**
     * 申请备注
     */
	@TableField("apply_remark")
	private String applyRemark;
    /**
     * 批退备注
     */
	@TableField("rejection_remark")
	private String rejectionRemark;
    /**
     * 批退历史备份
            [
            {
            总雇员数：
            账户总数：
            客户总数：
            批退备注：
            批退人：
            批退时间：
            },
            ]
     */
	@TableField("rejection_his")
	private String rejectionHis;
    /**
     * 申请人
     */
	@TableField("request_user")
	private String requestUser;
    /**
     * 申请日期
     */
	@TableField("request_date")
	private LocalDate requestDate;
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


	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentBatchNum() {
		return paymentBatchNum;
	}

	public void setPaymentBatchNum(String paymentBatchNum) {
		this.paymentBatchNum = paymentBatchNum;
	}

	public BigDecimal getTotalApplicationAmount() {
		return totalApplicationAmount;
	}

	public void setTotalApplicationAmount(BigDecimal totalApplicationAmount) {
		this.totalApplicationAmount = totalApplicationAmount;
	}

	public String getPaymentMonth() {
		return paymentMonth;
	}

	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}

	public Integer getPaymentState() {
		return paymentState;
	}

	public void setPaymentState(Integer paymentState) {
		this.paymentState = paymentState;
	}

	public String getCreatePaymentUser() {
		return createPaymentUser;
	}

	public void setCreatePaymentUser(String createPaymentUser) {
		this.createPaymentUser = createPaymentUser;
	}

	public LocalDate getCreatePaymentDate() {
		return createPaymentDate;
	}

	public void setCreatePaymentDate(LocalDate createPaymentDate) {
		this.createPaymentDate = createPaymentDate;
	}

	public LocalDate getFinancePaymentDate() {
		return financePaymentDate;
	}

	public void setFinancePaymentDate(LocalDate financePaymentDate) {
		this.financePaymentDate = financePaymentDate;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getTotalEmpCount() {
		return totalEmpCount;
	}

	public void setTotalEmpCount(Integer totalEmpCount) {
		this.totalEmpCount = totalEmpCount;
	}

	public Integer getTotalAccount() {
		return totalAccount;
	}

	public void setTotalAccount(Integer totalAccount) {
		this.totalAccount = totalAccount;
	}

	public Integer getTotalCom() {
		return totalCom;
	}

	public void setTotalCom(Integer totalCom) {
		this.totalCom = totalCom;
	}

	public String getApplyRemark() {
		return applyRemark;
	}

	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}

	public String getRejectionRemark() {
		return rejectionRemark;
	}

	public void setRejectionRemark(String rejectionRemark) {
		this.rejectionRemark = rejectionRemark;
	}

	public String getRejectionHis() {
		return rejectionHis;
	}

	public void setRejectionHis(String rejectionHis) {
		this.rejectionHis = rejectionHis;
	}

	public String getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
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
		return "SsPayment{" +
			", paymentId=" + paymentId +
			", paymentBatchNum=" + paymentBatchNum +
			", totalApplicationAmount=" + totalApplicationAmount +
			", paymentMonth=" + paymentMonth +
			", paymentState=" + paymentState +
			", createPaymentUser=" + createPaymentUser +
			", createPaymentDate=" + createPaymentDate +
			", financePaymentDate=" + financePaymentDate +
			", accountType=" + accountType +
			", totalEmpCount=" + totalEmpCount +
			", totalAccount=" + totalAccount +
			", totalCom=" + totalCom +
			", applyRemark=" + applyRemark +
			", rejectionRemark=" + rejectionRemark +
			", rejectionHis=" + rejectionHis +
			", requestUser=" + requestUser +
			", requestDate=" + requestDate +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
