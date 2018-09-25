package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zhangzhiwen on 2018/9/20.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("IndependentExportOpt")
public class IndependentExportOpt {

    @Excel(name = "公司编码", orderNum = "1")
    private String companyId;

    @Excel(name = "公司名称", orderNum = "2")
    private String title;

    @Excel(name = "组织机构代码", orderNum = "3")
    private String organizationCode;

    @Excel(name = "雇员编码", orderNum = "4")
    private  String employeeId;

    @Excel(name = "雇员姓名", orderNum = "5")
    private  String employeeName;

    @Excel(name = "证件号", width = 20 ,orderNum = "6")
    private String idNum;

    @Excel(name = "人员性质", width = 20 ,orderNum = "7")
    private String employeeNature;

    @Excel(name = "服务中心",width = 20, orderNum = "8")
    private String serviceCenter;

    @Excel(name = "Team",width = 20, orderNum = "9")
    private String  leaderShipName;
}
