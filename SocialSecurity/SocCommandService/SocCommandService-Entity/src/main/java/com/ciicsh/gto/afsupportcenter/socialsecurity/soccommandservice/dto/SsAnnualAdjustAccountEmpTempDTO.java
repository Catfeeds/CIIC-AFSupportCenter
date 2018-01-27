package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import java.io.Serializable;

public class SsAnnualAdjustAccountEmpTempDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long annualAdjustAccountId;
    private String repeatingColumn;
    private String columnCN;
    private String createdBy;

    public Long getAnnualAdjustAccountId() {
        return annualAdjustAccountId;
    }

    public void setAnnualAdjustAccountId(Long annualAdjustAccountId) {
        this.annualAdjustAccountId = annualAdjustAccountId;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
