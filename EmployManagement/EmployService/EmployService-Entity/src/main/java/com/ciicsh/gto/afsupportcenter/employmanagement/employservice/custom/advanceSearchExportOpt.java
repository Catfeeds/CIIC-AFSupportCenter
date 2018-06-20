package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * Created by LiYueLong on 2018/5/29.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("advanceSearchExportOpt")
public class advanceSearchExportOpt {

    @Excel(name = "状态", orderNum = "0")
    private String struts;

    @Excel(name = "预留档案类型", orderNum = "1")
    private String reservedArchiveType;

    @Excel(name = "预留档案编号", orderNum = "2")
    private Integer reservedArchiveNo;

    @Excel(name = "雇员姓名", orderNum = "3")
    private String employeeName;

    @Excel(name = "身份证号", orderNum = "4")
    private String employeeIdcardNo;

    @Excel(name = "入库日期", orderNum = "5")
    private LocalDate enteringDate;

    @Excel(name = "档案来源", orderNum = "6")
    private String archiveSource;

    @Excel(name = "存档地", orderNum = "7")
    private String archivalPlace;

    @Excel(name = "操作人", orderNum = "8")
    private String createdBy;

    @Excel(name = "备注", orderNum = "9")
    private String remark;
}
