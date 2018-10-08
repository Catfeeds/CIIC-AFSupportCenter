package com.ciicsh.gto.afsupportcenter.fundjob.bo;

import java.io.Serializable;
import java.util.List;

public class HfArchiveBasePeriodUpdateBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Long> empTaskIdList;
    private String modifiedBy;

    public List<Long> getEmpTaskIdList() {
        return empTaskIdList;
    }

    public void setEmpTaskIdList(List<Long> empTaskIdList) {
        this.empTaskIdList = empTaskIdList;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
