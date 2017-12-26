package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsPaymentComDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPaymentCom;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 本地社保应付金额交易记录主表,每月1号生成此表记录，用户也可以人工生成此表记录 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsPaymentComMapper extends BaseMapper<SsPaymentCom> {
    /**
     * <p>Description: 查询社保支付-企业账户</p>
     *
     * @author wengxk
     * @date 2017-12-21
     * @param ssPaymentComDTO 企业账户
     * @return  PageRows<SsStatement>
     */
    List<SsPaymentComDTO> paymentComQuery(SsPaymentComDTO ssPaymentComDTO);
}
