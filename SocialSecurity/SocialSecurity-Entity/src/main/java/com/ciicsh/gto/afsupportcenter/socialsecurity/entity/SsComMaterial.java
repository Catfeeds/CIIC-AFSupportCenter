package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 企业材料收缴
 */
@Table(name = "SS_ComMaterial")
public class SsComMaterial implements Serializable {
    /**
     * 雇员任务单编号
     */
    @Id
    @Column(name = "ComMaterialId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comMaterialId;

    /**
     * 本地社保的雇员任务单Id
     */
    @Column(name = "CompanyTaskId")
    private String companyTaskId;

    /**
     * 材料类型：原件、复印件、扫描件
     */
    @Column(name = "MaterialType")
    private Integer materialType;

    /**
     * 任务单提交人所属部门Id
     */
    @Column(name = "MaterialName")
    private String materialName;

    /**
     * 发起时间
     */
    @Column(name = "SubmitTime")
    private LocalDateTime submitTime;

    /**
     * 任务发起人备注
     */
    @Column(name = "ReceiveTime")
    private LocalDateTime receiveTime;

    @Column(name = "Remark")
    private String remark;

    @Column(name = "Status")
    private Integer status;

    /**
     * 是否可用
     */
    @Column(name = "IsActive")
    private Boolean isActive;

    /**
     * 创建时间
     */
    @Column(name = "DataChange_CreateTime")
    private LocalDateTime dataChangeCreateTime;

    /**
     * 最后更新时间
     */
    @Column(name = "DataChange_LastTime")
    private LocalDateTime dataChangeLastTime;

    /**
     * 创建者登录名
     */
    @Column(name = "CreatedBy")
    private String createdBy;

    /**
     * 修改者登录名
     */
    @Column(name = "ModifiedBy")
    private String modifiedBy;

    private static final long serialVersionUID = 1L;

    /**
     * 获取雇员任务单编号
     *
     * @return ComMaterialId - 雇员任务单编号
     */
    public Long getComMaterialId() {
        return comMaterialId;
    }

    /**
     * 设置雇员任务单编号
     *
     * @param comMaterialId 雇员任务单编号
     */
    public void setComMaterialId(Long comMaterialId) {
        this.comMaterialId = comMaterialId;
    }

    /**
     * 获取本地社保的雇员任务单Id
     *
     * @return CompanyTaskId - 本地社保的雇员任务单Id
     */
    public String getCompanyTaskId() {
        return companyTaskId;
    }

    /**
     * 设置本地社保的雇员任务单Id
     *
     * @param companyTaskId 本地社保的雇员任务单Id
     */
    public void setCompanyTaskId(String companyTaskId) {
        this.companyTaskId = companyTaskId == null ? null : companyTaskId.trim();
    }

    /**
     * 获取材料类型：原件、复印件、扫描件
     *
     * @return MaterialType - 材料类型：原件、复印件、扫描件
     */
    public Integer getMaterialType() {
        return materialType;
    }

    /**
     * 设置材料类型：原件、复印件、扫描件
     *
     * @param materialType 材料类型：原件、复印件、扫描件
     */
    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
    }

    /**
     * 获取任务单提交人所属部门Id
     *
     * @return MaterialName - 任务单提交人所属部门Id
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * 设置任务单提交人所属部门Id
     *
     * @param materialName 任务单提交人所属部门Id
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    /**
     * 获取发起时间
     *
     * @return SubmitTime - 发起时间
     */
    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置发起时间
     *
     * @param submitTime 发起时间
     */
    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 获取任务发起人备注
     *
     * @return ReceiveTime - 任务发起人备注
     */
    public LocalDateTime getReceiveTime() {
        return receiveTime;
    }

    /**
     * 设置任务发起人备注
     *
     * @param receiveTime 任务发起人备注
     */
    public void setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * @return Remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取是否可用
     *
     * @return IsActive - 是否可用
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * 设置是否可用
     *
     * @param isActive 是否可用
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取创建时间
     *
     * @return DataChange_CreateTime - 创建时间
     */
    public LocalDateTime getDataChangeCreateTime() {
        return dataChangeCreateTime;
    }

    /**
     * 设置创建时间
     *
     * @param dataChangeCreateTime 创建时间
     */
    public void setDataChangeCreateTime(LocalDateTime dataChangeCreateTime) {
        this.dataChangeCreateTime = dataChangeCreateTime;
    }

    /**
     * 获取最后更新时间
     *
     * @return DataChange_LastTime - 最后更新时间
     */
    public LocalDateTime getDataChangeLastTime() {
        return dataChangeLastTime;
    }

    /**
     * 设置最后更新时间
     *
     * @param dataChangeLastTime 最后更新时间
     */
    public void setDataChangeLastTime(LocalDateTime dataChangeLastTime) {
        this.dataChangeLastTime = dataChangeLastTime;
    }

    /**
     * 获取创建者登录名
     *
     * @return CreatedBy - 创建者登录名
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建者登录名
     *
     * @param createdBy 创建者登录名
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * 获取修改者登录名
     *
     * @return ModifiedBy - 修改者登录名
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * 设置修改者登录名
     *
     * @param modifiedBy 修改者登录名
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }
}