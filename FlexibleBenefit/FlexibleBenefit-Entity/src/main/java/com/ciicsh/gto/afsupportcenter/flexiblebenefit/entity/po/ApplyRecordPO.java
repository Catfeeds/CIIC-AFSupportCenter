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
 * 申请记录表
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
@TableName("fb_apply_record")
public class ApplyRecordPO extends Model<ApplyRecordPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 申请记录编号
     */
    @TableId(value = "apply_record_id", type = IdType.AUTO)
    private Integer applyRecordId;
    /**
     * 申请人编号
     */
    @TableField("applyer_id")
    private Integer applyerId;
    /**
     * 申请时间
     */
    @TableField("apply_time")
    private Date applyTime;
    /**
     * 申请类型：
     * 1-礼品
     * 2-市场活动
     */
    @TableField("apply_type")
    private Integer applyType;
    /**
     * 项目主题：礼品名称/活动标题
     */
    @TableField("project_topics")
    private String projectTopics;
    /**
     * 审批状态（0-审批中，1-同意，2-不同意，3-部分同意）
     */
    @TableField("record_approval_reason")
    private Integer recordApprovalReason;
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


    public Integer getApplyRecordId() {
        return applyRecordId;
    }

    public void setApplyRecordId(Integer applyRecordId) {
        this.applyRecordId = applyRecordId;
    }

    public Integer getApplyerId() {
        return applyerId;
    }

    public void setApplyerId(Integer applyerId) {
        this.applyerId = applyerId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public String getProjectTopics() {
        return projectTopics;
    }

    public void setProjectTopics(String projectTopics) {
        this.projectTopics = projectTopics;
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

    public Integer getRecordApprovalReason() {
        return recordApprovalReason;
    }

    public void setRecordApprovalReason(Integer recordApprovalReason) {
        this.recordApprovalReason = recordApprovalReason;
    }

    @Override
    protected Serializable pkVal() {
        return this.applyRecordId;
    }

    @Override
    public String toString() {
        return "ApplyRecordPO{" +
            ", applyRecordId=" + applyRecordId +
            ", applyerId=" + applyerId +
            ", applyTime=" + applyTime +
            ", applyType=" + applyType +
            ", projectTopics=" + projectTopics +
            ", recordApprovalReason=" + recordApprovalReason +
            ", isActive=" + isActive +
            ", createTime=" + createTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
