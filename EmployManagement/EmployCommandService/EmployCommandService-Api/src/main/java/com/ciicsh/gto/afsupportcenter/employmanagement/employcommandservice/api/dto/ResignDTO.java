package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by zhangzhiwen on 2018/3/29.
 */
public class ResignDTO {

    /**
     * 退工日期
     */
    private LocalDate resignDate;
    /**
     * 退档方向
     */
    private String returnDocDirect;
    /**
     * 退档日期
     */
    private LocalDate returnDocDate;
    /**
     * 暂存日期
     */
    private LocalDate cacheDate;
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
    private LocalDate jobCentreFeedbackDate;
    /**
     * 退工材料交付日期
     */
    private LocalDate resignMaterialDeliveryDate;
    /**
     * 退工操作员
     */
    private String resignOperateMan;

    private List<ResignFeedbackDTO> feedbackDTOList;


    public LocalDate getResignDate() {
        return resignDate;
    }

    public void setResignDate(LocalDate resignDate) {
        this.resignDate = resignDate;
    }

    public String getReturnDocDirect() {
        return returnDocDirect;
    }

    public void setReturnDocDirect(String returnDocDirect) {
        this.returnDocDirect = returnDocDirect;
    }

    public LocalDate getReturnDocDate() {
        return returnDocDate;
    }

    public void setReturnDocDate(LocalDate returnDocDate) {
        this.returnDocDate = returnDocDate;
    }

    public LocalDate getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(LocalDate cacheDate) {
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

    public LocalDate getJobCentreFeedbackDate() {
        return jobCentreFeedbackDate;
    }

    public void setJobCentreFeedbackDate(LocalDate jobCentreFeedbackDate) {
        this.jobCentreFeedbackDate = jobCentreFeedbackDate;
    }

    public String getResignOperateMan() {
        return resignOperateMan;
    }

    public void setResignOperateMan(String resignOperateMan) {
        this.resignOperateMan = resignOperateMan;
    }

    public LocalDate getResignMaterialDeliveryDate() {
        return resignMaterialDeliveryDate;
    }

    public void setResignMaterialDeliveryDate(LocalDate resignMaterialDeliveryDate) {
        this.resignMaterialDeliveryDate = resignMaterialDeliveryDate;
    }

    public List<ResignFeedbackDTO> getFeedbackDTOList() {
        return feedbackDTOList;
    }

    public void setFeedbackDTOList(List<ResignFeedbackDTO> feedbackDTOList) {
        this.feedbackDTOList = feedbackDTOList;
    }
}
