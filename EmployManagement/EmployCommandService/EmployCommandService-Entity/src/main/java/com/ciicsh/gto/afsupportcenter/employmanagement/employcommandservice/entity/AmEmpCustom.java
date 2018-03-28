package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户信息表
 * </p>
 *
 * @author xsj
 * @since 2018-03-28
 */
@TableName("am_emp_custom")
public class AmEmpCustom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键,可作为任务单序号
     */
    @TableId(value="emp_custom_id", type= IdType.AUTO)
    private Long empCustomId;
    /**
     * 任务id
     */
    @TableField("task_id")
    private String taskId;
    /**
     * 领导ID
     */
    @TableField("leader_ship_id")
    private String leaderShipId;
    /**
     * 领导姓名
     */
    @TableField("leader_ship_name")
    private String leaderShipName;
    /**
     * 创建者姓名
     */
    @TableField("created_display_name")
    private String createdDisplayName;
    /**
     * 修改者姓名
     */
    @TableField("modified_display_name")
    private String modifiedDisplayName;
    /**
     * 服务中心
     */
    @TableField("service_center")
    private String serviceCenter;
    /**
     * 是否可用
     */
    @TableField("is_active")
    private Boolean isActive;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */
    @TableField("modified_time")
    private LocalDateTime modifiedTime;
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


    public Long getEmpCustomId() {
        return empCustomId;
    }

    public void setEmpCustomId(Long empCustomId) {
        this.empCustomId = empCustomId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getLeaderShipId() {
        return leaderShipId;
    }

    public void setLeaderShipId(String leaderShipId) {
        this.leaderShipId = leaderShipId;
    }

    public String getLeaderShipName() {
        return leaderShipName;
    }

    public void setLeaderShipName(String leaderShipName) {
        this.leaderShipName = leaderShipName;
    }

    public String getCreatedDisplayName() {
        return createdDisplayName;
    }

    public void setCreatedDisplayName(String createdDisplayName) {
        this.createdDisplayName = createdDisplayName;
    }

    public String getModifiedDisplayName() {
        return modifiedDisplayName;
    }

    public void setModifiedDisplayName(String modifiedDisplayName) {
        this.modifiedDisplayName = modifiedDisplayName;
    }

    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
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
        return "AmEmpCustom{" +
            "empCustomId=" + empCustomId +
            ", taskId=" + taskId +
            ", leaderShipId=" + leaderShipId +
            ", leaderShipName=" + leaderShipName +
            ", createdDisplayName=" + createdDisplayName +
            ", modifiedDisplayName=" + modifiedDisplayName +
            ", serviceCenter=" + serviceCenter +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
