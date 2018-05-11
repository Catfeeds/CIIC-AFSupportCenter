package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.SalCompany;

/**
 * Created by zhangzhiwen on 2018/3/20.
 */
public class SalCompanyBO extends SalCompany {

    private  String  special;

    // 服务中心
    private String serviceCenter;

    // 客服经理
    private String salManagerName;

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    public String getSalManagerName() {
        return salManagerName;
    }

    public void setSalManagerName(String salManagerName) {
        this.salManagerName = salManagerName;
    }
}
