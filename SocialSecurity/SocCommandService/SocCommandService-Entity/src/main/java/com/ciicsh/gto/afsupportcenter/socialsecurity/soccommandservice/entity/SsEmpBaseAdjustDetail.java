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
 * 雇员社保基数调整记录明细表，
该表细化到每一个社保险种的月度段的基数、比例、公司金额、个人金额、差额（与Em
 * </p>
 */
@TableName("ss_emp_base_adjust_detail")
public class SsEmpBaseAdjustDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="emp_base_adjust_detailid", type= IdType.AUTO)
	private Long empBaseAdjustDetailid;
    /**
     * 外键,关联至主表SOC_EmpBaseAdjust
     */
	@TableField("emp_base_adjust_id")
	private Long empBaseAdjustId;
    /**
     * 外键，雇员社保档案Id
     */
	@TableField("emp_archive_id")
	private Long empArchiveId;
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
	@TableField("emp_css_policy_item_id")
	private String empCssPolicyItemId;
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
     * 个人缴纳部分的比例
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
     * 企业附加金额
     */
	@TableField("com_addition_amount")
	private BigDecimal comAdditionAmount;
    /**
     * 雇员附加金额
     */
	@TableField("emp_addition_amount")
	private BigDecimal empAdditionAmount;
    /**
     * 合计缴纳金额=企业缴纳部分的金额+雇员缴纳部分的金额
     */
	@TableField("comemp_amount")
	private BigDecimal comempAmount;
    /**
     * 企业缴纳部分差额
     */
	@TableField("com_diff_amount")
	private BigDecimal comDiffAmount;
    /**
     * 雇员缴纳部分差额
     */
	@TableField("emp_diff_amount")
	private BigDecimal empDiffAmount;
    /**
     * 差额合计=企业缴纳部分差额+雇员缴纳部分差额
     */
	@TableField("comemp_diff_amount")
	private BigDecimal comempDiffAmount;
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


	public Long getEmpBaseAdjustDetailid() {
		return empBaseAdjustDetailid;
	}

	public void setEmpBaseAdjustDetailid(Long empBaseAdjustDetailid) {
		this.empBaseAdjustDetailid = empBaseAdjustDetailid;
	}

	public Long getEmpBaseAdjustId() {
		return empBaseAdjustId;
	}

	public void setEmpBaseAdjustId(Long empBaseAdjustId) {
		this.empBaseAdjustId = empBaseAdjustId;
	}

	public Long getEmpArchiveId() {
		return empArchiveId;
	}

	public void setEmpArchiveId(Long empArchiveId) {
		this.empArchiveId = empArchiveId;
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

	public String getEmpCssPolicyItemId() {
		return empCssPolicyItemId;
	}

	public void setEmpCssPolicyItemId(String empCssPolicyItemId) {
		this.empCssPolicyItemId = empCssPolicyItemId;
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

	public BigDecimal getComDiffAmount() {
		return comDiffAmount;
	}

	public void setComDiffAmount(BigDecimal comDiffAmount) {
		this.comDiffAmount = comDiffAmount;
	}

	public BigDecimal getEmpDiffAmount() {
		return empDiffAmount;
	}

	public void setEmpDiffAmount(BigDecimal empDiffAmount) {
		this.empDiffAmount = empDiffAmount;
	}

	public BigDecimal getComempDiffAmount() {
		return comempDiffAmount;
	}

	public void setComempDiffAmount(BigDecimal comempDiffAmount) {
		this.comempDiffAmount = comempDiffAmount;
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
		return "SsEmpBaseAdjustDetail{" +
			", empBaseAdjustDetailid=" + empBaseAdjustDetailid +
			", empBaseAdjustId=" + empBaseAdjustId +
			", empArchiveId=" + empArchiveId +
			", ssType=" + ssType +
			", ssTypeName=" + ssTypeName +
			", comPolicyItemId=" + comPolicyItemId +
			", empCssPolicyItemId=" + empCssPolicyItemId +
			", comBase=" + comBase +
			", empBase=" + empBase +
			", comRatio=" + comRatio +
			", empRatio=" + empRatio +
			", comAmount=" + comAmount +
			", empAmount=" + empAmount +
			", comAdditionAmount=" + comAdditionAmount +
			", empAdditionAmount=" + empAdditionAmount +
			", comempAmount=" + comempAmount +
			", comDiffAmount=" + comDiffAmount +
			", empDiffAmount=" + empDiffAmount +
			", comempDiffAmount=" + comempDiffAmount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
