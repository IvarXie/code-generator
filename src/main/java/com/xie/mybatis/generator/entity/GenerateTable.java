package com.xie.mybatis.generator.entity;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @CreationDate: 2018-08-27 15:46
 */
public class GenerateTable {
    /**
     * 开关 true开启 false关闭
     */
    private boolean enable;
    /**
     * 生成目录位置
     */
    private String targetDir;
    /**
     * model文件位置
     */
    private String modelPackage;
    /**
     * mapper文件位置
     */
    private String mapperPackage;
    /**
     * example文件位置
     */
    private String examplePackage;
    /**
     * json文件位置
     */
    private String beanJsonPackage;
    /**
     * 主键
     */
    private String primaryKey;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getExamplePackage() {
        return examplePackage;
    }

    public void setExamplePackage(String examplePackage) {
        this.examplePackage = examplePackage;
    }

    public String getBeanJsonPackage() {
        return beanJsonPackage;
    }

    public void setBeanJsonPackage(String beanJsonPackage) {
        this.beanJsonPackage = beanJsonPackage;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }
}
