package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 退工联动表
 * </p>
 *
 * @author xsj
 * @since 2018-03-29
 */
@TableName("am_resign_link")
public class AmResignLink implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="resign_link_id", type= IdType.AUTO)
	private Long resignLinkId;
    /**
     * 任务id
     */
	@TableField("task_id")
	private String taskId;
    /**
     * 1退工任务单签收
     */
	@TableField("resign_feedback")
	private String resignFeedback;
    /**
     * 退工反馈日期
     */
	@TableField("resign_feedback_date")
	private LocalDate resignFeedbackDate;
    /**
     * 退工成功日期
     */
	@TableField("job_centre_feedback_date")
	private LocalDate jobCentreFeedbackDate;
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


	public Long getResignLinkId() {
		return resignLinkId;
	}

	public void setResignLinkId(Long resignLinkId) {
		this.resignLinkId = resignLinkId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getResignFeedback() {
		return resignFeedback;
	}

	public void setResignFeedback(String resignFeedback) {
		this.resignFeedback = resignFeedback;
	}

	public LocalDate getResignFeedbackDate() {
		return resignFeedbackDate;
	}

	public void setResignFeedbackDate(LocalDate resignFeedbackDate) {
		this.resignFeedbackDate = resignFeedbackDate;
	}

	public LocalDate getJobCentreFeedbackDate() {
		return jobCentreFeedbackDate;
	}

	public void setJobCentreFeedbackDate(LocalDate jobCentreFeedbackDate) {
		this.jobCentreFeedbackDate = jobCentreFeedbackDate;
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
		return "AmResignLink{" +
			"resignLinkId=" + resignLinkId +
			", taskId=" + taskId +
			", resignFeedback=" + resignFeedback +
			", resignFeedbackDate=" + resignFeedbackDate +
			", jobCentreFeedbackDate=" + jobCentreFeedbackDate +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
