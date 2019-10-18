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
     * 根目录
     */
    private String rootPackage;
    /**
     * 实体类开关
     */
    private boolean domain;
    /**
     * manager开关
     */
    private boolean manager;
    /**
     * dao开关
     */
    private boolean dao;
    /**
     * xml开关
     */
    private boolean mapperXml;
    /**
     * query开关
     */
    private boolean query;
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

    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    public boolean isDomain() {
        return domain;
    }

    public void setDomain(boolean domain) {
        this.domain = domain;
    }


    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public boolean isDao() {
        return dao;
    }

    public void setDao(boolean dao) {
        this.dao = dao;
    }

    public boolean isMapperXml() {
        return mapperXml;
    }

    public void setMapperXml(boolean mapperXml) {
        this.mapperXml = mapperXml;
    }

    public boolean isQuery() {
        return query;
    }

    public void setQuery(boolean query) {
        this.query = query;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }
}
