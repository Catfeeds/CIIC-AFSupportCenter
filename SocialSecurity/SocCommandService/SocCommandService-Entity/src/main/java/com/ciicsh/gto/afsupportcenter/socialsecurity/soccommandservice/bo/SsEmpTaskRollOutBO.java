package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.math.BigDecimal;

@ExcelTarget("ssEmpTaskRollOutBO")
public class SsEmpTaskRollOutBO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Excel(name = "姓名", orderNum = "1", width = 15)
    private String employeeName;
    @Excel(name = "身份证号码", width = 25)
    private String idNum;
    @Excel(name = "月平均工资收入", orderNum = "2", width = 25)
    private BigDecimal salary;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
