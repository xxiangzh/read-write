package com.xzh.rw.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源
 *
 * @author: 向振华
 * @date: 2020/11/21 14:02
 */
@Configuration
@MapperScan(basePackages = "com.xzh.rw.dao.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class DatabaseConfig {

    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

//    @Value("${mysql.datasource.config-location}")
//    private String configLocation;


    /**
     * 写数据源
     *
     * @Primary 标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。
     */
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource write() {
        return new DruidDataSource();
    }

    /**
     * 读数据源1
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.read1")
    public DataSource read1() {
        return new DruidDataSource();
    }

    /**
     * 读数据源2
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.read2")
    public DataSource read2() {
        return new DruidDataSource();
    }

    /**
     * 多数据源需要自己设置sqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(routingDataSource());
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 实体类对应的位置
        bean.setTypeAliasesPackage(typeAliasesPackage);
        // mybatis的XML的配置
        bean.setMapperLocations(resolver.getResources(mapperLocations));
//        bean.setConfigLocation(resolver.getResource(configLocation));
        return bean.getObject();
    }

    /**
     * 设置事务，事务需要知道当前使用的是哪个数据源才能进行事务处理
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(routingDataSource());
    }

    /**
     * 设置数据源路由，通过该类中的determineCurrentLookupKey决定使用哪个数据源
     */
    @Bean
    public AbstractRoutingDataSource routingDataSource() {
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DbContextHolder.WRITE, write());
        targetDataSources.put(DbContextHolder.READ + "1", read1());
        targetDataSources.put(DbContextHolder.READ + "2", read2());
        proxy.setDefaultTargetDataSource(write());
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }
}
