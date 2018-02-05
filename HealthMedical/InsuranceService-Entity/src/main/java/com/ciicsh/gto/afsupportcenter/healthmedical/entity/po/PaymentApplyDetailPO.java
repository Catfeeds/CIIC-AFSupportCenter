package com.ciicsh.gto.afsupportcenter.healthmedical.entity.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 支付申请详情表
 * </p>
 *
 * @author chenpb
 * @since 2018-01-30
 */
@TableName("hm_payment_apply_detail")
public class PaymentApplyDetailPO extends Model<PaymentApplyDetailPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 付款申请明细编号
     */
	@TableId(value="apply_detail_id", type= IdType.AUTO)
	private Integer applyDetailId;
    /**
     * 申请支付批次序号
     */
	private Integer applyBatchId;
    /**
     * 受理单类型（0-补充医疗理赔，1-未投保医疗理赔，2-雇员付款）
     */
	private Integer businessId;
    /**
     * 受理单编号
     */
	private Integer acceptanceId;
    /**
     * 公司编号
     */
	private Integer companyId;
    /**
     * 公司名称
     */
	private String companyName;
    /**
     * 雇员编号
     */
	private Integer employeeId;
    /**
     * 雇员姓名
     */
	private String employeeName;
    /**
     * 雇员银行账号
     */
	private String bankAccount;
    /**
     * 雇员银行名称
     */
	private String bankName;
    /**
     * 雇员银行编号
     */
	private String bankCode;
    /**
     * 支付金额
     */
	private BigDecimal payAmount;
    /**
     * 是否可用
     */
    @TableLogic
	private Boolean isActive;
    /**
     * 创建时间
     */
	private Date createdTime;
    /**
     * 最后更新时间
     */
	private Date modifiedTime;
    /**
     * 创建者登录名
     */
	private String createdBy;
    /**
     * 修改者登录名
     */
	private String modifiedBy;


	public Integer getApplyDetailId() {
		return applyDetailId;
	}

	public void setApplyDetailId(Integer applyDetailId) {
		this.applyDetailId = applyDetailId;
	}

	public Integer getApplyBatchId() {
		return applyBatchId;
	}

	public void setApplyBatchId(Integer applyBatchId) {
		this.applyBatchId = applyBatchId;
	}

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getAcceptanceId() {
		return acceptanceId;
	}

	public void setAcceptanceId(Integer acceptanceId) {
		this.acceptanceId = acceptanceId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
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
	protected Serializable pkVal() {
		return this.applyDetailId;
	}

	@Override
	public String toString() {
		return "PaymentApplyDetailPO{" +
			", applyDetailId=" + applyDetailId +
			", applyBatchId=" + applyBatchId +
			", businessId=" + businessId +
			", acceptanceId=" + acceptanceId +
			", companyId=" + companyId +
			", companyName=" + companyName +
			", employeeId=" + employeeId +
			", employeeName=" + employeeName +
			", bankAccount=" + bankAccount +
			", bankName=" + bankName +
			", bankCode=" + bankCode +
			", payAmount=" + payAmount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
