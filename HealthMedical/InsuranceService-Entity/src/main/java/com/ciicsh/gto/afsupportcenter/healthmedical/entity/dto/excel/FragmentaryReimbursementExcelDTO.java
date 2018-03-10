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
    @Excel(name = "中止日期", format = "yyyy-MM-dd")
    private Date stopDate;
    @Excel(name = "证件号码")
    private String IDNum;
    @Excel(name = "公司编号")
    private String companyId;
    @Excel(name = "公司名称")
    private String companyName;
    @Excel(name = "客户经理")
    private String manager;
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
    @Excel(name = "转出日期", format = "yyyy-MM-dd")
    private Date turnOutDate;
    @Excel(name = "转出地点")
    private String turnOutAddress;

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

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public String getIDNum() {
        return IDNum;
    }

    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
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

    public Date getTurnOutDate() {
        return turnOutDate;
    }

    public void setTurnOutDate(Date turnOutDate) {
        this.turnOutDate = turnOutDate;
    }

    public String getTurnOutAddress() {
        return turnOutAddress;
    }

    public void setTurnOutAddress(String turnOutAddress) {
        this.turnOutAddress = turnOutAddress;
    }

    @Override
    public String toString() {
        return "FragmentaryReimbursementExcelDTO{" +
            "employeeId='" + employeeId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", stopDate=" + stopDate +
            ", IDNum='" + IDNum + '\'' +
            ", companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", manager='" + manager + '\'' +
            ", caseMoney=" + caseMoney +
            ", invoiceNumber=" + invoiceNumber +
            ", medicalRemark='" + medicalRemark + '\'' +
            ", medicalClearingMoney=" + medicalClearingMoney +
            ", medicalClearingFeedBack='" + medicalClearingFeedBack + '\'' +
            ", turnOutDate=" + turnOutDate +
            ", turnOutAddress='" + turnOutAddress + '\'' +
            '}';
    }
}
