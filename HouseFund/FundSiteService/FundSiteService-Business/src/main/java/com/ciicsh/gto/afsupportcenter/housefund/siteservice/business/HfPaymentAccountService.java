package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfPaymentAccount;

public interface HfPaymentAccountService extends IService<HfPaymentAccount> {

    boolean saveRejectResult(Long pkId, String remark, Integer payStatus);
}
