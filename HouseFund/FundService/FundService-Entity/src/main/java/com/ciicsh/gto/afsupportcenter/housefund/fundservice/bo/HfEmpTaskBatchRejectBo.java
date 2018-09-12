package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import java.io.Serializable;

public class HfEmpTaskBatchRejectBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long[] selectedData;
    private String rejectionRemark;
    private String modifiedBy;
    private String modifiedDisplayName;

    public Long[] getSelectedData() {
        return selectedData;
    }

    public void setSelectedData(Long[] selectedData) {
        this.selectedData = selectedData;
    }

    public String getRejectionRemark() {
        return rejectionRemark;
    }

    public void setRejectionRemark(String rejectionRemark) {
        this.rejectionRemark = rejectionRemark;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDisplayName() {
        return modifiedDisplayName;
    }

    public void setModifiedDisplayName(String modifiedDisplayName) {
        this.modifiedDisplayName = modifiedDisplayName;
    }
}
