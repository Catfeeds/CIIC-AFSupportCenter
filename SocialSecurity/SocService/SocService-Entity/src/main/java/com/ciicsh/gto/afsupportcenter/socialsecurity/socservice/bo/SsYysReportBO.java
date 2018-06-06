package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SsYysReportBO {
    private String employeeId;
    private String employeeName;
    private BigDecimal baseAmount;
    private Integer category;
    private String changeTypeName;
    private BigDecimal comAmountPension;
    private BigDecimal empAmountPension;
    private BigDecimal onePaymentPension;
    private BigDecimal comAmountMedical;
    private BigDecimal empAmountMedical;
    private BigDecimal comAmountUnemployment;
    private BigDecimal empAmountUnemployment;
    private BigDecimal comCompensatedAmountPension;
    private BigDecimal empCompensatedAmountPension;
    private BigDecimal comCompensatedAmountMedical;
    private BigDecimal empCompensatedAmountMedical;
    private BigDecimal comCompensatedAmountUnemployment;
    private BigDecimal empCompensatedAmountUnemployment;
}
