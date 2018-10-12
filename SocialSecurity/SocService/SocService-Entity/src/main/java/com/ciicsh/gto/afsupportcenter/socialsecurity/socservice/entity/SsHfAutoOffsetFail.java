package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("ss_hf_auto_offset_fail")
public class SsHfAutoOffsetFail implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value="auto_offset_fail_id", type= IdType.AUTO)
    private Long autoOffsetFailId;

    @TableField("company_id")
    private String companyId;

    @TableField("employee_id")
    private String employeeId;

    @TableField("ss_hf_type")
    private Integer ssHfType;

    @TableField("offset_type")
    private Integer offsetType;

    @TableField("in_emp_task_id")
    private Long inEmpTaskId;

    @TableField("out_emp_task_id")
    private Long outEmpTaskId;

    @TableField("retry_times")
    private Integer retryTimes;

    /**
     * 是否有效, 0-无效 1-有效
     */
    @TableField("is_active")
    private Boolean isActive;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    public Long getAutoOffsetFailId() {
        return autoOffsetFailId;
    }

    public void setAutoOffsetFailId(Long autoOffsetFailId) {
        this.autoOffsetFailId = autoOffsetFailId;
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

    public Integer getSsHfType() {
        return ssHfType;
    }

    public void setSsHfType(Integer ssHfType) {
        this.ssHfType = ssHfType;
    }

    public Integer getOffsetType() {
        return offsetType;
    }

    public void setOffsetType(Integer offsetType) {
        this.offsetType = offsetType;
    }

    public Long getInEmpTaskId() {
        return inEmpTaskId;
    }

    public void setInEmpTaskId(Long inEmpTaskId) {
        this.inEmpTaskId = inEmpTaskId;
    }

    public Long getOutEmpTaskId() {
        return outEmpTaskId;
    }

    public void setOutEmpTaskId(Long outEmpTaskId) {
        this.outEmpTaskId = outEmpTaskId;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
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

    @Override
    public String toString() {
        return "SsHfAutoOffsetFail{" +
            ", autoOffsetFailId=" + autoOffsetFailId +
            ", companyId=" + companyId +
            ", employeeId=" + employeeId +
            ", ssHfType=" + ssHfType +
            ", offsetType=" + offsetType +
            ", inEmpTaskId=" + inEmpTaskId +
            ", outEmpTaskId=" + outEmpTaskId +
            ", retryTimes=" + retryTimes +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            "}";
    }
}
