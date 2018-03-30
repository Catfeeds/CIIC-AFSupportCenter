package com.ciicsh.gto.afsupportcenter.fundjob.service;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.fundjob.entity.HfPayment;

public interface HfPaymentService extends IService<HfPayment> {

    /**
     * 判断雇员是否垫付、是否可付接口，返回雇员的垫付状态(针对上海社保和上海公积金业务)
     * @param ssMonth 年月
     */
    void enquireFinanceComAccount(String ssMonth);
    void createPaymentAccount();
}
