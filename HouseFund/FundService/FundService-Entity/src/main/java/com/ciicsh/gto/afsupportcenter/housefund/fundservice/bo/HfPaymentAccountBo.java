package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfPaymentAccountBo extends HfPaymentAccount {

    /**
     * 企业账户名称
     */
    private String comAccountName;

    /**
     * 1 基本公积金、2 补充公积金
     */
    private String hfTypeName;

    /**
     * 支付状态: 1,未到帐  2,无需支付  3,可付  4,申请中  5,内部审批批退  6,已申请到财务部  7,财务部批退  8,财务部支付成功
     */
    private String paymentStateValue;

    /**
     * 1 中智大库  2 中智外包 3 独立户
     */
    private String accountTypeValue;

    /**
     * 公积金账户类型编号：1 中智大库  2 中智外包 3 独立户
     */
    private String fundAccountType;

    /**
     * 缴费银行：1 徐汇—X、2 西郊—C、3 东方路—P、4 卢湾—L、5 黄浦—H
     */
    private String paymentBankValue;

    /**
     * 缴费银行编号：1 徐汇—X、2 西郊—C、3 东方路—P、4 卢湾—L、5 黄浦—H
     */
    private Integer paymentBank;

    /**
     * 汇总金额
     */
    private BigDecimal sumAmount;

    /**
     * 补缴金额
     */
    private BigDecimal payInBackAmount;

}
