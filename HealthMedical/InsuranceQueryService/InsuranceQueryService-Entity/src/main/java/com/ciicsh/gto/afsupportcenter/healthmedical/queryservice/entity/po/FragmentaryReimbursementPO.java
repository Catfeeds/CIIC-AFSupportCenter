package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 零星报销表
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-29
 */
@TableName("hm_fragmentary_reimbursement")
public class FragmentaryReimbursement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 雇员终身编号
     */
	@TableField("employee_id")
	private String employeeId;
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
     * 医疗备注
     */
	@TableField("medical_remark")
	private String medicalRemark;
    /**
     * 医保结算金额
     */
	@TableField("medical_clearing_money")
	private BigDecimal medicalClearingMoney;
    /**
     * 医保结算反馈
     */
	@TableField("medical_clearing_feed_back")
	private String medicalClearingFeedBack;
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public String getMedicalRemark() {
		return medicalRemark;
	}

	public void setMedicalRemark(String medicalRemark) {
		this.medicalRemark = medicalRemark;
	}

	public BigDecimal getMedicalClearingMoney() {
		return medicalClearingMoney;
	}

	public void setMedicalClearingMoney(BigDecimal medicalClearingMoney) {
		this.medicalClearingMoney = medicalClearingMoney;
	}

	public String getMedicalClearingFeedBack() {
		return medicalClearingFeedBack;
	}

	public void setMedicalClearingFeedBack(String medicalClearingFeedBack) {
		this.medicalClearingFeedBack = medicalClearingFeedBack;
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
		return "FragmentaryReimbursement{" +
			", id=" + id +
			", employeeId=" + employeeId +
			", caseMoney=" + caseMoney +
			", invoiceNumber=" + invoiceNumber +
			", medicalRemark=" + medicalRemark +
			", medicalClearingMoney=" + medicalClearingMoney +
			", medicalClearingFeedBack=" + medicalClearingFeedBack +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
