package com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 企业公积金账户：存储中智大库、中智外包、独立户企业的账号，含基本公积金和补充公积金
HF：House Fun
 * </p>
 */
@TableName("hf_com_account")
public class HfComAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="com_account_id", type= IdType.AUTO)
	private Long comAccountId;
    /**
     * 企业账户名称
     */
	@TableField("com_account_name")
	private String comAccountName;
    /**
     * 付款方式:
            1 自付（客户自己汇缴给银行，雇员由中智办理）
            2 我司付款（客户预付）
            3 垫付
     */
	@TableField("payment_way")
	private Integer paymentWay;
    /**
     * 1 独立户 2 大库、3 外包
     */
	@TableField("hf_account_type")
	private Integer hfAccountType;
    /**
     * 客户公积金账户 每月的关账到哪一天1-31
     */
	@TableField("close_day")
	private Integer closeDay;
    /**
     * 公积金企业U盾代管
     */
	@TableField("ukey_store")
	private Integer ukeyStore;
    /**
     * 缴费区县：1 徐汇—X、2 西郊—C、3 东方路—P、4 卢湾—L、5 黄浦—H
     */
	@TableField("payment_bank")
	private Integer paymentBank;
    /**
     * 备注
     */
	private String remark;
    /**
     * 账户状态:0初始 1有效 2 终止
     */
	private Integer state;
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
	@TableField("Column_15")
	private String Column15;


	public Long getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(Long comAccountId) {
		this.comAccountId = comAccountId;
	}

	public String getComAccountName() {
		return comAccountName;
	}

	public void setComAccountName(String comAccountName) {
		this.comAccountName = comAccountName;
	}

	public Integer getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(Integer paymentWay) {
		this.paymentWay = paymentWay;
	}

	public Integer getHfAccountType() {
		return hfAccountType;
	}

	public void setHfAccountType(Integer hfAccountType) {
		this.hfAccountType = hfAccountType;
	}

	public Integer getCloseDay() {
		return closeDay;
	}

	public void setCloseDay(Integer closeDay) {
		this.closeDay = closeDay;
	}

	public Integer getUkeyStore() {
		return ukeyStore;
	}

	public void setUkeyStore(Integer ukeyStore) {
		this.ukeyStore = ukeyStore;
	}

	public Integer getPaymentBank() {
		return paymentBank;
	}

	public void setPaymentBank(Integer paymentBank) {
		this.paymentBank = paymentBank;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public String getColumn15() {
		return Column15;
	}

	public void setColumn15(String Column15) {
		this.Column15 = Column15;
	}

	@Override
	public String toString() {
		return "HfComAccount{" +
			", comAccountId=" + comAccountId +
			", comAccountName=" + comAccountName +
			", paymentWay=" + paymentWay +
			", hfAccountType=" + hfAccountType +
			", closeDay=" + closeDay +
			", ukeyStore=" + ukeyStore +
			", paymentBank=" + paymentBank +
			", remark=" + remark +
			", state=" + state +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			", Column15=" + Column15 +
			"}";
	}
}
