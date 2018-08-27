package com.jyall.mybatis.generator.model;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @Creation Date : 2018-05-18 10:40
 * Email is xie.wenbo@jyall.com
 * Copyright is 家园网络科技有限公司
 */
public class JerseyMethodInfo {
    private String returnValue;
    private String methodName;
    private String params;
    private String methodParams;

    public String getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
