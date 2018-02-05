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
 * 申请支付批次记录表
 * </p>
 *
 * @author chenpb
 * @since 2018-01-30
 */
@TableName("hm_payment_apply_batch")
public class PaymentApplyBatchPO extends Model<PaymentApplyBatchPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 申请支付批次序号（等同于传给结算中心接口的business_pkid）
     */
	@TableId(value="apply_batch_id", type= IdType.AUTO)
	private Integer applyBatchId;
    /**
     * 部门名称（健康医疗部）
     */
	private String departmentName;
    /**
     * 是否财务部（0-否，1-是）
        默认0
     */
	private Integer isFinanceDept;
    /**
     * 业务类型：默认11
     */
	private Integer businessType;
    /**
     * 付款方式
     */
	private Integer payWay;
    /**
     * 申请支付总金额
     */
	private BigDecimal payAmount;
    /**
     * 收款方名称（固定值：个人）
     */
	private String receiver;
    /**
     * 申请人（固定值：系统）
     */
	private String applyer;
    /**
     * 申请日期（2018-01-05）
     */
	private Date applyDate;
    /**
     * 付款用途（2018-01-05补充医疗报销）
     */
	private String payPurpose;
    /**
     * 付款原因（2018-01-05补充医疗报销）
     */
	private String payReason;
    /**
     * 批次状态
     */
	private Integer batchStatus;
    /**
     * 操作人（from结算中心API）
     */
	private String operator;
    /**
     * 操作时间（from结算中心API）
     */
	private Date operatorTime;
    /**
     * 备注（from结算中心API）
     */
	private String remark;
    /**
     * 是否可用
     */
	private Integer isActive;
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


	public Integer getApplyBatchId() {
		return applyBatchId;
	}

	public void setApplyBatchId(Integer applyBatchId) {
		this.applyBatchId = applyBatchId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getIsFinanceDept() {
		return isFinanceDept;
	}

	public void setIsFinanceDept(Integer isFinanceDept) {
		this.isFinanceDept = isFinanceDept;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getApplyer() {
		return applyer;
	}

	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getPayPurpose() {
		return payPurpose;
	}

	public void setPayPurpose(String payPurpose) {
		this.payPurpose = payPurpose;
	}

	public String getPayReason() {
		return payReason;
	}

	public void setPayReason(String payReason) {
		this.payReason = payReason;
	}

	public Integer getBatchStatus() {
		return batchStatus;
	}

	public void setBatchStatus(Integer batchStatus) {
		this.batchStatus = batchStatus;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getActive() {
		return isActive;
	}

	public void setActive(Integer isActive) {
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
		return this.applyBatchId;
	}

    public PaymentApplyBatchPO(String departmentName, Integer isFinanceDept, Integer businessType, Integer payWay, BigDecimal payAmount, String receiver, String applyer, Date applyDate, String payPurpose, String payReason, Integer batchStatus, Integer isActive, Date createdTime, Date modifiedTime, String createdBy, String modifiedBy) {
        this.departmentName = departmentName;
        this.isFinanceDept = isFinanceDept;
        this.businessType = businessType;
        this.payWay = payWay;
        this.payAmount = payAmount;
        this.receiver = receiver;
        this.applyer = applyer;
        this.applyDate = applyDate;
        this.payPurpose = payPurpose;
        this.payReason = payReason;
        this.batchStatus = batchStatus;
        this.isActive = isActive;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    @Override
	public String toString() {
		return "PaymentApplyBatchPO{" +
			", applyBatchId=" + applyBatchId +
			", departmentName=" + departmentName +
			", isFinanceDept=" + isFinanceDept +
			", businessType=" + businessType +
			", payWay=" + payWay +
			", payAmount=" + payAmount +
			", receiver=" + receiver +
			", applyer=" + applyer +
			", applyDate=" + applyDate +
			", payPurpose=" + payPurpose +
			", payReason=" + payReason +
			", batchStatus=" + batchStatus +
			", operator=" + operator +
			", operatorTime=" + operatorTime +
			", remark=" + remark +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
