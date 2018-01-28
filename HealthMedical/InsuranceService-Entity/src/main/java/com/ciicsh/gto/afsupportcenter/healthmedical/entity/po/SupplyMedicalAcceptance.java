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
 * 补充医疗受理单表
 * </p>
 *
 * @author xiweizhen
 */
@TableName("hm_supply_medical_acceptance")
public class SupplyMedicalAcceptance extends Model<SupplyMedicalAcceptance> {

    private static final long serialVersionUID = 1L;

    /**
     * 受理单编号（比如：201708100477）
     */
    @TableId(value = "acceptance_id", type = IdType.AUTO)
    private Long acceptanceId;
    /**
     * 案卷号
     */
    @TableField("dossier_number")
    private String dossierNumber;
    /**
     * 导入日期
     */
    @TableField("imput_date")
    private Date inputDate;
    /**
     * 受理单状态（0-未审批，1-已批退，2-已审核未同步，3-已同步未支付，4-财务退回，5-已同步已支付，6-已退票，7-已完成)
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


    public Long getAcceptanceId() {
        return acceptanceId;
    }

    public void setAcceptanceId(Long acceptanceId) {
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
    protected Serializable pkVal() {
        return this.acceptanceId;
    }

    @Override
    public String toString() {
        return "SupplyMedicalAcceptance{" +
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
