package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 雇员公积金备注表
 * </p>
 */
@TableName("ss_emp_remark")
public class SsEmpRemark implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="emp_remark_id", type= IdType.AUTO)
	private Long empRemarkId;
    /**
     * 客户主表ID
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 雇员ID
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 雇员本地社保档案ID
     */
	@TableField("emp_archive_id")
	private String empArchiveId;
    /**
     * 办理备注
     */
	@TableField("remark")
	private String remark;
    /**
     * 雇员任务单ID
     */
	@TableField("emp_task_id")
	private Long empTaskId;
    /**
     * 备注是否被修改过
     */
	@TableField("is_change")
	private Boolean isChange;
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
     * 创建者显示名
     */
	@TableField("created_display_name")
	private String createdDisplayName;
    /**
     * 修改者显示名
     */
	@TableField("modified_display_name")
	private String modifiedDisplayName;

    public Long getEmpRemarkId() {
        return empRemarkId;
    }

    public void setEmpRemarkId(Long empRemarkId) {
        this.empRemarkId = empRemarkId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmpArchiveId() {
        return empArchiveId;
    }

    public void setEmpArchiveId(String empArchiveId) {
        this.empArchiveId = empArchiveId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
    }

    public Boolean getChange() {
        return isChange;
    }

    public void setChange(Boolean change) {
        isChange = change;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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

    public String getCreatedDisplayName() {
        return createdDisplayName;
    }

    public void setCreatedDisplayName(String createdDisplayName) {
        this.createdDisplayName = createdDisplayName;
    }

    public String getModifiedDisplayName() {
        return modifiedDisplayName;
    }

    public void setModifiedDisplayName(String modifiedDisplayName) {
        this.modifiedDisplayName = modifiedDisplayName;
    }
}
