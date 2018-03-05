package com.ciicsh.gto.afsupportcenter.healthmedical.entity.po;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * TPA任务单记录表
 * </p>
 *
 * @author zhaogang
 * @since 2018-02-11
 */
@TableName("hm_zy_tpa_task")
public class ZyTpaTaskPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务单编号
     */
    @TableField("af_task_id")
    private Integer afTaskId;
    /**
     * 雇员编号
     */
    @TableField("employee_id")
    private String employeeId;
    /**
     * 公司编号
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 产品编号
     */
    @TableField("zy_product_id")
    private String zyProductId;
    /**
     * 产品名称
     */
    @TableField("zy_product_name")
    private String zyProductName;
    /**
     * 保额
     */
    @TableField("key_value")
    private BigDecimal keyValue;
    /**
     * 投保日期(yyyy-MM-dd格式)
     */
    @TableField("start_confirm_date")
    private LocalDate startConfirmDate;
    /**
     * 退保开始日期(yyyy-MM-dd格式)
     */
    @TableField("end_confirm_date")
    private LocalDate endConfirmDate;
    /**
     * 最终导出的xls数据
     */
    @TableField("xls_data")
    private String xlsData;
    /**
     * 是否可用
     */
    @TableField("is_active")
    private Boolean isActive;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalTime createdTime;
    /**
     * 最后更新时间
     */
    @TableField("modified_time")
    private LocalTime modifiedTime;
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


    public Integer getAfTaskId() {
        return afTaskId;
    }

    public void setAfTaskId(Integer afTaskId) {
        this.afTaskId = afTaskId;
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

    public String getZyProductId() {
        return zyProductId;
    }

    public void setZyProductId(String zyProductId) {
        this.zyProductId = zyProductId;
    }

    public String getZyProductName() {
        return zyProductName;
    }

    public void setZyProductName(String zyProductName) {
        this.zyProductName = zyProductName;
    }

    public BigDecimal getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(BigDecimal keyValue) {
        this.keyValue = keyValue;
    }

    public LocalDate getStartConfirmDate() {
        return startConfirmDate;
    }

    public void setStartConfirmDate(LocalDate startConfirmDate) {
        this.startConfirmDate = startConfirmDate;
    }

    public LocalDate getEndConfirmDate() {
        return endConfirmDate;
    }

    public void setEndConfirmDate(LocalDate endConfirmDate) {
        this.endConfirmDate = endConfirmDate;
    }

    public String getXlsData() {
        return xlsData;
    }

    public void setXlsData(String xlsData) {
        this.xlsData = xlsData;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalTime modifiedTime) {
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
    public String toString() {
        return "ZyTpaTask{" +
            ", afTaskId=" + afTaskId +
            ", employeeId=" + employeeId +
            ", companyId=" + companyId +
            ", zyProductId=" + zyProductId +
            ", zyProductName=" + zyProductName +
            ", keyValue=" + keyValue +
            ", startConfirmDate=" + startConfirmDate +
            ", endConfirmDate=" + endConfirmDate +
            ", xlsData=" + xlsData +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
