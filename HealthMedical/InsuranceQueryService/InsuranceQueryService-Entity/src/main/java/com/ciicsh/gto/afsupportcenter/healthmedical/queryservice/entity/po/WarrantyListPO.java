package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-29
 */
@TableName("hm_warranty_list")
public class WarrantyListPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 在保库编号
     */
    @TableId("warranty_id")
	private Integer warrantyId;
    /**
     * 保险产品编号
     */
	@TableField("insurance_product_id")
	private Integer insuranceProductId;
    /**
     * 雇员编号
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 公司编号
     */
	@TableField("company_id")
	private Integer companyId;
    /**
     * 投保日期
     */
	@TableField("insurance_date")
	private LocalDate insuranceDate;
    /**
     * 退保日期
     */
	@TableField("surrender_date")
	private LocalDate surrenderDate;
    /**
     * 保险开始日期
     */
	@TableField("insurance_start_date")
	private LocalDate insuranceStartDate;
    /**
     * 保险结束时间
     */
	@TableField("insurance_end_date")
	private LocalDate insuranceEndDate;
    /**
     * 说明
     */
	private String remark;
    /**
     * 标的（金额或者比例）
     */
	private BigDecimal target;
    /**
     * 医疗备注（特别约定和补充医疗相关的字段，以json格式存储）
     */
	@TableField("medical_note")
	private String medicalNote;
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


	public Integer getWarrantyId() {
		return warrantyId;
	}

	public void setWarrantyId(Integer warrantyId) {
		this.warrantyId = warrantyId;
	}

	public Integer getInsuranceProductId() {
		return insuranceProductId;
	}

	public void setInsuranceProductId(Integer insuranceProductId) {
		this.insuranceProductId = insuranceProductId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public LocalDate getInsuranceDate() {
		return insuranceDate;
	}

	public void setInsuranceDate(LocalDate insuranceDate) {
		this.insuranceDate = insuranceDate;
	}

	public LocalDate getSurrenderDate() {
		return surrenderDate;
	}

	public void setSurrenderDate(LocalDate surrenderDate) {
		this.surrenderDate = surrenderDate;
	}

	public LocalDate getInsuranceStartDate() {
		return insuranceStartDate;
	}

	public void setInsuranceStartDate(LocalDate insuranceStartDate) {
		this.insuranceStartDate = insuranceStartDate;
	}

	public LocalDate getInsuranceEndDate() {
		return insuranceEndDate;
	}

	public void setInsuranceEndDate(LocalDate insuranceEndDate) {
		this.insuranceEndDate = insuranceEndDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getTarget() {
		return target;
	}

	public void setTarget(BigDecimal target) {
		this.target = target;
	}

	public String getMedicalNote() {
		return medicalNote;
	}

	public void setMedicalNote(String medicalNote) {
		this.medicalNote = medicalNote;
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
		return "WarrantyList{" +
			", warrantyId=" + warrantyId +
			", insuranceProductId=" + insuranceProductId +
			", employeeId=" + employeeId +
			", companyId=" + companyId +
			", insuranceDate=" + insuranceDate +
			", surrenderDate=" + surrenderDate +
			", insuranceStartDate=" + insuranceStartDate +
			", insuranceEndDate=" + insuranceEndDate +
			", remark=" + remark +
			", target=" + target +
			", medicalNote=" + medicalNote +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
