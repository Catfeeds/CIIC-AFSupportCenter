package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 对账导入文件
 */
@Table(name = "SS_StatementImp")
public class SsStatementImp implements Serializable {
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
     * 雇员编号
     */
    @Column(name = "EmpNum")
    private String empNum;

    /**
     * 雇员姓名
     */
    @Column(name = "EmpName")
    private String empName;

    /**
     * 变更名称
     */
    @Column(name = "ChangeName")
    private String changeName;

    /**
     * 基数
     */
    @Column(name = "BaseAmount")
    private Integer baseAmount;

    /**
     * 社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     */
    @Column(name = "SSType")
    private Byte SSType;

    /**
     * 社保险种名称
     */
    @Column(name = "SSTypeName")
    private String SSTypeName;

    /**
     * 客户金额
     */
    @Column(name = "ComAmount")
    private BigDecimal comAmount;

    /**
     * 雇员金额
     */
    @Column(name = "EmpAmount")
    private BigDecimal empAmount;

    /**
     * 客户补缴金额
     */
    @Column(name = "ComCompensateAmount")
    private BigDecimal comCompensateAmount;

    /**
     * 雇员补缴金额
     */
    @Column(name = "EmpCompensateAmount")
    private BigDecimal empCompensateAmount;

    /**
     * 一次性支付
     */
    @Column(name = "OnePayment")
    private BigDecimal onePayment;

    /**
     * 是否有效
     */
    @Column(name = "IsActive")
    private Boolean isActive;

    /**
     * 创建时间
     */
    @Column(name = "DataChange_CreateTime")
    private LocalDateTime dataChangeCreateTime;

    /**
     * 更新时间
     */
    @Column(name = "DataChange_lastTime")
    private LocalDateTime dataChangelastTime;

    /**
     * 创建者用户Id
     */
    @Column(name = "CreatedBy")
    private String createdBy;

    /**
     * 更新者用户Id
     */
    @Column(name = "ModifiedBy")
    private String modifiedBy;

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
     * 获取雇员编号
     *
     * @return EmpNum - 雇员编号
     */
    public String getEmpNum() {
        return empNum;
    }

    /**
     * 设置雇员编号
     *
     * @param empNum 雇员编号
     */
    public void setEmpNum(String empNum) {
        this.empNum = empNum == null ? null : empNum.trim();
    }

    /**
     * 获取雇员姓名
     *
     * @return EmpName - 雇员姓名
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * 设置雇员姓名
     *
     * @param empName 雇员姓名
     */
    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    /**
     * 获取变更名称
     *
     * @return ChangeName - 变更名称
     */
    public String getChangeName() {
        return changeName;
    }

    /**
     * 设置变更名称
     *
     * @param changeName 变更名称
     */
    public void setChangeName(String changeName) {
        this.changeName = changeName == null ? null : changeName.trim();
    }

    /**
     * 获取基数
     *
     * @return BaseAmount - 基数
     */
    public Integer getBaseAmount() {
        return baseAmount;
    }

    /**
     * 设置基数
     *
     * @param baseAmount 基数
     */
    public void setBaseAmount(Integer baseAmount) {
        this.baseAmount = baseAmount;
    }

    /**
     * 获取社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     *
     * @return SSType - 社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     */
    public Byte getSSType() {
        return SSType;
    }

    /**
     * 设置社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     *
     * @param SSType 社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     */
    public void setSSType(Byte SSType) {
        this.SSType = SSType;
    }

    /**
     * 获取社保险种名称
     *
     * @return SSTypeName - 社保险种名称
     */
    public String getSSTypeName() {
        return SSTypeName;
    }

    /**
     * 设置社保险种名称
     *
     * @param SSTypeName 社保险种名称
     */
    public void setSSTypeName(String SSTypeName) {
        this.SSTypeName = SSTypeName == null ? null : SSTypeName.trim();
    }

    /**
     * 获取客户金额
     *
     * @return ComAmount - 客户金额
     */
    public BigDecimal getComAmount() {
        return comAmount;
    }

    /**
     * 设置客户金额
     *
     * @param comAmount 客户金额
     */
    public void setComAmount(BigDecimal comAmount) {
        this.comAmount = comAmount;
    }

    /**
     * 获取雇员金额
     *
     * @return EmpAmount - 雇员金额
     */
    public BigDecimal getEmpAmount() {
        return empAmount;
    }

    /**
     * 设置雇员金额
     *
     * @param empAmount 雇员金额
     */
    public void setEmpAmount(BigDecimal empAmount) {
        this.empAmount = empAmount;
    }

    /**
     * 获取客户补缴金额
     *
     * @return ComCompensateAmount - 客户补缴金额
     */
    public BigDecimal getComCompensateAmount() {
        return comCompensateAmount;
    }

    /**
     * 设置客户补缴金额
     *
     * @param comCompensateAmount 客户补缴金额
     */
    public void setComCompensateAmount(BigDecimal comCompensateAmount) {
        this.comCompensateAmount = comCompensateAmount;
    }

    /**
     * 获取雇员补缴金额
     *
     * @return EmpCompensateAmount - 雇员补缴金额
     */
    public BigDecimal getEmpCompensateAmount() {
        return empCompensateAmount;
    }

    /**
     * 设置雇员补缴金额
     *
     * @param empCompensateAmount 雇员补缴金额
     */
    public void setEmpCompensateAmount(BigDecimal empCompensateAmount) {
        this.empCompensateAmount = empCompensateAmount;
    }

    /**
     * 获取一次性支付
     *
     * @return OnePayment - 一次性支付
     */
    public BigDecimal getOnePayment() {
        return onePayment;
    }

    /**
     * 设置一次性支付
     *
     * @param onePayment 一次性支付
     */
    public void setOnePayment(BigDecimal onePayment) {
        this.onePayment = onePayment;
    }

    /**
     * 获取是否有效
     *
     * @return IsActive - 是否有效
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * 设置是否有效
     *
     * @param isActive 是否有效
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取创建时间
     *
     * @return DataChange_CreateTime - 创建时间
     */
    public LocalDateTime getDataChangeCreateTime() {
        return dataChangeCreateTime;
    }

    /**
     * 设置创建时间
     *
     * @param dataChangeCreateTime 创建时间
     */
    public void setDataChangeCreateTime(LocalDateTime dataChangeCreateTime) {
        this.dataChangeCreateTime = dataChangeCreateTime;
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

    /**
     * 获取创建者用户Id
     *
     * @return CreatedBy - 创建者用户Id
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建者用户Id
     *
     * @param createdBy 创建者用户Id
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * 获取更新者用户Id
     *
     * @return ModifiedBy - 更新者用户Id
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * 设置更新者用户Id
     *
     * @param modifiedBy 更新者用户Id
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }
}