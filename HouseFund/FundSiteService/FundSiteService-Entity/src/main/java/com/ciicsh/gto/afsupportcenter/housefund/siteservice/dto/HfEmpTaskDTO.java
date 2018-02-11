package com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HfEmpTaskDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String companyId;
    private String employeeId;
    private String employeeName;
    private String idNum;
    private Integer hfAccountType;
    private Integer hfType;
    private Integer paymentBank;
    private Integer taskCategory;
    private Integer urgent;
    private Integer processStatus;
    private Integer taskStatus;
    private LocalDate[] submitTime;
    private LocalDate submitTimeStart;
    private LocalDate submitTimeEnd;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Integer getHfAccountType() {
        return hfAccountType;
    }

    public void setHfAccountType(Integer hfAccountType) {
        this.hfAccountType = hfAccountType;
    }

    public Integer getHfType() {
        return hfType;
    }

    public void setHfType(Integer hfType) {
        this.hfType = hfType;
    }

    public Integer getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(Integer paymentBank) {
        this.paymentBank = paymentBank;
    }

    public Integer getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(Integer taskCategory) {
        this.taskCategory = taskCategory;
    }

    public Integer getUrgent() {
        return urgent;
    }

    public void setUrgent(Integer urgent) {
        this.urgent = urgent;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public LocalDate getSubmitTimeStart() {
        return submitTimeStart;
    }

    public void setSubmitTimeStart(LocalDate submitTimeStart) {
        this.submitTimeStart = submitTimeStart;
    }

    public LocalDate getSubmitTimeEnd() {
        return submitTimeEnd;
    }

    public LocalDate[] getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDate[] submitTime) {
        this.submitTime = submitTime;
        if (submitTime != null && submitTime.length == 2) {
            setSubmitTimeStart(submitTime[0]);
            setSubmitTimeEnd(submitTime[1]);
        }
    }

    public void setSubmitTimeEnd(LocalDate submitTimeEnd) {
        this.submitTimeEnd = submitTimeEnd;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }
}
