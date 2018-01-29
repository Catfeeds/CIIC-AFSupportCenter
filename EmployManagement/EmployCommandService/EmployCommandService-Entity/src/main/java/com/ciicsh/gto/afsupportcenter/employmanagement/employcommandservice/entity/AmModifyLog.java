package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 表单修改日志
 * </p>
 */
@TableName("am_modify_log")
public class AmModifyLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="am_modify_log_id", type= IdType.AUTO)
	private Long amModifyLogId;
    /**
     * 主键
     */
	@TableField("employment_id")
	private Long employmentId;
    /**
     * 1 用工备注 2 档案备注 3 退工备注
     */
	@TableField("log_type")
	private String logType;
    /**
     * 修改字段
     */
	@TableField("field_code")
	private String fieldCode;
    /**
     * 修改字段名称
     */
	@TableField("field_name")
	private String fieldName;
    /**
     * 修改人
     */
	@TableField("modify_man")
	private String modifyMan;
    /**
     * 修改时间
     */
	@TableField("modify_time")
	private LocalDateTime modifyTime;
    /**
     * 修改前值
     */
	@TableField("before_value")
	private String beforeValue;
    /**
     * 修改后值
     */
	@TableField("after_value")
	private String afterValue;
    /**
     * 修改后值
     */
	@TableField("after_code")
	private String afterCode;
    /**
     * 修改前值
     */
	@TableField("before_code")
	private String beforeCode;
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


	public Long getAmModifyLogId() {
		return amModifyLogId;
	}

	public void setAmModifyLogId(Long amModifyLogId) {
		this.amModifyLogId = amModifyLogId;
	}

	public Long getEmploymentId() {
		return employmentId;
	}

	public void setEmploymentId(Long employmentId) {
		this.employmentId = employmentId;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	public LocalDateTime getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getBeforeValue() {
		return beforeValue;
	}

	public void setBeforeValue(String beforeValue) {
		this.beforeValue = beforeValue;
	}

	public String getAfterValue() {
		return afterValue;
	}

	public void setAfterValue(String afterValue) {
		this.afterValue = afterValue;
	}

	public String getAfterCode() {
		return afterCode;
	}

	public void setAfterCode(String afterCode) {
		this.afterCode = afterCode;
	}

	public String getBeforeCode() {
		return beforeCode;
	}

	public void setBeforeCode(String beforeCode) {
		this.beforeCode = beforeCode;
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
		return "AmModifyLog{" +
			", amModifyLogId=" + amModifyLogId +
			", employmentId=" + employmentId +
			", logType=" + logType +
			", fieldCode=" + fieldCode +
			", fieldName=" + fieldName +
			", modifyMan=" + modifyMan +
			", modifyTime=" + modifyTime +
			", beforeValue=" + beforeValue +
			", afterValue=" + afterValue +
			", afterCode=" + afterCode +
			", beforeCode=" + beforeCode +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
