package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 */
@TableName("ss_file_import")
public class SsFileImport implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="file_import_id", type= IdType.AUTO)
	private Long fileImportId;
    /**
     * 文件上传类型, 1-年调客户雇员工资采集 2-年调社保账户雇员列表
     */
	@TableField("import_type")
	private Integer importType;
    /**
     * 关联单位Id, import_type对应业务主表的Id
     */
	@TableField("related_unit_id")
	private Long relatedUnitId;

	@TableField("import_batch_id")
	private Long importBatchId;
    /**
     * 文件服务器存储Id
     */
	@TableField("storage_file_id")
	private String storageFileId;
    /**
     * 文件服务器存储URL
     */
	@TableField("storage_file_url")
	private String storageFileUrl;
    /**
     * 上传文件名
     */
	@TableField("file_name")
	private String fileName;
    /**
     * 是否提交, 0-否 1-是
     */
    @TableField("is_submit")
    private Boolean isSubmit;
    /**
     * 是否有效, 0-无效 1-有效
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalDateTime createdTime;
    /**
     * 更新时间
     */
	@TableField("modified_time")
	private LocalDateTime modifiedTime;
    /**
     * 创建者用户Id
     */
	@TableField("created_by")
	private String createdBy;
    /**
     * 修改者用户Id
     */
	@TableField("modified_by")
	private String modifiedBy;


	public Long getFileImportId() {
		return fileImportId;
	}

	public void setFileImportId(Long fileImportId) {
		this.fileImportId = fileImportId;
	}

	public Integer getImportType() {
		return importType;
	}

	public void setImportType(Integer importType) {
		this.importType = importType;
	}

	public Long getRelatedUnitId() {
		return relatedUnitId;
	}

	public void setRelatedUnitId(Long relatedUnitId) {
		this.relatedUnitId = relatedUnitId;
	}

	public String getStorageFileId() {
		return storageFileId;
	}

	public void setStorageFileId(String storageFileId) {
		this.storageFileId = storageFileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

    public Boolean getSubmit() {
        return isSubmit;
    }

    public void setSubmit(Boolean submit) {
        isSubmit = submit;
    }

    public Long getImportBatchId() {
        return importBatchId;
    }

    public void setImportBatchId(Long importBatchId) {
        this.importBatchId = importBatchId;
    }

    public String getStorageFileUrl() {
        return storageFileUrl;
    }

    public void setStorageFileUrl(String storageFileUrl) {
        this.storageFileUrl = storageFileUrl;
    }

    @Override
	public String toString() {
		return "SsFileImport{" +
			", fileImportId=" + fileImportId +
			", importType=" + importType +
			", relatedUnitId=" + relatedUnitId +
            ", importBatchId=" + importBatchId +
			", storageFileId=" + storageFileId +
            ", storageFileUrl=" + storageFileUrl +
			", fileName=" + fileName +
            ", isSubmit=" + isSubmit +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
