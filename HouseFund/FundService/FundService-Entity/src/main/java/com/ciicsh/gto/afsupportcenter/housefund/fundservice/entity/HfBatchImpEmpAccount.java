package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 导入公积金账号批次
 * </p>
 */
@TableName("hf_batch_imp_emp_account")
public class HfBatchImpEmpAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="batch_imp_emp_account_id", type= IdType.AUTO)
	private Integer batchImpEmpAccountId;
    /**
     * 批次时间
     */
	@TableField("imp_time")
	private LocalDateTime impTime;
    /**
     * 批次导入人
     */
	@TableField("imp_operator")
	private String impOperator;
    /**
     * 批次备注
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


	public Integer getBatchImpEmpAccountId() {
		return batchImpEmpAccountId;
	}

	public void setBatchImpEmpAccountId(Integer batchImpEmpAccountId) {
		this.batchImpEmpAccountId = batchImpEmpAccountId;
	}

	public LocalDateTime getImpTime() {
		return impTime;
	}

	public void setImpTime(LocalDateTime impTime) {
		this.impTime = impTime;
	}

	public String getImpOperator() {
		return impOperator;
	}

	public void setImpOperator(String impOperator) {
		this.impOperator = impOperator;
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
		return "HfBatchImpEmpAccount{" +
			", batchImpEmpAccountId=" + batchImpEmpAccountId +
			", impTime=" + impTime +
			", impOperator=" + impOperator +
			", remark=" + remark +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
