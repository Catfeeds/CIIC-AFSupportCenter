package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;

import java.time.LocalDateTime;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员社保退账受理表。
如果为某雇员多缴纳了社保金额，则需向社保局提出退账申请，退账申请受理后落地到此表。
                                                
 * </p>
 *
 * @author xsj
 * @since 2018-01-17
 */
@TableName("ss_emp_refund")
public class SsEmpRefund implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="emp_refund_id", type= IdType.AUTO)
	private Integer empRefundId;
    /**
     * 冗余，雇员任务表
     */
	@TableField("emp_task_id")
	private Long empTaskId;
    /**
     * 外键，关联雇员社保档案Id
     */
	@TableField("emp_archive_id")
	private Long empArchiveId;
    /**
     * 办理月份（抵扣所属月份）
     */
	@TableField("ss_month")
	private String ssMonth;
    /**
     * 退账金额
     */
	private BigDecimal amount;
    /**
     * 处理完成的时间
     */
	@TableField("process_time")
	private LocalTime processTime;
    /**
     * 处理方式 1 -网上申报 2 柜面办理
     */
	@TableField("process_way")
	private Integer processWay;
    /**
     * 退账起始年月
     */
	@TableField("start_month")
	private String startMonth;
    /**
     * 退账截至月份
     */
	@TableField("end_month")
	private String endMonth;
    /**
     * 是否被抵扣支付
     */
	@TableField("if_deductions")
	private Integer ifDeductions;
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


	public Integer getEmpRefundId() {
		return empRefundId;
	}

	public void setEmpRefundId(Integer empRefundId) {
		this.empRefundId = empRefundId;
	}

	public Long getEmpTaskId() {
		return empTaskId;
	}

	public void setEmpTaskId(Long empTaskId) {
		this.empTaskId = empTaskId;
	}

	public Long getEmpArchiveId() {
		return empArchiveId;
	}

	public void setEmpArchiveId(Long empArchiveId) {
		this.empArchiveId = empArchiveId;
	}

	public String getSsMonth() {
		return ssMonth;
	}

	public void setSsMonth(String ssMonth) {
		this.ssMonth = ssMonth;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalTime getProcessTime() {
		return processTime;
	}

	public void setProcessTime(LocalTime processTime) {
		this.processTime = processTime;
	}

	public Integer getProcessWay() {
		return processWay;
	}

	public void setProcessWay(Integer processWay) {
		this.processWay = processWay;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public Integer getIfDeductions() {
		return ifDeductions;
	}

	public void setIfDeductions(Integer ifDeductions) {
		this.ifDeductions = ifDeductions;
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
		return "SsEmpRefund{" +
			"empRefundId=" + empRefundId +
			", empTaskId=" + empTaskId +
			", empArchiveId=" + empArchiveId +
			", ssMonth=" + ssMonth +
			", amount=" + amount +
			", processTime=" + processTime +
			", processWay=" + processWay +
			", startMonth=" + startMonth +
			", endMonth=" + endMonth +
			", ifDeductions=" + ifDeductions +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
