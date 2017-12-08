package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录）
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
@TableName("ss_statement")
public class SsStatement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 对账单Id
     */
	@TableId(value="statement_id", type= IdType.AUTO)
	private Long statementId;
    /**
     * 外键, 企业社保账户Id
     */
	@TableField("com_account_id")
	private Long comAccountId;
    /**
     * 社保月份
     */
	@TableField("ss_month")
	private String ssMonth;
    /**
     * 变更汇总表类型:YYSGSY
     */
	@TableField("imp_file_type")
	private String impFileType;
    /**
     * 导入文件名称
     */
	@TableField("imp_file_name")
	private String impFileName;
    /**
     * 导入文件路径
     */
	@TableField("imp_file_path")
	private String impFilePath;
    /**
     * 对账人系统用户Id
     */
	@TableField("statement_user_id")
	private String statementUserId;
    /**
     * 对账时间
     */
	@TableField("statement_time")
	private LocalDateTime statementTime;
    /**
     * 导入总数量
     */
	@TableField("imp_record_sum")
	private Integer impRecordSum;
    /**
     * 差异总数（按雇员）
     */
	@TableField("diff_sum_by_emp")
	private Integer diffSumByEmp;
    /**
     * 差异总数（按项目）
     */
	@TableField("diff_sum_by_item")
	private Integer diffSumByItem;
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


	public Long getStatementId() {
		return statementId;
	}

	public void setStatementId(Long statementId) {
		this.statementId = statementId;
	}

	public Long getComAccountId() {
		return comAccountId;
	}

	public void setComAccountId(Long comAccountId) {
		this.comAccountId = comAccountId;
	}

	public String getSsMonth() {
		return ssMonth;
	}

	public void setSsMonth(String ssMonth) {
		this.ssMonth = ssMonth;
	}

	public String getImpFileType() {
		return impFileType;
	}

	public void setImpFileType(String impFileType) {
		this.impFileType = impFileType;
	}

	public String getImpFileName() {
		return impFileName;
	}

	public void setImpFileName(String impFileName) {
		this.impFileName = impFileName;
	}

	public String getImpFilePath() {
		return impFilePath;
	}

	public void setImpFilePath(String impFilePath) {
		this.impFilePath = impFilePath;
	}

	public String getStatementUserId() {
		return statementUserId;
	}

	public void setStatementUserId(String statementUserId) {
		this.statementUserId = statementUserId;
	}

	public LocalDateTime getStatementTime() {
		return statementTime;
	}

	public void setStatementTime(LocalDateTime statementTime) {
		this.statementTime = statementTime;
	}

	public Integer getImpRecordSum() {
		return impRecordSum;
	}

	public void setImpRecordSum(Integer impRecordSum) {
		this.impRecordSum = impRecordSum;
	}

	public Integer getDiffSumByEmp() {
		return diffSumByEmp;
	}

	public void setDiffSumByEmp(Integer diffSumByEmp) {
		this.diffSumByEmp = diffSumByEmp;
	}

	public Integer getDiffSumByItem() {
		return diffSumByItem;
	}

	public void setDiffSumByItem(Integer diffSumByItem) {
		this.diffSumByItem = diffSumByItem;
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
		return "SsStatement{" +
			", statementId=" + statementId +
			", comAccountId=" + comAccountId +
			", ssMonth=" + ssMonth +
			", impFileType=" + impFileType +
			", impFileName=" + impFileName +
			", impFilePath=" + impFilePath +
			", statementUserId=" + statementUserId +
			", statementTime=" + statementTime +
			", impRecordSum=" + impRecordSum +
			", diffSumByEmp=" + diffSumByEmp +
			", diffSumByItem=" + diffSumByItem +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
