package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zhangzhiwen on 2018/3/2.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("resignSearchExportOpt")
public class resignSearchExportOpt {

    @Excel(name = "退工成功日期", orderNum = "1")
    private String jobCentreFeedbackDate;

    @Excel(name = "退工原因", orderNum = "2")
    private String outReason;

    @Excel(name = "用工属性", orderNum = "3")
    private String employProperty;

    @Excel(name = "办理类型", orderNum = "4")
    private String handleType;

    @Excel(name = "雇员编号", orderNum = "5")
    private String employeeId;

    @Excel(name = "雇员姓名", orderNum = "6")
    private String employeeName;

    @Excel(name = "证件号码", orderNum = "7")
    private String idNum;

    @Excel(name = "客户编号", orderNum = "8")
    private String companyId;

    @Excel(name = "公司名称", orderNum = "9")
    private String title;

    @Excel(name = "客服经理", orderNum = "10")
    private String leaderShipName;

    @Excel(name = "客服中心", orderNum = "11")
    private String serviceCenter;

    @Excel(name = "档案编号", orderNum = "12")
    private String docNum;

    @Excel(name = "预留档案编号", orderNum = "13")
    private String yuliuDocNum;

    @Excel(name = "存档地", orderNum = "14")
    private String archivePlace;

    @Excel(name = "实际录用日期", orderNum = "15")
    private String employDate;

    @Excel(name = "退工日期", orderNum = "16")
    private  String refuseDate;

    @Excel(name = "退工反馈", orderNum = "17")
    private  String refuseFeedback;

    @Excel(name = "用工反馈", orderNum = "18")
    private  String employFeedback;

    @Excel(name = "公司特殊情况", orderNum = "19")
    private  String refuseSpecial;

    @Excel(name = "是否翻牌",width = 20, orderNum = "20")
    private String changeCompany;

    private Integer employCode;

}
