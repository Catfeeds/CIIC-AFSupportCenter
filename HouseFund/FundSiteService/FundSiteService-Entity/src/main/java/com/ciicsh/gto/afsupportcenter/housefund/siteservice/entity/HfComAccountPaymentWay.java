package com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 企业公积金账户支付方式数据表
 * </p>
 *
 * @author 沈健
 * @since 2018-02-28
 */
@TableName("hf_com_account_payment_way")
public class HfComAccountPaymentWay extends Model<HfComAccountPaymentWay> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="com_account_paymentway_id", type= IdType.AUTO)
	private Long comAccountPaymentwayId;
    /**
     * 企业公积金账户支付方式编号
     */
	@TableField("paymentway_code")
	private Integer paymentwayCode;
    /**
     * 企业公积金账户支付方式值
     */
	@TableField("paymentway_value")
	private String paymentwayValue;
    /**
     * 是否可用
     */
	@TableField("is_active")
    @TableLogic
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private Date createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
	private Date modifiedTime;
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


	public Long getComAccountPaymentwayId() {
		return comAccountPaymentwayId;
	}

	public void setComAccountPaymentwayId(Long comAccountPaymentwayId) {
		this.comAccountPaymentwayId = comAccountPaymentwayId;
	}

	public Integer getPaymentwayCode() {
		return paymentwayCode;
	}

	public void setPaymentwayCode(Integer paymentwayCode) {
		this.paymentwayCode = paymentwayCode;
	}

	public String getPaymentwayValue() {
		return paymentwayValue;
	}

	public void setPaymentwayValue(String paymentwayValue) {
		this.paymentwayValue = paymentwayValue;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
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
	protected Serializable pkVal() {
		return this.comAccountPaymentwayId;
	}

	@Override
	public String toString() {
		return "HfComAccountPaymentWay{" +
			", comAccountPaymentwayId=" + comAccountPaymentwayId +
			", paymentwayCode=" + paymentwayCode +
			", paymentwayValue=" + paymentwayValue +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
