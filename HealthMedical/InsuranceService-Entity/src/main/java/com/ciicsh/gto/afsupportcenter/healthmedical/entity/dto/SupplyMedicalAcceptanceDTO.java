package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 补充医疗受理单表
 * 分页查询出入参数
 * </p>
 *
 * @author xiweizhen
 */
public class SupplyMedicalAcceptanceDTO extends CommonEntity {

    /**
     * 管理方编号
     */
    private String managementId;
    /**
     * 管理方名称
     */
    private String managementName;
    /**
     * 受理日期
     */
    private List<Date> inputDateRange;

    public List<Date> getInputDateRange() {
        return inputDateRange;
    }

    public void setInputDateRange(List<Date> inputDateRange) {
        this.inputDateRange = inputDateRange;
    }

    public String getManagementId() {
        return managementId;
    }

    public void setManagementId(String managementId) {
        this.managementId = managementId;
    }

    public String getManagementName() {
        return managementName;
    }

    public void setManagementName(String managementName) {
        this.managementName = managementName;
    }

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
    private String companyId;
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
     * 是否可用
     */
    @TableField("is_active")
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
    public String toString() {
        return super.toString() + "SupplyMedicalAcceptanceDTO{" +
            ", inputDateRange=" + inputDateRange +
            ", managementId=" + managementId +
            ", managementName=" + managementName +
            ", acceptanceId=" + acceptanceId +
            ", dossierNumber=" + dossierNumber +
            ", inputDate=" + inputDate +
            ", status=" + status +
            ", employeeId=" + employeeId +
            ", employeeName=" + employeeName +
            ", companyId=" + companyId +
            ", companyName=" + companyName +
            ", invoiceNumber=" + invoiceNumber +
            ", totalCompanyAmount=" + totalCompanyAmount +
            ", totalInsuranceCompanyMoney=" + totalInsuranceCompanyMoney +
            ", totalCsPaymentAmount=" + totalCsPaymentAmount +
            ", totalApplicationAmount=" + totalApplicationAmount +
            ", totalApprovedAmount=" + totalApprovedAmount +
            ", totalClaimAmount=" + totalClaimAmount +
            ", type=" + type +
            ", insuredName=" + insuredName +
            ", auditor=" + auditor +
            ", auditTime=" + auditTime +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
