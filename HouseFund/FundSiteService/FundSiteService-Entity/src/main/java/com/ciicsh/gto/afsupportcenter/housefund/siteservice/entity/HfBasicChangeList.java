package com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 基本公积金汇缴变更清册
 * </p>
 */
@TableName("hf_basic_change_list")
public class HfBasicChangeList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="basic_change_list_id", type= IdType.AUTO)
	private Long basicChangeListId;
    /**
     * 序号
     */
	@TableField("serial_num")
	private String serialNum;
    /**
     * 变更类型
     */
	@TableField("change_type")
	private Integer changeType;
    /**
     * 姓名
     */
	@TableField("emp_name")
	private String empName;
    /**
     * 身份证号
     */
	@TableField("emp_card_num")
	private String empCardNum;
    /**
     * 公积金账号
     */
	@TableField("hf_account")
	private String hfAccount;
    /**
     * 每月应缴额
     */
	@TableField("monthly_pay_amount")
	private BigDecimal monthlyPayAmount;
    /**
     * 工资收入
     */
	@TableField("base_amount")
	private BigDecimal baseAmount;
    /**
     * 缴存比例
     */
	private BigDecimal ratio;
    /**
     * 减少公积金账号
     */
	@TableField("reduce_hf_account")
	private String reduceHfAccount;
    /**
     * 减少姓名
     */
	@TableField("reduce_emp_name")
	private String reduceEmpName;
    /**
     * 摘要
     */
	@TableField("reduce_summary")
	private String reduceSummary;
    /**
     * 减少每月应缴额
     */
	@TableField("reduce_month_pay_amount")
	private BigDecimal reduceMonthPayAmount;
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


	public Long getBasicChangeListId() {
		return basicChangeListId;
	}

	public void setBasicChangeListId(Long basicChangeListId) {
		this.basicChangeListId = basicChangeListId;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public Integer getChangeType() {
		return changeType;
	}

	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpCardNum() {
		return empCardNum;
	}

	public void setEmpCardNum(String empCardNum) {
		this.empCardNum = empCardNum;
	}

	public String getHfAccount() {
		return hfAccount;
	}

	public void setHfAccount(String hfAccount) {
		this.hfAccount = hfAccount;
	}

	public BigDecimal getMonthlyPayAmount() {
		return monthlyPayAmount;
	}

	public void setMonthlyPayAmount(BigDecimal monthlyPayAmount) {
		this.monthlyPayAmount = monthlyPayAmount;
	}

	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	public String getReduceHfAccount() {
		return reduceHfAccount;
	}

	public void setReduceHfAccount(String reduceHfAccount) {
		this.reduceHfAccount = reduceHfAccount;
	}

	public String getReduceEmpName() {
		return reduceEmpName;
	}

	public void setReduceEmpName(String reduceEmpName) {
		this.reduceEmpName = reduceEmpName;
	}

	public String getReduceSummary() {
		return reduceSummary;
	}

	public void setReduceSummary(String reduceSummary) {
		this.reduceSummary = reduceSummary;
	}

	public BigDecimal getReduceMonthPayAmount() {
		return reduceMonthPayAmount;
	}

	public void setReduceMonthPayAmount(BigDecimal reduceMonthPayAmount) {
		this.reduceMonthPayAmount = reduceMonthPayAmount;
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
		return "HfBasicChangeList{" +
			", basicChangeListId=" + basicChangeListId +
			", serialNum=" + serialNum +
			", changeType=" + changeType +
			", empName=" + empName +
			", empCardNum=" + empCardNum +
			", hfAccount=" + hfAccount +
			", monthlyPayAmount=" + monthlyPayAmount +
			", baseAmount=" + baseAmount +
			", ratio=" + ratio +
			", reduceHfAccount=" + reduceHfAccount +
			", reduceEmpName=" + reduceEmpName +
			", reduceSummary=" + reduceSummary +
			", reduceMonthPayAmount=" + reduceMonthPayAmount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
