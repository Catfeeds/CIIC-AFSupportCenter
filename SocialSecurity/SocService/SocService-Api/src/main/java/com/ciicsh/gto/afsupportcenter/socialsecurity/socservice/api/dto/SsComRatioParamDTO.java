package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;

public class SsComRatioParamDTO {
    /**
     * 客户编号
     */
    private String companyId;
    /**
     * 账户类型
     */
    private int payAccountType;
    /**
     * 工伤年月
     */
    private String effectiveMonth;
    /**
     * 城市（默认上海）
     */
    private String cityCode;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getPayAccountType() {
        return payAccountType;
    }

    public void setPayAccountType(int payAccountType) {
        this.payAccountType = payAccountType;
    }

    public String getEffectiveMonth() {
        return effectiveMonth;
    }

    public void setEffectiveMonth(String effectiveMonth) {
        this.effectiveMonth = effectiveMonth;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}