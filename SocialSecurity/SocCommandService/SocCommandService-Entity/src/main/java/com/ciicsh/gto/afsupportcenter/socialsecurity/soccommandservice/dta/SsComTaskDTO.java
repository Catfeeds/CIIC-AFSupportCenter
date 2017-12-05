package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dta;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComTask;

import java.time.LocalDate;
import java.util.List;

public class SsComTaskDTO extends SsComTask{
    //客户名称
    private String companyName;
    //任务发起时间
    private List taskStartTime;
    //任务发起时间段的 首段
    private LocalDate submitTimeStrat;

    //任务发起时间的 尾段
    private LocalDate getSubmitTimeEnd;

    private Integer pageSize;

    private Integer currentPage;

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

    public List getTaskStartTime() {
        return taskStartTime;
    }

    public LocalDate getGetSubmitTimeEnd() {
        return getSubmitTimeEnd;
    }

    public void setTaskStartTime(List taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
