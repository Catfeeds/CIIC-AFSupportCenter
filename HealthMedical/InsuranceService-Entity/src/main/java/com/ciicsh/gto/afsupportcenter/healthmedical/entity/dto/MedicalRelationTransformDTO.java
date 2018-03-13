package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto;

/**
 * <p>
 * 医疗关系转移表
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-29
 */
public class MedicalRelationTransformDTO extends CommonEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员终身编号
     */
    private String employeeId;
    private String employeeName;
    private String IDNum;
    private String companyId;
    private String companyName;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getIDNum() {
        return IDNum;
    }

    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
