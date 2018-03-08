package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class HfMonthChargeDiffBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal amount;
    private BigDecimal empAmount;
    private BigDecimal comAmount;
}
