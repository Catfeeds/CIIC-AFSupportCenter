package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: guwei
 * @Description: 单项雇员
 * @Date: Created in 14:05 2018/2/26
 */
@TableName("emp_other_employee")
public class EmployeeOther extends Model<EmployeeOther> {

    /**
     * 单项雇员编号
     */
    @TableId(value = "other_employee_id", type= IdType.AUTO)
    private String otherEmployeeId;
    /**
     * 雇员编号
     */
    @TableField("employee_id")
    private String employeeId;
    /**
     * 客户编号
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 婚姻状况
     */
    @TableField("marriage_status")
    private Integer marriageStatus;
    /**
     * 现居地址
     */
    @TableField("address")
    private String address;
    /**
     * 备注
     */
    @TableField("remark")
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

    public Integer getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(Integer marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOtherEmployeeId() {
        return otherEmployeeId;
    }

    public void setOtherEmployeeId(String otherEmployeeId) {
        this.otherEmployeeId = otherEmployeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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

    public void setActive(Boolean active) {
        isActive = active;
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
        return otherEmployeeId;
    }

    @Override
    public String toString() {
        return "EmployeeOther{" +
            "otherEmployeeId='" + otherEmployeeId + '\'' +
            ", employeeId='" + employeeId + '\'' +
            ", companyId='" + companyId + '\'' +
            ", remark='" + remark + '\'' +
            ", isActive=" + isActive +
            ", address=" + address +
            ", marryStatus=" + marriageStatus +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            '}';
    }
}
