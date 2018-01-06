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
 * 审批步骤记录表
 * </p>
 */
@TableName("fb_approval_step")
public class ApprovalStepPO extends Model<ApprovalStepPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 审批步骤主键
     */
    @TableId(value = "step_id", type = IdType.AUTO)
    private Integer stepId;
    /**
     * 申请记录详细编号
     */
    @TableField("apply_record_detail_id")
    private Integer applyRecordDetailId;
    /**
     * 审批人编号（sm_user_info中的user_id）
     */
    @TableField("approver_id")
    private String approverId;
    /**
     * 审批人姓名
     */
    @TableField("approve_name")
    private String approveName;
    /**
     * 审批时间
     */
    @TableField("approve_time")
    private Date approveTime;
    /**
     * 审批动作：
     * 1-同意
     * 2-不同意
     */
    @TableField("approve_action")
    private Integer approveAction;
    /**
     * 审批备注
     */
    @TableField("approve_remark")
    private String approveRemark;
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


    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public Integer getApplyRecordDetailId() {
        return applyRecordDetailId;
    }

    public void setApplyRecordDetailId(Integer applyRecordDetailId) {
        this.applyRecordDetailId = applyRecordDetailId;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Integer getApproveAction() {
        return approveAction;
    }

    public void setApproveAction(Integer approveAction) {
        this.approveAction = approveAction;
    }

    public String getApproveRemark() {
        return approveRemark;
    }

    public void setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark;
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

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    @Override
    protected Serializable pkVal() {
        return this.stepId;
    }

    @Override
    public String toString() {
        return "ApprovalStepPO{" +
            "stepId=" + stepId +
            ", applyRecordDetailId=" + applyRecordDetailId +
            ", approverId='" + approverId + '\'' +
            ", approveName='" + approveName + '\'' +
            ", approveTime=" + approveTime +
            ", approveAction=" + approveAction +
            ", approveRemark='" + approveRemark + '\'' +
            ", isActive=" + isActive +
            ", createTime=" + createTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            '}';
    }
}
