package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 补充医疗--导入数据对象
 *
 * @author xiweizhen
 */
public class SupplyAcceptanceImportDTO {
    /**
     * 受理单编号（比如：201801100173-4）
     */
    @TableId("acceptance_id")
    private String acceptanceId;
    /**
     * 案卷号（from 中盈）
     */
    @TableField("dossier_number")
    private String dossierNumber;
    /**
     * 导入日期
     */
    @TableField("input_date")
    private Date inputDate;
    /**
     * 受理单状态（0-未审批，1-已批退，2-已审核未同步，3-已同步未支付，4-财务退回，5-已同步已支付，6-已退票，7-已完成）
     */
    private Integer status;
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
     * 公司编号
     */
    @TableField("company_id")
    private Integer companyId;
    /**
     * 公司名称
     */
    @TableField("company_name")
    private String companyName;
    /**
     * 发票数
     */
    @TableField("invoice_number")
    private Integer invoiceNumber;
    /**
     * 公司理赔总金额
     */
    @TableField("total_company_amount")
    private BigDecimal totalCompanyAmount;
    /**
     * 保险公司理赔总金额
     */
    @TableField("total_insurance_company_money")
    private BigDecimal totalInsuranceCompanyMoney;
    /**
     * 分类自付金额（总）
     */
    @TableField("total_cs_payment_amount")
    private BigDecimal totalCsPaymentAmount;
    /**
     * 申请金额（总）
     */
    @TableField("total_application_amount")
    private BigDecimal totalApplicationAmount;
    /**
     * 核准金额
     */
    @TableField("total_approved_amount")
    private BigDecimal totalApprovedAmount;
    /**
     * 索赔金额（总）
     */
    @TableField("total_claim_amount")
    private BigDecimal totalClaimAmount;
    /**
     * 类别（1-雇员，2-子女，3-配偶）
     */
    private Integer type;
    /**
     * 连带被保险人姓名
     */
    @TableField("insured_name")
    private String insuredName;
    /**
     * 审核人
     */
    private String auditor;
    /**
     * 审核时间
     */
    @TableField("audit_time")
    private Date auditTime;

    /**
     * 发票编号
     */
    @TableId(value = "invoice_id", type = IdType.AUTO)
    private Integer invoiceId;

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
    private Date clinicDate;
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
    @TableField("cs_payment_amount")
    private BigDecimal csPaymentAmount;
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

    public String getAcceptanceId() {
        return acceptanceId;
    }

    public void setAcceptanceId(String acceptanceId) {
        this.acceptanceId = acceptanceId;
    }

    public String getDossierNumber() {
        return dossierNumber;
    }

    public void setDossierNumber(String dossierNumber) {
        this.dossierNumber = dossierNumber;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public BigDecimal getTotalCompanyAmount() {
        return totalCompanyAmount;
    }

    public void setTotalCompanyAmount(BigDecimal totalCompanyAmount) {
        this.totalCompanyAmount = totalCompanyAmount;
    }

    public BigDecimal getTotalInsuranceCompanyMoney() {
        return totalInsuranceCompanyMoney;
    }

    public void setTotalInsuranceCompanyMoney(BigDecimal totalInsuranceCompanyMoney) {
        this.totalInsuranceCompanyMoney = totalInsuranceCompanyMoney;
    }

    public BigDecimal getTotalCsPaymentAmount() {
        return totalCsPaymentAmount;
    }

    public void setTotalCsPaymentAmount(BigDecimal totalCsPaymentAmount) {
        this.totalCsPaymentAmount = totalCsPaymentAmount;
    }

    public BigDecimal getTotalApplicationAmount() {
        return totalApplicationAmount;
    }

    public void setTotalApplicationAmount(BigDecimal totalApplicationAmount) {
        this.totalApplicationAmount = totalApplicationAmount;
    }

    public BigDecimal getTotalApprovedAmount() {
        return totalApprovedAmount;
    }

    public void setTotalApprovedAmount(BigDecimal totalApprovedAmount) {
        this.totalApprovedAmount = totalApprovedAmount;
    }

    public BigDecimal getTotalClaimAmount() {
        return totalClaimAmount;
    }

    public void setTotalClaimAmount(BigDecimal totalClaimAmount) {
        this.totalClaimAmount = totalClaimAmount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
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

    public Date getClinicDate() {
        return clinicDate;
    }

    public void setClinicDate(Date clinicDate) {
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

    public BigDecimal getCsPaymentAmount() {
        return csPaymentAmount;
    }

    public void setCsPaymentAmount(BigDecimal csPaymentAmount) {
        this.csPaymentAmount = csPaymentAmount;
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

    private String col1;
    private String col2;
    private String col3;
    private String col4;
    private String col5;
    private String col6;
    private String col7;
    private String col8;
    private String col9;
    private String col10;
    private String col11;
    private String col12;
    private String col13;
    private String col14;
    private String col15;
    private String col16;
    private String col17;
    private String col18;
    private String col19;
    private String col20;
    private String col21;
    private String col22;
    private String col23;
    private String col24;

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4;
    }

    public String getCol5() {
        return col5;
    }

    public void setCol5(String col5) {
        this.col5 = col5;
    }

    public String getCol6() {
        return col6;
    }

    public void setCol6(String col6) {
        this.col6 = col6;
    }

    public String getCol7() {
        return col7;
    }

    public void setCol7(String col7) {
        this.col7 = col7;
    }

    public String getCol8() {
        return col8;
    }

    public void setCol8(String col8) {
        this.col8 = col8;
    }

    public String getCol9() {
        return col9;
    }

    public void setCol9(String col9) {
        this.col9 = col9;
    }

    public String getCol10() {
        return col10;
    }

    public void setCol10(String col10) {
        this.col10 = col10;
    }

    public String getCol11() {
        return col11;
    }

    public void setCol11(String col11) {
        this.col11 = col11;
    }

    public String getCol12() {
        return col12;
    }

    public void setCol12(String col12) {
        this.col12 = col12;
    }

    public String getCol13() {
        return col13;
    }

    public void setCol13(String col13) {
        this.col13 = col13;
    }

    public String getCol14() {
        return col14;
    }

    public void setCol14(String col14) {
        this.col14 = col14;
    }

    public String getCol15() {
        return col15;
    }

    public void setCol15(String col15) {
        this.col15 = col15;
    }

    public String getCol16() {
        return col16;
    }

    public void setCol16(String col16) {
        this.col16 = col16;
    }

    public String getCol17() {
        return col17;
    }

    public void setCol17(String col17) {
        this.col17 = col17;
    }

    public String getCol18() {
        return col18;
    }

    public void setCol18(String col18) {
        this.col18 = col18;
    }

    public String getCol19() {
        return col19;
    }

    public void setCol19(String col19) {
        this.col19 = col19;
    }

    public String getCol20() {
        return col20;
    }

    public void setCol20(String col20) {
        this.col20 = col20;
    }

    public String getCol21() {
        return col21;
    }

    public void setCol21(String col21) {
        this.col21 = col21;
    }

    public String getCol22() {
        return col22;
    }

    public void setCol22(String col22) {
        this.col22 = col22;
    }

    public String getCol23() {
        return col23;
    }

    public void setCol23(String col23) {
        this.col23 = col23;
    }

    public String getCol24() {
        return col24;
    }

    public void setCol24(String col24) {
        this.col24 = col24;
    }

    @Override
    public String toString() {
        return "SupplyAcceptanceImportDTO{" +
            "acceptanceId='" + acceptanceId + '\'' +
            ", dossierNumber='" + dossierNumber + '\'' +
            ", inputDate=" + inputDate +
            ", status=" + status +
            ", employeeId='" + employeeId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", companyId=" + companyId +
            ", companyName='" + companyName + '\'' +
            ", invoiceNumber=" + invoiceNumber +
            ", totalCompanyAmount=" + totalCompanyAmount +
            ", totalInsuranceCompanyMoney=" + totalInsuranceCompanyMoney +
            ", totalCsPaymentAmount=" + totalCsPaymentAmount +
            ", totalApplicationAmount=" + totalApplicationAmount +
            ", totalApprovedAmount=" + totalApprovedAmount +
            ", totalClaimAmount=" + totalClaimAmount +
            ", type=" + type +
            ", insuredName='" + insuredName + '\'' +
            ", auditor='" + auditor + '\'' +
            ", auditTime=" + auditTime +
            ", invoiceId=" + invoiceId +
            ", medicalInstitution='" + medicalInstitution + '\'' +
            ", diseaseName='" + diseaseName + '\'' +
            ", clinicDate=" + clinicDate +
            ", selfPayAmount=" + selfPayAmount +
            ", wholePlanAmount=" + wholePlanAmount +
            ", attachAmount=" + attachAmount +
            ", ownExpenseAmount=" + ownExpenseAmount +
            ", accountAmount=" + accountAmount +
            ", applicationAmount=" + applicationAmount +
            ", claimAmount=" + claimAmount +
            ", approvedAmount=" + approvedAmount +
            ", csPaymentAmount=" + csPaymentAmount +
            ", companyMoney=" + companyMoney +
            ", insuranceCompanyMoney=" + insuranceCompanyMoney +
            ", closedStatus='" + closedStatus + '\'' +
            ", closedRemark='" + closedRemark + '\'' +
            '}';
    }
}
