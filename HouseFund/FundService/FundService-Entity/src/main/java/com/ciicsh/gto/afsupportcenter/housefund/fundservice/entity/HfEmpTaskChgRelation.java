package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("hf_emp_task_chg_relation")
public class HfEmpTaskChgRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value="chg_relation_id", type= IdType.AUTO)
    private Long chgRelationId;

    @TableField("company_id")
    private String companyId;

    @TableField("employee_id")
    private String employeeId;

    /**
     * 雇员任务单ID
     */
    @TableField(value="emp_task_id")
    private Long empTaskId;

    @TableField(value="pre_emp_task_id")
    private Long preEmpTaskId;

    /**
     * 是否有效, 0-无效 1-有效
     */
    @TableField("is_active")
    private Boolean isActive;

    /**
     * 创建者登录名
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    public Long getChgRelationId() {
        return chgRelationId;
    }

    public void setChgRelationId(Long chgRelationId) {
        this.chgRelationId = chgRelationId;
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

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
    }

    public Long getPreEmpTaskId() {
        return preEmpTaskId;
    }

    public void setPreEmpTaskId(Long preEmpTaskId) {
        this.preEmpTaskId = preEmpTaskId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
