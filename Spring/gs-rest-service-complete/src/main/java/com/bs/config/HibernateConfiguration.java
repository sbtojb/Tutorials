package com.bs.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bs.controller.BillGenerationController;
import com.bs.dao.ItemDAO;
import com.bs.service.BillGenerationService;

@Configuration
@EnableTransactionManagement
//@ComponentScan("com.bs")
@ComponentScan(basePackageClasses = {ItemDAO.class,BillGenerationService.class,BillGenerationController.class})
@PropertySource(value = { "classpath:hibernate.properties" })
public class HibernateConfiguration {
 
    @Autowired
    private Environment environment;
 
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
     
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) throws Exception {
        return new JpaTransactionManager(entityManagerFactory);
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean( DataSource dataSource  ) throws Exception {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource( dataSource );
        em.setPackagesToScan("com.bs.entity");
        em.setPersistenceProvider(new HibernatePersistenceProvider());
        Map<String, String> p = new HashMap<String, String>();
        p.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create-drop");
        p.put(org.hibernate.cfg.Environment.HBM2DDL_IMPORT_FILES, "import.sql");
        p.put(org.hibernate.cfg.Environment.DIALECT, environment.getRequiredProperty("hibernate.dialect"));
        p.put(org.hibernate.cfg.Environment.SHOW_SQL, "true");
        em.setJpaPropertyMap(p);
        return em;
    }
    
//    @Bean
//    public BillGenerationService billGenerationService() {
//        return new BillGenerationService();
//    }
//    
//    @Bean
//    public ItemDAO itemDAO() {
//        return new ItemDAO();
//    }
}