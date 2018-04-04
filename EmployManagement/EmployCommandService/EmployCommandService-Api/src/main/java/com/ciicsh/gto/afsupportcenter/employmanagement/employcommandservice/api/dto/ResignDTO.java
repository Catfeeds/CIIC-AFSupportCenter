package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangzhiwen on 2018/3/29.
 */
public class ResignDTO {

    /**
     * 退工日期
     */
    private Date resignDate;
    /**
     * 退档方向
     */
    private String returnDocDirect;
    /**
     * 退档日期
     */
    private Date returnDocDate;
    /**
     * 暂存日期
     */
    private Date cacheDate;
    /**
     * 转移方式
     */
    private String transferWay;
    /**
     * 转移备注
     */
    private String transferRemark;
    /**
     * 退工反馈
     */
    private String resignFeedback;
    /**
     * 退工成功日期
     */
    private Date jobCentreFeedbackDate;
    /**
     * 退工材料交付日期
     */
    private Date resignMaterialDeliveryDate;
    /**
     * 退工操作员
     */
    private String resignOperateMan;

    /**
     * 取一次性补贴日期
     * @return
     */
    private Date getOneTimeSubsidyDate;

    public Date getGetOneTimeSubsidyDate() {
        return getOneTimeSubsidyDate;
    }

    public void setGetOneTimeSubsidyDate(Date getOneTimeSubsidyDate) {
        this.getOneTimeSubsidyDate = getOneTimeSubsidyDate;
    }

    private List<ResignFeedbackDTO> feedbackDTOList;


    public Date getResignDate() {
        return resignDate;
    }

    public void setResignDate(Date resignDate) {
        this.resignDate = resignDate;
    }

    public String getReturnDocDirect() {
        return returnDocDirect;
    }

    public void setReturnDocDirect(String returnDocDirect) {
        this.returnDocDirect = returnDocDirect;
    }

    public Date getReturnDocDate() {
        return returnDocDate;
    }

    public void setReturnDocDate(Date returnDocDate) {
        this.returnDocDate = returnDocDate;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }

    public String getTransferWay() {
        return transferWay;
    }

    public void setTransferWay(String transferWay) {
        this.transferWay = transferWay;
    }

    public String getTransferRemark() {
        return transferRemark;
    }

    public void setTransferRemark(String transferRemark) {
        this.transferRemark = transferRemark;
    }

    public String getResignFeedback() {
        return resignFeedback;
    }

    public void setResignFeedback(String resignFeedback) {
        this.resignFeedback = resignFeedback;
    }

    public Date getJobCentreFeedbackDate() {
        return jobCentreFeedbackDate;
    }

    public void setJobCentreFeedbackDate(Date jobCentreFeedbackDate) {
        this.jobCentreFeedbackDate = jobCentreFeedbackDate;
    }

    public String getResignOperateMan() {
        return resignOperateMan;
    }

    public void setResignOperateMan(String resignOperateMan) {
        this.resignOperateMan = resignOperateMan;
    }

    public Date getResignMaterialDeliveryDate() {
        return resignMaterialDeliveryDate;
    }

    public void setResignMaterialDeliveryDate(Date resignMaterialDeliveryDate) {
        this.resignMaterialDeliveryDate = resignMaterialDeliveryDate;
    }

    public List<ResignFeedbackDTO> getFeedbackDTOList() {
        return feedbackDTOList;
    }

    public void setFeedbackDTOList(List<ResignFeedbackDTO> feedbackDTOList) {
        this.feedbackDTOList = feedbackDTOList;
    }
}
