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
 * 对账差异结果表
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-05
 */
@TableName("ss_statement_result")
public class SsStatementResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="statement_detail_id", type= IdType.AUTO)
	private Long statementDetailId;
    /**
     * 外键，对账单Id
     */
	@TableField("statement_id")
	private Long statementId;
    /**
     * 外键:雇员Id
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 项目名称
     */
	@TableField("project_name")
	private String projectName;
    /**
     * 外键：险种
     */
	@TableField("ss_type")
	private Integer ssType;
    /**
     * 从社保局导入金额
     */
	@TableField("imp_amount")
	private BigDecimal impAmount;
    /**
     * 社保系统计算出的金额
     */
	@TableField("ss_amount")
	private BigDecimal ssAmount;
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


	public Long getStatementDetailId() {
		return statementDetailId;
	}

	public void setStatementDetailId(Long statementDetailId) {
		this.statementDetailId = statementDetailId;
	}

	public Long getStatementId() {
		return statementId;
	}

	public void setStatementId(Long statementId) {
		this.statementId = statementId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getSsType() {
		return ssType;
	}

	public void setSsType(Integer ssType) {
		this.ssType = ssType;
	}

	public BigDecimal getImpAmount() {
		return impAmount;
	}

	public void setImpAmount(BigDecimal impAmount) {
		this.impAmount = impAmount;
	}

	public BigDecimal getSsAmount() {
		return ssAmount;
	}

	public void setSsAmount(BigDecimal ssAmount) {
		this.ssAmount = ssAmount;
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
		return "SsStatementResult{" +
			", statementDetailId=" + statementDetailId +
			", statementId=" + statementId +
			", employeeId=" + employeeId +
			", projectName=" + projectName +
			", ssType=" + ssType +
			", impAmount=" + impAmount +
			", ssAmount=" + ssAmount +
			", diffAmount=" + diffAmount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
