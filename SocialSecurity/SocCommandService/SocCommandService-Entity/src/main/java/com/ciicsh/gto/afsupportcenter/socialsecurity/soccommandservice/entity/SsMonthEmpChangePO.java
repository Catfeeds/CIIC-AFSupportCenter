package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员月度变更主表
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
@TableName("ss_month_emp_change")
public class SsMonthEmpChangePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="month_emp_change_id", type= IdType.AUTO)
	private Long monthEmpChangeId;
    /**
     * 外键：对账单Id
     */
    @TableField("statement_id")
    private String statementId;
    /**
     * 社保缴费月份，格式是yyyyMM
     */
	@TableField("ss_month")
	private String ssMonth;
    /**
     * 上次计算时间
     */
	@TableField("last_compute_time")
	private LocalDateTime lastComputeTime;
    /**
     * 变更汇总表类型:YYS\\GSY
     */
	@TableField("compute_type")
	private String computeType;
    /**
     * 计算用户Id
     */
    @TableField("compute_user_id")
    private String computeUserId;
    /**
     * 客户账号Id
     */
	@TableField("com_account_id")
	private Long comAccountId;
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


	public Long getMonthEmpChangeId() {
		return monthEmpChangeId;
	}

	public void setMonthEmpChangeId(Long monthEmpChangeId) {
		this.monthEmpChangeId = monthEmpChangeId;
	}

	public String getSsMonth() {
		return ssMonth;
	}

	public void setSsMonth(String ssMonth) {
		this.ssMonth = ssMonth;
	}

	public LocalDateTime getLastComputeTime() {
		return lastComputeTime;
	}

	public void setLastComputeTime(LocalDateTime lastComputeTime) {
		this.lastComputeTime = lastComputeTime;
	}

	public String getComputeUserId() {
		return computeUserId;
	}

	public void setComputeUserId(String computeUserId) {
		this.computeUserId = computeUserId;
	}

	public Long getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(Long comAccountId) {
		this.comAccountId = comAccountId;
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
		return "SsMonthEmpChangePO{" +
			", monthEmpChangeId=" + monthEmpChangeId +
			", ssMonth=" + ssMonth +
			", lastComputeTime=" + lastComputeTime +
			", computeUserId=" + computeUserId +
			", comAccountId=" + comAccountId +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public String getComputeType() {
        return computeType;
    }

    public void setComputeType(String computeType) {
        this.computeType = computeType;
    }
}
