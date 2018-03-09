package com.ciicsh.gto.afsupportcenter.healthmedical.entity.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 医疗关系转移表
 * </p>
 *
 * @author 赵刚
 * @since 2018-03-09
 */
@TableName("hm_medical_relation_transform")
public class MedicalRelationTransformPO extends Model<MedicalRelationTransformPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "transform_id", type = IdType.AUTO)
    private Integer transformId;
    /**
     * 雇员终身编号
     */
    @TableField("employee_id")
    private String employeeId;
    @TableField("employee_name")
    private String employeeName;
    @TableField("company_id")
    private String companyId;
    @TableField("company_name")
    private String companyName;
    @TableField("id_num")
    private String idNum;

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    /**
     * 转出日期
     */
    @TableField("turn_out_date")
    private Date turnOutDate;
    /**
     * 转出地址
     */
    @TableField("turn_out_address")
    private String turnOutAddress;
    /**
     * 转回日期
     */
    @TableField("turn_back_date")
    private Date turnBackDate;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否可用
     */
    @TableField("is_active")
    private Boolean isActive;
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


    public Integer getTransformId() {
        return transformId;
    }

    public void setTransformId(Integer transformId) {
        this.transformId = transformId;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getTurnOutDate() {
        return turnOutDate;
    }

    public void setTurnOutDate(Date turnOutDate) {
        this.turnOutDate = turnOutDate;
    }

    public String getTurnOutAddress() {
        return turnOutAddress;
    }

    public void setTurnOutAddress(String turnOutAddress) {
        this.turnOutAddress = turnOutAddress;
    }

    public Date getTurnBackDate() {
        return turnBackDate;
    }

    public void setTurnBackDate(Date turnBackDate) {
        this.turnBackDate = turnBackDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
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

    @Override
    protected Serializable pkVal() {
        return this.transformId;
    }

    @Override
    public String toString() {
        return "MedicalRelationTransformPO{" +
            ", transformId=" + transformId +
            ", employeeId=" + employeeId +
            ", employeeName=" + employeeName +
            ", companyId=" + companyId +
            ", companyName=" + companyName +
            ", turnOutDate=" + turnOutDate +
            ", turnOutAddress=" + turnOutAddress +
            ", turnBackDate=" + turnBackDate +
            ", remark=" + remark +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
