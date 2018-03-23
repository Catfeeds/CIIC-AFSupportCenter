package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SsStatementResultCompareBO {
    private String employeeId;
    private String employeeName;
    private Integer diffHeadcount;

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

    public Integer getDiffHeadcount() {
        return diffHeadcount;
    }

    public void setDiffHeadcount(Integer diffHeadcount) {
        this.diffHeadcount = diffHeadcount;
    }
}
