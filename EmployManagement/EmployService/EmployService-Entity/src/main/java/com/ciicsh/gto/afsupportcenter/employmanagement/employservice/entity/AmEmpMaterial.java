package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 材料签收表
 * </p>
 *
 * @author xsj
 * @since 2018-03-26
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
    @TableField("submitter_id")
    private String submitterId;
    /**
     * 材料提交人
     */
    @TableField("submitter_name")
    private String submitterName;
    /**
     * 前道提交时间
     */
    @TableField("submitter_date")
    private LocalDateTime submitterDate;
    @TableField("receive_id")
    private String receiveId;
    /**
     * 收到人
     */
    @TableField("receive_name")
    private String receiveName;
    /**
     * 后道收到时间
     */
    @TableField("receive_date")
    private LocalDate receiveDate;
    @TableField("reject_id")
    private String rejectId;
    /**
     * 批退人
     */
    @TableField("reject_name")
    private String rejectName;
    /**
     * 批退日期
     */
    @TableField("reject_date")
    private LocalDate rejectDate;
    /**
     * 材料批退原因
     */
    @TableField("reject_reason")
    private String rejectReason;
    /**
     * 1 用工材料签收
     2 退工归还材料签收
     */
    @TableField("operate_type")
    private Integer operateType;
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


    @TableId(value="emp_task_id")
    private Long empTaskId;

    @TableId(value="extension")
    private  String extension;

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
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

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName;
    }

    public LocalDateTime getSubmitterDate() {
        return submitterDate;
    }

    public void setSubmitterDate(LocalDateTime submitterDate) {
        this.submitterDate = submitterDate;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getRejectId() {
        return rejectId;
    }

    public void setRejectId(String rejectId) {
        this.rejectId = rejectId;
    }

    public String getRejectName() {
        return rejectName;
    }

    public void setRejectName(String rejectName) {
        this.rejectName = rejectName;
    }

    public LocalDate getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(LocalDate rejectDate) {
        this.rejectDate = rejectDate;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "AmEmpMaterial{" +
            "empMaterialId=" + empMaterialId +
            ", materialName=" + materialName +
            ", submitterId=" + submitterId +
            ", submitterName=" + submitterName +
            ", submitterDate=" + submitterDate +
            ", receiveId=" + receiveId +
            ", receiveName=" + receiveName +
            ", receiveDate=" + receiveDate +
            ", rejectId=" + rejectId +
            ", rejectName=" + rejectName +
            ", rejectDate=" + rejectDate +
            ", rejectReason=" + rejectReason +
            ", operateType=" + operateType +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            ", employeeId=" + employeeId +
            "}";
    }
}
