package com.ciicsh.gto.afsupportcenter.fundjob.bo;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 雇员级信息 调用财务接口用
 * </p>
 *
 * @author zhangxj
 * @since 2017-12-08
 */
public class HfMonthChargeBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer objId;
    private String employeeId;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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
