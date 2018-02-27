package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Created by houwanhua on 2018/1/29.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("StatementExportOpt")
public class StatementExportOpt {
    @Excel(name = "社保导入文件",width = 20, orderNum = "1")
    private String impFileName;
    @Excel(name = "社保月份", orderNum = "2")
    private String ssMonth;
    @Excel(name = "企业社保账户",width = 20, orderNum = "3")
    private String comAccountName;
    @Excel(name = "下载月度变更",width = 20, orderNum = "4")
    private String downLoadMonthChange;
    @Excel(name = "变更汇总表类型",width = 20, orderNum = "5")
    private String monthChangeType;
    @Excel(name = "差异数（按雇员）",width = 20, orderNum = "6")
    private String diffSumByEmp;
    @Excel(name = "对账操作人",width = 20, orderNum = "7")
    private String statementUserId;
    @Excel(name = "最近对账时间",width = 20,format = "yyyy-MM-dd", orderNum = "8")
    private LocalDateTime statementTime;
    private Long comAccountId;
    private String impFileType;
}
