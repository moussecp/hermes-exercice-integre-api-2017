package com.hermes_ecs.java_exercise_rest_api.dao;


import com.hermes_ecs.java_exercise_rest_api.domain.Product;

public interface ProductDao extends Dao<Long, Product> {
    boolean existsWithSameLabel(String label);

    boolean existsDifferentEntityWithSameLabel(Product product, String label);
}
