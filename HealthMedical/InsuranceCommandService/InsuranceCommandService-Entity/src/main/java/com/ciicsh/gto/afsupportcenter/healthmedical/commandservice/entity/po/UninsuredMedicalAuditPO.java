package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po;

import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-29
 */
@TableName("hm_uninsured_medical_audit")
public class UninsuredMedicalAuditPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("audit_id")
	private String auditId;
    /**
     * 序号
     */
	@TableField("um_id")
	private Integer umId;
    /**
     * 就诊医院
     */
	@TableField("clinic_hospital")
	private String clinicHospital;
    /**
     * 受理金额
     */
	@TableField("accept_amount")
	private String acceptAmount;
    /**
     * 付款方式
     */
	@TableField("pay_type")
	private String payType;
    /**
     * 诊断
     */
	private String diagnose;
    /**
     * 就诊日期
     */
	@TableField("diagnose_date")
	private String diagnoseDate;
    /**
     * 附件
     */
	private String attachment;
    /**
     * 住院天数
     */
	@TableField("hospitalization_days")
	private String hospitalizationDays;
    /**
     * 审核金额
     */
	@TableField("audit_amount")
	private String auditAmount;
    /**
     * 住院开始日期
     */
	@TableField("hospitalization_start_date")
	private String hospitalizationStartDate;
    /**
     * 住院结束日期
     */
	@TableField("hospitalization_end_date")
	private String hospitalizationEndDate;
    /**
     * 备注
     */
	private String remark;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalTime createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
	private LocalTime modifiedTime;
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


	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public Integer getUmId() {
		return umId;
	}

	public void setUmId(Integer umId) {
		this.umId = umId;
	}

	public String getClinicHospital() {
		return clinicHospital;
	}

	public void setClinicHospital(String clinicHospital) {
		this.clinicHospital = clinicHospital;
	}

	public String getAcceptAmount() {
		return acceptAmount;
	}

	public void setAcceptAmount(String acceptAmount) {
		this.acceptAmount = acceptAmount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getDiagnoseDate() {
		return diagnoseDate;
	}

	public void setDiagnoseDate(String diagnoseDate) {
		this.diagnoseDate = diagnoseDate;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getHospitalizationDays() {
		return hospitalizationDays;
	}

	public void setHospitalizationDays(String hospitalizationDays) {
		this.hospitalizationDays = hospitalizationDays;
	}

	public String getAuditAmount() {
		return auditAmount;
	}

	public void setAuditAmount(String auditAmount) {
		this.auditAmount = auditAmount;
	}

	public String getHospitalizationStartDate() {
		return hospitalizationStartDate;
	}

	public void setHospitalizationStartDate(String hospitalizationStartDate) {
		this.hospitalizationStartDate = hospitalizationStartDate;
	}

	public String getHospitalizationEndDate() {
		return hospitalizationEndDate;
	}

	public void setHospitalizationEndDate(String hospitalizationEndDate) {
		this.hospitalizationEndDate = hospitalizationEndDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalTime modifiedTime) {
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
	public String toString() {
		return "UninsuredMedicalAudit{" +
			", auditId=" + auditId +
			", umId=" + umId +
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
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
