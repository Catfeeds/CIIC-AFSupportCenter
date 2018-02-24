package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom;


import cn.afterturn.easypoi.excel.annotation.Excel;
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
@ExcelTarget("GsymxOpt")
public class GsymxOpt {
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
    @Excel(name = "月缴费基数",orderNum = "6")
    private BigDecimal paymentBase;
    @Excel(name = "工伤缴费金额",orderNum = "7")
    private BigDecimal accidentAmount;
    @Excel(name = "工伤补缴",orderNum = "8")
    private BigDecimal accidentRepayAmount;
    @Excel(name = "生育缴费金额",orderNum = "9")
    private BigDecimal maternityAmount;
    @Excel(name = "生育补缴",orderNum = "10")
    private BigDecimal maternityRepayAmount;
}
