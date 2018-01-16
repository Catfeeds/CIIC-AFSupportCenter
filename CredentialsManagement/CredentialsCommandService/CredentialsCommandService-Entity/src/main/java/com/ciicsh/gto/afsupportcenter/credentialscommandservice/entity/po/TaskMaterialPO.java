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
 * @Description: 证件任务单材料
 * @Date: Created in 15:13 2018/1/15
 */
@TableName("cmy_af_task_material")
public class TaskMaterialPO extends Model<TaskMaterialPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键：任务单材料id
     */
    @TableId(value="task_material_id", type= IdType.AUTO)
    private Long taskMaterialId;
    /**
     * 任务单id
     */
    @TableField("task_id")
    private String taskId;
    /**
     * 雇员id
     */
    @TableField("employee_id")
    private String employeeId;
    /**
     * 客户id
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 签收时间
     */
    @TableField("sign_in_time")
    private Date signInTime;
    /**
     * 签收人
     */
    @TableField("sign_in_people")
    private String signInPeople;
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


    public Long getTaskMaterialId() {
        return taskMaterialId;
    }

    public void setTaskMaterialId(Long taskMaterialId) {
        this.taskMaterialId = taskMaterialId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    public String getSignInPeople() {
        return signInPeople;
    }

    public void setSignInPeople(String signInPeople) {
        this.signInPeople = signInPeople;
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
        return this.taskMaterialId;
    }

    @Override
    public String toString() {
        return "TaskMaterialPO{" +
            "taskMaterialId=" + taskMaterialId +
            ", taskId=" + taskId +
            ", employeeId=" + employeeId +
            ", companyId=" + companyId +
            ", signInTime=" + signInTime +
            ", signInPeople=" + signInPeople +
            ", remark=" + remark +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
