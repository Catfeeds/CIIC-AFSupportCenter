package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po;

import com.baomidou.mybatisplus.enums.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 补充医疗案卷表
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-29
 */
@TableName("hm_supply_medical_dossier")
public class SupplyMedicalDossier implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 案卷序号
     */
	@TableId(value="dossier_id", type= IdType.AUTO)
	private Integer dossierId;
    /**
     * 案卷号
     */
	@TableField("dossier_number")
	private String dossierNumber;
    /**
     * 导入日期
     */
	@TableField("input_date")
	private LocalDate inputDate;
    /**
     * 案卷记录数
     */
	@TableField("row_count")
	private Integer rowCount;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalTime createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
	private LocalTime modifiedTime;
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


	public Integer getDossierId() {
		return dossierId;
	}

	public void setDossierId(Integer dossierId) {
		this.dossierId = dossierId;
	}

	public String getDossierNumber() {
		return dossierNumber;
	}

	public void setDossierNumber(String dossierNumber) {
		this.dossierNumber = dossierNumber;
	}

	public LocalDate getInputDate() {
		return inputDate;
	}

	public void setInputDate(LocalDate inputDate) {
		this.inputDate = inputDate;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalTime modifiedTime) {
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
		return "SupplyMedicalDossier{" +
			", dossierId=" + dossierId +
			", dossierNumber=" + dossierNumber +
			", inputDate=" + inputDate +
			", rowCount=" + rowCount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
