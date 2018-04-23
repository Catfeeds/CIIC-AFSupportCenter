package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 公积金汇缴支付公司名单
 * </p>
 */
@TableName("hf_payment_com")
public class HfPaymentCom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="payment_com_id", type= IdType.AUTO)
	private Long paymentComId;
    /**
     * 外键
     */
    @TableId(value="payment_id", type= IdType.AUTO)
    private Long paymentId;
    @TableId(value="com_account_id", type= IdType.AUTO)
    private Long comAccountId;
    @TableId(value="com_account_class_id", type= IdType.AUTO)
    private Long comAccountClassId;

    /**
     * 公积金类型
     */
	@TableField("hf_type")
	private Integer hfType;
    /**
     * 公司ID
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 缴费银行，来自字典库
     */
	@TableField("payment_bank")
	private String paymentBank;
    /**
     * 汇缴金额
     */
	@TableField("remitted_amount")
	private BigDecimal remittedAmount;
    /**
     * 补缴金额
     */
	@TableField("repair_amount")
	private BigDecimal repairAmount;
    /**
     * 汇缴人数
     */
	@TableField("remitted_count_emp")
	private Integer remittedCountEmp;

    @TableField("remitted_amount_add")
    private BigDecimal remittedAmountAdd;

    @TableField("remitted_amount_reduce")
    private BigDecimal remittedAmountReduce;

    @TableField("remitted_count_emp_add")
    private Integer remittedCountEmpAdd;

    @TableField("remitted_count_emp_reduce")
    private Integer remittedCountEmpReduce;

    @TableField("repair_count_emp")
    private Integer repairCountEmp;

    public BigDecimal getRemittedAmountAdd() {
        return remittedAmountAdd;
    }

    public void setRemittedAmountAdd(BigDecimal remittedAmountAdd) {
        this.remittedAmountAdd = remittedAmountAdd;
    }

    public BigDecimal getRemittedAmountReduce() {
        return remittedAmountReduce;
    }

    public void setRemittedAmountReduce(BigDecimal remittedAmountReduce) {
        this.remittedAmountReduce = remittedAmountReduce;
    }

    public Integer getRemittedCountEmpAdd() {
        return remittedCountEmpAdd;
    }

    public void setRemittedCountEmpAdd(Integer remittedCountEmpAdd) {
        this.remittedCountEmpAdd = remittedCountEmpAdd;
    }

    public Integer getRemittedCountEmpReduce() {
        return remittedCountEmpReduce;
    }

    public void setRemittedCountEmpReduce(Integer remittedCountEmpReduce) {
        this.remittedCountEmpReduce = remittedCountEmpReduce;
    }

    public Integer getRepairCountEmp() {
        return repairCountEmp;
    }

    public void setRepairCountEmp(Integer repairCountEmp) {
        this.repairCountEmp = repairCountEmp;
    }

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

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public Long getComAccountClassId() {
        return comAccountClassId;
    }

    public void setComAccountClassId(Long comAccountClassId) {
        this.comAccountClassId = comAccountClassId;
    }

    public Long getPaymentComId() {
		return paymentComId;
	}

	public void setPaymentComId(Long paymentComId) {
		this.paymentComId = paymentComId;
	}

	public Integer getHfType() {
		return hfType;
	}

	public void setHfType(Integer hfType) {
		this.hfType = hfType;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getPaymentBank() {
		return paymentBank;
	}

	public void setPaymentBank(String paymentBank) {
		this.paymentBank = paymentBank;
	}

	public BigDecimal getRemittedAmount() {
		return remittedAmount;
	}

	public void setRemittedAmount(BigDecimal remittedAmount) {
		this.remittedAmount = remittedAmount;
	}

	public BigDecimal getRepairAmount() {
		return repairAmount;
	}

	public void setRepairAmount(BigDecimal repairAmount) {
		this.repairAmount = repairAmount;
	}

	public Integer getRemittedCountEmp() {
		return remittedCountEmp;
	}

	public void setRemittedCountEmp(Integer remittedCountEmp) {
		this.remittedCountEmp = remittedCountEmp;
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
		return "HfPaymentCom{" +
			", paymentComId=" + paymentComId +
            ", paymentId=" + paymentId +
            ", comAccountId=" + comAccountId +
            ", comAccountClassId=" + comAccountClassId +
			", hfType=" + hfType +
			", companyId=" + companyId +
			", paymentBank=" + paymentBank +
			", remittedAmount=" + remittedAmount +
			", repairAmount=" + repairAmount +
			", remittedCountEmp=" + remittedCountEmp +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}