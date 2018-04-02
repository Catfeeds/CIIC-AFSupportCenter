package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.custom;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zhangzhiwen on 2018/3/2.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("employSearchExportOpt")
public class employSearchExportOpt {

    @Excel(name = "用工方式", orderNum = "1")
    private  String employWay;

    @Excel(name = "用工属性", orderNum = "2")
    private String employProperty;

    @Excel(name = "公司编码", orderNum = "3")
    private String companyId;

    @Excel(name = "公司名称", orderNum = "4")
    private String title;

    @Excel(name = "雇员编码", orderNum = "5")
    private String employeeId;

    @Excel(name = "雇员姓名", orderNum = "6")
    private  String employeeName;

    @Excel(name = "证件号", width = 20 ,orderNum = "7")
    private String idNum;

    @Excel(name = "客服经理", width = 20 ,orderNum = "8")
    private  String leaderShipName;

    @Excel(name = "客服中心",width = 20, orderNum = "9")
    private String serviceCenter;

    @Excel(name = "公司特殊情况",width = 20, orderNum = "10")
    private  String employSpecial;

    @Excel(name = "档案编号",width = 20, orderNum = "11")
    private  String docNum;

    @Excel(name = "预留档案编号",width = 20, orderNum = "12")
    private  String yuliuDocNum;

    @Excel(name = "用工反馈操作日期",width = 20, orderNum = "13")
    private String employFeedbackOptDate;

    @Excel(name = "调档反馈",width = 20, orderNum = "14")
    private String diaodangFeedback;

    @Excel(name = "调档反馈操作日期",width = 20, orderNum = "15")
    private String diaodangFeedbackOptDate;

}
