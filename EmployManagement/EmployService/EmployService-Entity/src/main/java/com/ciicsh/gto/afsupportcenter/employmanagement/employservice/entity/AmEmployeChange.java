package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
	private Date laborStartDate;
    /**
     * 合同结束日期
     */
	@TableField("labor_end_date")
	private Date laborEndDate;
    /**
     * 入职时间
     */
	@TableField("in_date")
	private Date inDate;
    /**
     * 离职原因
     */
	@TableField("out_reason")
	private String outReason;
    /**
     * 离职日期
     */
	@TableField("out_date")
	private Date outDate;

	@TableField("type")
    private  String type;

    @TableField("created_time")
    private LocalDateTime createdTime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public Date getLaborStartDate() {
        return laborStartDate;
    }

    public void setLaborStartDate(Date laborStartDate) {
        this.laborStartDate = laborStartDate;
    }

    public Date getLaborEndDate() {
        return laborEndDate;
    }

    public void setLaborEndDate(Date laborEndDate) {
        this.laborEndDate = laborEndDate;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getOutReason() {
		return outReason;
	}

	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
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
