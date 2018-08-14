package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto;

public class AmEmpCollectExportDTO {

    /**
     * 序号
     */
    private Integer id;

    /**
     * 雇员名称
     */
    private String employeeName;

    /**
     * 身份证号码
     */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
