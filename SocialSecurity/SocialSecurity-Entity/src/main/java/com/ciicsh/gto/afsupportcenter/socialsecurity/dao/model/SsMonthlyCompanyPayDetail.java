package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * 雇员月度费用明细
 */
@Table(name = "SS_MonthlyCompanyPayDetail")
public class SsMonthlyCompanyPayDetail extends BasicModel implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "MonthlyCompanyPayDetailId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monthlyCompanyPayDetailId;

    /**
     * 外键, 关联到月度缴费明细报表
     */
    @Column(name = "MonthlyCompanyPayId")
    private Long monthlyCompanyPayId;

    /**
     * 外键,雇员社保档案Id
     */
    @Column(name = "EmpArchiveId")
    private String empArchiveId;

    /**
     * 社保基数
     */
    @Column(name = "BaseAmount")
    private BigDecimal baseAmount;

    /**
     * 总金额
     */
    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    /**
     * 社保种类：1正常 2补缴 3调整 4 退账
     */
    @Column(name = "Category")
    private Boolean category;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id
     *
     * @return MonthlyCompanyPayDetailId - 记录Id
     */
    public Long getMonthlyCompanyPayDetailId() {
        return monthlyCompanyPayDetailId;
    }

    /**
     * 设置记录Id
     *
     * @param monthlyCompanyPayDetailId 记录Id
     */
    public void setMonthlyCompanyPayDetailId(Long monthlyCompanyPayDetailId) {
        this.monthlyCompanyPayDetailId = monthlyCompanyPayDetailId;
    }

    /**
     * 获取外键, 关联到月度缴费明细报表
     *
     * @return MonthlyCompanyPayId - 外键, 关联到月度缴费明细报表
     */
    public Long getMonthlyCompanyPayId() {
        return monthlyCompanyPayId;
    }

    /**
     * 设置外键, 关联到月度缴费明细报表
     *
     * @param monthlyCompanyPayId 外键, 关联到月度缴费明细报表
     */
    public void setMonthlyCompanyPayId(Long monthlyCompanyPayId) {
        this.monthlyCompanyPayId = monthlyCompanyPayId;
    }

    /**
     * 获取外键,雇员社保档案Id
     *
     * @return EmpArchiveId - 外键,雇员社保档案Id
     */
    public String getEmpArchiveId() {
        return empArchiveId;
    }

    /**
     * 设置外键,雇员社保档案Id
     *
     * @param empArchiveId 外键,雇员社保档案Id
     */
    public void setEmpArchiveId(String empArchiveId) {
        this.empArchiveId = empArchiveId == null ? null : empArchiveId.trim();
    }

    /**
     * 获取社保基数
     *
     * @return BaseAmount - 社保基数
     */
    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    /**
     * 设置社保基数
     *
     * @param baseAmount 社保基数
     */
    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    /**
     * 获取总金额
     *
     * @return TotalAmount - 总金额
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置总金额
     *
     * @param totalAmount 总金额
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取社保种类：1正常 2补缴 3调整 4 退账
     *
     * @return Category - 社保种类：1正常 2补缴 3调整 4 退账
     */
    public Boolean getCategory() {
        return category;
    }

    /**
     * 设置社保种类：1正常 2补缴 3调整 4 退账
     *
     * @param category 社保种类：1正常 2补缴 3调整 4 退账
     */
    public void setCategory(Boolean category) {
        this.category = category;
    }
}