package com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo;

import java.io.Serializable;

public class HfEmpTaskBatchRejectBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long[] selectedData;
    private String rejectionRemark;

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
}
