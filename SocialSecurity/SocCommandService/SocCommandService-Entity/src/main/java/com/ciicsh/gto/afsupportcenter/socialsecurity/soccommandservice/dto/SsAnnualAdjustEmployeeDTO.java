package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import java.io.Serializable;

public class SsAnnualAdjustEmployeeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String companyId;
    private Long comAccountId;
    private Integer ssAccountType;
    private Boolean noCollection;
    private String employeeId;
    private String idNum;
    private String ssSerial;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public Integer getSsAccountType() {
        return ssAccountType;
    }

    public void setSsAccountType(Integer ssAccountType) {
        this.ssAccountType = ssAccountType;
    }

    public Boolean getNoCollection() {
        return noCollection;
    }

    public void setNoCollection(Boolean noCollection) {
        this.noCollection = noCollection;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getSsSerial() {
        return ssSerial;
    }

    public void setSsSerial(String ssSerial) {
        this.ssSerial = ssSerial;
    }
}
