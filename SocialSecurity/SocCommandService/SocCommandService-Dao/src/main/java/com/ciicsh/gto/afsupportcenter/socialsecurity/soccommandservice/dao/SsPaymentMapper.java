package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsPaymentDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsPaymentSrarchDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPayment;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 社保支付批次 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsPaymentMapper extends BaseMapper<SsPayment> {

    /**
     * <p>Description: 查询社保支付-支付批次</p>
     *
     * @author wengxk
     * @date 2017-12-22
     * @param ssPaymentDTO 支付批次
     * @return  List<SsPaymentDTO>
     */
    List<SsPaymentDTO> paymentQuery(SsPaymentDTO ssPaymentDTO);

    /**
     * <p>Description: 按企业社保账户分类和状态检索批次</p>
     *
     * @author wengxk
     * @date 2017-12-22
     * @param ssPaymentSrarchDTO 查询条件
     * @return  List<SsPaymentDTO>
     */
    List<SsPaymentDTO> searchPaymentByAccountTypeAndState(SsPaymentSrarchDTO ssPaymentSrarchDTO);
}
