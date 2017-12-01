package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 企业材料收缴
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@TableName("ss_com_material")
public class SsComMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员任务单编号
     */
	@TableId(value="com_material_id", type= IdType.AUTO)
	private Long comMaterialId;
    /**
     * 本地社保的雇员任务单Id
     */
	@TableField("company_task_id")
	private Long companyTaskId;
    /**
     * 材料类型：原件、复印件、扫描件
     */
	@TableField("material_type")
	private Integer materialType;
    /**
     * 任务单提交人所属部门Id
     */
	@TableField("material_name")
	private String materialName;
    /**
     * 发起时间
     */
	@TableField("submit_time")
	private LocalTime submitTime;
    /**
     * 任务发起人备注
     */
	@TableField("receive_time")
	private LocalTime receiveTime;
	private String remark;
	private Integer status;
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


	public Long getComMaterialId() {
		return comMaterialId;
	}

	public void setComMaterialId(Long comMaterialId) {
		this.comMaterialId = comMaterialId;
	}

	public Long getCompanyTaskId() {
		return companyTaskId;
	}

	public void setCompanyTaskId(Long companyTaskId) {
		this.companyTaskId = companyTaskId;
	}

	public Integer getMaterialType() {
		return materialType;
	}

	public void setMaterialType(Integer materialType) {
		this.materialType = materialType;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public LocalTime getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(LocalTime submitTime) {
		this.submitTime = submitTime;
	}

	public LocalTime getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(LocalTime receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		return "SsComMaterial{" +
			", comMaterialId=" + comMaterialId +
			", companyTaskId=" + companyTaskId +
			", materialType=" + materialType +
			", materialName=" + materialName +
			", submitTime=" + submitTime +
			", receiveTime=" + receiveTime +
			", remark=" + remark +
			", status=" + status +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
