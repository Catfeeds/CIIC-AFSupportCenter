package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer;

import java.math.BigDecimal;

/**
 * Created by houwanhua on 2018/3/29.
 */
public class PaymentEmpBO {
    private String companyId;
    private String employeeId;
    private String employeeName;
    private String payMonth;
    private BigDecimal payAmount;
    private String businessTypeDesc;
    private Integer isAdvance;

    public String getCompanyId() {
        return companyId;
    }

    public PaymentEmpBO setCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public PaymentEmpBO setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public PaymentEmpBO setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
        return this;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public PaymentEmpBO setPayMonth(String payMonth) {
        this.payMonth = payMonth;
        return this;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public PaymentEmpBO setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
        return this;
    }

    public String getBusinessTypeDesc() {
        return businessTypeDesc;
    }

    public PaymentEmpBO setBusinessTypeDesc(String businessTypeDesc) {
        this.businessTypeDesc = businessTypeDesc;
        return this;
    }

    public Integer getIsAdvance() {
        return isAdvance;
    }

    public PaymentEmpBO setIsAdvance(Integer isAdvance) {
        this.isAdvance = isAdvance;
        return this;
    }
}
