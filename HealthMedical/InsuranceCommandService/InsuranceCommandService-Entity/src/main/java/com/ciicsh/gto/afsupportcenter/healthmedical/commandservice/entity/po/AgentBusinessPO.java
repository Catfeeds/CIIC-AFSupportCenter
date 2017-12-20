package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
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
public class AgentBusinessPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 代收代付发放id
     */
	@TableId(value="agent_business_id", type= IdType.AUTO)
	private Integer agentBusinessId;
    /**
     * 代收代付保单ID
     */
	@TableField("agent_business_ip_id")
	private Integer agentBusinessIpId;
    /**
     * 缴费年月（201604）
     */
	@TableField("payment_month")
	private String paymentMonth;
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


	public Integer getAgentBusinessId() {
		return agentBusinessId;
	}

	public void setAgentBusinessId(Integer agentBusinessId) {
		this.agentBusinessId = agentBusinessId;
	}

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
		return "AgentBusiness{" +
			", agentBusinessId=" + agentBusinessId +
			", agentBusinessIpId=" + agentBusinessIpId +
			", paymentMonth=" + paymentMonth +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
