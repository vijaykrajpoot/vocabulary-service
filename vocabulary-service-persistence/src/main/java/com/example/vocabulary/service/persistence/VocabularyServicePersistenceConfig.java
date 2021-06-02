package com.example.vocabulary.service.persistence;

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author Archetect
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {
                "com.example.vocabulary.service.persistence.repositories",
        },
        entityManagerFactoryRef = "vocabularyEMF",
        transactionManagerRef = "vocabularyTM")
@ComponentScan
public class VocabularyServicePersistenceConfig {
    private static final Logger logger = LoggerFactory.getLogger(VocabularyServicePersistenceConfig.class );
    private final Environment env;

    @Autowired
    public VocabularyServicePersistenceConfig(final Environment env) {
        this.env = env;
    }

    @Bean(name = "vocabularyTM")
    @Qualifier("vocabulary")
    public JpaTransactionManager vocabularyTM(
    @Qualifier("vocabularyDS") final DataSource dataSource,
    @Qualifier("vocabularyEMF") final EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean vocabularyEMF(
    @Qualifier("vocabularyDS") final DataSource dataSource,
    @Qualifier("vocabularyVA") final JpaVendorAdapter vendorAdapter) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPersistenceUnitName("vocabulary");
        factory.setPackagesToScan(
            "com.example.vocabulary.service.persistence.entities"
        );
        return factory;
    }

    @Bean
    @Qualifier("vocabulary")
    public JdbcTemplate vocabularyJdbcTemplate(@Qualifier("vocabularyDS") final DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean
    @ConditionalOnProperty(name = "liquibase", havingValue = "true", matchIfMissing = true)
    public SpringLiquibase vocabularyLiquibase(@Qualifier("vocabularyDS") final DataSource dataSource) {
        logger.info("Applying Liquibase");
        SpringLiquibase liquibase = new SpringLiquibase();
//         liquibase.setContexts(RuntimeMode.current().name());
        liquibase.setDataSource(dataSource);
        if (env.containsProperty("initdb") || env.containsProperty("gateway.initdb")) {
            liquibase.setDropFirst(true);
        }
        liquibase.setChangeLog("classpath:db/vocabulary-service/changelog-master.xml");
        return liquibase;
    }
}