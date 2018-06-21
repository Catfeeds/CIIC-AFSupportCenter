package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * Created by LiYueLong on 2018/6/14.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("ukeySearchExportOpt")
public class ukeySearchExportOpt {

    @Excel(name = "材料签收日期", orderNum = "0")
    private LocalDate materialReceiveDate;

    @Excel(name = "材料送办日期", orderNum = "1")
    private LocalDate materialDeliveryDate;

    @Excel(name = "材料反馈日期", orderNum = "2")
    private LocalDate materialFeedbackDate;

    @Excel(name = "公司名称", orderNum = "3")
    private String companyName;

    @Excel(name = "组织机构代码", orderNum = "4")
    private String organizationCode;

    @Excel(name = "到期日期", orderNum = "5")
    private LocalDate dueDate;

    @Excel(name = "注销日期", orderNum = "6")
    private LocalDate logoutDate;

    @Excel(name = "类别", orderNum = "7")
    private String keyType;

    @Excel(name = "编号", orderNum = "8")
    private String keyCode;

    @Excel(name = "密码", orderNum = "9")
    private String keyPwd;

    @Excel(name = "序列号", orderNum = "10")
    private String keySeq;

    @Excel(name = "收费标准", orderNum = "11")
    private String keyFee;
}
