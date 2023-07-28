package com.example.minio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author：陈迎鹏
 * @Description TODO
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
    private String url;


    private String userName;

    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
