package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPayment;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;

public interface HfPaymentService {

    JsonResult<String> doReviewdePass(HfPayment hfPayment);

    /**
     * 获得公积金汇缴支付列表
     * @param pageInfo
     * @return
     */
    PageRows<HfPaymentBo> getFundPays(PageInfo pageInfo);

}
