package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 档案工伤申报
 * </p>
 */
@TableName("am_injury")
public class AmInjury implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="injury_id", type= IdType.AUTO)
	private Long injuryId;
    /**
     * 外健
     */
    @TableId(value="emp_task_id")
    private Long empTaskId;
    /**
     * 工伤认定日期
     */
	@TableField("affirm_date")
	private LocalDate affirmDate;

    @TableField("evaluation_date")
    private LocalDate evaluationDate;

    /**
     * 鉴定结论
     */
	private String evaluation;
    /**
     * 申报单位
     */
	@TableField("declare_unit")
	private String declareUnit;
    /**
     * 是否放弃鉴定
     */
	@TableField("if_giveup_evaluation")
	private Integer ifGiveupEvaluation;
    /**
     * 完成
     */
	@TableField("if_complete")
	private Integer ifComplete;
    /**
     * 操作员
     */
	@TableField("operate_man")
	private String operateMan;
    /**
     * 操作日期
     */
	@TableField("operate_date")
	private LocalDate operateDate;
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

    @TableId(value="remark")
    private String remark;

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public Long getInjuryId() {
		return injuryId;
	}

	public void setInjuryId(Long injuryId) {
		this.injuryId = injuryId;
	}

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
    }

    public LocalDate getAffirmDate() {
		return affirmDate;
	}

	public void setAffirmDate(LocalDate affirmDate) {
		this.affirmDate = affirmDate;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public String getDeclareUnit() {
		return declareUnit;
	}

	public void setDeclareUnit(String declareUnit) {
		this.declareUnit = declareUnit;
	}

	public Integer getIfGiveupEvaluation() {
		return ifGiveupEvaluation;
	}

	public void setIfGiveupEvaluation(Integer ifGiveupEvaluation) {
		this.ifGiveupEvaluation = ifGiveupEvaluation;
	}

	public Integer getIfComplete() {
		return ifComplete;
	}

	public void setIfComplete(Integer ifComplete) {
		this.ifComplete = ifComplete;
	}

	public String getOperateMan() {
		return operateMan;
	}

	public void setOperateMan(String operateMan) {
		this.operateMan = operateMan;
	}

	public LocalDate getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(LocalDate operateDate) {
		this.operateDate = operateDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
	public String toString() {
		return "AmInjury{" +
			", injuryId=" + injuryId +
			", affirmDate=" + affirmDate +
			", evaluation=" + evaluation +
			", declareUnit=" + declareUnit +
			", ifGiveupEvaluation=" + ifGiveupEvaluation +
			", ifComplete=" + ifComplete +
			", operateMan=" + operateMan +
			", operateDate=" + operateDate +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
