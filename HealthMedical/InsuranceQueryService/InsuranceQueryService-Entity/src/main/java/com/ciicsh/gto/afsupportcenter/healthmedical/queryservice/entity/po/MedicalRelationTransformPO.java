package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po;

import com.baomidou.mybatisplus.enums.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 医疗关系转移表
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
@TableName("hm_medical_relation_transform")
public class MedicalRelationTransformPO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@TableId(value="transform_id", type= IdType.AUTO)
	private Integer transformId;
	/**
	 * 雇员终身编号
	 */
	@TableField("employee_id")
	private String employeeId;
	/**
	 * 转出日期
	 */
	@TableField("turn_out_date")
	private LocalDate turnOutDate;
	/**
	 * 转出地址
	 */
	@TableField("turn_out_address")
	private String turnOutAddress;
	/**
	 * 转回日期
	 */
	@TableField("turn_back_date")
	private LocalDate turnBackDate;
	/**
	 * 备注
	 */
	private String remark;
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


	public Integer getTransformId() {
		return transformId;
	}

	public void setTransformId(Integer transformId) {
		this.transformId = transformId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getTurnOutDate() {
		return turnOutDate;
	}

	public void setTurnOutDate(LocalDate turnOutDate) {
		this.turnOutDate = turnOutDate;
	}

	public String getTurnOutAddress() {
		return turnOutAddress;
	}

	public void setTurnOutAddress(String turnOutAddress) {
		this.turnOutAddress = turnOutAddress;
	}

	public LocalDate getTurnBackDate() {
		return turnBackDate;
	}

	public void setTurnBackDate(LocalDate turnBackDate) {
		this.turnBackDate = turnBackDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		return "MedicalRelationTransform{" +
				", transformId=" + transformId +
				", employeeId=" + employeeId +
				", turnOutDate=" + turnOutDate +
				", turnOutAddress=" + turnOutAddress +
				", turnBackDate=" + turnBackDate +
				", remark=" + remark +
				", isActive=" + isActive +
				", createdTime=" + createdTime +
				", modifiedTime=" + modifiedTime +
				", createdBy=" + createdBy +
				", modifiedBy=" + modifiedBy +
				"}";
	}
}
