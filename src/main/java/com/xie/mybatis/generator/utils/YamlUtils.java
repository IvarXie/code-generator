package com.xie.mybatis.generator.utils;

import com.xie.mybatis.generator.entity.Properties;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @CreationDate: 2018-08-27 11:46
 */
public class YamlUtils {
    private static final Yaml yaml = new Yaml();
    private static InputStream inputStream;
    private final String PATH = "/application.yml";
    public YamlUtils() {
        inputStream = this.getClass().getResourceAsStream(PATH);
    }

    public static Properties getMysqlProperties(){
        return yaml.loadAs(inputStream,Properties.class);
    }

}
