package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpBaseDetail;

public class SsCreateEmpChangeReportBaseDetailBO extends SsEmpBaseDetail {
    private String startMonth = "";
    private String endMonth = "";
    private String baseAmount = "";

    public String getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(String baseAmount) {
        this.baseAmount = baseAmount;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }
}
