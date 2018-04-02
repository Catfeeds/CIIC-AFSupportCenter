package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

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
 * 企业公积金账户汇缴区县数据表
 * </p>
 *
 * @author likai
 * @since 2018-04-02
 */
@TableName("hf_com_account_payment_bank")
public class HfComAccountPaymentBank implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="com_account_payment_bank_id", type= IdType.AUTO)
	private Long comAccountPaymentBankId;
    /**
     * 企业公积金账户汇缴区县编号
     */
	@TableField("payment_bank_code")
	private Integer paymentBankCode;
    /**
     * 企业公积金账户汇缴区县值
     */
	@TableField("payment_bank_value")
	private String paymentBankValue;
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

    public Long getComAccountPaymentBankId() {
        return comAccountPaymentBankId;
    }

    public void setComAccountPaymentBankId(Long comAccountPaymentBankId) {
        this.comAccountPaymentBankId = comAccountPaymentBankId;
    }

    public Integer getPaymentBankCode() {
        return paymentBankCode;
    }

    public void setPaymentBankCode(Integer paymentBankCode) {
        this.paymentBankCode = paymentBankCode;
    }

    public String getPaymentBankValue() {
        return paymentBankValue;
    }

    public void setPaymentBankValue(String paymentBankValue) {
        this.paymentBankValue = paymentBankValue;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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
	public String toString() {
		return "HfComAccountPaymentBank{" +
			", comAccountPaymentBankId=" + comAccountPaymentBankId +
			", paymentBankCode=" + paymentBankCode +
			", paymentBankValue=" + paymentBankValue +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
