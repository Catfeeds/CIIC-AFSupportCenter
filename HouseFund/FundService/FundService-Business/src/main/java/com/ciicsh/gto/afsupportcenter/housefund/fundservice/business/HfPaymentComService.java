package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentComBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentCom;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

public interface HfPaymentComService extends IService<HfPaymentCom> {

    /**
     * 获得公积金汇缴支付详细操作数据
     * @param pageInfo
     * @return
     */
    PageRows<HfPaymentComBo> getFundPaysDetailOperationData(PageInfo pageInfo);
}
