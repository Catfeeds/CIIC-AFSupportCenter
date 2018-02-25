package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SsAnnualAdjustCompanyEmpTempDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long annualAdjustCompanyId;
    private String companyId;
    private String repeatingColumn;
    private String columnCN;
    private String createdBy;

    public Long getAnnualAdjustCompanyId() {
        return annualAdjustCompanyId;
    }

    public void setAnnualAdjustCompanyId(Long annualAdjustCompanyId) {
        this.annualAdjustCompanyId = annualAdjustCompanyId;
    }

    public String getRepeatingColumn() {
        return repeatingColumn;
    }

    public void setRepeatingColumn(String repeatingColumn) {
        this.repeatingColumn = repeatingColumn;
    }

    public String getColumnCN() {
        return columnCN;
    }

    public void setColumnCN(String columnCN) {
        this.columnCN = columnCN;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
