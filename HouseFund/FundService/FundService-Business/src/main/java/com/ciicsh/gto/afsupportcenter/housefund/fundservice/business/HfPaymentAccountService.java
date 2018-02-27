package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentAccount;

public interface HfPaymentAccountService extends IService<HfPaymentAccount> {

    boolean saveRejectResult(Long pkId, String remark, Integer payStatus);
}
