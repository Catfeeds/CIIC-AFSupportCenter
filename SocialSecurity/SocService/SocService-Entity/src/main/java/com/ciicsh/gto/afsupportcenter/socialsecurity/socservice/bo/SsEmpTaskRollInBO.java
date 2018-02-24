package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.math.BigDecimal;

@ExcelTarget("ssEmpTaskRollInBO")
public class SsEmpTaskRollInBO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Excel(name = "序号", width = 20)
    private String ssSerial;
    @Excel(name = "姓名", orderNum = "2", width = 15)
    private String employeeName;
    @Excel(name = "身份证号码", orderNum = "1", width = 25)
    private String idNum;
    @Excel(name = "缴费起始年月", orderNum = "3", width = 20)
    private String startMonth;
    @Excel(name = "月平均工资收入", orderNum = "4", width = 25)
    private BigDecimal salary;

    public String getSsSerial() {
        return ssSerial;
    }

    public void setSsSerial(String ssSerial) {
        this.ssSerial = ssSerial;
    }

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

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
