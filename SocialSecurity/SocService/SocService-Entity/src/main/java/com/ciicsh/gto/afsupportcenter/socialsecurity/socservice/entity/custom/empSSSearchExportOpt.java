package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by linhui on 2018/2/7.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("empSSSearchExportOpt")
public class empSSSearchExportOpt {

    private String companyId;

    private Long  empArchiveId;
    @Excel(name = "雇员编号", orderNum = "1")
    private String employeeId;
    @Excel(name = "雇员姓名", orderNum = "2")
    private String employeeName;
    @Excel(name = "证件号", width = 20 ,orderNum = "3")
    private String idNum;
    @Excel(name = "企业社保账号",width = 20, orderNum = "4")
    private String ssAccount;
    @Excel(name = "社保状态", orderNum = "5", replace = {"已办_1","已做_2","转出_3"})
    private Integer archiveTaskStatus;
    @Excel(name = "社保账户类型", orderNum = "6", replace = {"中智大库_1","中智外包_2","独立户_3"})
    private Integer ssAccountType;
    @Excel(name = "结算区县", orderNum = "7")
    private String settlementArea;
    @Excel(name = "客户名称", width = 30 ,orderNum = "8")
    private String title;
    @Excel(name = "社保账户名称",width = 30, orderNum = "9")
    private String comAccountName;
    @Excel(name = "起缴月份", orderNum = "10")
    private String startMonth;
    @Excel(name = "截止月份", orderNum = "11")
    private String endMonth;
    @Excel(name = "办理月份", orderNum = "12")
    private String ssMonth;
    @Excel(name = "入职日期", orderNum = "13")
    private String inDate;
}
