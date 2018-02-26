package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.customer;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业材料收缴
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-26
 */
public class SsComMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员任务单编号
     */
	private Long comMaterialId;
    /**
     * 本地社保的雇员任务单Id
     */
	private Long comTaskId;
    /**
     * 材料类型：1 原件、2  复印件、3 扫描件
     */
	private Integer materialType;
    /**
     * 任务单提交人所属部门Id
     */
	private String materialName;
    /**
     * 发起时间
     */
	private LocalDateTime submitTime;
    /**
     * 任务发起人备注
     */
	private LocalDateTime receiveTime;
	private String remark;
    /**
     * 0： 未签收 1： 已签收 2： 材料不齐全
     */
	private Integer status;
    /**
     * 是否可用
     */
	private Boolean isActive;
    /**
     * 创建时间
     */

	private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */

	private LocalDateTime modifiedTime;
    /**
     * 创建者登录名
     */

	private String createdBy;
    /**
     * 修改者登录名
     */
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
		return "SsComMaterial{" +
			", comMaterialId=" + comMaterialId +
			", comTaskId=" + comTaskId +
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
