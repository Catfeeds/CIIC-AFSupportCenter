package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员月度费用明细
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-16
 */
@TableName("ss_month_com_pay_detail")
public class SsMonthComPayDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="month_com_pay_detail_id", type= IdType.AUTO)
	private Long monthComPayDetailId;
    /**
     * 外键, 关联到月度缴费明细报表
     */
	@TableField("month_com_pay_id")
	private Long monthComPayId;
    /**
     * 外键,雇员社保档案Id
     */
	@TableField("emp_archive_id")
	private String empArchiveId;
    /**
     * 社保基数
     */
	@TableField("base_amount")
	private BigDecimal baseAmount;
    /**
     * 总金额
     */
	@TableField("total_amount")
	private BigDecimal totalAmount;
    /**
     * 社保种类：1标准 2新进 3补缴 4调整 5转出
     */
	@TableField("cost_category")
	private Integer costCategory;
    /**
     * 是否有效, 0-无效 1-有效
     */
	@TableField("is_active")
	private Boolean isActive;
	@TableField("created_time")
	private LocalDateTime createdTime;
	@TableField("modified_time")
	private LocalDateTime modifiedTime;
	@TableField("created_by")
	private String createdBy;
	@TableField("modified_by")
	private String modifiedBy;


	public Long getMonthComPayDetailId() {
		return monthComPayDetailId;
	}

	public void setMonthComPayDetailId(Long monthComPayDetailId) {
		this.monthComPayDetailId = monthComPayDetailId;
	}

	public Long getMonthComPayId() {
		return monthComPayId;
	}

	public void setMonthComPayId(Long monthComPayId) {
		this.monthComPayId = monthComPayId;
	}

	public String getEmpArchiveId() {
		return empArchiveId;
	}

	public void setEmpArchiveId(String empArchiveId) {
		this.empArchiveId = empArchiveId;
	}

	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getCostCategory() {
		return costCategory;
	}

	public void setCostCategory(Integer costCategory) {
		this.costCategory = costCategory;
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
		return "SsMonthComPayDetail{" +
			", monthComPayDetailId=" + monthComPayDetailId +
			", monthComPayId=" + monthComPayId +
			", empArchiveId=" + empArchiveId +
			", baseAmount=" + baseAmount +
			", totalAmount=" + totalAmount +
			", costCategory=" + costCategory +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
