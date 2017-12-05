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
 * 对账导入雇员明细
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-05
 */
@TableName("ss_statement_imp")
public class SsStatementImp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="statement_imp_id", type= IdType.AUTO)
	private Long statementImpId;
    /**
     * 外键，对账单Id
     */
	@TableField("statement_id")
	private Long statementId;
    /**
     * 雇员编号
     */
	@TableField("emp_num")
	private String empNum;
    /**
     * 雇员姓名
     */
	@TableField("emp_name")
	private String empName;
    /**
     * 变更名称
     */
	@TableField("change_name")
	private String changeName;
    /**
     * 基数
     */
	@TableField("base_amount")
	private BigDecimal baseAmount;
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
     * 客户金额
     */
	@TableField("com_amount")
	private BigDecimal comAmount;
    /**
     * 雇员金额
     */
	@TableField("emp_amount")
	private BigDecimal empAmount;
    /**
     * 客户补缴金额
     */
	@TableField("com_compensate_amount")
	private BigDecimal comCompensateAmount;
    /**
     * 雇员补缴金额
     */
	@TableField("emp_compensate_amount")
	private BigDecimal empCompensateAmount;
    /**
     * 一次性支付
     */
	@TableField("one_payment")
	private BigDecimal onePayment;
    /**
     * 是否有效
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalDateTime createdTime;
    /**
     * 更新时间
     */
	@TableField("modified_time")
	private LocalDateTime modifiedTime;
    /**
     * 创建者用户Id
     */
	@TableField("created_by")
	private String createdBy;
    /**
     * 更新者用户Id
     */
	@TableField("modified_by")
	private String modifiedBy;


	public Long getStatementImpId() {
		return statementImpId;
	}

	public void setStatementImpId(Long statementImpId) {
		this.statementImpId = statementImpId;
	}

	public Long getStatementId() {
		return statementId;
	}

	public void setStatementId(Long statementId) {
		this.statementId = statementId;
	}

	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
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

	public BigDecimal getComCompensateAmount() {
		return comCompensateAmount;
	}

	public void setComCompensateAmount(BigDecimal comCompensateAmount) {
		this.comCompensateAmount = comCompensateAmount;
	}

	public BigDecimal getEmpCompensateAmount() {
		return empCompensateAmount;
	}

	public void setEmpCompensateAmount(BigDecimal empCompensateAmount) {
		this.empCompensateAmount = empCompensateAmount;
	}

	public BigDecimal getOnePayment() {
		return onePayment;
	}

	public void setOnePayment(BigDecimal onePayment) {
		this.onePayment = onePayment;
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
		return "SsStatementImp{" +
			", statementImpId=" + statementImpId +
			", statementId=" + statementId +
			", empNum=" + empNum +
			", empName=" + empName +
			", changeName=" + changeName +
			", baseAmount=" + baseAmount +
			", ssType=" + ssType +
			", ssTypeName=" + ssTypeName +
			", comAmount=" + comAmount +
			", empAmount=" + empAmount +
			", comCompensateAmount=" + comCompensateAmount +
			", empCompensateAmount=" + empCompensateAmount +
			", onePayment=" + onePayment +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
