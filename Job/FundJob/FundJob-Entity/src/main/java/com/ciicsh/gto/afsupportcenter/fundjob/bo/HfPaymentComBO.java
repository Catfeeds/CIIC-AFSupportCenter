package com.ciicsh.gto.afsupportcenter.fundjob.bo;


import java.io.Serializable;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录）
 * </p>
 *
 * @author zhangxj
 * @since 2017-12-08
 */
public class HfPaymentComBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
    private Long paymentComId;

    private Long paymentId;

    private String companyId;

    /**
     * 外键：企业社保账户
     */
    private Long comAccountId;
    /**
     * 付款方式
     */
    private String paymentWay;

    public Long getPaymentComId() {
        return paymentComId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public void setPaymentComId(Long paymentComId) {
        this.paymentComId = paymentComId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
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
