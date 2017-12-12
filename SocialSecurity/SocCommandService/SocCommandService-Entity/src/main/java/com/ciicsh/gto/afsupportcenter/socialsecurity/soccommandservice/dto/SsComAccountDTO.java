package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAccountRatio;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComAccount;

public class SsComAccountDTO extends SsComAccount {

    //企业工伤比例变更
    private SsAccountRatio ssAccountRatio;

    // 来源表 sal_company
    // 客户编号
    private String companyId;
    // 客户名称
    private String title;

    public SsAccountRatio getSsAccountRatio() {
        return ssAccountRatio;
    }

    public void setSsAccountRatio(SsAccountRatio ssAccountRatio) {
        this.ssAccountRatio = ssAccountRatio;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
