package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 批量导入修改数据
 * </p>
 */
@TableName("am_batimp_modify_data")
public class AmBatimpModifyData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="batch_imp_modify_data_id", type= IdType.AUTO)
	private Long batchImpModifyDataId;
    /**
     * 批量导入修改模板
     */
	@TableField("batch_imp_modify_id")
	private Long batchImpModifyId;
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
     * 导入内容
     */
	@TableField("imp_value")
	private String impValue;
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


	public Long getBatchImpModifyDataId() {
		return batchImpModifyDataId;
	}

	public void setBatchImpModifyDataId(Long batchImpModifyDataId) {
		this.batchImpModifyDataId = batchImpModifyDataId;
	}

	public Long getBatchImpModifyId() {
		return batchImpModifyId;
	}

	public void setBatchImpModifyId(Long batchImpModifyId) {
		this.batchImpModifyId = batchImpModifyId;
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

	public String getImpValue() {
		return impValue;
	}

	public void setImpValue(String impValue) {
		this.impValue = impValue;
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
		return "AmBatimpModifyData{" +
			", batchImpModifyDataId=" + batchImpModifyDataId +
			", batchImpModifyId=" + batchImpModifyId +
			", fieldCode=" + fieldCode +
			", fieldName=" + fieldName +
			", impValue=" + impValue +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
