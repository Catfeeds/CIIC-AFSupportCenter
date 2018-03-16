package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.math.BigDecimal;

@ExcelTarget("NewStatementExcelItemDTO")
public class NewStatementExcelItemDTO {
    @Excel(name = "个人账号", orderNum = "1")
    private String personalAccount;
    @Excel(name = "姓名", orderNum = "2")
    private String empName;
    @Excel(name = "身份证号码",orderNum = "3")
    private String idNum;
    @Excel(name = "月缴额",orderNum = "4")
    private BigDecimal monthlyAmount;


    public String getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(String personalAccount) {
        this.personalAccount = personalAccount;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public BigDecimal getMonthlyAmount() {
        return monthlyAmount;
    }

    public void setMonthlyAmount(BigDecimal monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }
}
