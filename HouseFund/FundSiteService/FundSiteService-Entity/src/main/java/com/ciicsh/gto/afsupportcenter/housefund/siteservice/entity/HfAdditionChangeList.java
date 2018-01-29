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
 * 补充公积金汇缴变更清册
 * </p>
 */
@TableName("hf_addition_change_list")
public class HfAdditionChangeList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="addition_change_list_id", type= IdType.AUTO)
	private Long additionChangeListId;
    /**
     * 序号
     */
	@TableField("serial_num")
	private String serialNum;
    /**
     * 变更类型
     */
	@TableField("change_type")
	private String changeType;
    /**
     * 姓名
     */
	@TableField("emp_name")
	private BigDecimal empName;
    /**
     * 公积金账号
     */
	@TableField("basic_hf_account")
	private String basicHfAccount;
    /**
     * 补充公积金账号
     */
	@TableField("addition_hf_account")
	private String additionHfAccount;
    /**
     * 每月应缴额
     */
	@TableField("monthly_pay_amount")
	private BigDecimal monthlyPayAmount;
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


	public Long getAdditionChangeListId() {
		return additionChangeListId;
	}

	public void setAdditionChangeListId(Long additionChangeListId) {
		this.additionChangeListId = additionChangeListId;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public BigDecimal getEmpName() {
		return empName;
	}

	public void setEmpName(BigDecimal empName) {
		this.empName = empName;
	}

	public String getBasicHfAccount() {
		return basicHfAccount;
	}

	public void setBasicHfAccount(String basicHfAccount) {
		this.basicHfAccount = basicHfAccount;
	}

	public String getAdditionHfAccount() {
		return additionHfAccount;
	}

	public void setAdditionHfAccount(String additionHfAccount) {
		this.additionHfAccount = additionHfAccount;
	}

	public BigDecimal getMonthlyPayAmount() {
		return monthlyPayAmount;
	}

	public void setMonthlyPayAmount(BigDecimal monthlyPayAmount) {
		this.monthlyPayAmount = monthlyPayAmount;
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
		return "HfAdditionChangeList{" +
			", additionChangeListId=" + additionChangeListId +
			", serialNum=" + serialNum +
			", changeType=" + changeType +
			", empName=" + empName +
			", basicHfAccount=" + basicHfAccount +
			", additionHfAccount=" + additionHfAccount +
			", monthlyPayAmount=" + monthlyPayAmount +
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
