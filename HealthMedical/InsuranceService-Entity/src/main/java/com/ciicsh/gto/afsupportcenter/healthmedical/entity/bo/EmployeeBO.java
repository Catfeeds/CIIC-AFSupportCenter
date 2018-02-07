package com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo;

import java.util.Date;

/**
 * 发票雇员信息详情
 *
 * @author xiweizhen
 */
public class EmployeeBO {
    private String employeeId;
    private String employeeName;
    private String companyId;
    private String companyName;
    private Date startDate;
    private Date startConfirmDate;
    private Date endDate;
    private Date endConfirmDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartConfirmDate() {
        return startConfirmDate;
    }

    public void setStartConfirmDate(Date startConfirmDate) {
        this.startConfirmDate = startConfirmDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndConfirmDate() {
        return endConfirmDate;
    }

    public void setEndConfirmDate(Date endConfirmDate) {
        this.endConfirmDate = endConfirmDate;
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

    @Override
    public String toString() {
        return "EmployeeBO{" +
            "employeeId='" + employeeId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", endDate='" + startDate + '\'' +
            ", endConfirmDate='" + startConfirmDate + '\'' +
            ", endDate='" + endDate + '\'' +
            ", endConfirmDate='" + endConfirmDate + '\'' +
            '}';
    }
}
