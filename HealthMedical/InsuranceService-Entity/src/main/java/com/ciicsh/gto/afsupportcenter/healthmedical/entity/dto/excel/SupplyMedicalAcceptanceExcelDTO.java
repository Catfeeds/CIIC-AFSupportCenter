package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 16:51 2018/3/9
 */
@ExcelTarget("SupplyMedicalAcceptanceExcel")
public class SupplyMedicalAcceptanceExcelDTO implements Serializable{

    @Excel(name = "受理编号")
    private String acceptanceId;
    @Excel(name = "案卷号")
    private String dossierNumber;
    @Excel(name = "导入日期")
    private Date inputDate;
    @Excel(name = "雇员编号")
    private String employeeId;
    @Excel(name = "雇员姓名")
    private String employeeName;
    @Excel(name = "公司编号")
    private String companyId;
    @Excel(name = "公司名称")
    private String companyName;
    @Excel(name = "状态")
    private Integer status;
    @Excel(name = "处理日期")
    private Date auditTime;
    @Excel(name = "发票合计")
    private Integer invoiceNumber;
    @Excel(name = "公司理赔金额")
    private BigDecimal totalCompanyAmount;
    @Excel(name = "保险公司理赔金额")
    private BigDecimal totalInsuranceCompanyMoney;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
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

    @Override
    public String toString() {
        return "SupplyMedicalAcceptanceExcelDTO{" +
            "acceptanceId='" + acceptanceId + '\'' +
            ", dossierNumber='" + dossierNumber + '\'' +
            ", inputDate=" + inputDate +
            ", employeeId='" + employeeId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", status=" + status +
            ", auditTime=" + auditTime +
            ", invoiceNumber=" + invoiceNumber +
            ", totalCompanyAmount=" + totalCompanyAmount +
            ", totalInsuranceCompanyMoney=" + totalInsuranceCompanyMoney +
            '}';
    }
}
