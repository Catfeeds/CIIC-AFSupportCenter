package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import java.math.BigDecimal;

/**
 * 对账单项目实体
 */
public class FundStatementItemPO
{
    /**
     * 对账单Id
     */
    private long statementId;


    /**
     * 公积金月份
     */
    private String hfMonth;

    /**
     * 公积金企业账户名称
     */
    private String comAccountName;

    /**
     * 导入记录总数
     */
    private int impRecordCount;

    /**
     * 差异总数
     */
    private int diffCount;


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

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getStatementId() {
        return statementId;
    }

    public void setStatementId(long statementId) {
        this.statementId = statementId;
    }

    public String getHfMonth() {
        return hfMonth;
    }

    public void setHfMonth(String hfMonth) {
        this.hfMonth = hfMonth;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public int getImpRecordCount() {
        return impRecordCount;
    }

    public void setImpRecordCount(int impRecordCount) {
        this.impRecordCount = impRecordCount;
    }

    public int getDiffCount() {
        return diffCount;
    }

    public void setDiffCount(int diffCount) {
        this.diffCount = diffCount;
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

    public FundStatementDetailPO toKey(){
       FundStatementDetailPO po = new FundStatementDetailPO();
       po.setStatementId(this.statementId);
       po.setComAccountName(this.comAccountName);
       po.setDiffCount(this.getDiffCount());
       po.setHfMonth(this.getHfMonth());
       po.setImpRecordCount(this.getImpRecordCount());
       return po;
    }

}
