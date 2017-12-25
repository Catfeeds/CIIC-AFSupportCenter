package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.Serializable;

/**
 * <p>
 * 补充医疗理赔发票表
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */

public class AcceptanceInvoicePO implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer invoiceId;
	private Integer acceptanceId;
    /**
     * 就诊机构
     */
	private String medicalInstitution;
    /**
     * 疾病名称
     */
	private String diseaseName;
    /**
     * 就诊日期
     */
	private LocalDate clinicDate;
    /**
     * 自付金额
     */
	private BigDecimal selfPayAmount;
    /**
     * 统筹金额
     */
	private BigDecimal wholePlanAmount;
    /**
     * 附加金额
     */
	private BigDecimal attachAmount;
    /**
     * 自费金额
     */
	private BigDecimal ownExpenseAmount;
    /**
     * 账户金额
     */
	private BigDecimal accountAmount;
    /**
     * 申请金额
     */
	private BigDecimal applicationAmount;
    /**
     * 赔付金额
     */
	private BigDecimal claimAmount;
    /**
     * 核准金额
     */

	private BigDecimal approvedAmount;
    /**
     * 分类自付金额
     */

	private BigDecimal classifiedSelfPaymentAmount;
    /**
     * 公司理赔金额
     */

	private BigDecimal companyMoney;
    /**
     * 保险理赔金额
     */

	private BigDecimal insuranceCompanyMoney;
    /**
     * 是否可用
     */

	private Boolean isActive;
    /**
     * 创建时间
     */

	private LocalTime createdTime;
    /**
     * 最后更新时间
     */

	private LocalTime modifiedTime;
    /**
     * 创建者登录名
     */

	private String createdBy;
    /**
     * 修改者登录名
     */

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
		return "AcceptanceInvoice{" +
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
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
