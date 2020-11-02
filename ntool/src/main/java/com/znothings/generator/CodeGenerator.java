package com.znothings.generator;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * 配置文档，https://baomidou.com/config/generator-config.html
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/10/22 6:17 PM
 * @version 1.0
 */
public class CodeGenerator {
    /**
     * 作者
     */
    static String author="zenghh";
    static String templatePath="/templates/mapper.xml.ftl";
    static String projectPath = System.getProperty("usr.dir");
    /**
     * 输出目录
     */
    static String output="";
    static String moduleName="city";
    static String parent="com.platform.modules";

    /**
     * 数据库配置
     */
    static String url ="jdbc:mysql://119.45.207.82:3306/syhd-dev?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&serverTimezone=GMT%2B8";
    static String driverName="com.mysql.cj.jdbc.Driver";
    static String userName="root";
    static String password="123456@Hmxxs!";
    /** 表明用，分割*/
    static String tableName="CITY_INTRODUCE";



    /** 全局策略 */
    public static GlobalConfig globalConfig;
    /** 数据源 */
    public static DataSourceConfig dataSourceConfig;
    /** 包配置 */
    public static PackageConfig packageConfig;
    /** 模版配置 */
    public static TemplateConfig templateConfig;
    /** 自定义配置 */
    public static InjectionConfig injectionConfig;
    /** 策略配置 */
    public static StrategyConfig strategyConfig;

    public static void main(String[] args) {
        init();
        //0.代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        //1.全局配置
        autoGenerator.setGlobalConfig(globalConfig);
        //2.数据库配置
        autoGenerator.setDataSource(dataSourceConfig);
        //3.包配置
        autoGenerator.setPackageInfo(packageConfig);
        //4.自定义配置
        injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        fileOutConfig();
        autoGenerator.setCfg(injectionConfig);
        //5.模版设置
        autoGenerator.setTemplate(templateConfig);
        //6.策略配置
        autoGenerator.setStrategy(strategyConfig);
        //7.设置模版引擎
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        //生成代码
        autoGenerator.execute();

    }

    public static void init(){


        globalConfig = new GlobalConfig();
        output = projectPath + "/src/main/java";
        globalConfig.setOutputDir(output);
        globalConfig.setAuthor(author);
        globalConfig.setSwagger2(true);
        globalConfig.setOpen(false);
        globalConfig.setEntityName("%sEntity");
        globalConfig.setMapperName("%sDao");
        globalConfig.setXmlName("%sDao");
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        //是否覆盖
        globalConfig.setFileOverride(true);


        dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setDriverName(driverName);
        dataSourceConfig.setUsername(userName);
        dataSourceConfig.setPassword(password);

        packageConfig = new PackageConfig();
        packageConfig.setModuleName(moduleName);
        packageConfig.setParent(parent);
        packageConfig.setMapper("dao");

        templateConfig = new TemplateConfig();
        templateConfig.disable(TemplateType.XML);

        strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        // 表名，多个英文逗号分割
        if (!StringUtils.isEmpty(tableName)){
            strategyConfig.setInclude(tableName.split(","));
        }
    }



    public static void fileOutConfig(){
        //自定义文件输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + packageConfig.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(focList);
    }
}
