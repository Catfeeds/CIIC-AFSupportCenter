package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 企业账户级每月支付信息快照
公积金专员每月记录所有客户的支付情况


 * </p>
 */
@TableName("hf_com_account_month")
public class HfComAccountMonth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="com_account_month_id", type= IdType.AUTO)
	private Long comAccountMonthId;
    /**
     * 外键：企业公积金账户
     */
	@TableField("com_account_id")
	private Long comAccountId;
    /**
     * 多个客户编号使用逗号分开
     */
	@TableField("com_nums")
	private String comNums;
    /**
     * 多个客服经理逗号分开
     */
	@TableField("com_service_manager")
	private String comServiceManager;
    /**
     * 多个服务中心逗号分开
     */
	@TableField("com_service_center")
	private String comServiceCenter;
    /**
     * 公积金企业U盾代管 1 是 0 否
     */
	@TableField("ukey_strore")
	private Integer ukeyStrore;
    /**
     * 月度备注
     */
	@TableField("monthly_remark")
	private String monthlyRemark;
    /**
     * 缴费银行 1 徐汇—X、2 西郊—C、3 东方路—P、4 卢湾—L、5 黄浦—H
     */
	@TableField("payment_bank")
	private Integer paymentBank;
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


	public Long getComAccountMonthId() {
		return comAccountMonthId;
	}

	public void setComAccountMonthId(Long comAccountMonthId) {
		this.comAccountMonthId = comAccountMonthId;
	}

	public Long getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(Long comAccountId) {
		this.comAccountId = comAccountId;
	}

	public String getComNums() {
		return comNums;
	}

	public void setComNums(String comNums) {
		this.comNums = comNums;
	}

	public String getComServiceManager() {
		return comServiceManager;
	}

	public void setComServiceManager(String comServiceManager) {
		this.comServiceManager = comServiceManager;
	}

	public String getComServiceCenter() {
		return comServiceCenter;
	}

	public void setComServiceCenter(String comServiceCenter) {
		this.comServiceCenter = comServiceCenter;
	}

	public Integer getUkeyStrore() {
		return ukeyStrore;
	}

	public void setUkeyStrore(Integer ukeyStrore) {
		this.ukeyStrore = ukeyStrore;
	}

	public String getMonthlyRemark() {
		return monthlyRemark;
	}

	public void setMonthlyRemark(String monthlyRemark) {
		this.monthlyRemark = monthlyRemark;
	}

	public Integer getPaymentBank() {
		return paymentBank;
	}

	public void setPaymentBank(Integer paymentBank) {
		this.paymentBank = paymentBank;
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
		return "HfComAccountMonth{" +
			", comAccountMonthId=" + comAccountMonthId +
			", comAccountId=" + comAccountId +
			", comNums=" + comNums +
			", comServiceManager=" + comServiceManager +
			", comServiceCenter=" + comServiceCenter +
			", ukeyStrore=" + ukeyStrore +
			", monthlyRemark=" + monthlyRemark +
			", paymentBank=" + paymentBank +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
