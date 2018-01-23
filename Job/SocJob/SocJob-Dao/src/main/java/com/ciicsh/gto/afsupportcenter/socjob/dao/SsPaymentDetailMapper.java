package com.ciicsh.gto.afsupportcenter.socjob.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsPaymentDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 本地社保应付金额交易记录明细表 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface SsPaymentDetailMapper extends BaseMapper<SsPaymentDetail> {
    /**
     * 是否存在支付信息
     * @param comAccountId 企业社保账户
     * @param paymentMonth 支付年月
     * @return 删除本地社保应付金额交易记录明细信息
     */
    Integer delPaymentDetail(@Param("comAccountId") long comAccountId, @Param("paymentMonth") String paymentMonth);
}
