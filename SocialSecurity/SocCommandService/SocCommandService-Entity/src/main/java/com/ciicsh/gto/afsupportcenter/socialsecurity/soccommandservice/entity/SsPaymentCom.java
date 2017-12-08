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
 * 本地社保应付金额交易记录主表,每月1号生成此表记录，用户也可以人工生成此表记录
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
@TableName("ss_payment_com")
public class SsPaymentCom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="payment_com_id", type= IdType.AUTO)
	private Long paymentComId;
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
     * 申请支付的金额合计,=TotalComPayAmount+TotalEmpPayAmount
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
     * 额外金
     */
	@TableField("extra_amount")
	private BigDecimal extraAmount;
    /**
     * 申请人的系统用户ID
     */
	@TableField("applier_id")
	private String applierId;
    /**
     * 申请日期
     */
	@TableField("application_date")
	private LocalDate applicationDate;
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
	@TableField("emp_head_count")
	private Integer empHeadCount;
    /**
     * 支付状态: 0-已创建 1 申请中 2完成支付 3 批退
     */
	@TableField("payment_status")
	private Integer paymentStatus;
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


	public Long getPaymentComId() {
		return paymentComId;
	}

	public void setPaymentComId(Long paymentComId) {
		this.paymentComId = paymentComId;
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

	public BigDecimal getExtraAmount() {
		return extraAmount;
	}

	public void setExtraAmount(BigDecimal extraAmount) {
		this.extraAmount = extraAmount;
	}

	public String getApplierId() {
		return applierId;
	}

	public void setApplierId(String applierId) {
		this.applierId = applierId;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
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

	public Integer getEmpHeadCount() {
		return empHeadCount;
	}

	public void setEmpHeadCount(Integer empHeadCount) {
		this.empHeadCount = empHeadCount;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
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
			", comAccountId=" + comAccountId +
			", companyId=" + companyId +
			", paymentMonth=" + paymentMonth +
			", totalPayAmount=" + totalPayAmount +
			", totalComPayAmount=" + totalComPayAmount +
			", totalEmpPayAmount=" + totalEmpPayAmount +
			", extraAmount=" + extraAmount +
			", applierId=" + applierId +
			", applicationDate=" + applicationDate +
			", remark=" + remark +
			", actualPaymentDate=" + actualPaymentDate +
			", empHeadCount=" + empHeadCount +
			", paymentStatus=" + paymentStatus +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
