package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchive;

import java.util.List;


public class AmArchiveBO extends AmArchive {

    private  Boolean  end;

    private  String isFrist;

    private Long[] empTaskIds;

    private boolean formAdvance=false;

    private String employeeName;

    private String idNum;

    private  String remark;

    private List<Long> employmentIds;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Long> getEmploymentIds() {
        return employmentIds;
    }

    public void setEmploymentIds(List<Long> employmentIds) {
        this.employmentIds = employmentIds;
    }

    public Long[] getEmpTaskIds() {
        return empTaskIds;
    }

    public void setEmpTaskIds(Long[] empTaskIds) {
        this.empTaskIds = empTaskIds;
    }

    public String getIsFrist() {
        return isFrist;
    }

    public void setIsFrist(String isFrist) {
        this.isFrist = isFrist;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public boolean getFormAdvance() {
        return formAdvance;
    }

    public void setFormAdvance(boolean formAdvance) {
        this.formAdvance = formAdvance;
    }

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
