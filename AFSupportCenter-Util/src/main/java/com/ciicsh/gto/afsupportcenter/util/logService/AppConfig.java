package com.ciicsh.gto.afsupportcenter.util.logService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * APP配置类
 *
 * @author sunjian
 * @since 2018-3-8
 */
@Component
public class AppConfig {
    @Value("${app.id}")
    private String appId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}

