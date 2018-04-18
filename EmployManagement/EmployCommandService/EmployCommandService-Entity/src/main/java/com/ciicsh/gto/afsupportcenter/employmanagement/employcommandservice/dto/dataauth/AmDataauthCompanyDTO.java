package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dto.dataauth;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AmDataauthCompanyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 组织ID
     */
    private Long serviceCenterId;

    /**
     * 客户ID
     */
    private List<String> companyIds = new ArrayList<>();


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getServiceCenterId() {
        return serviceCenterId;
    }

    public void setServiceCenterId(Long serviceCenterId) {
        this.serviceCenterId = serviceCenterId;
    }

    public List<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<String> companyIds) {
        this.companyIds = companyIds;
    }
}
