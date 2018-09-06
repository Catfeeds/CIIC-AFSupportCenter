package com.ciicsh.gto.afsupportcenter.socjob.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 本地社保应付金额交易记录主表,每月1号生成此表记录，用户也可以人工生成此表记录
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-27
 */
@TableName("ss_payment_com")
public class SsPaymentCom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="payment_com_id", type= IdType.AUTO)
	private Long paymentComId;
	@TableField("payment_id")
	private Long paymentId;
    /**
     * 大库、独立库账户Id
     */
	@TableField("com_account_id")
	private Long comAccountId;
    /**
     * 客户Id,能关联到客户和社保账户,
            比如欧莱雅10家分公司分开支付
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 支付年月,格式yyyyMM
     */
	@TableField("payment_month")
	private String paymentMonth;
    /**
     * 应缴纳金额
     */
	@TableField("ought_amount")
	private BigDecimal oughtAmount;
    /**
     * 申请支付的金额合计,=TotalComPayAmount+TotalEmpPayAmount+extra_amount
     */
	@TableField("total_pay_amount")
	private BigDecimal totalPayAmount;
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
     * 退账抵扣费用
     */
	@TableField("refund_deducted")
	private BigDecimal refundDeducted;
    /**
     * 调整抵扣费用
     */
	@TableField("adjust_deducted")
	private BigDecimal adjustDeducted;
    /**
     * 额外金
     */
	@TableField("extra_amount")
	private BigDecimal extraAmount;
    /**
     * 加入批次人的系统用户
     */
	@TableField("join_payment_user")
	private String joinPaymentUser;
    /**
     * 加入批次日期
     */
	@TableField("join_payment_date")
	private LocalDate joinPaymentDate;
    /**
     * 抵扣费用是否纳入支付申请: 0-不纳入 1-纳入
     */
	@TableField("if_deducted_into_pay")
	private Integer ifDeductedIntoPay;
    /**
     * 申请备注
     */
	private String remark;
    /**
     * 财务实际支付日期
     */
	@TableField("actual_payment_date")
	private LocalDate actualPaymentDate;
    /**
     * 支付总人头数
     */
	@TableField("emp_count")
	private Integer empCount;
    /**
     * 支付状态: 1,未到帐  2,无需支付  3 ,可付 4,申请中  5,内部审批批退 6,已申请到财务部  7,财务部批退  8,财务部支付成功
     */
	@TableField("payment_state")
	private Integer paymentState;
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

    @TableField("com_payment_status")
    private String comPaymentStatus;

    public String getComPaymentStatus() {
        return comPaymentStatus;
    }

    public void setComPaymentStatus(String comPaymentStatus) {
        this.comPaymentStatus = comPaymentStatus;
    }

    public Long getPaymentComId() {
		return paymentComId;
	}

	public void setPaymentComId(Long paymentComId) {
		this.paymentComId = paymentComId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
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

	public String getPaymentMonth() {
		return paymentMonth;
	}

	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}

	public BigDecimal getOughtAmount() {
		return oughtAmount;
	}

	public void setOughtAmount(BigDecimal oughtAmount) {
		this.oughtAmount = oughtAmount;
	}

	public BigDecimal getTotalPayAmount() {
		return totalPayAmount;
	}

	public void setTotalPayAmount(BigDecimal totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
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

	public BigDecimal getRefundDeducted() {
		return refundDeducted;
	}

	public void setRefundDeducted(BigDecimal refundDeducted) {
		this.refundDeducted = refundDeducted;
	}

	public BigDecimal getAdjustDeducted() {
		return adjustDeducted;
	}

	public void setAdjustDeducted(BigDecimal adjustDeducted) {
		this.adjustDeducted = adjustDeducted;
	}

	public BigDecimal getExtraAmount() {
		return extraAmount;
	}

	public void setExtraAmount(BigDecimal extraAmount) {
		this.extraAmount = extraAmount;
	}

	public String getJoinPaymentUser() {
		return joinPaymentUser;
	}

	public void setJoinPaymentUser(String joinPaymentUser) {
		this.joinPaymentUser = joinPaymentUser;
	}

	public LocalDate getJoinPaymentDate() {
		return joinPaymentDate;
	}

	public void setJoinPaymentDate(LocalDate joinPaymentDate) {
		this.joinPaymentDate = joinPaymentDate;
	}

	public Integer getIfDeductedIntoPay() {
		return ifDeductedIntoPay;
	}

	public void setIfDeductedIntoPay(Integer ifDeductedIntoPay) {
		this.ifDeductedIntoPay = ifDeductedIntoPay;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LocalDate getActualPaymentDate() {
		return actualPaymentDate;
	}

	public void setActualPaymentDate(LocalDate actualPaymentDate) {
		this.actualPaymentDate = actualPaymentDate;
	}

	public Integer getEmpCount() {
		return empCount;
	}

	public void setEmpCount(Integer empCount) {
		this.empCount = empCount;
	}

	public Integer getPaymentState() {
		return paymentState;
	}

	public void setPaymentState(Integer paymentState) {
		this.paymentState = paymentState;
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
		return "SsPaymentCom{" +
			", paymentComId=" + paymentComId +
			", paymentId=" + paymentId +
			", comAccountId=" + comAccountId +
			", companyId=" + companyId +
			", paymentMonth=" + paymentMonth +
			", oughtAmount=" + oughtAmount +
			", totalPayAmount=" + totalPayAmount +
			", totalComPayAmount=" + totalComPayAmount +
			", totalEmpPayAmount=" + totalEmpPayAmount +
			", refundDeducted=" + refundDeducted +
			", adjustDeducted=" + adjustDeducted +
			", extraAmount=" + extraAmount +
			", joinPaymentUser=" + joinPaymentUser +
			", joinPaymentDate=" + joinPaymentDate +
			", ifDeductedIntoPay=" + ifDeductedIntoPay +
			", remark=" + remark +
			", actualPaymentDate=" + actualPaymentDate +
			", empCount=" + empCount +
			", paymentState=" + paymentState +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
