package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 雇员公积金账号预录入表
 * </p>
 */
@TableName("hf_emp_pre_input")
public class HfEmpPreInput implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员本地社保档案Id
     */
	@TableId(value="input_id", type= IdType.AUTO)
	private Long inputId;
    /**
     * 雇员主表ID
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 雇员证件号
     */
	@TableField("id_num")
	private String idNum;
    /**
     * 雇员姓名
     */
	@TableField("employee_name")
	private String employeeName;
    /**
     * 雇员基本公积金账号
     */
    @TableField(value="hf_emp_bas_account")
	private String hfEmpBasAccount;
    /**
     * 雇员补充公积金账号
     */
    @TableField(value="hf_emp_add_account")
    private String hfEmpAddAccount;
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

    public Long getInputId() {
        return inputId;
    }

    public void setInputId(Long inputId) {
        this.inputId = inputId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getHfEmpBasAccount() {
        return hfEmpBasAccount;
    }

    public void setHfEmpBasAccount(String hfEmpBasAccount) {
        this.hfEmpBasAccount = hfEmpBasAccount;
    }

    public String getHfEmpAddAccount() {
        return hfEmpAddAccount;
    }

    public void setHfEmpAddAccount(String hfEmpAddAccount) {
        this.hfEmpAddAccount = hfEmpAddAccount;
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

    @Override
	public String toString() {
		return "HfEmpArchive{" +
			", inputId=" + inputId +
			", employeeId=" + employeeId +
			", idNum=" + idNum +
			", employeeName=" + employeeName +
			", hfEmpBasAccount=" + hfEmpBasAccount +
			", hfEmpAddAccount=" + hfEmpAddAccount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
