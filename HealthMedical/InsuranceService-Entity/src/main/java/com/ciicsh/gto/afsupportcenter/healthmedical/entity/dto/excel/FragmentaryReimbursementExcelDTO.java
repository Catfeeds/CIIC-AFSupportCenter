package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 14:47 2018/3/10
 */
@ExcelTarget("FragmentaryReimbursementExcel")
public class FragmentaryReimbursementExcelDTO implements Serializable {

    @Excel(name = "雇员编号")
    private String employeeId;
    @Excel(name = "雇员姓名")
    private String employeeName;
    @Excel(name = "证件号码")
    private String idNum;
    @Excel(name = "公司编号")
    private String companyId;
    @Excel(name = "公司名称")
    private String companyName;
    @Excel(name = "受理金额")
    private BigDecimal caseMoney;
    @Excel(name = "发票数")
    private Integer invoiceNumber;
    @Excel(name = "医疗备注")
    private String medicalRemark;
    @Excel(name = "医保结算金额")
    private BigDecimal medicalClearingMoney;
    @Excel(name = "医疗结算反馈")
    private String medicalClearingFeedBack;

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

    public BigDecimal getCaseMoney() {
        return caseMoney;
    }

    public void setCaseMoney(BigDecimal caseMoney) {
        this.caseMoney = caseMoney;
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getMedicalRemark() {
        return medicalRemark;
    }

    public void setMedicalRemark(String medicalRemark) {
        this.medicalRemark = medicalRemark;
    }

    public BigDecimal getMedicalClearingMoney() {
        return medicalClearingMoney;
    }

    public void setMedicalClearingMoney(BigDecimal medicalClearingMoney) {
        this.medicalClearingMoney = medicalClearingMoney;
    }

    public String getMedicalClearingFeedBack() {
        return medicalClearingFeedBack;
    }

    public void setMedicalClearingFeedBack(String medicalClearingFeedBack) {
        this.medicalClearingFeedBack = medicalClearingFeedBack;
    }

    @Override
    public String toString() {
        return "FragmentaryReimbursementExcelDTO{" +
            "employeeId='" + employeeId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", idNum='" + idNum + '\'' +
            ", companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", caseMoney=" + caseMoney +
            ", invoiceNumber=" + invoiceNumber +
            ", medicalRemark='" + medicalRemark + '\'' +
            ", medicalClearingMoney=" + medicalClearingMoney +
            ", medicalClearingFeedBack='" + medicalClearingFeedBack + '\'' +
            '}';
    }
}
