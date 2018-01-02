package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpArchive;

public class SsEmpArchiveBO extends SsEmpArchive {

    // 来源表 emp_employee
    // 雇员姓名
    private String employeeName;
    // 雇员证件号
    private String idNum;
    //企业社保账号
    private String ssAccount;
    //社保类型
    private Integer ssAccountType;
    //结算区县
    private String settlementArea;
    //客户名称
    private String title;
    //社保账户名称
    private String comAccountName;
    //

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

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }

    public Integer getSsAccountType() {
        return ssAccountType;
    }

    public void setSsAccountType(Integer ssAccountType) {
        this.ssAccountType = ssAccountType;
    }

    public String getSettlementArea() {
        return settlementArea;
    }

    public void setSettlementArea(String settlementArea) {
        this.settlementArea = settlementArea;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }
}
