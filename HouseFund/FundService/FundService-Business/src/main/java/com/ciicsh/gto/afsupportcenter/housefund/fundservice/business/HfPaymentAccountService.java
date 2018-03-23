package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentAccountBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentAccount;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

public interface HfPaymentAccountService extends IService<HfPaymentAccount> {

    boolean updatePaymentInfo(Long pkId, String remark, Integer payStatus);

    /**
     * 获得公积金汇缴支付列表
     * @param pageInfo
     * @return
     */
    PageRows<HfPaymentAccountBo> getMakePayLists(PageInfo pageInfo);
}
