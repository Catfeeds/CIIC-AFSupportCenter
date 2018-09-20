package com.ciicsh.gto.afsupportcenter.cmjob.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 定时任务表
 * </p>
 *
 * @author guwei
 * @since 2018-09-18
 */
@TableName("cm_timed_task")
public class TimedTask extends Model<TimedTask> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="timed_task_id", type= IdType.AUTO)
	private Long timedTaskId;
    /**
     * 任务单编号
     */
	@TableField("task_id")
	private Long taskId;
    /**
     * 是否进入账单
     */
	@TableField("is_implement")
	private Boolean isImplement;
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


	public Long getTimedTaskId() {
		return timedTaskId;
	}

	public void setTimedTaskId(Long timedTaskId) {
		this.timedTaskId = timedTaskId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Boolean getImplement() {
		return isImplement;
	}

	public void setImplement(Boolean isImplement) {
		this.isImplement = isImplement;
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
		return this.timedTaskId;
	}

	@Override
	public String toString() {
		return "TimedTask{" +
			"timedTaskId=" + timedTaskId +
			", taskId=" + taskId +
			", isImplement=" + isImplement +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
