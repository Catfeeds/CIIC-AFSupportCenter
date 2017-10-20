package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 对账导入文件
 */
@Table(name = "SS_StatementImp")
public class SsStatementImp extends BasicModel implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "StatementImpId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statementImpId;

    /**
     * 外键，对账单Id
     */
    @Column(name = "StatementId")
    private Long statementId;

    /**
     * 更新时间
     */
    @Column(name = "DataChange_lastTime")
    private LocalDateTime dataChangelastTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id
     *
     * @return StatementImpId - 记录Id
     */
    public Long getStatementImpId() {
        return statementImpId;
    }

    /**
     * 设置记录Id
     *
     * @param statementImpId 记录Id
     */
    public void setStatementImpId(Long statementImpId) {
        this.statementImpId = statementImpId;
    }

    /**
     * 获取外键，对账单Id
     *
     * @return StatementId - 外键，对账单Id
     */
    public Long getStatementId() {
        return statementId;
    }

    /**
     * 设置外键，对账单Id
     *
     * @param statementId 外键，对账单Id
     */
    public void setStatementId(Long statementId) {
        this.statementId = statementId;
    }

    /**
     * 获取更新时间
     *
     * @return DataChange_lastTime - 更新时间
     */
    public LocalDateTime getDataChangelastTime() {
        return dataChangelastTime;
    }

    /**
     * 设置更新时间
     *
     * @param dataChangelastTime 更新时间
     */
    public void setDataChangelastTime(LocalDateTime dataChangelastTime) {
        this.dataChangelastTime = dataChangelastTime;
    }
}