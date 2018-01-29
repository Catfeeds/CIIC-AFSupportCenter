package com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 导入公积金账号
用户更希望使用雇员身份证号+客户编号 定位人员
 * </p>
 */
@TableName("hf_batch_imp_emp_account_detail")
public class HfBatchImpEmpAccountDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="record_id", type= IdType.AUTO)
	private Long recordId;
    /**
     * 雇员姓名
     */
	@TableField("emp_name")
	private String empName;
    /**
     * 雇员编号
     */
	@TableField("emp_num")
	private String empNum;
    /**
     * 身份证号
     */
	@TableField("emp_card_num")
	private String empCardNum;
    /**
     * 客户编号
     */
	@TableField("com_num")
	private String comNum;
    /**
     * 客户名称
     */
	@TableField("com_name")
	private String comName;
    /**
     * 基本公积金账号
     */
	@TableField("basic_hf_account")
	private String basicHfAccount;
    /**
     * 补充公积金账号
     */
	@TableField("addition_hf_account")
	private String additionHfAccount;
    /**
     * 导入状态
     */
	@TableField("imp_state")
	private Integer impState;
    /**
     * 失败原因
     */
	@TableField("fail_reason")
	private String failReason;
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


	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public String getEmpCardNum() {
		return empCardNum;
	}

	public void setEmpCardNum(String empCardNum) {
		this.empCardNum = empCardNum;
	}

	public String getComNum() {
		return comNum;
	}

	public void setComNum(String comNum) {
		this.comNum = comNum;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getBasicHfAccount() {
		return basicHfAccount;
	}

	public void setBasicHfAccount(String basicHfAccount) {
		this.basicHfAccount = basicHfAccount;
	}

	public String getAdditionHfAccount() {
		return additionHfAccount;
	}

	public void setAdditionHfAccount(String additionHfAccount) {
		this.additionHfAccount = additionHfAccount;
	}

	public Integer getImpState() {
		return impState;
	}

	public void setImpState(Integer impState) {
		this.impState = impState;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
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
		return "HfBatchImpEmpAccountDetail{" +
			", recordId=" + recordId +
			", empName=" + empName +
			", empNum=" + empNum +
			", empCardNum=" + empCardNum +
			", comNum=" + comNum +
			", comName=" + comName +
			", basicHfAccount=" + basicHfAccount +
			", additionHfAccount=" + additionHfAccount +
			", impState=" + impState +
			", failReason=" + failReason +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
