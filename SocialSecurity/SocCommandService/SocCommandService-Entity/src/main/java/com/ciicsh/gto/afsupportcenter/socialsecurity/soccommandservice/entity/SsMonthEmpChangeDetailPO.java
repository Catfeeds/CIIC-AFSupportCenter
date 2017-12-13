package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员月度变更表明细
该表结果有可能需要调整
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
@TableName("ss_month_emp_change_detail")
public class SsMonthEmpChangeDetailPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="month_emp_change_detail_id", type= IdType.AUTO)
	private Long monthEmpChangeDetailId;
    /**
     * 外键,关联到变更主表
     */
	@TableField("month_emp_change_id")
	private Long monthEmpChangeId;
    /**
     * 外键，员工Id
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 变更类型
     */
	@TableField("change_type")
	private String changeType;
    /**
     * 变更类型名称
     */
    @TableField("change_type_name")
    private String changeTypeName;
    /**
     * 基数
     */
	@TableField("base_amount")
	private BigDecimal baseAmount;
    /**
     * 社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     */
	@TableField("ss_type")
	private Integer ssType;
    /**
     * 社保险种名称
     */
	@TableField("ss_name")
	private String ssName;
    /**
     * 企业金额
     */
	@TableField("com_amount")
	private BigDecimal comAmount;
    /**
     * 雇员金额
     */
	@TableField("emp_amount")
	private BigDecimal empAmount;
    /**
     * 客户补缴金额
     */
	@TableField("com_compensated_amount")
	private BigDecimal comCompensatedAmount;
    /**
     * 雇员补缴金额
     */
	@TableField("emp_compensated_amount")
	private BigDecimal empCompensatedAmount;
    /**
     * 一次性支付
     */
	@TableField("one_payment")
	private BigDecimal onePayment;
    /**
     * 是否有效, 0-无效 1-有效
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalDateTime createdTime;
    /**
     * 更新时间
     */
	@TableField("modified_time")
	private LocalDateTime modifiedTime;
    /**
     * 创建者用户Id
     */
	@TableField("created_by")
	private String createdBy;
    /**
     * 修改者用户Id
     */
	@TableField("modified_by")
	private String modifiedBy;


	public Long getMonthEmpChangeDetailId() {
		return monthEmpChangeDetailId;
	}

	public void setMonthEmpChangeDetailId(Long monthEmpChangeDetailId) {
		this.monthEmpChangeDetailId = monthEmpChangeDetailId;
	}

	public Long getMonthEmpChangeId() {
		return monthEmpChangeId;
	}

	public void setMonthEmpChangeId(Long monthEmpChangeId) {
		this.monthEmpChangeId = monthEmpChangeId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}

	public Integer getSsType() {
		return ssType;
	}

	public void setSsType(Integer ssType) {
		this.ssType = ssType;
	}

	public String getSsName() {
		return ssName;
	}

	public void setSsName(String ssName) {
		this.ssName = ssName;
	}

	public BigDecimal getComAmount() {
		return comAmount;
	}

	public void setComAmount(BigDecimal comAmount) {
		this.comAmount = comAmount;
	}

	public BigDecimal getEmpAmount() {
		return empAmount;
	}

	public void setEmpAmount(BigDecimal empAmount) {
		this.empAmount = empAmount;
	}

	public BigDecimal getComCompensatedAmount() {
		return comCompensatedAmount;
	}

	public void setComCompensatedAmount(BigDecimal comCompensatedAmount) {
		this.comCompensatedAmount = comCompensatedAmount;
	}

	public BigDecimal getEmpCompensatedAmount() {
		return empCompensatedAmount;
	}

	public void setEmpCompensatedAmount(BigDecimal empCompensatedAmount) {
		this.empCompensatedAmount = empCompensatedAmount;
	}

	public BigDecimal getOnePayment() {
		return onePayment;
	}

	public void setOnePayment(BigDecimal onePayment) {
		this.onePayment = onePayment;
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
		return "SsMonthEmpChangeDetailPO{" +
			", monthEmpChangeDetailId=" + monthEmpChangeDetailId +
			", monthEmpChangeId=" + monthEmpChangeId +
			", employeeId=" + employeeId +
			", changeType=" + changeType +
			", baseAmount=" + baseAmount +
			", ssType=" + ssType +
			", ssName=" + ssName +
			", comAmount=" + comAmount +
			", empAmount=" + empAmount +
			", comCompensatedAmount=" + comCompensatedAmount +
			", empCompensatedAmount=" + empCompensatedAmount +
			", onePayment=" + onePayment +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}

    public String getChangeTypeName() {
        return changeTypeName;
    }

    public void setChangeTypeName(String changeTypeName) {
        this.changeTypeName = changeTypeName;
    }
}
