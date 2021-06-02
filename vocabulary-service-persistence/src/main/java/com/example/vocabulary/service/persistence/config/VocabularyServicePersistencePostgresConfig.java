package com.example.vocabulary.service.persistence.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * @author Service Archetype
 */
@Configuration
@ConditionalOnProperty(name = "db", havingValue = "postgres")
public class VocabularyServicePersistencePostgresConfig {

    private static final Logger logger = LoggerFactory.getLogger(VocabularyServicePersistencePostgresConfig.class);

    private final Environment env;

    @Autowired
    public VocabularyServicePersistencePostgresConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource vocabularyDS() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setPoolName("vocabulary");
        logger.info("Configuring Vocabulary Persistence with a PostgreSQL database");
        dataSource.setJdbcUrl(env.getRequiredProperty("vocabulary-service.db.postgres.url"));
        dataSource.setUsername(env.getRequiredProperty("vocabulary-service.db.postgres.user"));
        dataSource.setPassword(env.getRequiredProperty("vocabulary-service.db.postgres.password"));
        if (env.containsProperty("vocabulary-service.db.postgres.pool.maximumPoolSize")) {
            dataSource.setMaximumPoolSize(env.getProperty("vocabulary-service.db.postgres.pool.maximumPoolSize", Integer.class));
        }
        if (env.containsProperty("vocabulary-service.db.postgres.pool.connectionTimeout")) {
            dataSource.setConnectionTimeout(env.getProperty("vocabulary-service.db.postgres.pool.connectionTimeout", Long.class));
        }
        if (env.containsProperty("vocabulary-service.db.postgres.pool.maxLifetime")) {
            dataSource.setMaxLifetime(env.getProperty("vocabulary-service.db.postgres.pool.maxLifetime", Long.class));
        }
        if (env.containsProperty("vocabulary-service.db.postgres.pool.idleTimeout")) {
            dataSource.setIdleTimeout(env.getProperty("vocabulary-service.db.postgres.pool.idleTimeout", Long.class));
        }
        return dataSource;
    }

    @Bean
    public HibernateJpaVendorAdapter vocabularyVA() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
//         vendorAdapter.setShowSql(Switches.showSql.isEnabled());
        return vendorAdapter;
    }
}
