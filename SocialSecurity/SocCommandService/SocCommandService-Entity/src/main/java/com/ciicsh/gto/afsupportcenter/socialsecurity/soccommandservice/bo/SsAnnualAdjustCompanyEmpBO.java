package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustCompanyEmp;

/**
 *
 */
public class SsAnnualAdjustCompanyEmpBO extends SsAnnualAdjustCompanyEmp {
    private static final long serialVersionUID = 1L;

    private String companyId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
