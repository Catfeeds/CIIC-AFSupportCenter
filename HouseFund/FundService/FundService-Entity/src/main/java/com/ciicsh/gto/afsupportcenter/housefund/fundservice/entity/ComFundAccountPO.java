package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;

/**
 * 企业账户信息
 */
public class ComFundAccountPO
{

    /**
     * 企业公积金账户Id
     */
    private int comAccountId;

    /**
     * 企业账户名称
     */
    private String comAccountName;

    /**
     * 1 基本公积金、2 补充公积金
     */
    private Byte hfType;

    /**
     * 付款方式:
     1 自付（客户自己汇缴给银行，雇员由中智办理）
     2 我司付款（客户预付）
     3 垫付
     */
    private Byte paymentWay;

    /**
     * 1 大库 2 外包 3 独立户
     */
    private Byte accountType;

    /**
     * 每月关账日, 1-31
     */
    private Byte closeDay;


    /**
     * 公积金企业U盾代管情况
     * 0-没有 1-有(客户自办)  2-有(中智代办)
     */
    private Byte ukeyStore;

    /**
     * 缴费支行编号
     * 1 徐汇—X、2 西郊—C、3 东方路—P、4 卢湾—L、5 黄浦—H
     *
     */
    private Byte paymentBank;

    /**
     * 账户备注说明
     */
    private String remark;

    /**
     * 基本公积金账户编号
     */
    private String comAccount;


    /**
     * 公积金缴费起始年月
     */
    private String payStartMonth;

    /**
     * 公积金缴费截止年月
     */
    private String payEndMonth;


    /**
     * 公积金账号是否属于临时保管
     * 1-临时保管状态 0-非临时保管状态
     *
     * */
    private Byte accountTempStore;
}
