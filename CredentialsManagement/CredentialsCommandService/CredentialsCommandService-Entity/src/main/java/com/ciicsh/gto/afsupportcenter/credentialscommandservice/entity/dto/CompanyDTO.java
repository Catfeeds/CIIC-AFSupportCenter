package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 14:07 2018/2/12
 */
public class CompanyDTO {

    private String companyId;

    private String companyName;

    private String address;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
            "companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", address='" + address + '\'' +
            '}';
    }
}
