package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * 一次性社保、滞纳金等纯金额的产品险种，与五险一金不同
 */
@Table(name = "SS_AdditionalFee")
public class SsAdditionalFee extends BasicModel implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "AdditionalFeeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long additionalFeeId;

    /**
     * 外键，雇员社保档案Id
     */
    @Column(name = "EmpArchiveId")
    private String empArchiveId;

    /**
     * 外键，雇员社保基数记录Id
     */
    @Column(name = "EmpBaseId")
    private Long empBaseId;

    /**
     * 险种类型, 取自全局数据字典表gtobasicdb.DicItem
     */
    @Column(name = "SocialSecurityType")
    private Byte socialSecurityType;

    /**
     * 险种名称,如养老金，医保金等
     */
    @Column(name = "SocialSecurityName")
    private String socialSecurityName;

    /**
     * 企业缴纳部分金额
     */
    @Column(name = "ComAmount")
    private BigDecimal comAmount;

    /**
     * 雇员缴纳部分金额
     */
    @Column(name = "EmpAmount")
    private Long empAmount;

    /**
     * 总金额=企业缴纳部分金额+个人缴纳部分金额
     */
    @Column(name = "TotalAmount")
    private Long totalAmount;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id
     *
     * @return AdditionalFeeId - 记录Id
     */
    public Long getAdditionalFeeId() {
        return additionalFeeId;
    }

    /**
     * 设置记录Id
     *
     * @param additionalFeeId 记录Id
     */
    public void setAdditionalFeeId(Long additionalFeeId) {
        this.additionalFeeId = additionalFeeId;
    }

    /**
     * 获取外键，雇员社保档案Id
     *
     * @return EmpArchiveId - 外键，雇员社保档案Id
     */
    public String getEmpArchiveId() {
        return empArchiveId;
    }

    /**
     * 设置外键，雇员社保档案Id
     *
     * @param empArchiveId 外键，雇员社保档案Id
     */
    public void setEmpArchiveId(String empArchiveId) {
        this.empArchiveId = empArchiveId == null ? null : empArchiveId.trim();
    }

    /**
     * 获取外键，雇员社保基数记录Id
     *
     * @return EmpBaseId - 外键，雇员社保基数记录Id
     */
    public Long getEmpBaseId() {
        return empBaseId;
    }

    /**
     * 设置外键，雇员社保基数记录Id
     *
     * @param empBaseId 外键，雇员社保基数记录Id
     */
    public void setEmpBaseId(Long empBaseId) {
        this.empBaseId = empBaseId;
    }

    /**
     * 获取险种类型, 取自全局数据字典表gtobasicdb.DicItem
     *
     * @return SocialSecurityType - 险种类型, 取自全局数据字典表gtobasicdb.DicItem
     */
    public Byte getSocialSecurityType() {
        return socialSecurityType;
    }

    /**
     * 设置险种类型, 取自全局数据字典表gtobasicdb.DicItem
     *
     * @param socialSecurityType 险种类型, 取自全局数据字典表gtobasicdb.DicItem
     */
    public void setSocialSecurityType(Byte socialSecurityType) {
        this.socialSecurityType = socialSecurityType;
    }

    /**
     * 获取险种名称,如养老金，医保金等
     *
     * @return SocialSecurityName - 险种名称,如养老金，医保金等
     */
    public String getSocialSecurityName() {
        return socialSecurityName;
    }

    /**
     * 设置险种名称,如养老金，医保金等
     *
     * @param socialSecurityName 险种名称,如养老金，医保金等
     */
    public void setSocialSecurityName(String socialSecurityName) {
        this.socialSecurityName = socialSecurityName == null ? null : socialSecurityName.trim();
    }

    /**
     * 获取企业缴纳部分金额
     *
     * @return ComAmount - 企业缴纳部分金额
     */
    public BigDecimal getComAmount() {
        return comAmount;
    }

    /**
     * 设置企业缴纳部分金额
     *
     * @param comAmount 企业缴纳部分金额
     */
    public void setComAmount(BigDecimal comAmount) {
        this.comAmount = comAmount;
    }

    /**
     * 获取雇员缴纳部分金额
     *
     * @return EmpAmount - 雇员缴纳部分金额
     */
    public Long getEmpAmount() {
        return empAmount;
    }

    /**
     * 设置雇员缴纳部分金额
     *
     * @param empAmount 雇员缴纳部分金额
     */
    public void setEmpAmount(Long empAmount) {
        this.empAmount = empAmount;
    }

    /**
     * 获取总金额=企业缴纳部分金额+个人缴纳部分金额
     *
     * @return TotalAmount - 总金额=企业缴纳部分金额+个人缴纳部分金额
     */
    public Long getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置总金额=企业缴纳部分金额+个人缴纳部分金额
     *
     * @param totalAmount 总金额=企业缴纳部分金额+个人缴纳部分金额
     */
    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }
}