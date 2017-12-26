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
 * 雇员月度费用明细项目
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-23
 */
@TableName("ss_month_charge_item")
public class SsMonthChargeItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="month_charge_item_id", type= IdType.AUTO)
	private Long monthChargeItemId;
    /**
     * 外键, 关联到雇员月度费用明细表
     */
	@TableField("month_charge_id")
	private Long monthChargeId;
    /**
     * 险种类型, 取自全局数据字典表gtobasicdb.DicItem
     */
	@TableField("ss_type")
	private Integer ssType;
    /**
     * 社保险种名称
     */
	@TableField("ss_type_name")
	private String ssTypeName;
    /**
     * 企业金额
     */
	@TableField("com_amount")
	private BigDecimal comAmount;
    /**
     * 雇员金额
     */
	@TableField("emp_amount")
	private BigDecimal empAmount;
    /**
     * 合计金额
     */
	@TableField("sub_total_amount")
	private BigDecimal subTotalAmount;
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


	public Long getMonthChargeItemId() {
		return monthChargeItemId;
	}

	public void setMonthChargeItemId(Long monthChargeItemId) {
		this.monthChargeItemId = monthChargeItemId;
	}

	public Long getMonthChargeId() {
		return monthChargeId;
	}

	public void setMonthChargeId(Long monthChargeId) {
		this.monthChargeId = monthChargeId;
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

	public BigDecimal getComAmount() {
		return comAmount;
	}

	public void setComAmount(BigDecimal comAmount) {
		this.comAmount = comAmount;
	}

	public BigDecimal getEmpAmount() {
		return empAmount;
	}

	public void setEmpAmount(BigDecimal empAmount) {
		this.empAmount = empAmount;
	}

	public BigDecimal getSubTotalAmount() {
		return subTotalAmount;
	}

	public void setSubTotalAmount(BigDecimal subTotalAmount) {
		this.subTotalAmount = subTotalAmount;
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
		return "SsMonthChargeItem{" +
			", monthChargeItemId=" + monthChargeItemId +
			", monthChargeId=" + monthChargeId +
			", ssType=" + ssType +
			", ssTypeName=" + ssTypeName +
			", comAmount=" + comAmount +
			", empAmount=" + empAmount +
			", subTotalAmount=" + subTotalAmount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
