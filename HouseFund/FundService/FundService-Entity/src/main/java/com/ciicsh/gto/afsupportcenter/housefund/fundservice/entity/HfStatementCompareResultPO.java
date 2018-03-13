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
 * 对账差异结果
 * </p>
 */
@TableName("hf_statement_compare_result")
public class HfStatementCompareResultPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="statement_compare_result_id", type= IdType.AUTO)
	private Long statementCompareResultId;
    /**
     * 外键，对账单Id
     */
	@TableField("statement_compare_id")
	private Long statementCompareId;
    /**
     * 外键:雇员Id
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 导入金额
     */
	@TableField("imp_amount")
	private BigDecimal impAmount;
    /**
     * 系统计算出的金额
     */
	@TableField("sys_amount")
	private BigDecimal sysAmount;
    /**
     * 差值
     */
	@TableField("diff_amount")
	private BigDecimal diffAmount;
    /**
     * 是否有效
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
     * 更新者用户Id
     */
	@TableField("modified_by")
	private String modifiedBy;


	public Long getStatementCompareResultId() {
		return statementCompareResultId;
	}

	public void setStatementCompareResultId(Long statementCompareResultId) {
		this.statementCompareResultId = statementCompareResultId;
	}

	public Long getStatementCompareId() {
		return statementCompareId;
	}

	public void setStatementCompareId(Long statementCompareId) {
		this.statementCompareId = statementCompareId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public BigDecimal getImpAmount() {
		return impAmount;
	}

	public void setImpAmount(BigDecimal impAmount) {
		this.impAmount = impAmount;
	}

	public BigDecimal getSysAmount() {
		return sysAmount;
	}

	public void setSysAmount(BigDecimal sysAmount) {
		this.sysAmount = sysAmount;
	}

	public BigDecimal getDiffAmount() {
		return diffAmount;
	}

	public void setDiffAmount(BigDecimal diffAmount) {
		this.diffAmount = diffAmount;
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
		return "HfStatementCompareResultPO{" +
			", statementCompareResultId=" + statementCompareResultId +
			", statementCompareId=" + statementCompareId +
			", employeeId=" + employeeId +
			", impAmount=" + impAmount +
			", sysAmount=" + sysAmount +
			", diffAmount=" + diffAmount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
