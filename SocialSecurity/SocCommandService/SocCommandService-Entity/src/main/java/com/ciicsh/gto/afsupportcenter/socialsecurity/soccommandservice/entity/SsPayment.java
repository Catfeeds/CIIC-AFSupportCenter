package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 社保支付批次
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@TableName("ss_payment")
public class SsPayment implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="payment_id", type= IdType.AUTO)
	private Integer paymentId;
    /**
     * 出账批号
     */
	@TableField("payment_batch_num")
	private String paymentBatchNum;
    /**
     * 申请总金额
     */
	@TableField("total_application_amonut")
	private BigDecimal totalApplicationAmonut;
    /**
     * 总雇员数
     */
	@TableField("total_emp_count")
	private Integer totalEmpCount;
    /**
     * 支付年月YYYYMM
     */
	@TableField("payment_month")
	private Integer paymentMonth;
    /**
     * 当前支付状态
     */
	private Integer state;
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
     * 大库、外包、独立户
     */
	@TableField("account_type")
	private Integer accountType;
    /**
     * 账户总人数
     */
	@TableField("total_emp")
	private Integer totalEmp;
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


	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentBatchNum() {
		return paymentBatchNum;
	}

	public void setPaymentBatchNum(String paymentBatchNum) {
		this.paymentBatchNum = paymentBatchNum;
	}

	public BigDecimal getTotalApplicationAmonut() {
		return totalApplicationAmonut;
	}

	public void setTotalApplicationAmonut(BigDecimal totalApplicationAmonut) {
		this.totalApplicationAmonut = totalApplicationAmonut;
	}

	public Integer getTotalEmpCount() {
		return totalEmpCount;
	}

	public void setTotalEmpCount(Integer totalEmpCount) {
		this.totalEmpCount = totalEmpCount;
	}

	public Integer getPaymentMonth() {
		return paymentMonth;
	}

	public void setPaymentMonth(Integer paymentMonth) {
		this.paymentMonth = paymentMonth;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public Integer getTotalEmp() {
		return totalEmp;
	}

	public void setTotalEmp(Integer totalEmp) {
		this.totalEmp = totalEmp;
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
		return "SsPayment{" +
			", paymentId=" + paymentId +
			", paymentBatchNum=" + paymentBatchNum +
			", totalApplicationAmonut=" + totalApplicationAmonut +
			", totalEmpCount=" + totalEmpCount +
			", paymentMonth=" + paymentMonth +
			", state=" + state +
			", createPaymentUser=" + createPaymentUser +
			", createPaymentDate=" + createPaymentDate +
			", financePaymentDate=" + financePaymentDate +
			", accountType=" + accountType +
			", totalEmp=" + totalEmp +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
