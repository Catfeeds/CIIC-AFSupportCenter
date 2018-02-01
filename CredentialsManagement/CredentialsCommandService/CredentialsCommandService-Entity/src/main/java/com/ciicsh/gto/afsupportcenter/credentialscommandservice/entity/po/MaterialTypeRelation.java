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
 * @Description: 证件类型与材料关系表
 * @Date: Created in 15:10 2018/1/15
 */
@TableName("cm_material_type_relation")
public class MaterialTypeRelation extends Model<MaterialTypeRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键：证件类型与材料关系表id
     */
    @TableId(value="material_type_relation_id", type= IdType.AUTO)
    private Long materialTypeRelationId;
    /**
     * 证件类型(1：积分办理、2：居住证B证、3：留学生落户、4：居转户、5：夫妻分局、6：人才引进)
     */
    @TableField("credentials_type")
    private Integer credentialsType;
    /**
     * 证件办理类型(1：积分申请、2：积分确认、3：信息变更、4：随员、5：重置密码、6：新办、7：继办、8：随员、9：个人信息修改、10：挂失、11、补办)
     */
    @TableField("credentials_deal_type")
    private Integer credentialsDealType;
    /**
     * 层级
     */
    private String level;
    /**
     * 材料id
     */
    @TableField("material_id")
    private Long materialId;
    /**
     * 材料名称
     */
    @TableField("material_name")
    private String materialName;
    /**
     * 材料级别(1 2 3)
     */
    @TableField("material_level")
    private Integer materialLevel;
    /**
     * 父材料ID
     */
    @TableField("material_pid")
    private Long materialPid;
    /**
     * 类型(1：单选 、2：复选)
     */
    private Integer type;
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


    public Long getMaterialTypeRelationId() {
        return materialTypeRelationId;
    }

    public void setMaterialTypeRelationId(Long materialTypeRelationId) {
        this.materialTypeRelationId = materialTypeRelationId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(Integer credentialsType) {
        this.credentialsType = credentialsType;
    }

    public Integer getCredentialsDealType() {
        return credentialsDealType;
    }

    public void setCredentialsDealType(Integer credentialsDealType) {
        this.credentialsDealType = credentialsDealType;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getMaterialLevel() {
        return materialLevel;
    }

    public void setMaterialLevel(Integer materialLevel) {
        this.materialLevel = materialLevel;
    }

    public Long getMaterialPid() {
        return materialPid;
    }

    public void setMaterialPid(Long materialPid) {
        this.materialPid = materialPid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        return this.materialTypeRelationId;
    }

    @Override
    public String toString() {
        return "MaterialTypeRelation{" +
            "materialTypeRelationId=" + materialTypeRelationId +
            ", credentialsType=" + credentialsType +
            ", credentialsDealType=" + credentialsDealType +
            ", materialId=" + materialId +
            ", materialName=" + materialName +
            ", materialLevel=" + materialLevel +
            ", materialPid=" + materialPid +
            ", type=" + type +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
