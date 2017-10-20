package com.hermes_ecs.java_exercise_rest_api.dao;

import com.google.common.base.Optional;
import com.hermes_ecs.java_exercise_rest_api.domain.Identifiable;

import java.util.List;

public interface Dao<I, T extends Identifiable<I>> {
    Optional<T> find(I id);

    void persist(T entity);

    List<T> findAll();

    boolean exists(I id);
}
