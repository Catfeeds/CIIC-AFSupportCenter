package com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo;

import java.math.BigDecimal;

/**
 * 受理单统计信息
 */
public class AcceptanceStatisticsBO {
    /**
     * 记录总数
     */
    private Integer total;

    /**
     * 公司理赔金额总计
     */
    private BigDecimal totalCompanyMoney;
    /**
     * 保险公司理赔金额总计
     */
    private BigDecimal totalInsuranceCompanyMoney;

    /**
     * 发票张数
     */
    private Integer invoiceTotal;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public BigDecimal getTotalCompanyMoney() {
        return totalCompanyMoney;
    }

    public void setTotalCompanyMoney(BigDecimal totalCompanyMoney) {
        this.totalCompanyMoney = totalCompanyMoney;
    }

    public BigDecimal getTotalInsuranceCompanyMoney() {
        return totalInsuranceCompanyMoney;
    }

    public void setTotalInsuranceCompanyMoney(BigDecimal totalInsuranceCompanyMoney) {
        this.totalInsuranceCompanyMoney = totalInsuranceCompanyMoney;
    }

    public Integer getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(Integer invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    @Override
    public String toString() {
        return "AcceptanceStatisticsBO{" +
            "total=" + total +
            ", totalCompanyMoney=" + totalCompanyMoney +
            ", totalInsuranceCompanyMoney=" + totalInsuranceCompanyMoney +
            ", invoiceTotal=" + invoiceTotal +
            '}';
    }
}
