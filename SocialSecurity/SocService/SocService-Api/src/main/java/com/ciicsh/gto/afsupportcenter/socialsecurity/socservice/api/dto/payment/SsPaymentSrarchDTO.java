package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.payment;

import java.util.List;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录）
 * </p>
 *
 * @author wengxk
 * @since 2017-12-08
 */
public class SsPaymentSrarchDTO {

    private static final long serialVersionUID = 1L;


    /**
     * 企业社保账户分类
     */
    private String accountType;

    /**
     * 支付状态List
     */
    private List<String> paymentStateList;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<String> getPaymentStateList() {
        return paymentStateList;
    }

    public void setPaymentStateList(List<String> paymentStateList) {
        this.paymentStateList = paymentStateList;
    }
}
