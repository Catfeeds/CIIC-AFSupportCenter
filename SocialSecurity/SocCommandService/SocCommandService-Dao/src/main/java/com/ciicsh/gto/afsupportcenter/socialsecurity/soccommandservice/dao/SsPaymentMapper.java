package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsPaymentSrarchBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.PayapplyCompanyProxyDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.PayapplyEmployeeProxyDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPayment;
import org.apache.ibatis.annotations.Param;

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
     * @param ssPaymentBO 支付批次
     * @return List<SsPaymentBO>
     * @author wengxk
     * @date 2017-12-22
     */
    List<SsPaymentBO> paymentQuery(SsPaymentBO ssPaymentBO);

    /**
     * <p>Description: 按企业社保账户分类和状态检索批次</p>
     *
     * @param ssPaymentSrarchBO 查询条件
     * @return List<SsPaymentBO>
     * @author wengxk
     * @date 2017-12-22
     */
    List<SsPaymentBO> searchPaymentByAccountTypeAndState(SsPaymentSrarchBO ssPaymentSrarchBO);

    /**
     * <p></p>
     *
     * @author linhui
     * @date 2018-01-11
     */
    List<PayapplyCompanyProxyDTO> getPaymentComList(@Param("paymentId") Long paymentId, @Param("paymentMonth") String
        paymentMonth);

    List<PayapplyEmployeeProxyDTO> getPaymentEmpList(@Param("paymentId") Long paymentId, @Param("paymentMonth")
        String paymentMonth);
}
