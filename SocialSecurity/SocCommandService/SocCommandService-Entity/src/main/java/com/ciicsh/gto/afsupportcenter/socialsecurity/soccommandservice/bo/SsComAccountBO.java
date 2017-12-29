package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAccountRatio;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComTask;

import java.util.List;

public class SsComAccountBO extends SsComAccount {

    //企业工伤比例变更(新增)
    private SsAccountRatio ssAccountRatio;

    // 来源表 sal_company
    // 客户编号
    private String companyId;
    // 客户名称
    private String title;
    //历史企业任务单
    private List<SsComTask> ssComTaskList;

    //账户关联公司
    private List<SsAccountComRelationBO> ssAccountComRelationBOList;
    //企业工伤比例变更(查询) 1对多
    private List<SsAccountRatio> ssAccountRatioList;
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

    public List<SsComTask> getSsComTaskList() {
        return ssComTaskList;
    }

    public void setSsComTaskList(List<SsComTask> ssComTaskList) {
        this.ssComTaskList = ssComTaskList;
    }

    public List<SsAccountComRelationBO> getSsAccountComRelationBOList() {
        return ssAccountComRelationBOList;
    }

    public void setSsAccountComRelationBOList(List<SsAccountComRelationBO> ssAccountComRelationBOList) {
        this.ssAccountComRelationBOList = ssAccountComRelationBOList;
    }

    public List<SsAccountRatio> getSsAccountRatioList() {
        return ssAccountRatioList;
    }

    public void setSsAccountRatioList(List<SsAccountRatio> ssAccountRatioList) {
        this.ssAccountRatioList = ssAccountRatioList;
    }
}