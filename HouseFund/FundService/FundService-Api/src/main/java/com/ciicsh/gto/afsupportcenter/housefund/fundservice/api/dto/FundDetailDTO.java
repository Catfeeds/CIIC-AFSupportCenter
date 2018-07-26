package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto;

public class FundDetailDTO {
    private String fundType;
    private String basePay;
    private String percentageOfCompanies;
    private String companiesPay;
    private String proportionOfIndividuals;
    private String individualContributions;
    private String total;
    private String status;

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getBasePay() {
        return basePay;
    }

    public void setBasePay(String basePay) {
        this.basePay = basePay;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
