package com.ciicsh.gto.afsupportcenter.socjob.service;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsPaymentCom;

/**
 * Created by houwanhua on 2018/1/19.
 */
public interface PaymentService extends IService<SsPaymentCom> {
    /**
     * 判断雇员是否垫付、是否可付接口，返回雇员的垫付状态(针对上海社保和上海公积金业务)
     * @param ssMonth 年月
     */
    void enquireFinanceComAccount(String ssMonth);
    void enquireFinanceComAccountTest(String ssMonth, Long paymentComId, Long comAccountId);
}
