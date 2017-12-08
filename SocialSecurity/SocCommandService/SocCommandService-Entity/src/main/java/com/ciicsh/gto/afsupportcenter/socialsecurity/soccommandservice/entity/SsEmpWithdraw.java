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
 * 记录本地社保和全国委托社保中，向社保局提取雇员社保金额的业务记录，这是一种特殊业务。
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-08
 */
@TableName("ss_emp_withdraw")
public class SsEmpWithdraw implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="emp_withdraw_id", type= IdType.AUTO)
	private Long empWithdrawId;
    /**
     * 本地社保的雇员任务单Id
     */
	@TableField("emp_task_id")
	private String empTaskId;
    /**
     * 外键，雇员本地社保档案Id
     */
	@TableField("emp_archived_id")
	private String empArchivedId;
    /**
     * 提取金额
     */
	private BigDecimal amount;
    /**
     * 处理方式：1 网上申报 2 柜面办理
     */
	@TableField("process_way")
	private Integer processWay;
    /**
     * 处理时间
     */
	@TableField("process_time")
	private LocalDateTime processTime;
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


	public Long getEmpWithdrawId() {
		return empWithdrawId;
	}

	public void setEmpWithdrawId(Long empWithdrawId) {
		this.empWithdrawId = empWithdrawId;
	}

	public String getEmpTaskId() {
		return empTaskId;
	}

	public void setEmpTaskId(String empTaskId) {
		this.empTaskId = empTaskId;
	}

	public String getEmpArchivedId() {
		return empArchivedId;
	}

	public void setEmpArchivedId(String empArchivedId) {
		this.empArchivedId = empArchivedId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getProcessWay() {
		return processWay;
	}

	public void setProcessWay(Integer processWay) {
		this.processWay = processWay;
	}

	public LocalDateTime getProcessTime() {
		return processTime;
	}

	public void setProcessTime(LocalDateTime processTime) {
		this.processTime = processTime;
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
		return "SsEmpWithdraw{" +
			", empWithdrawId=" + empWithdrawId +
			", empTaskId=" + empTaskId +
			", empArchivedId=" + empArchivedId +
			", amount=" + amount +
			", processWay=" + processWay +
			", processTime=" + processTime +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
