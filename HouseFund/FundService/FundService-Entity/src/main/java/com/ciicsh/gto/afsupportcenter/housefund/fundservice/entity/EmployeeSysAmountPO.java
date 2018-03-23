package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import java.math.BigDecimal;

/**
 * 员工系统金额
 */
public class EmployeeSysAmountPO {
    /**
     * 终身雇员编号
     */
    private String employeeId;


    /**
     * 系统中落地的公积金汇缴金额
     */
    private BigDecimal sysAmount;


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getSysAmount() {
        return sysAmount;
    }

    public void setSysAmount(BigDecimal sysAmount) {
        this.sysAmount = sysAmount;
    }
}
