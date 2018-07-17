package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SsEmpLastPaymentBO {

    private String ssTypeName;
    private BigDecimal comAmount;
    private BigDecimal empAmount;
    private BigDecimal subTotalAmount;
    private BigDecimal comRatio;
    private BigDecimal empRatio;
    private BigDecimal baseAmount;
}
