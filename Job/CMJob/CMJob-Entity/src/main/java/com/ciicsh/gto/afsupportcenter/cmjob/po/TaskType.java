package com.ciicsh.gto.afsupportcenter.cmjob.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: guwei
 * @Description: 证件办理类型表
 * @Date: Created in 16:54 2018/4/3
 */
@TableName("cm_task_type")
public class TaskType extends Model<TaskType> {

    private static final long serialVersionUID = 1L;

    @TableId("task_type_id")
    private Long taskTypeId;
    @TableField("task_type_code")
    private String taskTypeCode;
    /**
     * 任务类型名称
     */
    @TableField("task_type_name")
    private String taskTypeName;
    /**
     * 基础产品ID
     */
    @TableField("basic_product_id")
    private String basicProductId;
    /**
     * 产品ID
     */
    @TableField("product_id")
    private String productId;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 父ID
     */
    private Long pid;
    @TableField("is_active")
    private Boolean isActive;
    @TableField("created_time")
    private Date createdTime;
    @TableField("modified_time")
    private Date modifiedTime;
    @TableField("created_by")
    private String createdBy;
    @TableField("modified_by")
    private String modifiedBy;


    public Long getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(Long taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public String getTaskTypeCode() {
        return taskTypeCode;
    }

    public void setTaskTypeCode(String taskTypeCode) {
        this.taskTypeCode = taskTypeCode;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

    public String getBasicProductId() {
        return basicProductId;
    }

    public void setBasicProductId(String basicProductId) {
        this.basicProductId = basicProductId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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
        return this.taskTypeId;
    }

    @Override
    public String toString() {
        return "TaskType{" +
            "taskTypeId=" + taskTypeId +
            ", taskTypeCode=" + taskTypeCode +
            ", taskTypeName=" + taskTypeName +
            ", basicProductId=" + basicProductId +
            ", level=" + level +
            ", pid=" + pid +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
