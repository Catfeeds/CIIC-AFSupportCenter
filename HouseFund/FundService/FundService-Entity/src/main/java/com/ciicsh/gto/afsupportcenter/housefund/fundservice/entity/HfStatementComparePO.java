package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 公积金对账主表
 * </p>
 */
@TableName("hf_statement_compare")
public class HfStatementComparePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="statement_compare_id", type= IdType.AUTO)
	private Long statementCompareId;
    /**
     * 公积金月份
     */
	@TableField("hf_month")
	private String hfMonth;
    /**
     * 导入文件路径物理文件名
     */
	@TableField("imp_path")
	private String impPath;
    /**
     * 对账人
     */
	@TableField("compare_man")
	private String compareMan;
    /**
     * 对账时间
     */
	@TableField("compare_time")
	private LocalDateTime compareTime;
    /**
     * 公积金类型: 1 基本  2 补充
            
     */
	@TableField("hf_type")
	private Integer hfType;
    /**
     * 企业公积金账户Id, 关联至HF_ComAccount
     */
	@TableField("com_account_id")
	private Integer comAccountId;
    /**
     * 1 独立户 2 大库、3 外包
     */
	@TableField("hf_account_type")
	private Integer hfAccountType;
    /**
     * 导入记录总数
     */
	@TableField("imp_record_count")
	private Integer impRecordCount;
    /**
     * 差异记录总数
     */
	@TableField("diff_count")
	private Integer diffCount;
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


	public Long getStatementCompareId() {
		return statementCompareId;
	}

	public void setStatementCompareId(Long statementCompareId) {
		this.statementCompareId = statementCompareId;
	}

	public String getHfMonth() {
		return hfMonth;
	}

	public void setHfMonth(String hfMonth) {
		this.hfMonth = hfMonth;
	}

	public String getImpPath() {
		return impPath;
	}

	public void setImpPath(String impPath) {
		this.impPath = impPath;
	}

	public String getCompareMan() {
		return compareMan;
	}

	public void setCompareMan(String compareMan) {
		this.compareMan = compareMan;
	}

	public LocalDateTime getCompareTime() {
		return compareTime;
	}

	public void setCompareTime(LocalDateTime compareTime) {
		this.compareTime = compareTime;
	}

	public Integer getHfType() {
		return hfType;
	}

	public void setHfType(Integer hfType) {
		this.hfType = hfType;
	}

	public Integer getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(Integer comAccountId) {
		this.comAccountId = comAccountId;
	}

	public Integer getHfAccountType() {
		return hfAccountType;
	}

	public void setHfAccountType(Integer hfAccountType) {
		this.hfAccountType = hfAccountType;
	}

	public Integer getImpRecordCount() {
		return impRecordCount;
	}

	public void setImpRecordCount(Integer impRecordCount) {
		this.impRecordCount = impRecordCount;
	}

	public Integer getDiffCount() {
		return diffCount;
	}

	public void setDiffCount(Integer diffCount) {
		this.diffCount = diffCount;
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
		return "HfStatementComparePO{" +
			", statementCompareId=" + statementCompareId +
			", hfMonth=" + hfMonth +
			", impPath=" + impPath +
			", compareMan=" + compareMan +
			", compareTime=" + compareTime +
			", hfType=" + hfType +
			", comAccountId=" + comAccountId +
			", hfAccountType=" + hfAccountType +
			", impRecordCount=" + impRecordCount +
			", diffCount=" + diffCount +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
