package com.ciicsh.gto.afsupportcenter.socjob.entity.bo;

import java.math.BigDecimal;

public class PaymentDetailBO {

    private String ssType;
    private BigDecimal comAmountOrigSum;

    public String getSsType() {
        return ssType;
    }

    public void setSsType(String ssType) {
        this.ssType = ssType;
    }

    public BigDecimal getComAmountOrigSum() {
        return comAmountOrigSum;
    }

    public void setComAmountOrigSum(BigDecimal comAmountOrigSum) {
        this.comAmountOrigSum = comAmountOrigSum;
    }
}
