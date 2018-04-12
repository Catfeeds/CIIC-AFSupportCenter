package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto;

import java.math.BigDecimal;

public class FundStatementItemDTO {

    /**
     * 公积金企业账号
     */
    private String comAccount = "";
    /**
     * 个人公积金账号
     */
    private String empAccount = "";

    /**
     * 员工姓名
     */
    private String empName = "";

    /**
     * 导入月缴金额
     */
    private BigDecimal impAmount = BigDecimal.ZERO;

    /**
     * 系统金额
     */
    private BigDecimal sysAmount = BigDecimal.ZERO;

    /**
     * 差异金额
     */
    private BigDecimal diffAmount = BigDecimal.ZERO;

    public String getComAccount() {
        return comAccount;
    }

    public void setComAccount(String comAccount) {
        this.comAccount = comAccount;
    }

    public String getEmpAccount() {
        return empAccount;
    }

    public void setEmpAccount(String empAccount) {
        this.empAccount = empAccount;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public BigDecimal getImpAmount() {
        return impAmount;
    }

    public void setImpAmount(BigDecimal impAmount) {
        this.impAmount = impAmount;
    }

    public BigDecimal getSysAmount() {
        return sysAmount;
    }

    public void setSysAmount(BigDecimal sysAmount) {
        this.sysAmount = sysAmount;
    }

    public BigDecimal getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(BigDecimal diffAmount) {
        this.diffAmount = diffAmount;
    }
}
