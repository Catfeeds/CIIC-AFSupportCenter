package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by houwanhua on 2018/1/24.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("YysmxOpt")
public class YysmxOpt {
    @Excel(name = "结算情况", orderNum = "1")
    private String settlement;
    @Excel(name = "变更日期", format = "yyyyMMdd",orderNum = "2")
    private Date chageDate;
    @Excel(name = "工号",orderNum = "3")
    private Integer jobNumber;
    @Excel(name = "姓名",orderNum = "4")
    private String empName;
    @Excel(name = "变更类型",orderNum = "5")
    private String changeType;
    @Excel(name = "个人月缴费基数",orderNum = "6")
    private BigDecimal personalPaymentBase;

    @ExcelEntity(name = "养老保险",show = true)
    private PensionOpt pensionOpt;

    @ExcelEntity(name = "医疗保险",show = true)
    private MedicalOpt medicalOpt;

    @ExcelEntity(name = "失业保险",show = true)
    private UnemploymentOpt unemploymentOpt;
}
