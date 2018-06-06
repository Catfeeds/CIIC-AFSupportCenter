package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 档案Ukey续签表,
 * </p>
 *
 * @author LiYueLong
 * @since 2018-06-5
 */
@TableName("am_archive_ukey_renew")
public class AmArchiveUkeyRenew implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;

    /**
     * Ukey主键
     */
    @TableId(value="key_id")
    private Long keyId;

    /**
     * 费用
     */
    @TableField("key_fee")
    private String keyFee;

    /**
     * 续签费用
     */
    @TableField("key_renew_fee")
    private String keyRenewFee;

    /**
     * 续签时间
     */
    @TableField("key_renew_time")
    private Date keyRenewTime;

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

    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    public String getKeyFee() {
        return keyFee;
    }

    public void setKeyFee(String keyFee) {
        this.keyFee = keyFee;
    }

    public String getKeyRenewFee() {
        return keyRenewFee;
    }

    public void setKeyRenewFee(String keyRenewFee) {
        this.keyRenewFee = keyRenewFee;
    }

    public Date getKeyRenewTime() {
        return keyRenewTime;
    }

    public void setKeyRenewTime(Date keyRenewTime) {
        this.keyRenewTime = keyRenewTime;
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
