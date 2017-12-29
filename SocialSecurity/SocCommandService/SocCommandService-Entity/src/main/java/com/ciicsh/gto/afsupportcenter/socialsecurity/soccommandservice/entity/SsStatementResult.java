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
 * @since 2017-12-26
 */
@TableName("ss_statement_result")
public class SsStatementResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="statement_result_id", type= IdType.AUTO)
	private Long statementResultId;
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
     * 变更类型
     */
	@TableField("change_type")
	private Integer changeType;
    /**
     * 变更类型名称
     */
	@TableField("change_type_name")
	private String changeTypeName;
    /**
     * 项目类型
     */
	@TableField("project_type")
	private Integer projectType;
    /**
     * 项目名称
     */
	@TableField("project_type_name")
	private String projectTypeName;
    /**
     * 外键：险种
     */
	@TableField("ss_type")
	private Integer ssType;
    /**
     * 社保险种名称
     */
	@TableField("ss_type_name")
	private String ssTypeName;
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
     * 0 正常差异 1 系统不存在  2 导入不存在
     */
	@TableField("diff_headcount")
	private Integer diffHeadcount;
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
		return statementResultId;
	}

	public void setStatementDetailId(Long statementDetailId) {
		this.statementResultId = statementDetailId;
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

	public Integer getChangeType() {
		return changeType;
	}

	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}

	public String getChangeTypeName() {
		return changeTypeName;
	}

	public void setChangeTypeName(String changeTypeName) {
		this.changeTypeName = changeTypeName;
	}

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	public String getProjectTypeName() {
		return projectTypeName;
	}

	public void setProjectTypeName(String projectTypeName) {
		this.projectTypeName = projectTypeName;
	}

	public Integer getSsType() {
		return ssType;
	}

	public void setSsType(Integer ssType) {
		this.ssType = ssType;
	}

	public String getSsTypeName() {
		return ssTypeName;
	}

	public void setSsTypeName(String ssTypeName) {
		this.ssTypeName = ssTypeName;
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

	public Integer getDiffHeadcount() {
		return diffHeadcount;
	}

	public void setDiffHeadcount(Integer diffHeadcount) {
		this.diffHeadcount = diffHeadcount;
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
			", statementDetailId=" + statementResultId +
			", statementId=" + statementId +
			", employeeId=" + employeeId +
			", changeType=" + changeType +
			", changeTypeName=" + changeTypeName +
			", projectType=" + projectType +
			", projectTypeName=" + projectTypeName +
			", ssType=" + ssType +
			", ssTypeName=" + ssTypeName +
			", impAmount=" + impAmount +
			", ssAmount=" + ssAmount +
			", diffAmount=" + diffAmount +
			", diffHeadcount=" + diffHeadcount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
