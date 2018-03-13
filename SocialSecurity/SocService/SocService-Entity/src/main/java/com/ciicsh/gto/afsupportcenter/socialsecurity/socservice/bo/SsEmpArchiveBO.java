package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTask;

import java.math.BigDecimal;

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
    //学历
    private String education;
    //户口地址
    private String residenceAddress;
    //户口属性
    private String residenceAttribute;
    //联系人地址
    private String contactAddress;
    //雇员属性
    private String employeeAttribute;
    //新增和转入 对应一些雇员信息，比如人员属性和工资等
    private SsEmpTask SsEmpTask;
    //旧基数
    private BigDecimal oldEmpBase;
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getResidenceAttribute() {
        return residenceAttribute;
    }

    public void setResidenceAttribute(String residenceAttribute) {
        this.residenceAttribute = residenceAttribute;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getEmployeeAttribute() {
        return employeeAttribute;
    }

    public void setEmployeeAttribute(String employeeAttribute) {
        this.employeeAttribute = employeeAttribute;
    }

    public com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTask getSsEmpTask() {
        return SsEmpTask;
    }

    public void setSsEmpTask(com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTask ssEmpTask) {
        SsEmpTask = ssEmpTask;
    }

    public BigDecimal getOldEmpBase() {
        return oldEmpBase;
    }

    public void setOldEmpBase(BigDecimal oldEmpBase) {
        this.oldEmpBase = oldEmpBase;
    }
}
