package com.jyall.mybatis.generator.model;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @Creation Date : 2018-05-17 21:31
 * Email is xie.wenbo@jyall.com
 * Copyright is 家园网络科技有限公司
 */
public class JerseyMethodModel {
    private String requestType;

    private String path;

    private String consumes;

    private String methodInfo;

    private String httpMethod;

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    private JerseyMethodInfo jerseyMethodInfo;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getConsumes() {
        return consumes;
    }

    public void setConsumes(String consumes) {
        this.consumes = consumes;
    }

    public String getMethodInfo() {
        return methodInfo;
    }

    public void setMethodInfo(String methodInfo) {
        this.methodInfo = methodInfo;
    }

    public JerseyMethodInfo getJerseyMethodInfo() {
        return jerseyMethodInfo;
    }

    public void setJerseyMethodInfo(JerseyMethodInfo jerseyMethodInfo) {
        this.jerseyMethodInfo = jerseyMethodInfo;
    }
}
