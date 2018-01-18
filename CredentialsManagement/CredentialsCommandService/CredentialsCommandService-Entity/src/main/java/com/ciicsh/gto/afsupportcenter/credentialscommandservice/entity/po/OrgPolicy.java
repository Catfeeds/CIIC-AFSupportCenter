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
 * @Description: 办理机构政策
 * @Date: Created in 15:11 2018/1/15
 */
@TableName("cm_org_policy")
public class OrgPolicy extends Model<OrgPolicy> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键:办理机构政策id
     */
    @TableId(value="org_poilcy_id", type= IdType.AUTO)
    private Long orgPoilcyId;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 证件类型(1：积分办理、2：居住证B证、3：留学生落户、4：居转户、5：夫妻分局、6：人才引进)
     */
    private Integer type;
    /**
     * 机构政策内容
     */
    @TableField("policy_description")
    private String policyDescription;
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


    public Long getOrgPoilcyId() {
        return orgPoilcyId;
    }

    public void setOrgPoilcyId(Long orgPoilcyId) {
        this.orgPoilcyId = orgPoilcyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPolicyDescription() {
        return policyDescription;
    }

    public void setPolicyDescription(String policyDescription) {
        this.policyDescription = policyDescription;
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
        return this.orgPoilcyId;
    }

    @Override
    public String toString() {
        return "OrgPolicy{" +
            "orgPoilcyId=" + orgPoilcyId +
            ", name=" + name +
            ", type=" + type +
            ", policyDescription=" + policyDescription +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
