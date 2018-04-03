package com.ciicsh.gto.afsupportcenter.fundjob.service;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.fundjob.entity.HfPayment;
import org.springframework.web.bind.annotation.RequestParam;

public interface HfPaymentService extends IService<HfPayment> {

    /**
     * 判断雇员是否垫付、是否可付接口，返回雇员的垫付状态(针对上海社保和上海公积金业务)
     */
    void enquireFinanceComAccount();
    void enquireFinanceComAccountTest(String ssMonth, Long comAccountId);
    void createPaymentAccount();
}
