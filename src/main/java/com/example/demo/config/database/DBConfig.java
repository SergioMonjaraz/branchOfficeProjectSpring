package com.example.demo.config.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "branchOfficeEntity",
        transactionManagerRef = "branchOfficeTransaction",
        basePackages =  {"com.example.demo.repositories"}
)
public class DBConfig {

    public  DBConfig(){
        super();
    }

    @Bean(name = "branchOfficeDataSource")
    @ConfigurationProperties(prefix = "datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "branchOfficeEntity")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("branchOfficeDataSource") DataSource dataSource){
        return builder.dataSource(dataSource).packages("com.example.demo.models").persistenceUnit("branchOffice").build();
    }

    @Bean(name = "branchOfficeTransaction")
    public PlatformTransactionManager scieComTransactionManager(@Qualifier("branchOfficeEntity") EntityManagerFactory branchOfficeEntityManagerFactory) {
        return new JpaTransactionManager(branchOfficeEntityManagerFactory);
    }


}
