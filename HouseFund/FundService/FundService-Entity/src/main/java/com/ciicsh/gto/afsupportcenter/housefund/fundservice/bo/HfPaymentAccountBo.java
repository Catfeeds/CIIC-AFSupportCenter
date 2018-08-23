package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfPaymentAccountBo extends HfPaymentAccount {

    private Long paymentAccountId;
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
     * 缴费银行：15 徐汇—X、16 西郊—C、17东方路—P、18 卢湾—L、0 黄浦—H
     */
    private String paymentBankValue;

    /**
     * 缴费银行编号：15 徐汇—X、16 西郊—C、17东方路—P、18 卢湾—L、0 黄浦—H
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

    private String companyId;
    private String companyName;
    private String paymentMonthValue;

    // 公积金账户数量
    private int fCount;
    //  支付状态: 1 ,可付(默认) 2,送审 3 汇缴(已申请到财务部 ) 4 财务部批退 5,财务部批准 6 出票 7 回单
    private int paymentState;
    // 企业公积金账户类型 1 大库 2 外包 3 独立户
    private int hfAccountType;
    // 当月汇缴金额
    private BigDecimal remittedAmount;
    // 补缴金额
    private BigDecimal repairAmount;
    // 汇缴人数 当月总人数
    private BigDecimal remittedCountEmp;
    // 公司名称
    private String title;
    private Long paymentId;
    //服务中心
    private int serviceCenterValue;
    //客服经理
    private String leaderShipName;
}
