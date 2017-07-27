package com.yrkj.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by xuenianxiang on 2017/6/5.
 */
@ConfigurationProperties(prefix = "weixin")
public class WXConfig {

    private String appId;

    private String secret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
