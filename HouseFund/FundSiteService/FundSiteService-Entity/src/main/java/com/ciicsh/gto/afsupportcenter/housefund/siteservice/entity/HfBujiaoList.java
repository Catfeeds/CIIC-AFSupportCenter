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
 * 基本公积金补缴清册
 * </p>
 */
@TableName("hf_bujiao_list")
public class HfBujiaoList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="bujiao_list_id", type= IdType.AUTO)
	private Long bujiaoListId;
    /**
     * 公积金类型:基本补充
            
     */
	@TableField("hf_type")
	private Integer hfType;
    /**
     * 序号
     */
	@TableField("serial_num")
	private Integer serialNum;
    /**
     * 职工公积金账号
     */
	@TableField("hf_emp_account")
	private String hfEmpAccount;
    /**
     * 姓名
     */
	@TableField("emp_name")
	private String empName;
    /**
     * 身份证号码
     */
	@TableField("emp_card_num")
	private String empCardNum;
    /**
     * 补缴原因
     */
	@TableField("bujiao_reason")
	private String bujiaoReason;
    /**
     * 期间
     */
	private String period;
    /**
     * 缴存比例
     */
	private BigDecimal ratio;
    /**
     * 月补缴金额
     */
	@TableField("monthly_pay_amount")
	private BigDecimal monthlyPayAmount;
    /**
     * 补缴金额小计
     */
	@TableField("bujiao_sub_total")
	private BigDecimal bujiaoSubTotal;
    /**
     * 补缴金额合计
     */
	@TableField("bujiao_total")
	private BigDecimal bujiaoTotal;
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


	public Long getBujiaoListId() {
		return bujiaoListId;
	}

	public void setBujiaoListId(Long bujiaoListId) {
		this.bujiaoListId = bujiaoListId;
	}

	public Integer getHfType() {
		return hfType;
	}

	public void setHfType(Integer hfType) {
		this.hfType = hfType;
	}

	public Integer getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}

	public String getHfEmpAccount() {
		return hfEmpAccount;
	}

	public void setHfEmpAccount(String hfEmpAccount) {
		this.hfEmpAccount = hfEmpAccount;
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

	public String getBujiaoReason() {
		return bujiaoReason;
	}

	public void setBujiaoReason(String bujiaoReason) {
		this.bujiaoReason = bujiaoReason;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	public BigDecimal getMonthlyPayAmount() {
		return monthlyPayAmount;
	}

	public void setMonthlyPayAmount(BigDecimal monthlyPayAmount) {
		this.monthlyPayAmount = monthlyPayAmount;
	}

	public BigDecimal getBujiaoSubTotal() {
		return bujiaoSubTotal;
	}

	public void setBujiaoSubTotal(BigDecimal bujiaoSubTotal) {
		this.bujiaoSubTotal = bujiaoSubTotal;
	}

	public BigDecimal getBujiaoTotal() {
		return bujiaoTotal;
	}

	public void setBujiaoTotal(BigDecimal bujiaoTotal) {
		this.bujiaoTotal = bujiaoTotal;
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
		return "HfBujiaoList{" +
			", bujiaoListId=" + bujiaoListId +
			", hfType=" + hfType +
			", serialNum=" + serialNum +
			", hfEmpAccount=" + hfEmpAccount +
			", empName=" + empName +
			", empCardNum=" + empCardNum +
			", bujiaoReason=" + bujiaoReason +
			", period=" + period +
			", ratio=" + ratio +
			", monthlyPayAmount=" + monthlyPayAmount +
			", bujiaoSubTotal=" + bujiaoSubTotal +
			", bujiaoTotal=" + bujiaoTotal +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
