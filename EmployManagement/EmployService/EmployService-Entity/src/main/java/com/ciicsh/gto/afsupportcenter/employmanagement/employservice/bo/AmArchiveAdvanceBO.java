package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AmArchiveAdvanceBO{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long archiveAdvanceId;

    /**
     * 档案类型 档案预增固定显示为 Cc
     */
    private String reservedArchiveType;

    /**
     * 档案编号
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
    private String archivePlace;

    /**
     * 备注
     */
    private String remark;

    /**
     * 退出寄出地日期
     */
    private LocalDate exitThePlaceDate;

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

    private String params;

    private List<String> param;

    public LocalDate getExitThePlaceDate() {
        return exitThePlaceDate;
    }

    public void setExitThePlaceDate(LocalDate exitThePlaceDate) {
        this.exitThePlaceDate = exitThePlaceDate;
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

    public String getArchivePlace() {
        return archivePlace;
    }

    public void setArchivePlace(String archivePlace) {
        this.archivePlace = archivePlace;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public List<String> getParam() {
        return param;
    }

    public void setParam(List<String> param) {
        this.param = param;
    }
}
