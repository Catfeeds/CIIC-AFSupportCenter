package com.ciicsh.gto.afsupportcenter.socjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsPaymentCom;
import com.ciicsh.gto.afsupportcenter.socjob.entity.bo.SsMonthChargeBO;
import com.ciicsh.gto.afsupportcenter.socjob.entity.bo.SsPaymentComBO;
import com.ciicsh.gto.afsupportcenter.socjob.entity.bo.SsPaymentComProxyBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.List;


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

    //查询未支付客户询问财务是否可付 WAY 1
    List<SsPaymentComBO> getPaymentComList(Map map);
    //查询未支付客户询问财务是否可付 WAY 2
    List<SsPaymentComProxyBO> getPaymentComList2(Map map);

    //按照财务服务契约提供雇员级信息
    List<SsMonthChargeBO> getSsPaymentEmpList(Map map);

    SsMonthChargeBO getSsPaymentComIsCompanyEnjoyAdvance(Map map);

    //根据接口返回的数据批量更新月度明细的支付状态
    void updateSsMonthCharge(Map map);

    //根据接口返回的数据批量更新客户的支付状态
    void updateSsPaymentCom(Map map);

    //查询 客户下有多少 不可付的记录
    Integer countBySsEmpPaymentStatus(Map map);
    void updateSsMonthChargeTotalAmount(@Param("comAccountId") long comAccountId, @Param("paymentMonth") String paymentMonth);
}
