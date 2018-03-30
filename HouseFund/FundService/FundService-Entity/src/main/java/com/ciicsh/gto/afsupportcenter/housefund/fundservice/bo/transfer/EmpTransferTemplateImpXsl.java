package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("EmpTransferTemplateImpXsl")
public class EmpTransferTemplateImpXsl {
        @Excel(name = "雇员编号", orderNum = "1")
        private String empName;
        @Excel(name = "雇员姓名",orderNum = "2")
        private String idNum;
        @Excel(name = "回单日期",orderNum = "3")
        private String empAccount;
    }

