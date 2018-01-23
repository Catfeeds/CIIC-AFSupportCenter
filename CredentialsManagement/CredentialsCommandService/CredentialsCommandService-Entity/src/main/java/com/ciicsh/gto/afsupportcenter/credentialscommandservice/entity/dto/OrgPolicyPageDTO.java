package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 17:46 2018/1/16
 */
public class OrgPolicyPageDTO implements Serializable{

    /**
     * 主键:办理机构政策id
     */
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
    private String policyDescription;
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;
    /**
     * 最后更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
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
    public String toString() {
        return "OrgPolicyPageDTO{" +
            "orgPoilcyId=" + orgPoilcyId +
            ", name='" + name + '\'' +
            ", type=" + type +
            ", policyDescription='" + policyDescription + '\'' +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            '}';
    }
}
