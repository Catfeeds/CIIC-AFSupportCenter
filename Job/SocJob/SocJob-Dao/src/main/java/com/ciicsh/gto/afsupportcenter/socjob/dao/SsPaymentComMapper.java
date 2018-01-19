package com.ciicsh.gto.afsupportcenter.socjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsPaymentCom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 本地社保应付金额交易记录主表,每月1号生成此表记录，用户也可以人工生成此表记录 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface SsPaymentComMapper extends BaseMapper<SsPaymentCom> {

    /**
     * 是否存在支付信息
     * @param comAccountId 企业社保账户
     * @param paymentMonth 支付年月
     * @return 判断是否存在
     */
    Integer ifExistPayment(@Param("comAccountId") long comAccountId, @Param("paymentMonth") String paymentMonth);
}
