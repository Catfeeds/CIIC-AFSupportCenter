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
 * 礼品申请记录表
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
@TableName("fb_apply_gift_record")
public class ApplyGiftRecordPO extends Model<ApplyGiftRecordPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "gift_record_id", type = IdType.AUTO)
    private Integer giftRecordId;
    @TableField("apply_record_detail_id")
    private Integer applyRecordDetailId;
    /**
     * 礼品主键
     */
    @TableField("gift_id")
    private Integer giftId;
    /**
     * 申请礼品数量，默认是1
     */
    @TableField("apply_num")
    private Integer applyNum;
    /**
     * 是否可用
     */
    @TableField("is_active")
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


    public Integer getGiftRecordId() {
        return giftRecordId;
    }

    public void setGiftRecordId(Integer giftRecordId) {
        this.giftRecordId = giftRecordId;
    }

    public Integer getApplyRecordDetailId() {
        return applyRecordDetailId;
    }

    public void setApplyRecordDetailId(Integer applyRecordDetailId) {
        this.applyRecordDetailId = applyRecordDetailId;
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
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
    protected Serializable pkVal() {
        return this.giftRecordId;
    }

    @Override
    public String toString() {
        return "ApplyGiftRecordPO{" +
                ", giftRecordId=" + giftRecordId +
                ", applyRecordDetailId=" + applyRecordDetailId +
                ", giftId=" + giftId +
                ", applyNum=" + applyNum +
                ", isActive=" + isActive +
                ", createTime=" + createTime +
                ", modifiedTime=" + modifiedTime +
                ", createdBy=" + createdBy +
                ", modifiedBy=" + modifiedBy +
                "}";
    }
}
