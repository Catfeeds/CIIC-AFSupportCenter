package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto;

/**
 * 企业公积金账户信息表 参数 DTO
 */
public class HfComAccountParamDTO {
    // 客户ID
    private String companyId;
    /**
     * 账户类型：1:中智大库 2中智外包 3独立户
     */
    private Integer hfAccountType;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getHfAccountType() {
        return hfAccountType;
    }

    public void setHfAccountType(Integer hfAccountType) {
        this.hfAccountType = hfAccountType;
    }
}
