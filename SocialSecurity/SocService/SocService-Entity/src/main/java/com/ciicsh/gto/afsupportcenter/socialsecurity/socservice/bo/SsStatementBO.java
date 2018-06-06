package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;


import java.time.LocalDateTime;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录）
 * </p>
 *
 * @author wengxk
 * @since 2017-12-08
 */
public class SsStatementBO {

    private static final long serialVersionUID = 1L;


    /**
     * 外键, 企业社保账户名
     */
    private String comAccountName;

    /**
     * 最小差异数（按雇员）
     */
    private Integer minDiffSumByEmp;

    /**
     * 最大差异数（按雇员）
     */
    private Integer maxDiffSumByEmp;

    private  Long monthEmpChangeId;

    public Long getMonthEmpChangeId() {
        return monthEmpChangeId;
    }

    public void setMonthEmpChangeId(Long monthEmpChangeId) {
        this.monthEmpChangeId = monthEmpChangeId;
    }

    /**
     * 对账单Id
     */
    private Long statementId;
    /**
     * 外键, 企业社保账户Id
     */
    private String comAccountId;
    private String companyId;
    /**
     * 社保月份
     */
    private String ssMonth;
    /**
     * 变更汇总表类型:YYSGSY
     */
    private String impFileType;
    /**
     * 导入文件名称
     */
    private String impFileName;
    /**
     * 导入文件路径
     */
    private String impFilePath;
    /**
     * 对账人系统用户Id
     */
    private String statementUserId;
    /**
     * 对账时间
     */
    private LocalDateTime statementTime;
    /**
     * 导入总数量
     */
    private Integer impRecordSum;
    /**
     * 差异总数（按雇员）
     */
    private Integer diffSumByEmp;
    /**
     * 差异总数（按项目）
     */
    private Integer diffSumByItem;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Long getStatementId() {
        return statementId;
    }

    public void setStatementId(Long statementId) {
        this.statementId = statementId;
    }

    public String getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(String comAccountId) {
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


    public Integer getMinDiffSumByEmp() {
        return minDiffSumByEmp;
    }

    public void setMinDiffSumByEmp(Integer minDiffSumByEmp) {
        this.minDiffSumByEmp = minDiffSumByEmp;
    }

    public Integer getMaxDiffSumByEmp() {
        return maxDiffSumByEmp;
    }

    public void setMaxDiffSumByEmp(Integer maxDiffSumByEmp) {
        this.maxDiffSumByEmp = maxDiffSumByEmp;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }
}
