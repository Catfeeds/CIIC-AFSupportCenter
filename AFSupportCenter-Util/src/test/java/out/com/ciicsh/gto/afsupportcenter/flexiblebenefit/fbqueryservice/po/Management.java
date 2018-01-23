package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.po;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 管理方
 * </p>
 */
@TableName("sal_management")
public class Management extends Model<Management> {

    private static final long serialVersionUID = 1L;

    /**
     * 管理方ID
     */
    @TableId("management_id")
    private String managementId;
    /**
     * 管理方名称
     */
    private String title;
    /**
     * 管理方描述
     */
    private String description;
    /**
     * 客户数量
     */
    @TableField("company_quantity")
    private Integer companyQuantity;
    /**
     * 雇员数量
     */
    @TableField("employee_number")
    private Integer employeeNumber;
    /**
     * 是否全球500强
     */
    @TableField("is_five_hundred")
    private Boolean isFiveHundred;
    /**
     * 其它名称
     */
    @TableField("other_name")
    private String otherName;
    /**
     * 来源 0：未知 1： 自主开发  2： 交叉销售 3： BD分配
     */
    private Integer source;
    /**
     * 管理方状态
     * 0-草稿
     * 1-审批中
     * 2-审批通过
     * 3-审批审批拒绝
     */
    private Integer status;
    /**
     * 是否为渠道方
     */
    @TableField("is_channel")
    private Boolean isChannel;
    /**
     * 母公司国家
     */
    @TableField("country_code")
    private String countryCode;
    /**
     * 知名产品
     */
    @TableField("famous_product")
    private String famousProduct;
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
     * 创建者
     */
    @TableField("created_by")
    private String createdBy;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;
    /**
     * 最后修改者
     */
    @TableField("modified_by")
    private String modifiedBy;
    /**
     * 最后修改时间
     */
    @TableField("modified_time")
    private Date modifiedTime;


    public String getManagementId() {
        return managementId;
    }

    public void setManagementId(String managementId) {
        this.managementId = managementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCompanyQuantity() {
        return companyQuantity;
    }

    public void setCompanyQuantity(Integer companyQuantity) {
        this.companyQuantity = companyQuantity;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Boolean getFiveHundred() {
        return isFiveHundred;
    }

    public void setFiveHundred(Boolean isFiveHundred) {
        this.isFiveHundred = isFiveHundred;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getChannel() {
        return isChannel;
    }

    public void setChannel(Boolean isChannel) {
        this.isChannel = isChannel;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFamousProduct() {
        return famousProduct;
    }

    public void setFamousProduct(String famousProduct) {
        this.famousProduct = famousProduct;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.managementId;
    }

    @Override
    public String toString() {
        return "Management{" +
            ", managementId=" + managementId +
            ", title=" + title +
            ", description=" + description +
            ", companyQuantity=" + companyQuantity +
            ", employeeNumber=" + employeeNumber +
            ", isFiveHundred=" + isFiveHundred +
            ", otherName=" + otherName +
            ", source=" + source +
            ", status=" + status +
            ", isChannel=" + isChannel +
            ", countryCode=" + countryCode +
            ", famousProduct=" + famousProduct +
            ", remark=" + remark +
            ", isActive=" + isActive +
            ", createdBy=" + createdBy +
            ", createdTime=" + createdTime +
            ", modifiedBy=" + modifiedBy +
            ", modifiedTime=" + modifiedTime +
            "}";
    }
}
