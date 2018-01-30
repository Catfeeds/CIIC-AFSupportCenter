package com.ciicsh.gto.afsupportcenter.healthmedical.entity.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 智灵通理赔汇总表
 * </p>
 *
 * @author xiweizhen
 */
@TableName("hm_acceptance_detailed")
public class AcceptanceDetailed extends Model<AcceptanceDetailed> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "detailed_id", type = IdType.AUTO)
    private Integer detailedId;
    /**
     * 序号
     */
    @TableField("zlt_sn")
    private Integer zltSn;
    /**
     * 案卷号
     */
    @TableField("dossier_number")
    private String dossierNumber;
    /**
     * 雇员编号
     */
    @TableField("employee_id")
    private String employeeId;
    /**
     * 雇员姓名
     */
    @TableField("employee_name")
    private String employeeName;
    /**
     * 身份证
     */
    @TableField("id_num")
    private String idNum;
    /**
     * 医保标志
     */
    @TableField("health_care_tag")
    private Boolean healthCareTag;
    /**
     * 发票张数
     */
    @TableField("invoice_number")
    private Integer invoiceNumber;
    /**
     * 索赔金额
     */
    @TableField("compensation_amount")
    private BigDecimal compensationAmount;
    /**
     * 就诊日期
     */
    @TableField("clinic_date")
    private Date clinicDate;
    /**
     * 受理日期
     */
    @TableField("acceptance_date")
    private Date acceptanceDate;
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
     * 状态
     */
    private String status;
    /**
     * 核赔金额
     */
    @TableField("hepei_amount")
    private BigDecimal hepeiAmount;
    /**
     * 结案状态
     */
    @TableField("closed_status")
    private String closedStatus;
    /**
     * 结案原因
     */
    @TableField("closed_reason")
    private String closedReason;
    /**
     * 结案日期
     */
    @TableField("closed_date")
    private Date closedDate;
    /**
     * 财务年度累计已赔金额
     */
    @TableField("finanical_amount")
    private BigDecimal finanicalAmount;
    /**
     * 保单年度累计已赔金额
     */
    @TableField("insurance_policy_amount")
    private BigDecimal insurancePolicyAmount;
    /**
     * 国寿理赔金额
     */
    @TableField("guoshou_amount")
    private BigDecimal guoshouAmount;
    /**
     * 国寿结案日期
     */
    @TableField("guoshou_closed_date")
    private Date guoshouClosedDate;
    /**
     * 结案备注
     */
    @TableField("closed_remark")
    private String closedRemark;
    /**
     * 发票类型
     */
    @TableField("invoice_type")
    private String invoiceType;
    /**
     * 账户金额
     */
    @TableField("account_amount")
    private BigDecimal accountAmount;
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
     * 不属医保报销金额
     */
    @TableField("un_health_care_amount")
    private BigDecimal unHealthCareAmount;
    /**
     * 申请金额
     */
    @TableField("application_amount")
    private BigDecimal applicationAmount;
    /**
     * 核准金额
     */
    @TableField("approved_amount")
    private BigDecimal approvedAmount;
    /**
     * 他方补偿金额
     */
    @TableField("other_amount")
    private BigDecimal otherAmount;
    /**
     * 他方补偿原因
     */
    @TableField("other_reason")
    private String otherReason;
    /**
     * 部分拒付金额
     */
    @TableField("other_reject_amount")
    private BigDecimal otherRejectAmount;
    /**
     * 部分拒付原因
     */
    @TableField("other_reject_reason")
    private String otherRejectReason;
    /**
     * 调整给付金额
     */
    @TableField("adjust_amount")
    private BigDecimal adjustAmount;
    /**
     * 调整给付原因
     */
    @TableField("adjust_reason")
    private String adjustReason;
    /**
     * 公司理赔金额
     */
    @TableField("company_money")
    private BigDecimal companyMoney;
    /**
     * 公司编号
     */
    @TableField("company_id")
    private Integer companyId;
    /**
     * 理赔金额
     */
    @TableField("claim_amount")
    private BigDecimal claimAmount;
    /**
     * 连带被保险人姓名
     */
    @TableField("insured_name")
    private String insuredName;
    /**
     * 分类自负金额
     */
    @TableField("cs_payment_amount")
    private BigDecimal csPaymentAmount;
    /**
     * 报销类别
     */
    private String type;
    /**
     * 投保公司
     */
    @TableField("insurance_company")
    private String insuranceCompany;
    /**
     * 受理批号
     */
    @TableField("acceptance_id")
    private String acceptanceId;
    /**
     * 保险理赔金额
     */
    @TableField("insurance_company_money")
    private BigDecimal insuranceCompanyMoney;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;


    public Integer getDetailedId() {
        return detailedId;
    }

    public void setDetailedId(Integer detailedId) {
        this.detailedId = detailedId;
    }

    public Integer getZltSn() {
        return zltSn;
    }

    public void setZltSn(Integer zltSn) {
        this.zltSn = zltSn;
    }

    public String getDossierNumber() {
        return dossierNumber;
    }

    public void setDossierNumber(String dossierNumber) {
        this.dossierNumber = dossierNumber;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Boolean getHealthCareTag() {
        return healthCareTag;
    }

    public void setHealthCareTag(Boolean healthCareTag) {
        this.healthCareTag = healthCareTag;
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public BigDecimal getCompensationAmount() {
        return compensationAmount;
    }

    public void setCompensationAmount(BigDecimal compensationAmount) {
        this.compensationAmount = compensationAmount;
    }

    public Date getClinicDate() {
        return clinicDate;
    }

    public void setClinicDate(Date clinicDate) {
        this.clinicDate = clinicDate;
    }

    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getHepeiAmount() {
        return hepeiAmount;
    }

    public void setHepeiAmount(BigDecimal hepeiAmount) {
        this.hepeiAmount = hepeiAmount;
    }

    public String getClosedStatus() {
        return closedStatus;
    }

    public void setClosedStatus(String closedStatus) {
        this.closedStatus = closedStatus;
    }

    public String getClosedReason() {
        return closedReason;
    }

    public void setClosedReason(String closedReason) {
        this.closedReason = closedReason;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public BigDecimal getFinanicalAmount() {
        return finanicalAmount;
    }

    public void setFinanicalAmount(BigDecimal finanicalAmount) {
        this.finanicalAmount = finanicalAmount;
    }

    public BigDecimal getInsurancePolicyAmount() {
        return insurancePolicyAmount;
    }

    public void setInsurancePolicyAmount(BigDecimal insurancePolicyAmount) {
        this.insurancePolicyAmount = insurancePolicyAmount;
    }

    public BigDecimal getGuoshouAmount() {
        return guoshouAmount;
    }

    public void setGuoshouAmount(BigDecimal guoshouAmount) {
        this.guoshouAmount = guoshouAmount;
    }

    public Date getGuoshouClosedDate() {
        return guoshouClosedDate;
    }

    public void setGuoshouClosedDate(Date guoshouClosedDate) {
        this.guoshouClosedDate = guoshouClosedDate;
    }

    public String getClosedRemark() {
        return closedRemark;
    }

    public void setClosedRemark(String closedRemark) {
        this.closedRemark = closedRemark;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
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

    public BigDecimal getUnHealthCareAmount() {
        return unHealthCareAmount;
    }

    public void setUnHealthCareAmount(BigDecimal unHealthCareAmount) {
        this.unHealthCareAmount = unHealthCareAmount;
    }

    public BigDecimal getApplicationAmount() {
        return applicationAmount;
    }

    public void setApplicationAmount(BigDecimal applicationAmount) {
        this.applicationAmount = applicationAmount;
    }

    public BigDecimal getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(BigDecimal approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public BigDecimal getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(BigDecimal otherAmount) {
        this.otherAmount = otherAmount;
    }

    public String getOtherReason() {
        return otherReason;
    }

    public void setOtherReason(String otherReason) {
        this.otherReason = otherReason;
    }

    public BigDecimal getOtherRejectAmount() {
        return otherRejectAmount;
    }

    public void setOtherRejectAmount(BigDecimal otherRejectAmount) {
        this.otherRejectAmount = otherRejectAmount;
    }

    public String getOtherRejectReason() {
        return otherRejectReason;
    }

    public void setOtherRejectReason(String otherRejectReason) {
        this.otherRejectReason = otherRejectReason;
    }

    public BigDecimal getAdjustAmount() {
        return adjustAmount;
    }

    public void setAdjustAmount(BigDecimal adjustAmount) {
        this.adjustAmount = adjustAmount;
    }

    public String getAdjustReason() {
        return adjustReason;
    }

    public void setAdjustReason(String adjustReason) {
        this.adjustReason = adjustReason;
    }

    public BigDecimal getCompanyMoney() {
        return companyMoney;
    }

    public void setCompanyMoney(BigDecimal companyMoney) {
        this.companyMoney = companyMoney;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public BigDecimal getCsPaymentAmount() {
        return csPaymentAmount;
    }

    public void setCsPaymentAmount(BigDecimal csPaymentAmount) {
        this.csPaymentAmount = csPaymentAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getAcceptanceId() {
        return acceptanceId;
    }

    public void setAcceptanceId(String acceptanceId) {
        this.acceptanceId = acceptanceId;
    }

    public BigDecimal getInsuranceCompanyMoney() {
        return insuranceCompanyMoney;
    }

    public void setInsuranceCompanyMoney(BigDecimal insuranceCompanyMoney) {
        this.insuranceCompanyMoney = insuranceCompanyMoney;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.detailedId;
    }

    @Override
    public String toString() {
        return "AcceptanceDetailed{" +
            ", detailedId=" + detailedId +
            ", zltSn=" + zltSn +
            ", dossierNumber=" + dossierNumber +
            ", employeeId=" + employeeId +
            ", employeeName=" + employeeName +
            ", idNum=" + idNum +
            ", healthCareTag=" + healthCareTag +
            ", invoiceNumber=" + invoiceNumber +
            ", compensationAmount=" + compensationAmount +
            ", clinicDate=" + clinicDate +
            ", acceptanceDate=" + acceptanceDate +
            ", medicalInstitution=" + medicalInstitution +
            ", diseaseName=" + diseaseName +
            ", status=" + status +
            ", hepeiAmount=" + hepeiAmount +
            ", closedStatus=" + closedStatus +
            ", closedReason=" + closedReason +
            ", closedDate=" + closedDate +
            ", finanicalAmount=" + finanicalAmount +
            ", insurancePolicyAmount=" + insurancePolicyAmount +
            ", guoshouAmount=" + guoshouAmount +
            ", guoshouClosedDate=" + guoshouClosedDate +
            ", closedRemark=" + closedRemark +
            ", invoiceType=" + invoiceType +
            ", accountAmount=" + accountAmount +
            ", selfPayAmount=" + selfPayAmount +
            ", wholePlanAmount=" + wholePlanAmount +
            ", attachAmount=" + attachAmount +
            ", ownExpenseAmount=" + ownExpenseAmount +
            ", unHealthCareAmount=" + unHealthCareAmount +
            ", applicationAmount=" + applicationAmount +
            ", approvedAmount=" + approvedAmount +
            ", otherAmount=" + otherAmount +
            ", otherReason=" + otherReason +
            ", otherRejectAmount=" + otherRejectAmount +
            ", otherRejectReason=" + otherRejectReason +
            ", adjustAmount=" + adjustAmount +
            ", adjustReason=" + adjustReason +
            ", companyMoney=" + companyMoney +
            ", companyId=" + companyId +
            ", claimAmount=" + claimAmount +
            ", insuredName=" + insuredName +
            ", csPaymentAmount=" + csPaymentAmount +
            ", type=" + type +
            ", insuranceCompany=" + insuranceCompany +
            ", acceptanceId=" + acceptanceId +
            ", insuranceCompanyMoney=" + insuranceCompanyMoney +
            ", createdTime=" + createdTime +
            "}";
    }
}
