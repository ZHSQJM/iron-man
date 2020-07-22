package com.zhs.mybatis.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/21 15:48
 * @package: com.zhs.mybatis.config
 * @description:
 */
@Configuration
@MapperScan(basePackages = "com.zhs.mybatis.dao.primary",
 sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class PrimaryDataSourceConfigWithAtomikos {


    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "primary")
    @Primary
    public DataSource primaryDataSource(){
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/primary/*.xml"));
         return bean.getObject();
    }

//    @Primary
//    @Bean(name = "primaryTransactionManager")
//    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource")DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }

    @Primary
    @Bean(name = "primarySqlSessionTemplate")
    public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
