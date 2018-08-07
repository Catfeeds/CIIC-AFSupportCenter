package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;


/**
 * Created by houwanhua on 2018/1/24.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PensionOpt{
    @Excel(name = "单位缴费额", orderNum = "1")
    private BigDecimal comAccount;
    @Excel(name = "个人缴费额", orderNum = "2",type=10)
    private BigDecimal empAccount;
    @Excel(name = "单位补缴", orderNum = "3",type=10)
    private BigDecimal comRepayAccount;
    @Excel(name = "个人补缴", orderNum = "4",type=10)
    private BigDecimal empRepayAccount;
    @Excel(name = "一次性支付", orderNum = "5",type=10)
    private BigDecimal oncePaymentAccount;
}
