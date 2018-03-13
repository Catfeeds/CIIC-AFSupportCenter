package com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 未投保审核--受理单关联表
 * </p>
 *
 * @author xiweizhen
 */
public class UninsuredMedicalAuditBO {
    /**
     * 未投保医疗受理编号
     */
    @TableId(value = "um_acceptance_id", type = IdType.AUTO)
    private Integer umAcceptanceId;
    /**
     * 雇员终身编号
     */
    @TableField("employee_id")
    private String employeeId;
    /**
     * 雇员姓名
     */
    private String employeeName;
    /**
     * 公司编号
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 公司名称
     */
    @TableField("company_name")
    private String companyName;
    /**
     * 款项类型:1-医疗费,2-体检费用,3-住院补贴,4-大额理赔款,5-其他
     */
    @TableField("money_type")
    private Integer moneyType;
    /**
     * 受理类型:1-雇员,2-子女,3-配偶
     */
    @TableField("case_type")
    private Integer caseType;
    /**
     * 中止日期
     */
    @TableField("dimission_date")
    private Date dimissionDate;
    /**
     * 退保日期
     */
    @TableField("surrender_date")
    private Date surrenderDate;
    /**
     * 连带人名字
     */
    @TableField("joint_person_name")
    private String jointPersonName;
    /**
     * 连带人出生日期
     */
    @TableField("joint_person_birth_date")
    private Date jointPersonBirthDate;
    /**
     * 医疗备注
     */
    @TableField("medical_remark")
    private String medicalRemark;
    /**
     * 受理金额
     */
    @TableField("case_money")
    private BigDecimal caseMoney;
    /**
     * 发票张数
     */
    @TableField("invoice_number")
    private Integer invoiceNumber;
    /**
     * 受理单状态（0-未受理，1-已受理，2-拒赔，3-已审核未同步，4-已同步未支付，5-财务退回，6-已同步已支付，7-已退票，8-已完成）
     */
    private Integer status;
    /**
     * 受理人
     */
    private String handler;
    /**
     * 受理日期
     */
    @TableField("handler_date")
    private Date handlerDate;
    /**
     * 拒赔类型:1-退员工,2-退客户,3-作废,4-其他
     */
    @TableField("reject_type")
    private Integer rejectType;
    /**
     * 处理备注
     */
    private String remark;

    @TableId(value = "audit_id", type = IdType.AUTO)
    private Integer auditId;

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
     * 审核人
     */
    private String auditor;
    /**
     * 审核日期
     */
    @TableField("audit_date")
    private Date auditDate;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getUmAcceptanceId() {
        return umAcceptanceId;
    }

    public void setUmAcceptanceId(Integer umAcceptanceId) {
        this.umAcceptanceId = umAcceptanceId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(Integer moneyType) {
        this.moneyType = moneyType;
    }

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public Date getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(Date dimissionDate) {
        this.dimissionDate = dimissionDate;
    }

    public Date getSurrenderDate() {
        return surrenderDate;
    }

    public void setSurrenderDate(Date surrenderDate) {
        this.surrenderDate = surrenderDate;
    }

    public String getJointPersonName() {
        return jointPersonName;
    }

    public void setJointPersonName(String jointPersonName) {
        this.jointPersonName = jointPersonName;
    }

    public Date getJointPersonBirthDate() {
        return jointPersonBirthDate;
    }

    public void setJointPersonBirthDate(Date jointPersonBirthDate) {
        this.jointPersonBirthDate = jointPersonBirthDate;
    }

    public String getMedicalRemark() {
        return medicalRemark;
    }

    public void setMedicalRemark(String medicalRemark) {
        this.medicalRemark = medicalRemark;
    }

    public BigDecimal getCaseMoney() {
        return caseMoney;
    }

    public void setCaseMoney(BigDecimal caseMoney) {
        this.caseMoney = caseMoney;
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Date getHandlerDate() {
        return handlerDate;
    }

    public void setHandlerDate(Date handlerDate) {
        this.handlerDate = handlerDate;
    }

    public Integer getRejectType() {
        return rejectType;
    }

    public void setRejectType(Integer rejectType) {
        this.rejectType = rejectType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
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

    @Override
    public String toString() {
        return "UninsuredMedicalAuditBO{" +
            "umAcceptanceId=" + umAcceptanceId +
            ", employeeId='" + employeeId + '\'' +
            ", moneyType=" + moneyType +
            ", caseType=" + caseType +
            ", dimissionDate=" + dimissionDate +
            ", surrenderDate=" + surrenderDate +
            ", jointPersonName='" + jointPersonName + '\'' +
            ", jointPersonBirthDate=" + jointPersonBirthDate +
            ", medicalRemark='" + medicalRemark + '\'' +
            ", caseMoney=" + caseMoney +
            ", invoiceNumber=" + invoiceNumber +
            ", status=" + status +
            ", handler='" + handler + '\'' +
            ", handlerDate=" + handlerDate +
            ", rejectType=" + rejectType +
            ", remark='" + remark + '\'' +
            ", auditId=" + auditId +
            ", clinicHospital='" + clinicHospital + '\'' +
            ", acceptAmount=" + acceptAmount +
            ", payType=" + payType +
            ", diagnose='" + diagnose + '\'' +
            ", diagnoseDate=" + diagnoseDate +
            ", attachment='" + attachment + '\'' +
            ", hospitalizationDays=" + hospitalizationDays +
            ", auditAmount=" + auditAmount +
            ", hospitalizationStartDate=" + hospitalizationStartDate +
            ", hospitalizationEndDate=" + hospitalizationEndDate +
            ", auditor='" + auditor + '\'' +
            ", auditDate=" + auditDate +
            '}';
    }
}
