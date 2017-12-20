package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;

import java.time.LocalDateTime;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 代收代付发放明细表
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-18
 */
@TableName("hm_agent_business_detail")
public class AgentBusinessDetailPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 代收代付支付明细ID
     */
	@TableId(value="agent_business_detail_id", type= IdType.AUTO)
	private Integer agentBusinessDetailId;
    /**
     * 代收代付发放id
     */
	@TableField("agent_business_id")
	private Integer agentBusinessId;
    /**
     * 公司编号
     */
	@TableField("company_id")
	private Integer companyId;
    /**
     * 公司名称
     */
	private String title;
    /**
     * 保费
     */
	private BigDecimal premium;
    /**
     * 总人数
     */
	@TableField("total_head_count")
	private Integer totalHeadCount;
    /**
     * 备注
     */
	private String remark;
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


	public Integer getAgentBusinessDetailId() {
		return agentBusinessDetailId;
	}

	public void setAgentBusinessDetailId(Integer agentBusinessDetailId) {
		this.agentBusinessDetailId = agentBusinessDetailId;
	}

	public Integer getAgentBusinessId() {
		return agentBusinessId;
	}

	public void setAgentBusinessId(Integer agentBusinessId) {
		this.agentBusinessId = agentBusinessId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPremium() {
		return premium;
	}

	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	public Integer getTotalHeadCount() {
		return totalHeadCount;
	}

	public void setTotalHeadCount(Integer totalHeadCount) {
		this.totalHeadCount = totalHeadCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		return "AgentBusinessDetail{" +
			", agentBusinessDetailId=" + agentBusinessDetailId +
			", agentBusinessId=" + agentBusinessId +
			", companyId=" + companyId +
			", title=" + title +
			", premium=" + premium +
			", totalHeadCount=" + totalHeadCount +
			", remark=" + remark +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
