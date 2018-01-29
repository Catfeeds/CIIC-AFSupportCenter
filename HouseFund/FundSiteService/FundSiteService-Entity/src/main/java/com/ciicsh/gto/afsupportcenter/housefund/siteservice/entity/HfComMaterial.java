package com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 企业客户相关材料 
 * </p>
 */
@TableName("hf_com_material")
public class HfComMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="com_material_id", type= IdType.AUTO)
	private Long comMaterialId;
    /**
     * 企业任务单Id
     */
	@TableField("com_task_id")
	private Long comTaskId;
    /**
     * 材料类型：原件、复印件、扫描件
     */
	@TableField("material_type")
	private Integer materialType;
    /**
     * 材料名称
     */
	@TableField("material_name")
	private String materialName;
    /**
     * 材料提交时间
     */
	@TableField("submit_time")
	private LocalDateTime submitTime;
    /**
     * 材料收到时间
     */
	@TableField("receive_time")
	private LocalDateTime receiveTime;
    /**
     * 说明备注
     */
	private String remark;
    /**
     * 材料收到状态: 1已收到 2 不齐全 3未收到
     */
	private Integer state;
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


	public Long getComMaterialId() {
		return comMaterialId;
	}

	public void setComMaterialId(Long comMaterialId) {
		this.comMaterialId = comMaterialId;
	}

	public Long getComTaskId() {
		return comTaskId;
	}

	public void setComTaskId(Long comTaskId) {
		this.comTaskId = comTaskId;
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

	public LocalDateTime getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(LocalDateTime submitTime) {
		this.submitTime = submitTime;
	}

	public LocalDateTime getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(LocalDateTime receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
		return "HfComMaterial{" +
			", comMaterialId=" + comMaterialId +
			", comTaskId=" + comTaskId +
			", materialType=" + materialType +
			", materialName=" + materialName +
			", submitTime=" + submitTime +
			", receiveTime=" + receiveTime +
			", remark=" + remark +
			", state=" + state +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
