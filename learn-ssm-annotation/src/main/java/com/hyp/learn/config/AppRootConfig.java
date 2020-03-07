package com.hyp.learn.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * mybatis-spring:https://www.cnblogs.com/java-chen-hao/p/11833780.html
 * 这里首先@Configuration表示这是一个配置类，然后加载了一个configs.properties文件，内部定义了数据库连接的相关配置。类中首先利用阿里开发的druid连接池获取数据库连接，接着基于此连接获取了Mybatis中的关键对象sqlSessionFactory，这是Spring关于Mybatis部份的整合，最后注册了一个扫描dao包的扫描器，dao包中用于存放数据持久层的接口，而扫描器扫描后，可以生成这个接口相对应的bean对象，不需要我们再调用getMapper（）方法手动获取。
 */
//Spring的容器不扫描controller;父容器
@ComponentScan(value = "com.hyp.learn", excludeFilters = {
        @Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
})
//@PropertySource("classpath:db.properties")
public class AppRootConfig {

    public static final String DB_FILE = "db.properties";

    public static final String SQL_FILE = "schema.sql";

    public AppRootConfig() {
        System.out.println("AppRootConfig");
    }


    @Bean
    public DataSource getDataSource() {
        Properties properties = null;
        DataSource ds = null;
        try {
            properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource(DB_FILE));
            ds = DruidDataSourceFactory.
                    createDataSource(properties);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return ds;

        //使用嵌入式数据库
//        return new EmbeddedDatabaseBuilder()
//                .addScript(SQL_FILE)
//                .build();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Autowired DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean getFactory(@Autowired DataSource ds) throws IOException {
        //创建SqlSessionFactoryBean对象
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();

        //插件
        // 设置MyBatis分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "h2");
        pageInterceptor.setProperties(properties);
        factory.setPlugins(new Interceptor[]{pageInterceptor});

        //设置数据源
        factory.setDataSource(ds);


        //设置Mapper.xml路径
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperLocations = resolver.getResources("classpath:mapper/*.xml");
        factory.setMapperLocations(mapperLocations);


        //配置configuration
//        Configuration configuration=new Configuration();
//        factory.setConfiguration(configuration);

        //或者配置文件
        Resource pathResource = new ClassPathResource("mybatis-config.xml");
        factory.setConfigLocation(pathResource);


        return factory;
    }

    @Bean
    public MapperScannerConfigurer getScanner() {
        MapperScannerConfigurer scanner = new MapperScannerConfigurer();
        scanner.setBasePackage("com.hyp.learn.dao");
        scanner.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return scanner;
    }
}