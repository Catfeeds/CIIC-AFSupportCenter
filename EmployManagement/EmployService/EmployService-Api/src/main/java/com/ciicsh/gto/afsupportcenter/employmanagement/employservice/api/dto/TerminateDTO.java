package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto;

import java.util.Date;


/**
 * Created by zhangzhiwen on 2018/9/13.
 */
public class TerminateDTO {
    /**
     * 终止日期
     */
    private Date outDate;
    /**
     * 终止原因
     */
    private String outReason;
    /**
     * 终止操作日期
     */
    private Date jobCentreFeedbackDate;
    /**
     * 终止后档案意向
     */
    private String returnDocDirect;
    /**
     * 档案调出日期
     */
    private Date  returnDocDate;


    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getOutReason() {
        return outReason;
    }

    public void setOutReason(String outReason) {
        this.outReason = outReason;
    }

    public Date getJobCentreFeedbackDate() {
        return jobCentreFeedbackDate;
    }

    public void setJobCentreFeedbackDate(Date jobCentreFeedbackDate) {
        this.jobCentreFeedbackDate = jobCentreFeedbackDate;
    }

    public Date getReturnDocDate() {
        return returnDocDate;
    }

    public void setReturnDocDate(Date returnDocDate) {
        this.returnDocDate = returnDocDate;
    }

    public String getReturnDocDirect() {
        return returnDocDirect;
    }

    public void setReturnDocDirect(String returnDocDirect) {
        this.returnDocDirect = returnDocDirect;
    }
}
