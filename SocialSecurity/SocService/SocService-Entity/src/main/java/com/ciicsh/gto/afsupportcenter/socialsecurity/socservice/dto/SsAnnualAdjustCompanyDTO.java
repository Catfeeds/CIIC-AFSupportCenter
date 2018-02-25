package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto;

import java.io.Serializable;

public class SsAnnualAdjustCompanyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String companyId;
    private Long comAccountId;
    private Integer ssAccountType;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public Integer getSsAccountType() {
        return ssAccountType;
    }

    public void setSsAccountType(Integer ssAccountType) {
        this.ssAccountType = ssAccountType;
    }
}
