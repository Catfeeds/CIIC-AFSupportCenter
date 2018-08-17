package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("EmpAccountTemplateXsl")
public class EmpAccountTemplateXsl {
        @Excel(name = "雇员姓名", orderNum = "2")
        private String empName;
        @Excel(name = "身份证号",orderNum = "3")
        private String idNum;
        @Excel(name = "个人账号",orderNum = "1")
        private String empAccount;

        @Excel(name = "月缴额",orderNum = "4")
        private String money;
    }

