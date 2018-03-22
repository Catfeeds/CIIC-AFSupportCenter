package com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.enumeration;

/**
 * Created by houwanhua on 2018/3/21.
 */
public enum Const {

    APPID("10006003","APPID"),
    SAVECOMTASK("saveComTask","保存企业任务"),
    GETACCOUNTLIST("getAccountList","获取企业账户列表"),
    GETACCOUNTBYCOMPANY("getAccountByCompany","根据公司ID和公积金类别获取公积金账户信息"),
    SOURCE("support-center-housefund-api-service","支持中心公积金API服务");

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
