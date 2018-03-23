package com.ciicsh.gto.afsupportcenter.socialsecurity.messageservice.host.enumeration;

/**
 *
 */
public enum LogInfo {

    SOURCE("support-center-social-message-service","支持中心社保消息服务");

    LogInfo(String key, String value){
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
