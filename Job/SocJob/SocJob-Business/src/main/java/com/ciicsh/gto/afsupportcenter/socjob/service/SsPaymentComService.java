package com.ciicsh.gto.afsupportcenter.socjob.service;

/**
 * Created by houwanhua on 2018/1/11.
 */
public interface SsPaymentComService {
    /**
     * 生成社保支付账号信息
     * @param paymentMonth 支付年月
     */
    void generateSocPaymentInfo(String paymentMonth);
}
