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
 * 雇员公积金汇缴月份段
 * </p>
 */
@TableName("hf_archive_base_period")
public class HfArchiveBasePeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="archive_base_period_id", type= IdType.AUTO)
	private Long archiveBasePeriodId;
    /**
     * 外键，雇员档案Id
     */
	@TableField("emp_archive_id")
	private Long empArchiveId;
    /**
     * 雇员任务单Id
     */
	@TableField("emp_task_id")
	private Long empTaskId;
    /**
     * 基数
     */
	@TableField("base_amount")
	private BigDecimal baseAmount;
    /**
     * 缴费段开始月份YYYYMM
     */
	@TableField("start_month")
	private String startMonth;
    /**
     * 缴费段截至月份YYYYMM
     */
	@TableField("end_month")
	private String endMonth;
    /**
     * 公积金汇缴月份
     */
	@TableField("hf_month")
	private String hfMonth;
    /**
     * 公积金类型: 1 基本  2 补充
            
     */
	@TableField("hf_type")
	private Integer hfType;
    /**
     * 汇缴方式:1 - 正常 2 - 补缴
     */
	@TableField("remit_way")
	private Integer remitWay;
    /**
     * 比例
     */
	private BigDecimal ratio;
    /**
     * 个人比例
     */
	@TableField("ratio_emp")
	private BigDecimal ratioEmp;
    /**
     * 企业比例
     */
	@TableField("ratio_com")
	private BigDecimal ratioCom;
    /**
     * 金额
     */
	private BigDecimal amount;
    /**
     * 个人金额
     */
	@TableField("amount_emp")
	private BigDecimal amountEmp;
    /**
     * 企业金额
     */
	@TableField("com_amount")
	private BigDecimal comAmount;
    /**
     * 补缴原因
            1 漏缴补缴
            2 少缴补缴
            3 欠缴单位补缴
            4 外省市转入补缴
            5 错缴更正补缴
            6 特殊补缴
            7 账外补缴（特殊补缴）
     */
	@TableField("bujiao_reason")
	private Integer bujiaoReason;
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


	public Long getArchiveBasePeriodId() {
		return archiveBasePeriodId;
	}

	public void setArchiveBasePeriodId(Long archiveBasePeriodId) {
		this.archiveBasePeriodId = archiveBasePeriodId;
	}

	public Long getEmpArchiveId() {
		return empArchiveId;
	}

	public void setEmpArchiveId(Long empArchiveId) {
		this.empArchiveId = empArchiveId;
	}

	public Long getEmpTaskId() {
		return empTaskId;
	}

	public void setEmpTaskId(Long empTaskId) {
		this.empTaskId = empTaskId;
	}

	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getHfMonth() {
		return hfMonth;
	}

	public void setHfMonth(String hfMonth) {
		this.hfMonth = hfMonth;
	}

	public Integer getHfType() {
		return hfType;
	}

	public void setHfType(Integer hfType) {
		this.hfType = hfType;
	}

	public Integer getRemitWay() {
		return remitWay;
	}

	public void setRemitWay(Integer remitWay) {
		this.remitWay = remitWay;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	public BigDecimal getRatioEmp() {
		return ratioEmp;
	}

	public void setRatioEmp(BigDecimal ratioEmp) {
		this.ratioEmp = ratioEmp;
	}

	public BigDecimal getRatioCom() {
		return ratioCom;
	}

	public void setRatioCom(BigDecimal ratioCom) {
		this.ratioCom = ratioCom;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountEmp() {
		return amountEmp;
	}

	public void setAmountEmp(BigDecimal amountEmp) {
		this.amountEmp = amountEmp;
	}

	public BigDecimal getComAmount() {
		return comAmount;
	}

	public void setComAmount(BigDecimal comAmount) {
		this.comAmount = comAmount;
	}

	public Integer getBujiaoReason() {
		return bujiaoReason;
	}

	public void setBujiaoReason(Integer bujiaoReason) {
		this.bujiaoReason = bujiaoReason;
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
		return "HfArchiveBasePeriod{" +
			", archiveBasePeriodId=" + archiveBasePeriodId +
			", empArchiveId=" + empArchiveId +
			", empTaskId=" + empTaskId +
			", baseAmount=" + baseAmount +
			", startMonth=" + startMonth +
			", endMonth=" + endMonth +
			", hfMonth=" + hfMonth +
			", hfType=" + hfType +
			", remitWay=" + remitWay +
			", ratio=" + ratio +
			", ratioEmp=" + ratioEmp +
			", ratioCom=" + ratioCom +
			", amount=" + amount +
			", amountEmp=" + amountEmp +
			", comAmount=" + comAmount +
			", bujiaoReason=" + bujiaoReason +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
