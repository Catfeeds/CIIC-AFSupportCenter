package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员信息变更表
 * </p>
 */
@TableName("am_employe_change")
public class AmEmployeChange implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键,可作为任务单序号
     */
	@TableId(value="emp_change_id", type= IdType.AUTO)
	private Long empChangeId;
    /**
     * 外键:任务单ID
     */
	@TableField("emp_task_id")
	private Long empTaskId;
    /**
     * 合同起始日期
     */
	@TableField("labor_start_date")
	private LocalDate laborStartDate;
    /**
     * 合同结束日期
     */
	@TableField("labor_end_date")
	private LocalDate laborEndDate;
    /**
     * 入职时间
     */
	@TableField("in_date")
	private LocalDate inDate;
    /**
     * 离职原因
     */
	@TableField("out_reason")
	private String outReason;
    /**
     * 离职日期
     */
	@TableField("out_date")
	private LocalDate outDate;


	public Long getEmpChangeId() {
		return empChangeId;
	}

	public void setEmpChangeId(Long empChangeId) {
		this.empChangeId = empChangeId;
	}

	public Long getEmpTaskId() {
		return empTaskId;
	}

	public void setEmpTaskId(Long empTaskId) {
		this.empTaskId = empTaskId;
	}

	public LocalDate getLaborStartDate() {
		return laborStartDate;
	}

	public void setLaborStartDate(LocalDate laborStartDate) {
		this.laborStartDate = laborStartDate;
	}

	public LocalDate getLaborEndDate() {
		return laborEndDate;
	}

	public void setLaborEndDate(LocalDate laborEndDate) {
		this.laborEndDate = laborEndDate;
	}

	public LocalDate getInDate() {
		return inDate;
	}

	public void setInDate(LocalDate inDate) {
		this.inDate = inDate;
	}

	public String getOutReason() {
		return outReason;
	}

	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}

	public LocalDate getOutDate() {
		return outDate;
	}

	public void setOutDate(LocalDate outDate) {
		this.outDate = outDate;
	}

	@Override
	public String toString() {
		return "AmEmployeChange{" +
			", empChangeId=" + empChangeId +
			", empTaskId=" + empTaskId +
			", laborStartDate=" + laborStartDate +
			", laborEndDate=" + laborEndDate +
			", inDate=" + inDate +
			", outReason=" + outReason +
			", outDate=" + outDate +
			"}";
	}
}
