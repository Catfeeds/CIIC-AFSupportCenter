package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 未投保审核
 * </p>
 *
 * @author xiweizhen
 */
public class UninsuredMedicalAuditDTO extends CommonEntity {
    /**
     * 雇员姓名
     */
    private String employeeName;
    /**
     * 证件号
     */
    private String idNum;
    /**
     * 客户ID
     */
    private String companyId;
    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 受理日期
     */
    private List<Date> handlerDateRange;
    /**
     * 审核日期
     */
    private List<Date> auditDateRange;
    /**
     * 管理方编号
     */
    private String managementId;
    /**
     * 管理方名称
     */
    private String managementName;

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
     * 审批状态
     */
    private Boolean status;
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
     * 审核主键
     */
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
    private MultipartFile attachment;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
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

    public List<Date> getHandlerDateRange() {
        return handlerDateRange;
    }

    public void setHandlerDateRange(List<Date> handlerDateRange) {
        this.handlerDateRange = handlerDateRange;
    }

    public String getManagementId() {
        return managementId;
    }

    public void setManagementId(String managementId) {
        this.managementId = managementId;
    }

    public String getManagementName() {
        return managementName;
    }

    public void setManagementName(String managementName) {
        this.managementName = managementName;
    }

    public List<Date> getAuditDateRange() {
        return auditDateRange;
    }

    public void setAuditDateRange(List<Date> auditDateRange) {
        this.auditDateRange = auditDateRange;
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

    public MultipartFile getAttachment() {
        return attachment;
    }

    public void setAttachment(MultipartFile attachment) {
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
        return "UninsuredMedicalAuditDTO{" +
            "employeeName='" + employeeName + '\'' +
            ", idNum='" + idNum + '\'' +
            ", companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", handlerDateRange=" + handlerDateRange +
            ", auditDateRange=" + auditDateRange +
            ", managementId='" + managementId + '\'' +
            ", managementName='" + managementName + '\'' +
            ", umAcceptanceId=" + umAcceptanceId +
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
            ", auditId=" + auditId +
            ", clinicHospital='" + clinicHospital + '\'' +
            ", acceptAmount=" + acceptAmount +
            ", payType=" + payType +
            ", diagnose='" + diagnose + '\'' +
            ", diagnoseDate=" + diagnoseDate +
            ", attachment=" + attachment +
            ", hospitalizationDays=" + hospitalizationDays +
            ", auditAmount=" + auditAmount +
            ", hospitalizationStartDate=" + hospitalizationStartDate +
            ", hospitalizationEndDate=" + hospitalizationEndDate +
            ", remark='" + remark + '\'' +
            ", auditor='" + auditor + '\'' +
            ", auditDate=" + auditDate +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            '}';
    }
}
