package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpArchive;

public class SsEmpArchiveDTO extends SsEmpArchive {

    // 来源表 emp_employee
    // 雇员姓名
    private String employeeName;
    // 雇员证件号
    private String idNum;

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
}
