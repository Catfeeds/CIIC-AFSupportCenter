package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 本地社保应付金额交易记录明细表
 */
@Table(name = "SS_PaymentDetail")
public class SsPaymentDetail implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "PaymentDetailId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentDetailId;

    /**
     * 外键，支付记录Id
     */
    @Column(name = "PaymentId")
    private Long paymentId;

    /**
     * 序号
     */
    @Column(name = "Seq")
    private String seq;

    /**
     * 支付项目名称
     */
    @Column(name = "PaymentItemName")
    private String paymentItemName;

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
     * 金额
     */
    @Column(name = "Amount")
    private BigDecimal amount;

    /**
     * 是否有效,0-无效 1-有效
     */
    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "DataChange_CreateTime")
    private LocalDateTime dataChangeCreateTime;

    @Column(name = "DataChange_LastTime")
    private LocalDateTime dataChangeLastTime;

    @Column(name = "CreateBy")
    private String createBy;

    @Column(name = "ModifiedBy")
    private String modifiedBy;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id
     *
     * @return PaymentDetailId - 记录Id
     */
    public Long getPaymentDetailId() {
        return paymentDetailId;
    }

    /**
     * 设置记录Id
     *
     * @param paymentDetailId 记录Id
     */
    public void setPaymentDetailId(Long paymentDetailId) {
        this.paymentDetailId = paymentDetailId;
    }

    /**
     * 获取外键，支付记录Id
     *
     * @return PaymentId - 外键，支付记录Id
     */
    public Long getPaymentId() {
        return paymentId;
    }

    /**
     * 设置外键，支付记录Id
     *
     * @param paymentId 外键，支付记录Id
     */
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * 获取序号
     *
     * @return Seq - 序号
     */
    public String getSeq() {
        return seq;
    }

    /**
     * 设置序号
     *
     * @param seq 序号
     */
    public void setSeq(String seq) {
        this.seq = seq == null ? null : seq.trim();
    }

    /**
     * 获取支付项目名称
     *
     * @return PaymentItemName - 支付项目名称
     */
    public String getPaymentItemName() {
        return paymentItemName;
    }

    /**
     * 设置支付项目名称
     *
     * @param paymentItemName 支付项目名称
     */
    public void setPaymentItemName(String paymentItemName) {
        this.paymentItemName = paymentItemName == null ? null : paymentItemName.trim();
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
     * 获取金额
     *
     * @return Amount - 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置金额
     *
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取是否有效,0-无效 1-有效
     *
     * @return IsActive - 是否有效,0-无效 1-有效
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * 设置是否有效,0-无效 1-有效
     *
     * @param isActive 是否有效,0-无效 1-有效
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return DataChange_CreateTime
     */
    public LocalDateTime getDataChangeCreateTime() {
        return dataChangeCreateTime;
    }

    /**
     * @param dataChangeCreateTime
     */
    public void setDataChangeCreateTime(LocalDateTime dataChangeCreateTime) {
        this.dataChangeCreateTime = dataChangeCreateTime;
    }

    /**
     * @return DataChange_LastTime
     */
    public LocalDateTime getDataChangeLastTime() {
        return dataChangeLastTime;
    }

    /**
     * @param dataChangeLastTime
     */
    public void setDataChangeLastTime(LocalDateTime dataChangeLastTime) {
        this.dataChangeLastTime = dataChangeLastTime;
    }

    /**
     * @return CreateBy
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * @return ModifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }
}