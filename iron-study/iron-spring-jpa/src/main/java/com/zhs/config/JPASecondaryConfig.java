//package com.zhs.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//import java.util.Map;
//
///**
// * @project: iron-man
// * @author: zhs
// * @date: 2020/7/14 11:27
// * @package: com.zhs.config
// * @description:
// */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "entityManagerFactorySecondary",
//        transactionManagerRef = "transactionManagerSecondary",
//        basePackages = {"com.zhs.repositroy.secondary"}
//)
//public class JPASecondaryConfig {
//
//    @Autowired
//    private JpaProperties jpaProperties;
//
//
//
//    @Bean(name = "secondaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.secondary")
//    public DataSource primaryDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//
//    @Bean(name ="entityManagerSecondary")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
//        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
//    }
//
//    @Bean(name = "entityManagerFactorySecondary")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder){
//
//        final Map<String, Object> properties = jpaProperties.getHibernateProperties(new HibernateSettings());
//        return builder.dataSource(primaryDataSource()).properties(properties).packages("com.zhs.repositroy.secondary").persistenceUnit("secondaryPersistenceUnit").build();
//    }
//
//    @Bean(name = "transactionManagerSecondary")
//    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder){
//        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
//    }
//}
