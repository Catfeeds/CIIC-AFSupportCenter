package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AmArchiveUkeyBO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 材料签收日期
     */
    private LocalDate materialReceiveDate;

    /**
     * 材料送办日期
     */
    private LocalDate materialDeliveryDate;

    /**
     * 材料反馈日期
     */
    private LocalDate materialFeedbackDate;

    /**
     * 绑定的公司编号
     */
    private String companyId;

    /**
     * 绑定的公司名称
     */
    private String companyName;

    /**
     * 服务中心
     */
    private String serviceCenter;

    /**
     * 组织机构代码
     */
    private String organizationCode;

    /**
     * 公司社保登记码
     */
    private String ssAccount;

    /**
     * 客服经理
     */
    private String teamName;

    /**
     * 到期日期
     */
    private LocalDate dueDate;

    /**
     * 注销日期
     */
    private LocalDate logoutDate;

    /**
     * Ukey类别
     */
    private String keyType;

    /**
     * uKey编号
     */
    private String keyCode;

    /**
     * 档案部uKey密码
     */
    private String keyPwd;

    /**
     * Ukey序列号
     */
    private String keySeq;

    /**
     * 费用
     */
    private String keyFee;

    /**
     * 是否可用
     */
    private Integer isActive;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 最后更新时间
     */
    private Date modifiedTime;

    /**
     * 创建者登录名
     */
    private String createdBy;

    /**
     * 修改者登录名
     */
    private String modifiedBy;

    private String params;

    private List<String> param;

    /**
     * 更新方式
     */
    private String type;

    /**
     * 操作日期
     */
    private LocalDate renewDate;

    /**
     * 续签到期日期
     */
    private LocalDate renewDueDate;

    private boolean flag = false;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }

    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public LocalDate getRenewDueDate() {
        return renewDueDate;
    }

    public void setRenewDueDate(LocalDate renewDueDate) {
        this.renewDueDate = renewDueDate;
    }

    public LocalDate getRenewDate() {
        return renewDate;
    }

    public void setRenewDate(LocalDate renewDate) {
        this.renewDate = renewDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getParam() {
        return param;
    }

    public void setParam(List<String> param) {
        this.param = param;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
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
