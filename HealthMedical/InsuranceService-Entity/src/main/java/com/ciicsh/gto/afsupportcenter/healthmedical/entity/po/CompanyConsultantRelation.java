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
 * 客户和客户业务顾问关联表
 * </p>
 *
 * @author xiweizhen
 */
@TableName("sal_company_consultant_relation")
public class CompanyConsultantRelation extends Model<CompanyConsultantRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "company_consultant_id", type = IdType.AUTO)
    private Long companyConsultantId;
    /**
     * af客户ID，关联AF客户信息表ID
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 公司业务类型 1:AF  2:BPO 3:FC
     */
    @TableField("branch_flag")
    private Integer branchFlag;
    /**
     * 顾问Id，系统用户信息ID
     */
    @TableField("sys_user_id")
    private String sysUserId;
    /**
     * 顾问姓名
     */
    @TableField("consultant_name")
    private String consultantName;
    /**
     * 是否有效 1:有效 0:无效
     */
    @TableField("is_active")
    private Boolean isActive;
    /**
     * 数据创建时间
     */
    @TableField("created_time")
    private Date createdTime;
    /**
     * 最后修改时间
     */
    @TableField("modified_time")
    private Date modifiedTime;
    /**
     * 数据创建人
     */
    @TableField("created_by")
    private String createdBy;
    /**
     * 最后修改人
     */
    @TableField("modified_by")
    private String modifiedBy;


    public Long getCompanyConsultantId() {
        return companyConsultantId;
    }

    public void setCompanyConsultantId(Long companyConsultantId) {
        this.companyConsultantId = companyConsultantId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getBranchFlag() {
        return branchFlag;
    }

    public void setBranchFlag(Integer branchFlag) {
        this.branchFlag = branchFlag;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
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
        return this.companyConsultantId;
    }

    @Override
    public String toString() {
        return "CompanyConsultantRelation{" +
            ", companyConsultantId=" + companyConsultantId +
            ", companyId=" + companyId +
            ", branchFlag=" + branchFlag +
            ", sysUserId=" + sysUserId +
            ", consultantName=" + consultantName +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
