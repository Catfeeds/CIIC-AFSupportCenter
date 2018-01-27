package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author xiweizhen
 * @date 2017/12/6 10:47
 */
public class MarketActivityDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 市场活动主键
     */
    private Integer id;
    /**
     * 活动主题
     */
    private String activityTitle;
    /**
     * 发布人
     */
    private String publisher;
    /**
     * 活动开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    /**
     * 活动结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 状态，进行中0，已结束1
     */
    private Integer status;
    /**
     * 活动内容
     */
    private String content;
    /**
     * 礼品形式：以逗号隔开的数组，1实物，2纸质票券，3电子票券
     */
    private String giftForm;
    /**
     * 派送方式：0送至中心，1委托派送
     */
    private String sendWay;
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 最后更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
    private String modifiedBy;


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
        this.activityTitle = activityTitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGiftForm() {
        return giftForm;
    }

    public void setGiftForm(String giftForm) {
        this.giftForm = giftForm;
    }

    public String getSendWay() {
        return sendWay;
    }

    public void setSendWay(String sendWay) {
        this.sendWay = sendWay;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
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
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String toString() {
        return "MarketActivityDTO{" +
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
}