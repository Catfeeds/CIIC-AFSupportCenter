package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchive;


/**
 * Created by zhangzhiwen on 2018/1/29.
 */
public class AmArchiveBO extends AmArchive {

    private  Boolean  end;

    private  String isFrist;

    private Long[] empTaskIds;

    private Boolean formAdvance;

    private String employeeName;

    private String idNum;

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

    public Boolean getFormAdvance() {
        return formAdvance;
    }

    public void setFormAdvance(Boolean formAdvance) {
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
