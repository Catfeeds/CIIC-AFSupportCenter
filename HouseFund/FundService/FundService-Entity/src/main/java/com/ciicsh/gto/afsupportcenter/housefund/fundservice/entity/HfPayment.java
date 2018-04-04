package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 公积金汇缴支付批次表
 * </p>
 *
 * @author 沈健
 * @since 2018-03-14
 */
@TableName("hf_payment")
public class HfPayment extends Model<HfPayment> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
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
	private Date createPaymentDate;
    /**
     * 财务支付日期
     */
	@TableField("finance_payment_date")
	private Date financePaymentDate;
    /**
     * 1 中智大库  2 中智外包 3 独立户
     */
	@TableField("hf_account_type")
	private Integer hfAccountType;
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
            [{
            总雇员数：
            账户总数：
            客户总数：
            批退备注：
            批退人：
            批退时间：
            },{},{}]
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
    private Date requestDate;


    @TableField("receiver")
    private String receiver;
    //付款申请编号
    @TableField("pay_apply_code")
    private String payApplyCode;
    /**
     * 是否可用
     */
	@TableField("is_active")
    @TableLogic
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private Date createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
	private Date modifiedTime;
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

    public String getPayApplyCode() {
        return payApplyCode;
    }

    public void setPayApplyCode(String payApplyCode) {
        this.payApplyCode = payApplyCode;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

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

	public Date getCreatePaymentDate() {
		return createPaymentDate;
	}

	public void setCreatePaymentDate(Date createPaymentDate) {
		this.createPaymentDate = createPaymentDate;
	}

	public Date getFinancePaymentDate() {
		return financePaymentDate;
	}

	public void setFinancePaymentDate(Date financePaymentDate) {
		this.financePaymentDate = financePaymentDate;
	}

	public Integer getHfAccountType() {
		return hfAccountType;
	}

	public void setHfAccountType(Integer hfAccountType) {
		this.hfAccountType = hfAccountType;
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

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
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
		return this.paymentId;
	}

	@Override
	public String toString() {
		return "HfPayment{" +
			", paymentId=" + paymentId +
			", paymentBatchNum=" + paymentBatchNum +
			", totalApplicationAmonut=" + totalApplicationAmonut +
			", totalEmpCount=" + totalEmpCount +
			", paymentMonth=" + paymentMonth +
			", paymentState=" + paymentState +
			", createPaymentUser=" + createPaymentUser +
			", createPaymentDate=" + createPaymentDate +
			", financePaymentDate=" + financePaymentDate +
			", hfAccountType=" + hfAccountType +
			", applyRemark=" + applyRemark +
			", rejectionRemark=" + rejectionRemark +
			", rejectionHis=" + rejectionHis +
			", requestUser=" + requestUser +
			", requestDate=" + requestDate +
            ", payApplyCode=" + payApplyCode +
            ", receiver=" + receiver +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
