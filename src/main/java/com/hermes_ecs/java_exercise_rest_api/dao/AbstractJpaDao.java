package com.hermes_ecs.java_exercise_rest_api.dao;

import com.google.common.base.Optional;
import com.hermes_ecs.java_exercise_rest_api.domain.Identifiable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class AbstractJpaDao<I, T extends Identifiable<I>> implements Dao<I, T> {

    @PersistenceContext(name = "wattoDataStore")
    private EntityManager em;

    private Class<T> clazz;

    private AbstractJpaDao() {
    }

    protected AbstractJpaDao(Class<T> clazz) {
        this();
        this.clazz = clazz;
    }

    @Override
    public Optional<T> find(I id) {
        return Optional.fromNullable(em.find(clazz, id));
    }

    @Override
    public void persist(T entity) {
        em.persist(entity);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(clazz);
        return em.createQuery(query.select(query.from(clazz))).getResultList();
    }

    @Override
    public boolean exists(I id) {
        return em.find(clazz, id) != null;
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}
