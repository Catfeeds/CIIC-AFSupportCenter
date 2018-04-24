package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.math.BigDecimal;

@ExcelTarget("StatementExportOpt")
public class FundStatementItemDTO {

    /**
     * 公积金企业账号
     */
    private String comAccount = "";
    /**
     * 个人公积金账号
     */
    @Excel(name = "导入公积金账号",width = 20, orderNum = "1")
    private String empAccount = "";

    /**
     * 员工姓名
     */
    @Excel(name = "姓名",width = 20, orderNum = "2")
    private String empName = "";

    /**
     * 导入月缴金额
     */
    @Excel(name = "导入月缴金额",width = 20, orderNum = "3")
    private BigDecimal impAmount = BigDecimal.ZERO;

    /**
     * 系统金额
     */
    @Excel(name = "系统金额",width = 20, orderNum = "4")
    private BigDecimal sysAmount = BigDecimal.ZERO;

    /**
     * 差异金额
     */
    @Excel(name = "差异金额",width = 20, orderNum = "5")
    private BigDecimal diffAmount = BigDecimal.ZERO;

    @Excel(name = "备注",width = 30, orderNum = "6")
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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
