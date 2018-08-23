package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HfEmpLastPaymentBO {
    private String companyId;
    private String employeeId;
    private Integer hfType;
    private BigDecimal amount;
    private BigDecimal comAmount;
    private BigDecimal empAmount;
    private BigDecimal base;
    private BigDecimal ratio;
    private BigDecimal ratioCom;
    private BigDecimal ratioEmp;
}
