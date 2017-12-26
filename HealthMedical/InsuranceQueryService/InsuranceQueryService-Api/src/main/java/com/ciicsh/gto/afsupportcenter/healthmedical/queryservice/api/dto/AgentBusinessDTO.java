package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.api.dto;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;

import java.time.LocalDate;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 代收代付发放表
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
@TableName("hm_agent_business")
public class AgentBusinessDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 代收代付保单ID
     */
	@TableField("agent_business_ip_id")
	private Integer agentBusinessIpId;

	/**
	 * 代收代付保单名称
	 */
	@TableField("agent_business_ip_name")
	private String agentBusinessIpName;


    /**
     * 缴费年月（201604）
     */
	@TableField("payment_month")
	private String paymentMonth;
	/**
	 * 保险公司编号
	 */
	@TableId(value="insurance_company_id", type= IdType.AUTO)
	private Integer insuranceCompanyId;
	/**
	 * 保险公司名称
	 */
	@TableField("insurance_company_name")
	private String insuranceCompanyName;
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


	public Integer getAgentBusinessIpId() {
		return agentBusinessIpId;
	}

	public void setAgentBusinessIpId(Integer agentBusinessIpId) {
		this.agentBusinessIpId = agentBusinessIpId;
	}


	public String getPaymentMonth() {
		return paymentMonth;
	}

	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
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

	public String getInsuranceCompanyName() {
		return insuranceCompanyName;
	}

	public void setInsuranceCompanyName(String insuranceCompanyName) {
		this.insuranceCompanyName = insuranceCompanyName;
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
}
