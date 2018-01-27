package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto;

import java.util.List;

/**
 * 企业社保账户信息表 参数 DTO
 */
public class SsComAccountParamDto {
    // 客户ID
    private String companyId;
    /**
     * 账户类型：1:中智大库 2中智外包 3独立户
     */
    private Integer ssAccountType;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getSsAccountType() {
        return ssAccountType;
    }

    public void setSsAccountType(Integer ssAccountType) {
        this.ssAccountType = ssAccountType;
    }
}
