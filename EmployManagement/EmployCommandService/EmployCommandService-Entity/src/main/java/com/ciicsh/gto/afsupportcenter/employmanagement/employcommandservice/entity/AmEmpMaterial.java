package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 材料签收表
 * </p>
 */
@TableName("am_emp_material")
public class AmEmpMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="emp_material_id", type= IdType.AUTO)
	private Long empMaterialId;
    /**
     * 材料名称
     */
	@TableField("material_name")
	private String materialName;
    /**
     * 前道提交时间
     */
	@TableField("submit_date")
	private LocalDate submitDate;
    /**
     * 后道收到时间
     */
	@TableField("receive_date")
	private LocalDate receiveDate;
    /**
     * 收到人
     */
	@TableField("receive_man")
	private String receiveMan;
    /**
     * 批退日期
     */
	@TableField("reject_date")
	private LocalDate rejectDate;
    /**
     * 批退人
     */
	@TableField("reject_man")
	private String rejectMan;
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

    /**
     * 雇员id
     */
    @TableField("employee_id")
    private String employeeId;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmpMaterialId() {
		return empMaterialId;
	}

	public void setEmpMaterialId(Long empMaterialId) {
		this.empMaterialId = empMaterialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public LocalDate getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(LocalDate submitDate) {
		this.submitDate = submitDate;
	}

	public LocalDate getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(LocalDate receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getReceiveMan() {
		return receiveMan;
	}

	public void setReceiveMan(String receiveMan) {
		this.receiveMan = receiveMan;
	}

	public LocalDate getRejectDate() {
		return rejectDate;
	}

	public void setRejectDate(LocalDate rejectDate) {
		this.rejectDate = rejectDate;
	}

	public String getRejectMan() {
		return rejectMan;
	}

	public void setRejectMan(String rejectMan) {
		this.rejectMan = rejectMan;
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
		return "AmEmpMaterial{" +
			", empMaterialId=" + empMaterialId +
			", materialName=" + materialName +
			", submitDate=" + submitDate +
			", receiveDate=" + receiveDate +
			", receiveMan=" + receiveMan +
			", rejectDate=" + rejectDate +
			", rejectMan=" + rejectMan +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
