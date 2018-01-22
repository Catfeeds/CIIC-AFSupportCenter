package com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.CommonEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 未投保医疗
 * </p>
 *
 * @author xiweizhen
 */
public class UninsuredMedicalBO extends CommonEntity {
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
     * 未投保医疗受理编号
     */
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
    public String toString() {
        return super.toString() + "UninsuredMedicalBO{" +
            "employeeName='" + employeeName + '\'' +
            ", idNum='" + idNum + '\'' +
            ", companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
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
            ", remark='" + remark + '\'' +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            '}';
    }
}
