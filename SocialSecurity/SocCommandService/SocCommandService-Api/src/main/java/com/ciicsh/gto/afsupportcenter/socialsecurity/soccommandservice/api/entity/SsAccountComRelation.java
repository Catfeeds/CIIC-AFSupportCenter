package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业社保账户与公司关系表，当企业社保账户性质是独立库，例如：欧莱雅有10家子公司，在中智就一个社保账户
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-26
 */
public class SsAccountComRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
	private Long accountComRelationId;
    /**
     * 外键, 企业社保账户Id
     */
	private Long comAccountId;
    /**
     * 外键, 客户Id, 来自CMY_COMPANY
     */
	private String companyId;
    /**
     * 是否账户主客户
     */
	private Integer majorCom;
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


	public Long getAccountComRelationId() {
		return accountComRelationId;
	}

	public void setAccountComRelationId(Long accountComRelationId) {
		this.accountComRelationId = accountComRelationId;
	}

	public Long getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(Long comAccountId) {
		this.comAccountId = comAccountId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Integer getMajorCom() {
		return majorCom;
	}

	public void setMajorCom(Integer majorCom) {
		this.majorCom = majorCom;
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
		return "SsAccountComRelation{" +
			", accountComRelationId=" + accountComRelationId +
			", comAccountId=" + comAccountId +
			", companyId=" + companyId +
			", majorCom=" + majorCom +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
