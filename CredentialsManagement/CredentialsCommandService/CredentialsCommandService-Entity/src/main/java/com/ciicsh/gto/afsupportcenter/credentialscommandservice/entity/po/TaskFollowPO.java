package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: guwei
 * @Description: 任务跟进
 * @Date: Created in 15:13 2018/1/15
 */
@TableName("cmy_af_task_follow")
public class TaskFollowPO extends Model<TaskFollowPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键：任务单跟踪id
     */
    @TableId(value="task_follow_id", type= IdType.AUTO)
    private Long taskFollowId;
    /**
     * 跟进内容
     */
    @TableField("follow_description")
    private String followDescription;
    /**
     * 是否可用
     */
    @TableField("is_active")
    private Boolean isActive;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;
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


    public Long getTaskFollowId() {
        return taskFollowId;
    }

    public void setTaskFollowId(Long taskFollowId) {
        this.taskFollowId = taskFollowId;
    }

    public String getFollowDescription() {
        return followDescription;
    }

    public void setFollowDescription(String followDescription) {
        this.followDescription = followDescription;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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
        return this.taskFollowId;
    }

    @Override
    public String toString() {
        return "TaskFollowPO{" +
            "taskFollowId=" + taskFollowId +
            ", followDescription=" + followDescription +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
