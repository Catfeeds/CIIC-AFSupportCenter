package com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.enumeration;

/**
 * Created by houwanhua on 2018/3/21.
 */
public enum Const {

    SAVECOMTASK("saveComTask","保存企业任务"),
    GETACCOUNTLIST("getAccountList","获取企业账户列表"),
    GETACCOUNTBYCOMPANY("getAccountByCompany","根据公司ID获取公积金账户信息");

    Const(String key, String value){
        this.key = key;
        this.value = value;
    };

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
