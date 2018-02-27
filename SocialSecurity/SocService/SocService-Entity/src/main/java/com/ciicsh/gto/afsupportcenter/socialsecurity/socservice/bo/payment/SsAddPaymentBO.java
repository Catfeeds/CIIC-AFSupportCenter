package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.payment;

import java.util.List;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录）
 * </p>
 *
 * @author wengxk
 * @since 2017-12-08
 */
public class SsAddPaymentBO {

    private static final long serialVersionUID = 1L;

    /**
     * 支付批次ID
     */
    private Long paymentId;

    /**
     * 企业社保账户分类
     */
    private List<Long> paymentComIdList;

    public List<Long> getPaymentComIdList() {
        return paymentComIdList;
    }

    public void setPaymentComIdList(List<Long> paymentComIdList) {
        this.paymentComIdList = paymentComIdList;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
}

