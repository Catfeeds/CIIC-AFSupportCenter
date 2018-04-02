package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.enumeration;

/**
 *
 */
public enum LogInfo {

    SOURCE("support-center-social-site-service","支持中心社保站点服务");

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
