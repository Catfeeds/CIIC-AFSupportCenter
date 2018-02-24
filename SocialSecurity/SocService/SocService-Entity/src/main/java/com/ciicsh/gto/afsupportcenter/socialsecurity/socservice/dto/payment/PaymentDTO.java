package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.payment;

import java.util.Date;
import java.util.List;

public class PaymentDTO {
    private String departmentName;
    private int isFinacedept;
    private int businessType;
    private long businessPkId;
    private int payWay;
    private double payAmount;
    private String receiver;
    private String applyer;
    private Date applyDate;
    private String payPurpose;
    private String payReason;
    private PaymentComDTO paymentComDTO;
    private PaymentEmpDTO paymentEmpDTO;
    private List<PaymentComDTO> paymentComList;
    private List<PaymentEmpDTO> paymentEmpList;

    public List<PaymentComDTO> getPaymentComList() {
        return paymentComList;
    }

    public void setPaymentComList(List<PaymentComDTO> paymentComList) {
        this.paymentComList = paymentComList;
    }

    public List<PaymentEmpDTO> getPaymentEmpList() {
        return paymentEmpList;
    }

    public void setPaymentEmpList(List<PaymentEmpDTO> paymentEmpList) {
        this.paymentEmpList = paymentEmpList;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getIsFinacedept() {
        return isFinacedept;
    }

    public void setIsFinacedept(int isFinacedept) {
        this.isFinacedept = isFinacedept;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public long getBusinessPkId() {
        return businessPkId;
    }

    public void setBusinessPkId(long businessPkId) {
        this.businessPkId = businessPkId;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getPayPurpose() {
        return payPurpose;
    }

    public void setPayPurpose(String payPurpose) {
        this.payPurpose = payPurpose;
    }

    public String getPayReason() {
        return payReason;
    }

    public void setPayReason(String payReason) {
        this.payReason = payReason;
    }

    public PaymentComDTO getPaymentComDTO() {
        return paymentComDTO;
    }

    public void setPaymentComDTO(PaymentComDTO paymentComDTO) {
        this.paymentComDTO = paymentComDTO;
    }

    public PaymentEmpDTO getPaymentEmpDTO() {
        return paymentEmpDTO;
    }

    public void setPaymentEmpDTO(PaymentEmpDTO paymentEmpDTO) {
        this.paymentEmpDTO = paymentEmpDTO;
    }
}
