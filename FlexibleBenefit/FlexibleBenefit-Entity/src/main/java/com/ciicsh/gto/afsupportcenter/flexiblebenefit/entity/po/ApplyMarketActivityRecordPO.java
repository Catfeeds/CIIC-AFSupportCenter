package com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 市场活动申请礼品表
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
@TableName("fb_apply_market_activity_record")
public class ApplyMarketActivityRecordPO extends Model<ApplyMarketActivityRecordPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 赠送市场活动编号
     */
    @TableId(value = "markert_activity_record_id", type = IdType.AUTO)
    private Integer markertActivityRecordId;
    /**
     * 活动申请表主键
     */
    @TableField("apply_record_detail_id")
    private Integer applyRecordDetailId;
    @TableField("activity_id")
    private Integer activityId;
    /**
     * 申请数量
     */
    private Integer number;
    /**
     * 礼品形式
     */
    @TableField("gift_form")
    private Integer giftForm;
    /**
     * 派送方式
     */
    @TableField("send_way")
    private Integer sendWay;
    /**
     * 派送方式
     */
    @TableField("approval_number")
    private Integer approvalNumber;
    /**
     * 派送地址
     */
    @TableField("delivery_address")
    private String deliveryAddress;
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 最后更新时间
     */
    @TableField("modified_time")
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    @TableField("created_by")
    private String createdBy;
    /**
     * 修改者登录名
     */
    @TableField("modified_by")
    private String modifiedBy;


    public Integer getMarkertActivityRecordId() {
        return markertActivityRecordId;
    }

    public void setMarkertActivityRecordId(Integer markertActivityRecordId) {
        this.markertActivityRecordId = markertActivityRecordId;
    }

    public Integer getApplyRecordDetailId() {
        return applyRecordDetailId;
    }

    public void setApplyRecordDetailId(Integer applyRecordDetailId) {
        this.applyRecordDetailId = applyRecordDetailId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getGiftForm() {
        return giftForm;
    }

    public void setGiftForm(Integer giftForm) {
        this.giftForm = giftForm;
    }

    public Integer getSendWay() {
        return sendWay;
    }

    public void setSendWay(Integer sendWay) {
        this.sendWay = sendWay;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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
    protected Serializable pkVal() {
        return this.markertActivityRecordId;
    }

    public Integer getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(Integer approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    @Override
    public String toString() {
        return "ApplyMarketActivityRecordPO{" +
            "markertActivityRecordId=" + markertActivityRecordId +
            ", applyRecordDetailId=" + applyRecordDetailId +
            ", activityId=" + activityId +
            ", number=" + number +
            ", giftForm=" + giftForm +
            ", sendWay=" + sendWay +
            ", approvalNumber=" + approvalNumber +
            ", deliveryAddress='" + deliveryAddress + '\'' +
            ", isActive=" + isActive +
            ", createTime=" + createTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            '}';
    }
}
