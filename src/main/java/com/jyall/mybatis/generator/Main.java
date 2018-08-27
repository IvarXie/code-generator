package com.jyall.mybatis.generator;

import com.jyall.mybatis.generator.core.Configure;
import com.jyall.mybatis.generator.core.DataProcessor;
import com.jyall.mybatis.generator.model.JerseyMethodInfo;
import com.jyall.mybatis.generator.model.JerseyMethodModel;
import com.jyall.mybatis.generator.model.JerseyModel;
import com.jyall.mybatis.generator.model.Table;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.jyall.mybatis.generator.core.StringUtils.underLineToCamel3;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @Creation Date : 2018-05-18 9:57
 * Email is xie.wenbo@jyall.com
 * Copyright is 家园网络科技有限公司
 */
public class Main {
    public static void main(String[] args) {
        generateTable();
//        generateController("http://10.10.39.32:8082/v1/api");
    }

    /**
     * 生成数据库实体
     */
    private static void generateTable(){
        Configure config = new Configure();
        config.setTargetDir("./src/");
        config.setModelPackage("com.jyall.property.api.pojo");
        config.setBeanJsonPackage("com.jyall.property.api.json");
        config.setExamplePackage("com.jyall.example");
        config.setMapperPackage("com.jyall.mapper");
        config.setPrimaryKey("id");
        Generator generator = new Generator(config);

        String tableNamePattern = "%";
        DataProcessor t = new DataProcessor();
        List<Table> tableInfos =  t.getTableInfos(tableNamePattern);

        try {
            for (Table table : tableInfos) {
                generator.generate(table);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成简单c层代码
     */
    private static void generateController(String apiUrl){
        Configure config = new Configure();
        config.setTargetDir("./src/");
        Generator generator = new Generator(config);
        String result = doGet(apiUrl);
        String[] lineArrs = result.split("\n");
        JerseyMethodModel jerseyMethodModel = new JerseyMethodModel();
        List<JerseyMethodModel> list1 = new ArrayList<>();
        String feignClient = "";
        for(String lines : Arrays.asList(lineArrs).stream().filter(s -> !s.contains("import")&&!s.contains("package")&&!s.contains("*")&&!s.endsWith("{")&&!s.endsWith("}")).collect(Collectors.toList())){
            if(lines.contains("FeignClient")){
                feignClient = lines;
            }else{

                if(lines.contains("@POST")||lines.contains("@GET")||lines.contains("@PUSH")||lines.contains("@PUT")){
                    jerseyMethodModel.setRequestType(lines);
                }else if(StringUtils.isNotBlank(lines)){
                    if(lines.endsWith(";")){
                        lines = lines.replace(";","");
                    }
                    if(lines.contains("@Path(")){
                        jerseyMethodModel.setPath(lines);
                    }else if(lines.contains("@Consumes(")){
                        jerseyMethodModel.setConsumes(lines);
                    }else if(lines.contains("ResponseEntity")){
                        jerseyMethodModel.setMethodInfo(lines.replace(";",""));
                    }
                }else {
                    list1.add(jerseyMethodModel);
                    jerseyMethodModel = new JerseyMethodModel();
                }
            }
        }
        //ResponseEntity<List<DistrictBaseInfoJson>> district_queryAllDistrictsByProperty(DistrictQueryParam arg0);
        Map<String,List<JerseyMethodModel>> map1 = new HashMap<>();
        list1.stream().filter(ll->ll!=null&&StringUtils.isNotBlank(ll.getPath())&&StringUtils.isNotBlank(ll.getMethodInfo())&&StringUtils.isNotBlank(ll.getRequestType())).forEach(ll->{
            String lowerBeanName = ll.getPath().substring(ll.getPath().indexOf("/v1/")+4,ll.getPath().indexOf("/",ll.getPath().indexOf("/v1/")+4));
            String property = "property";
            int startIndex,endIndex;
            if(lowerBeanName.equals(property)){
                startIndex = ll.getPath().indexOf(property)+property.length()+1;
                endIndex = ll.getPath().indexOf("/",ll.getPath().indexOf(property)+property.length()+1);
                if(endIndex>startIndex){
                    lowerBeanName = ll.getPath().substring(startIndex,endIndex);
                }
            }
            JerseyMethodInfo jerseyMethodInfo = new JerseyMethodInfo();
            jerseyMethodInfo.setMethodName(underLineToCamel3(ll.getMethodInfo().trim().substring(ll.getMethodInfo().indexOf(" "),ll.getMethodInfo().indexOf("(")-1)));
            startIndex = ll.getMethodInfo().indexOf("(");
            endIndex = ll.getMethodInfo().lastIndexOf(")")-1;
            endIndex = endIndex<=startIndex?startIndex:endIndex;
            jerseyMethodInfo.setParams(Arrays.asList(ll.getMethodInfo().trim().substring(startIndex,endIndex).split(" "))
                    .stream().filter(s -> s.startsWith("arg")).collect(Collectors.joining()));
            jerseyMethodInfo.setReturnValue(ll.getMethodInfo().trim().substring(ll.getMethodInfo().indexOf("ResponseEntity")-1,ll.getMethodInfo().indexOf(" ")));
            System.out.println(ll.getMethodInfo().trim().substring(startIndex,endIndex));
            jerseyMethodInfo.setMethodParams(ll.getMethodInfo().trim().substring(startIndex,endIndex));
            ll.setJerseyMethodInfo(jerseyMethodInfo);
            ll.setHttpMethod(ll.getRequestType().substring(ll.getRequestType().indexOf("@")+1,ll.getRequestType().length()));
            if(map1.containsKey(lowerBeanName)){
                map1.get(lowerBeanName).add(ll);
            }else{
                List<JerseyMethodModel> methodList = new ArrayList<>();
                methodList.add(ll);
                map1.put(lowerBeanName,methodList);
            }
        });
        String finalFeignClient = feignClient;
        List<JerseyModel> jerseyModels = new ArrayList<>();
        map1.entrySet().stream().forEach(stringListEntry -> {
            JerseyModel jerseyModel = new JerseyModel();
            jerseyModel.setPackageName("com.jyall.property.api");
            jerseyModel.setFeignClient(finalFeignClient);
            jerseyModel.setPath(stringListEntry.getKey());
            jerseyModel.setClassName(com.jyall.mybatis.generator.core.StringUtils.underLineToCamel(com.jyall.mybatis.generator.core.StringUtils.toUpperCaseFirst(stringListEntry.getKey())));
            jerseyModel.setLowerClassName(com.jyall.mybatis.generator.core.StringUtils.toLowerCaseFirst(jerseyModel.getClassName()));
            jerseyModel.setJerseyMethodModelList(stringListEntry.getValue());
            jerseyModels.add(jerseyModel);
        });

        System.out.println(jerseyModels);
        jerseyModels.stream().forEach(jerseyModel -> {
            generator.generateJerseyModel(jerseyModel);
            generator.generateFeignClient(jerseyModel);
        });
    }

    private static String doGet(String apiUrl) {
        HttpGet httpGet = new HttpGet(apiUrl);
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpResponse response = httpClient.execute(httpGet);
            return  EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

