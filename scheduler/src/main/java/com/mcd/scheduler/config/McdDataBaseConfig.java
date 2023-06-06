package com.mcd.scheduler.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableJpaRepositories(
  basePackages = "com.mcd.scheduler.mst", 
  entityManagerFactoryRef = "mcdEntityManager", 
  transactionManagerRef = "authorTransactionManager")
public class McdDataBaseConfig {
	
	@Autowired
	Environment env;
	
	@Bean
    @ConfigurationProperties(prefix="spring.author-datasource")
    public DataSource authorDataSource() {
        return DataSourceBuilder.create().build();
    }
   
    @Bean
    public LocalContainerEntityManagerFactoryBean mcdEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(authorDataSource());
        em.setPackagesToScan(
                new String[] { "com.mcd.scheduler.mst.model"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",   env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect",        env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.show-sql",       env.getProperty("spring.jpa.show-sql"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager authorTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		mcdEntityManager().getObject());
        return transactionManager;
    }

}
