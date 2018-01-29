package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Created by houwanhua on 2018/1/29.
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("YysExportOpt")
public class YysExportOpt {
    @Excel(name = "雇员编号", orderNum = "1")
    private String employeeId;
    @Excel(name = "姓名", orderNum = "2")
    private String employeeName;
    @Excel(name = "变更类型", orderNum = "3")
    private String changeTypeName;
    @Excel(name = "个人月缴纳基数",width = 20, orderNum = "4")
    private BigDecimal baseAmount;
    @Excel(name = "养老单位缴费额",width = 20, orderNum = "5")
    private BigDecimal pensionComAmount;
    @Excel(name = "养老个人缴费额",width = 20, orderNum = "6")
    private BigDecimal pensionEmpAmount;
    @Excel(name = "养老单位补缴", width = 20,orderNum = "7")
    private BigDecimal pensionComRepayAmount;
    @Excel(name = "养老个人补缴", width = 20,orderNum = "8")
    private BigDecimal pensionEmpRepayAmount;
    @Excel(name = "养老一次性支付", width = 20,orderNum = "9")
    private BigDecimal pensionOnePayment;
    @Excel(name = "医疗单位缴费额", width = 20,orderNum = "10")
    private BigDecimal medicalComAmount;
    @Excel(name = "医疗个人缴费额", width = 20,orderNum = "11")
    private BigDecimal medicalEmpAmount;
    @Excel(name = "医疗单位补缴", width = 20,orderNum = "12")
    private BigDecimal medicalComRepayAmount;
    @Excel(name = "医疗个人补缴", width = 20,orderNum = "13")
    private BigDecimal medicalEmpRepayAmount;
    @Excel(name = "失业单位缴费额", width = 20,orderNum = "14")
    private BigDecimal unemploymentComAmount;
    @Excel(name = "失业个人缴费额", width = 20,orderNum = "15")
    private BigDecimal unemploymentEmpAmount;
    @Excel(name = "失业单位补缴", width = 20,orderNum = "16")
    private BigDecimal unemploymentComRepayAmount;
    @Excel(name = "失业个人补缴", width = 20,orderNum = "17")
    private BigDecimal unemploymentEmpRepayAmount;
    private Long monthEmpChangeId;
    private String ssMonth;
    private Long comAccountId;
    private String comAccountName;
    private Integer changeType;
}
