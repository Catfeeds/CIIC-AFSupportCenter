package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 月度缴费明细报表，系统在每月26日晚上自动生成每月的明细数据，用户可重新生成
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-08
 */
@TableName("ss_month_com_pay")
public class SsMonthComPay implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	@TableId(value="month_com_pay_id", type= IdType.AUTO)
	private Long monthComPayId;
    /**
     * 外键：企业社保账户
     */
	@TableField("com_account_id")
	private Long comAccountId;
    /**
     * 社保缴纳月份，格式为yyyyMM
     */
	@TableField("ss_month")
	private String ssMonth;
    /**
     * 外键：gtofrontdb.CMY_COMPANY
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 计算人用户Id
     */
	@TableField("compute_user_id")
	private String computeUserId;
    /**
     * 计算时间
     */
	@TableField("last_compute_time")
	private LocalDateTime lastComputeTime;
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


	public Long getMonthComPayId() {
		return monthComPayId;
	}

	public void setMonthComPayId(Long monthComPayId) {
		this.monthComPayId = monthComPayId;
	}

	public Long getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(Long comAccountId) {
		this.comAccountId = comAccountId;
	}

	public String getSsMonth() {
		return ssMonth;
	}

	public void setSsMonth(String ssMonth) {
		this.ssMonth = ssMonth;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getComputeUserId() {
		return computeUserId;
	}

	public void setComputeUserId(String computeUserId) {
		this.computeUserId = computeUserId;
	}

	public LocalDateTime getLastComputeTime() {
		return lastComputeTime;
	}

	public void setLastComputeTime(LocalDateTime lastComputeTime) {
		this.lastComputeTime = lastComputeTime;
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
		return "SsMonthComPay{" +
			", monthComPayId=" + monthComPayId +
			", comAccountId=" + comAccountId +
			", ssMonth=" + ssMonth +
			", companyId=" + companyId +
			", computeUserId=" + computeUserId +
			", lastComputeTime=" + lastComputeTime +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
