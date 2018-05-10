package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 批量导入模板字段
 * </p>
 */
@TableName("am_imp_modify_template_field")
public class AmImpModifyTemplateField implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="imp_modify_template_field_id", type= IdType.AUTO)
	private Long impModifyTemplateFieldId;
    /**
     * 外键
     */
	@TableField("imp_modify_template_id")
	private Long impModifyTemplateId;
    /**
     * 字段代码
     */
	@TableField("field_code")
	private String fieldCode;
    /**
     * 字段名称
     */
	@TableField("field_name")
	private String fieldName;
    /**
     * 字段类型
     */
	@TableField("field_type")
	private String fieldType;
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


	public Long getImpModifyTemplateFieldId() {
		return impModifyTemplateFieldId;
	}

	public void setImpModifyTemplateFieldId(Long impModifyTemplateFieldId) {
		this.impModifyTemplateFieldId = impModifyTemplateFieldId;
	}

	public Long getImpModifyTemplateId() {
		return impModifyTemplateId;
	}

	public void setImpModifyTemplateId(Long impModifyTemplateId) {
		this.impModifyTemplateId = impModifyTemplateId;
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

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
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
		return "AmImpModifyTemplateField{" +
			", impModifyTemplateFieldId=" + impModifyTemplateFieldId +
			", impModifyTemplateId=" + impModifyTemplateId +
			", fieldCode=" + fieldCode +
			", fieldName=" + fieldName +
			", fieldType=" + fieldType +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
