package com.hermes_ecs.java_exercise_rest_api.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan
public class PersistenceConfigurationTest {
    private static final String PERSISTENCE_UNIT_NAME = "wattoApiDataStoreTest";

    @Bean
    public EntityManagerFactory createEntityManagerFactory() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        return entityManagerFactory;
    }

    @Bean
    public EntityManager createEntityManager(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    @Bean
    public PlatformTransactionManager createTransactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}