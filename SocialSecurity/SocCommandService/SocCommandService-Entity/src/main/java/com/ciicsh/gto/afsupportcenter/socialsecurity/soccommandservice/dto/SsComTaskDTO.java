package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComTask;

import java.util.Date;

public class SsComTaskDTO extends SsComTask{
    //客户名称
    private String companyName;

    //任务发起时间段的 首段
    @JSONField(format="yyyy-MM-dd")
    private Date submitTimeStart;

    //任务发起时间的 尾段
    @JSONField(format="yyyy-MM-dd")
    private Date submitTimeEnd;

    //账户类型
    private String accountType;

    //结算区县
    private String regionValue;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getSubmitTimeStart() {
        return submitTimeStart;
    }

    public Date getSubmitTimeEnd() {
        return submitTimeEnd;
    }

    public void setSubmitTimeStart(Date submitTimeStart) {
        this.submitTimeStart = submitTimeStart;
    }

    public void setSubmitTimeEnd(Date submitTimeEnd) {
        this.submitTimeEnd = submitTimeEnd;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getRegionValue() {
        return regionValue;
    }

    public void setRegionValue(String regionValue) {
        this.regionValue = regionValue;
    }
}
