package com.ciicsh.gto.afsupportcenter.fundjob.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 公积金汇缴支付公司账户名单
 * </p>
 */
@TableName("hf_payment_account")
public class HfPaymentAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="payment_account_id", type= IdType.AUTO)
	private Long paymentAccountId;
    /**
     * 外键
     */
	@TableField("payment_id")
	private Long paymentId;
    /**
     * 外键：企业公积金账户
     */
	@TableField("com_account_id")
	private String comAccountId;
    /**
     * 缴费银行，来自字典库
     */
	@TableField("payment_bank")
	private String paymentBank;
    /**
     * 当前支付状态
            1,未到帐  2,无需支付  3 ,可付 4,申请中  5,内部审批批退 6,已申请到财务部  7,财务部批退  8,财务部支付成功
            
     */
	@TableField("payment_status")
	private Integer paymentStatus;
    /**
     * 申请支付的公司部分的总金额
     */
	@TableField("total_com_pay_amount")
	private BigDecimal totalComPayAmount;
    /**
     * 申请支付的雇员部分的总金额
     */
	@TableField("total_emp_pay_amount")
	private BigDecimal totalEmpPayAmount;
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


	public Long getPaymentAccountId() {
		return paymentAccountId;
	}

	public void setPaymentAccountId(Long paymentAccountId) {
		this.paymentAccountId = paymentAccountId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(String comAccountId) {
		this.comAccountId = comAccountId;
	}

	public String getPaymentBank() {
		return paymentBank;
	}

	public void setPaymentBank(String paymentBank) {
		this.paymentBank = paymentBank;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public BigDecimal getTotalComPayAmount() {
		return totalComPayAmount;
	}

	public void setTotalComPayAmount(BigDecimal totalComPayAmount) {
		this.totalComPayAmount = totalComPayAmount;
	}

	public BigDecimal getTotalEmpPayAmount() {
		return totalEmpPayAmount;
	}

	public void setTotalEmpPayAmount(BigDecimal totalEmpPayAmount) {
		this.totalEmpPayAmount = totalEmpPayAmount;
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
		return "HfPaymentAccount{" +
			", paymentAccountId=" + paymentAccountId +
			", paymentId=" + paymentId +
			", comAccountId=" + comAccountId +
			", paymentBank=" + paymentBank +
			", paymentStatus=" + paymentStatus +
			", totalComPayAmount=" + totalComPayAmount +
			", totalEmpPayAmount=" + totalEmpPayAmount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
