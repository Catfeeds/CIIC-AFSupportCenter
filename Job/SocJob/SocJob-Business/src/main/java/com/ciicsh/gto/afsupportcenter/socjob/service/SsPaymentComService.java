package com.ciicsh.gto.afsupportcenter.socjob.service;

import com.ciicsh.gto.afsupportcenter.socjob.entity.custom.SsAccountComExt;
import com.ciicsh.gto.afsupportcenter.util.kafkaMessage.SocReportMessage;

/**
 * Created by houwanhua on 2018/1/11.
 */
public interface SsPaymentComService {
    /**
     * 生成社保支付账号信息
     * @param paymentMonth 支付年月
     */
    void generateSocPaymentInfo(String paymentMonth) throws Exception;


    /**
     * 根据支付年月和社保账户生成社保支付信息
     * @param comAccountId 社保账号
     * @param paymentMonth 支付年月
     */
    void generateSocPaymentInfo(Long comAccountId,String paymentMonth)throws Exception;

    void generateMonthEmpChangeReport(SocReportMessage message)throws Exception;

    void generatePaymentDetailReport(Long comAccountId,String paymentMonth)throws Exception;
}
