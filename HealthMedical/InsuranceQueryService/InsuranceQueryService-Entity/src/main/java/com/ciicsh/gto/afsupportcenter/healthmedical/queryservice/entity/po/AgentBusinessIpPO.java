package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 代收代付保单表（不提供维护界面，跑脚本录入）
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-29
 */
@TableName("hm_agent_business_ip")
public class AgentBusinessIpPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 代收代付保单ID
     */
    @TableId("agent_business_ip_id")
	private Integer agentBusinessIpId;
    /**
     * 代收代付保单名称
     */
	@TableField("agent_business_ip_name")
	private String agentBusinessIpName;
    /**
     * 保险公司编号
     */
	@TableField("insurance_company_id")
	private Integer insuranceCompanyId;
	@TableField("agent_business_ip_start_date")
	private LocalDate agentBusinessIpStartDate;
    /**
     * 缴费周期：
1 月缴
2 季缴
3 年缴
     */
	@TableField("agent_business_ip_end_date")
	private LocalDate agentBusinessIpEndDate;
    /**
     * 缴费周期：
1 月缴
2 季缴
3 年缴
     */
	@TableField("payment_cycle")
	private Integer paymentCycle;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalTime createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
	private LocalTime modifiedTime;
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


	public Integer getAgentBusinessIpId() {
		return agentBusinessIpId;
	}

	public void setAgentBusinessIpId(Integer agentBusinessIpId) {
		this.agentBusinessIpId = agentBusinessIpId;
	}

	public String getAgentBusinessIpName() {
		return agentBusinessIpName;
	}

	public void setAgentBusinessIpName(String agentBusinessIpName) {
		this.agentBusinessIpName = agentBusinessIpName;
	}

	public Integer getInsuranceCompanyId() {
		return insuranceCompanyId;
	}

	public void setInsuranceCompanyId(Integer insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	}

	public LocalDate getAgentBusinessIpStartDate() {
		return agentBusinessIpStartDate;
	}

	public void setAgentBusinessIpStartDate(LocalDate agentBusinessIpStartDate) {
		this.agentBusinessIpStartDate = agentBusinessIpStartDate;
	}

	public LocalDate getAgentBusinessIpEndDate() {
		return agentBusinessIpEndDate;
	}

	public void setAgentBusinessIpEndDate(LocalDate agentBusinessIpEndDate) {
		this.agentBusinessIpEndDate = agentBusinessIpEndDate;
	}

	public Integer getPaymentCycle() {
		return paymentCycle;
	}

	public void setPaymentCycle(Integer paymentCycle) {
		this.paymentCycle = paymentCycle;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalTime modifiedTime) {
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
		return "AgentBusinessIp{" +
			", agentBusinessIpId=" + agentBusinessIpId +
			", agentBusinessIpName=" + agentBusinessIpName +
			", insuranceCompanyId=" + insuranceCompanyId +
			", agentBusinessIpStartDate=" + agentBusinessIpStartDate +
			", agentBusinessIpEndDate=" + agentBusinessIpEndDate +
			", paymentCycle=" + paymentCycle +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
