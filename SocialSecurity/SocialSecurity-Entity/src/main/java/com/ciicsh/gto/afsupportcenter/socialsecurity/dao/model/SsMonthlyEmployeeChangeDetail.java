package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * 雇员月度变更表明细
 */
@Table(name = "SS_MonthlyEmployeeChangeDetail")
public class SsMonthlyEmployeeChangeDetail extends BasicModel implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "MonthlyEmployeeChangeDetailId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monthlyEmployeeChangeDetailId;

    /**
     * 外键,关联到变更主表
     */
    @Column(name = "MonthlyEmployeeChangeId")
    private Long monthlyEmployeeChangeId;

    /**
     * 外键，员工Id
     */
    @Column(name = "EmployeeId")
    private String employeeId;

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
    @Column(name = "SocialSecurityType")
    private Byte socialSecurityType;

    /**
     * 社保险种名称
     */
    @Column(name = "SocialSecurityName")
    private String socialSecurityName;

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
    @Column(name = "ComCompensatedAmount")
    private BigDecimal comCompensatedAmount;

    /**
     * 雇员补缴金额
     */
    @Column(name = "EmpCompensatedAmount")
    private BigDecimal empCompensatedAmount;

    /**
     * 一次性支付
     */
    @Column(name = "OnePayment")
    private BigDecimal onePayment;

    /**
     * 创建者用户Id
     */
    @Column(name = "CreateBy")
    private String createBy;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id
     *
     * @return MonthlyEmployeeChangeDetailId - 记录Id
     */
    public Long getMonthlyEmployeeChangeDetailId() {
        return monthlyEmployeeChangeDetailId;
    }

    /**
     * 设置记录Id
     *
     * @param monthlyEmployeeChangeDetailId 记录Id
     */
    public void setMonthlyEmployeeChangeDetailId(Long monthlyEmployeeChangeDetailId) {
        this.monthlyEmployeeChangeDetailId = monthlyEmployeeChangeDetailId;
    }

    /**
     * 获取外键,关联到变更主表
     *
     * @return MonthlyEmployeeChangeId - 外键,关联到变更主表
     */
    public Long getMonthlyEmployeeChangeId() {
        return monthlyEmployeeChangeId;
    }

    /**
     * 设置外键,关联到变更主表
     *
     * @param monthlyEmployeeChangeId 外键,关联到变更主表
     */
    public void setMonthlyEmployeeChangeId(Long monthlyEmployeeChangeId) {
        this.monthlyEmployeeChangeId = monthlyEmployeeChangeId;
    }

    /**
     * 获取外键，员工Id
     *
     * @return EmployeeId - 外键，员工Id
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * 设置外键，员工Id
     *
     * @param employeeId 外键，员工Id
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
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
     * @return SocialSecurityType - 社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     */
    public Byte getSocialSecurityType() {
        return socialSecurityType;
    }

    /**
     * 设置社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     *
     * @param socialSecurityType 社保险种类型，取自全局数据字典表gtobasicdb.DicItem
     */
    public void setSocialSecurityType(Byte socialSecurityType) {
        this.socialSecurityType = socialSecurityType;
    }

    /**
     * 获取社保险种名称
     *
     * @return SocialSecurityName - 社保险种名称
     */
    public String getSocialSecurityName() {
        return socialSecurityName;
    }

    /**
     * 设置社保险种名称
     *
     * @param socialSecurityName 社保险种名称
     */
    public void setSocialSecurityName(String socialSecurityName) {
        this.socialSecurityName = socialSecurityName == null ? null : socialSecurityName.trim();
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
     * @return ComCompensatedAmount - 客户补缴金额
     */
    public BigDecimal getComCompensatedAmount() {
        return comCompensatedAmount;
    }

    /**
     * 设置客户补缴金额
     *
     * @param comCompensatedAmount 客户补缴金额
     */
    public void setComCompensatedAmount(BigDecimal comCompensatedAmount) {
        this.comCompensatedAmount = comCompensatedAmount;
    }

    /**
     * 获取雇员补缴金额
     *
     * @return EmpCompensatedAmount - 雇员补缴金额
     */
    public BigDecimal getEmpCompensatedAmount() {
        return empCompensatedAmount;
    }

    /**
     * 设置雇员补缴金额
     *
     * @param empCompensatedAmount 雇员补缴金额
     */
    public void setEmpCompensatedAmount(BigDecimal empCompensatedAmount) {
        this.empCompensatedAmount = empCompensatedAmount;
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
     * 获取创建者用户Id
     *
     * @return CreateBy - 创建者用户Id
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建者用户Id
     *
     * @param createBy 创建者用户Id
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }
}