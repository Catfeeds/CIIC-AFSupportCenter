package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 工伤比例
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-26
 */
@TableName("ss_account_ratio")
public class SsAccountRatio implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	private Long ssAccountRatioId;
    /**
     * 外键, 企业社保账户Id
     */
	private Long comAccountId;
    /**
     * 行业类别
     */
	private String industryCategory;
    /**
     * 企业比例
     */
	private BigDecimal comRatio;
    /**
     * 开始月份
     */
	private String startMonth;
    /**
     * 截至月份
     */
	private String endMonth;
    /**
     * 是否可用
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


	public Long getSsAccountRatioId() {
		return ssAccountRatioId;
	}

	public void setSsAccountRatioId(Long ssAccountRatioId) {
		this.ssAccountRatioId = ssAccountRatioId;
	}

	public Long getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(Long comAccountId) {
		this.comAccountId = comAccountId;
	}

	public String getIndustryCategory() {
		return industryCategory;
	}

	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
	}

	public BigDecimal getComRatio() {
		return comRatio;
	}

	public void setComRatio(BigDecimal comRatio) {
		this.comRatio = comRatio;
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
		return "SsAccountRatio{" +
			", ssAccountRatioId=" + ssAccountRatioId +
			", comAccountId=" + comAccountId +
			", industryCategory=" + industryCategory +
			", comRatio=" + comRatio +
			", startMonth=" + startMonth +
			", endMonth=" + endMonth +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
