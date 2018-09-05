package com.ciicsh.gto.afsupportcenter.fundjob.bo;


import java.io.Serializable;
import java.math.BigDecimal;

public class HfPaymentComProxyBO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer objId;
    private String companyId;
    private String isCompanyEnjoyAdvance;
    private String payMonth;
    private BigDecimal payAmount;

    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getIsCompanyEnjoyAdvance() {
        return isCompanyEnjoyAdvance;
    }

    public void setIsCompanyEnjoyAdvance(String isCompanyEnjoyAdvance) {
        this.isCompanyEnjoyAdvance = isCompanyEnjoyAdvance;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}
