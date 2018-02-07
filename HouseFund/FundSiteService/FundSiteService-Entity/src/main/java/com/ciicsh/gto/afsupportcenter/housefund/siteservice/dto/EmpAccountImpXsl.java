package com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("EmpAccountImpXsl")
public class EmpAccountImpXsl {
        @Excel(name = "雇员姓名", orderNum = "1")
        private String empName;
        @Excel(name = "身份证号",orderNum = "2")
        private String idNum;
        @Excel(name = "公积金账号",orderNum = "3")
        private String empAccount;
    }

