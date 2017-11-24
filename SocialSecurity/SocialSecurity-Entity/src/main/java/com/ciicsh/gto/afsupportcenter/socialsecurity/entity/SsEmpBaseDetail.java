package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 雇员社保汇缴基数明细表，
该表细化到每一个社保险种的月度段的基数、比例、公司缴纳金额、个人缴纳金额
 */
@Table(name = "SS_EmpBaseDetail")
public class SsEmpBaseDetail implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "EmpBaseDetailId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empBaseDetailId;

    /**
     * 外键,雇员社保汇缴基数Id
     */
    @Column(name = "EmpBasePeriodId")
    private Long empBasePeriodId;

    /**
     * 外键，雇员本地社保档案Id
     */
    @Column(name = "EmpArchivedId")
    private String empArchivedId;

    /**
     * 社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     */
    @Column(name = "SSType")
    private Byte SSType;

    /**
     * 社保险种名称
     */
    @Column(name = "SSTypeName")
    private String SSTypeName;

    /**
     * 企业缴纳部分引用的政策明细Id,关联政策表gtofrontdb.CMY_SocialSecurityPolicyItem.CSSPolicyItemId
     */
    @Column(name = "ComCSSPolicyItemId")
    private String comCSSPolicyItemId;

    /**
     * 雇员缴纳部分引用的政策明细Id, gtofrontdb.CMY_SocialSecurityPolicyItem.CSSPolicyItemId
     */
    @Column(name = "EmpCSSPolicyItemId")
    private String empCSSPolicyItemId;

    /**
     * 企业缴纳部分的基数
     */
    @Column(name = "ComBase")
    private BigDecimal comBase;

    /**
     * 雇员缴纳部分的基数
     */
    @Column(name = "EmpBase")
    private BigDecimal empBase;

    /**
     * 企业缴纳部分的比例
     */
    @Column(name = "ComRatio")
    private BigDecimal comRatio;

    /**
     * 雇员缴纳部分的比例
     */
    @Column(name = "EmpRatio")
    private BigDecimal empRatio;

    /**
     * 企业缴纳部分的金额
     */
    @Column(name = "ComAmount")
    private BigDecimal comAmount;

    /**
     * 雇员缴纳部分的金额
     */
    @Column(name = "EmpAmount")
    private BigDecimal empAmount;

    /**
     * 企业缴纳部分的附加金额
     */
    @Column(name = "ComAdditionAmount")
    private BigDecimal comAdditionAmount;

    /**
     * 个人缴纳部分的附加金额
     */
    @Column(name = "EmpAdditionAmount")
    private BigDecimal empAdditionAmount;

    /**
     * 公司和个人的合计金额
     */
    @Column(name = "ComEmpAmount")
    private BigDecimal comEmpAmount;

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
     * @return EmpBaseDetailId - 记录Id
     */
    public Long getEmpBaseDetailId() {
        return empBaseDetailId;
    }

    /**
     * 设置记录Id
     *
     * @param empBaseDetailId 记录Id
     */
    public void setEmpBaseDetailId(Long empBaseDetailId) {
        this.empBaseDetailId = empBaseDetailId;
    }

    /**
     * 获取外键,雇员社保汇缴基数Id
     *
     * @return EmpBasePeriodId - 外键,雇员社保汇缴基数Id
     */
    public Long getEmpBasePeriodId() {
        return empBasePeriodId;
    }

    /**
     * 设置外键,雇员社保汇缴基数Id
     *
     * @param empBasePeriodId 外键,雇员社保汇缴基数Id
     */
    public void setEmpBasePeriodId(Long empBasePeriodId) {
        this.empBasePeriodId = empBasePeriodId;
    }

    /**
     * 获取外键，雇员本地社保档案Id
     *
     * @return EmpArchivedId - 外键，雇员本地社保档案Id
     */
    public String getEmpArchivedId() {
        return empArchivedId;
    }

    /**
     * 设置外键，雇员本地社保档案Id
     *
     * @param empArchivedId 外键，雇员本地社保档案Id
     */
    public void setEmpArchivedId(String empArchivedId) {
        this.empArchivedId = empArchivedId == null ? null : empArchivedId.trim();
    }

    /**
     * 获取社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     *
     * @return SSType - 社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     */
    public Byte getSSType() {
        return SSType;
    }

    /**
     * 设置社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     *
     * @param SSType 社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     */
    public void setSSType(Byte SSType) {
        this.SSType = SSType;
    }

    /**
     * 获取社保险种名称
     *
     * @return SSTypeName - 社保险种名称
     */
    public String getSSTypeName() {
        return SSTypeName;
    }

    /**
     * 设置社保险种名称
     *
     * @param SSTypeName 社保险种名称
     */
    public void setSSTypeName(String SSTypeName) {
        this.SSTypeName = SSTypeName == null ? null : SSTypeName.trim();
    }

    /**
     * 获取企业缴纳部分引用的政策明细Id,关联政策表gtofrontdb.CMY_SocialSecurityPolicyItem.CSSPolicyItemId
     *
     * @return ComCSSPolicyItemId - 企业缴纳部分引用的政策明细Id,关联政策表gtofrontdb.CMY_SocialSecurityPolicyItem.CSSPolicyItemId
     */
    public String getComCSSPolicyItemId() {
        return comCSSPolicyItemId;
    }

    /**
     * 设置企业缴纳部分引用的政策明细Id,关联政策表gtofrontdb.CMY_SocialSecurityPolicyItem.CSSPolicyItemId
     *
     * @param comCSSPolicyItemId 企业缴纳部分引用的政策明细Id,关联政策表gtofrontdb.CMY_SocialSecurityPolicyItem.CSSPolicyItemId
     */
    public void setComCSSPolicyItemId(String comCSSPolicyItemId) {
        this.comCSSPolicyItemId = comCSSPolicyItemId == null ? null : comCSSPolicyItemId.trim();
    }

    /**
     * 获取雇员缴纳部分引用的政策明细Id, gtofrontdb.CMY_SocialSecurityPolicyItem.CSSPolicyItemId
     *
     * @return EmpCSSPolicyItemId - 雇员缴纳部分引用的政策明细Id, gtofrontdb.CMY_SocialSecurityPolicyItem.CSSPolicyItemId
     */
    public String getEmpCSSPolicyItemId() {
        return empCSSPolicyItemId;
    }

    /**
     * 设置雇员缴纳部分引用的政策明细Id, gtofrontdb.CMY_SocialSecurityPolicyItem.CSSPolicyItemId
     *
     * @param empCSSPolicyItemId 雇员缴纳部分引用的政策明细Id, gtofrontdb.CMY_SocialSecurityPolicyItem.CSSPolicyItemId
     */
    public void setEmpCSSPolicyItemId(String empCSSPolicyItemId) {
        this.empCSSPolicyItemId = empCSSPolicyItemId == null ? null : empCSSPolicyItemId.trim();
    }

    /**
     * 获取企业缴纳部分的基数
     *
     * @return ComBase - 企业缴纳部分的基数
     */
    public BigDecimal getComBase() {
        return comBase;
    }

    /**
     * 设置企业缴纳部分的基数
     *
     * @param comBase 企业缴纳部分的基数
     */
    public void setComBase(BigDecimal comBase) {
        this.comBase = comBase;
    }

    /**
     * 获取雇员缴纳部分的基数
     *
     * @return EmpBase - 雇员缴纳部分的基数
     */
    public BigDecimal getEmpBase() {
        return empBase;
    }

    /**
     * 设置雇员缴纳部分的基数
     *
     * @param empBase 雇员缴纳部分的基数
     */
    public void setEmpBase(BigDecimal empBase) {
        this.empBase = empBase;
    }

    /**
     * 获取企业缴纳部分的比例
     *
     * @return ComRatio - 企业缴纳部分的比例
     */
    public BigDecimal getComRatio() {
        return comRatio;
    }

    /**
     * 设置企业缴纳部分的比例
     *
     * @param comRatio 企业缴纳部分的比例
     */
    public void setComRatio(BigDecimal comRatio) {
        this.comRatio = comRatio;
    }

    /**
     * 获取雇员缴纳部分的比例
     *
     * @return EmpRatio - 雇员缴纳部分的比例
     */
    public BigDecimal getEmpRatio() {
        return empRatio;
    }

    /**
     * 设置雇员缴纳部分的比例
     *
     * @param empRatio 雇员缴纳部分的比例
     */
    public void setEmpRatio(BigDecimal empRatio) {
        this.empRatio = empRatio;
    }

    /**
     * 获取企业缴纳部分的金额
     *
     * @return ComAmount - 企业缴纳部分的金额
     */
    public BigDecimal getComAmount() {
        return comAmount;
    }

    /**
     * 设置企业缴纳部分的金额
     *
     * @param comAmount 企业缴纳部分的金额
     */
    public void setComAmount(BigDecimal comAmount) {
        this.comAmount = comAmount;
    }

    /**
     * 获取雇员缴纳部分的金额
     *
     * @return EmpAmount - 雇员缴纳部分的金额
     */
    public BigDecimal getEmpAmount() {
        return empAmount;
    }

    /**
     * 设置雇员缴纳部分的金额
     *
     * @param empAmount 雇员缴纳部分的金额
     */
    public void setEmpAmount(BigDecimal empAmount) {
        this.empAmount = empAmount;
    }

    /**
     * 获取企业缴纳部分的附加金额
     *
     * @return ComAdditionAmount - 企业缴纳部分的附加金额
     */
    public BigDecimal getComAdditionAmount() {
        return comAdditionAmount;
    }

    /**
     * 设置企业缴纳部分的附加金额
     *
     * @param comAdditionAmount 企业缴纳部分的附加金额
     */
    public void setComAdditionAmount(BigDecimal comAdditionAmount) {
        this.comAdditionAmount = comAdditionAmount;
    }

    /**
     * 获取个人缴纳部分的附加金额
     *
     * @return EmpAdditionAmount - 个人缴纳部分的附加金额
     */
    public BigDecimal getEmpAdditionAmount() {
        return empAdditionAmount;
    }

    /**
     * 设置个人缴纳部分的附加金额
     *
     * @param empAdditionAmount 个人缴纳部分的附加金额
     */
    public void setEmpAdditionAmount(BigDecimal empAdditionAmount) {
        this.empAdditionAmount = empAdditionAmount;
    }

    /**
     * 获取公司和个人的合计金额
     *
     * @return ComEmpAmount - 公司和个人的合计金额
     */
    public BigDecimal getComEmpAmount() {
        return comEmpAmount;
    }

    /**
     * 设置公司和个人的合计金额
     *
     * @param comEmpAmount 公司和个人的合计金额
     */
    public void setComEmpAmount(BigDecimal comEmpAmount) {
        this.comEmpAmount = comEmpAmount;
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