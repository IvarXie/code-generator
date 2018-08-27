package com.xie.mybatis.generator.entity;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @CreationDate: 2018-08-27 15:03
 */
public class Database{
    private String url;
    private String driver;
    private String username;
    private String password;
    private int poolSize;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }
}