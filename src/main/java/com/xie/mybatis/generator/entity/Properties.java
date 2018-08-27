package com.xie.mybatis.generator.entity;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @CreationDate: 2018-08-27 11:58
 */
public class Properties {
    /**
     * 数据库配置
     */
    private Database database;
    private GenerateTable generateTable;
    private Boolean generateController;
    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public GenerateTable getGenerateTable() {
        return generateTable;
    }

    public void setGenerateTable(GenerateTable generateTable) {
        this.generateTable = generateTable;
    }

    public Boolean getGenerateController() {
        return generateController;
    }

    public void setGenerateController(Boolean generateController) {
        this.generateController = generateController;
    }
}

