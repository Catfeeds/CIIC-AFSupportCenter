package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 本地社保中，中智公司与社保局的对账单（各一条记录）
 */
@Table(name = "SS_Statement")
public class SsStatement extends BasicModel implements Serializable {
    /**
     * 对账单Id
     */
    @Id
    @Column(name = "StatementId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statementId;

    /**
     * 外键, 企业社保账户Id
     */
    @Column(name = "ComAccountId")
    private String comAccountId;

    /**
     * 社保月份
     */
    @Column(name = "SOCMonth")
    private String SOCMonth;

    /**
     * 变更汇总表类型:YYSGSY
     */
    @Column(name = "ImpFileType")
    private String impFileType;

    /**
     * 导入文件名称
     */
    @Column(name = "ImpFileName")
    private String impFileName;

    /**
     * 导入文件路径
     */
    @Column(name = "ImpFilePath")
    private String impFilePath;

    /**
     * 对账人系统用户Id
     */
    @Column(name = "StatementUserId")
    private String statementUserId;

    /**
     * 对账时间
     */
    @Column(name = "StatementTime")
    private LocalDateTime statementTime;

    /**
     * 导入总数量
     */
    @Column(name = "ImpRecordSum")
    private Integer impRecordSum;

    /**
     * 差异总数（按雇员）
     */
    @Column(name = "DiffSumByEmp")
    private Integer diffSumByEmp;

    /**
     * 差异总数（按项目）
     */
    @Column(name = "DiffSumByItem")
    private Integer diffSumByItem;

    private static final long serialVersionUID = 1L;

    /**
     * 获取对账单Id
     *
     * @return StatementId - 对账单Id
     */
    public Long getStatementId() {
        return statementId;
    }

    /**
     * 设置对账单Id
     *
     * @param statementId 对账单Id
     */
    public void setStatementId(Long statementId) {
        this.statementId = statementId;
    }

    /**
     * 获取外键, 企业社保账户Id
     *
     * @return ComAccountId - 外键, 企业社保账户Id
     */
    public String getComAccountId() {
        return comAccountId;
    }

    /**
     * 设置外键, 企业社保账户Id
     *
     * @param comAccountId 外键, 企业社保账户Id
     */
    public void setComAccountId(String comAccountId) {
        this.comAccountId = comAccountId == null ? null : comAccountId.trim();
    }

    /**
     * 获取社保月份
     *
     * @return SOCMonth - 社保月份
     */
    public String getSOCMonth() {
        return SOCMonth;
    }

    /**
     * 设置社保月份
     *
     * @param SOCMonth 社保月份
     */
    public void setSOCMonth(String SOCMonth) {
        this.SOCMonth = SOCMonth == null ? null : SOCMonth.trim();
    }

    /**
     * 获取变更汇总表类型:YYSGSY
     *
     * @return ImpFileType - 变更汇总表类型:YYSGSY
     */
    public String getImpFileType() {
        return impFileType;
    }

    /**
     * 设置变更汇总表类型:YYSGSY
     *
     * @param impFileType 变更汇总表类型:YYSGSY
     */
    public void setImpFileType(String impFileType) {
        this.impFileType = impFileType == null ? null : impFileType.trim();
    }

    /**
     * 获取导入文件名称
     *
     * @return ImpFileName - 导入文件名称
     */
    public String getImpFileName() {
        return impFileName;
    }

    /**
     * 设置导入文件名称
     *
     * @param impFileName 导入文件名称
     */
    public void setImpFileName(String impFileName) {
        this.impFileName = impFileName == null ? null : impFileName.trim();
    }

    /**
     * 获取导入文件路径
     *
     * @return ImpFilePath - 导入文件路径
     */
    public String getImpFilePath() {
        return impFilePath;
    }

    /**
     * 设置导入文件路径
     *
     * @param impFilePath 导入文件路径
     */
    public void setImpFilePath(String impFilePath) {
        this.impFilePath = impFilePath == null ? null : impFilePath.trim();
    }

    /**
     * 获取对账人系统用户Id
     *
     * @return StatementUserId - 对账人系统用户Id
     */
    public String getStatementUserId() {
        return statementUserId;
    }

    /**
     * 设置对账人系统用户Id
     *
     * @param statementUserId 对账人系统用户Id
     */
    public void setStatementUserId(String statementUserId) {
        this.statementUserId = statementUserId == null ? null : statementUserId.trim();
    }

    /**
     * 获取对账时间
     *
     * @return StatementTime - 对账时间
     */
    public LocalDateTime getStatementTime() {
        return statementTime;
    }

    /**
     * 设置对账时间
     *
     * @param statementTime 对账时间
     */
    public void setStatementTime(LocalDateTime statementTime) {
        this.statementTime = statementTime;
    }

    /**
     * 获取导入总数量
     *
     * @return ImpRecordSum - 导入总数量
     */
    public Integer getImpRecordSum() {
        return impRecordSum;
    }

    /**
     * 设置导入总数量
     *
     * @param impRecordSum 导入总数量
     */
    public void setImpRecordSum(Integer impRecordSum) {
        this.impRecordSum = impRecordSum;
    }

    /**
     * 获取差异总数（按雇员）
     *
     * @return DiffSumByEmp - 差异总数（按雇员）
     */
    public Integer getDiffSumByEmp() {
        return diffSumByEmp;
    }

    /**
     * 设置差异总数（按雇员）
     *
     * @param diffSumByEmp 差异总数（按雇员）
     */
    public void setDiffSumByEmp(Integer diffSumByEmp) {
        this.diffSumByEmp = diffSumByEmp;
    }

    /**
     * 获取差异总数（按项目）
     *
     * @return DiffSumByItem - 差异总数（按项目）
     */
    public Integer getDiffSumByItem() {
        return diffSumByItem;
    }

    /**
     * 设置差异总数（按项目）
     *
     * @param diffSumByItem 差异总数（按项目）
     */
    public void setDiffSumByItem(Integer diffSumByItem) {
        this.diffSumByItem = diffSumByItem;
    }
}