package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto;


import java.time.LocalDateTime;


public class SsAccountComRelationDTO{
    /**
     * 记录Id
     */
    private Long accountComRelationId;
    /**
     * 外键, 企业社保账户Id
     */
    private Long comAccountId;
    /**
     * 外键, 客户Id, 来自CMY_COMPANY
     */
    private String companyId;
    /**
     * 是否账户主客户
     */
    private Integer majorCom;
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */
    private LocalDateTime modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
    private String modifiedBy;
    /**
     * 公司名称
     */
    private String title;
    /**
     * 操作标志,1:保存,2:更新
     */
    private String saveflag;

    public Long getAccountComRelationId() {
        return accountComRelationId;
    }

    public void setAccountComRelationId(Long accountComRelationId) {
        this.accountComRelationId = accountComRelationId;
    }

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getMajorCom() {
        return majorCom;
    }

    public void setMajorCom(Integer majorCom) {
        this.majorCom = majorCom;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSaveflag() {
        return saveflag;
    }

    public void setSaveflag(String saveflag) {
        this.saveflag = saveflag;
    }
}
