package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsPaymentDetail;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 本地社保应付金额交易记录明细表 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsPaymentDetailService extends IService<SsPaymentDetail> {

    /**
     * <p>Description: 付款通知查询</p>
     *
     * @author wengxk
     * @date 2018-01-02
     * @param ssPaymentDetail 客户费用明细ID
     * @return  SsPaymentDetail
     */
    List<SsPaymentDetail> paymentDetailQuery(SsPaymentDetail ssPaymentDetail);
}
