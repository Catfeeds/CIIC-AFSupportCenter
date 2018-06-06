package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * 档案Ukey表,
 * </p>
 *
 * @author LiYueLong
 * @since 2018-05-31
 */
@TableName("am_archive_ukey")
public class AmArchiveUkey implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;

    /**
     * Ukey类别
     */
	@TableField("key_type")
	private String keyType;

    /**
     * uKey编号
     */
	@TableField("key_code")
	private String keyCode;

    /**
     * Ukey序列号
     */
	@TableField("key_seq")
	private String keySeq;

    /**
     * 档案部uKey密码
     */
    @TableField("key_pwd")
    private String keyPwd;

    /**
     * uKey状态
     */
    @TableField("key_status")
    private String keyStatus;

    /**
     * 费用
     */
    @TableField("key_fee")
    private String keyFee;

    /**
     * 绑定的公司ID
     */
    @TableField("company_id")
    private String companyId;

    /**
     * 绑定的公司名称
     */
    @TableField("company_name")
    private String companyName;

    /**
     * 是否可用
     */
    @TableField("is_active")
    private Integer isActive;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getKeySeq() {
        return keySeq;
    }

    public void setKeySeq(String keySeq) {
        this.keySeq = keySeq;
    }

    public String getKeyPwd() {
        return keyPwd;
    }

    public void setKeyPwd(String keyPwd) {
        this.keyPwd = keyPwd;
    }

    public String getKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(String keyStatus) {
        this.keyStatus = keyStatus;
    }

    public String getKeyFee() {
        return keyFee;
    }

    public void setKeyFee(String keyFee) {
        this.keyFee = keyFee;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
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
}
