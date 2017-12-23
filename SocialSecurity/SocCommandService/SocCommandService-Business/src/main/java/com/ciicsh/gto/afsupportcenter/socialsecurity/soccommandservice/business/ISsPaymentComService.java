package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsPaymentComDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPaymentCom;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;

/**
 * <p>
 * 本地社保应付金额交易记录主表,每月1号生成此表记录，用户也可以人工生成此表记录 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface ISsPaymentComService extends IService<SsPaymentCom> {
    /**
     * <p>Description: 查询社保支付-企业账户</p>
     *
     * @author wengxk
     * @date 2017-12-21
     * @param pageInfo 翻页检索条件
     * @return  PageRows<SsPaymentComDTO>
     */
    PageRows<SsPaymentComDTO> paymentComQuery(PageInfo pageInfo);

    /**
     * <p>Description: 保存调整结果</p>
     *
     * @author wengxk
     * @date 2017-12-23
     * @param ssPaymentComDTO 翻页检索条件
     * @return  JsonResult<>
     */
    JsonResult<String> saveAdjustment(SsPaymentComDTO ssPaymentComDTO);
}
