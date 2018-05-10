package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentComBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.HfFundPayCreatePaymentAccountPara;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentCom;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;

import java.util.List;

public interface HfPaymentComService extends IService<HfPaymentCom> {

    JsonResult createPaymentCom(HfFundPayCreatePaymentAccountPara hfFundPayCreatePaymentAccountPara);

    /**
     * 编辑页面添加支付详细数据
     * @param hfFundPayCreatePaymentAccountPara 添加的支付批次详情
     * @return 添加结果
     */
    JsonResult createPaymentComById(HfFundPayCreatePaymentAccountPara hfFundPayCreatePaymentAccountPara);
}
