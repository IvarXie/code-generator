package com.xie.mybatis.generator.utils;

import com.xie.mybatis.generator.entity.Properties;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @CreationDate: 2018-08-27 11:46
 */
public class YamlUtils {
    private static final Yaml yaml = new Yaml();
    private static InputStream inputStream;
    private static final String PATH = "/application.yml";
    static {
        inputStream = YamlUtils.class.getResourceAsStream(PATH);
    }

    public static Properties getProperties(){
        return yaml.loadAs(inputStream,Properties.class);
    }

}
