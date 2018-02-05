package com.ciicsh.gto.afsupportcenter.healthmedical.entity.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableLogic;

/**
 * <p>
 * AF雇员付款申请记录表
 * </p>
 *
 * @author chenpb
 * @since 2018-01-29
 */
@TableName("cmy_af_employee_payment_apply")
public class EmployeePaymentApplyPO extends Model<EmployeePaymentApplyPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员付款申请记录编号
     */
	@TableId(value="payment_apply_id", type= IdType.AUTO)
	private Integer paymentApplyId;
    /**
     * 客户编号
     */
    private String companyId;
    /**
     * 客户名称
     */
    private String companyName;
    /**
     * 雇员编号
     */
    private String employeeId;
    /**
     * 雇员姓名
     */
    private String employeeName;
    /**
     * 申请类型
1-支付个人
     */
	private Integer applyTypeId;
    /**
     * 付款类型：
1-13月工资
2-14月管理费
3-补充医疗
4-采暖费
5-长年服务金
等
     */
	private Integer paymentTypeId;
    /**
     * 税前总金额
     */
	private BigDecimal preTaxTotalAmount;
    /**
     * 税额
     */
	private BigDecimal taxAmount;
    /**
     * 实际支付总金额
     */
	private BigDecimal payTotalAmount;
    /**
     * 是否无发票报销
     */
	private Boolean invoiceReimbursement;
    /**
     * 发票张数
     */
	private Integer invoiceNumber;
    /**
     * 备注
     */
	private String remark;
    /**
     * 状态：
1-未审核
2-已批退
3-已审核未支付
4-已支付
5-财务退回
6-银行退票
7-已完成
     */
	private Integer status;
    /**
     * 是否激活
     */
	private Integer isActive;
    /**
     * 创建时间
     */
	private Date createdTime;
    /**
     * 修改时间
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


	public Integer getPaymentApplyId() {
		return paymentApplyId;
	}

	public void setPaymentApplyId(Integer paymentApplyId) {
		this.paymentApplyId = paymentApplyId;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getApplyTypeId() {
		return applyTypeId;
	}

	public void setApplyTypeId(Integer applyTypeId) {
		this.applyTypeId = applyTypeId;
	}

	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public BigDecimal getPreTaxTotalAmount() {
		return preTaxTotalAmount;
	}

	public void setPreTaxTotalAmount(BigDecimal preTaxTotalAmount) {
		this.preTaxTotalAmount = preTaxTotalAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getPayTotalAmount() {
		return payTotalAmount;
	}

	public void setPayTotalAmount(BigDecimal payTotalAmount) {
		this.payTotalAmount = payTotalAmount;
	}

	public Boolean getInvoiceReimbursement() {
		return invoiceReimbursement;
	}

	public void setInvoiceReimbursement(Boolean invoiceReimbursement) {
		this.invoiceReimbursement = invoiceReimbursement;
	}

	public Integer getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Integer invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		return this.paymentApplyId;
	}

	@Override
	public String toString() {
		return "EmployeePaymentApplyPO{" +
			", paymentApplyId=" + paymentApplyId +
			", employeeId=" + employeeId +
			", applyTypeId=" + applyTypeId +
			", paymentTypeId=" + paymentTypeId +
			", preTaxTotalAmount=" + preTaxTotalAmount +
			", taxAmount=" + taxAmount +
			", payTotalAmount=" + payTotalAmount +
			", invoiceReimbursement=" + invoiceReimbursement +
			", invoiceNumber=" + invoiceNumber +
			", remark=" + remark +
			", status=" + status +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
