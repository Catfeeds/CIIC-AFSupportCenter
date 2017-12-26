package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPayment;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录）
 * </p>
 *
 * @author wengxk
 * @since 2017-12-08
 */
public class SsPaymentDTO extends SsPayment {

    private static final long serialVersionUID = 1L;


    /**
     * 最小支付年月
     */
    private String paymentMonthMin;

    /**
     * 最大支付年月
     */
    private String paymentMonthMax;

    public String getPaymentMonthMin() {
        return paymentMonthMin;
    }

    public void setPaymentMonthMin(String paymentMonthMin) {
        this.paymentMonthMin = paymentMonthMin;
    }

    public String getPaymentMonthMax() {
        return paymentMonthMax;
    }

    public void setPaymentMonthMax(String paymentMonthMax) {
        this.paymentMonthMax = paymentMonthMax;
    }
}
