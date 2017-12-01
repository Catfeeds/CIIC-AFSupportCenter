package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员社保汇缴基数明细表，
该表细化到每一个社保险种的月度段的基数、比例、公司缴纳金额、个人缴纳金额
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@TableName("ss_emp_base_detail")
public class SsEmpBaseDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="emp_base_adjust_id", type= IdType.AUTO)
	private Long empBaseAdjustId;
    /**
     * 外键,雇员社保汇缴基数Id
     */
	@TableField("emp_base_period_id")
	private Long empBasePeriodId;
    /**
     * 外键，雇员本地社保档案Id
     */
	@TableField("emp_archived_id")
	private String empArchivedId;
    /**
     * 社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     */
	@TableField("ss_type")
	private Integer ssType;
    /**
     * 社保险种名称
     */
	@TableField("ss_type_name")
	private String ssTypeName;
    /**
     * 企业缴纳部分引用的政策明细Id,关联政策表gtofrontdb.CMY_SocialSecurityPolicyItem.CSSPolicyItemId
     */
	@TableField("com_policy_item_id")
	private String comPolicyItemId;
    /**
     * 雇员缴纳部分引用的政策明细Id, gtofrontdb.CMY_SocialSecurityPolicyItem.CSSPolicyItemId
     */
	@TableField("emp_policy_item_id")
	private String empPolicyItemId;
    /**
     * 企业缴纳部分的基数
     */
	@TableField("com_base")
	private BigDecimal comBase;
    /**
     * 雇员缴纳部分的基数
     */
	@TableField("emp_base")
	private BigDecimal empBase;
    /**
     * 企业缴纳部分的比例
     */
	@TableField("com_ratio")
	private BigDecimal comRatio;
    /**
     * 雇员缴纳部分的比例
     */
	@TableField("emp_ratio")
	private BigDecimal empRatio;
    /**
     * 企业缴纳部分的金额
     */
	@TableField("com_amount")
	private BigDecimal comAmount;
    /**
     * 雇员缴纳部分的金额
     */
	@TableField("emp_amount")
	private BigDecimal empAmount;
    /**
     * 企业缴纳部分的附加金额
     */
	@TableField("com_addition_amount")
	private BigDecimal comAdditionAmount;
    /**
     * 个人缴纳部分的附加金额
     */
	@TableField("emp_addition_amount")
	private BigDecimal empAdditionAmount;
    /**
     * 公司和个人的合计金额
     */
	@TableField("comemp_amount")
	private BigDecimal comempAmount;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalTime createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
	private LocalTime modifiedTime;
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


	public Long getEmpBaseAdjustId() {
		return empBaseAdjustId;
	}

	public void setEmpBaseAdjustId(Long empBaseAdjustId) {
		this.empBaseAdjustId = empBaseAdjustId;
	}

	public Long getEmpBasePeriodId() {
		return empBasePeriodId;
	}

	public void setEmpBasePeriodId(Long empBasePeriodId) {
		this.empBasePeriodId = empBasePeriodId;
	}

	public String getEmpArchivedId() {
		return empArchivedId;
	}

	public void setEmpArchivedId(String empArchivedId) {
		this.empArchivedId = empArchivedId;
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

	public String getComPolicyItemId() {
		return comPolicyItemId;
	}

	public void setComPolicyItemId(String comPolicyItemId) {
		this.comPolicyItemId = comPolicyItemId;
	}

	public String getEmpPolicyItemId() {
		return empPolicyItemId;
	}

	public void setEmpPolicyItemId(String empPolicyItemId) {
		this.empPolicyItemId = empPolicyItemId;
	}

	public BigDecimal getComBase() {
		return comBase;
	}

	public void setComBase(BigDecimal comBase) {
		this.comBase = comBase;
	}

	public BigDecimal getEmpBase() {
		return empBase;
	}

	public void setEmpBase(BigDecimal empBase) {
		this.empBase = empBase;
	}

	public BigDecimal getComRatio() {
		return comRatio;
	}

	public void setComRatio(BigDecimal comRatio) {
		this.comRatio = comRatio;
	}

	public BigDecimal getEmpRatio() {
		return empRatio;
	}

	public void setEmpRatio(BigDecimal empRatio) {
		this.empRatio = empRatio;
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

	public BigDecimal getComAdditionAmount() {
		return comAdditionAmount;
	}

	public void setComAdditionAmount(BigDecimal comAdditionAmount) {
		this.comAdditionAmount = comAdditionAmount;
	}

	public BigDecimal getEmpAdditionAmount() {
		return empAdditionAmount;
	}

	public void setEmpAdditionAmount(BigDecimal empAdditionAmount) {
		this.empAdditionAmount = empAdditionAmount;
	}

	public BigDecimal getComempAmount() {
		return comempAmount;
	}

	public void setComempAmount(BigDecimal comempAmount) {
		this.comempAmount = comempAmount;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalTime modifiedTime) {
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
		return "SsEmpBaseDetail{" +
			", empBaseAdjustId=" + empBaseAdjustId +
			", empBasePeriodId=" + empBasePeriodId +
			", empArchivedId=" + empArchivedId +
			", ssType=" + ssType +
			", ssTypeName=" + ssTypeName +
			", comPolicyItemId=" + comPolicyItemId +
			", empPolicyItemId=" + empPolicyItemId +
			", comBase=" + comBase +
			", empBase=" + empBase +
			", comRatio=" + comRatio +
			", empRatio=" + empRatio +
			", comAmount=" + comAmount +
			", empAmount=" + empAmount +
			", comAdditionAmount=" + comAdditionAmount +
			", empAdditionAmount=" + empAdditionAmount +
			", comempAmount=" + comempAmount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
