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
 * 一次性社保、滞纳金等纯金额的产品险种，与五险一金不同
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-26
 */
@TableName("ss_additional_fee")
public class SsAdditionalFee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="additional_fee_id", type= IdType.AUTO)
	private Long additionalFeeId;
    /**
     * 外键，雇员社保档案Id
     */
	@TableField("emp_archive_id")
	private String empArchiveId;
    /**
     * 外键，雇员社保基数记录Id
     */
	@TableField("emp_base_period_id")
	private Long empBasePeriodId;
    /**
     * 险种类型, 取自全局数据字典表gtobasicdb.DicItem
     */
	@TableField("ss_type")
	private Integer ssType;
    /**
     * 险种名称,如养老金，医保金等
     */
	@TableField("ss_name")
	private String ssName;
    /**
     * 企业缴纳部分金额
     */
	@TableField("com_amount")
	private BigDecimal comAmount;
    /**
     * 雇员缴纳部分金额
     */
	@TableField("emp_amount")
	private BigDecimal empAmount;
    /**
     * 总金额=企业缴纳部分金额+个人缴纳部分金额
     */
	@TableField("total_amount")
	private BigDecimal totalAmount;
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


	public Long getAdditionalFeeId() {
		return additionalFeeId;
	}

	public void setAdditionalFeeId(Long additionalFeeId) {
		this.additionalFeeId = additionalFeeId;
	}

	public String getEmpArchiveId() {
		return empArchiveId;
	}

	public void setEmpArchiveId(String empArchiveId) {
		this.empArchiveId = empArchiveId;
	}

	public Long getEmpBasePeriodId() {
		return empBasePeriodId;
	}

	public void setEmpBasePeriodId(Long empBasePeriodId) {
		this.empBasePeriodId = empBasePeriodId;
	}

	public Integer getSsType() {
		return ssType;
	}

	public void setSsType(Integer ssType) {
		this.ssType = ssType;
	}

	public String getSsName() {
		return ssName;
	}

	public void setSsName(String ssName) {
		this.ssName = ssName;
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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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
		return "SsAdditionalFee{" +
			", additionalFeeId=" + additionalFeeId +
			", empArchiveId=" + empArchiveId +
			", empBasePeriodId=" + empBasePeriodId +
			", ssType=" + ssType +
			", ssName=" + ssName +
			", comAmount=" + comAmount +
			", empAmount=" + empAmount +
			", totalAmount=" + totalAmount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
