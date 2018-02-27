package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员月度汇缴明细库，每个雇员每一月份一条记录
当任务单状态为已办，?该表就应该有对应的明细数据，包含调整数据
 * </p>
 */
@TableName("hf_month_charge")
public class HfMonthCharge implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="month_charge_id", type= IdType.AUTO)
	private Long monthChargeId;
    /**
     * 外键：HF_EmpArchive
     */
	@TableField("emp_archive_id")
	private Long empArchiveId;

    @TableField("emp_task_id")
	private Long empTaskId;
    /**
     * 客户主表ID
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 雇员主表ID
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 公积金类型 1 基本 2 补充
     */
	@TableField("hf_type")
	private Integer hfType;
    /**
     * 企业汇缴月份
     */
	@TableField("hf_month")
	private String hfMonth;
    /**
     * 雇员所属公积金月份
     */
	@TableField("ss_month_belong")
	private String ssMonthBelong;
    /**
     * 缴费类型:标准（正常汇缴）、补缴、调整、封存、启封
     */
	@TableField("payment_type")
	private Integer paymentType;
    /**
     * 补缴原因
            1 漏缴补缴
            2 少缴补缴
            3 欠缴单位补缴
            4 外省市转入补缴
            5 错缴更正补缴
            6 特殊补缴
            7 账外补缴（特殊补缴）
     */
	@TableField("repair_reason")
	private Integer repairReason;
    /**
     * 基数
     */
	private BigDecimal base;
    /**
     * 比例
     */
	private BigDecimal ratio;
    /**
     * 个人比例
     */
	@TableField("ratio_emp")
	private BigDecimal ratioEmp;
    /**
     * 企业比例
     */
	@TableField("ratio_com")
	private BigDecimal ratioCom;
    /**
     * 金额
     */
	private BigDecimal amount;
    /**
     * 个人金额
     */
	@TableField("emp_amount")
	private BigDecimal empAmount;
    /**
     * 企业金额
     */
	@TableField("com_amount")
	private BigDecimal comAmount;
    /**
     * 财务接口要求的雇员支付状态
     */
	@TableField("emp_payment_status")
	private Integer empPaymentStatus;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
	private LocalDateTime modifiedTime;
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


	public Long getMonthChargeId() {
		return monthChargeId;
	}

	public void setMonthChargeId(Long monthChargeId) {
		this.monthChargeId = monthChargeId;
	}

	public Long getEmpArchiveId() {
		return empArchiveId;
	}

	public void setEmpArchiveId(Long empArchiveId) {
		this.empArchiveId = empArchiveId;
	}

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
    }

    public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getHfType() {
		return hfType;
	}

	public void setHfType(Integer hfType) {
		this.hfType = hfType;
	}

	public String getHfMonth() {
		return hfMonth;
	}

	public void setHfMonth(String hfMonth) {
		this.hfMonth = hfMonth;
	}

	public String getSsMonthBelong() {
		return ssMonthBelong;
	}

	public void setSsMonthBelong(String ssMonthBelong) {
		this.ssMonthBelong = ssMonthBelong;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getRepairReason() {
		return repairReason;
	}

	public void setRepairReason(Integer repairReason) {
		this.repairReason = repairReason;
	}

	public BigDecimal getBase() {
		return base;
	}

	public void setBase(BigDecimal base) {
		this.base = base;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	public BigDecimal getRatioEmp() {
		return ratioEmp;
	}

	public void setRatioEmp(BigDecimal ratioEmp) {
		this.ratioEmp = ratioEmp;
	}

	public BigDecimal getRatioCom() {
		return ratioCom;
	}

	public void setRatioCom(BigDecimal ratioCom) {
		this.ratioCom = ratioCom;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getEmpAmount() {
		return empAmount;
	}

	public void setEmpAmount(BigDecimal empAmount) {
		this.empAmount = empAmount;
	}

	public BigDecimal getComAmount() {
		return comAmount;
	}

	public void setComAmount(BigDecimal comAmount) {
		this.comAmount = comAmount;
	}

	public Integer getEmpPaymentStatus() {
		return empPaymentStatus;
	}

	public void setEmpPaymentStatus(Integer empPaymentStatus) {
		this.empPaymentStatus = empPaymentStatus;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
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
		return "HfEmpMonthCharge{" +
			", monthChargeId=" + monthChargeId +
			", empArchiveId=" + empArchiveId +
            ", empTaskId=" + empTaskId +
			", companyId=" + companyId +
			", employeeId=" + employeeId +
			", hfType=" + hfType +
			", hfMonth=" + hfMonth +
			", ssMonthBelong=" + ssMonthBelong +
			", paymentType=" + paymentType +
			", repairReason=" + repairReason +
			", base=" + base +
			", ratio=" + ratio +
			", ratioEmp=" + ratioEmp +
			", ratioCom=" + ratioCom +
			", amount=" + amount +
			", empAmount=" + empAmount +
			", comAmount=" + comAmount +
			", empPaymentStatus=" + empPaymentStatus +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
