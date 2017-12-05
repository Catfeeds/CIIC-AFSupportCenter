package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dta;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComTask;

import java.time.LocalDate;

public class SsComTaskDTO extends SsComTask{
    //客户姓名
    private String companyName;

    //任务发起时间段的 首段
    private LocalDate submitTimeStrat;

    //任务发起时间的 尾段
    private LocalDate getSubmitTimeEnd;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDate getSubmitTimeStrat() {
        return submitTimeStrat;
    }

    public void setSubmitTimeStrat(LocalDate submitTimeStrat) {
        this.submitTimeStrat = submitTimeStrat;
    }

    public void setGetSubmitTimeEnd(LocalDate getSubmitTimeEnd) {
        this.getSubmitTimeEnd = getSubmitTimeEnd;
    }
}
