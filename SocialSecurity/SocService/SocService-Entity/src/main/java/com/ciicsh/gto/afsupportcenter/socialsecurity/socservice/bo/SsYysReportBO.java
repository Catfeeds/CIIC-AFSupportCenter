package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("SsYysReportBO")
public class SsYysReportBO {
    @Excel(name = "雇员编号", width = 20, orderNum = "1")
    private String employeeId;
    @Excel(name = "姓名", width = 20, orderNum = "2")
    private String employeeName;
    @Excel(name = "变更类型", orderNum = "3")
    private String changeTypeName;
    @Excel(name = "个人月缴纳基数",width = 20, orderNum = "4", type=10)
    private BigDecimal baseAmount;
    @Excel(name = "养老单位缴费额",width = 20, orderNum = "5", type=10)
    private BigDecimal comAmountPension;
    @Excel(name = "养老个人缴费额",width = 20, orderNum = "6", type=10)
    private BigDecimal empAmountPension;
    @Excel(name = "养老单位补缴", width = 20,orderNum = "7", type=10)
    private BigDecimal comCompensatedAmountPension;
    @Excel(name = "养老个人补缴", width = 20,orderNum = "8", type=10)
    private BigDecimal empCompensatedAmountPension;
    @Excel(name = "养老一次性支付", width = 20,orderNum = "9", type=10)
    private BigDecimal onePaymentPension;
    @Excel(name = "医疗单位缴费额", width = 20,orderNum = "10", type=10)
    private BigDecimal comAmountMedical;
    @Excel(name = "医疗个人缴费额", width = 20,orderNum = "11", type=10)
    private BigDecimal empAmountMedical;
    @Excel(name = "医疗单位补缴", width = 20,orderNum = "12", type=10)
    private BigDecimal comCompensatedAmountMedical;
    @Excel(name = "医疗个人补缴", width = 20,orderNum = "13", type=10)
    private BigDecimal empCompensatedAmountMedical;
    @Excel(name = "失业单位缴费额", width = 20,orderNum = "14", type=10)
    private BigDecimal comAmountUnemployment;
    @Excel(name = "失业个人缴费额", width = 20,orderNum = "15", type=10)
    private BigDecimal empAmountUnemployment;
    @Excel(name = "失业单位补缴", width = 20,orderNum = "16", type=10)
    private BigDecimal comCompensatedAmountUnemployment;
    @Excel(name = "失业个人补缴", width = 20,orderNum = "17", type=10)
    private BigDecimal empCompensatedAmountUnemployment;
    @Excel(name = "社保登记码", width = 20, orderNum = "18")
    private String ssAccount;
    @Excel(name = "客户编号", width = 25, orderNum = "19")
    private String companyId;
    @Excel(name = "服务中心", width = 30, orderNum = "20")
    private String serviceCenter;


    private Long ssSerial;
    private String comAccountName;
    private Integer category;
}
