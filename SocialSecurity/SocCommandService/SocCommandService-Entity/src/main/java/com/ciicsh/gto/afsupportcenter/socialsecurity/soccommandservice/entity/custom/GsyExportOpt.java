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
@ExcelTarget("GsyExportOpt")
public class GsyExportOpt {
    @Excel(name = "雇员编号", orderNum = "1")
    private String employeeId;
    @Excel(name = "姓名", orderNum = "2")
    private String employeeName;
    @Excel(name = "变更类型", orderNum = "3")
    private String changeTypeName;
    @Excel(name = "个人月缴纳基数", width = 20,orderNum = "4")
    private BigDecimal baseAmount;
    @Excel(name = "工伤单位缴费额", width = 20,orderNum = "5")
    private BigDecimal accidentComAmount;
    @Excel(name = "工伤单位补缴", width = 20,orderNum = "6")
    private BigDecimal accidentComRepayAmount;
    @Excel(name = "生育单位缴费额", width = 20,orderNum = "7")
    private BigDecimal maternityComAmount;
    @Excel(name = "生育单位补缴", width = 20,orderNum = "8")
    private BigDecimal maternityComRepayAmount;
    private Long monthEmpChangeId;
    private String ssMonth;
    private Long comAccountId;
    private String comAccountName;
    private Integer changeType;
}
