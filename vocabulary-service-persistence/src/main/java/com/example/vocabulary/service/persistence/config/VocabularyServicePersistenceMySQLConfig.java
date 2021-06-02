package com.example.vocabulary.service.persistence.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
/**
 * @author Payment Gateway Archetype
 */
@Configuration
@ConditionalOnProperty(name = "db", havingValue = "mysql")

public class VocabularyServicePersistenceMySQLConfig {

    private static final Logger logger = LoggerFactory.getLogger(VocabularyServicePersistenceMySQLConfig.class);

    private final Environment env;

    @Autowired
    public VocabularyServicePersistenceMySQLConfig(Environment env) {
        this.env = env;
    }


    @Bean
    public DataSource vocabularyDS() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setPoolName("vocabulary");
        logger.info("Configuring Vocabulary Persistence with a MySQL database");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl(env.getRequiredProperty("vocabulary-service.db.mysql.url"));
        dataSource.setUsername(env.getRequiredProperty("vocabulary-service.db.mysql.user"));
        dataSource.setPassword(env.getRequiredProperty("vocabulary-service.db.mysql.password"));
        if (env.containsProperty("vocabulary-service.db.mysql.pool.maximumPoolSize")) {
            dataSource.setMaximumPoolSize(env.getProperty("vocabulary-service.db.mysql.pool.maximumPoolSize", Integer.class));
        }
        if (env.containsProperty("vocabulary-service.db.mysql.pool.connectionTimeout")) {
            dataSource.setConnectionTimeout(env.getProperty("vocabulary-service.db.mysql.pool.connectionTimeout", Long.class));
        }
        if (env.containsProperty("vocabulary-service.db.mysql.pool.maxLifetime")) {
            dataSource.setMaxLifetime(env.getProperty("vocabulary-service.db.mysql.pool.maxLifetime", Long.class));
        }
        if (env.containsProperty("vocabulary-service.db.mysql.pool.idleTimeout")) {
            dataSource.setIdleTimeout(env.getProperty("vocabulary-service.db.mysql.pool.idleTimeout", Long.class));
        }
        return dataSource;
    }

    @Bean
    public HibernateJpaVendorAdapter vocabularyVA() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        // TODO: make switchable
//        vendorAdapter.setShowSql(Switches.showSql.isEnabled());
        return vendorAdapter;
    }
}
