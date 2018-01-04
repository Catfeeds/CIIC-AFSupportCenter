package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 未投保医疗
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-29
 */
@TableName("hm_uninsured_medical")
public class UninsuredMedicalPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
	@TableId(value="um_id", type= IdType.AUTO)
	private Integer umId;
    /**
     * 雇员终身编号
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 款项类型:
1-医疗费
2-体检费用
3-住院补贴
4-托费
5-大额理赔款
6-其他

     */
	@TableField("money_type")
	private Integer moneyType;
    /**
     * 受理类型:
1-雇员
2-子女
3-配偶
     */
	@TableField("case_type")
	private Integer caseType;
    /**
     * 中止日期
     */
	@TableField("dimission_date")
	private LocalDate dimissionDate;
    /**
     * 退保日期
     */
	@TableField("surrender_date")
	private LocalDate surrenderDate;
    /**
     * 连带人名字
     */
	@TableField("joint_person_name")
	private String jointPersonName;
    /**
     * 连带人出生日期
     */
	@TableField("joint_person_birth_date")
	private LocalDate jointPersonBirthDate;
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
     * 状态：
1-未处理
2-已受理
3-拒赔
4-已审核
     */
	private Integer status;
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


	public Integer getUmId() {
		return umId;
	}

	public void setUmId(Integer umId) {
		this.umId = umId;
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

	public LocalDate getDimissionDate() {
		return dimissionDate;
	}

	public void setDimissionDate(LocalDate dimissionDate) {
		this.dimissionDate = dimissionDate;
	}

	public LocalDate getSurrenderDate() {
		return surrenderDate;
	}

	public void setSurrenderDate(LocalDate surrenderDate) {
		this.surrenderDate = surrenderDate;
	}

	public String getJointPersonName() {
		return jointPersonName;
	}

	public void setJointPersonName(String jointPersonName) {
		this.jointPersonName = jointPersonName;
	}

	public LocalDate getJointPersonBirthDate() {
		return jointPersonBirthDate;
	}

	public void setJointPersonBirthDate(LocalDate jointPersonBirthDate) {
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
		return "UninsuredMedical{" +
			", umId=" + umId +
			", employeeId=" + employeeId +
			", moneyType=" + moneyType +
			", caseType=" + caseType +
			", dimissionDate=" + dimissionDate +
			", surrenderDate=" + surrenderDate +
			", jointPersonName=" + jointPersonName +
			", jointPersonBirthDate=" + jointPersonBirthDate +
			", medicalRemark=" + medicalRemark +
			", caseMoney=" + caseMoney +
			", invoiceNumber=" + invoiceNumber +
			", status=" + status +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
