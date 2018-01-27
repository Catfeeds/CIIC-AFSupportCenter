package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SsAnnualAdjustAccountEmpDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long annualAdjustAccountId;
    private Integer matchStatus;
    private Integer accountStatus;
    private String companyId;
    private String ssSerial;
    private String employeeId;
    private String employeeName;
    private BigDecimal avgMonthSalaryStart;
    private BigDecimal avgMonthSalaryEnd;

    public Long getAnnualAdjustAccountId() {
        return annualAdjustAccountId;
    }

    public void setAnnualAdjustAccountId(Long annualAdjustAccountId) {
        this.annualAdjustAccountId = annualAdjustAccountId;
    }

    public Integer getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(Integer matchStatus) {
        this.matchStatus = matchStatus;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getSsSerial() {
        return ssSerial;
    }

    public void setSsSerial(String ssSerial) {
        this.ssSerial = ssSerial;
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

    public BigDecimal getAvgMonthSalaryStart() {
        return avgMonthSalaryStart;
    }

    public void setAvgMonthSalaryStart(BigDecimal avgMonthSalaryStart) {
        this.avgMonthSalaryStart = avgMonthSalaryStart;
    }

    public BigDecimal getAvgMonthSalaryEnd() {
        return avgMonthSalaryEnd;
    }

    public void setAvgMonthSalaryEnd(BigDecimal avgMonthSalaryEnd) {
        this.avgMonthSalaryEnd = avgMonthSalaryEnd;
    }
}
