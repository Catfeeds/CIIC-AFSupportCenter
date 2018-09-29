package com.ciicsh.gto.afsupportcenter.fundjob.bo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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

    private Boolean inactive;
    private Long empArchiveId;
    private List<Long> empTaskIdList;
    private Integer hfType;
    private String hfMonth;
    private String hfStopMonth;
    private String ssMonthBelongStart;
    private String ssMonthBelongEnd;
    private String paymentTypes;
    private Integer chgPaymentType;
    private String modifiedBy;
    private Long exceptEmpTaskId;

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

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public Long getEmpArchiveId() {
        return empArchiveId;
    }

    public void setEmpArchiveId(Long empArchiveId) {
        this.empArchiveId = empArchiveId;
    }

    public List<Long> getEmpTaskIdList() {
        return empTaskIdList;
    }

    public void setEmpTaskIdList(List<Long> empTaskIdList) {
        this.empTaskIdList = empTaskIdList;
    }

    public Integer getHfType() {
        return hfType;
    }

    public void setHfType(Integer hfType) {
        this.hfType = hfType;
    }

    public String getHfMonth() {
        return hfMonth;
    }

    public void setHfMonth(String hfMonth) {
        this.hfMonth = hfMonth;
    }

    public String getHfStopMonth() {
        return hfStopMonth;
    }

    public void setHfStopMonth(String hfStopMonth) {
        this.hfStopMonth = hfStopMonth;
    }

    public String getSsMonthBelongStart() {
        return ssMonthBelongStart;
    }

    public void setSsMonthBelongStart(String ssMonthBelongStart) {
        this.ssMonthBelongStart = ssMonthBelongStart;
    }

    public String getSsMonthBelongEnd() {
        return ssMonthBelongEnd;
    }

    public void setSsMonthBelongEnd(String ssMonthBelongEnd) {
        this.ssMonthBelongEnd = ssMonthBelongEnd;
    }

    public String getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(String paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public Integer getChgPaymentType() {
        return chgPaymentType;
    }

    public void setChgPaymentType(Integer chgPaymentType) {
        this.chgPaymentType = chgPaymentType;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getExceptEmpTaskId() {
        return exceptEmpTaskId;
    }

    public void setExceptEmpTaskId(Long exceptEmpTaskId) {
        this.exceptEmpTaskId = exceptEmpTaskId;
    }
}
