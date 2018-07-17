package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;

public class SocialSecurityDetailDTO {

    private String socialSecurityType;
    private String companiesBase;
    private String percentageOfCompanies;
    private String companiesPay;
    private String personalBase;
    private String proportionOfIndividuals;
    private String individualContributions;
    private String total;

    public String getSocialSecurityType() {
        return socialSecurityType;
    }

    public void setSocialSecurityType(String socialSecurityType) {
        this.socialSecurityType = socialSecurityType;
    }

    public String getCompaniesBase() {
        return companiesBase;
    }

    public void setCompaniesBase(String companiesBase) {
        this.companiesBase = companiesBase;
    }

    public String getPercentageOfCompanies() {
        return percentageOfCompanies;
    }

    public void setPercentageOfCompanies(String percentageOfCompanies) {
        this.percentageOfCompanies = percentageOfCompanies;
    }

    public String getCompaniesPay() {
        return companiesPay;
    }

    public void setCompaniesPay(String companiesPay) {
        this.companiesPay = companiesPay;
    }

    public String getPersonalBase() {
        return personalBase;
    }

    public void setPersonalBase(String personalBase) {
        this.personalBase = personalBase;
    }

    public String getProportionOfIndividuals() {
        return proportionOfIndividuals;
    }

    public void setProportionOfIndividuals(String proportionOfIndividuals) {
        this.proportionOfIndividuals = proportionOfIndividuals;
    }

    public String getIndividualContributions() {
        return individualContributions;
    }

    public void setIndividualContributions(String individualContributions) {
        this.individualContributions = individualContributions;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
