package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MarketActivityPO {
    private Integer id;

    private String activityTitle;

    private String publisher;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Byte status;

    private String content;

    private String giftForm;

    private String sendWay;

    private Boolean isActive;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedTime;

    private String createdBy;

    private String modifiedBy;

    @Override
    public String toString() {
        return "MarketActivityPO{" +
                "id=" + id +
                ", activityTitle='" + activityTitle + '\'' +
                ", publisher='" + publisher + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", giftForm='" + giftForm + '\'' +
                ", sendWay='" + sendWay + '\'' +
                ", isActive=" + isActive +
                ", createTime=" + createTime +
                ", modifiedTime=" + modifiedTime +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle == null ? null : activityTitle.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getGiftForm() {
        return giftForm;
    }

    public void setGiftForm(String giftForm) {
        this.giftForm = giftForm == null ? null : giftForm.trim();
    }

    public String getSendWay() {
        return sendWay;
    }

    public void setSendWay(String sendWay) {
        this.sendWay = sendWay;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }
}