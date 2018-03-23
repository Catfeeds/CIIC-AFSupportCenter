package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.custom;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * Created by zhangzhiwen on 2018/3/4.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("archiveSearchExportOpt")
public class archiveSearchExportOpt {

    @Excel(name = "用工方式", orderNum = "1")
    private  String employWay;

    @Excel(name = "用工属性", orderNum = "2")
    private String employProperty;

    @Excel(name = "职介反馈日期", orderNum = "3")
    private String jobCentreFeedbackDate;

    @Excel(name = "退工原因", orderNum = "4")
    private String outReason;

    @Excel(name = "办理类型", orderNum = "5")
    private String handleType;

    @Excel(name = "雇员编码", orderNum = "6")
    private String employeeId;

    @Excel(name = "雇员姓名", orderNum = "7")
    private  String employeeName;

    @Excel(name = "证件号码", orderNum = "8")
    private String idNum;

    @Excel(name = "客户编号", orderNum = "9")
    private String companyId;

    @Excel(name = "公司名称", orderNum = "10")
    private String title;

    @Excel(name = "客服经理", orderNum = "11")
    private String serviceManager;

    @Excel(name = "客服中心", orderNum = "12")
    private String serviceCenter;

    @Excel(name = "档案编号", orderNum = "13")
    private String docNum;

    @Excel(name = "预留档案编号", orderNum = "14")
    private String yuliuDocNum;

    @Excel(name = "存档地", orderNum = "15")
    private String archivePlace;

    @Excel(name = "实际录用日期", orderNum = "16")
    private String employDate;

    @Excel(name = "退工日期", orderNum = "17")
    private  String outDate;

    @Excel(name = "出库日期", orderNum = "18")
    private  String storageOutDate;

    @Excel(name = "用工反馈", orderNum = "19")
    private String employFeedback;
    @Excel(name = "退工反馈", orderNum = "20")
    private String resignFeedback1;
    @Excel(name = "退工反馈2", orderNum = "21")
    private  String resignFeedback2;
    @Excel(name = "退档日期", orderNum = "22")
    private String returnDocDate;
    @Excel(name = "退工送办日期", orderNum = "23")
    private LocalDate resignHandleDate;
    @Excel(name = "公司特殊情况",width = 20, orderNum = "24")
    private  String companySpecialCase;
}
