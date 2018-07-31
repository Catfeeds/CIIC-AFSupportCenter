package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * 档案预增表,
 * </p>
 *
 * @author LiYueLong
 * @since 2018-05-24
 */
@TableName("am_archive_advance")
public class AmArchiveAdvance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="archive_advance_id", type= IdType.AUTO)
	private Long archiveAdvanceId;

    /**
     * 档案类型 档案预增固定为Cc
     */
	@TableField("reserved_archive_type")
	private String reservedArchiveType;

    /**
     * 档案编号
     */
	@TableField("reserved_archive_no")
	private Integer reservedArchiveNo;

    /**
     * 匹配的任务单id 当status=2时update此字段
     */
	@TableField("am_emp_task_id")
	private Integer amEmpTaskId;

    /**
     * 匹配的雇员编号当status=2时update此字段
     */
    @TableField("employee_id")
    private String employeeId;

    /**
     * 雇员姓名
     */
    @TableField("employee_name")
    private String employeeName;

    /**
     * 身份证号
     */
    @TableField("employee_idcard_no")
    private String employeeIdcardNo;

    /**
     * 入库日期
     */
    @TableField("entering_date")
    private LocalDate enteringDate;

    /**
     * 档案来源
     */
    @TableField("archive_source")
    private String archiveSource;

    /**
     * 存档地
     */
    @TableField("archival_place")
    private String archivalPlace;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 退出寄出地日期
     */
    @TableField("exit_the_place_date")
    private LocalDate exitThePlaceDate;

    /**
     * 状态0：删除1：未匹配2：已匹配
     */
    @TableField("status")
    private Integer status;

    /**
     * 是否可用
     */
    @TableField("is_active")
    private Integer isActive;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;

    /**
     * 最后更新时间
     */
    @TableField("modified_time")
    private Date modifiedTime;

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

    public LocalDate getExitThePlaceDate() {
        return exitThePlaceDate;
    }

    public void setExitThePlaceDate(LocalDate exitThePlaceDate) {
        this.exitThePlaceDate = exitThePlaceDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getArchiveAdvanceId() {
        return archiveAdvanceId;
    }

    public void setArchiveAdvanceId(Long archiveAdvanceId) {
        this.archiveAdvanceId = archiveAdvanceId;
    }

    public String getReservedArchiveType() {
        return reservedArchiveType;
    }

    public void setReservedArchiveType(String reservedArchiveType) {
        this.reservedArchiveType = reservedArchiveType;
    }

    public Integer getReservedArchiveNo() {
        return reservedArchiveNo;
    }

    public void setReservedArchiveNo(Integer reservedArchiveNo) {
        this.reservedArchiveNo = reservedArchiveNo;
    }

    public Integer getAmEmpTaskId() {
        return amEmpTaskId;
    }

    public void setAmEmpTaskId(Integer amEmpTaskId) {
        this.amEmpTaskId = amEmpTaskId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeIdcardNo() {
        return employeeIdcardNo;
    }

    public void setEmployeeIdcardNo(String employeeIdcardNo) {
        this.employeeIdcardNo = employeeIdcardNo;
    }

    public LocalDate getEnteringDate() {
        return enteringDate;
    }

    public void setEnteringDate(LocalDate enteringDate) {
        this.enteringDate = enteringDate;
    }

    public String getArchiveSource() {
        return archiveSource;
    }

    public void setArchiveSource(String archiveSource) {
        this.archiveSource = archiveSource;
    }

    public String getArchivalPlace() {
        return archivalPlace;
    }

    public void setArchivalPlace(String archivalPlace) {
        this.archivalPlace = archivalPlace;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
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
}
