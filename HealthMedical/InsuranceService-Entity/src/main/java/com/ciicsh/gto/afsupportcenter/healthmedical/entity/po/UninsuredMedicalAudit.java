package com.ciicsh.gto.afsupportcenter.healthmedical.entity.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 未投保审核
 * </p>
 *
 * @author xiweizhen
 */
@TableName("hm_uninsured_medical_audit")
public class UninsuredMedicalAudit extends Model<UninsuredMedicalAudit> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "audit_id", type = IdType.AUTO)
    private Integer auditId;
    /**
     * 序号
     */
    @TableField("um_acceptance_id")
    private Integer umAcceptanceId;
    /**
     * 就诊医院
     */
    @TableField("clinic_hospital")
    private String clinicHospital;
    /**
     * 受理金额
     */
    @TableField("accept_amount")
    private BigDecimal acceptAmount;
    /**
     * 付款方式:1-打卡,2-现金
     */
    @TableField("pay_type")
    private Integer payType;
    /**
     * 诊断
     */
    private String diagnose;
    /**
     * 就诊日期
     */
    @TableField("diagnose_date")
    private Date diagnoseDate;
    /**
     * 附件
     */
    private String attachment;
    /**
     * 住院天数
     */
    @TableField("hospitalization_days")
    private Integer hospitalizationDays;
    /**
     * 审核金额
     */
    @TableField("audit_amount")
    private BigDecimal auditAmount;
    /**
     * 住院开始日期
     */
    @TableField("hospitalization_start_date")
    private Date hospitalizationStartDate;
    /**
     * 住院结束日期
     */
    @TableField("hospitalization_end_date")
    private Date hospitalizationEndDate;
    /**
     * 备注
     */
    private String remark;
    /**
     * 审核人
     */
    private String auditor;
    /**
     * 审核日期
     */
    @TableField("audit_date")
    private Date auditDate;
    /**
     * 是否可用
     */
    @TableField("is_active")
    private Boolean isActive;
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


    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    public Integer getUmAcceptanceId() {
        return umAcceptanceId;
    }

    public void setUmAcceptanceId(Integer umAcceptanceId) {
        this.umAcceptanceId = umAcceptanceId;
    }

    public String getClinicHospital() {
        return clinicHospital;
    }

    public void setClinicHospital(String clinicHospital) {
        this.clinicHospital = clinicHospital;
    }

    public BigDecimal getAcceptAmount() {
        return acceptAmount;
    }

    public void setAcceptAmount(BigDecimal acceptAmount) {
        this.acceptAmount = acceptAmount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public Date getDiagnoseDate() {
        return diagnoseDate;
    }

    public void setDiagnoseDate(Date diagnoseDate) {
        this.diagnoseDate = diagnoseDate;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Integer getHospitalizationDays() {
        return hospitalizationDays;
    }

    public void setHospitalizationDays(Integer hospitalizationDays) {
        this.hospitalizationDays = hospitalizationDays;
    }

    public BigDecimal getAuditAmount() {
        return auditAmount;
    }

    public void setAuditAmount(BigDecimal auditAmount) {
        this.auditAmount = auditAmount;
    }

    public Date getHospitalizationStartDate() {
        return hospitalizationStartDate;
    }

    public void setHospitalizationStartDate(Date hospitalizationStartDate) {
        this.hospitalizationStartDate = hospitalizationStartDate;
    }

    public Date getHospitalizationEndDate() {
        return hospitalizationEndDate;
    }

    public void setHospitalizationEndDate(Date hospitalizationEndDate) {
        this.hospitalizationEndDate = hospitalizationEndDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
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

    @Override
    protected Serializable pkVal() {
        return this.auditId;
    }

    @Override
    public String toString() {
        return "UninsuredMedicalAudit{" +
            ", auditId=" + auditId +
            ", umAcceptanceId=" + umAcceptanceId +
            ", clinicHospital=" + clinicHospital +
            ", acceptAmount=" + acceptAmount +
            ", payType=" + payType +
            ", diagnose=" + diagnose +
            ", diagnoseDate=" + diagnoseDate +
            ", attachment=" + attachment +
            ", hospitalizationDays=" + hospitalizationDays +
            ", auditAmount=" + auditAmount +
            ", hospitalizationStartDate=" + hospitalizationStartDate +
            ", hospitalizationEndDate=" + hospitalizationEndDate +
            ", remark=" + remark +
            ", auditor=" + auditor +
            ", auditDate=" + auditDate +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
