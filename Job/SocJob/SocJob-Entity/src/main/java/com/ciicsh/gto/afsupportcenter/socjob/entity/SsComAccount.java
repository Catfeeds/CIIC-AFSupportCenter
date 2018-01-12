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
 * 企业社保账户信息表
企业社保账户分类 : 大库（中智大库、外包库）、独立户
 * </p>
 */
@TableName("ss_com_account")
public class SsComAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业社保账户Id
     */
	@TableId(value="com_account_id", type= IdType.AUTO)
	private Long comAccountId;
    /**
     * 多租户Id
     */
	@TableField("customer_id")
	private String customerId;
    /**
     * 供应商Id, 取自gtosupplierdb.SUP_Supplier
     */
	@TableField("supplier_id")
	private String supplierId;
    /**
     * 账户类型：1:中智大库 2中智外包 3独立户
     */
	@TableField("ss_account_type")
	private Integer ssAccountType;
    /**
     * 参保户登记码（账号）
     */
	@TableField("ss_account")
	private String ssAccount;
    /**
     * 银行账号(牡丹卡号)
     */
	@TableField("bank_account")
	private String bankAccount;
    /**
     * 养老金账户公司名称
     */
	@TableField("com_account_name")
	private String comAccountName;
    /**
     * 结算区县(社保局所在上海地区)
     */
	@TableField("settlement_area")
	private String settlementArea;
    /**
     * 付款银行(一般情况是工商银行)
     */
	@TableField("payment_bank")
	private String paymentBank;
    /**
     * 付款方式：.
1-我司代付款
2-客户自付
3-我司垫付
     */
	@TableField("payment_way")
	private Integer paymentWay;
    /**
     * 社保帐单寄到哪里: 1 我司，2 客户公司
     */
	@TableField("bill_receiver")
	private Integer billReceiver;
    /**
     * 客户交付社保费用给中智的截止日期
     */
	@TableField("expire_date")
	private Integer expireDate;
    /**
     * 养老金独立开户用户名（使用U盾登陆的用户名）
     */
	@TableField("ss_username")
	private String ssUsername;
    /**
     * 养老金独立开户密码（使用U盾登陆的密码）
     */
	@TableField("ss_pwd")
	private String ssPwd;
    /**
     * 初期余额
     */
	@TableField("initial_balance")
	private BigDecimal initialBalance;
    /**
     * 初期欠费
     */
	@TableField("initial_debt")
	private BigDecimal initialDebt;
    /**
     * 来源地:1-新开；2-AF转入；3-其他供应商转入
     */
	@TableField("origin_place")
	private Integer originPlace;
    /**
     * 来源地备注
     */
	@TableField("origin_place_remark")
	private String originPlaceRemark;
    /**
     * 查询账号
     */
	@TableField("query_account")
	private String queryAccount;
    /**
     * 交予方式:1-交客服  2-传真  3-邮寄
     */
	@TableField("deliver_way")
	private String deliverWay;
    /**
     * 交予方式备注
     */
	@TableField("deliver_way_remark")
	private String deliverWayRemark;
    /**
     * 给凭证时间
     */
	@TableField("provide_certificate_time")
	private LocalDate provideCertificateTime;
    /**
     * 变更时间
     */
	@TableField("change_time")
	private LocalDateTime changeTime;
    /**
     * 收到日期
     */
	@TableField("receive_date")
	private LocalDate receiveDate;
    /**
     * 转入日期
     */
	@TableField("into_date")
	private LocalDate intoDate;
    /**
     * 终止日期
     */
	@TableField("end_date")
	private LocalDate endDate;
    /**
     * 发出材料:正式通知书、预生成通知书、收据、银行对账单、汇总表、实时表
     */
	@TableField("dispatch_material")
	private String dispatchMaterial;
    /**
     * 备注
     */
	private String remark;
    /**
     * 账户状态:0初始 1有效 2 终止
     */
	private Integer state;
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


	public Long getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(Long comAccountId) {
		this.comAccountId = comAccountId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getSsAccountType() {
		return ssAccountType;
	}

	public void setSsAccountType(Integer ssAccountType) {
		this.ssAccountType = ssAccountType;
	}

	public String getSsAccount() {
		return ssAccount;
	}

	public void setSsAccount(String ssAccount) {
		this.ssAccount = ssAccount;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getComAccountName() {
		return comAccountName;
	}

	public void setComAccountName(String comAccountName) {
		this.comAccountName = comAccountName;
	}

	public String getSettlementArea() {
		return settlementArea;
	}

	public void setSettlementArea(String settlementArea) {
		this.settlementArea = settlementArea;
	}

	public String getPaymentBank() {
		return paymentBank;
	}

	public void setPaymentBank(String paymentBank) {
		this.paymentBank = paymentBank;
	}

	public Integer getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(Integer paymentWay) {
		this.paymentWay = paymentWay;
	}

	public Integer getBillReceiver() {
		return billReceiver;
	}

	public void setBillReceiver(Integer billReceiver) {
		this.billReceiver = billReceiver;
	}

	public Integer getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Integer expireDate) {
		this.expireDate = expireDate;
	}

	public String getSsUsername() {
		return ssUsername;
	}

	public void setSsUsername(String ssUsername) {
		this.ssUsername = ssUsername;
	}

	public String getSsPwd() {
		return ssPwd;
	}

	public void setSsPwd(String ssPwd) {
		this.ssPwd = ssPwd;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	public BigDecimal getInitialDebt() {
		return initialDebt;
	}

	public void setInitialDebt(BigDecimal initialDebt) {
		this.initialDebt = initialDebt;
	}

	public Integer getOriginPlace() {
		return originPlace;
	}

	public void setOriginPlace(Integer originPlace) {
		this.originPlace = originPlace;
	}

	public String getOriginPlaceRemark() {
		return originPlaceRemark;
	}

	public void setOriginPlaceRemark(String originPlaceRemark) {
		this.originPlaceRemark = originPlaceRemark;
	}

	public String getQueryAccount() {
		return queryAccount;
	}

	public void setQueryAccount(String queryAccount) {
		this.queryAccount = queryAccount;
	}

	public String getDeliverWay() {
		return deliverWay;
	}

	public void setDeliverWay(String deliverWay) {
		this.deliverWay = deliverWay;
	}

	public String getDeliverWayRemark() {
		return deliverWayRemark;
	}

	public void setDeliverWayRemark(String deliverWayRemark) {
		this.deliverWayRemark = deliverWayRemark;
	}

	public LocalDate getProvideCertificateTime() {
		return provideCertificateTime;
	}

	public void setProvideCertificateTime(LocalDate provideCertificateTime) {
		this.provideCertificateTime = provideCertificateTime;
	}

	public LocalDateTime getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(LocalDateTime changeTime) {
		this.changeTime = changeTime;
	}

	public LocalDate getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(LocalDate receiveDate) {
		this.receiveDate = receiveDate;
	}

	public LocalDate getIntoDate() {
		return intoDate;
	}

	public void setIntoDate(LocalDate intoDate) {
		this.intoDate = intoDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getDispatchMaterial() {
		return dispatchMaterial;
	}

	public void setDispatchMaterial(String dispatchMaterial) {
		this.dispatchMaterial = dispatchMaterial;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
		return "SsComAccount{" +
			", comAccountId=" + comAccountId +
			", customerId=" + customerId +
			", supplierId=" + supplierId +
			", ssAccountType=" + ssAccountType +
			", ssAccount=" + ssAccount +
			", bankAccount=" + bankAccount +
			", comAccountName=" + comAccountName +
			", settlementArea=" + settlementArea +
			", paymentBank=" + paymentBank +
			", paymentWay=" + paymentWay +
			", billReceiver=" + billReceiver +
			", expireDate=" + expireDate +
			", ssUsername=" + ssUsername +
			", ssPwd=" + ssPwd +
			", initialBalance=" + initialBalance +
			", initialDebt=" + initialDebt +
			", originPlace=" + originPlace +
			", originPlaceRemark=" + originPlaceRemark +
			", queryAccount=" + queryAccount +
			", deliverWay=" + deliverWay +
			", deliverWayRemark=" + deliverWayRemark +
			", provideCertificateTime=" + provideCertificateTime +
			", changeTime=" + changeTime +
			", receiveDate=" + receiveDate +
			", intoDate=" + intoDate +
			", endDate=" + endDate +
			", dispatchMaterial=" + dispatchMaterial +
			", remark=" + remark +
			", state=" + state +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
