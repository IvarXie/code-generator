package com.xie.mybatis.generator;

import com.xie.mybatis.generator.core.Configure;
import com.xie.mybatis.generator.core.DataProcessor;
import com.xie.mybatis.generator.entity.Properties;
import com.xie.mybatis.generator.model.JerseyMethodInfo;
import com.xie.mybatis.generator.model.JerseyMethodModel;
import com.xie.mybatis.generator.model.JerseyModel;
import com.xie.mybatis.generator.model.Table;
import com.xie.mybatis.generator.utils.FileUtils;
import com.xie.mybatis.generator.utils.YamlUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.xie.mybatis.generator.core.StringUtils.underLineToCamel3;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @Creation Date : 2018-05-18 9:57
 */
public class Main {
    private static Properties properties = YamlUtils.getProperties();
    public static void main(String[] args) {
        if(properties.getGenerateTable().isEnable()){
            generateTable("fund");
        }
    }

    /**
     * 生成数据库实体
     */
    private static void generateTable(){
        generateTable(null);
    }

    /**
     * 生成数据库实体
     */
    private static void generateTable(String tableName){
        Configure config = new Configure();
        Generator generator = new Generator(config);
        String tableNamePattern = "%";
        if(StringUtils.isNotBlank(tableName)){
            tableNamePattern = tableName;
        }
        DataProcessor t = new DataProcessor();
        List<Table> tableInfos =  t.getTableInfos(tableNamePattern);
        try {
            FileUtils.delFile(config.getTargetDir());
            for (Table table : tableInfos) {
                generator.generate(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

