package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsPaymentComBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsPaymentCom;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
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
     * @param ssPaymentComBO 企业账户
     * @return  PageRows<SsStatement>
     */
    List<SsPaymentComBO> paymentComQuery(SsPaymentComBO ssPaymentComBO);
    /**
     * <p>Description: 根据批次检索</p>
     *
     * @author wengxk
     * @date 2017-12-27
     * @param paymentId 批次ID
     * @return   List<SsPaymentCom>
     */
    List<SsPaymentCom> getPaymentComByPaymentId(Long paymentId);

    /**
     * <p>Description: 根据ID获取信息及扩展信息</p>
     *
     * @author wengxk
     * @date 2018-01-02
     * @param paymentComId 客户费用明细ID
     * @return   SsPaymentComBO
     */
    SsPaymentComBO getPaymentComBoByPaymentId(Long paymentComId);

    /**
     * <p>Description: 去重获取一个批次下的所有客户明细的社保账号</p>
     *
     * @author wengxk
     * @date 2018-01-04
     * @param paymentId 批次ID
     * @return   List<String>
     */
    List<Long> getAccountIdByPaymentId(Long paymentId);

    /**
     * <p>Description: 获取指定社保账户、支付年月条件下不在指定批次ID下的客户费用明细条数</p>
     *
     * @author wengxk
     * @date 2018-01-04
     * @param ssPaymentCom 客户费用明细查询条件
     * @return   Integer
     */
    Integer getPaymentComCountNotInPayment(SsPaymentCom ssPaymentCom);

    BigDecimal getExtraAmountBySsAccount(@Param("paymentMonth") String paymentMonth, @Param("ssAccount")String ssAccount);

    Integer ssCanDeal(@Param("paymentMonth")String paymentMonth,@Param("ssAccountType")Integer ssAccountType);
}
