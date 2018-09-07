package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HfPaymentComListBO {
    private Integer comAccountId;
    private BigDecimal paymentAmount;
    private String companyId;
    private String isCompanyEnjoyAdvance;
    private String payMonth;
    private BigDecimal payAmount;
}
