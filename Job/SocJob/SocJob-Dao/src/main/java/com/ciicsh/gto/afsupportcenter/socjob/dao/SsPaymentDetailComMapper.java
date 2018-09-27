package com.ciicsh.gto.afsupportcenter.socjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsPaymentDetailCom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SsPaymentDetailComMapper extends BaseMapper<SsPaymentDetailCom> {
    /**
     * 是否存在支付信息
     * @param comAccountId 企业社保账户
     * @param paymentMonth 支付年月
     * @return 删除本地社保应付金额交易记录明细信息
     */
    Integer delPaymentDetailCom(@Param("comAccountId") long comAccountId, @Param("paymentMonth") String paymentMonth);
}
