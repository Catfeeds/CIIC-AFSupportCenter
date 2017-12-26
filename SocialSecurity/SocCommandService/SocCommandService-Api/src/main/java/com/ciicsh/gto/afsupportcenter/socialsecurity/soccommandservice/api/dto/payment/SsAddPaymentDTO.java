package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.payment;

import java.util.List;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录）
 * </p>
 *
 * @author wengxk
 * @since 2017-12-08
 */
public class SsAddPaymentDTO {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    private String paymentId;

    /**
     * 企业社保账户分类
     */
    private List<String> paymentComIdList;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public List<String> getPaymentComIdList() {
        return paymentComIdList;
    }

    public void setPaymentComIdList(List<String> paymentComIdList) {
        this.paymentComIdList = paymentComIdList;
    }
}
