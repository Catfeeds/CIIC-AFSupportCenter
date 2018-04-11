package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

/**
 * Created by zhangzhiwen on 2018/2/1.
 */
public class AmResTaskCountBO {

    private  Integer  noFeedback;
    private  Integer  refuseWaitFinished;
    private  Integer  refuseFinished;
    private  Integer  refuseBeforeWithFile;
    private  Integer  refuseTicketStampNoReturn;
    private  Integer  refuseFailed;
    private  Integer  beforeBatchNeedRefuse;
    private  Integer  other;
    private  Integer amount;

    public Integer getRefuseWaitFinished() {
        return refuseWaitFinished;
    }

    public void setRefuseWaitFinished(Integer refuseWaitFinished) {
        this.refuseWaitFinished = refuseWaitFinished;
    }

    public Integer getNoFeedback() {
        return noFeedback;
    }

    public void setNoFeedback(Integer noFeedback) {
        this.noFeedback = noFeedback;
    }

    public Integer getRefuseFinished() {
        return refuseFinished;
    }

    public void setRefuseFinished(Integer refuseFinished) {
        this.refuseFinished = refuseFinished;
    }

    public Integer getRefuseBeforeWithFile() {
        return refuseBeforeWithFile;
    }

    public void setRefuseBeforeWithFile(Integer refuseBeforeWithFile) {
        this.refuseBeforeWithFile = refuseBeforeWithFile;
    }

    public Integer getRefuseTicketStampNoReturn() {
        return refuseTicketStampNoReturn;
    }

    public void setRefuseTicketStampNoReturn(Integer refuseTicketStampNoReturn) {
        this.refuseTicketStampNoReturn = refuseTicketStampNoReturn;
    }

    public Integer getRefuseFailed() {
        return refuseFailed;
    }

    public void setRefuseFailed(Integer refuseFailed) {
        this.refuseFailed = refuseFailed;
    }

    public Integer getBeforeBatchNeedRefuse() {
        return beforeBatchNeedRefuse;
    }

    public void setBeforeBatchNeedRefuse(Integer beforeBatchNeedRefuse) {
        this.beforeBatchNeedRefuse = beforeBatchNeedRefuse;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
