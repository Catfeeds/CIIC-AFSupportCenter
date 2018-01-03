package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po;

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
 * 补充医疗受理单相关发票表
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-29
 */
@TableName("hm_supply_medical_invoice")
public class SupplyMedicalInvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发票编号
     */
	@TableId(value="invoice_id", type= IdType.AUTO)
	private Integer invoiceId;
    /**
     * 受理单编号
     */
	@TableField("acceptance_id")
	private Integer acceptanceId;
    /**
     * 就诊机构
     */
	@TableField("medical_institution")
	private String medicalInstitution;
    /**
     * 疾病名称
     */
	@TableField("disease_name")
	private String diseaseName;
    /**
     * 就诊日期
     */
	@TableField("clinic_date")
	private LocalDate clinicDate;
    /**
     * 自付金额
     */
	@TableField("self_pay_amount")
	private BigDecimal selfPayAmount;
    /**
     * 统筹金额
     */
	@TableField("whole_plan_amount")
	private BigDecimal wholePlanAmount;
    /**
     * 附加金额
     */
	@TableField("attach_amount")
	private BigDecimal attachAmount;
    /**
     * 自费金额
     */
	@TableField("own_expense_amount")
	private BigDecimal ownExpenseAmount;
    /**
     * 账户金额
     */
	@TableField("account_amount")
	private BigDecimal accountAmount;
    /**
     * 申请金额
     */
	@TableField("application_amount")
	private BigDecimal applicationAmount;
    /**
     * 赔付金额
     */
	@TableField("claim_amount")
	private BigDecimal claimAmount;
    /**
     * 核准金额
     */
	@TableField("approved_amount")
	private BigDecimal approvedAmount;
    /**
     * 分类自付金额
     */
	@TableField("classified_self_payment_amount")
	private BigDecimal classifiedSelfPaymentAmount;
    /**
     * 公司理赔金额
     */
	@TableField("company_money")
	private BigDecimal companyMoney;
    /**
     * 保险理赔金额
     */
	@TableField("insurance_company_money")
	private BigDecimal insuranceCompanyMoney;
    /**
     * 结案状态
     */
	@TableField("closed_status")
	private String closedStatus;
    /**
     * 结案备注
     */
	@TableField("closed_remark")
	private String closedRemark;
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


	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Integer getAcceptanceId() {
		return acceptanceId;
	}

	public void setAcceptanceId(Integer acceptanceId) {
		this.acceptanceId = acceptanceId;
	}

	public String getMedicalInstitution() {
		return medicalInstitution;
	}

	public void setMedicalInstitution(String medicalInstitution) {
		this.medicalInstitution = medicalInstitution;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public LocalDate getClinicDate() {
		return clinicDate;
	}

	public void setClinicDate(LocalDate clinicDate) {
		this.clinicDate = clinicDate;
	}

	public BigDecimal getSelfPayAmount() {
		return selfPayAmount;
	}

	public void setSelfPayAmount(BigDecimal selfPayAmount) {
		this.selfPayAmount = selfPayAmount;
	}

	public BigDecimal getWholePlanAmount() {
		return wholePlanAmount;
	}

	public void setWholePlanAmount(BigDecimal wholePlanAmount) {
		this.wholePlanAmount = wholePlanAmount;
	}

	public BigDecimal getAttachAmount() {
		return attachAmount;
	}

	public void setAttachAmount(BigDecimal attachAmount) {
		this.attachAmount = attachAmount;
	}

	public BigDecimal getOwnExpenseAmount() {
		return ownExpenseAmount;
	}

	public void setOwnExpenseAmount(BigDecimal ownExpenseAmount) {
		this.ownExpenseAmount = ownExpenseAmount;
	}

	public BigDecimal getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount = accountAmount;
	}

	public BigDecimal getApplicationAmount() {
		return applicationAmount;
	}

	public void setApplicationAmount(BigDecimal applicationAmount) {
		this.applicationAmount = applicationAmount;
	}

	public BigDecimal getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}

	public BigDecimal getApprovedAmount() {
		return approvedAmount;
	}

	public void setApprovedAmount(BigDecimal approvedAmount) {
		this.approvedAmount = approvedAmount;
	}

	public BigDecimal getClassifiedSelfPaymentAmount() {
		return classifiedSelfPaymentAmount;
	}

	public void setClassifiedSelfPaymentAmount(BigDecimal classifiedSelfPaymentAmount) {
		this.classifiedSelfPaymentAmount = classifiedSelfPaymentAmount;
	}

	public BigDecimal getCompanyMoney() {
		return companyMoney;
	}

	public void setCompanyMoney(BigDecimal companyMoney) {
		this.companyMoney = companyMoney;
	}

	public BigDecimal getInsuranceCompanyMoney() {
		return insuranceCompanyMoney;
	}

	public void setInsuranceCompanyMoney(BigDecimal insuranceCompanyMoney) {
		this.insuranceCompanyMoney = insuranceCompanyMoney;
	}

	public String getClosedStatus() {
		return closedStatus;
	}

	public void setClosedStatus(String closedStatus) {
		this.closedStatus = closedStatus;
	}

	public String getClosedRemark() {
		return closedRemark;
	}

	public void setClosedRemark(String closedRemark) {
		this.closedRemark = closedRemark;
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
		return "SupplyMedicalInvoice{" +
			", invoiceId=" + invoiceId +
			", acceptanceId=" + acceptanceId +
			", medicalInstitution=" + medicalInstitution +
			", diseaseName=" + diseaseName +
			", clinicDate=" + clinicDate +
			", selfPayAmount=" + selfPayAmount +
			", wholePlanAmount=" + wholePlanAmount +
			", attachAmount=" + attachAmount +
			", ownExpenseAmount=" + ownExpenseAmount +
			", accountAmount=" + accountAmount +
			", applicationAmount=" + applicationAmount +
			", claimAmount=" + claimAmount +
			", approvedAmount=" + approvedAmount +
			", classifiedSelfPaymentAmount=" + classifiedSelfPaymentAmount +
			", companyMoney=" + companyMoney +
			", insuranceCompanyMoney=" + insuranceCompanyMoney +
			", closedStatus=" + closedStatus +
			", closedRemark=" + closedRemark +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
