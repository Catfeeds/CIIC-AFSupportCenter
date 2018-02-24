package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto;

import java.io.Serializable;

public class SsAnnualAdjustAccountDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long annualAdjustAccountId;
    private Long comAccountId;
    private Integer ssAccountType;

    public Long getAnnualAdjustAccountId() {
        return annualAdjustAccountId;
    }

    public void setAnnualAdjustAccountId(Long annualAdjustAccountId) {
        this.annualAdjustAccountId = annualAdjustAccountId;
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
