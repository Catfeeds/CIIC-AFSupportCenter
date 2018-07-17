package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto;

import java.util.List;

public class FundDTO {
    private String fundAccount;
    private String supplementaryFundAccount;
    private String cityName;
    private List<FundDetailDTO> fundDetails;

    public String getFundAccount() {
        return fundAccount;
    }

    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
    }

    public String getSupplementaryFundAccount() {
        return supplementaryFundAccount;
    }

    public void setSupplementaryFundAccount(String supplementaryFundAccount) {
        this.supplementaryFundAccount = supplementaryFundAccount;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<FundDetailDTO> getFundDetails() {
        return fundDetails;
    }

    public void setFundDetails(List<FundDetailDTO> fundDetails) {
        this.fundDetails = fundDetails;
    }
}
