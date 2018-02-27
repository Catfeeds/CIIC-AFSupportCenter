package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SsAnnualAdjustCompanyEmpDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long annualAdjustCompanyEmpId;
    private Long annualAdjustCompanyId;
    private String employeeName;
    private String idNum;
    private BigDecimal baseAmountStart;
    private BigDecimal baseAmountEnd;
    private String employeeId;
    private String ssSerial;
    private String companyId;

    public Long getAnnualAdjustCompanyEmpId() {
        return annualAdjustCompanyEmpId;
    }

    public void setAnnualAdjustCompanyEmpId(Long annualAdjustCompanyEmpId) {
        this.annualAdjustCompanyEmpId = annualAdjustCompanyEmpId;
    }

    public Long getAnnualAdjustCompanyId() {
        return annualAdjustCompanyId;
    }

    public void setAnnualAdjustCompanyId(Long annualAdjustCompanyId) {
        this.annualAdjustCompanyId = annualAdjustCompanyId;
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

    public BigDecimal getBaseAmountStart() {
        return baseAmountStart;
    }

    public void setBaseAmountStart(BigDecimal baseAmountStart) {
        this.baseAmountStart = baseAmountStart;
    }

    public BigDecimal getBaseAmountEnd() {
        return baseAmountEnd;
    }

    public void setBaseAmountEnd(BigDecimal baseAmountEnd) {
        this.baseAmountEnd = baseAmountEnd;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getSsSerial() {
        return ssSerial;
    }

    public void setSsSerial(String ssSerial) {
        this.ssSerial = ssSerial;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
