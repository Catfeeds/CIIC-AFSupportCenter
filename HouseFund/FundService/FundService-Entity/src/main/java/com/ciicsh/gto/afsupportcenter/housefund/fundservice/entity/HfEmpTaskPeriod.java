package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 任务单费用段表,记录任务单历史办理记录,不受将来的调整影响
从雇员公积金详细中跳转的任务表单,应从该表获取数
 * </p>
 */
@TableName("hf_emp_task_period")
public class HfEmpTaskPeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="emp_task_period_id", type= IdType.AUTO)
	private Long empTaskPeriodId;
    /**
     * 本地社保的雇员任务单Id
     */
	@TableField("emp_task_id")
	private Long empTaskId;
    /**
     * 本地社保的雇员任务单费用段Id
     */
    @TableField("archive_base_period_id")
    private Long archiveBasePeriodId;
    /**
     * 公积金汇缴基数
     */
	@TableField("base_amount")
	private BigDecimal baseAmount;
    /**
     * 缴费段开始月份
     */
	@TableField("start_month")
	private String startMonth;
    /**
     * 缴费段结束月份
     */
	@TableField("end_month")
	private String endMonth;
    /**
     * 公积金汇缴月份
     */
	@TableField("hf_month")
	private String hfMonth;
    /**
     * 任务单场景：新增、补缴、调整
     */
	private BigDecimal amount;
    /**
     * 任务单场景：新增、补缴、调整
     */
	private BigDecimal ratio;
    /**
     * 个人比例
     */
    @TableField("ratio_emp")
    private BigDecimal ratioEmp;
    /**
     * 企业比例
     */
    @TableField("ratio_com")
    private BigDecimal ratioCom;
    /**
     * 补缴原因
            1 漏缴补缴
            2 少缴补缴
            3 欠缴单位补缴
            4 外省市转入补缴
            5 错缴更正补缴
            6 特殊补缴
            7 账外补缴（特殊补缴）
     */
	@TableField("repair_reason")
	private Integer repairReason;
    /**
     * 汇缴方式:   1 - 正常 2 - 补缴 3 - 调整
     */
	@TableField("remit_way")
	private Integer remitWay;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
    @JSONField(serialize=false)
    @TableField("created_time")
	private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
    @JSONField(serialize=false)
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


	public Long getEmpTaskPeriodId() {
		return empTaskPeriodId;
	}

	public void setEmpTaskPeriodId(Long empTaskPeriodId) {
		this.empTaskPeriodId = empTaskPeriodId;
	}

	public Long getEmpTaskId() {
		return empTaskId;
	}

	public void setEmpTaskId(Long empTaskId) {
		this.empTaskId = empTaskId;
	}

	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
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

	public String getHfMonth() {
		return hfMonth;
	}

	public void setHfMonth(String hfMonth) {
		this.hfMonth = hfMonth;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	public Integer getRepairReason() {
		return repairReason;
	}

	public void setRepairReason(Integer repairReason) {
		this.repairReason = repairReason;
	}

	public Integer getRemitWay() {
		return remitWay;
	}

	public void setRemitWay(Integer remitWay) {
		this.remitWay = remitWay;
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

    public BigDecimal getRatioEmp() {
        return ratioEmp;
    }

    public void setRatioEmp(BigDecimal ratioEmp) {
        this.ratioEmp = ratioEmp;
    }

    public BigDecimal getRatioCom() {
        return ratioCom;
    }

    public void setRatioCom(BigDecimal ratioCom) {
        this.ratioCom = ratioCom;
    }

    public Long getArchiveBasePeriodId() {
        return archiveBasePeriodId;
    }

    public void setArchiveBasePeriodId(Long archiveBasePeriodId) {
        this.archiveBasePeriodId = archiveBasePeriodId;
    }

    @Override
	public String toString() {
		return "HfEmpTaskPeriod{" +
			", empTaskPeriodId=" + empTaskPeriodId +
			", empTaskId=" + empTaskId +
            ", archiveBasePeriodId=" + archiveBasePeriodId +
			", baseAmount=" + baseAmount +
			", startMonth=" + startMonth +
			", endMonth=" + endMonth +
			", hfMonth=" + hfMonth +
			", amount=" + amount +
			", ratio=" + ratio +
            ", ratioEmp=" + ratioEmp +
            ", ratioCom=" + ratioCom +
			", repairReason=" + repairReason +
			", remitWay=" + remitWay +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
