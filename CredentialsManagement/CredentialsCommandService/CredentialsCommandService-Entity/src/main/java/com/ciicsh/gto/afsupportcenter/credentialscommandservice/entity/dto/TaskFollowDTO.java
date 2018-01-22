package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 15:59 2018/1/22
 */
public class TaskFollowDTO implements Serializable{

    /**
     * 主键：任务单跟踪id
     */
    private Long taskFollowId;
    /**
     * 任务单id
     */
    private Long taskId;
    /**
     * 跟进内容
     */
    private String followDescription;
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 最后更新时间
     */
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
    private String modifiedBy;

    public Long getTaskFollowId() {
        return taskFollowId;
    }

    public void setTaskFollowId(Long taskFollowId) {
        this.taskFollowId = taskFollowId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public void setActive(Boolean active) {
        isActive = active;
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
    public String toString() {
        return "TaskFollowDTO{" +
            "taskFollowId=" + taskFollowId +
            ", followDescription='" + followDescription + '\'' +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            '}';
    }
}
