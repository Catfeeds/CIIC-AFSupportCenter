package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAccountRatio;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComAccount;

public class SsComAccountDTO extends SsComAccount {

    //企业工伤比例变更
    private SsAccountRatio ssAccountRatio;

    public SsAccountRatio getSsAccountRatio() {
        return ssAccountRatio;
    }

    public void setSsAccountRatio(SsAccountRatio ssAccountRatio) {
        this.ssAccountRatio = ssAccountRatio;
    }
}
