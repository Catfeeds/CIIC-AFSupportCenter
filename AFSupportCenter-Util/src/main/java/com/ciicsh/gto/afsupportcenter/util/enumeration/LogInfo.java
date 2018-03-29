package com.ciicsh.gto.afsupportcenter.util.enumeration;

/**
 *
 */
public enum LogInfo {

    SOURCE_MESSAGE("support-center-social-message-service","支持中心社保消息服务"),
    SOURCE_API("support-center-social-api-service","支持中心社保消息服务1"),
    SOURCE_SITE("support-center-social-site-service","支持中心社保消息服务1");

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
