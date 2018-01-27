package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 企业公积金账户：存储中智大库、中智外包、独立户企业的账号，含基本公积金和补充公积金
 * </p>
 */
@TableName("hf_com_account_class")
public class HfComAccountClass implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="com_account_class_id", type= IdType.AUTO)
	private Long comAccountClassId;
    /**
     * 外键：企业公积金账户
     */
	@TableField("com_account_id")
	private String comAccountId;
    /**
     * 1 基本公积金、2 补充公积金
     */
	@TableField("hf_type")
	private Integer hfType;
    /**
     * 企业基本补充公积金账号
     */
	@TableField("hf_com_account")
	private String hfComAccount;
    /**
     * 客户缴费起始年月
     */
	@TableField("com_start_month")
	private String comStartMonth;
    /**
     * 截止缴费年月（截单日）
     */
	@TableField("end_month")
	private String endMonth;
    /**
     * 专员操作起始年月，纯粹记录无关联逻辑
     */
	@TableField("operate_start_month")
	private String operateStartMonth;
    /**
     * 账号暂保管，当基本公积金或补充公积金下面无人状态，专员需修改当前状态 1 是 0  否
     */
	@TableField("account_temp_store")
	private Integer accountTempStore;
    /**
     * 王莺说:有小部分客户的基本和补充的末次汇缴月是不相同的,比如有些客户的补充公积金原来中智代缴，后来改成自己缴
     */
	@TableField("com_hf_month")
	private Integer comHfMonth;
    /**
     * 1 销户 2 公司自做 3 转其他代理商
     */
	@TableField("end_type")
	private Integer endType;
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


	public Long getComAccountClassId() {
		return comAccountClassId;
	}

	public void setComAccountClassId(Long comAccountClassId) {
		this.comAccountClassId = comAccountClassId;
	}

	public String getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(String comAccountId) {
		this.comAccountId = comAccountId;
	}

	public Integer getHfType() {
		return hfType;
	}

	public void setHfType(Integer hfType) {
		this.hfType = hfType;
	}

	public String getHfComAccount() {
		return hfComAccount;
	}

	public void setHfComAccount(String hfComAccount) {
		this.hfComAccount = hfComAccount;
	}

	public String getComStartMonth() {
		return comStartMonth;
	}

	public void setComStartMonth(String comStartMonth) {
		this.comStartMonth = comStartMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getOperateStartMonth() {
		return operateStartMonth;
	}

	public void setOperateStartMonth(String operateStartMonth) {
		this.operateStartMonth = operateStartMonth;
	}

	public Integer getAccountTempStore() {
		return accountTempStore;
	}

	public void setAccountTempStore(Integer accountTempStore) {
		this.accountTempStore = accountTempStore;
	}

	public Integer getComHfMonth() {
		return comHfMonth;
	}

	public void setComHfMonth(Integer comHfMonth) {
		this.comHfMonth = comHfMonth;
	}

	public Integer getEndType() {
		return endType;
	}

	public void setEndType(Integer endType) {
		this.endType = endType;
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
		return "HfComAccountClass{" +
			", comAccountClassId=" + comAccountClassId +
			", comAccountId=" + comAccountId +
			", hfType=" + hfType +
			", hfComAccount=" + hfComAccount +
			", comStartMonth=" + comStartMonth +
			", endMonth=" + endMonth +
			", operateStartMonth=" + operateStartMonth +
			", accountTempStore=" + accountTempStore +
			", comHfMonth=" + comHfMonth +
			", endType=" + endType +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
