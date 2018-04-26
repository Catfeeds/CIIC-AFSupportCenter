package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.EmpTaskStatusBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.PaymentComBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.PaymentEmpBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HfPrintRemittedBookBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPayment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 公积金汇缴支付批次表 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface HfPaymentMapper extends BaseMapper<HfPayment> {

    List<PaymentComBO> getHfPaymentComList(@Param("paymentId") Long paymentId,@Param("paymentMonth") String paymentMonth);

    List<PaymentEmpBO> getHfPaymentEmpList(@Param("paymentId") Long paymentId, @Param("paymentMonth") String paymentMonth);

    List<HfPaymentBo> getFundPays(HfPaymentBo hfPaymentBo);

    Integer getHfPaymentBankId(@Param("paymentId") Long paymentId);

    List<EmpTaskStatusBO> getEmpTaskStatusByPaymentId(@Param("paymentId") Long paymentId);

    List<HfPrintRemittedBookBO> printRemittedBook(@Param("paymentId") Long paymentId, @Param("hfType") Integer hfType);

}
