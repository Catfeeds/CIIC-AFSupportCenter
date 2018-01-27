package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo;

import java.io.Serializable;

public class SsAnnualAdjustAccountBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer accountStatus;
    private Integer matchStatus;
    private Integer cnt = 0;

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(Integer matchStatus) {
        this.matchStatus = matchStatus;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
}
