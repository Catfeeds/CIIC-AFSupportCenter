package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * 雇员社保基数调整记录明细表，
该表细化到每一个社保险种的月度段的基数、比例、公司金额、个人金额、差额（与Em
 */
@Table(name = "SS_EmpBaseAdjustDetail")
public class SsEmpBaseAdjustDetail extends BasicModel implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "EmpBaseAdjustDetailId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empBaseAdjustDetailId;

    /**
     * 外键,关联至主表SOC_EmpBaseAdjust
     */
    @Column(name = "EmpBaseAdjustId")
    private Long empBaseAdjustId;

    /**
     * 外键，雇员社保档案Id
     */
    @Column(name = "EmpArchivedId")
    private String empArchivedId;

    /**
     * 社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     */
    @Column(name = "SocialSecurityType")
    private Byte socialSecurityType;

    /**
     * 社保险种名称
     */
    @Column(name = "SocialSecurityName")
    private String socialSecurityName;

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
     * 个人缴纳部分的比例
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
     * 合计缴纳金额=企业缴纳部分的金额+雇员缴纳部分的金额
     */
    @Column(name = "ComEmpAmount")
    private BigDecimal comEmpAmount;

    /**
     * 企业附加金额
     */
    @Column(name = "ComAdditionAmount")
    private BigDecimal comAdditionAmount;

    /**
     * 雇员附加金额
     */
    @Column(name = "EmpAdditionAmount")
    private BigDecimal empAdditionAmount;

    /**
     * 企业缴纳部分差额
     */
    @Column(name = "ComDiffAmount")
    private BigDecimal comDiffAmount;

    /**
     * 雇员缴纳部分差额
     */
    @Column(name = "EmpDiffAmount")
    private BigDecimal empDiffAmount;

    /**
     * 差额合计=企业缴纳部分差额+雇员缴纳部分差额
     */
    @Column(name = "ComEmpDiffAmount")
    private BigDecimal comEmpDiffAmount;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id
     *
     * @return EmpBaseAdjustDetailId - 记录Id
     */
    public Long getEmpBaseAdjustDetailId() {
        return empBaseAdjustDetailId;
    }

    /**
     * 设置记录Id
     *
     * @param empBaseAdjustDetailId 记录Id
     */
    public void setEmpBaseAdjustDetailId(Long empBaseAdjustDetailId) {
        this.empBaseAdjustDetailId = empBaseAdjustDetailId;
    }

    /**
     * 获取外键,关联至主表SOC_EmpBaseAdjust
     *
     * @return EmpBaseAdjustId - 外键,关联至主表SOC_EmpBaseAdjust
     */
    public Long getEmpBaseAdjustId() {
        return empBaseAdjustId;
    }

    /**
     * 设置外键,关联至主表SOC_EmpBaseAdjust
     *
     * @param empBaseAdjustId 外键,关联至主表SOC_EmpBaseAdjust
     */
    public void setEmpBaseAdjustId(Long empBaseAdjustId) {
        this.empBaseAdjustId = empBaseAdjustId;
    }

    /**
     * 获取外键，雇员社保档案Id
     *
     * @return EmpArchivedId - 外键，雇员社保档案Id
     */
    public String getEmpArchivedId() {
        return empArchivedId;
    }

    /**
     * 设置外键，雇员社保档案Id
     *
     * @param empArchivedId 外键，雇员社保档案Id
     */
    public void setEmpArchivedId(String empArchivedId) {
        this.empArchivedId = empArchivedId == null ? null : empArchivedId.trim();
    }

    /**
     * 获取社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     *
     * @return SocialSecurityType - 社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     */
    public Byte getSocialSecurityType() {
        return socialSecurityType;
    }

    /**
     * 设置社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     *
     * @param socialSecurityType 社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     */
    public void setSocialSecurityType(Byte socialSecurityType) {
        this.socialSecurityType = socialSecurityType;
    }

    /**
     * 获取社保险种名称
     *
     * @return SocialSecurityName - 社保险种名称
     */
    public String getSocialSecurityName() {
        return socialSecurityName;
    }

    /**
     * 设置社保险种名称
     *
     * @param socialSecurityName 社保险种名称
     */
    public void setSocialSecurityName(String socialSecurityName) {
        this.socialSecurityName = socialSecurityName == null ? null : socialSecurityName.trim();
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
     * 获取个人缴纳部分的比例
     *
     * @return EmpRatio - 个人缴纳部分的比例
     */
    public BigDecimal getEmpRatio() {
        return empRatio;
    }

    /**
     * 设置个人缴纳部分的比例
     *
     * @param empRatio 个人缴纳部分的比例
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
     * 获取合计缴纳金额=企业缴纳部分的金额+雇员缴纳部分的金额
     *
     * @return ComEmpAmount - 合计缴纳金额=企业缴纳部分的金额+雇员缴纳部分的金额
     */
    public BigDecimal getComEmpAmount() {
        return comEmpAmount;
    }

    /**
     * 设置合计缴纳金额=企业缴纳部分的金额+雇员缴纳部分的金额
     *
     * @param comEmpAmount 合计缴纳金额=企业缴纳部分的金额+雇员缴纳部分的金额
     */
    public void setComEmpAmount(BigDecimal comEmpAmount) {
        this.comEmpAmount = comEmpAmount;
    }

    /**
     * 获取企业附加金额
     *
     * @return ComAdditionAmount - 企业附加金额
     */
    public BigDecimal getComAdditionAmount() {
        return comAdditionAmount;
    }

    /**
     * 设置企业附加金额
     *
     * @param comAdditionAmount 企业附加金额
     */
    public void setComAdditionAmount(BigDecimal comAdditionAmount) {
        this.comAdditionAmount = comAdditionAmount;
    }

    /**
     * 获取雇员附加金额
     *
     * @return EmpAdditionAmount - 雇员附加金额
     */
    public BigDecimal getEmpAdditionAmount() {
        return empAdditionAmount;
    }

    /**
     * 设置雇员附加金额
     *
     * @param empAdditionAmount 雇员附加金额
     */
    public void setEmpAdditionAmount(BigDecimal empAdditionAmount) {
        this.empAdditionAmount = empAdditionAmount;
    }

    /**
     * 获取企业缴纳部分差额
     *
     * @return ComDiffAmount - 企业缴纳部分差额
     */
    public BigDecimal getComDiffAmount() {
        return comDiffAmount;
    }

    /**
     * 设置企业缴纳部分差额
     *
     * @param comDiffAmount 企业缴纳部分差额
     */
    public void setComDiffAmount(BigDecimal comDiffAmount) {
        this.comDiffAmount = comDiffAmount;
    }

    /**
     * 获取雇员缴纳部分差额
     *
     * @return EmpDiffAmount - 雇员缴纳部分差额
     */
    public BigDecimal getEmpDiffAmount() {
        return empDiffAmount;
    }

    /**
     * 设置雇员缴纳部分差额
     *
     * @param empDiffAmount 雇员缴纳部分差额
     */
    public void setEmpDiffAmount(BigDecimal empDiffAmount) {
        this.empDiffAmount = empDiffAmount;
    }

    /**
     * 获取差额合计=企业缴纳部分差额+雇员缴纳部分差额
     *
     * @return ComEmpDiffAmount - 差额合计=企业缴纳部分差额+雇员缴纳部分差额
     */
    public BigDecimal getComEmpDiffAmount() {
        return comEmpDiffAmount;
    }

    /**
     * 设置差额合计=企业缴纳部分差额+雇员缴纳部分差额
     *
     * @param comEmpDiffAmount 差额合计=企业缴纳部分差额+雇员缴纳部分差额
     */
    public void setComEmpDiffAmount(BigDecimal comEmpDiffAmount) {
        this.comEmpDiffAmount = comEmpDiffAmount;
    }
}