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
 * 公积金汇缴支付公司名单
 * </p>
 */
@TableName("hf_payment_com")
public class HfPaymentCom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="payment_com_id", type= IdType.AUTO)
	private Long paymentComId;
    /**
     * 公积金类型
     */
	@TableField("hf_type")
	private Integer hfType;
    /**
     * 公司ID
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 缴费银行，来自字典库
     */
	@TableField("payment_bank")
	private String paymentBank;
    /**
     * 汇缴金额
     */
	@TableField("remitted_amount")
	private BigDecimal remittedAmount;
    /**
     * 补缴金额
     */
	@TableField("bujiao_amount")
	private BigDecimal bujiaoAmount;
    /**
     * 汇缴人数
     */
	@TableField("remitted_count_emp")
	private Integer remittedCountEmp;
    /**
     * 到账金额
     */
	@TableField("daozhang_amount")
	private BigDecimal daozhangAmount;
    /**
     * 到账人数
     */
	@TableField("daozhang_count_emp")
	private Integer daozhangCountEmp;
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

	public Integer getHfType() {
		return hfType;
	}

	public void setHfType(Integer hfType) {
		this.hfType = hfType;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getPaymentBank() {
		return paymentBank;
	}

	public void setPaymentBank(String paymentBank) {
		this.paymentBank = paymentBank;
	}

	public BigDecimal getRemittedAmount() {
		return remittedAmount;
	}

	public void setRemittedAmount(BigDecimal remittedAmount) {
		this.remittedAmount = remittedAmount;
	}

	public BigDecimal getBujiaoAmount() {
		return bujiaoAmount;
	}

	public void setBujiaoAmount(BigDecimal bujiaoAmount) {
		this.bujiaoAmount = bujiaoAmount;
	}

	public Integer getRemittedCountEmp() {
		return remittedCountEmp;
	}

	public void setRemittedCountEmp(Integer remittedCountEmp) {
		this.remittedCountEmp = remittedCountEmp;
	}

	public BigDecimal getDaozhangAmount() {
		return daozhangAmount;
	}

	public void setDaozhangAmount(BigDecimal daozhangAmount) {
		this.daozhangAmount = daozhangAmount;
	}

	public Integer getDaozhangCountEmp() {
		return daozhangCountEmp;
	}

	public void setDaozhangCountEmp(Integer daozhangCountEmp) {
		this.daozhangCountEmp = daozhangCountEmp;
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
		return "HfPaymentCom{" +
			", paymentComId=" + paymentComId +
			", hfType=" + hfType +
			", companyId=" + companyId +
			", paymentBank=" + paymentBank +
			", remittedAmount=" + remittedAmount +
			", bujiaoAmount=" + bujiaoAmount +
			", remittedCountEmp=" + remittedCountEmp +
			", daozhangAmount=" + daozhangAmount +
			", daozhangCountEmp=" + daozhangCountEmp +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
