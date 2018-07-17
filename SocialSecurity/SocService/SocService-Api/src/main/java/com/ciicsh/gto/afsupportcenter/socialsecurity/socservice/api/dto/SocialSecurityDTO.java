package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;

import java.util.List;

public class SocialSecurityDTO {

    private String pensionAccounts;
    private String cityName;
    private List<SocialSecurityDetailDTO> socialSecurityDetails;

    public String getPensionAccounts() {
        return pensionAccounts;
    }

    public void setPensionAccounts(String pensionAccounts) {
        this.pensionAccounts = pensionAccounts;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<SocialSecurityDetailDTO> getSocialSecurityDetails() {
        return socialSecurityDetails;
    }

    public void setSocialSecurityDetails(List<SocialSecurityDetailDTO> socialSecurityDetails) {
        this.socialSecurityDetails = socialSecurityDetails;
    }
}
