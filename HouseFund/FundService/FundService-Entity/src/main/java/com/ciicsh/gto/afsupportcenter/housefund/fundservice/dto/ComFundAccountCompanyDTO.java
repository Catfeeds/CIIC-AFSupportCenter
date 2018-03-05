package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto;

import java.util.Date;

/**
 * 公司公积金账户绑定的客户信息
 */
public class ComFundAccountCompanyDTO {

    /**
     * 客户编号
     */
    private String companyId;


    /**
     * 客户名称
     */
    private String companyName;


    /**
     * 客户经理
     */
    private String accountManager;


    /**
     * 绑定时间, 格式: yyyy-MM-dd HH:mm
     */
    private String bindedTime;


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

    public String getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(String accountManager) {
        this.accountManager = accountManager;
    }

    public String getBindedTime() {
        return bindedTime;
    }

    public void setBindedTime(String bindedTime) {
        this.bindedTime = bindedTime;
    }
}
