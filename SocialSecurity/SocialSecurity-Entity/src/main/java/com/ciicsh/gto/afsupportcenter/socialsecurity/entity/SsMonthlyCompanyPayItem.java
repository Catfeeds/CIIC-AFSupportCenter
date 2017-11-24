package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * 雇员月度费用明细项目
 */
@Table(name = "SS_MonthlyCompanyPayItem")
public class SsMonthlyCompanyPayItem implements Serializable {
    /**
     * 记录Id  UUID_SHORT()
     */
    @Id
    @Column(name = "MonthlyCompanyPayItemId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monthlyCompanyPayItemId;

    /**
     * 外键, 关联到雇员月度费用明细表
     */
    @Column(name = "MonthlyCompanyPayDetailId")
    private Long monthlyCompanyPayDetailId;

    /**
     * 险种类型, 取自全局数据字典表gtobasicdb.DicItem
     */
    @Column(name = "SSType")
    private Byte SSType;

    /**
     * 社保险种名称
     */
    @Column(name = "SSName")
    private String SSName;

    /**
     * 企业金额
     */
    @Column(name = "ComAmount")
    private BigDecimal comAmount;

    /**
     * 雇员金额
     */
    @Column(name = "EmpAmount")
    private BigDecimal empAmount;

    /**
     * 合计金额
     */
    @Column(name = "SubTotalAmount")
    private BigDecimal subTotalAmount;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id  UUID_SHORT()
     *
     * @return MonthlyCompanyPayItemId - 记录Id  UUID_SHORT()
     */
    public Long getMonthlyCompanyPayItemId() {
        return monthlyCompanyPayItemId;
    }

    /**
     * 设置记录Id  UUID_SHORT()
     *
     * @param monthlyCompanyPayItemId 记录Id  UUID_SHORT()
     */
    public void setMonthlyCompanyPayItemId(Long monthlyCompanyPayItemId) {
        this.monthlyCompanyPayItemId = monthlyCompanyPayItemId;
    }

    /**
     * 获取外键, 关联到雇员月度费用明细表
     *
     * @return MonthlyCompanyPayDetailId - 外键, 关联到雇员月度费用明细表
     */
    public Long getMonthlyCompanyPayDetailId() {
        return monthlyCompanyPayDetailId;
    }

    /**
     * 设置外键, 关联到雇员月度费用明细表
     *
     * @param monthlyCompanyPayDetailId 外键, 关联到雇员月度费用明细表
     */
    public void setMonthlyCompanyPayDetailId(Long monthlyCompanyPayDetailId) {
        this.monthlyCompanyPayDetailId = monthlyCompanyPayDetailId;
    }

    /**
     * 获取险种类型, 取自全局数据字典表gtobasicdb.DicItem
     *
     * @return SSType - 险种类型, 取自全局数据字典表gtobasicdb.DicItem
     */
    public Byte getSSType() {
        return SSType;
    }

    /**
     * 设置险种类型, 取自全局数据字典表gtobasicdb.DicItem
     *
     * @param SSType 险种类型, 取自全局数据字典表gtobasicdb.DicItem
     */
    public void setSSType(Byte SSType) {
        this.SSType = SSType;
    }

    /**
     * 获取社保险种名称
     *
     * @return SSName - 社保险种名称
     */
    public String getSSName() {
        return SSName;
    }

    /**
     * 设置社保险种名称
     *
     * @param SSName 社保险种名称
     */
    public void setSSName(String SSName) {
        this.SSName = SSName == null ? null : SSName.trim();
    }

    /**
     * 获取企业金额
     *
     * @return ComAmount - 企业金额
     */
    public BigDecimal getComAmount() {
        return comAmount;
    }

    /**
     * 设置企业金额
     *
     * @param comAmount 企业金额
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
     * 获取合计金额
     *
     * @return SubTotalAmount - 合计金额
     */
    public BigDecimal getSubTotalAmount() {
        return subTotalAmount;
    }

    /**
     * 设置合计金额
     *
     * @param subTotalAmount 合计金额
     */
    public void setSubTotalAmount(BigDecimal subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
    }
}