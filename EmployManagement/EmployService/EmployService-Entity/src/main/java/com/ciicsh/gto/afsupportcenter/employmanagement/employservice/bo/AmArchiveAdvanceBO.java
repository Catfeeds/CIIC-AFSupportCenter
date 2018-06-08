package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveAdvance;

import java.time.LocalDate;
import java.util.Date;

public class AmArchiveAdvanceBO{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long archiveAdvanceId;

    /**
     * 预留档案类型 A、Aa、B、Bb......Z、Zz
     */
    private String reservedArchiveType;

    /**
     * 预留档案编号
     */
    private Integer reservedArchiveNo;

    /**
     * 匹配的任务单id 当status=2时update此字段
     */
    private Integer amEmpTaskId;

    /**
     * 匹配的雇员编号当status=2时update此字段
     */
    private String employeeId;

    /**
     * 雇员姓名
     */
    private String employeeName;

    /**
     * 身份证号
     */
    private String employeeIdcardNo;

    /**
     * 入库日期
     */
    private LocalDate enteringDate;

    /**
     * 档案来源
     */
    private String archiveSource;

    /**
     * 存档地
     */
    private String archivalPlace;

    /**
     * 删除备注
     */
    private String deleteRemark;

    /**
     * 状态0：删除1：未匹配2：已匹配
     */
    private Integer status;

    /**
     * 是否可用
     */
    private Integer isActive;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 最后更新时间
     */
    private Date modifiedTime;

    /**
     * 创建者登录名
     */
    private String createdBy;

    /**
     * 修改者登录名
     */
    private String modifiedBy;

    private boolean exist;

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

    public String getDeleteRemark() {
        return deleteRemark;
    }

    public void setDeleteRemark(String deleteRemark) {
        this.deleteRemark = deleteRemark;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
