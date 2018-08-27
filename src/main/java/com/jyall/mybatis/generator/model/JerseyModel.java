package com.jyall.mybatis.generator.model;

import java.util.List;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @Creation Date : 2018-05-17 20:07
 * Email is xie.wenbo@jyall.com
 * Copyright is 家园网络科技有限公司
 */
public class JerseyModel {
    private String packageName;

    private String feignClient;

    private String className;

    private String lowerClassName;

    private String path;

    private List<JerseyMethodModel> jerseyMethodModelList;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFeignClient() {
        return feignClient;
    }

    public void setFeignClient(String feignClient) {
        this.feignClient = feignClient;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<JerseyMethodModel> getJerseyMethodModelList() {
        return jerseyMethodModelList;
    }

    public void setJerseyMethodModelList(List<JerseyMethodModel> jerseyMethodModelList) {
        this.jerseyMethodModelList = jerseyMethodModelList;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLowerClassName() {
        return lowerClassName;
    }

    public void setLowerClassName(String lowerClassName) {
        this.lowerClassName = lowerClassName;
    }
}
