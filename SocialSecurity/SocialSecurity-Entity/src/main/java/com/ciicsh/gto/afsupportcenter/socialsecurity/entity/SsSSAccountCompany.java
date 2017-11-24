package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 企业社保账户与公司关系表，当企业社保账户性质是独立库，例如：欧莱雅有10家子公司，在中智就一个社保账户
 */
@Table(name = "SS_SSAccountCompany")
public class SsSSAccountCompany implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "SSAccountCompanyId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SSAccountCompanyId;

    /**
     * 外键, 企业社保账户Id
     */
    @Column(name = "ComAccountId")
    private String comAccountId;

    /**
     * 外键, 客户Id, 来自gtofrontdb.CMY_COMPANY
     */
    @Column(name = "CompanyId")
    private String companyId;

    /**
     * 是否可用
     */
    @Column(name = "IsActive")
    private Boolean isActive;

    /**
     * 创建时间
     */
    @Column(name = "DataChange_CreateTime")
    private LocalDateTime dataChangeCreateTime;

    /**
     * 最后更新时间
     */
    @Column(name = "DataChange_LastTime")
    private LocalDateTime dataChangeLastTime;

    /**
     * 创建者登录名
     */
    @Column(name = "CreatedBy")
    private String createdBy;

    /**
     * 修改者登录名
     */
    @Column(name = "ModifiedBy")
    private String modifiedBy;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id
     *
     * @return SSAccountCompanyId - 记录Id
     */
    public Long getSSAccountCompanyId() {
        return SSAccountCompanyId;
    }

    /**
     * 设置记录Id
     *
     * @param SSAccountCompanyId 记录Id
     */
    public void setSSAccountCompanyId(Long SSAccountCompanyId) {
        this.SSAccountCompanyId = SSAccountCompanyId;
    }

    /**
     * 获取外键, 企业社保账户Id
     *
     * @return ComAccountId - 外键, 企业社保账户Id
     */
    public String getComAccountId() {
        return comAccountId;
    }

    /**
     * 设置外键, 企业社保账户Id
     *
     * @param comAccountId 外键, 企业社保账户Id
     */
    public void setComAccountId(String comAccountId) {
        this.comAccountId = comAccountId == null ? null : comAccountId.trim();
    }

    /**
     * 获取外键, 客户Id, 来自gtofrontdb.CMY_COMPANY
     *
     * @return CompanyId - 外键, 客户Id, 来自gtofrontdb.CMY_COMPANY
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置外键, 客户Id, 来自gtofrontdb.CMY_COMPANY
     *
     * @param companyId 外键, 客户Id, 来自gtofrontdb.CMY_COMPANY
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * 获取是否可用
     *
     * @return IsActive - 是否可用
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * 设置是否可用
     *
     * @param isActive 是否可用
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取创建时间
     *
     * @return DataChange_CreateTime - 创建时间
     */
    public LocalDateTime getDataChangeCreateTime() {
        return dataChangeCreateTime;
    }

    /**
     * 设置创建时间
     *
     * @param dataChangeCreateTime 创建时间
     */
    public void setDataChangeCreateTime(LocalDateTime dataChangeCreateTime) {
        this.dataChangeCreateTime = dataChangeCreateTime;
    }

    /**
     * 获取最后更新时间
     *
     * @return DataChange_LastTime - 最后更新时间
     */
    public LocalDateTime getDataChangeLastTime() {
        return dataChangeLastTime;
    }

    /**
     * 设置最后更新时间
     *
     * @param dataChangeLastTime 最后更新时间
     */
    public void setDataChangeLastTime(LocalDateTime dataChangeLastTime) {
        this.dataChangeLastTime = dataChangeLastTime;
    }

    /**
     * 获取创建者登录名
     *
     * @return CreatedBy - 创建者登录名
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建者登录名
     *
     * @param createdBy 创建者登录名
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * 获取修改者登录名
     *
     * @return ModifiedBy - 修改者登录名
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * 设置修改者登录名
     *
     * @param modifiedBy 修改者登录名
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }
}