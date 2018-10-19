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
     * 材料签收日期
     */
    @TableField("material_receive_date")
    private LocalDate materialReceiveDate;

    /**
     * 材料送办日期
     */
    @TableField("material_delivery_date")
    private LocalDate materialDeliveryDate;

    /**
     * 材料反馈日期
     */
    @TableField("material_feedback_date")
    private LocalDate materialFeedbackDate;

    /**
     * 绑定的公司编号
     */
    @TableField("company_id")
    private String companyId;

    /**
     * 绑定的公司名称
     */
    @TableField("company_name")
    private String companyName;

    /**
     * 服务中心
     */
    @TableField("service_center")
    private String serviceCenter;

    /**
     * 组织机构代码
     */
    @TableField("organization_code")
    private String organizationCode;

    /**
     * 到期日期
     */
    @TableField("due_date")
    private LocalDate dueDate;

    /**
     * 注销日期
     */
    @TableField("logout_date")
    private LocalDate logoutDate;

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
     * 档案部uKey密码
     */
    @TableField("key_pwd")
    private String keyPwd;

    /**
     * Ukey序列号
     */
	@TableField("key_seq")
	private String keySeq;

    /**
     * 费用
     */
    @TableField("key_fee")
    private String keyFee;

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

    /**
     * team 客服经理
     */
    @TableField("team")
    private String team;

    /**
     * 社保登记码
     */
    @TableField("ss_account")
    private String ssAccount;

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getMaterialReceiveDate() {
        return materialReceiveDate;
    }

    public void setMaterialReceiveDate(LocalDate materialReceiveDate) {
        this.materialReceiveDate = materialReceiveDate;
    }

    public LocalDate getMaterialDeliveryDate() {
        return materialDeliveryDate;
    }

    public void setMaterialDeliveryDate(LocalDate materialDeliveryDate) {
        this.materialDeliveryDate = materialDeliveryDate;
    }

    public LocalDate getMaterialFeedbackDate() {
        return materialFeedbackDate;
    }

    public void setMaterialFeedbackDate(LocalDate materialFeedbackDate) {
        this.materialFeedbackDate = materialFeedbackDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(LocalDate logoutDate) {
        this.logoutDate = logoutDate;
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

    public String getKeyPwd() {
        return keyPwd;
    }

    public void setKeyPwd(String keyPwd) {
        this.keyPwd = keyPwd;
    }

    public String getKeySeq() {
        return keySeq;
    }

    public void setKeySeq(String keySeq) {
        this.keySeq = keySeq;
    }

    public String getKeyFee() {
        return keyFee;
    }

    public void setKeyFee(String keyFee) {
        this.keyFee = keyFee;
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
