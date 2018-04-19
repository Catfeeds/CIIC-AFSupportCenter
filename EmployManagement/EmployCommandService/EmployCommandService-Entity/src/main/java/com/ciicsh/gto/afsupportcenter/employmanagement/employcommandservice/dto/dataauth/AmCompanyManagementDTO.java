package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dto.dataauth;

import java.io.Serializable;
import java.util.List;

public class AmCompanyManagementDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户ID
     */
    private String companyId;
    /**
     * 客户名称
     */
    private String companyName;
    /**
     * 管理方名称
     */
    private String managementName;
    /**
     * 服务中心
     */
    private String serviceCenter;

    /**
     * 已配置的客户
     */
    private List<String> companyIds;


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

    public String getManagementName() {
        return managementName;
    }

    public void setManagementName(String managementName) {
        this.managementName = managementName;
    }

    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    public List<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<String> companyIds) {
        this.companyIds = companyIds;
    }
}
