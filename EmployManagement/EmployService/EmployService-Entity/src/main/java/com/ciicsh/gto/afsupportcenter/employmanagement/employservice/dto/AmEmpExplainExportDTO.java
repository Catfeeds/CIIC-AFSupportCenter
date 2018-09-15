package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto;

import java.util.Date;

public class AmEmpExplainExportDTO {


    /**
     * 雇员名称
     */
    private String employeeName;

    // 身份证号码
    private String inNumber;

    // 离职或入职日期
    private Date date;


    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getInNumber() {
        return inNumber;
    }

    public void setInNumber(String inNumber) {
        this.inNumber = inNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
