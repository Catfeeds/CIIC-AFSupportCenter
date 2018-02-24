package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.math.BigDecimal;

@ExcelTarget("annualAdjustEmployee")
public class SsAnnualAdjustAccountEmpBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long annualAdjustAccountEmpId;
    private Long annualAdjustAccountId;

    @Excel(name = "匹配状态", replace = {"未匹配_0", "已匹配_1"}, width = 10)
    private Integer matchStatus;
    @Excel(name = "客户编号", orderNum = "2", width = 20)
    private String companyId;
    @Excel(name = "客户名称", orderNum = "3", width = 30)
    private String companyName;
    @Excel(name = "雇员编号", orderNum = "5", width = 20)
    private Long employeeId;
    @Excel(name = "人员类别", replace = {"一般人员信息_1", "转出人员信息_2"}, width = 20, orderNum = "1")
    private Integer accountStatus;
    @Excel(name = "社保序号", orderNum = "4", width = 20)
    private Integer ssSerial;
    @Excel(name = "雇员姓名", orderNum = "6", width = 15)
    private String employeeName;
    @Excel(name = "身份证号", orderNum = "7", width = 25)
    private String idNum;
    @Excel(name = "本单位缴费月数", orderNum = "8", width = 20)
    private Integer paymentMonths;
    @Excel(name = "职工月平均工资收入", orderNum = "9", width = 25)
    private BigDecimal avgMonthSalary;

    public Integer getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(Integer matchStatus) {
        this.matchStatus = matchStatus;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getAnnualAdjustAccountEmpId() {
        return annualAdjustAccountEmpId;
    }

    public void setAnnualAdjustAccountEmpId(Long annualAdjustAccountEmpId) {
        this.annualAdjustAccountEmpId = annualAdjustAccountEmpId;
    }

    public Long getAnnualAdjustAccountId() {
        return annualAdjustAccountId;
    }

    public void setAnnualAdjustAccountId(Long annualAdjustAccountId) {
        this.annualAdjustAccountId = annualAdjustAccountId;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getSsSerial() {
        return ssSerial;
    }

    public void setSsSerial(Integer ssSerial) {
        this.ssSerial = ssSerial;
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

    public Integer getPaymentMonths() {
        return paymentMonths;
    }

    public void setPaymentMonths(Integer paymentMonths) {
        this.paymentMonths = paymentMonths;
    }

    public BigDecimal getAvgMonthSalary() {
        return avgMonthSalary;
    }

    public void setAvgMonthSalary(BigDecimal avgMonthSalary) {
        this.avgMonthSalary = avgMonthSalary;
    }
}
