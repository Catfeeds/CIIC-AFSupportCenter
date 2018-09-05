package com.ciicsh.gto.afsupportcenter.socjob.entity.bo;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SsPaymentComProxyBO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer objId;
    private String companyId;
    private String isCompanyEnjoyAdvance;
    private String payMonth;
    private BigDecimal payAmount;
}
