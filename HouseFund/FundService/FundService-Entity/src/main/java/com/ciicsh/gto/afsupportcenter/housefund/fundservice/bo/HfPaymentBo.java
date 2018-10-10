package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;


import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPayment;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfPaymentBo extends HfPayment {

    /**
     * 支付状态: 3 ,可付(默认)   4,申请中  5,内部审批批退 6,已申请到财务部  7,财务部批退  8,财务部支付成功
     */
    private String paymentStateValue;

    /**
     * 1 中智大库  2 中智外包 3 独立户
     */
    private String accountTypeValue;

    /**
     * 制单日期
     */
    private String createPaymentDateString;

    /**
     * 财务支付日期
     */
    private String financePaymentDateString;
    private String sendAuditDateString;
    private String requestDateString;
    private String companyId;
    private String orderParams;
}
