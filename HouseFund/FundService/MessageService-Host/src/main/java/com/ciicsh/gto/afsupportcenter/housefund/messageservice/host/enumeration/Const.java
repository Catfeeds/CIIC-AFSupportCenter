package com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.enumeration;

/**
 * Created by houwanhua on 2018/3/21.
 */
public enum Const {

    APPID("10006017","APPID"),
    SOURCE("support-center-housefund-message-service","支持中心公积金消息服务");

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
