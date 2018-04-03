package com.ciicsh.gto.afsupportcenter.fundjob.bo;


import java.io.Serializable;

public class HfPaymentAccountBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */

    private Long paymentAccountId;
    private Long comAccountClassId;
    private Long paymentId;
    private int paymentStatus;
    private String paymentMonth;
    /**
     * 外键：企业社保账户
     */
    private Long comAccountId;
    /**
     * 付款方式
     */
    private String paymentWay;

    public Long getPaymentAccountId() {
        return paymentAccountId;
    }

    public void setPaymentAccountId(Long paymentAccountId) {
        this.paymentAccountId = paymentAccountId;
    }

    public Long getComAccountClassId() {
        return comAccountClassId;
    }

    public void setComAccountClassId(Long comAccountClassId) {
        this.comAccountClassId = comAccountClassId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(String paymentMonth) {
        this.paymentMonth = paymentMonth;
    }

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }
}
