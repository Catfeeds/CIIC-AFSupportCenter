package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer;

/**
 * Created by houwanhua on 2018/3/28.
 */
public class PaymentProcessParmBO {
    private String paymentId;
    private String operator;

    public String getPaymentId() {
        return paymentId;
    }

    public PaymentProcessParmBO setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public PaymentProcessParmBO setOperator(String operator) {
        this.operator = operator;
        return this;
    }
}
