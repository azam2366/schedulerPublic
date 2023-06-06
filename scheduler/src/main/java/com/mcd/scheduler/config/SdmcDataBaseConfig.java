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
  basePackages = "com.mcd.scheduler.sms",
  entityManagerFactoryRef = "smsEmailManager",
  transactionManagerRef = "bookTransactionManager")
public class SdmcDataBaseConfig {
	
	
	@Autowired
	Environment env;
	
	
	@Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource bookDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean smsEmailManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(bookDataSource());
        em.setPackagesToScan(
                new String[] { "com.mcd.scheduler.sms"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",   env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect",        env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("spring.jpa.show-sql",       env.getProperty("spring.jpa.show-sql"));
        
        
        em.setJpaPropertyMap(properties);

        return em;
    }
    @Bean
    @Primary
    public PlatformTransactionManager bookTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		smsEmailManager().getObject());
        return transactionManager;
    }

}
