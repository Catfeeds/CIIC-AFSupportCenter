package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.payment;


import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 本地社保应付金额交易记录明细表
 * </p>
 */
public class SsPaymentDetailDTO  {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	private Long paymentDetailId;
    /**
     * 外键，大库、独立库账户Id
     */
    private Long comAccountId;
    /**
     * 支付年月,格式yyyyMM
     */
    private String paymentMonth;
    /**
     * 序号
     */
	private String seq;
    /**
     * 支付项目代码
     */
	private Integer paymentItem;
    /**
     * 支付项目名称
     */
	private String paymentItemName;
    /**
     * 基本养老金额
     */
    private BigDecimal basePensionAmount;
    /**
     * 基本医疗金额
     */
	private BigDecimal baseMedicalAmount;
    /**
     * 地方附加医疗金额
     */
	private BigDecimal addMedicalAmount;
    /**
     * 失业保险金额
     */
	private BigDecimal unemploymentAmount;
    /**
     * 工伤保险金额
     */
	private BigDecimal accidentAmount;
    /**
     * 生育保险金额
     */
	private BigDecimal maternityAmount;
    /**
     * 是否有效,0-无效 1-有效
     */
	private Boolean isActive;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */
    private LocalDateTime modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
    private String modifiedBy;


	public Long getPaymentDetailId() {
		return paymentDetailId;
	}

	public void setPaymentDetailId(Long paymentDetailId) {
		this.paymentDetailId = paymentDetailId;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public Integer getPaymentItem() {
		return paymentItem;
	}

	public void setPaymentItem(Integer paymentItem) {
		this.paymentItem = paymentItem;
	}

	public String getPaymentItemName() {
		return paymentItemName;
	}

	public void setPaymentItemName(String paymentItemName) {
		this.paymentItemName = paymentItemName;
	}

	public BigDecimal getBasePensionAmount() {
		return basePensionAmount;
	}

	public void setBasePensionAmount(BigDecimal basePensionAmount) {
		this.basePensionAmount = basePensionAmount;
	}

	public BigDecimal getBaseMedicalAmount() {
		return baseMedicalAmount;
	}

	public void setBaseMedicalAmount(BigDecimal baseMedicalAmount) {
		this.baseMedicalAmount = baseMedicalAmount;
	}

	public BigDecimal getAddMedicalAmount() {
		return addMedicalAmount;
	}

	public void setAddMedicalAmount(BigDecimal addMedicalAmount) {
		this.addMedicalAmount = addMedicalAmount;
	}

	public BigDecimal getUnemploymentAmount() {
		return unemploymentAmount;
	}

	public void setUnemploymentAmount(BigDecimal unemploymentAmount) {
		this.unemploymentAmount = unemploymentAmount;
	}

	public BigDecimal getAccidentAmount() {
		return accidentAmount;
	}

	public void setAccidentAmount(BigDecimal accidentAmount) {
		this.accidentAmount = accidentAmount;
	}

	public BigDecimal getMaternityAmount() {
		return maternityAmount;
	}

	public void setMaternityAmount(BigDecimal maternityAmount) {
		this.maternityAmount = maternityAmount;
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


    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public String getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(String paymentMonth) {
        this.paymentMonth = paymentMonth;
    }
}
