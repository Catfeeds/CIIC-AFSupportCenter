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
 * 任务单费用段表,记录任务单历史办理记录,不受将来的调整影响
从雇员社保详细中跳转的任务表单,应从该表获取数据
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-26
 */
@TableName("ss_emp_task_period")
public class SsEmpTaskPeriod implements Serializable {

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
	private String empTaskId;
    /**
     * 基数, 五险合一(基数一致）时有效
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
     * 缴纳方式:   1 - 正常 2 - 补缴 3 - 调整
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


	public Long getEmpTaskPeriodId() {
		return empTaskPeriodId;
	}

	public void setEmpTaskPeriodId(Long empTaskPeriodId) {
		this.empTaskPeriodId = empTaskPeriodId;
	}

	public String getEmpTaskId() {
		return empTaskId;
	}

	public void setEmpTaskId(String empTaskId) {
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

	@Override
	public String toString() {
		return "SsEmpTaskPeriod{" +
			", empTaskPeriodId=" + empTaskPeriodId +
			", empTaskId=" + empTaskId +
			", baseAmount=" + baseAmount +
			", startMonth=" + startMonth +
			", endMonth=" + endMonth +
			", remitWay=" + remitWay +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
