package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("SsPayAmountImpXsl")
public class SsPayAmountImpXsl {


   // @Excel(name = "支付批次号", orderNum = "1")
    private String paymentBatchNum;
    @Excel(name = "支付年月",orderNum = "1")
    private String paymentMonth;
    @Excel(name = "客户编号", orderNum = "2")
    private String companyId;
    @Excel(name = "申请支付总金额",orderNum = "3")
    private BigDecimal totalApplicationAmount;

}
