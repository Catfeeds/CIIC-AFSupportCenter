package com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 企业公积金任务单状态数据表
 * </p>
 *
 * @author 沈健
 * @since 2018-02-28
 */
@TableName("hf_com_task_task_status")
public class HfComTaskTaskStatus extends Model<HfComTaskTaskStatus> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="com_task_task_status_id", type= IdType.AUTO)
	private Long comTaskTaskStatusId;
    /**
     * 企业公积金任务单状态编号
     */
	@TableField("task_status_code")
	private Integer taskStatusCode;
    /**
     * 企业公积金任务单状态值
     */
	@TableField("task_status_value")
	private String taskStatusValue;
    /**
     * 是否可用
     */
	@TableField("is_active")
    @TableLogic
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


	public Long getComTaskTaskStatusId() {
		return comTaskTaskStatusId;
	}

	public void setComTaskTaskStatusId(Long comTaskTaskStatusId) {
		this.comTaskTaskStatusId = comTaskTaskStatusId;
	}

	public Integer getTaskStatusCode() {
		return taskStatusCode;
	}

	public void setTaskStatusCode(Integer taskStatusCode) {
		this.taskStatusCode = taskStatusCode;
	}

	public String getTaskStatusValue() {
		return taskStatusValue;
	}

	public void setTaskStatusValue(String taskStatusValue) {
		this.taskStatusValue = taskStatusValue;
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
		return this.comTaskTaskStatusId;
	}

	@Override
	public String toString() {
		return "HfComTaskTaskStatus{" +
			", comTaskTaskStatusId=" + comTaskTaskStatusId +
			", taskStatusCode=" + taskStatusCode +
			", taskStatusValue=" + taskStatusValue +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
